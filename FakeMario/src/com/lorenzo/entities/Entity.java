package com.lorenzo.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Entity {

	protected Body body;
	protected float radius;
	protected int numContacts;
	protected FixtureDef fixtureDef;
	protected Fixture fixture;
	protected FixtureDef footFixtureDef;
	protected Fixture footFixture;
	
	public Entity(World world, float radius){
		numContacts = 0;
		this.radius = radius;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(10, 30);
		body = world.createBody(bodyDef);
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(radius, radius);

		fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 1.2f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.1f;

		fixture = body.createFixture(fixtureDef);
		
		PolygonShape footPolygon = new PolygonShape();
		footPolygon.setAsBox(radius, (float)1, new Vector2(0, (float) -5.5), 0);
		footFixtureDef = new FixtureDef();
		footFixtureDef.shape = footPolygon;
		footFixtureDef.density = 1;
		footFixtureDef.isSensor = true;
		footFixture = body.createFixture(footFixtureDef);
	}
	
	public void startContact(){
		numContacts++;
	}
	
	public void endContact(){
		numContacts--;
	}
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public int getNumContacts() {
		return numContacts;
	}

	public void setNumContacts(int numContacts) {
		this.numContacts = numContacts;
	}

	public FixtureDef getFixtureDef() {
		return fixtureDef;
	}

	public void setFixtureDef(FixtureDef fixtureDef) {
		this.fixtureDef = fixtureDef;
	}
}
