package com.lorenzo.fakemario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lorenzo.camera.ParallaxCamera;
import com.lorenzo.entities.MainCharacter;
import com.lorenzo.logic.GameWorld;
import com.lorenzo.utils.Utils;

public class FakeMario extends ApplicationAdapter {

	private GameWorld world;
	private ParallaxCamera camera;
	private SpriteBatch batch;
	private float screenW;
	private float screenH;

	public void create() {	

		screenW = Gdx.graphics.getWidth();
		screenH = Gdx.graphics.getHeight();
		//float w = 1;
		
		//float h = w * screenH / screenW;
		
		world = new GameWorld(screenW, screenH);
		
		camera = new ParallaxCamera();
		camera.setToOrtho(false, screenW, screenH);

		batch = new SpriteBatch();
	}

	public void dispose() {
		batch.dispose();
		world.dispose();
	}

	public void render() {		
		//This must be loaded externally
		Float characterSpeed = (float) 3;
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		world.updateCamera(camera, batch);
		
		MainCharacter character = world.getCharacter();

		batch.begin();
		//world.getDebugRenderer().render(world.getWorld(), camera.combined);
		batch.draw(character.getCharacterSprite(), character.getBody().getPosition().x*Utils.BOX_WORLD_TO, character.getBody().getPosition().y*Utils.BOX_WORLD_TO);
		batch.end();

		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			if(character.getBody().getPosition().x >= 0){
				character.moveCharacter(GameWorld.moveState.MS_LEFT,characterSpeed);
			}
		}else{
			if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				if(character.getBody().getPosition().x < 10877){
					character.moveCharacter(GameWorld.moveState.MS_RIGHT,characterSpeed);
				}
			}else{
				character.moveCharacter(GameWorld.moveState.MS_STOP, characterSpeed);
			}
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			character.jump();
		}
		
		//The 108877 is Widht of the image, should be loaded externally
		if(character.getBody().getPosition().x*Utils.BOX_WORLD_TO > camera.position.x){
			if(camera.position.x < 10877 - screenW/2){
				camera.position.x = character.getBody().getPosition().x*Utils.BOX_WORLD_TO;
			}
		}
		if(character.getBody().getPosition().x*Utils.BOX_WORLD_TO < camera.position.x - screenW/2){
			if(camera.position.x > screenW/2){
				camera.position.x = character.getBody().getPosition().x*Utils.BOX_WORLD_TO+Gdx.graphics.getWidth()/2;
			}
		}
		
		world.stepGameWorld();
	}

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void resume() {
	}
}
