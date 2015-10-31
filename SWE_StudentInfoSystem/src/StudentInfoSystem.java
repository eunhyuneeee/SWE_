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
	static String _database = "student_info";

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
	String select2;

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

		StudentInfoDB db = new StudentInfoDB();
		db.dataBase();
		
		InputSetting is = new InputSetting();
		Component c = (Component) e.getSource();
		
		if(c == add){
			display.setText("");
			is.setEditable(ADD);
			select="add";
		}
			
		else if (c == update) {	
			display.setText("");
			is.setEnable(UPDATE);
			select="update";
		}

		else if (c == delete) {	
			display.setText("");
			is.setEnable(DELETE);	
			select="delete";
		}
		
		else if (c == view) {		
			display.setText("");
			is.setEnable(VIEW);
			select="view";
		}
			
				
		else if(c == okay) {
			
			if(select=="add"){
				db.add();
				clear();
			}
			else if(select=="update"){
				db.update();
				clear();
			}
			else if(select=="delete"){
				db.delete();
				clear();
			}
			else if(select=="view"){
				db.view();
				clear();
			}
		}
	}
	

	public void clear() {
		input_id.setText("");
		input_name.setText("");
		input_depart.setText("");
		input_pnum.setText("");
	}

	public static Connection makeConnection() {
		String url = "jdbc:mysql://" + _host + ":" + _port + "/" + _database;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, _user, _password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}