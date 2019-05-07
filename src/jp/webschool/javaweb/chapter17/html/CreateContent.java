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

				pw.println("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n"
						+ "pageEncoding=\"UTF-8\" %>\n"
						+ "<jsp:include page=\"" + productPage + "template.jsp\">\n"
						+ "<jsp:param name=\"id\" value=\"" + id + "\" />\n"
						+ "<jsp:param name=\"name\" value=\"" + name + "\" />\n"
						+ "<jsp:param name=\"price\" value=\"" + price + "\" />\n"
						+ "</jsp:include>");
				pw.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
