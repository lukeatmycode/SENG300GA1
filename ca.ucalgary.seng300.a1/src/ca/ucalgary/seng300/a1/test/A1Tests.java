package ca.ucalgary.seng300.a1.test;

import org.lsmr.vending.*;
import ca.ucalgary.seng300.a1.*;
import org.lsmr.vending.hardware.*;
import static org.junit.Assert.*;

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
	private CoinSlotListenerDevice listener1, listener2, listener3, listener4, listener5, listener6;

	@Before
	public void setUp() throws Exception {
		slot = new CoinSlot(new int[] {1});
		listener1 = new CoinSlotListenerDevice(slot);
		listener2 = new CoinSlotListenerDevice(slot);
		listener3 = new CoinSlotListenerDevice(button);
		listener4 = new CoinSlotListenerDevice(button);
		listener5 = new CoinSlotListenerDevice(vend);
		listener6 = new CoinSlotListenerDevice(vend);
	}

	@Test
	public void testGetValue() throws DisabledException {
		slot.addCoin(new Coin(1));
		assertEquals(1,listener1.getValue());
		assertEquals(1,listener1.getValue());
	}
	
	@Test
	public void testPressButtonWhenCoinsEnough() {
		
	}
	
	@Test
	public void testPressButtonWhenCoinsNotEnough() {
		
	}

}
