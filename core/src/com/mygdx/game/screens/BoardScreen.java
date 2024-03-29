package com.mygdx.game.screens;

import java.util.List;

import com.app.models.Instance;
import com.app.models.Unit;
import com.app.models.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
import com.mygdx.game.MyGame;
import com.mygdx.game.data.comm.GameDataInterface;
import com.mygdx.game.data.comm.GameDataUtils;

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
	private BattleInstance battle;
	private AssetManager manager;

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

	public BattleInstance getBattle() {
		return battle;
	}

	public void setBattle(BattleInstance battle) {
		this.battle = battle;
	}

}
