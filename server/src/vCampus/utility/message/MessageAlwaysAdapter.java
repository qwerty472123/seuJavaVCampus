package vCampus.utility.message;

import javax.swing.SwingUtilities;

public class MessageAlwaysAdapter implements MessageListener {

	@Override
	public boolean isAvailiable() {
		return true;
	}

	@Override
	public boolean isRemovable() {
		return false;
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
