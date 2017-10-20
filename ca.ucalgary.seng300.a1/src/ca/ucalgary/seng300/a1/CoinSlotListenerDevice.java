package ca.ucalgary.seng300.a1;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CoinSlot;
import org.lsmr.vending.hardware.CoinSlotListener;

public class CoinSlotListenerDevice implements CoinSlotListener {

	private int value = 0;
	
	public CoinSlotListenerDevice(CoinSlot slot) {
		slot.register(this);
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
	 * 
	 */
	@Override
	public void coinRejected(CoinSlot slot, Coin coin) {
		// TODO coins get returned, or do nothing for now? Since "Mr. Client" isn't worried about dispensing change yet
		
	}

	public int getValue() {
		return value;
	}
	
	/**
	 * A setter method for value. Should be called when pop is dispensed to model the payment.
	 * 
	 */
	public void payForItem(int amount) {
		if (amount >= 0 && amount <= value) {
			value -= amount;
		}
		else {
			// TODO could create a custom exception or just have it do nothing. Thoughts?
		}
	}
	
}
