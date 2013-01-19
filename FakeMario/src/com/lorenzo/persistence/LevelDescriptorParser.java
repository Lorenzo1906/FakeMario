package com.lorenzo.persistence;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.lorenzo.model.Actor;
import com.lorenzo.model.ActorType;
import com.lorenzo.model.ItemContent;
import com.lorenzo.model.ItemType;
import com.lorenzo.model.Position;
import com.lorenzo.model.Size;
import com.lorenzo.model.SpecificItem;
import com.lorenzo.model.Static;


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
		
		Actor actor = new Actor("data/Mario/mario.pack", new Position("100", "300"), new Size("64", "64"), ActorType.MAIN_CHARACTER);
		
		List<Static> firstLayer = new ArrayList<Static>();
		firstLayer.add(new Static(new Position("0", "0"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("0", "256"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("0", "512"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("256", "0"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("256", "256"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("256", "512"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("512", "0"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("512", "256"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("512", "512"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("768", "0"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("768", "256"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("768", "512"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("1024", "0"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("1024", "256"), new Size("256","256"), "data/layers/Layer11.png"));
		firstLayer.add(new Static(new Position("1024", "512"), new Size("256","256"), "data/layers/Layer12.png"));

		List<Static> secondLayer = new ArrayList<Static>();
		secondLayer.add(new Static(new Position("155", "465"), new Size("256","256"), "data/layers/Layer21.png"));
		secondLayer.add(new Static(new Position("550", "380"), new Size("256","256"), "data/layers/Layer23.png"));
		secondLayer.add(new Static(new Position("1040", "390"), new Size("256","256"), "data/layers/Layer24.png"));
		secondLayer.add(new Static(new Position("1500", "420"), new Size("256","256"), "data/layers/Layer21.png"));
		secondLayer.add(new Static(new Position("2000", "420"), new Size("256","256"), "data/layers/Layer23.png"));
		secondLayer.add(new Static(new Position("2450", "390"), new Size("256","256"), "data/layers/Layer24.png"));

		List<Static> thirdLayer = new ArrayList<Static>();
		thirdLayer.add(new Static(new Position("1405", "265"), new Size("256","256"), "data/layers/Layer22.png"));
		thirdLayer.add(new Static(new Position("1800", "520"), new Size("256","256"), "data/layers/Layer24.png"));
		thirdLayer.add(new Static(new Position("2400", "260"), new Size("256","256"), "data/layers/Layer21.png"));
		thirdLayer.add(new Static(new Position("2900", "420"), new Size("256","256"), "data/layers/Layer22.png"));
		thirdLayer.add(new Static(new Position("3450", "350"), new Size("256","256"), "data/layers/Layer24.png"));
		thirdLayer.add(new Static(new Position("4000", "200"), new Size("256","256"), "data/layers/Layer21.png"));

		List<Static> fourthLayer = new ArrayList<Static>();
		fourthLayer.add(new Static(new Position("0", "64"), new Size("256","256"), "data/layers/Layer31.png"));
		fourthLayer.add(new Static(new Position("400", "64"), new Size("256","256"), "data/layers/Layer32.png"));
		fourthLayer.add(new Static(new Position("790", "64"), new Size("256","256"), "data/layers/Layer33.png"));
		fourthLayer.add(new Static(new Position("1000", "64"), new Size("256","256"), "data/layers/Layer31.png"));
		fourthLayer.add(new Static(new Position("1500", "64"), new Size("256","256"), "data/layers/Layer32.png"));
		fourthLayer.add(new Static(new Position("2000", "64"), new Size("256","256"), "data/layers/Layer33.png"));
		fourthLayer.add(new Static(new Position("2300", "64"), new Size("256","256"), "data/layers/Layer31.png"));
		fourthLayer.add(new Static(new Position("2700", "64"), new Size("256","256"), "data/layers/Layer32.png"));
		fourthLayer.add(new Static(new Position("3500", "64"), new Size("256","256"), "data/layers/Layer33.png"));

		List<Static> staticSprites = new ArrayList<Static>();
		staticSprites.add(new Static(new Position("0", "0"), new Size("10880","64"), "data/Brick.png"));

		List<SpecificItem> items = new ArrayList<SpecificItem>();
		items.add(new SpecificItem(new Position("1280", "256"), new Size("64", "64"), "data/Steel.png", 1, ItemType.DESTRUCTIBLE, ItemContent.NOTHING));
		items.add(new SpecificItem(new Position("1344", "256"), new Size("64", "64"), "data/Steel.png", 1, ItemType.GIFT_BOX, ItemContent.LIFE));

		LevelDescriptor level = new LevelDescriptor();
		level.setLevelName("Level One");
		level.setLevelWidth(10880);
		level.setFirstLayer(firstLayer);
		level.setSecondLayer(secondLayer);
		level.setThirdLayer(thirdLayer);
		level.setFourthLayer(fourthLayer);
		level.setStaticSprites(staticSprites);
		level.setSpecificItems(items);
		level.setCharacter(actor);

		Json json = new Json();
		System.out.println(json.prettyPrint(level));
	}
}
