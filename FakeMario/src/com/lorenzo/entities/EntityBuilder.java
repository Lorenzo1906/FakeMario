package com.lorenzo.entities;

import com.badlogic.gdx.physics.box2d.World;

public abstract class EntityBuilder {

	protected Entity entity;
	
	public abstract EntityBuilder buildBody(World world, float posX, float posY);
	public abstract EntityBuilder buildFixture(float width, float height);
	public abstract EntityBuilder buildFootFixture(float width, float height);
	public abstract EntityBuilder buildTexture(String dir);
	public abstract EntityBuilder buildTextureAtlas(String dir);
	public abstract EntityBuilder buildSprite();
	public abstract Entity build();
}
