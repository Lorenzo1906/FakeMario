package com.lorenzo.fakemario;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FakeMario implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	Rectangle bucket;
	private Sprite sprite;
	
	@Override
	public void create() {		
		texture = new Texture(Gdx.files.internal("data/FirstLevel.gif"));
		sprite = new Sprite(texture);
		sprite.setPosition(0, 0);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 296, 224);
		
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    
	    camera.update();
	    
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    batch.draw(texture, sprite.getX(), sprite.getY());
	    batch.end();
	    
	    System.out.println(camera.position.x);
	    
	    if(Gdx.input.isKeyPressed(Keys.LEFT)){
	    	if(camera.position.x > 148){
	    		camera.position.x -= 1;
	    	}
	    }
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)){
	    	if(camera.position.x < 3236){
	    		camera.position.x += 1;
	    	}
	    }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
