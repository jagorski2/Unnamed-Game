package com.mygdx.game.screens;

import java.util.List;


import com.app.models.Instance;
import com.app.models.Unit;
import com.app.models.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.InstanceTile;
import com.mygdx.game.InstanceUnit;
import com.mygdx.game.MyGame;
import com.mygdx.game.data.comm.GameDataInterface;
import com.mygdx.game.data.comm.GameDataUtils;

public class BoardScreen implements Screen, InputProcessor{
	SpriteBatch batch;
	public InstanceUnit selectedUnit;
	private MyGame game;
	polyBatch = new PolygonSpriteBatch();
	public static ShapeRenderer project_shape_renderer;
	public static PolygonSpriteBatch sprite_batch;
	public static TextureRegion textureGreen;
	public static TextureRegion textureGrass;
	public static short triangles[];
	public static float[] current_hexagon;
	public static int hex_number = 0;
	public static OrthographicCamera camera;
	public static Vector3 rightPos;
	BattleInstance battle;
	ShaderProgram shader;
	public static boolean unitIsSelected = false;
	private int viewPortWidth = 300;
	private int viewPortHeight = 300;
	public OrthographicCamera cam;
	public static int vPW;
	public static int vPH;
	public static InstanceTile clickedHexagon;
	public static InstanceTile selectedHexagon;
	final Matrix4 matrix = new Matrix4();	
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	
	public BoardScreen(MyGame game) {
		vPW = vPH = 25;
		cam = new OrthographicCamera(vPW, vPH * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));			
		cam.position.set(91, 20, 40);
		cam.direction.set(-1, -1, -1);
		cam.near = 1;
		cam.far = 100;		
		matrix.setToRotation(new Vector3(1, 0, 0), 90);
 
		Gdx.input.setInputProcessor(this);
		this.game = game;
		touchPos = new Vector3();
		rightPos = new Vector3();
		sprite_batch = new PolygonSpriteBatch(50);
		textureGreen = new TextureRegion(new Texture(Gdx.files.internal("textures/Green.png")),800,800);
		textureGrass = new TextureRegion(new Texture(Gdx.files.internal("textures/Grass.jpg")),800,800);
		triangles = new short[]{ 0,3,1,			/* Point 0 -> Point 3 -> 1 */
				1,4,2,			/* Point 1 -> Point 4 -> 2 */
				2,5,3,			/* Point 2 -> Point 5 -> 3 */
				3,0,4,			/* Point 3 -> Point 0 -> 4 */
				4,1,5,			/* Point 4 -> Point 1 -> 5 */
				5,2,0 };

		batch = new SpriteBatch();
		project_shape_renderer = new ShapeRenderer();
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, viewPortWidth, viewPortHeight);


		GameDataInterface gameData = GameDataUtils.getInstance();
		
		/*
		 * hardcoded a instance to send to the gameDataInterface
		 */
		Instance instance = new Instance();
		instance.setBoardId(1);
		instance.setInstanceId(1);
		instance.setMissionId(1);
		instance.setTurnId(1);
		battle = gameData.getBattleInstance(instance);
		User user = new User();
		user.setName("fernando");
		user.setPassword("fernando");
		user.setUserId(1);
		
		List<Unit> list = gameData.getUnits(instance,user);
		while (list.isEmpty()) {
			this.render(0);
		}
		for (Unit i : list) {
			System.out.println(i.getUnitId() + ":" + i.getMaxHealth() + ":");
		}
		
		System.out.println("success");
	}

	public void render(float delta) {
		int width = battle.getBoard().getWidth();
		int height= battle.getBoard().getHeight();
		//Tile[][] tiles = BattleBoard.getTiles();
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	cam.update();		

	polyBatch.setProjectionMatrix(cam.combined);
	polyBatch.setTransformMatrix(matrix);
	shapeRend.setProjectionMatrix(cam.combined);
	shapeRend.setTransformMatrix(matrix);
	
	polyBatch.begin();
	for(int z = 0; z < 24; z++) {
		for(int x = 0; x < 12; x++) { 
			tiles[x][z].getPolygonSprite().draw(polyBatch);		
		}
	}
	polyBatch.end();
	for(int z = 0; z < 24; z++) {
		for(int x = 0; x < 12; x++) {
			shapeRend.setColor(Color.BLACK);
			shapeRend.begin(ShapeType.Line);
			shapeRend.polygon(tiles[x][z].getHexagon().getVertices() );
			shapeRend.end();
		}	
	}
	drawOccupiedTiles();
	

	
	checkTileTouched();
	if(selectedHexagon != null){
	//drawSelectedHexagon();
	
	}
}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		last.set(-1, -1, -1);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Ray pickRay = cam.getPickRay(x, y);
		Intersector.intersectRayPlane(pickRay, xzPlane, curr);
 
		if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
			pickRay = cam.getPickRay(last.x, last.y);
			Intersector.intersectRayPlane(pickRay, xzPlane, delta);			
			delta.sub(curr);
			cam.position.add(delta.x, delta.y, delta.z);
			System.out.println(cam.position.x+" "+cam.position.y+" "+cam.position.z );
		}
		last.set(x, y, 0);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	final Plane xzPlane = new Plane(new Vector3(0, 1, 0), 0);
	final Vector3 intersection = new Vector3();
	PolygonSprite lastSelectedTile = null;
	InstanceTile selectedTile = null;
	InstanceTile tiles[][];
	PolygonSprite Closest = null;
	public static Vector3 touchPos = new Vector3();
	
	private void checkTileTouched() {
		//tiles = battle.getBoard().getTile(x, y)getTiles();
		if(Gdx.input.justTouched()) {
			Ray pickRay = cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
			Intersector.intersectRayPlane(pickRay, xzPlane, intersection);
			battle.getBoard().findClickedHexagon(intersection);
			if(selectedHexagon == clickedHexagon){
				unitIsSelected = false;
			}
			
			if(clickedHexagon.isOccupied() && !unitIsSelected){
				selectedUnit = clickedHexagon.getUnit();
				selectedHexagon = clickedHexagon;
				unitIsSelected = true;
				
			}
			else if(clickedHexagon.isOccupied() && unitIsSelected){
				if(selectedHexagon.getUnit().canAttack(selectedHexagon.getUnit().getUnitType())){
					attack(clickedHexagon.getUnit(),selectedHexagon.getUnit());
					System.out.println("ATTACK");
					System.out.println(clickedHexagon.getUnit().getHealth());
					unitIsSelected = false;
					
				}
			}
			else if(unitIsSelected && !clickedHexagon.isOccupied()){
				clickedHexagon.setUnit(selectedUnit);
				clickedHexagon.setOccupied(true);
				selectedHexagon.setOccupied(false);
				unitIsSelected = false;
					
				}
			}
			
		}
	private void attack(InstanceUnit unit, InstanceUnit unit2) {
		int damage = getDamage(unit.getUnitType(), unit2.getUnitType());
		unit.setHealth(unit.getHealth()-damage);
		if(unit.getHealth() < 0){
			unit.setDead(true);
		}
		
	}
	private int getDamage(int unitType, int unitType2) {
		// TODO Auto-generated method stub
		return 23;
	}

}
