package TImetableGA;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;

public class TimetableWin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel("����Main�����޸����ò������޸ĺ��������������µĿγ̰��Ž������ʾ");
	JTable lecTable = null;
	JButton export = new JButton("����");
	String[][] cellData = { { "9:00------10:00", "", "", "", "", "", "", "" }, { "10:00------11:00", "", "", "", "", "", "", "" },
			{ "11:00------12:00", "", "", "", "", "", "", "" }, { "12:00-13:00(break)", "", "", "", "", "", "", "" },
			{ "13:00------14:00", "", "", "", " ", "", "", "" }, { "14:00------15:00", "", "", "", " ", "", "", "" },
			{ "15:00------16:00", "", "", "", " ", "", "", "" } };
	String[] colums = { "Times", "Mon", "Tue", "Wed", "Thu", "Fri" };

	public JPanel southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(label);
		southPanel.add(export);
		export.addActionListener(this);
		return southPanel;
	}

	public TimetableWin() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		UIManager.setLookAndFeel(lookAndFeel);
		lecTable = new JTable(cellData, colums);
		lecTable.setDragEnabled(false);
		lecTable.setEnabled(false);
		//
		TableColumn firsetColumn = lecTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(180);
		firsetColumn.setMaxWidth(180);
		firsetColumn.setMinWidth(180);

		for (int i = 1; i < colums.length; i++) {
			TableColumn Column = lecTable.getColumnModel().getColumn(i);
			Column.setPreferredWidth(150);
			Column.setMaxWidth(150);
			Column.setMinWidth(150);
		}
		lecTable.setRowHeight(60);
		lecTable.setPreferredScrollableViewportSize(new Dimension(1020, 250));
		JScrollPane scrollPane = new JScrollPane(lecTable);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(southPanel(), BorderLayout.SOUTH);
		this.setTitle("�����Ŵ��㷨�Զ����ſγ�");
		this.setVisible(true);
		this.setSize(920, 500);
		GUIUtil.toCenter(this);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// initializeCS();
	}
	// private void initializeCS() {
	// TableModel tableModel = lecTable.getModel();
	// }

	// public static void main(String[] args)
	// throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	// new TimetableWin();
	// }

	public void fillClass(Class bestClass, Timetable timetable) {
		String timeslot = timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot();
		int group = timetable.getGroup(bestClass.getGroupId()).getGroupId();
		String course = timetable.getModule(bestClass.getModuleId()).getcourseName();
		String lecturer = timetable.getlecturer(bestClass.getlecturerId()).getLecturerName();
		String room = timetable.getRoom(bestClass.getRoomId()).getRoomNumber();
		String info = "<html>Group:" + group + "<br>" + course + "<br>Lecturer:" + lecturer + "<br>Room:" + room + "</html>";
		int row = getRowNum(timeslot);
		if (timeslot.startsWith("Mon")) {
			lecTable.setValueAt(info, row, 1);
		} else if (timeslot.startsWith("Tue")) {
			lecTable.setValueAt(info, row, 2);
		} else if (timeslot.startsWith("Wed")) {
			lecTable.setValueAt(info, row, 3);
		} else if (timeslot.startsWith("Thu")) {
			lecTable.setValueAt(info, row, 4);
		} else if (timeslot.startsWith("Fri")) {
			lecTable.setValueAt(info, row, 5);
		}
	}

	private int getRowNum(String timeslot) {
		int row = -1;
		String trimmedTimeslot = timeslot.split(" ")[1];
		if (trimmedTimeslot.startsWith("9:00")) {
			row = 0;
		} else if (trimmedTimeslot.startsWith("10:00")) {
			row = 1;
		} else if (trimmedTimeslot.startsWith("11:00")) {
			row = 2;
		} else if (trimmedTimeslot.startsWith("13:00")) {
			row = 4;
		} else if (trimmedTimeslot.startsWith("14:00")) {
			row = 5;
		} else if (trimmedTimeslot.startsWith("15:00")) {
			row = 6;
		}
		return row;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == export) {
			File file = new File(this.getTitle().split(" ")[1]);
			String fi = file.getAbsolutePath() + ".csv";
			System.out.println(file);
			FileWriter out = null;
			try {
				out = new FileWriter(fi);
				for (int i = 0; i < lecTable.getColumnCount(); i++) {
					out.write(lecTable.getColumnName(i) + ",");
				}
				out.write("\n");
				for (int i = 0; i < lecTable.getRowCount(); i++) {
					for (int j = 0; j < lecTable.getColumnCount(); j++) {
						out.write(delHTMLTag(lecTable.getValueAt(i, j).toString()) + ",");
					}
					out.write("\n");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "�ļ������ɹ��ڸ�Ŀ¼��");
		}
	}

	public String delHTMLTag(String htmlStr) {
		// htmlStr = htmlStr.replaceFirst("<html>", "\"");
		// htmlStr = htmlStr.replaceFirst("<\\html>", "\"");
		// htmlStr = htmlStr.replaceFirst("<br>", "\n");
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ
		String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(" "); // ����script��ǩ

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(" "); // ����style��ǩ

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(" "); // ����html��ǩ

		return htmlStr.trim(); // �����ı��ַ���
	}
}
