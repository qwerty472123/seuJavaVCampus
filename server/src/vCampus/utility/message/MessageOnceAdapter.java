package vCampus.utility.message;

import javax.swing.SwingUtilities;

public class MessageOnceAdapter implements MessageListener {

	private boolean isUsed;
	
	MessageOnceAdapter() {
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

	public void resolveMessageForSwing(Message msg) {
	}
	
	@Override
	public boolean resolveMessage(Message msg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				resolveMessageForSwing(msg);
			}
		});
		return false;
	}

}