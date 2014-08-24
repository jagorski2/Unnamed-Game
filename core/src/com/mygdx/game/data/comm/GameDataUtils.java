package com.mygdx.game.data.comm;
/**
 * MockGameData -> no connections to server
 * RealGameData -> connects to the server
 * @author ianno_000
 *
 */
public class GameDataUtils {
	
	public static GameDataInterface getInstance() {
		return new MockGameData();
		//return new RealGameData();
	}

}
