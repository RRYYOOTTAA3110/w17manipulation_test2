package jp.webschool.javaweb.chapter17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.webschool.javaweb.chapter17.bean.ItemBean;
import jp.webschool.javaweb.chapter17.html.CreateContent;

public class ItemDAO {
	private static final String SELECT = "select * from item";
	private static final String CONDITIONAL_SELECT = "select * from item where id like ? and name like ? and price<=?";
	private static final String ID_SELECT = "select * from item where id=?";
	private static final String INSERT = "insert into item values(?, ?, ?)";
	private static final String UPDATE = "update item set id=?, name=?, price=? where id=?";
	private static final String DELETE = "delete from item where id=?";

	private DataSource source;

	public ItemDAO() throws NamingException {
		InitialContext context = new InitialContext();
		source = (DataSource) context.lookup("java:comp/env/jdbc/datasource");
	}

	//一覧取得
	public List<ItemBean> getItemListAll() throws SQLException {

		List<ItemBean> itemList = new ArrayList<ItemBean> ();
		Connection connection = source.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(SELECT);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				ItemBean item = new ItemBean();
				item.setId(result.getString("id"));
				item.setName(result.getString("name"));
				item.setPrice(result.getInt("price"));
				itemList.add(item);

				//製品ページ作成
				CreateContent content = new CreateContent();
				content.CreatePage(result.getString("id"), result.getString("name"), result.getInt("price"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return itemList;

	}

	//条件指定で一覧取得
	public List<ItemBean> getItemListConditionally(String id, String name, int price) throws SQLException {

		List<ItemBean> itemList = new ArrayList<ItemBean> ();
		Connection connection = source.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(CONDITIONAL_SELECT);
			statement.setString(1, "%" + id + "%");
			statement.setString(2, "%" + name + "%");
			statement.setInt(3, price);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				ItemBean item = new ItemBean();
				item.setId(result.getString("id"));
				item.setName(result.getString("name"));
				item.setPrice(result.getInt("price"));
				itemList.add(item);
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return itemList;

	}

	 //商品1件取得
	public ItemBean getItemById(String id) throws SQLException {

		ItemBean item = new ItemBean();
		Connection connection = source.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(ID_SELECT);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				item.setId(result.getString("id"));
				item.setName(result.getString("name"));
				item.setPrice(result.getInt("price"));
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return item;
	}

	//商品の追加
	public void insertItem(String id, String name, int price) throws SQLException {

		Connection connection = source.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setInt(3, price);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	//商品の更新
	public void updateItem(String id, String name, int price, String oldId) throws SQLException {

		Connection connection = source.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setInt(3, price);
			statement.setString(4, oldId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	//商品の削除
	public void deleteItem(String id) throws SQLException {

		Connection connection = source.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}
