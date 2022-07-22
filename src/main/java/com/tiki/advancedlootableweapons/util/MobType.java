package com.tiki.advancedlootableweapons.util;

public enum MobType {
	LAND(0),
	WATER(1),
	AIR(2),
	PEACEFUL(3),
	HOSTILE(4),
	NEUTRAL(5);
	
	private int type;
	
	MobType(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
	
	public String toString() {
		switch(this.type) {
			case 0:
				return "Land";
			case 1:
				return "Water";
			case 2:
				return "Air";
			case 3:
				return "Peaceful";
			case 4:
				return "Harmful";
			case 5:
				return "Neutral";
			default:
				return "Type not found";
		}
	}
}
