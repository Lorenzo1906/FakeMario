package com.lorenzo.logic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lorenzo.camera.ParallaxCamera;
import com.lorenzo.entities.MainCharacter;
import com.lorenzo.utils.Utils;

public class GameWorld {

	private World world;
	private MainCharacter character;
	//TODO:Fix this
	private Texture[] texturesLayer1;
	private Texture[] texturesLayer2;
	private Texture[] texturesLayer3;
	private Texture[] texturesLayer4;
	private List<Sprite> spritesLayer1;
	private List<Sprite> spritesLayer2;
	private List<Sprite> spritesLayer3;
	private List<Sprite> spritesLayer4;
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private EntityContactListener contactListener;
	private float screenW;
	//private float screenH;

	public static enum moveState{
		MS_STOP,
		MS_LEFT,
		MS_RIGHT,
	}


	public GameWorld(float screenW, float screenH) {

		this.screenW = screenW;
		//this.screenH = screenH;

		world = new World(new Vector2(0, -10),true);

		contactListener = new EntityContactListener();
		world.setContactListener(contactListener);


		inicializeLayers();
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

	private void inicializeLayers(){
		//lAYER 1
		texturesLayer1 = new Texture[1];
		texturesLayer1[0] = new Texture(Gdx.files.internal("data/layers/Layer11.png"));

		spritesLayer1 = new ArrayList<Sprite>();
		int y = 0;
		for(int i = 0; i<108877; i += 256){
			spritesLayer1.add(new Sprite(texturesLayer1[0]));
			spritesLayer1.get(y).setSize(256, 256);
			spritesLayer1.get(y).setPosition(i, 0);
			y++;

			spritesLayer1.add(new Sprite(texturesLayer1[0]));
			spritesLayer1.get(y).setSize(256, 256);
			spritesLayer1.get(y).setPosition(i, 256);
			y++;

			spritesLayer1.add(new Sprite(texturesLayer1[0]));
			spritesLayer1.get(y).setSize(256, 256);
			spritesLayer1.get(y).setPosition(i,512);
			y++;
		}

		//lAYER 2
		texturesLayer2 = new Texture[4];
		texturesLayer2[0] = new Texture(Gdx.files.internal("data/layers/Layer21.png"));
		texturesLayer2[1] = new Texture(Gdx.files.internal("data/layers/Layer22.png"));
		texturesLayer2[2] = new Texture(Gdx.files.internal("data/layers/Layer23.png"));
		texturesLayer2[3] = new Texture(Gdx.files.internal("data/layers/Layer24.png"));

		spritesLayer2 = new ArrayList<Sprite>();
		int W = 0;
		for(int i = 0; i<108877; i += 256){
			int  ran = MathUtils.random(4);
			if(ran <=3){
				spritesLayer2.add(new Sprite(texturesLayer2[ran]));
				spritesLayer2.get(W).setSize(256, 256);
				spritesLayer2.get(W).setPosition(i, 256);
				W++;
			}

			int  ran2 = MathUtils.random(4);
			if(ran2 <=3){
				spritesLayer2.add(new Sprite(texturesLayer2[ran2]));
				spritesLayer2.get(W).setSize(256, 256);
				spritesLayer2.get(W).setPosition(i, 512);
				W++;
			}
		}

		texturesLayer3 = new Texture[4];
		texturesLayer3[0] = new Texture(Gdx.files.internal("data/layers/Layer21.png"));
		texturesLayer3[1] = new Texture(Gdx.files.internal("data/layers/Layer22.png"));
		texturesLayer3[2] = new Texture(Gdx.files.internal("data/layers/Layer23.png"));
		texturesLayer3[3] = new Texture(Gdx.files.internal("data/layers/Layer24.png"));

		spritesLayer3 = new ArrayList<Sprite>();
		int u = 0;
		for(int i = 0; i<108877; i += 256){
			int  ran = MathUtils.random(4);
			if(ran <=3){
				spritesLayer3.add(new Sprite(texturesLayer3[ran]));
				spritesLayer3.get(u).setSize(256, 256);
				spritesLayer3.get(u).setPosition(i, 256);
				u++;
			}

			int  ran2 = MathUtils.random(4);
			if(ran2 <=3){
				spritesLayer3.add(new Sprite(texturesLayer3[ran2]));
				spritesLayer3.get(u).setSize(256, 256);
				spritesLayer3.get(u).setPosition(i, 512);
				u++;
			}
		}

		//lAYER 3
		texturesLayer4 = new Texture[3];
		texturesLayer4[0] = new Texture(Gdx.files.internal("data/layers/Layer31.png"));
		texturesLayer4[1] = new Texture(Gdx.files.internal("data/layers/Layer32.png"));
		texturesLayer4[2] = new Texture(Gdx.files.internal("data/layers/Layer33.png"));

		spritesLayer4 = new ArrayList<Sprite>();
		int e = 0;
		for(int i = 0; i<108877; i += 256){
			spritesLayer4.add(new Sprite(texturesLayer4[MathUtils.random(2)]));
			spritesLayer4.get(e).setSize(256, 256);
			spritesLayer4.get(e).setPosition(i, 0);
			e++;
		}

	}

	public void updateCamera(ParallaxCamera camera, SpriteBatch batch){
		// background layer, no parallax, centered around origin
		batch.setProjectionMatrix(camera.calculateParallaxMatrix(0.25f, 1));
		batch.disableBlending();
		batch.begin();
		for (Sprite sprite : spritesLayer1) {
			batch.draw(sprite, sprite.getX()-screenW, sprite.getY());
		}
		batch.end();
		batch.enableBlending();

		// midground layer, 0.5 parallax (move at half speed on x, full speed on y)
		batch.setProjectionMatrix(camera.calculateParallaxMatrix(0.5f, 1));
		batch.begin();
		for (Sprite sprite : spritesLayer2) {
			batch.draw(sprite, sprite.getX()-screenW, sprite.getY());
		}
		batch.end();

		batch.setProjectionMatrix(camera.calculateParallaxMatrix(0.7f, 1));
		batch.begin();
		for (Sprite sprite : spritesLayer3) {
			batch.draw(sprite, sprite.getX()-screenW, sprite.getY());
		}
		batch.end();

		// foreground layer, 1.0 parallax (move at full speed)
		batch.setProjectionMatrix(camera.calculateParallaxMatrix(1f, 1));
		batch.begin();
		for (Sprite sprite : spritesLayer4) {
			batch.draw(sprite, sprite.getX()-screenW, sprite.getY());
		}
		batch.end();
	}

	public void dispose(){
		world.dispose();
	}

}
