package vCampus.utility.loop;

import java.util.Map;

import javax.swing.SwingUtilities;

public class LoopAlwaysAdapter implements LoopListener {

	@Override
	public boolean isAvailiable() {
		return true;
	}

	@Override
	public boolean isRemovable() {
		return false;
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
