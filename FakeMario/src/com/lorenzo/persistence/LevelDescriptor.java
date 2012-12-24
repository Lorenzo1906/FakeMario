package com.lorenzo.persistence;

import java.util.List;

import com.lorenzo.entities.Background;

public class LevelDescriptor {
	private String levelName;
	private int levelWidth;
	private List<Background> firstLayer;
	private List<Background> secondLayer;
	private List<Background> thirdLayer;
	private List<Background> fourthLayer;
	
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

	public List<Background> getFirstLayer() {
		return firstLayer;
	}

	public void setFirstLayer(List<Background> firstLayer) {
		this.firstLayer = firstLayer;
	}

	public List<Background> getSecondLayer() {
		return secondLayer;
	}

	public void setSecondLayer(List<Background> secondLayer) {
		this.secondLayer = secondLayer;
	}

	public List<Background> getThirdLayer() {
		return thirdLayer;
	}

	public void setThirdLayer(List<Background> thirdLayer) {
		this.thirdLayer = thirdLayer;
	}

	public List<Background> getFourthLayer() {
		return fourthLayer;
	}

	public void setFourthLayer(List<Background> fourthLayer) {
		this.fourthLayer = fourthLayer;
	}

}
