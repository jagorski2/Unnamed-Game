package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.hex.Board;

/**
 * This class handles all data referring to a Player during a given battle
 * instance.
 * 
 * @author ianno_000
 *
 */
public class BattleInstancePlayer {

	private List<InstanceUnit> units;
	private int player_id;

	public BattleInstancePlayer() {
		units = new ArrayList<InstanceUnit>();
		player_id = 0;
	}

	public List<InstanceUnit> getUnits() {
		return units;
	}

	public void setUnits(List<InstanceUnit> units) {
		this.units = units;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	/*
	 * Draw all the units that this BattleInstancePlayer object contains.
	 */
	public void drawAllUnits() {
		for (int i = 0; i < units.size(); i++) {
			InstanceUnit unit = units.get(i);
			if (unit != null) {
				//TODO draw unit...
			}
		}

	}
}
