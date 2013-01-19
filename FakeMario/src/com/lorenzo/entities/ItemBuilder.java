package com.lorenzo.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class ItemBuilder extends EntityBuilder{

	public ItemBuilder() {
		entity = new Item();
	}
	
	@Override
	public EntityBuilder buildBody(World world, float posX, float posY) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(posX, posY);
		entity.setBody(world.createBody(bodyDef));
		return this;
	}

	@Override
	public EntityBuilder buildFixture(float width, float height) {
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width/2, height/2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 45f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.1f;

		entity.setFixture(entity.getBody().createFixture(fixtureDef));
		return this;
	}

	@Override
	public EntityBuilder buildFootFixture(float width, float height) {
		PolygonShape footPolygon = new PolygonShape();
		footPolygon.setAsBox(width/2, 1f, new Vector2(0, -(width/2)), 0);
		FixtureDef footFixtureDef = new FixtureDef();
		footFixtureDef.shape = footPolygon;
		footFixtureDef.density = 1;
		footFixtureDef.isSensor = true;
		entity.setFootFixture(entity.getBody().createFixture(footFixtureDef));
		
		entity.getFootFixture().setUserData(entity);
		
		return this;
	}
	
	@Override
	public EntityBuilder buildTextureAtlas(String dir) {
		TextureAtlas atlas = new TextureAtlas( Gdx.files.internal( dir ) );
		entity.setAtlas(atlas);
		return this;
	}

	@Override
	public EntityBuilder buildTexture(String dir) {
		Texture characterTexture = new Texture(Gdx.files.internal(dir));
		entity.setTexture(characterTexture);
		
		return this;
	}

	@Override
	public EntityBuilder buildSprite() {
		Sprite sprite = new Sprite(entity.getTexture());
		entity.setSprite(sprite);
		return this;
	}

	@Override
	public Entity build() {
		return entity;
	}

}
