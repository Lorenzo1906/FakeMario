package com.lorenzo.logic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lorenzo.entities.Size;
import com.lorenzo.entities.Static;
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
	private List<Sprite> staticSprites;
	private List<Texture> textures;
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

		textures = new ArrayList<Texture>();

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
		staticSprites = generateStatics(level.getStaticSprites());
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

	public List<Sprite> getStaticSprites() {
		return staticSprites;
	}

	public void setStaticSprites(List<Sprite> staticSprites) {
		this.staticSprites = staticSprites;
	}


	public void stepGameWorld(){
		world.step( 1/60f, 6, 2 );
	}

	private void inicializeFloor(){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type  = BodyType.StaticBody;
		bodyDef.position.set(0, 0);
		Body leftWallBody = world.createBody(bodyDef);
		Body rightWallBody = world.createBody(bodyDef);
		Body ceilingBody = world.createBody(bodyDef);
		PolygonShape polygonShape = new PolygonShape();
		FixtureDef myFixtureDef = new FixtureDef();
		myFixtureDef.shape = polygonShape;

		//Left wall
		polygonShape.setAsBox(1.65f, 10877, new Vector2(0, 0), 0);
		leftWallBody.createFixture(myFixtureDef);
		//Right wall
		polygonShape.setAsBox(1.65f, 10877, new Vector2(10877* Utils.WORLD_TO_BOX, 0), 0);
		rightWallBody.createFixture(myFixtureDef);
		//Ceiling
		polygonShape.setAsBox(10877* Utils.WORLD_TO_BOX, 0, new Vector2(0, 10877* Utils.WORLD_TO_BOX), 0);
		ceilingBody.createFixture(myFixtureDef);
	}

	private void inicializeCharacter(){
		character = new MainCharacter(world, 3.2f);
	}

	private List<Sprite> generateLayer(List<Static> sprites){
		List<Sprite> spritesFinals = new ArrayList<Sprite>();
		for (Static background : sprites) {
			Texture texture = new Texture(Gdx.files.internal(background.getTextureDir()));
			Position pos = background.getPos();
			Size size = background.getSize();
			Sprite sprite = new Sprite(texture);
			sprite.setSize(Float.parseFloat(size.getWidth()), Float.parseFloat(size.getHeight()));
			sprite.setPosition(Float.parseFloat(pos.getPosX()), Float.parseFloat(pos.getPosY()));
			spritesFinals.add(sprite);
			textures.add(texture);
		}
		return spritesFinals;
	}
	
	private List<Sprite> generateStatics(List<Static> statics){
		List<Sprite> spriteFinals = new ArrayList<Sprite>();
		for (Static static1 : statics) {
			Texture texture = new Texture(Gdx.files.internal(static1.getTextureDir()));
			texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			Position pos = static1.getPos();
			Size size = static1.getSize();
			Sprite sprite = new Sprite(texture);
			sprite.setSize(Float.parseFloat(size.getWidth()), Float.parseFloat(size.getHeight()));
			sprite.setPosition(Float.parseFloat(pos.getPosX()), Float.parseFloat(pos.getPosY()));
			
			BodyDef bodyDef = new BodyDef();
			bodyDef.type  = BodyType.StaticBody;
			bodyDef.position.set(Float.parseFloat(pos.getPosX())*Utils.WORLD_TO_BOX, Float.parseFloat(pos.getPosY())*Utils.WORLD_TO_BOX);
			Body floorBody = world.createBody(bodyDef);
			PolygonShape polygonShape = new PolygonShape();
			FixtureDef myFixtureDef = new FixtureDef();
			myFixtureDef.shape = polygonShape;
			polygonShape.setAsBox((Float.parseFloat(size.getWidth())*Utils.WORLD_TO_BOX)/2, (Float.parseFloat(size.getHeight())*Utils.WORLD_TO_BOX)/2);
			floorBody.createFixture(myFixtureDef);
			
			spriteFinals.add(sprite);
			textures.add(texture);
		}
		return spriteFinals;
	}

	public void dispose(){
		world.dispose();
		character.dispose();
		for (Texture texture : textures) {
			texture.dispose();
		}
	}

}
