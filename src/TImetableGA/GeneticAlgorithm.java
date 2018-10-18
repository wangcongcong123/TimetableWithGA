package TImetableGA;

/**
 * 遗传算法的核心过程在这里进行
 * 
 * @author
 *
 */
public class GeneticAlgorithm {

	private int populationSize;
	private double mutationRate;
	private double crossoverRate;
	private int elitismCount;
	protected int tournamentSize;

	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
		this.tournamentSize = tournamentSize;
	}

	/**
	 * 根据populationSize初始化population类
	 * 
	 * @return population
	 */
	public Population initPopulation(Timetable timetable) {
		Population population = new Population(this.populationSize, timetable);
		return population;
	}

	public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
		return (generationsCount > maxGenerations);
	}

	public boolean isTerminationConditionMet(Population population) {
		return population.getFittest(0).getFitness() == 1.0;
	}

	/**
	 * 计算个体的适配置
	 * 
	 * @param individual
	 * @param timetable
	 * @return fitness
	 */
	public double calcFitness(Individual individual, Timetable timetable) {
		// 克隆一个课程表
		Timetable threadTimetable = new Timetable(timetable);
		threadTimetable.createClasses(individual);

		// 计算适配值，如果冲突值为0，则达到最佳适配，便可终止繁衍，得出最后的结果
		int clashes = threadTimetable.calcClashes();
		double fitness = 1 / (double) (clashes + 1);

		individual.setFitness(fitness);
		return fitness;
	}

	/**
	 * 评估population的合适值=包含的所有个体的合适值之和
	 * 
	 * @param population
	 * @param timetable
	 */
	public void evalPopulation(Population population, Timetable timetable) {
		double populationFitness = 0;

		for (Individual individual : population.getIndividuals()) {
			populationFitness += this.calcFitness(individual, timetable);
		}

		population.setPopulationFitness(populationFitness);
	}

	/**
	 * Selects parent for crossover using tournament selection
	 * 
	 * 根据适者生存法则，具最大fittness值的将选为parent
	 * 
	 * @param population
	 * @return
	 */
	public Individual selectParent(Population population) {
		Population tournament = new Population(this.tournamentSize);

		population.shuffle();
		for (int i = 0; i < this.tournamentSize; i++) {
			Individual tournamentIndividual = population.getIndividual(i);
			tournament.setIndividual(i, tournamentIndividual);
		}
		return tournament.getFittest(0);
	}

	/**
	 * 添加基因突变到population
	 * 
	 * @param population
	 * @param timetable
	 * @return 突变后的 population
	 */
	public Population mutatePopulation(Population population, Timetable timetable) {
		Population newPopulation = new Population(this.populationSize);
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual individual = population.getFittest(populationIndex);
			Individual randomIndividual = new Individual(timetable);
			for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
				// 这里elitismCount设置为2，所有最适配的前3三个individual 将不考虑基因突变的问题
				if (populationIndex > this.elitismCount) {
					if (this.mutationRate > Math.random()) {
						individual.setGene(geneIndex, randomIndividual.getGene(geneIndex));
					}
				}
			}
			newPopulation.setIndividual(populationIndex, individual);
		}
		return newPopulation;
	}

	/**
	 * 对population繁衍更新
	 * 
	 * @param population
	 *            The population to apply crossover to
	 * @return The new population
	 */
	public Population crossoverPopulation(Population population) {
		// Create new population
		Population newPopulation = new Population(population.size());

		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual parent1 = population.getFittest(populationIndex);

			// 判断是否进行让色体交换繁衍
			if (this.crossoverRate > Math.random() && populationIndex > this.elitismCount) {
				// 初始化后代
				Individual offspring = new Individual(parent1.getChromosomeLength());

				// 寻找第二个parent
				Individual parent2 = selectParent(population);

				// 雌雄双方让色体交配长生后代
				for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
					// 这里使用子代的染色体为前代双方染色体各一半
					if (0.5 > Math.random()) {
						offspring.setGene(geneIndex, parent1.getGene(geneIndex));
					} else {
						offspring.setGene(geneIndex, parent2.getGene(geneIndex));
					}
				}
				// 添加后代到population，相当于更新族群，淘汰掉劣势物种
				newPopulation.setIndividual(populationIndex, offspring);
			} else {
				// 如果是很优秀的物种，适者生存，继续保留到下一代
				newPopulation.setIndividual(populationIndex, parent1);
			}
		}
		return newPopulation;
	}

}
