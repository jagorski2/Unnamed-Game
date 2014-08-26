package com.mygdx.game.screens;

import java.util.ArrayList;
import java.util.List;

import com.app.models.Instance;
import com.app.models.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.BattleInstancePlayer;
import com.mygdx.game.MyGame;
import com.mygdx.game.data.comm.GameDataInterface;
import com.mygdx.game.data.comm.GameDataUtils;
import com.mygdx.game.hex.Board;

public class BoardScreen implements Screen{
	SpriteBatch batch;
	 
	private MyGame game;
	public static ShapeRenderer project_shape_renderer;
	public static PolygonSpriteBatch sprite_batch;
	public static TextureRegion textureGreen;
	public static TextureRegion textureGrass;
	public static short triangles[];
	public static float[] current_hexagon;
	public static int hex_number = 0;
	public static OrthographicCamera camera;
	public static Vector3 touchPos;
	public static Vector3 rightPos;
	BattleInstance battle;
	ShaderProgram shader;
	public static boolean unitIsSelected = false;
	private int viewPortWidth = 300;
	private int viewPortHeight = 300;
	
	public BoardScreen(MyGame game) {
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
		List<Instance> list = gameData.getInstances(user);
		while (list.isEmpty()) {
			this.render(0);
		}
		for (Instance i : list) {
			System.out.println(i.getBoardId());
		}
		System.out.println("success");
		//System.out.println("success");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		if (battle != null && battle.getBoard() != null && battle.getBoard().isReady()) {
			batch.begin();
			battle.drawBattleInstance();
			batch.end();
		}
		project_shape_renderer.end();
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


}
