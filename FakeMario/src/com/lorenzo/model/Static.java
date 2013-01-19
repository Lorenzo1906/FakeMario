package com.lorenzo.model;


public class Static {
	private String textureDir;
	private Position pos;
	private Size size;

	public Static() {
	}
	
	public Static(Position pos, Size size, String textureDir) {
		this.pos = pos;
		this.size = size;
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
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
}
