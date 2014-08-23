package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.data.comm.GameData;
import com.mygdx.game.data.comm.GameDataInterface;
import com.mygdx.game.data.comm.GameDataUtils;
import com.mygdx.game.data.json.User;
import com.mygdx.game.hex.Board;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	 
	public static PolygonSpriteBatch sprite_batch;
	public static ShapeRenderer Project_Shape_Renderer;
	public static TextureRegion textureGreen;
	public static TextureRegion textureGrass;
	public static float Vert_Array[][];
	public static float hex_center[][];
	public static short triangles[];
	public static float[] Current_Hexagon;
	public static PolygonRegion polyReg;
	public static int hex_number = 0;
	public static OrthographicCamera camera;
	public static Vector3 touchPos;
	public static Vector3 rightPos;
	public static boolean unitIsSelected;
	public int ViewPortWidth;
	public int ViewPortHeight;
	BattleInstance battle;
	ShaderProgram shader;
 
	
	
	Board board;			//this is only instantiated when we get a response from the server
	
	@Override
	public void create () {
		touchPos = new Vector3();
		ViewPortWidth = 300;
		ViewPortHeight = 300;
		unitIsSelected = false;
		Project_Shape_Renderer = new ShapeRenderer();
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
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, ViewPortWidth, ViewPortHeight);


		List<BattleInstancePlayer> players = new ArrayList<BattleInstancePlayer>();
		players.add(new BattleInstancePlayer());

		
	
       	GameDataInterface gameData = GameDataUtils.getInstance();
       	board = gameData.getBoard(1);
       	battle = new BattleInstance(board,players);	
        
	}

	@Override
	public void render () {		
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		boardLoopLogic();
		MyGdxGame.Project_Shape_Renderer.end();
	}

	private void boardLoopLogic() {
		if (board.isReady()) {
			batch.begin();
			battle.drawBattleInstance();
			batch.end();
		}
	}
}
