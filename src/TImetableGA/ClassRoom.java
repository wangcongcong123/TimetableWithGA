package TImetableGA;

/**
 * 这是教师的实体类，封装的是有关教室的数据
 */
public class ClassRoom {
	private final int roomId;
	private final String roomNumber;
	private final int capacity;

	/**
	 * 初始化
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
	 * 得到教室编码
	 * 
	 * @return roomId
	 */
	public int getRoomId() {
		return this.roomId;
	}

	/**
	 * 得到教室位置
	 * 
	 * @return roomNumber
	 */
	public String getRoomNumber() {
		return this.roomNumber;
	}

	/**
	 * 得到教室容量
	 * 
	 * @return capacity
	 */
	public int getRoomCapacity() {
		return this.capacity;
	}
}