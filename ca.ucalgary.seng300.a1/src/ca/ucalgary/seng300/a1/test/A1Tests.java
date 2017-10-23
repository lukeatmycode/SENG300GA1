/**
 * SENG300 Group Assignment 1
 * Group 2:
 * Stephen Pollo
 * Luke Kissick
 * Xi Wang
 * Emilie Guidos
 * 
 * Test Suite for the vending machine's new CoinSlot and 
 * SelectionButton listener class
 */

package ca.ucalgary.seng300.a1.test;

import org.lsmr.vending.*;
import ca.ucalgary.seng300.a1.*;
import org.lsmr.vending.hardware.*;
import static org.junit.Assert.*;

import java.awt.List;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Unit tests the CoinSlotlistenerDevice
 */
public class A1Tests {
	private CoinSlot slot;
	private SelectionButton button;
	private VendingMachine vend;
	private CoinSlotListenerDevice listener;

	@Before
	public void setUp() throws Exception {

		int selectionButtonCount = 6;
		int coinRackCapacity = 15;
		int popCanRackCapacity = 10;
		int receptacleCapacity = 200;
		int [] coinValues = {5, 10, 25, 100, 200};
		int [] popCanLoading = new int[selectionButtonCount];
		java.util.List<String> popCanNames = Arrays.asList("Cola","Sprite","Fonda","Diet","GingerAle","DrPepper");
		java.util.List<Integer> popCanCosts = Arrays.asList(250,250,250,250,250,250);
		
		vend = new VendingMachine(coinValues, selectionButtonCount, coinRackCapacity, popCanRackCapacity, receptacleCapacity);
		vend.configure(popCanNames, popCanCosts);
		
		for(int i = 0; i < popCanLoading.length; i++) {
			popCanLoading[i] = popCanRackCapacity;
		}
		vend.loadPopCans(popCanLoading);
		
		slot = vend.getCoinSlot();
		
		listener = new CoinSlotListenerDevice(vend);
		
	}

	/**
	 * Tests the insertion of a valid coin
	 * Input - valid coin (25 cents)
	 * Expected output - 25
	 * 
	 * @throws DisabledException
	 */
	@Test
	public void testGetValue() throws DisabledException {
		slot.addCoin(new Coin(25));
		assertEquals(25,listener.getValue());

	}
	
	/**
	 * Tests the insertion of an invalid coin
	 * Input - invalid coin (7 cents)
	 * Expected output - 0
	 * 
	 * @throws DisabledException
	 */
	@Test
	public void testInvalidCoin() throws DisabledException {
		slot.addCoin(new Coin(7));
		assertEquals(0,listener.getValue());

	}
	
	/**
	 * 
	 * @throws DisabledException
	 */
	@Test
	public void testPressButtonWhenCoinsEnough() throws DisabledException {
		slot.addCoin(new Coin(200));
		slot.addCoin(new Coin(25));
		slot.addCoin(new Coin(25));
		button = vend.getSelectionButton(0);
		listener.pressed(button);
		Deliverable [] vendedItems = vend.getDeliveryChute().removeItems();
		
		//Product should have vended and value subtracted
		assertEquals(0,listener.getValue()); 
		assertEquals(PopCan.class, vendedItems[0].getClass());
		assertEquals(9, vend.getPopCanRack(0).size());
	}
	
	/**
	 * Verifies that when a button is pressed but there are insufficient funds in the
	 * machine that no pop is dispensed and no money has been deducted.
	 * 
	 * @throws DisabledException
	 */
	//NOTE: This test does not yet work (Does not test for right things yet)
	@Test
	public void testPressButtonWhenCoinsNotEnough() throws DisabledException {
		slot.addCoin(new Coin(25));
		button = vend.getSelectionButton(0);
		listener.pressed(button);
		Deliverable [] vendedItems = vend.getDeliveryChute().removeItems();
		boolean thrown = false;
		try {
			vendedItems[0].getClass();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			thrown = true;
		}
		
		assertEquals(25,listener.getValue());
		assertEquals(10, vend.getPopCanRack(0).size());
		assertEquals(true, thrown);
	}

}
