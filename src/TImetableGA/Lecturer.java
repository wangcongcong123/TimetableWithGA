package TImetableGA;

/**
 * ����һ����ʦʵ���࣬��װ���ǽ�ʦ��ص�����
 */
public class Lecturer {
	private final int lecturerId;
	private final String lecturerName;

	/**
	 * ��ʼ��
	 * 
	 * @param lecturerId
	 * @param lecturerName
	 */
	public Lecturer(int lecturerId, String lecturerName) {
		this.lecturerId = lecturerId;
		this.lecturerName = lecturerName;
	}

	/**
	 * ��ȡ��ʦ���
	 * 
	 * @return lecturerId
	 */
	public int getLecturerId() {
		return this.lecturerId;
	}

	/**
	 * ��ȡ��ʦ����
	 * 
	 * @return lecturerName
	 */
	public String getLecturerName() {
		return this.lecturerName;
	}
}
