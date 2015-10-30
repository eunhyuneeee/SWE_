import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StudentInfoSystem extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int NONE = 0;
	public static final int ADD = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	public static final int VIEW = 4;
	
	public JTextArea display;
	public JTextField jt_id, jt_name, jt_depart, jt_pnum;
	public JButton add, delete, update, view; 
	public Connection connect;
	public Statement state;
	public String std_id, std_name, std_depart, std_pnum;
	
	public StudentInfoSystem(){
	
		super("Student Information Book");		
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DBconnect();
	}
	
	private void DBconnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/student_info";
			connect = DriverManager.getConnection(url,"root","0070");
			state = connect.createStatement();
		}catch(Exception e){
			e.printStackTrace();	
		}			
	}
	private void init(){
		
		JPanel top = new JPanel();
		top.add(new JLabel("<STUDENT INFORMATION BOOK>"));
		getContentPane().add("North", top);
		
		display = new JTextArea();
		display.setEditable(true);
		getContentPane().add("Center", new JScrollPane(display));
		
		JPanel left = new JPanel(new GridLayout(7,2));
		left.setPreferredSize(new Dimension(200,400));
		left.add(new JLabel("   학      번"));
		left.add(jt_id=new JTextField(30));
		left.add(new JLabel("   이      름"));
		left.add(jt_name=new JTextField(30));
		left.add(new JLabel("   학      과"));
		left.add(jt_depart=new JTextField(30));
		left.add(new JLabel("   핸드폰번호"));
		left.add(jt_pnum=new JTextField(30));
		getContentPane().add("West",left);		
		
		makeButton();
	}
	
	
	private void makeButton(){
		JPanel button = new JPanel();
		button.add(add=new JButton("ADD"));
		add.addActionListener(this);
		
		button.add(delete=new JButton("DELETE"));
		delete.addActionListener(this);
		
		button.add(update=new JButton("UPDATE"));
		update.addActionListener(this);
	
		button.add(view=new JButton("VIEW"));
		view.addActionListener(this);
		
		getContentPane().add("South", button);
	}
	
	public void clear(){
		jt_id.setText("");
		jt_name.setText("");
		jt_depart.setText("");
		jt_pnum.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		Component c = (Component)e.getSource();
		
		String s_id = jt_id.getText().trim();
		String s_name = jt_name.getText().trim();
		String s_depart = jt_depart.getText().trim();
		String s_pnum = jt_pnum.getText().trim();
		
		boolean result;
		
		StudentInfoDB db = new StudentInfoDB(s_id, s_name, s_depart, s_pnum);
		
		if(c == add){
			
			result = db.info_add();
						
			if(result){
				clear();
				display.append(" =============================================================" +"\n");
				display.append(" \t     학생 정보가 추가되었습니다.\n ");
				display.append(" ============================================================="+"\n");	
				}	
		}
		}
	
	public static void main(String[] args){
		StudentInfoSystem s = new StudentInfoSystem();
		s.setSize(600,400);
		s.setVisible(true);
	}
}
