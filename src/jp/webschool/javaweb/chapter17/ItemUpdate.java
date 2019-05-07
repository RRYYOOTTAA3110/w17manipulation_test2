package jp.webschool.javaweb.chapter17;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.webschool.javaweb.chapter17.bean.ItemBean;
import jp.webschool.javaweb.chapter17.dao.ItemDAO;
import jp.webschool.javaweb.chapter17.util.Encoder;
import jp.webschool.javaweb.chapter17.util.PropertyLoader;

/**
 * Servlet implementation class ItemUpdate
 */
@WebServlet("/ItemUpdate")
public class ItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = Encoder.encodeUTF8(request.getParameter("id"));
		String resultPage = PropertyLoader.getProperty("url.jsp.error");

		try {
			ItemDAO dao = new ItemDAO();
			ItemBean item = dao.getItemById(id);
			request.setAttribute("item", item);
			resultPage = PropertyLoader.getProperty("url.jsp.updateItem");

		} catch (NamingException e) {
			request.setAttribute("errorMessage", e.getMessage());

		} catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
