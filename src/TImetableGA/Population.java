package TImetableGA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population {
	private Individual population[];
	private double populationFitness = -1;

	/**
	 * 初始化空的population
	 * 
	 * @param populationSize
	 *            The size of the population
	 */
	public Population(int populationSize) {
		// Initial population
		this.population = new Individual[populationSize];
	}

	/**
	 * 根据课程表创建population
	 * 
	 * @param populationSize
	 * @param timetable
	 */
	public Population(int populationSize, Timetable timetable) {
		this.population = new Individual[populationSize];

		for (int individualCount = 0; individualCount < populationSize; individualCount++) {
			Individual individual = new Individual(timetable);
			this.population[individualCount] = individual;
		}
	}

	/**
	 * 根据染色体长度初始化population
	 * 
	 * @param populationSize
	 * @param chromosomeLength
	 */
	public Population(int populationSize, int chromosomeLength) {
		this.population = new Individual[populationSize];
		for (int individualCount = 0; individualCount < populationSize; individualCount++) {
			Individual individual = new Individual(chromosomeLength);
			this.population[individualCount] = individual;
		}
	}

	/**
	 * 得到所有的个体
	 * 
	 * @return individuals Individuals in population
	 */
	public Individual[] getIndividuals() {
		return this.population;
	}

	/**
	 * 找到最适合的个体
	 * 
	 * @param offset
	 * @return Individual
	 */
	public Individual getFittest(int offset) {
		Arrays.sort(this.population, new Comparator<Individual>() {
			@Override
			public int compare(Individual o1, Individual o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});

		return this.population[offset];
	}

	/**
	 * 
	 * @param fitness
	 * 
	 */
	public void setPopulationFitness(double fitness) {
		this.populationFitness = fitness;
	}

	/**
	 * 
	 * @return populationFitness
	 */
	public double getPopulationFitness() {
		return this.populationFitness;
	}

	/**
	 * 
	 * @return size
	 */
	public int size() {
		return this.population.length;
	}

	/**
	 * 
	 * @param individual
	 * @param offset
	 * @return individual
	 */
	public Individual setIndividual(int offset, Individual individual) {
		return population[offset] = individual;
	}

	/**
	 * 
	 * @param offset
	 * @return individual
	 */
	public Individual getIndividual(int offset) {
		return population[offset];
	}

	/**
	 * 洗牌population中的个体
	 * 
	 * @param void
	 * @return void
	 */
	public void shuffle() {
		Random rnd = new Random();
		for (int i = population.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Individual a = population[index];
			population[index] = population[i];
			population[i] = a;
		}
	}
}