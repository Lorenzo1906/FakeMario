package com.lorenzo.logic;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lorenzo.entities.MainCharacter;

public class EntityContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		
		Object firstBody = contact.getFixtureA().getUserData();
		Object secondBody = contact.getFixtureB().getUserData();
		
		if(firstBody instanceof MainCharacter){
			MainCharacter mainCharacter = (MainCharacter) firstBody;
			mainCharacter.startContact();
		}
		
		if(secondBody instanceof MainCharacter){
			MainCharacter mainCharacter = (MainCharacter) secondBody;
			mainCharacter.startContact();
		}
	}

	@Override
	public void endContact(Contact contact) {
		Object firstBody = contact.getFixtureA().getUserData();
		Object secondBody = contact.getFixtureB().getUserData();
		
		if(firstBody instanceof MainCharacter){
			MainCharacter mainCharacter = (MainCharacter) firstBody;
			mainCharacter.endContact();
		}
		
		if(secondBody instanceof MainCharacter){
			MainCharacter mainCharacter = (MainCharacter) secondBody;
			mainCharacter.endContact();
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
