package c230926.studentServ;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import c230926.student.StudentDAO;
import c230926.student.StudentVO;

/**
 * Servlet implementation class Join
 */
@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");

		String html = "<html>";
		html +="<head>";
		html +="<meta charset='UTF-8' />";
		html +="<title>학생 목록</title>";
		html +="</head>";
		html +="<body>";
		html +="<form action='join' method='post'>";
		html +="<input type='text' name='student-name' placeholder='NAME' /><br>";
		html +="<input type='text' name='student-id' placeholder='ID' /><br>";
		html +="<input type='text' name='student-pw' placeholder='PASSWORD' /><br>";
		html +="<input type='text' name='student-age' placeholder='AGE' /><br>";
		html +="<input type='text' name='student-git' placeholder='GITADDRESS' /><br>";
		html +="<button>회원가입</button>";
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
		String studentName = request.getParameter("student-name");
		String studentId = request.getParameter("student-id");
		String studentPw = request.getParameter("student-pw");
		int studentAge = Integer.parseInt(request.getParameter("student-age"));
		String studentGit = request.getParameter("student-git");
		StudentDAO dao = new StudentDAO();
		dao.setStudent(studentName, studentId, studentPw, studentAge, studentGit);
		
//		doGet(request, response);
	}

}
