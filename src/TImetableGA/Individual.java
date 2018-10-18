package TImetableGA;

/**
 * individual 类，封装的主要是chromosome和fitness 一个individual对应一条染色体，基因是组成染色体的基本单位
 * 
 * @author
 *
 */
public class Individual {

	private int[] chromosome;
	private double fitness = -1;

	/**
	 * 随机初始化个体
	 * 
	 * @param timetable
	 *            The timetable information
	 */
	public Individual(Timetable timetable) {
		int numClasses = timetable.getNumClasses();
		// 1 基因 代表教室, 1 基因代表时间, 1 基因代表讲师
		int chromosomeLength = numClasses * 3;
		int newChromosome[] = new int[chromosomeLength];
		int chromosomeIndex = 0;
		for (Group group : timetable.getGroupsAsArray()) {
			// 遍历
			for (int moduleId : group.getModuleIds()) {
				// 添加随机时间
				int timeslotId = timetable.getRandomTimeslot().getTimeslotId();
				newChromosome[chromosomeIndex] = timeslotId;
				chromosomeIndex++;
				// 添加随机教室
				int roomId = timetable.getRandomRoom().getRoomId();
				newChromosome[chromosomeIndex] = roomId;
				chromosomeIndex++;

				// 添加随机
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
		// 通过染色体长度创建个体
		int[] individual;
		individual = new int[chromosomeLength];

		for (int gene = 0; gene < chromosomeLength; gene++) {
			individual[gene] = gene;
		}

		this.chromosome = individual;
	}

	public Individual(int[] chromosome) {
		// 通过传递染色体直接初始化
		this.chromosome = chromosome;
	}

	/**
	 * 获取个体染色体
	 * 
	 * @return
	 */
	public int[] getChromosome() {
		return this.chromosome;
	}

	/**
	 * 得到个体染色体长度
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
	 * 根据位置值得到基因代码
	 * 
	 * @param offset
	 * @return gene
	 */
	public int getGene(int offset) {
		return this.chromosome[offset];
	}

	/**
	 * 存储适配值
	 * 
	 * @param fitness
	 * 
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * 得到个体适配值
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
	 * 检测某个基因值是否在个体的染色体上
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
