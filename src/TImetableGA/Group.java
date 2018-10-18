package TImetableGA;

/**
 * ѧ���ǰ��շֿ��������Դ�ѧ�γ�Ϊ���ӣ�����������ͬרҵ����Щ�γ�����Ҫ��ͬ�ϵģ����������������ſζ����������������רҵ��������ͬѧ��Ҫ�ϡ� ����һ������ǣ�ͬһרҵ����̫�࣬����Ҳ�����ͬʱ���ɣ�Ϊ�˿γ̰��ŵķ����ԣ�Ҳ�з���ı�Ҫ��
 *
 */
/**
 * ����һ���й����ʵ���࣬��װ�����й��������
 * 
 * @author
 *
 */
public class Group {
	private final int groupId;
	private final int groupSize;
	private final int moduleIds[];

	/**
	 * ��ʼ��
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
	 * �õ�����
	 * 
	 * @return groupId
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * �õ�������
	 * 
	 * @return groupSize
	 */
	public int getGroupSize() {
		return this.groupSize;
	}

	/**
	 * �õ�����ͬѧ����μ����пγ̵ı�ż���
	 * 
	 * @return moduleIds
	 */
	public int[] getModuleIds() {
		return this.moduleIds;
	}
}
