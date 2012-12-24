package com.lorenzo.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.lorenzo.logic.GameWorld.moveState;
import com.lorenzo.utils.Utils;

public class MainCharacter extends Entity {

	private Texture characterTexture;
	private Sprite characterSprite;
	
	public MainCharacter(World world, float radius) {
		super(world, radius);
		
		characterTexture = new Texture(Gdx.files.internal("data/Mario.png"));
		characterSprite = new Sprite(characterTexture);

		footFixture.setUserData(this);
	}
	
	public Texture getCharacterTexture() {
		return characterTexture;
	}

	public void setCharacterTexture(Texture characterTexture) {
		this.characterTexture = characterTexture;
	}

	public Sprite getCharacterSprite() {
		return characterSprite;
	}

	public void setCharacterSprite(Sprite characterSprite) {
		this.characterSprite = characterSprite;
	}
	
	public void moveCharacter(moveState moveState, Float characterSpeed){
		Vector2 vel = body.getLinearVelocity();
		float desiredVel = 0;
		switch(moveState){
		case MS_LEFT:
			desiredVel = -characterSpeed* Utils.BOX_WORLD_TO;
			break;
		case MS_STOP:
			desiredVel = 0;
			break;
		case MS_RIGHT:
			desiredVel = characterSpeed* Utils.BOX_WORLD_TO;
			break;
		}
		float velChange = desiredVel - vel.x;
		float impulse = body.getMass()*velChange;
		body.applyLinearImpulse(new Vector2(impulse,0), body.getWorldCenter());
	}
	
	public void jump(){
		if(numContacts > 0){
			float impulse = body.getMass()*12;
			body.applyLinearImpulse(new Vector2(0, impulse), body.getWorldCenter());
		}
	}
	
	public void dispose(){
		characterTexture.dispose();
	}
}
