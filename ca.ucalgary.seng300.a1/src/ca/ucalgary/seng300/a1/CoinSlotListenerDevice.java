package ca.ucalgary.seng300.a1;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CoinSlot;
import org.lsmr.vending.hardware.CoinSlotListener;

public class CoinSlotListenerDevice implements CoinSlotListener {

	private int value = 0;
	private boolean disabled = false;
	
	public CoinSlotListenerDevice(CoinSlot slot) {
		slot.register(this);
		disabled = slot.isDisabled();
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
		hardware.enable();
		hardware.notify();
		
	}

	/**
     * Announces that the indicated hardware has been disabled.
     * 
     * @param hardware
     *            The device that has been enabled.
     */
	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		hardware.disable(); // won't this cause a stack overflow? hardware.disable() calls notifyDisabled() which calls this method
		hardware.notify();
		
	}

	@Override
	public void validCoinInserted(CoinSlot slot, Coin coin) {
		if (!disabled) {
			value = value + coin.getValue();
		}
		
	}

	@Override
	public void coinRejected(CoinSlot slot, Coin coin) {
		// coins get returned 
		
	}

	public int getValue(){
		return value;
	}
	
}
