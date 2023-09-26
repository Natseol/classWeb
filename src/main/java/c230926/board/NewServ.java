package c230926.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import c230926.student.StudentDAO;

/**
 * Servlet implementation class NewServ
 */
@WebServlet("/new")
public class NewServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("작성");
		
		response.setCharacterEncoding("UTF-8");

		String html = "<html>";
		html +="<head>";
		html +="<meta charset='UTF-8' />";
		html +="<title>새 글 작성</title>";
		html +="</head>";
		html +="<body>";
		html +="<form action='new' method='post'>";
		html +="<input type='text' name='board-name' placeholder='작성자' /><br>";
		html +="<input type='text' name='board-subject' placeholder='제목' /><br>";
		html +="<input type='text' class='text' name='board-text' placeholder='내용' /><br>";
		html +="<button>작성</button>";
		html +="</form>";
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
		String boardName = request.getParameter("board-name");
		String boardSubject = request.getParameter("board-subject");
		String boardText = request.getParameter("board-text");
		BoardDAO dao = new BoardDAO();
		dao.setBoard(boardName, boardSubject, boardText);
		response.sendRedirect("./board");
	}

}
