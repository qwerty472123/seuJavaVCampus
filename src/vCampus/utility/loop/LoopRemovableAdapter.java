package vCampus.utility.loop;

import java.util.Map;

import javax.swing.SwingUtilities;

public class LoopRemovableAdapter implements LoopListener {

	private boolean isRemoved;
	
	public LoopRemovableAdapter() {
		isRemoved = false;
	}
	
	@Override
	public boolean isAvailiable() {
		return !isRemoved;
	}

	@Override
	public boolean isRemovable() {
		return isRemoved;
	}
	
	public void remove() {
		isRemoved = true;
	}

	public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
	}
	
	@Override
	public boolean resolveMessage(Message msg, Map<String, Object> transferData) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				resolveMessageForSwing(msg, transferData);
			}
		});
		return false;
	}

}
