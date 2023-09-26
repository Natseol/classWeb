package c230926.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TextServ
 */
@WebServlet("/text")
public class TextServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("수정");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardDAO dao = new BoardDAO();
		
		BoardVO vo = dao.getBoard(Integer.parseInt(request.getParameter("id")));
		
		String html = "<html>";
		html +="<head>";
		html +="<meta charset='UTF-8' />";
		html +="<title>게시판</title>";
		html +="<style>";
		html +=".text {";
		html +="width:400px;";
		html +="height:700px;";
		html +="}";
		html +="</style>";
		html +="</head>";
		html +="<body>";
		html += "<form action='./text?id="+vo.getId()+"' method='post'>"; 
		html +="작성자 <input type='text' name='board-name' placeholder='"+vo.getName()+"' /><br>";
		html +="제목 <input type='text' name='board-subject' placeholder='"+vo.getSubject()+"' /><br>";
		html +="<input type='text' class='text' name='board-text' placeholder='"+vo.getText()+"' /><br>";
		html +="<button>변경</button>";
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
		response.setCharacterEncoding("UTF-8");
		String boardName = request.getParameter("board-name");
		String boardSubject = request.getParameter("board-subject");
		String boardText = request.getParameter("board-text");
		BoardDAO dao = new BoardDAO();
		dao.updateBoard(boardName, boardSubject, boardText, Integer.parseInt(request.getParameter("id")));
		System.out.println("업데이트");
		response.sendRedirect("./board");
	}

}
