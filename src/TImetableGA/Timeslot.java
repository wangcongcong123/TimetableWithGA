package TImetableGA;

/**
 * ʱ������ã����� ��Wed 9:00-10:00��
 * 
 * @author bkanber
 *
 */
public class Timeslot {
	private final int timeslotId;
	private final String timeslot;

	/**
	 * ��ʼ��
	 * 
	 * @param timeslotId
	 * @param timeslot
	 */
	public Timeslot(int timeslotId, String timeslot) {
		this.timeslotId = timeslotId;
		this.timeslot = timeslot;
	}

	/**
	 * ��ȡʱ��α���
	 * 
	 * @return timeslotId
	 */
	public int getTimeslotId() {
		return this.timeslotId;
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return timeslot
	 */
	public String getTimeslot() {
		return this.timeslot;
	}
}
