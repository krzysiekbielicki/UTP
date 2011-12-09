package pl.edu.pjwstk.s8267.utp.lab9;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Zadanie1 extends JFrame implements ActionListener, ListSelectionListener, PropertyChangeListener {

	int k = 0;
	int n = 15;
	private DefaultListModel<TaskData> dataModel;
	private JButton stop;
	private JButton result;
	private JButton start;
	private JTextArea ta;

	Zadanie1() {
		dataModel = new DefaultListModel<TaskData>();
		JList<TaskData> list = new JList<TaskData>(dataModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setCellRenderer(new ListCellRenderer<TaskData>() {

			@Override
			public Component getListCellRendererComponent(
					JList<? extends TaskData> list, TaskData value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JLabel l = new JLabel(value.name);
				if (isSelected) {
					l.setOpaque(true);
		            l.setBackground(Color.LIGHT_GRAY);
				}
				return l;
			}
		});
		add(new JScrollPane(list), "North");
		ta = new JTextArea(20, 50);
		add(new JScrollPane(ta), "Center");
		JPanel p = new JPanel();
		start= new JButton("Start");
		start.addActionListener(this);
		p.add(start);
		stop = new JButton("Stop selected");
		stop.setEnabled(false);
		stop.setActionCommand("Stop");
		stop.addActionListener(this);
		p.add(stop);
		result = new JButton("Selected result");
		result.setEnabled(false);
		result.setActionCommand("Result");
		result.addActionListener(this);
		p.add(result);
		add(p, "South");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			Method m = this.getClass().getDeclaredMethod("task" + cmd);
			m.invoke(this);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	class SumTask implements Callable<Integer> {

		private int taskNum, limit;

		public SumTask(int taskNum, int limit) {
			this.taskNum = taskNum;
			this.limit = limit;
			dataModel.addElement(new TaskData("Task "+taskNum));
		}

		public Integer call() throws Exception {
			int sum = 0;
			for (int i = 1; i <= limit; i++) {
				if (Thread.currentThread().isInterrupted()) {
					dataModel.get(taskNum-1).appendData("execution interrupted\n");
					return null;
				}
				sum += i;
				dataModel.get(taskNum-1).appendData("part result = " + sum + '\n');
				Thread.sleep(1000);
			}
			dataModel.get(taskNum-1).appendData("finished execution\n");
			return sum;
		}
	};

	Vector<Future<Integer>> task = new Vector<Future<Integer>>();

	// ExecutorService exec = Executors.newSingleThreadExecutor();
	ExecutorService exec = Executors.newFixedThreadPool(3);
	private int selectedIndex;

	public void taskStart() {
		try {
			task.addElement(exec.submit(new SumTask(++k, 15)));
		} catch (RejectedExecutionException exc) {
			//ta.append("Execution rejected\n");
			return;
		}
	}

	public void taskResult() {
		String msg = "";
		if(selectedIndex < 0)
			return;
		if (task.get(selectedIndex).isCancelled())
			msg = "Task cancelled.";
		else if (task.get(selectedIndex).isDone()) {
			try {
				msg = "Ready. Result = " + task.get(selectedIndex).get();
			} catch (Exception exc) {
				msg = exc.getMessage();
			}
		} else
			msg = "Task is running or waiting for execution";
		JOptionPane.showMessageDialog(null, msg);
	}

	public void taskStop() {
		if(selectedIndex < 0)
			return;
		task.get(selectedIndex).cancel(true);
		stop.setEnabled(false);
	}

	public static void main(String[] args) {
		new Zadanie1();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(selectedIndex >= 0)
			dataModel.get(selectedIndex).setPropertyChangeListener(null);
		selectedIndex = ((JList<String>)e.getSource()).getSelectedIndex();
		stop.setEnabled(selectedIndex >= 0 && !task.get(selectedIndex).isCancelled() && !task.get(selectedIndex).isDone());
		result.setEnabled(selectedIndex >= 0);
		if(selectedIndex >= 0) {
			ta.setText(dataModel.get(selectedIndex).data);
			dataModel.get(selectedIndex).setPropertyChangeListener(this);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		ta.setText((String) evt.getNewValue());
	}

}