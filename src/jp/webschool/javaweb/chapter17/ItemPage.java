package jp.webschool.javaweb.chapter17;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.webschool.javaweb.chapter17.util.Encoder;
import jp.webschool.javaweb.chapter17.util.PropertyLoader;

/**
 * Servlet implementation class ItemPage
 */
@WebServlet("/ItemPage")
public class ItemPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = Encoder.encodeUTF8(request.getParameter("id"));
		String resultDirectory = PropertyLoader.getProperty("url.jsp.productPage");
		String resultPage = resultDirectory + id + ".jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}


}
