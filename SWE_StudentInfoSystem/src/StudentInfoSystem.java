import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInfoSystem extends JFrame implements ActionListener {
	static String _host = "localhost";
	static String _port = "3306";
	static String _user = "root";
	static String _password = "0070";
	static String _database = "smu";

	static JTextArea display;
	static JTextField input_id, input_name, input_depart, input_pnum;
	static JButton add, delete, update, view, okay;
	
	public final int NONE = 0;
	public final int ADD = 1;
	public final int UPDATE = 2;
	public final int DELETE = 3;
	public final int VIEW = 4;

	ResultSet rs = null;
	String select;

	public static void main(String[] args) {
		StudentInfoSystem sis = new StudentInfoSystem();
		sis.MainMenu();
		sis.setSize(700, 400);
		sis.setVisible(true);
	}

	private void MainMenu() {
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init() {

		JPanel top = new JPanel();
		top.add(new JLabel("<STUDENT INFORMATION BOOK>"));
		getContentPane().add("North", top);
		makeButton();
		
		display = new JTextArea();
		display.setEditable(true);
		getContentPane().add("Center", new JScrollPane(display));
		
		JPanel left = new JPanel(new GridLayout(8, 2));
		left.add(new JLabel("   학      번"));
		left.add(input_id = new JTextField(30));
		left.add(new JLabel("   이      름"));
		left.add(input_name = new JTextField(30));
		left.add(new JLabel("   학      과"));
		left.add(input_depart = new JTextField(30));
		left.add(new JLabel("   핸드폰번호"));
		left.add(input_pnum = new JTextField(30));
		left.setPreferredSize(new Dimension(150, 400));
		getContentPane().add("West", left);
		
		setEditable(NONE);
		makeButton();
	}

	private void makeButton() {

		JPanel right = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
		right.add(add = new JButton("추         가"));
		add.addActionListener(this);
		right.add(update = new JButton("업데이트"));
		update.addActionListener(this);
		right.add(delete = new JButton("삭         제"));
		delete.addActionListener(this);
		right.add(view = new JButton("검         색"));
		view.addActionListener(this);
		right.setPreferredSize(new Dimension(100, 400));
		getContentPane().add("East", right);
		
		JPanel bottom = new JPanel();
		bottom.add(okay = new JButton("확     인"));
		okay.addActionListener(this);
		getContentPane().add("South", bottom);	
		
	}

	public void actionPerformed(ActionEvent e) {
		String id = input_id.getText().trim();
		String name = input_name.getText().trim();
		String depart = input_depart.getText().trim();
		String pnum = input_pnum.getText().trim();
		
		StudentInfoDB db = new StudentInfoDB();
		db.dataBase();
	
		Component c = (Component) e.getSource();
		
		if(c == add){
			setEditable(ADD);
			select="add";
			clear();
		}	
		else if (c == update) {	
			setEditable(UPDATE);
			select="update";
			clear();
		}
		else if (c == delete) {	
			setEditable(DELETE);	
			select="delete";
			clear();
		}	
		else if (c == view) {		
			setEditable(VIEW);
			select="view";
			clear();
		}
		
		else if(c == okay) {
			boolean result;
			
			switch(select) {
			
			case "add":
				result = db.add(id, name, depart, pnum);
				if(result){
					JOptionPane.showMessageDialog(null, "학생 정보가 추가되었습니다.", "성 공", JOptionPane.PLAIN_MESSAGE);
					db.view(id, "ALL");
				}else
					JOptionPane.showMessageDialog(null,"중복된 학번입니다. 다시 입력하세요.","오 류",JOptionPane.WARNING_MESSAGE);		
				break;
				
			case "update":
				result = db.update(id, pnum);
				if(result){
					JOptionPane.showMessageDialog(null, "학생 정보가 변경되었습니다.", "성 공", JOptionPane.PLAIN_MESSAGE);
					db.view(id, "ALL");			
				}else
					JOptionPane.showMessageDialog(null, "학생정보록에 해당 학번이 없습니다.", "오 류 ", JOptionPane.WARNING_MESSAGE);
				break;
				
			case "delete" :
				result = db.delete(id);
				if(result){
					JOptionPane.showMessageDialog(null, "학생 정보가 삭제되었습니다.", "성 공", JOptionPane.PLAIN_MESSAGE);
					db.view(id, "ALL");	
				}else
					JOptionPane.showMessageDialog(null, "삭제실패. 일치하는 학생 정보가 없습니다.", "오 류", JOptionPane.WARNING_MESSAGE);
				break;
				
			case "view" :
				result = db.view(id,"search");
				if(result){
					JOptionPane.showMessageDialog(null, "학생 정보가 검색되었습니다.", "성 공 ", JOptionPane.PLAIN_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "해당 학번이 없습니다. 다시 입력하세요.", "VIEW", JOptionPane.WARNING_MESSAGE);
				break;	
			}
		}	
	}
	
	public void setEditable(int n) {
		StudentInfoSystem.input_id.setEditable(false);
		StudentInfoSystem.input_name.setEditable(false);
		StudentInfoSystem.input_depart.setEditable(false);
		StudentInfoSystem.input_pnum.setEditable(false);

		switch (n) {

		case ADD:
			StudentInfoSystem.input_id.setEditable(true);
			StudentInfoSystem.input_name.setEditable(true);
			StudentInfoSystem.input_depart.setEditable(true);
			StudentInfoSystem.input_pnum.setEditable(true);
			break;

		case UPDATE:
			StudentInfoSystem.input_id.setEditable(true);
			StudentInfoSystem.input_pnum.setEditable(true);
			break;

		case DELETE:
			StudentInfoSystem.input_id.setEditable(true);
			break;

		case VIEW:
			StudentInfoSystem.input_id.setEditable(true);
			break;
		}
	}

	public void clear() {
		input_id.setText("");
		input_name.setText("");
		input_depart.setText("");
		input_pnum.setText("");
	}
}