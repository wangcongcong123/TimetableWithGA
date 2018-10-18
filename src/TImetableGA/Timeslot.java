package TImetableGA;

/**
 * 时间段设置，例如 “Wed 9:00-10:00”
 * 
 * @author bkanber
 *
 */
public class Timeslot {
	private final int timeslotId;
	private final String timeslot;

	/**
	 * 初始化
	 * 
	 * @param timeslotId
	 * @param timeslot
	 */
	public Timeslot(int timeslotId, String timeslot) {
		this.timeslotId = timeslotId;
		this.timeslot = timeslot;
	}

	/**
	 * 获取时间段编码
	 * 
	 * @return timeslotId
	 */
	public int getTimeslotId() {
		return this.timeslotId;
	}

	/**
	 * 获取时间段
	 * 
	 * @return timeslot
	 */
	public String getTimeslot() {
		return this.timeslot;
	}
}
