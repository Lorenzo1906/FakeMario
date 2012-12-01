package com.lorenzo.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lorenzo.entities.MainCharacter;
import com.lorenzo.utils.Utils;

public class GameWorld {

	private World world;
	private MainCharacter character;
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private EntityContactListener contactListener;

	public static enum moveState{
		MS_STOP,
		MS_LEFT,
		MS_RIGHT,
	}


	public GameWorld() {
		world = new World(new Vector2(0, -10),true);

		contactListener = new EntityContactListener();
		world.setContactListener(contactListener);
		
		inicializeCharacter();
		inicializeFloor();

	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
		this.debugRenderer = debugRenderer;
	}
	
	public MainCharacter getCharacter() {
		return character;
	}

	public void setCharacter(MainCharacter character) {
		this.character = character;
	}

	public void stepGameWorld(){
		world.step( 1/60f, 6, 2 );
	}

	private void inicializeFloor(){
		
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type  = BodyType.StaticBody;
		bodyDef.position.set(0, 0);
		Body staticBody = world.createBody(bodyDef);
		PolygonShape polygonShape = new PolygonShape();
		FixtureDef myFixtureDef = new FixtureDef();
		myFixtureDef.shape = polygonShape;

		polygonShape.setAsBox(1.65f, 10877, new Vector2(0, 0), 0);
		staticBody.createFixture(myFixtureDef);
		polygonShape.setAsBox(10877* Utils.WORLD_TO_BOX, 0, new Vector2(0, (float) 1.6), 0);
		staticBody.createFixture(myFixtureDef);
		polygonShape.setAsBox(1.65f, 10877, new Vector2(10877* Utils.WORLD_TO_BOX, 0), 0);
		staticBody.createFixture(myFixtureDef);
		polygonShape.setAsBox(10877* Utils.WORLD_TO_BOX, 0, new Vector2(0, 10877* Utils.WORLD_TO_BOX), 0);
		staticBody.createFixture(myFixtureDef);
	}

	private void inicializeCharacter(){
		character = new MainCharacter(world, 6f);
	}

	public void dispose(){
		world.dispose();
	}

}
