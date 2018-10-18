package TImetableGA;

/**
 * �Ŵ��㷨�ĺ��Ĺ������������
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
	 * ����populationSize��ʼ��population��
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
	 * ��������������
	 * 
	 * @param individual
	 * @param timetable
	 * @return fitness
	 */
	public double calcFitness(Individual individual, Timetable timetable) {
		// ��¡һ���γ̱�
		Timetable threadTimetable = new Timetable(timetable);
		threadTimetable.createClasses(individual);

		// ��������ֵ�������ͻֵΪ0����ﵽ������䣬�����ֹ���ܣ��ó����Ľ��
		int clashes = threadTimetable.calcClashes();
		double fitness = 1 / (double) (clashes + 1);

		individual.setFitness(fitness);
		return fitness;
	}

	/**
	 * ����population�ĺ���ֵ=���������и���ĺ���ֵ֮��
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
	 * �����������淨�򣬾����fittnessֵ�Ľ�ѡΪparent
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
	 * ��ӻ���ͻ�䵽population
	 * 
	 * @param population
	 * @param timetable
	 * @return ͻ���� population
	 */
	public Population mutatePopulation(Population population, Timetable timetable) {
		Population newPopulation = new Population(this.populationSize);
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual individual = population.getFittest(populationIndex);
			Individual randomIndividual = new Individual(timetable);
			for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
				// ����elitismCount����Ϊ2�������������ǰ3����individual �������ǻ���ͻ�������
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
	 * ��population���ܸ���
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

			// �ж��Ƿ������ɫ�彻������
			if (this.crossoverRate > Math.random() && populationIndex > this.elitismCount) {
				// ��ʼ�����
				Individual offspring = new Individual(parent1.getChromosomeLength());

				// Ѱ�ҵڶ���parent
				Individual parent2 = selectParent(population);

				// ����˫����ɫ�彻�䳤�����
				for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
					// ����ʹ���Ӵ���Ⱦɫ��Ϊǰ��˫��Ⱦɫ���һ��
					if (0.5 > Math.random()) {
						offspring.setGene(geneIndex, parent1.getGene(geneIndex));
					} else {
						offspring.setGene(geneIndex, parent2.getGene(geneIndex));
					}
				}
				// ��Ӻ����population���൱�ڸ�����Ⱥ����̭����������
				newPopulation.setIndividual(populationIndex, offspring);
			} else {
				// ����Ǻ���������֣��������棬������������һ��
				newPopulation.setIndividual(populationIndex, parent1);
			}
		}
		return newPopulation;
	}

}
