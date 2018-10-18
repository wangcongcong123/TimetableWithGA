package TImetableGA;

/**
 * individual �࣬��װ����Ҫ��chromosome��fitness һ��individual��Ӧһ��Ⱦɫ�壬���������Ⱦɫ��Ļ�����λ
 * 
 * @author
 *
 */
public class Individual {

	private int[] chromosome;
	private double fitness = -1;

	/**
	 * �����ʼ������
	 * 
	 * @param timetable
	 *            The timetable information
	 */
	public Individual(Timetable timetable) {
		int numClasses = timetable.getNumClasses();
		// 1 ���� �������, 1 �������ʱ��, 1 �������ʦ
		int chromosomeLength = numClasses * 3;
		int newChromosome[] = new int[chromosomeLength];
		int chromosomeIndex = 0;
		for (Group group : timetable.getGroupsAsArray()) {
			// ����
			for (int moduleId : group.getModuleIds()) {
				// ������ʱ��
				int timeslotId = timetable.getRandomTimeslot().getTimeslotId();
				newChromosome[chromosomeIndex] = timeslotId;
				chromosomeIndex++;
				// ����������
				int roomId = timetable.getRandomRoom().getRoomId();
				newChromosome[chromosomeIndex] = roomId;
				chromosomeIndex++;

				// ������
				Course module = timetable.getModule(moduleId);
				newChromosome[chromosomeIndex] = module.getRandomProfessorId();
				chromosomeIndex++;
			}
		}

		this.chromosome = newChromosome;
	}

	/**
	 * @param chromosomeLength
	 * 
	 */
	public Individual(int chromosomeLength) {
		// ͨ��Ⱦɫ�峤�ȴ�������
		int[] individual;
		individual = new int[chromosomeLength];

		for (int gene = 0; gene < chromosomeLength; gene++) {
			individual[gene] = gene;
		}

		this.chromosome = individual;
	}

	public Individual(int[] chromosome) {
		// ͨ������Ⱦɫ��ֱ�ӳ�ʼ��
		this.chromosome = chromosome;
	}

	/**
	 * ��ȡ����Ⱦɫ��
	 * 
	 * @return
	 */
	public int[] getChromosome() {
		return this.chromosome;
	}

	/**
	 * �õ�����Ⱦɫ�峤��
	 * 
	 * @return
	 */
	public int getChromosomeLength() {
		return this.chromosome.length;
	}

	/**
	 * Set gene at offset
	 * 
	 * @param gene
	 * @param offset
	 */
	public void setGene(int offset, int gene) {
		this.chromosome[offset] = gene;
	}

	/**
	 * ����λ��ֵ�õ��������
	 * 
	 * @param offset
	 * @return gene
	 */
	public int getGene(int offset) {
		return this.chromosome[offset];
	}

	/**
	 * �洢����ֵ
	 * 
	 * @param fitness
	 * 
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * �õ���������ֵ
	 * 
	 * @return
	 */
	public double getFitness() {
		return this.fitness;
	}

	public String toString() {
		String output = "";
		for (int gene = 0; gene < this.chromosome.length; gene++) {
			output += this.chromosome[gene] + " ";
		}
		return output;
	}

	/**
	 * ���ĳ������ֵ�Ƿ��ڸ����Ⱦɫ����
	 * 
	 * @param gene
	 * @return
	 */
	public boolean containsGene(int gene) {
		for (int i = 0; i < this.chromosome.length; i++) {
			if (this.chromosome[i] == gene) {
				return true;
			}
		}
		return false;
	}

}
