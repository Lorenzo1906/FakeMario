package com.lorenzo.entities;

public class Position {
	private String posX;
	private String posY;
	
	public Position() {
	}
	
	public Position(String posX, String posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public String getPosX() {
		return posX;
	}

	public void setPosX(String posX) {
		this.posX = posX;
	}

	public String getPosY() {
		return posY;
	}

	public void setPosY(String posY) {
		this.posY = posY;
	}
}
