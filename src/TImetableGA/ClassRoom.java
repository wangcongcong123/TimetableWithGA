package TImetableGA;

/**
 * ���ǽ�ʦ��ʵ���࣬��װ�����йؽ��ҵ�����
 */
public class ClassRoom {
	private final int roomId;
	private final String roomNumber;
	private final int capacity;

	/**
	 * ��ʼ��
	 * 
	 * @param roomId
	 * @param roomNumber
	 * @param capacity
	 */
	public ClassRoom(int roomId, String roomNumber, int capacity) {
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
	}

	/**
	 * �õ����ұ���
	 * 
	 * @return roomId
	 */
	public int getRoomId() {
		return this.roomId;
	}

	/**
	 * �õ�����λ��
	 * 
	 * @return roomNumber
	 */
	public String getRoomNumber() {
		return this.roomNumber;
	}

	/**
	 * �õ���������
	 * 
	 * @return capacity
	 */
	public int getRoomCapacity() {
		return this.capacity;
	}
}