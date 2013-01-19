package com.lorenzo.entities;

import com.lorenzo.model.ItemContent;
import com.lorenzo.model.ItemType;

public class Item extends Entity{
	
	private int hits;
	private int maximunHits;
	private boolean flaggedForDelete;
	private ItemType itemType;
	private ItemContent itemContent;

	public Item() {
		hits = 0;
		setFlaggedForDelete(false);
	}
	
	public void startContact(){
		hits++;
		if(itemType.equals(ItemType.DESTRUCTIBLE)){
			if(hits == maximunHits){
				setFlaggedForDelete(true);
			}
		}
		if(itemType.equals(ItemType.GIFT_BOX)){
			if (itemContent.equals(ItemContent.LIFE)) {
				if(hits <= maximunHits){
					System.out.println("You have one life more :D");
				}
			}
		}
	}
	
	public void destroy(){
		body.setActive(false);
		dispose();
	}

	public void dispose(){
		texture.dispose();
	}

	public int getHits() {
		return hits;
	}

	public int getMaximunHits() {
		return maximunHits;
	}

	public void setMaximunHits(int maximunHits) {
		this.maximunHits = maximunHits;
	}

	public boolean isFlaggedForDelete() {
		return flaggedForDelete;
	}

	public void setFlaggedForDelete(boolean flaggedForDelete) {
		this.flaggedForDelete = flaggedForDelete;
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
}
