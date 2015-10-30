import java.sql.*;

public class StudentInfoDB extends StudentInfoSystem{
	
	String db_id ;
	String db_name;
	String db_depart;
	String db_pnum ;
	
	
	public StudentInfoDB(String id, String name, String depart, String pnum){
		db_id = id;
		db_name = name;
		db_depart = depart;
		db_pnum = pnum;
	}
	
	// 학번이 동일한게 있거나 , 정보입력칸에 하나라도 입력하지 않는 경우 수행x 
	public boolean info_add(){
				
		try {
			state.executeUpdate("insert into student_info(Id, Name, Department, Phone_num) values('"+db_id+"','"+db_name+"','"+db_depart+"','"+db_pnum+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;	
	}

}
