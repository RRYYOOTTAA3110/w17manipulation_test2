package jp.webschool.javaweb.chapter17;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.webschool.javaweb.chapter17.bean.ItemBean;
import jp.webschool.javaweb.chapter17.dao.ItemDAO;
import jp.webschool.javaweb.chapter17.util.PropertyLoader;

/**
 * Servlet implementation class ItemManager
 */
@WebServlet("/ItemManager")
public class ItemManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resultPage = PropertyLoader.getProperty("url.jsp.error");

		try {
			ItemDAO dao = new ItemDAO();
			List<ItemBean> itemList = dao.getItemListAll();

			//request.setAttribute("itemList", itemList);
			Iterator<ItemBean> iterator = itemList.iterator();
			List<ItemBean> itemList10 = new ArrayList<ItemBean>();
			for (int i = 0; iterator.hasNext() && i < 10; i++) {
				itemList10.add(iterator.next());
			}
			request.setAttribute("itemList", itemList10);

			resultPage = PropertyLoader.getProperty("url.jsp.selectItem");

			int maxPage = (int) (itemList.size() / 10 + 1);
			HttpSession session = request.getSession(true);
			session.setAttribute("itemList", itemList);
			session.setAttribute("page", "1");
			session.setAttribute("maxPage", Integer.toString(maxPage));

		} catch (NamingException e) {
			request.setAttribute("errorMessage", e.getMessage());

		} catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
