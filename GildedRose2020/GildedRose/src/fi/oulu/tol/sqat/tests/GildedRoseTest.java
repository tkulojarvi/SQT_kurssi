package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void backstagePass10DaysOrLessTest() {
		//create an inn, add backstage pass, simulate days until 10 days remain
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		
		inn.oneDay(); //14, 19
		inn.oneDay(); //13, 18
		inn.oneDay(); //12, 17
		inn.oneDay(); //11, 16
		inn.oneDay(); //10, 18 <--
		
		//access a list of items, get the quality of the stage pass
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality is the right amount (should be 18)
		assertEquals("Failed quality for Backstage Pass at exactly 10 days", 18, quality);
		
		//the 9th day?
		inn.oneDay();
		
		//access a list of items, get the quality of the stage pass
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		//assert quality is the right amount (should be 20)
		assertEquals("Failed quality for Backstage Pass at 9 days", 20, quality);
		
	}
	
	@Test
	public void backstagePass5DaysOrLessTest() {
		//create an inn, add backstage pass, simulate days until 5 days remain
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
				
		inn.oneDay(); 
		//14, 19
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//13, 18
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//12, 17
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//11, 16 
		items = inn.getItems();
		quality = items.get(0).getQuality();
		int sellIn = items.get(0).getSellIn();
		//menee oikein tähän asti.
		
		inn.oneDay(); 
		//10, 18
		//tässä menee jotain pieleen laskussa. laskee vain +1 vaikka pitäisi olla +2.
		items = inn.getItems();
		quality = items.get(0).getQuality();
		sellIn = items.get(0).getSellIn();
		
		inn.oneDay(); 
		//9, 20
		//tässä lasketaan oikein +2.
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//8, 22
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//7, 24
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//6, 26
		items = inn.getItems();
		quality = items.get(0).getQuality();
		
		inn.oneDay(); 
		//5, 29 <--
				
		//access a list of items, get the quality of the stage pass
		items = inn.getItems();
		quality = items.get(0).getQuality();
				
		//assert quality is the right amount (should be 29)
		assertEquals("Failed quality for Backstage Pass at exactly 5 days", 29, quality);
				
		//the 4th day?
		inn.oneDay();
				
		//access a list of items, get the quality of the stage pass
		items = inn.getItems();
		quality = items.get(0).getQuality();
				
		//assert quality is the right amount (should be 32)
		assertEquals("Failed quality for Backstage Pass at 9 days", 32, quality);		
	}
	
	@Test
	public void pastSellInDateTest() {
		//create an inn, add an item, and simulate days till past sell in date
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 2, 20));
		
		inn.oneDay(); //1 days, 19
		inn.oneDay(); //0 days, 18
		inn.oneDay(); //-1 days, 16
		
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased twice as fast
		assertEquals("Failed quality for Dexterity Vest past Sell In date", 16, quality);
	}
	
	@Test
	public void pastSellInDateAgedBrieTest() {
		//create an inn, add an item, and simulate days till past sell in date
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 10));
		
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		inn.oneDay(); //1 days, 11
		quality = items.get(0).getQuality();
		inn.oneDay(); //0 days, 12
		quality = items.get(0).getQuality();
		inn.oneDay(); //-1 days, 13
		quality = items.get(0).getQuality();
		
		
		
		//assert quality has INCREASED
		assertEquals("Failed quality for Aged Brie past Sell In date", 13, quality);
	}
	
	@Test
	public void pastSellInDateBackstagePassTest() {
		//create an inn, add backstage pass, simulate days until 0 days remain
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
				
		inn.oneDay(); //14, 19
		inn.oneDay(); //13, 18
		inn.oneDay(); //12, 17
		inn.oneDay(); //11, 16
		
		inn.oneDay(); //10, 18
		inn.oneDay(); //9, 20
		inn.oneDay(); //8, 22
		inn.oneDay(); //7, 24
		inn.oneDay(); //6, 26
		inn.oneDay(); //5, 29
		
		inn.oneDay(); //4, 32
		inn.oneDay(); //3, 35
		inn.oneDay(); //2, 38
		inn.oneDay(); //1, 41
		inn.oneDay(); //0, 44
		inn.oneDay(); //-1, 0 <--
				
		//access a list of items, get the quality of the stage pass
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
				
		//assert quality is the right amount (should be 0)
		assertEquals("Failed quality for Backstage Pass after concert", 0, quality);	
	}
	
	@Test
	public void pastSellInDateSulfurasTest() {
		//create an inn, add an item, and simulate days till past sell in date
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

		inn.oneDay(); //-1 days, 80
				
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
				
		//assert quality is the same
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros past Sell In date", 80, quality);
	}
	

	
	@Test
	public void mainTest() {
		//Run main, add items, decrease quality by one
		GildedRose inn = new GildedRose();
		inn.main(null);
		
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality is the same
		assertEquals("Failed quality for +5 Dexterity Vest", 19, quality);
	}
	
	@Test
	public void qualityNeverNegativeTest() {
		//create an inn, add an item, and simulate days
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 1));
		
		inn.oneDay(); //9, 0
		inn.oneDay(); //8, 0
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Quality negative", 0, quality);
	}
	
	@Test
	public void qualityNeverOver50Test() {
		//create an inn, add an item, and simulate days
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 49));
		
		inn.oneDay(); //9, 50 
		inn.oneDay(); //8, 50
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Quality over 50", 50, quality);
	}
	
	@Test
	public void backstagePassBranchCoverageTest() {
		//create an inn, add backstage pass, quality 50.
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49));
		
		inn.oneDay(); //4, 50
		inn.oneDay(); //3, 50
		
		//access a list of items, get the quality of the stage pass
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality is the right amount (should be 18)
		assertEquals("Failed quality for Backstage Pass at maximum quality", 50, quality);
	}
	
	@Test
	public void pastSulfurasImpossibleScenarioTest() {
		//create an inn, add an item, and simulate days till past sell in date
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));

		inn.oneDay(); //-2 days, 80
				
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		
		int sellIn = items.get(0).getSellIn();
				
		//assert quality is the same
		assertEquals("Failed sell in date for Sulfuras, Hand of Ragnaros", -1, sellIn);
	}
	
	@Test
	public void pastSulfurasImpossibleScenario2Test() {
		//create an inn, add an item, and simulate days till past sell in date
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 0));

		inn.oneDay(); //-2 days, 0
				
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
				
		//assert quality is the same
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros past Sell In date", 0, quality);
	}
	
	@Test
	public void agedBrie50QualityTest() {
		//create an inn, add an item, and simulate days till past sell in date
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", -1, 50));
		
		//access a list of items, get the quality of the item
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		inn.oneDay(); //-2 days, 50

		//assert quality is 50
		assertEquals("Failed quality for Aged Brie past Sell In date at max quality", 50, quality);
	}
}
