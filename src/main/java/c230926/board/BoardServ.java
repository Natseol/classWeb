package c230926.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import c230926.student.StudentDAO;
import c230926.student.StudentVO;

/**
 * Servlet implementation class Board
 */
@WebServlet("/board")
public class BoardServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("시작");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.getList();
		
		BoardVO vo = null;
		if (request.getParameter("id")!=null) {
			vo = dao.getBoard(Integer.parseInt(request.getParameter("id")));
		}
		
		String html = "<html>";
		html +="<head>";
		html +="<meta charset='UTF-8' />";
		html +="<title>게시판</title>";
		html +="</head>";
		html +="<body>";
		if(vo==null) {
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId());
				html +=list.get(i).getId();
				html +=" ";
				html +="<a href='./board?id="+list.get(i).getId()+"'>"+list.get(i).getSubject()+"</a>";
				html +=" ";
				html +=list.get(i).getName();
				html +=" ";
				html +=list.get(i).getDate();
				html +="<br>";
			}
			html +="<a href='./new'><button>새 글 작성</button></a>";
		} else {
				html += "<div>";
				html += "작성자:"+vo.getName()+"<br>";
				html += "제목:"+vo.getSubject()+"<br>";
				html += vo.getText()+"<br>";
				html +="<a href='./text?id="+vo.getId()+"'>"+"<button>글 수정</button></a>";			
				html += "</div>";
				html += "<form action='./board?id="+vo.getId()+"' method='post'>"; 
				html += "<a><button>글 삭제</button></a>";
				html += "</form>";
		}
		html +="</body>";
		html +="</html>";
		response.getWriter().append(html);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BoardDAO dao = new BoardDAO();
		int boardId = Integer.parseInt(request.getParameter("id"));
		dao.deleteBoard(boardId);
		response.sendRedirect("./board");

	}

}
