package vCampus.utility.message;

public interface MessageListener {
	public boolean isAvailiable();
	public boolean isRemovable();
	public boolean resolveMessage(Message msg);
}
