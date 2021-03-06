package com.lorenzo.persistence;

import java.util.List;

import com.lorenzo.model.Actor;
import com.lorenzo.model.SpecificItem;
import com.lorenzo.model.Static;

public class LevelDescriptor {
	private String levelName;
	private int levelWidth;
	private Actor character;
	private List<Static> firstLayer;
	private List<Static> secondLayer;
	private List<Static> thirdLayer;
	private List<Static> fourthLayer;
	private List<Static> staticSprites;
	private List<SpecificItem> specificItems;

	public LevelDescriptor() {
	}
	
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelWidth() {
		return levelWidth;
	}

	public void setLevelWidth(int levelWidth) {
		this.levelWidth = levelWidth;
	}
	
	public Actor getCharacter() {
		return character;
	}

	public void setCharacter(Actor character) {
		this.character = character;
	}


	public List<Static> getFirstLayer() {
		return firstLayer;
	}

	public void setFirstLayer(List<Static> firstLayer) {
		this.firstLayer = firstLayer;
	}

	public List<Static> getSecondLayer() {
		return secondLayer;
	}

	public void setSecondLayer(List<Static> secondLayer) {
		this.secondLayer = secondLayer;
	}

	public List<Static> getThirdLayer() {
		return thirdLayer;
	}

	public void setThirdLayer(List<Static> thirdLayer) {
		this.thirdLayer = thirdLayer;
	}

	public List<Static> getFourthLayer() {
		return fourthLayer;
	}

	public void setFourthLayer(List<Static> fourthLayer) {
		this.fourthLayer = fourthLayer;
	}
	public List<Static> getStaticSprites() {
		return staticSprites;
	}

	public void setStaticSprites(List<Static> staticSprites) {
		this.staticSprites = staticSprites;
	}

	public List<SpecificItem> getSpecificItems() {
		return specificItems;
	}

	public void setSpecificItems(List<SpecificItem> specificItems) {
		this.specificItems = specificItems;
	}

}
