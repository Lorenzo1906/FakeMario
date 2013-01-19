package com.lorenzo.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class Entity {
	protected Body body;
	protected Fixture fixture;
	protected Fixture footFixture;
	protected Texture texture;
	protected TextureAtlas atlas;
	protected Sprite sprite;
	
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public Fixture getFixture() {
		return fixture;
	}
	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}
	public Fixture getFootFixture() {
		return footFixture;
	}
	public void setFootFixture(Fixture footFixture) {
		this.footFixture = footFixture;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public TextureAtlas getAtlas() {
		return atlas;
	}
	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
