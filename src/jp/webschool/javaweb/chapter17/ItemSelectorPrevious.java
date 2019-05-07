package jp.webschool.javaweb.chapter17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.webschool.javaweb.chapter17.bean.ItemBean;
import jp.webschool.javaweb.chapter17.util.Cast;
import jp.webschool.javaweb.chapter17.util.PropertyLoader;

/**
 * Servlet implementation class ItemSelectorPrevious
 */
@WebServlet("/ItemSelectorPrevious")
public class ItemSelectorPrevious extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		List<ItemBean> itemList = Cast.castList(session.getAttribute("itemList"));
		Iterator<ItemBean> iterator = itemList.iterator();

		List<ItemBean> itemList10 = new ArrayList<ItemBean> ();
		int page = Integer.parseInt((String) session.getAttribute("page"));

		if (page > 1) {
			page--;
		}

		for (int i = 0; iterator.hasNext() && i < page * 10; i++) {
			ItemBean item = iterator.next();
			if (i >= (page - 1) * 10) {
				itemList10.add(item);
			}
		}

		request.setAttribute("itemList", itemList10);
		session.setAttribute("page", Integer.toString(page));

		String resultpage = PropertyLoader.getProperty("url.jsp.selectItem");
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultpage);
		dispatcher.forward(request, response);
	}
}
