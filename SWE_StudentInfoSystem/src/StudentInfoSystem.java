import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StudentInfoSystem extends JFrame implements ActionListener{
	static String _host = "localhost";
	static String _port = "3306";
	static String _user = "root";
	static String _password = "0070";
	static String _database = "student_info";
	
	static JTextArea display;
	static JTextField input_id, input_name, input_depart, input_pnum;
	static JButton add, delete, update, view; 
	
    ResultSet rs = null;
    
     public static void main(String[] args) {
    	 StudentInfoSystem sis = new  StudentInfoSystem();
 		 sis.MainMenu();       
 		 sis.setSize(700,400);
		 sis.setVisible(true);
     }
     
     private void MainMenu() {
    	
 		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
 		left.add(input_id=new JTextField(30));
 		left.add(new JLabel("   이      름"));
 		left.add(input_name=new JTextField(30));
 		left.add(new JLabel("   학      과"));
 		left.add(input_depart=new JTextField(30));
 		left.add(new JLabel("   핸드폰번호"));
 		left.add(input_pnum=new JTextField(30));
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
 	
 	public void actionPerformed(ActionEvent e) {
 		
 		StudentInfoDB db = new StudentInfoDB();
		db.dataBase();
		Component c = (Component)e.getSource();
		
		if(c==add){
			db.add();
			clear();
		}
	}
 	
 	public void clear(){
		input_id.setText("");
		input_name.setText("");
		input_depart.setText("");
		input_pnum.setText("");
	}
     
	public static Connection makeConnection(){
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