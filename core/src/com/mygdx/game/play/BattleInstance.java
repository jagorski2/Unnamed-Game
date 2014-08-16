package com.mygdx.game.play;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.UnitTypeConstants;
import com.mygdx.game.hex.Board;
import com.mygdx.game.hex.Hexagon;
import com.mygdx.game.hex.HexagonBoardRenderer;


/**
 * This class will contain all references to the instance of a single battle, and maintain the overall game logic.
 * 
 * @author ianno_000
 *
 */
public class BattleInstance 
{
	


	public List<BattleInstancePlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<BattleInstancePlayer> players) {
		this.players = players;
	}
	

	private Board board;								//the board that the battle instance will take place on.
	private HexagonBoardRenderer board_artist;			//instance to draw the board
	private Tile focused_tile; 						//this is the current focus on the player on the screen, it will be highlighted
	private Tile clicked_tile;
	private Tile selected_tile;
	private Unit selected_Unit;
	
	private List<BattleInstancePlayer> players;			//contains all the players that are involved in the battle instance.
	private int turn;								//index in the players data structure to determine the turn.
	private Tile[][] tiles;
	
	/**
	 * There is no reason to have an instance of this class without a board and a list of players...
	 * 
	 */
	public BattleInstance(Board board, List<BattleInstancePlayer> players) 
	{
		this.board = board;
		this.players = players;
		board_artist = new HexagonBoardRenderer(board);
		
		
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public void setBoard(Board board) 
	{
		this.board = board;
	}
	
	public int getTurn() 
	{
		return turn;
	}
	
	public void setTurn(int turn) 
	{
		this.turn = turn;
	}
	
	/*
	 * Draw all of the assets/components of this BattleInstance Object 
	 */
	public void drawBattleInstance() 
	{
		if((Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))){// && MyGdxGame.camera.position.y < 300)){
			MyGdxGame.camera.position.y +=5;
			MyGdxGame.camera.update();
			
		}
		if((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))){// && MyGdxGame.camera.position.x > 300){
			MyGdxGame.camera.position.x -=5;
			MyGdxGame.camera.update();
		}
		if((Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))){
			MyGdxGame.camera.position.y -=5;
			MyGdxGame.camera.update();
		}
		if((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))){
			MyGdxGame.camera.position.x +=5;
			MyGdxGame.camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.Z)){
			MyGdxGame.camera.viewportHeight +=20;
			MyGdxGame.camera.viewportWidth +=20;
			MyGdxGame.camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.X)){
			MyGdxGame.camera.viewportHeight -=20;
			MyGdxGame.camera.viewportWidth -=20;
			MyGdxGame.camera.update();
		}
		
		
		
		MyGdxGame.touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
		MyGdxGame.camera.unproject(MyGdxGame.touchPos);

		setFocusedTilesHexagon();
		Tile clicked_tile = null;
		if (Gdx.input.justTouched()) {
			if(clicked_tile == selected_tile){
				MyGdxGame.unitIsSeclected = false;
			}
			MyGdxGame.rightPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			MyGdxGame.camera.unproject(MyGdxGame.rightPos);
			clicked_tile = board.closestTile(MyGdxGame.rightPos);
			if(clicked_tile.isOccupied()){
				selected_Unit = clicked_tile.getUnit();
				selected_tile = clicked_tile;
				MyGdxGame.unitIsSeclected = true;
				
			}else if(!clicked_tile.isOccupied() && MyGdxGame.unitIsSeclected){
					clicked_tile.setUnit(selected_Unit);
					clicked_tile.setOccupied(true);
					selected_tile.setOccupied(false);
					MyGdxGame.unitIsSeclected = false;					
				
			}

		}
	
		board_artist.drawBoard();
		for (BattleInstancePlayer player : players)
		{
			player.drawAllUnits();
		}
		
		if (this.focused_tile != null) 
		{
			this.drawFocusedHexagon();
		}
	}
	
	
	
	/**
	 * Draw the focused hexagon of the player.
	 */
	public void drawFocusedHexagon() 
	{

		MyGdxGame.Project_Shape_Renderer
				.setProjectionMatrix(MyGdxGame.camera.combined);
		MyGdxGame.Project_Shape_Renderer.setColor(Color.ORANGE);
		MyGdxGame.Project_Shape_Renderer.begin(ShapeType.Line);
		MyGdxGame.Project_Shape_Renderer.polygon(getFocusedTilesHexagon()
				.getVertices());
		MyGdxGame.Project_Shape_Renderer.end();
	}

	public void drawOccupiedTiles() {
		tiles = board.getTiles();
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				Hexagon hex = tiles[i][j].getHexagon();
				if (tiles[i][j].isOccupied()) {
					if (tiles[i][j].getUnit().getUnitType() == UnitTypeConstants.Warrior  ){
						this.drawWarrior(tiles[i][j].getHexagon());						
					}
					if (tiles[i][j].getUnit().getUnitType() == UnitTypeConstants.Mage){
						this.drawMage(tiles[i][j].getHexagon());						
					}
					if (tiles[i][j].getUnit().getUnitType() == UnitTypeConstants.Archer){
						this.drawArcher(tiles[i][j].getHexagon());						
					}
			}
		}
		MyGdxGame.Project_Shape_Renderer.setColor(Color.BLACK);
	}
		}

	private void drawWarrior(Hexagon hexagon) {
		MyGdxGame.Project_Shape_Renderer.setColor(Color.RED);
		MyGdxGame.Project_Shape_Renderer.begin(ShapeType.Line);
		MyGdxGame.Project_Shape_Renderer.polygon(hexagon.getVertices());
		MyGdxGame.Project_Shape_Renderer.end();
	}
	private void drawMage(Hexagon hexagon) {
		MyGdxGame.Project_Shape_Renderer.setColor(Color.BLUE);
		MyGdxGame.Project_Shape_Renderer.begin(ShapeType.Line);
		MyGdxGame.Project_Shape_Renderer.polygon(hexagon.getVertices());
		MyGdxGame.Project_Shape_Renderer.end();
	}
	private void drawArcher(Hexagon hexagon) {
		MyGdxGame.Project_Shape_Renderer.setColor(Color.GREEN);
		MyGdxGame.Project_Shape_Renderer.begin(ShapeType.Line);
		MyGdxGame.Project_Shape_Renderer.polygon(hexagon.getVertices());
		MyGdxGame.Project_Shape_Renderer.end();
	}

	public Hexagon getFocusedTilesHexagon() 
	{
		return focused_tile.getHexagon() ;
	}

	public void setFocusedTilesHexagon() 
	{
		this.focused_tile = board.closestTile(MyGdxGame.touchPos);
	}



}
