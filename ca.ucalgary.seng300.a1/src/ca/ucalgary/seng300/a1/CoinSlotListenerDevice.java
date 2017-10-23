package ca.ucalgary.seng300.a1;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;

public class CoinSlotListenerDevice implements CoinSlotListener, SelectionButtonListener {

	private int value = 0;
	private VendingMachine vending;
	
	/**
	 * Constructor used for testing purposes
	 * @param slot The CoinSlot being used for testing
	 */
	public CoinSlotListenerDevice(CoinSlot slot) {
		slot.register(this);
		value = 0;	
	}
	 
	/**
	 * Constructor used for testing purposes
	 * @param button the SelectionButton being used for testing
	 */
	public CoinSlotListenerDevice(SelectionButton button) {
		button.register(this);
	}
	
	/**
	 * Main constructor. Registers the listener to the coin slot and all 
	 * buttons of the vending machine
	 * @param vend the VendingMachine object to listen to
	 */
	public CoinSlotListenerDevice(VendingMachine vend) {
		this.vending = vend;
		for (int i = 0; i < vend.getNumberOfSelectionButtons(); i++) {
			vend.getSelectionButton(i).register(this);
		}
		vend.getCoinSlot().register(this);
		value = 0;
	}
	
	/**
     * Announces that the indicated hardware has been enabled.
     * 
     * @param hardware
     *            The device that has been enabled.
     */
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// Do nothing for now
	}

	/**
     * Announces that the indicated hardware has been disabled.
     * 
     * @param hardware
     *            The device that has been enabled.
     */
	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// Do nothing for now
	}

	/**
	 * Updates value to track the amount of money inserted so far
	 */
	@Override
	public void validCoinInserted(CoinSlot slot, Coin coin) {
		if (!slot.isDisabled()) {
			value += coin.getValue();
		}	
	}

	/**
	 * Handles the return of invalid coins
	 */
	@Override
	public void coinRejected(CoinSlot slot, Coin coin) {
		// Do nothing for now
		
	}

	/**
	 * Getter method for value - the amount of valid money in the machine
	 * @return value 
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * A setter method for value. Should be called when pop is dispensed to model the payment.
	 * Will only deduct money if there is enough money in the machine.
	 * @param amount of money to deduct as payment 
	 */
	public void payForItem(int amount) {
		if (amount >= 0 && amount <= value) {
			value -= amount;
		}
		else {
			// do nothing for now
		}
	}

	/**
	 * Handles the pop vending decision associated with a press of a 
	 * SelectionButton.
	 * @param SelectionButton the button that got pressed
	 */
	@Override
	public void pressed(SelectionButton button) {
		int index = findButtonIndex(button);
		int cost = vending.getPopKindCost(index);
		
		
		if(index == -1){
			// Do nothing for now
		}
		else if (value >= cost) {
			try {
				vending.getPopCanRack(index).dispensePopCan();
			} catch (DisabledException | EmptyException | CapacityExceededException e) {
				e.printStackTrace();
			}
			payForItem(cost);
		}
		else {
			// Do nothing for now
			
			//Added by Cynthia
			//What about report not enough coins and choose to return the inserted coins or continue inserting coin?
			// These are likely going to be in the future assignments 
		}
		
	}
	
	/**
	 * Finds the index of a SelectionButton in this objects VendingMachine reference's
	 * button array
	 * @param button the SelectionButton to search for in the VendingMachine button array
	 * @return the index of the SelectionButton in the button array, -1 if it's not there
	 */
	public int findButtonIndex (SelectionButton button) {
		for (int i = 0; i < vending.getNumberOfSelectionButtons(); i++) {
			if (vending.getSelectionButton(i) == button) {
				return i;
			}
		}
		return -1;
	}
	
}
