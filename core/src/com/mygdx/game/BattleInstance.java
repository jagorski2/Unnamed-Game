package com.mygdx.game;

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
import com.mygdx.game.utils.UnitTypeConstants;
import com.mygdx.game.hex.Board;
import com.mygdx.game.hex.Hexagon;


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
	private Tile focused_tile; 						//this is the current focus on the player on the screen, it will be highlighted
	private Tile selected_tile;
	private Unit selected_Unit;
	
	private List<BattleInstancePlayer> players;			//contains all the players that are involved in the battle instance.
	private int turn;								//index in the players data structure to determine the turn.
	
	/**
	 * There is no reason to have an instance of this class without a board and a list of players...
	 * 
	 */
	public BattleInstance(Board board, List<BattleInstancePlayer> players) 
	{
		this.board = board;
		this.players = players;
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
			BoardScreen.camera.position.y +=5;
			BoardScreen.camera.update();
			
		}
		if((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))){// && MyGdxGame.camera.position.x > 300){
			BoardScreen.camera.position.x -=5;
			BoardScreen.camera.update();
		}
		if((Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))){
			BoardScreen.camera.position.y -=5;
			BoardScreen.camera.update();
		}
		if((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))){
			BoardScreen.camera.position.x +=5;
			BoardScreen.camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.Z)){
			BoardScreen.camera.viewportHeight +=20;
			BoardScreen.camera.viewportWidth +=20;
			BoardScreen.camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.X)){
			BoardScreen.camera.viewportHeight -=20;
			BoardScreen.camera.viewportWidth -=20;
			BoardScreen.camera.update();
		}
		
		
		
		BoardScreen.touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
		BoardScreen.camera.unproject(BoardScreen.touchPos);

		setFocusedTilesHexagon();
		Tile clicked_tile = null;
		if (Gdx.input.justTouched()) {
			if(clicked_tile == selected_tile){
				BoardScreen.unitIsSelected = false;
			}
			BoardScreen.rightPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			BoardScreen.camera.unproject(BoardScreen.rightPos);
			clicked_tile = board.getClosestTile(BoardScreen.rightPos);
			if(clicked_tile != null && clicked_tile.isOccupied()){
				selected_Unit = clicked_tile.getUnit();
				selected_tile = clicked_tile;
				BoardScreen.unitIsSelected = true;
				
			}else if(clicked_tile != null && !clicked_tile.isOccupied() && BoardScreen.unitIsSelected){
				
					clicked_tile.setUnit(selected_Unit);
					clicked_tile.setOccupied(true);
					selected_tile.setOccupied(false);
					BoardScreen.unitIsSelected = false;					
				
			}
		}

		board.drawBoard();
		for (BattleInstancePlayer player : players)
		{
			player.drawAllUnits();
		}
		
		if (this.focused_tile != null) 
		{
			this.drawFocusedHexagon();
		}
		this.drawOccupiedTiles();
	}
	
	
	
	/**
	 * Draw the focused hexagon of the player.
	 */
	public void drawFocusedHexagon() 
	{

		BoardScreen.project_shape_renderer
				.setProjectionMatrix(BoardScreen.camera.combined);
		BoardScreen.project_shape_renderer.setColor(Color.ORANGE);
		BoardScreen.project_shape_renderer.begin(ShapeType.Line);
		BoardScreen.project_shape_renderer.polygon(getFocusedTilesHexagon()
				.getVertices());
		BoardScreen.project_shape_renderer.end();
	}

	//@Deprecated
	public void drawOccupiedTiles() {
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				Tile tile = board.getTile(i, j);
				if (tile != null && tile.isOccupied()) {
					 Hexagon hex = tile.getHexagon();
					 Unit unit = tile.getUnit();
					 unit.setHexagon(hex);
					 unit.drawUnit();
				}
			}
		BoardScreen.project_shape_renderer.setColor(Color.BLACK);
		}
	}

	public Hexagon getFocusedTilesHexagon() 
	{
		return focused_tile.getHexagon() ;
	}

	public void setFocusedTilesHexagon() 
	{
		this.focused_tile = board.getClosestTile(BoardScreen.touchPos);
	}



}
