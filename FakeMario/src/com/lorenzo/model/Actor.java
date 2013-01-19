package com.lorenzo.model;

public class Actor {
	private String textureDir;
	private Position pos;
	private Size size;
	private ActorType actorType;
	
	public Actor() {
	}
	
	public Actor(String textureDir, Position pos, Size size, ActorType actorType){
		this.pos = pos;
		this.size = size;
		this.textureDir = textureDir;
		this.actorType = actorType;
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

	public ActorType getActorType() {
		return actorType;
	}

	public void setActorType(ActorType actorType) {
		this.actorType = actorType;
	}
}
