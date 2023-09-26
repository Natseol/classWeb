package c230926.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class StudentDAO {
	private Connection con;

	public List<StudentVO> getList() {
		List<StudentVO> list = new ArrayList<>();

		try {
			connect();

			String query = "select * from student2";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query); // 스트림
			while(rs.next()) {//컬럼을 가져옴
				StudentVO temp = new StudentVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("student_id"),
						rs.getString("student_pw"),
						rs.getInt("age"),
						rs.getString("git")						
						);
				list.add(temp);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;				
	}

//	private void connect() throws Exception{
//		Class.forName("oracle.jdbc.OracleDriver");
//		con = DriverManager.getConnection(
//				"jdbc:oracle:thin:@localhost:1521/xe",
//				"java",
//				"qwer"
//				);
//	}
	public StudentVO getStudent(String studentId, String studentPw) {
		StudentVO temp = null;
		try {
			connect();

			String query = "select * from student2 where student_id=? and student_pw=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, studentId);
			pstmt.setString(2, studentPw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {//컬럼을 가져옴
				temp = new StudentVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("student_id"),
						rs.getString("student_pw"),
						rs.getInt("age"),
						rs.getString("git")						
						);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;	
	}
	
	public StudentVO getStudent(int id) {
		StudentVO temp = null;
		try {
			connect();

			String query = "select * from student2 where id=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {//컬럼을 가져옴
				temp = new StudentVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("student_id"),
						rs.getString("student_pw"),
						rs.getInt("age"),
						rs.getString("git")						
						);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;	
	}
	

	private void connect() throws Exception{
		Context ctx = new InitialContext();
		Context envContext = (Context) ctx.lookup("java:/comp/env");
		DataSource dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		con = dataFactory.getConnection();
	}
}
