package vCampus.utility.loop;

import java.util.Map;

public interface LoopListener {
	public boolean isAvailiable();
	public boolean isRemovable();
	public boolean resolveMessage(Message msg, Map<String, Object> transferData) throws Exception;
}
