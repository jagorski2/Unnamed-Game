package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.game.hex.Board;
import com.mygdx.game.hex.HexagonBoardRenderer;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	public static PolygonSpriteBatch sprite_batch;
	public static TextureRegion textureSolid;
	public static float Vert_Array[][];
	public static float hex_center[][];
	public static short triangles[];
	public static PolygonRegion polyReg;
	public static int hex_number = 0;
	//Texture img;
	Board b;
	HexagonBoardRenderer artist;
	ShaderProgram shader;
	
	@Override
	public void create () {
		sprite_batch = new PolygonSpriteBatch(50);
		textureSolid = new TextureRegion(new Texture(Gdx.files.internal("textures/Green.png")),800,800);
		Vert_Array = new float[200][200];
		hex_center = new float[200][200];
		triangles = new short[]{ 0,3,1,			/* Point 0 -> Point 3 -> 1 */
				1,4,2,			/* Point 1 -> Point 4 -> 2 */
				2,5,3,			/* Point 2 -> Point 5 -> 3 */
				3,0,4,			/* Point 3 -> Point 0 -> 4 */
				4,1,5,			/* Point 4 -> Point 1 -> 5 */
				5,2,0 };

		batch = new SpriteBatch();
		b = new Board(-20, -30, 4, 8, 50 );
		artist = new HexagonBoardRenderer(b);
		artist.initHexagons();
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		artist.drawBoard();
		batch.end();
	}
}
