package TImetableGA;

/**
 * 学生是按照分开，这里以大学课程为例子，对于两个不同专业，有些课程是需要共同上的，比如计算机网络这门课对物联网和软件工程专业的这两组同学都要上。 还有一种情况是：同一专业人数太多，大教室不足以同时容纳，为了课程安排的方便性，也有分组的必要。
 *
 */
/**
 * 这是一个有关组的实体类，封装的是有关组的数据
 * 
 * @author
 *
 */
public class Group {
	private final int groupId;
	private final int groupSize;
	private final int moduleIds[];

	/**
	 * 初始化
	 * 
	 * @param groupId
	 * @param groupSize
	 * @param moduleIds
	 */
	public Group(int groupId, int groupSize, int moduleIds[]) {
		this.groupId = groupId;
		this.groupSize = groupSize;
		this.moduleIds = moduleIds;
	}

	/**
	 * 得到组编号
	 * 
	 * @return groupId
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * 得到组人数
	 * 
	 * @return groupSize
	 */
	public int getGroupSize() {
		return this.groupSize;
	}

	/**
	 * 得到改组同学所需参加所有课程的编号集合
	 * 
	 * @return moduleIds
	 */
	public int[] getModuleIds() {
		return this.moduleIds;
	}
}
