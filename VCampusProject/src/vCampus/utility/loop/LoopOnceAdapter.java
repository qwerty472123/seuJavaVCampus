package vCampus.utility.loop;

import java.util.Map;

import javax.swing.SwingUtilities;

public class LoopOnceAdapter implements LoopListener {

	private boolean isUsed;
	
	public LoopOnceAdapter() {
		isUsed = false;
	}
	
	@Override
	public boolean isAvailiable() {
		if (!isUsed) {
			isUsed = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean isRemovable() {
		return isUsed;
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
