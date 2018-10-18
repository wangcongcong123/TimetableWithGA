package TImetableGA;

import java.util.HashMap;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * 程序入口类
 * 
 * @author
 *
 */
public class Main {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// 创建一个课程表
		Timetable timetable = createTimetable();
		// Initialize GA-初始化遗传算法
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
		// 初始化 population-这里timetable为遗传算法的population
		Population population = ga.initPopulation(timetable);
		// 评估 population
		ga.evalPopulation(population, timetable);
		int generation = 1;

		// 开始进化并输出适配相关数据，包括fitness 等
		while (ga.isTerminationConditionMet(generation, 1000) == false && ga.isTerminationConditionMet(population) == false) {
			Individual[] individuals = population.getIndividuals();
			System.out.println("***************Generation" + generation + "***************");
			int i = 1;
			for (Individual individual : individuals) {
				System.out.println("Chromosome no." + i + ": " + individual.getFitness());
				System.out.println(individual.toString() + "\n");
				i++;
			}
			timetable.createClasses(population.getFittest(0));
			System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
			System.out.println("Clashes: " + timetable.calcClashes());
			population = ga.crossoverPopulation(population);
			population = ga.mutatePopulation(population, timetable);
			ga.evalPopulation(population, timetable);
			generation++;
		}
		System.out.println("******************************Generation" + generation + "******************************");
		int i = 1;
		Individual[] individuals = population.getIndividuals();
		for (Individual individual : individuals) {
			System.out.println("Chromosome no." + i + ": " + individual.getFitness());
			System.out.println(individual.toString() + "\n");
			i++;
		}
		// 打印最后结果，包括进化代数与最终适配值final fitness，冲突次数clashes
		timetable.createClasses(population.getFittest(0));
		System.out.println();
		System.out.println("Solution found in " + generation + " generations");
		System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
		System.out.println("Clashes: " + timetable.calcClashes());
		// 输出课程表
		System.out.println();
		Class classes[] = timetable.getClasses();
		int classIndex = 1;

