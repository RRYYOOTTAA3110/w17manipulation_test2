package jp.webschool.javaweb.chapter17.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jp.webschool.javaweb.chapter17.util.PropertyLoader;

public class CreateContent {

	public void CreatePage(String id, String name, int price) {

		//ファイルが格納されるフォルダ指定
		String productPage = PropertyLoader.getProperty("url.jsp.productPage");

		try {
			//存在判定対象のファイル
			File file = new File(productPage + id + ".jsp");

			if (!(file.exists())) {
				PrintWriter pw = new PrintWriter
						(new BufferedWriter(new FileWriter("C:/java/w17manipulation_test2/WebContent/WEB-INF/jsp/" + id + ".jsp")));

				/*pw.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n"
						+ "pageEncoding=\"UTF-8\" %>\n"
						+ "<jsp:include page=\"" + productPage + "template.jsp\">\n"
						+ "<jsp:param name=\"id\" value=\"" + id + "\" />\n"
						+ "<jsp:param name=\"name\" value=\"" + name + "\" />\n"
						+ "<jsp:param name=\"price\" value=\"" + price + "\" />\n"
						+ "</jsp:include>");*/
				pw.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n"
						+ "pageEncoding=\"UTF-8\"\n"
						+ "import=\"jp.webschool.javaweb.chapter17.util.PropertyLoader\"\n"
						+ "import=\"jp.webschool.javaweb.chapter17.util.Encoder\"%>\n"
						+ "<% request.setCharacterEncoding(\"utf-8\"); %>\n"
						+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
						+ "<html>\n"
						+ "<head>\n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
						+ "<title>" + name + "</title>\n"
						+ "<link href=\"css/table.css\" rel=\"stylesheet\" type=\"text/css\">\n"
						+ "</head>\n"
						+ "<body>\n"
						+ "<h1>" + name + "</h1>\n"
						+ "<table>\n"
						+ "<tr>\n"
						+ "<th id=\"id\">ID</th>\n"
						+ "<th>商品名</th>\n"
						+ "<th id=\"price\">価格</th>\n"
						+ "</tr>\n"
						+ "<tr>\n"
						+ "<td>" + id + "</td>\n"
						+ "<td>" + name + "</td>\n"
						+ "<td>" + price + "円</td>\n"
						+ "</tr>\n"
						+ "<tr>\n"
						+ "<td colspan=\"3\"><a href=\"<%=PropertyLoader.getProperty(\"url.servlet.ItemManager\") %>\">一覧へ戻る</a></td>\n"
						+ "</tr>\n"
						+ "</table>\n"
						+ "</body>\n"
						+ "</html>\n");
				pw.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
