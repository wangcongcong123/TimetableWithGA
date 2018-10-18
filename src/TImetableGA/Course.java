package TImetableGA;

/**
 * 这是一个课程类,用来封装有关课程的数据
 */
public class Course {
	private final int courseId;
	private final String courseCode;
	private final String course;
	private final int professorIds[];

	/**
	 * 初始化
	 * 
	 * @param courseId
	 * @param courseCode
	 * @param course
	 * @param professorIds
	 */
	public Course(int courseId, String courseCode, String course, int professorIds[]) {
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.course = course;
		this.professorIds = professorIds;
	}

	/**
	 * 获取课程编号
	 * 
	 * @return courseId
	 */
	public int getcourseId() {
		return this.courseId;
	}

	/**
	 * 过去课程代码
	 * 
	 * @return courseCode
	 */
	public String getcourseCode() {
		return this.courseCode;
	}

	/**
	 * 获取课程名
	 * 
	 * @return courseName
	 */
	public String getcourseName() {
		return this.course;
	}

	/**
	 * 过去讲师编号
	 * 
	 * @return professorId
	 */
	public int getRandomProfessorId() {
		int professorId = professorIds[(int) (professorIds.length * Math.random())];
		return professorId;
	}
}
