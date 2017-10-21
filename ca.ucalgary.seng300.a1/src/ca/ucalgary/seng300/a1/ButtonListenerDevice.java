package ca.ucalgary.seng300.a1;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;

public class ButtonListenerDevice implements SelectionButtonListener {
	
	private VendingMachine vending;
	
	public ButtonListenerDevice(VendingMachine vend) {
		this.vending = vend;
		for (int i = 0; i < vend.getNumberOfSelectionButtons(); i++) {
			vend.getSelectionButton(i).register(this);
		}
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// do nothing for now
		
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// do nothing for now
		
	}

	@Override
	public void pressed(SelectionButton button) {
		// TODO Auto-generated method stub
		int index = findButtonIndex(button);
		int cost = vending.getPopKindCost(index);
		
		// check if there is enough money in machine
		
		// if there is, dispense the pop and deduct the pop cost from the money in the machine
		
	}
	
	public int findButtonIndex (SelectionButton button) {
		for (int i = 0; i < vending.getNumberOfSelectionButtons(); i++) {
			if (vending.getSelectionButton(i) == button) {
				return i;
			}
		}
		return -1;
	}

}
