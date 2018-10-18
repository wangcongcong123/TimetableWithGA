package TImetableGA;

/**
 * 这是一个讲师实体类，封装的是讲师相关的数据
 */
public class Lecturer {
	private final int lecturerId;
	private final String lecturerName;

	/**
	 * 初始化
	 * 
	 * @param lecturerId
	 * @param lecturerName
	 */
	public Lecturer(int lecturerId, String lecturerName) {
		this.lecturerId = lecturerId;
		this.lecturerName = lecturerName;
	}

	/**
	 * 获取讲师编号
	 * 
	 * @return lecturerId
	 */
	public int getLecturerId() {
		return this.lecturerId;
	}

	/**
	 * 获取讲师姓名
	 * 
	 * @return lecturerName
	 */
	public String getLecturerName() {
		return this.lecturerName;
	}
}
