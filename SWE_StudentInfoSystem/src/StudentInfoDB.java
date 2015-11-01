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

	public boolean add(String add_id, String name, String depart, String pnum) {

		try {
			check_sql = "select Id from student_info where Id LIKE '" + add_id + "' ";
			rs = st.executeQuery(check_sql);

			if (rs.next()) return false;
			else {
				result_sql = "insert into student_info values" + "('" + add_id + "','" + name + "','" + depart + "','"
						+ pnum + "')";
				st.executeUpdate(result_sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return true;
	}

	public boolean update(String update_id, String new_pnum) {

		try {
			check_sql = "select Id from student_info where Id = '" + update_id + "' ";
			rs = st.executeQuery(check_sql);

			if (rs.next()) {
				result_sql = "update student_info set Phone_num = '" + new_pnum + "' where Id= '" + update_id + "'";
				st.executeUpdate(result_sql);
			} else  return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(String del_id) {

		try {
			check_sql = "select Id from student_info where Id LIKE '" + del_id + "' ";
			rs = st.executeQuery(check_sql);

			if (rs.next()) {

				result_sql = "delete from student_info where Id= '" + del_id + "'";
				st.executeUpdate(result_sql);

			} else  return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean view(String search_id, String caseValue) {

		try {
			if (caseValue == "search")
				result_sql = " select * from student_info WHERE Id LIKE '" + search_id + "' ";
			else if (caseValue == "ALL")
				result_sql = "select * from student_info ";

			rs = st.executeQuery(result_sql);

			if (rs.next()) {
				rs = st.getResultSet();

				rs.beforeFirst();

				StudentInfoSystem.display.setText("");
				StudentInfoSystem.display
						.append("  ============================================================" + "\n");
				StudentInfoSystem.display.append("       학번 \t이름 \t  학과 \t       핸드폰번호  \n");
				StudentInfoSystem.display
						.append("  ============================================================" + "\n");

				while (rs.next()) {

					String viewId = rs.getString(1);
					String viewName = rs.getString(2);
					String viewDepart = rs.getString(3);
					String viewPnum = rs.getString(4);

					StudentInfoSystem.display
							.append("  "+viewId + "\t" + viewName + "\t"+ viewDepart + "\t      " + viewPnum + "\n");
				}
			} else return false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
