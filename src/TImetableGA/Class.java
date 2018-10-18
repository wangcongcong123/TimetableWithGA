package TImetableGA;

/**
 * 这是一个实体类，封装有关class的数据
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
	 * 初始化
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
	 * 添加老师
	 * 
	 * @param lecturerId
	 */
	public void addlecturer(int lecturerId) {
		this.lecturerId = lecturerId;
	}

	/**
	 * 添加时间段
	 * 
	 * @param timeslotId
	 */
	public void addTimeslot(int timeslotId) {
		this.timeslotId = timeslotId;
	}

	/**
	 * 添加教师
	 * 
	 * @param roomId
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * 获取班级编号
	 * 
	 * @return classId
	 */
	public int getClassId() {
		return this.classId;
	}

	/**
	 * 获取组编号
	 * 
	 * @return groupId
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * 获取课程代码
	 * 
	 * @return moduleId
	 */
	public int getModuleId() {
		return this.moduleId;
	}

	/**
	 * 获取老师编号
	 * 
	 * @return lecturerId
	 */
	public int getlecturerId() {
		return this.lecturerId;
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
	 * 获取教师编码
	 * 
	 * @return roomId
	 */
	public int getRoomId() {
		return this.roomId;
	}
}
