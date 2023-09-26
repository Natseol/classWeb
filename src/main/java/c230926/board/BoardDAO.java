package c230926.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	private Connection con;

	public List<BoardVO> getList() {
		List<BoardVO> list = new ArrayList<>();

		try {
			connect();

			String query = "select * from board";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query); // 스트림
			while(rs.next()) {//컬럼을 가져옴
				BoardVO temp = new BoardVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("subject"),
						rs.getString("text"),
						rs.getString("created_at")
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
	
	public void setBoard(String name, String subject, String text) {
		try {
			connect();
			
			String insertQuery = "insert into board (name, subject, text, created_at) values (?,?,?,sysdate)";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, text);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBoard(int id) {
		try {
			connect();
			
			String insertQuery = "delete from board where id=?";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBoard(String name, String subject, String text, int id) {
		try {
			connect();
			
			String insertQuery = "update board set name=?, subject=?, text=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, text);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BoardVO getBoard(int id) {
		BoardVO temp = null;
		try {
			connect();

			String query = "select * from board where id=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {//컬럼을 가져옴
				temp = new BoardVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("subject"),
						rs.getString("text"),
						rs.getString("created_at")
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
