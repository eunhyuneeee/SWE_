import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class StudentInfoDB {
	
	
	Connection con = null;
	java.sql.Statement st = null;
	ResultSet rs = null;
	int r = 0;
	
	public void dataBase() {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://" + StudentInfoSystem._host + ":" + StudentInfoSystem._port + "/" + StudentInfoSystem._database;
		String username = StudentInfoSystem._user;
		String password = StudentInfoSystem._password;
		
		try {
			Class.forName(jdbcDriver).newInstance();
			con = (Connection) DriverManager.getConnection(dbUrl,username,password);
			st = con.createStatement();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void add(){				
		
		String id = StudentInfoSystem.input_id.getText().trim();	
		try { 
			String str = "select Id from student_info where Id LIKE '"+id+"' ";
			rs = st.executeQuery(str);
			
			if(rs.next()) {
				StudentInfoSystem.display.append("중복된 학번입니다. 다시 입력하세요.");
			}		
		} catch (SQLException e) {
            e.printStackTrace();
        }	
		
		String name = StudentInfoSystem.input_name.getText().trim();
		String depart = StudentInfoSystem.input_depart.getText().trim();
		String pnum = StudentInfoSystem.input_pnum.getText().trim();
		
		try {       
            String sql2 = "insert into student_info values" + "('"+id+"','"+name+"','"+depart+"','"+pnum+"')";
            st.executeUpdate(sql2); 
            StudentInfoSystem.display.append("=============================================================" +"\n");
            StudentInfoSystem.display.append(" \t     학생 정보가 추가되었습니다.\n ");
            StudentInfoSystem.display.append(" ============================================================="+"\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }	
	}
	
	/*
	public void dataSearch(){
		System.out.print("책 제목을 입력해주세요: ");
		String searchtitle = scan.nextLine();
		
		try {
			String showData = " select * from book WHERE title LIKE '%"+searchtitle+"%' ";
			rs = st.executeQuery(showData);
			
			if(rs.next()) {
				rs=st.getResultSet();
				
				System.out.println("[search result]");
				rs.beforeFirst();
				
				System.out.println("____________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "publisher"));
				System.out.println("____________________________________________________________");				
				while(rs.next()) {					
					System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
	                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
	                         + String.format(" %20s ", rs.getString(3)));
				}
				System.out.println("____________________________________________________________");	
				} else { 
					System.out.print("검색 결과가 없습니다.If you want to go back to menu press1 => ");
					int rg = scan.nextInt();
					switch (rg) {
					case 1:
						mg.Manage();
						break;
					default:
						System.out.println("Insert is wrong.");
						break;
					}
					}
			
			System.out.println("");
			
			System.out.print("원하는책번호를입력하세요: ");
			String searchbn = scan.nextLine();
			
			try {
				String search = " select * from book WHERE book_number LIKE '"+searchbn+"' ";
				rs=st.executeQuery(search);
				
				if(rs.next()) {
					rs=st.getResultSet();
					
					System.out.println("[search result]");
					rs.beforeFirst();
					System.out.println("________________________________________________________________________________________________________________________");
					System.out.println(String.format("  %s  ", "book_number") + "|" 
					                 + String.format(" %20s ", "title") + "|"  
					                 + String.format(" %20s ", "writer") + "|" 
					                 + String.format(" %20s ", "publisher") + "|"
					                 + String.format(" %10s ", "borrow 가능") + "|"
					                 + String.format(" %20s ", "borrowed Id"));
					System.out.println("________________________________________________________________________________________________________________________");
					String can = "O";
					while(rs.next()) {						
						if(rs.getString(5)!=null) {
							can = "X";
						} 
						
						System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
		                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
		                         + String.format(" %20s ", rs.getString(3)) + "|"  
		                         + String.format(" %20s ", rs.getString(4)) + "|"
		                         + String.format(" %10s ", can) + "|"
		                         + String.format(" %20s ", rs.getString(5)));
						}
					System.out.println("________________________________________________________________________________________________________________________");
					} else { 
						System.out.print("잘못된 책 번호입니다. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
						int rg = scan.nextInt();
						switch (rg) {
						case 1:
							mg.Manage();
							break;
						default:
							System.out.println("Insert is wrong.");
							break;
						}
						}
				} catch(Exception e) {
					System.out.println("Data가 없습니다.");
					return;
				}
			} catch(Exception e) {
				System.out.println("Data가 없습니다.");
				return;
			}
	}
	
	public void dataDelete(){ 
		System.out.print("삭제할 책 제목을 입력하세요: ");
		String searchtitle = scan.nextLine();
		
		try {
			String showData = " select * from book WHERE title LIKE '%"+searchtitle+"%' ";
			rs = st.executeQuery(showData);
			
			if(rs.next()) {
				rs=st.getResultSet();
				
				System.out.println("[search result]");
				rs.beforeFirst();
				System.out.println("________________________________________________________________________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "writer") + "|" 
				                 + String.format(" %20s ", "publisher") + "|"
				                 + String.format(" %10s ", "borrow 가능") + "|"
				                 + String.format(" %20s ", "borrowed Id"));
				System.out.println("________________________________________________________________________________________________________________________");
				String can = "O";
				while(rs.next()) {						
					if(rs.getString(5)!=null) {
						can = "X";
					} 
					
					System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
	                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
	                         + String.format(" %20s ", rs.getString(3)) + "|"  
	                         + String.format(" %20s ", rs.getString(4)) + "|"
	                         + String.format(" %10s ", can) + "|"
	                         + String.format(" %20s ", rs.getString(5)));
					}
				System.out.println("________________________________________________________________________________________________________________________");
				} else { 
					System.out.print("검색 결과가 없습니다. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
					int rg = scan.nextInt();
					switch (rg) {
					case 1:
						mg.Manage();
						break;
					default:
						System.out.println("Insert is wrong.");
						break;
					}
					}
			
			System.out.println("");
				
			System.out.print("삭제할 책 번호를 입력하세요: ");
			String delbook = scan.nextLine();
			
			try {
				String delete = "delete from book where book_number = '"+delbook+"'";
				r = st.executeUpdate(delete);
				
					
				if(r!=0) { 
					System.out.println("삭제 완료"); 
					} else { 
						System.out.print("삭제 실패. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
						int rg = scan.nextInt();
						switch (rg) {
						case 1:
							mg.Manage();
							break;
						default:
							System.out.println("Insert is wrong.");
							break;
						}
						}
				} catch(Exception e) {
					System.out.println("Data가 없습니다.");
					return;
					}
			} catch(Exception e) {
				System.out.println("Data가 없습니다.");
				return;
				}
		}

	public void dataChange(){
		System.out.print("업데이트하고 싶은 책 제목을 입력하세요 :");
		String searchtitle = scan.nextLine();
		
		try {
			String showData = " select * from book WHERE title LIKE '%"+searchtitle+"%' ";
			rs = st.executeQuery(showData);
			
			if(rs.next()) {
				rs=st.getResultSet();
				
				System.out.println("[search result]");
				rs.beforeFirst();
				System.out.println("________________________________________________________________________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "writer") + "|" 
				                 + String.format(" %20s ", "publisher") + "|"
				                 + String.format(" %10s ", "borrow 가능") + "|"
				                 + String.format(" %20s ", "borrowed Id"));
				System.out.println("________________________________________________________________________________________________________________________");
				String can = "O";
				while(rs.next()) {						
					if(rs.getString(5)!=null) {
						can = "X";
					} 
					
					System.out.println(String.format("     %5s     ", rs.getString(1)) + "|" 
	                 		 + String.format(" %20s ",  rs.getString(2)) + "|"  
	                         + String.format(" %20s ", rs.getString(3)) + "|"  
	                         + String.format(" %20s ", rs.getString(4)) + "|"
	                         + String.format(" %10s ", can) + "|"
	                         + String.format(" %20s ", rs.getString(5)));
					}
				System.out.println("________________________________________________________________________________________________________________________");
				} else { 
					System.out.print("검색 결과가 없습니다. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
					int rg = scan.nextInt();
					switch (rg) {
					case 1:
						mg.Manage();
						break;
					default:
						System.out.println("Insert is wrong.");
						break;
					}
					}
			
			System.out.println("");
			
			System.out.print("업데이트할 책 번호를 입력하세요: ");
			String upbook = scan.nextLine();
				
			try
			{
				String showData4 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
				rs = st.executeQuery(showData4);
				
				if(rs.next()) {
					rs=st.getResultSet();
					
					System.out.println("[update할 책 정보]");
					rs.beforeFirst();
					while(rs.next()) {
						System.out.print(rs.getString(1)+" "); //System.out.print("책 번호 : ");
						System.out.print(rs.getString(2)+" "); //System.out.print("책 제목 : ");
						System.out.print(rs.getString(3)+" "); //System.out.print("저자 : ");
						System.out.print(rs.getString(4)+" "); //System.out.print("출판사 : ");
						System.out.println(rs.getString(5));   //System.out.print("대출 학생 ID : ");
						}
					
					System.out.println("1.책 제목 변경 | 2.저자 변경 | 3.출판사 변경");
					System.out.print("선택 : ");
					int upnum = scan.nextInt();
					scan.nextLine();
					
					if(upnum==1) { // 책 제목 변경 
						
						System.out.print("새로운 이름을 입력하세요: ");
						String uptitle = scan.nextLine();
						String updata = " update book set title='"+uptitle+"' where book_number LIKE '"+upbook+"' ";
						st.executeUpdate(updata);
						
						if(st.executeUpdate(updata)>0) { 
							System.out.println("업데이트 완료"); 
							} else { 
								System.out.print("업데이트 실패. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
								int rg = scan.nextInt();
								switch (rg) {
								case 1:
									mg.Manage();
									break;
								default:
									System.out.println("Insert is wrong.");
									break;
								}
								}
						
						String showData3 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
						rs=st.executeQuery(showData3);
						
						if(rs!=null) {
							rs=st.getResultSet();
						}
						
						System.out.println("[update result]");
						while(rs.next()) {
							System.out.print(rs.getString(1)+" "); //System.out.print("책 번호 : ");
							System.out.print(rs.getString(2)+" "); //System.out.print("책 제목 : ");
							System.out.print(rs.getString(3)+" "); //System.out.print("저자 : ");
							System.out.print(rs.getString(4)+" "); //System.out.print("출판사 : ");
							System.out.println(rs.getString(5));   //System.out.print("대출 학생 ID : ");
							}
						} else if(upnum==2) { // 저자 변경
							
							System.out.print("새로운 저자를 입력하세요: ");
							String upwriter = scan.nextLine();
							String updata = " update book set writer='"+upwriter+"' where book_number LIKE '"+upbook+"' ";
							st.executeUpdate(updata);
							
							if(st.executeUpdate(updata)>0) { 
								System.out.println("업데이트 완료"); 
								} else { 
									System.out.print("업데이트 실패. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
									int rg = scan.nextInt();
									switch (rg) {
									case 1:
										mg.Manage();
										break;
									default:
										System.out.println("Insert is wrong.");
										break;
									}
									}
							
							String showData3 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
							rs=st.executeQuery(showData3);
							
							if(rs!=null) {
								rs=st.getResultSet();
							}
							
							System.out.println("[update result]");
							while(rs.next()) {
								System.out.print(rs.getString(1)+" "); //System.out.print("책 번호 : ");
								System.out.print(rs.getString(2)+" "); //System.out.print("책 제목 : ");
								System.out.print(rs.getString(3)+" "); //System.out.print("저자 : ");
								System.out.print(rs.getString(4)+" "); //System.out.print("출판사 : ");
								System.out.println(rs.getString(5));   //System.out.print("대출 학생 ID : ");
								}
						} else if(upnum==3) {
							
							System.out.print("새로운 출판사를 입력하세요: ");
							String uppublisher  = scan.nextLine();
							String updata = " update book set publisher='"+uppublisher +"' where book_number LIKE '"+upbook+"' ";
							st.executeUpdate(updata);
							
							if(st.executeUpdate(updata)>0) { 
								System.out.println("업데이트 완료"); 
								} else { 
									System.out.print("업데이트 실패. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
									int rg = scan.nextInt();
									switch (rg) {
									case 1:
										mg.Manage();
										break;
									default:
										System.out.println("Insert is wrong.");
										break;
									}
									}
							
							String showData3 = " select * from book WHERE book_number LIKE '"+upbook+"' ";
							rs=st.executeQuery(showData3);
							
							if(rs!=null) {
								rs=st.getResultSet();
							}
							
							System.out.println("[update result]");
							while(rs.next()) {
								System.out.print(rs.getString(1)+" "); //System.out.print("책 번호 : ");
								System.out.print(rs.getString(2)+" "); //System.out.print("책 제목 : ");
								System.out.print(rs.getString(3)+" "); //System.out.print("저자 : ");
								System.out.print(rs.getString(4)+" "); //System.out.print("출판사 : ");
								System.out.println(rs.getString(5));   //System.out.print("대출 학생 ID : ");
								}
							}
					} else { 
						System.out.print("잘못된 책 번호입니다. 메뉴로 돌아가고 싶으면 1을 입력하세요 => ");
						int rg = scan.nextInt();
						switch (rg) {
						case 1:
							mg.Manage();
							break;
						default:
							System.out.println("Insert is wrong.");
							break;
						}
						}
				} catch(SQLException e) {
					System.out.println("Data가 없습니다.");
					return;
					}
			} catch(Exception e) {
				System.out.println("Data가 없습니다.");
				return;
				}
		}

 */
	}
