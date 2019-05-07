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
import jp.webschool.javaweb.chapter17.util.Encoder;
import jp.webschool.javaweb.chapter17.util.PropertyLoader;

/**
 * Servlet implementation class ItemSelector
 */
@WebServlet("/ItemSelector")
public class ItemSelector extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = Encoder.encodeUTF8(request.getParameter("id"));
		String name = Encoder.encodeUTF8(request.getParameter("name"));
		String stringPrice = Encoder.encodeUTF8(request.getParameter("price"));
		String resultPage = PropertyLoader.getProperty("url.jsp.error");

		try {
			int price = Integer.MAX_VALUE;

			if (id.length() == 0) {
				id = "%";
			}

			if (name.length() == 0) {
				name = "%";
			}

			if (stringPrice.length() != 0) {
				price = Integer.parseInt(stringPrice);
			}

			ItemDAO dao = new ItemDAO();
			List<ItemBean> itemList = dao.getItemListConditionally(id, name, price);
			//request.setAttribute("itemList", itemList);
			Iterator<ItemBean> iterator = itemList.iterator();
			List<ItemBean> itemList10 = new ArrayList<ItemBean>();
			for (int i = 0; iterator.hasNext() && i < 10; i++) {
				itemList10.add(iterator.next());
			}
			request.setAttribute("itemList", itemList10);

			resultPage = PropertyLoader.getProperty("url.jsp.selectItem");

			int maxPage = (int) (itemList.size() / 10 + 1);
			HttpSession session = request.getSession(false);
			session.setAttribute("itemList", itemList);
			session.setAttribute("page", "1");
			session.setAttribute("maxPage", Integer.toString(maxPage));

		} catch (NamingException e) {
			request.setAttribute("errorMessage", e.getMessage());

		} catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());

		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", PropertyLoader.getProperty("message.NumberFormatException"));
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}
}
