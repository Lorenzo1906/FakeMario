package com.lorenzo.logic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lorenzo.entities.Background;
import com.lorenzo.entities.MainCharacter;
import com.lorenzo.entities.Position;
import com.lorenzo.persistence.LevelDescriptor;
import com.lorenzo.persistence.LevelDescriptorParser;
import com.lorenzo.utils.Utils;

public class GameWorld {

	private World world;
	private MainCharacter character;
	private List<Sprite> firstLayer;
	private List<Sprite> secondLayer;
	private List<Sprite> thirdLayer;
	private List<Sprite> fourthLayer;
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private EntityContactListener contactListener;

	public static enum moveState{
		MS_STOP,
		MS_LEFT,
		MS_RIGHT,
	}


	public GameWorld(float screenW, float screenH) {
		world = new World(new Vector2(0, -10),true);

		contactListener = new EntityContactListener();
		world.setContactListener(contactListener);

		inicializeCharacter();
		inicializeFloor();
		

		String levelDir = "descriptors/firstLevelDescriptor.json";

		LevelDescriptorParser levelParser = new LevelDescriptorParser(levelDir);
		LevelDescriptor level = levelParser.parseLevel();
		System.out.println(level.getLevelName());
		firstLayer = generateLayer(level.getFirstLayer());
		secondLayer = generateLayer(level.getSecondLayer());
		thirdLayer = generateLayer(level.getThirdLayer());
		fourthLayer = generateLayer(level.getFourthLayer());
		//levelParser.writeLevel();
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
	
	public List<Sprite> getFirstLayer() {
		return firstLayer;
	}

	public void setFirstLayer(List<Sprite> firstLayer) {
		this.firstLayer = firstLayer;
	}

	public List<Sprite> getSecondLayer() {
		return secondLayer;
	}

	public void setSecondLayer(List<Sprite> secondLayer) {
		this.secondLayer = secondLayer;
	}

	public List<Sprite> getThirdLayer() {
		return thirdLayer;
	}

	public void setThirdLayer(List<Sprite> thirdLayer) {
		this.thirdLayer = thirdLayer;
	}

	public List<Sprite> getFourthLayer() {
		return fourthLayer;
	}

	public void setFourthLayer(List<Sprite> fourthLayer) {
		this.fourthLayer = fourthLayer;
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

	private List<Sprite> generateLayer(List<Background> sprites){
		List<Sprite> spritesFinals = new ArrayList<Sprite>();
		for (Background background : sprites) {
			Texture texture = new Texture(Gdx.files.internal(background.getTextureDir()));
			Position pos = background.getPos();
			Sprite sprite = new Sprite(texture);
			sprite.setSize(256, 256);
			sprite.setPosition(Float.parseFloat(pos.getPosX()), Float.parseFloat(pos.getPosY()));
			spritesFinals.add(sprite);
		}
		return spritesFinals;
	}

	public void dispose(){
		world.dispose();
		character.dispose();
	}

}