		Group[] groups = timetable.getGroupsAsArray();
		HashMap<Integer, TimetableWin> winds = new HashMap<Integer, TimetableWin>();
		for (Group group : groups) {
			TimetableWin timetableWin = new TimetableWin();
			timetableWin.setTitle(timetableWin.getTitle() + " Group-" + group.getGroupId());
			winds.put(group.getGroupId(), timetableWin);
		}
		for (Class bestClass : classes) {
			System.out.println("Class " + classIndex + ":");
			System.out.println("Course: " + timetable.getModule(bestClass.getModuleId()).getcourseName());
			int groupId = timetable.getGroup(bestClass.getGroupId()).getGroupId();
			System.out.println("Group: " + groupId);
			System.out.println("Room: " + timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
			System.out.println("Lecturer: " + timetable.getlecturer(bestClass.getlecturerId()).getLecturerName());
			System.out.println("Time: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
			winds.get(groupId).fillClass(bestClass, timetable);
			System.out.println("-----");
			classIndex++;
		}
	}

	/**
	 * 这个方法用来创建课程表
	 * 
	 * 这里为了方便，采用简单的hardcode方式提供初始化数据，包括 1.输入教师信息，包括：一共n（最多10）个老师T1、T2、T3、…Tn， 分别教课程 L1、L2、L3、…Ln,每个老师需要输入每天可以教学的时间（比如9am-11am）
	 * 2.输入课程信息，包括：一共m（最多10）们课程L1、L2、L3、…Ln，每个课程要输入报名的人数最多250人。每个课程需要输入每天上课需要的时间段（比如时间段 是一个小时，需要两个时间段就是上两个小时课），输入每一周需要上同每个课程的次数。（比如java输入5，每周就排5次java课）
	 * 3.输入教室信息，包括：一共x（最多25）个教室C1、C2、C3、…Cn，每个教室需要输入最大人数限制，最多坐250人。 4.输入每天的上课时间，分成z（比如6）个时段，分别输入开始时间和结束时间，在最中间插入y（比如2）个时段的休息时间。每周上课五天。 5.输入专业名称，学期数，课程名称，教师名称，教室标号。
	 * 
	 * @return
	 */
	private static Timetable createTimetable() {
		// Create timetable
		Timetable timetable = new Timetable();
		// Set up rooms
		timetable.addRoom(1, "TB1-302", 115);
		timetable.addRoom(2, "TB1-301", 130);
		timetable.addRoom(4, "TB2-102", 120);
		timetable.addRoom(5, "TB3-402", 125);
		// Set up timeslots
		// 下面参数为输出图形界面的默认配置，如果修改，可以参考命令行的输出
		String[] days = { "Mon", "Tue", "Wed", "Thu", "Fri" };
		for (int i = 0; i < days.length; i++) {
			timetable.addTimeslot(1 + i * 6, days[i] + " 9:00 - 10:00");
			timetable.addTimeslot(2 + i * 6, days[i] + " 10:00 - 11:00");
			timetable.addTimeslot(3 + i * 6, days[i] + " 11:00 - 12:00");
			// 中午休息时间这里设置为12:00-13:00 -》当然这里可以根据需求调整
			timetable.addTimeslot(4 + i * 6, days[i] + " 13:00 - 14:00");
			timetable.addTimeslot(5 + i * 6, days[i] + " 14:00 - 15:00");
			timetable.addTimeslot(6 + i * 6, days[i] + " 15:00 - 16:00");
		}
		// Set up timeslots
		// the following parameters are tailorable, such as insert a break time between the second and third classes every day or add more classes or subdivide them
		// timetable.addTimeslot(1, "Mon 9:00 - 11:00");
		// timetable.addTimeslot(2, "Mon 11:00 - 13:00");
		// timetable.addTimeslot(3, "Mon 13:00 - 15:00");
		// timetable.addTimeslot(4, "Tue 9:00 - 11:00");
		// timetable.addTimeslot(5, "Tue 11:00 - 13:00");
		// timetable.addTimeslot(6, "Tue 13:00 - 15:00");
		// timetable.addTimeslot(7, "Wed 9:00 - 11:00");
		// timetable.addTimeslot(8, "Wed 11:00 - 13:00");
		// timetable.addTimeslot(9, "Wed 13:00 - 15:00");
		// timetable.addTimeslot(10, "Thu 9:00 - 11:00");
		// timetable.addTimeslot(11, "Thu 11:00 - 13:00");
		// timetable.addTimeslot(12, "Thu 13:00 - 15:00");
		// timetable.addTimeslot(13, "Fri 9:00 - 11:00");
		// timetable.addTimeslot(14, "Fri 11:00 - 13:00");
		// timetable.addTimeslot(15, "Fri 13:00 - 15:00");
		// 下面参数可以任意调整
		// Set up lecturers
		timetable.addlecturer(1, "Dr. David");
		timetable.addlecturer(2, "Dr. Sean");
		timetable.addlecturer(3, "Dr. Micheal");
		timetable.addlecturer(4, "Prof. Henry");
		// 下面参数可以任意调整
		// Set up modules and define the lecturers that teach them
		timetable.addModule(1, "comp30020", "OOD", new int[] { 1, 2 });
		timetable.addModule(2, "comp20010", "OOP", new int[] { 1, 3 });
		timetable.addModule(3, "comp30012", "Computer Networks", new int[] { 1, 2 });
		timetable.addModule(4, "comp30022", "Computer Systems", new int[] { 3, 4 });
		timetable.addModule(5, "comp40021", "Program Construction", new int[] { 4 });
		timetable.addModule(6, "comp40045", "Software Architecture", new int[] { 1, 4 });
		timetable.addModule(7, "EEE30045", "Digital Circuit", new int[] { 3, 4 });
		// 下面参数可以任意调整
		// Set up student groups and the modules they take.
		// the first argument GroupID stands for different grousp of students, for example, IOT students take different courses from SE students
		// semester1
		timetable.addGroup(1, 101, new int[] { 1, 1, 3, 3, 4, 6, 6, 6, 3 });
		// semester 2
		timetable.addGroup(2, 125, new int[] { 1, 2, 3, 5, 5, 2, 7, 7, 7, 2 });
		// timetable.addGroup(3, 118, new int[] { 3, 6, 5 });
		// timetable.addGroup(4, 25, new int[] { 1, 4 });
		// timetable.addGroup(5, 20, new int[] { 2, 3, 5 });
		// timetable.addGroup(6, 22, new int[] { 1, 4, 5 });
		// timetable.addGroup(7, 16, new int[] { 1, 3 });
		// timetable.addGroup(8, 18, new int[] { 2, 6 });
		// timetable.addGroup(9, 24, new int[] { 1, 6 });
		// timetable.addGroup(10, 25, new int[] { 3, 4 });
		return timetable;
	}
}
