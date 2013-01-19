package com.lorenzo.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.lorenzo.logic.GameWorld.moveState;
import com.lorenzo.utils.Utils;

public class MainCharacter extends Entity{

	private int numContacts;
	private Animation runAnimationBackward;
	private Animation runAnimationFordward;
	private float stateTimeRunBackward;
	private float stateTimeRunFordward;
	private moveState lastMoveState;

	public MainCharacter() {
		lastMoveState = moveState.MS_STOP;
	}

	public void moveCharacter(moveState moveStateCurrent, Float characterSpeed){
		stateTimeRunBackward += Gdx.graphics.getDeltaTime();
		stateTimeRunFordward += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = null;
		Vector2 vel = body.getLinearVelocity();
		float desiredVel = 0;
		switch(moveStateCurrent){
		case MS_LEFT:
			desiredVel = -characterSpeed* Utils.BOX_WORLD_TO;
			currentFrame = runAnimationBackward.getKeyFrame(stateTimeRunBackward, true);
			lastMoveState = moveState.MS_LEFT;
			break;
		case MS_STOP:
			desiredVel = 0;
			if(lastMoveState == moveState.MS_LEFT){
				currentFrame = runAnimationBackward.getKeyFrame(0, true);
			}else {
				currentFrame = runAnimationFordward.getKeyFrame(0, true);
			}
			break;
		case MS_RIGHT:
			desiredVel = characterSpeed* Utils.BOX_WORLD_TO;
			currentFrame = runAnimationFordward.getKeyFrame(stateTimeRunFordward, true);
			lastMoveState = moveState.MS_RIGHT;
			break;
		}
		if(numContacts == 0){
			desiredVel = desiredVel/2;
		}
		float velChange = desiredVel - vel.x;
		float impulse = body.getMass()*velChange;
		body.applyLinearImpulse(new Vector2(impulse,0), body.getWorldCenter());

		Sprite sprite = new Sprite(currentFrame);
		setSprite(sprite);
	}

	public void jump(){
		if(numContacts > 0){
			Vector2 vel = body.getLinearVelocity();
			vel.y = 23;
			body.setLinearVelocity(vel);
		}
	}
	
	public void startAnimations(){
		setSprite(atlas.createSprite("stand"));
		
		runAnimationBackward = new Animation(0.15f, atlas.findRegions("runBackward"));
		runAnimationFordward = new Animation(0.15f, atlas.findRegions("runFordward"));
		stateTimeRunBackward = 0f; 
		stateTimeRunFordward = 0f; 
	}

	public void startContact(){
		numContacts++;
	}

	public void endContact(){
		numContacts--;
	}

	public void dispose(){
		texture.dispose();
		atlas.dispose();
	}
}
