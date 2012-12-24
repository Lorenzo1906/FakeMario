package com.lorenzo.persistence;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.lorenzo.entities.Background;
import com.lorenzo.entities.Position;


public class LevelDescriptorParser {

	private FileHandle levelDataFile;
	private Json json;
	
	public LevelDescriptorParser(String levelDir) {
		levelDataFile = Gdx.files.internal(levelDir);
		json = new Json();
	}
	
	public LevelDescriptor parseLevel(){
		String levelAsCode = levelDataFile.readString();
		return json.fromJson(LevelDescriptor.class, levelAsCode);
	}

	public void writeLevel(){
		List<Background> firstLayer = new ArrayList<Background>();
		firstLayer.add(new Background(new Position("0", "0"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("0", "256"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("0", "512"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("256", "0"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("256", "256"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("256", "512"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("512", "0"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("512", "256"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("512", "512"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("768", "0"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("768", "256"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("768", "512"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("1024", "0"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("1024", "256"), "data/layers/Layer11.png"));
		firstLayer.add(new Background(new Position("1024", "512"), "data/layers/Layer12.png"));
		
		List<Background> secondLayer = new ArrayList<Background>();
		secondLayer.add(new Background(new Position("256", "0"), "data/layers/Layer21.png"));
		secondLayer.add(new Background(new Position("256", "50"), "data/layers/Layer21.png"));
		secondLayer.add(new Background(new Position("256", "100"), "data/layers/Layer21.png"));
		
		List<Background> thirdLayer = new ArrayList<Background>();
		thirdLayer.add(new Background(new Position("256", "25"), "data/layers/Layer23.png"));
		thirdLayer.add(new Background(new Position("256", "75"), "data/layers/Layer23.png"));
		thirdLayer.add(new Background(new Position("256", "125"), "data/layers/Layer23.png"));
		
		List<Background> fourthLayer = new ArrayList<Background>();
		fourthLayer.add(new Background(new Position("256", "0"), "data/layers/Layer31.png"));
		fourthLayer.add(new Background(new Position("256", "50"), "data/layers/Layer31.png"));
		fourthLayer.add(new Background(new Position("256", "100"), "data/layers/Layer31.png"));
		
		LevelDescriptor level = new LevelDescriptor();
		level.setLevelName("Level One");
		level.setLevelWidth(108877);
		level.setFirstLayer(firstLayer);
		level.setSecondLayer(secondLayer);
		level.setThirdLayer(thirdLayer);
		level.setFourthLayer(fourthLayer);
		
		Json json = new Json();
		System.out.println(json.prettyPrint(level));
	}
}
