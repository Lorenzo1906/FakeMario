package com.lorenzo.entities;

public class Background {
	private String textureDir;
	private Position pos;
	
	public Background() {
	}
	
	public Background(Position pos, String textureDir) {
		this.pos = pos;
		this.textureDir = textureDir;
	}
	
	public String getTextureDir() {
		return textureDir;
	}

	public void setTextureDir(String textureDir) {
		this.textureDir = textureDir;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
}
