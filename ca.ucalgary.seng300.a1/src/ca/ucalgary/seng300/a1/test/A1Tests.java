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
		int selectionButtonCount = 3;
		java.util.List<String> popCanNames = Arrays.asList("Cola","Sprite","Fonda");
		java.util.List<Integer> popCanCosts = Arrays.asList(1,2,3);
		
		vend = new VendingMachine(new int[] {1}, selectionButtonCount, 100, 100, 100);
		vend.configure(popCanNames, popCanCosts);
		slot = vend.getCoinSlot();
		
		listener = new CoinSlotListenerDevice(vend);
		
	}

	@Test
	public void testGetValue() throws DisabledException {
		slot.addCoin(new Coin(1));
		assertEquals(1,listener.getValue());
	}
	
	
	@Test
	public void testPressButtonWhenCoinsEnough() throws DisabledException {
		//First insert enough coins. (10)
		for(int i = 0; i <= 10; i++) {
			slot.addCoin(new Coin(1));
		}
		button = vend.getSelectionButton(0);
		listener.pressed(button);
		assertEquals(7,listener.getValue());
	}
	
	@Test
	public void testPressButtonWhenCoinsNotEnough() {
		button = vend.getSelectionButton(0);
		listener.pressed(button);
		assertEquals(10,listener.getValue());
	}

}
