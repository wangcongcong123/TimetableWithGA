package TImetableGA;

/**
 * ����һ���γ���,������װ�йؿγ̵�����
 */
public class Course {
	private final int courseId;
	private final String courseCode;
	private final String course;
	private final int professorIds[];

	/**
	 * ��ʼ��
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
	 * ��ȡ�γ̱��
	 * 
	 * @return courseId
	 */
	public int getcourseId() {
		return this.courseId;
	}

	/**
	 * ��ȥ�γ̴���
	 * 
	 * @return courseCode
	 */
	public String getcourseCode() {
		return this.courseCode;
	}

	/**
	 * ��ȡ�γ���
	 * 
	 * @return courseName
	 */
	public String getcourseName() {
		return this.course;
	}

	/**
	 * ��ȥ��ʦ���
	 * 
	 * @return professorId
	 */
	public int getRandomProfessorId() {
		int professorId = professorIds[(int) (professorIds.length * Math.random())];
		return professorId;
	}
}
