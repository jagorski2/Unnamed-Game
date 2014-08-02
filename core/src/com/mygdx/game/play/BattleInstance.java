package com.mygdx.game.play;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
	private Hexagon focused_hex;						//this is the current focus on the player on the screen, it will be highlighted
	
	private List<BattleInstancePlayer> players;			//contains all the players that are involved in the battle instance.
	private int turn;									//index in the players data structure to determine the turn.
	
	
	/**
	 * There is no reason to have an instance of this class without a board and a list of players...
	 * 
	 */
	public BattleInstance(Board board, List<BattleInstancePlayer> players) 
	{
		this.board = board;
		this.players = players;
		board_artist = new HexagonBoardRenderer(board);
		
		
		//focused_hex = board.getHexagons()[5][5];		//delete this line
		
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
		board_artist.drawBoard();
		focused_hex = board.closestHexagon(Gdx.input.getX(),Gdx.input.getY());
		
		for (BattleInstancePlayer player : players)
		{
			player.drawAllUnits();
		}
		
		if (this.focused_hex != null) 
		{
			this.drawFocusedHexagon();
		}
		
	
	}
	
	/**
	 * Draw the focused hexagon of the player.
	 */
	public void drawFocusedHexagon() 
	
	{ 
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(Color.ORANGE);
		r.begin(ShapeType.Line);
		r.polygon(focused_hex.getVertices());
		r.end();
	}

	public Hexagon getFocused_hex() 
	{
		return focused_hex;
	}

	public void setFocused_hex(Hexagon focused_hex) 
	{
		this.focused_hex = focused_hex;
	}

}
