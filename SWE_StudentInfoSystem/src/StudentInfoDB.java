import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class StudentInfoDB {

	Connection con = null; 
	java.sql.Statement st = null;
	ResultSet rs = null;
	int r = 0;
	String check_sql, result_sql = null;

	public void dataBase() {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://" + StudentInfoSystem._host + ":" + StudentInfoSystem._port + "/"
				+ StudentInfoSystem._database;
		String username = StudentInfoSystem._user;
		String password = StudentInfoSystem._password;

		try {
			Class.forName(jdbcDriver).newInstance();
			con = (Connection) DriverManager.getConnection(dbUrl, username, password);
			st = con.createStatement();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void add(String add_id, String name, String depart, String pnum) {

		try {
			check_sql = "select Id from student_info where Id LIKE '" + add_id + "' ";
			rs = st.executeQuery(check_sql);

			if (rs.next()) {
				StudentInfoSystem.display
						.append("=============================================================" + "\n");
				StudentInfoSystem.display.append(" \t   중복된 학번입니다. 다시 입력하세요.\n ");
				StudentInfoSystem.display
						.append(" =============================================================" + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			result_sql = "insert into student_info values" + "('" + add_id + "','" + name + "','" + depart + "','" + pnum
					+ "')";
			st.executeUpdate(result_sql);
			StudentInfoSystem.display.append("=============================================================" + "\n");
			StudentInfoSystem.display.append(" \t     학생 정보가 추가되었습니다.\n ");
			StudentInfoSystem.display.append(" =============================================================" + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }	
	}
	
	public void delete(String del_id) {

		try {
			check_sql = "select Id from student_info where Id LIKE '" + del_id + "' ";
			rs = st.executeQuery(check_sql);

			if (rs.next()) {

				try {
					result_sql = "delete from student_info where Id= '" + del_id + "'";
					st.executeUpdate(result_sql);
					StudentInfoSystem.display
							.append("=============================================================" + "\n");
					StudentInfoSystem.display.append(" \t     학생 정보가 삭제되었습니다.\n ");
					StudentInfoSystem.display
							.append(" =============================================================" + "\n");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				StudentInfoSystem.display
						.append("=============================================================" + "\n");
				StudentInfoSystem.display.append(" \t     삭제실패. 일치하는 학생 정보가 없습니다.\n ");
				StudentInfoSystem.display
						.append(" =============================================================" + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String update_id, String new_pnum) {

		check_sql = "select Id from student_info where Id = '" + update_id + "' ";
		try {
			rs = st.executeQuery(check_sql);

			if (rs.next()) {

				result_sql = "update student_info set Phone_num = '" + new_pnum + "' where Id= '" + update_id + "'";

				st.executeUpdate(result_sql);

				StudentInfoSystem.display
						.append("=============================================================" + "\n");
				StudentInfoSystem.display.append(" \t     학생 정보가 변경되었습니다.\n ");
				StudentInfoSystem.display
						.append(" =============================================================" + "\n");
			} else {
				StudentInfoSystem.display
						.append("=============================================================" + "\n");
				StudentInfoSystem.display.append(" \t   학생정보록에 해당 학번이 없습니다.\n ");
				StudentInfoSystem.display
						.append(" =============================================================" + "\n");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void view(String search_id){	
			try {
				result_sql = " select * from student_info WHERE Id LIKE '"+search_id+"' ";
				rs=st.executeQuery(result_sql);
				
				if(rs.next()) {
					rs=st.getResultSet();
					
					rs.beforeFirst();
					StudentInfoSystem.display.append("====================================================================" +"\n");
					StudentInfoSystem.display.append("    Id \t Name \t  Department \t\t Phonenumber  \n");
					StudentInfoSystem.display.append("===================================================================="+"\n");
					
					while(rs.next()) {						
						
						String s_id = rs.getString(1);
						String s_name = rs.getString(2);
						String s_depart = rs.getString(3);
						String s_pnum = rs.getString(4);
						
						StudentInfoSystem.display.append(s_id+ "\t" +s_name+ "\t" +s_depart+  "\t\t" +s_pnum+"\n");
						System.out.println(s_id+ "\t" +s_name+ "\t" +s_depart+  "\t\t" +s_pnum+"\n");
					}
				} 
				else
					StudentInfoSystem.display.append("해당학번이 없습니다. 다시 입력하세요.");
					
			} catch(Exception e) {
				System.out.println("Data가 없습니다.");
				return;
	
			}
	}
}