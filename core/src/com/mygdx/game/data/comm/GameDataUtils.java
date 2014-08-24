package com.mygdx.game.data.comm;

public class GameDataUtils {
	
	public static GameDataInterface getInstance() {
		return new MockGameData();
		//return new RealGameData();
	}

}
