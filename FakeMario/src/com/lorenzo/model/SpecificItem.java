package com.lorenzo.model;

public class SpecificItem {
	private String textureDir;
	private Position pos;
	private Size size;
	private int hits;
	private ItemType itemType;
	private ItemContent itemContent;
	
	public SpecificItem() {
	}
	
	public SpecificItem(Position pos, Size size, String textureDir, int hits, ItemType itemType, ItemContent itemContent){
		this.pos = pos;
		this.size = size;
		this.textureDir = textureDir;
		this.hits = hits;
		this.itemType = itemType;
		this.itemContent = itemContent;
	}
	
	public String getTextureDir() {
		return textureDir;
	}

	public void setTextureDir(String textureDir) {
		this.textureDir = textureDir;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public ItemContent getItemContent() {
		return itemContent;
	}

	public void setItemContent(ItemContent itemContent) {
		this.itemContent = itemContent;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}
}
