package TImetableGA;

import java.util.HashMap;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * ���������
 * 
 * @author
 *
 */
public class Main {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// ����һ���γ̱�
		Timetable timetable = createTimetable();
		// Initialize GA-��ʼ���Ŵ��㷨
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
		// ��ʼ�� population-����timetableΪ�Ŵ��㷨��population
		Population population = ga.initPopulation(timetable);
		// ���� population
		ga.evalPopulation(population, timetable);
		int generation = 1;

		// ��ʼ�������������������ݣ�����fitness ��
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
		// ��ӡ�����������������������������ֵfinal fitness����ͻ����clashes
		timetable.createClasses(population.getFittest(0));
		System.out.println();
		System.out.println("Solution found in " + generation + " generations");
		System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
		System.out.println("Clashes: " + timetable.calcClashes());
		// ����γ̱�
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
	 * ����������������γ̱�
	 * 
	 * ����Ϊ�˷��㣬���ü򵥵�hardcode��ʽ�ṩ��ʼ�����ݣ����� 1.�����ʦ��Ϣ��������һ��n�����10������ʦT1��T2��T3����Tn�� �ֱ�̿γ� L1��L2��L3����Ln,ÿ����ʦ��Ҫ����ÿ����Խ�ѧ��ʱ�䣨����9am-11am��
	 * 2.����γ���Ϣ��������һ��m�����10���ǿγ�L1��L2��L3����Ln��ÿ���γ�Ҫ���뱨�����������250�ˡ�ÿ���γ���Ҫ����ÿ���Ͽ���Ҫ��ʱ��Σ�����ʱ��� ��һ��Сʱ����Ҫ����ʱ��ξ���������Сʱ�Σ�������ÿһ����Ҫ��ͬÿ���γ̵Ĵ�����������java����5��ÿ�ܾ���5��java�Σ�
	 * 3.���������Ϣ��������һ��x�����25��������C1��C2��C3����Cn��ÿ��������Ҫ��������������ƣ������250�ˡ� 4.����ÿ����Ͽ�ʱ�䣬�ֳ�z������6����ʱ�Σ��ֱ����뿪ʼʱ��ͽ���ʱ�䣬�����м����y������2����ʱ�ε���Ϣʱ�䡣ÿ���Ͽ����졣 5.����רҵ���ƣ�ѧ�������γ����ƣ���ʦ���ƣ����ұ�š�
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
		// �������Ϊ���ͼ�ν����Ĭ�����ã�����޸ģ����Բο������е����
		String[] days = { "Mon", "Tue", "Wed", "Thu", "Fri" };
		for (int i = 0; i < days.length; i++) {
			timetable.addTimeslot(1 + i * 6, days[i] + " 9:00 - 10:00");
			timetable.addTimeslot(2 + i * 6, days[i] + " 10:00 - 11:00");
			timetable.addTimeslot(3 + i * 6, days[i] + " 11:00 - 12:00");
			// ������Ϣʱ����������Ϊ12:00-13:00 -����Ȼ������Ը����������
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
		// ������������������
		// Set up lecturers
		timetable.addlecturer(1, "Dr. David");
		timetable.addlecturer(2, "Dr. Sean");
		timetable.addlecturer(3, "Dr. Micheal");
		timetable.addlecturer(4, "Prof. Henry");
		// ������������������
		// Set up modules and define the lecturers that teach them
		timetable.addModule(1, "comp30020", "OOD", new int[] { 1, 2 });
		timetable.addModule(2, "comp20010", "OOP", new int[] { 1, 3 });
		timetable.addModule(3, "comp30012", "Computer Networks", new int[] { 1, 2 });
		timetable.addModule(4, "comp30022", "Computer Systems", new int[] { 3, 4 });
		timetable.addModule(5, "comp40021", "Program Construction", new int[] { 4 });
		timetable.addModule(6, "comp40045", "Software Architecture", new int[] { 1, 4 });
		timetable.addModule(7, "EEE30045", "Digital Circuit", new int[] { 3, 4 });
		// ������������������
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
