package jp.webschool.javaweb.chapter17.html;

import java.io.File;

import jp.webschool.javaweb.chapter17.util.PropertyLoader;

public class FileDelete {

	public void FindDelete(String id) {

		//削除対象ファイルが格納されているフォルダ指定
		String productPage = PropertyLoader.getProperty("url.jsp.productPage");

		//削除対象のファイル
		File file = new File(productPage + id + ".jsp");

		if (file.exists()) {
			file.delete();
		}

	}

}
