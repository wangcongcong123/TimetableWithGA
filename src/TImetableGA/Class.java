package TImetableGA;

/**
 * ����һ��ʵ���࣬��װ�й�class������
 * 
 * @author
 *
 */
public class Class {
	private final int classId;
	private final int groupId;
	private final int moduleId;
	private int lecturerId;
	private int timeslotId;
	private int roomId;

	/**
	 * ��ʼ��
	 * 
	 * @param classId
	 * @param groupId
	 * @param moduleId
	 */
	public Class(int classId, int groupId, int moduleId) {
		this.classId = classId;
		this.moduleId = moduleId;
		this.groupId = groupId;
	}

	/**
	 * �����ʦ
	 * 
	 * @param lecturerId
	 */
	public void addlecturer(int lecturerId) {
		this.lecturerId = lecturerId;
	}

	/**
	 * ���ʱ���
	 * 
	 * @param timeslotId
	 */
	public void addTimeslot(int timeslotId) {
		this.timeslotId = timeslotId;
	}

	/**
	 * ��ӽ�ʦ
	 * 
	 * @param roomId
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * ��ȡ�༶���
	 * 
	 * @return classId
	 */
	public int getClassId() {
		return this.classId;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return groupId
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * ��ȡ�γ̴���
	 * 
	 * @return moduleId
	 */
	public int getModuleId() {
		return this.moduleId;
	}

	/**
	 * ��ȡ��ʦ���
	 * 
	 * @return lecturerId
	 */
	public int getlecturerId() {
		return this.lecturerId;
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
	 * ��ȡ��ʦ����
	 * 
	 * @return roomId
	 */
	public int getRoomId() {
		return this.roomId;
	}
}
