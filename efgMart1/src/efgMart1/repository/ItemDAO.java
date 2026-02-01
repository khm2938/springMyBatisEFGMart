package efgMart1.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import efgMart1.common.JDBCTemplate;
import efgMart1.model.vo.Item;

public class ItemDAO {
	private Properties prop = new Properties();
	
	public ItemDAO() {
		try {
	        prop.loadFromXML(new FileInputStream("resources/item-query.xml"));
	    } catch (IOException e) {
	        System.out.println("[오류] item-query.xml 파일을 찾을 수 없습니다.");
	        e.printStackTrace();
	    }
    }

	public ArrayList<Item> selectAllItemList(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; // select할때 필요한 필드 rset
		ArrayList<Item> selectAllItemList = new ArrayList<Item>();

		try {
			// 3.쿼리문 작성
			String query = prop.getProperty("selectAllItemList");
			// 4.Statement 객체생성 및 질의 전달
			pstmt = con.prepareStatement(query);
			// 5.쿼리문 실행
			rset = pstmt.executeQuery();
			// 6. rset에 있는 레코드를 추출해서 ArrayList<Item>에 담는다.

			while (rset.next()) {
				String itemId = rset.getString("ITEM_ID");
				String modelName = rset.getString("MODEL_NAME");
				String brand = rset.getString("BRAND");
				String category = rset.getString("ITEM_CATEGORY");
				String color = rset.getString("COLOR");
				int shoeSize = rset.getInt("SHOE_SIZE");
				int price = rset.getInt("PRICE");
				int discountRate = rset.getInt("DISCOUNT_RATE");
				int stockCount = rset.getInt("STOCK_COUNT");

				Item item = new Item(itemId, modelName, brand, category, color, shoeSize, price, discountRate, stockCount);
				selectAllItemList.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return selectAllItemList;
	}

	public int insertItem(Connection con, Item item) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertItem");
		
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, item.getItemId());
			pstmt.setString(2, item.getModelName());
			pstmt.setString(3, item.getBrand());
			pstmt.setString(4, item.getCategory());
			pstmt.setString(5, item.getColor());
			pstmt.setInt(6, item.getShoeSize());
			pstmt.setInt(7, item.getPrice());
			pstmt.setInt(8, item.getDiscountRate());
			pstmt.setInt(9, item.getStockCount());
			// 6. 쿼리문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteItem(Connection con, String itemId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteItem");
		try {
			// 4. Statement 객체생성 및 질의 전달
			pstmt = con.prepareStatement(query);

			// 5.입력값 매칭
			pstmt.setString(1, itemId);
			
			// 6. 쿼리문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납
				JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateItem(Connection con, Item item) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateItem");
		try {
			// 4. Statement 객체생성 및 질의 전달
			pstmt = con.prepareStatement(query);

			// 5.입력값 매칭
			pstmt.setInt(1, item.getPrice());
			pstmt.setInt(2, item.getDiscountRate());
			pstmt.setString(3, item.getItemId());
			
			// 6. 쿼리문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납
				JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Item> selectItemListByBrand(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; // select할때 필요한 필드 rset
		ArrayList<Item> selectItemListByBrand = new ArrayList<Item>();
		String query = prop.getProperty("selectItemListByBrand");
		try {
			// 4.Statement 객체생성 및 질의 전달
			pstmt = con.prepareStatement(query);
			// 5.쿼리문 실행
			rset = pstmt.executeQuery();
			// 6. rset에 있는 레코드를 추출해서 ArrayList<Item>에 담는다.

			while (rset.next()) {
				String itemId = rset.getString("ITEM_ID");
				String modelName = rset.getString("MODEL_NAME");
				String brand = rset.getString("BRAND");
				String category = rset.getString("ITEM_CATEGORY");
				String color = rset.getString("COLOR");
				int shoeSize = rset.getInt("SHOE_SIZE");
				int price = rset.getInt("PRICE");
				int discountRate = rset.getInt("DISCOUNT_RATE");
				int stockCount = rset.getInt("STOCK_COUNT");

				Item item = new Item(itemId, modelName, brand, category, color, shoeSize, price, discountRate, stockCount);
				selectItemListByBrand.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return selectItemListByBrand;
	}
	
	public ArrayList<Item> searchedItemList(Connection con, Item searchedItem) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; //select할때 필요한 필드 rset
		ArrayList<Item> searchedItemList = new ArrayList<>();
		String query = prop.getProperty("searchedItemList");
		
		try {
			//4.쿼리문 전달
			pstmt = con.prepareStatement(query);
			//5.입력값 매칭
			pstmt.setString(1, searchedItem.getItemId().substring(0, 7) + "%");
			//6.쿼리문 실행
			rset = pstmt.executeQuery();
			//7. rset에 있는 레코드를 추출해서 ArrayList<Item>에 담는다.
			while (rset.next()) {
				String itemId = rset.getString("ITEM_ID");
				String modelName = rset.getString("MODEL_NAME");
				String brand = rset.getString("BRAND");
				String category = rset.getString("ITEM_CATEGORY");
				String color = rset.getString("COLOR");
				int shoeSize = rset.getInt("SHOE_SIZE");
				int price = rset.getInt("PRICE");
				int discountRate = rset.getInt("DISCOUNT_RATE");
				int stockCount = rset.getInt("STOCK_COUNT");

				Item item = new Item(itemId, modelName, brand, category, color, shoeSize, price, discountRate, stockCount);
				searchedItemList.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return searchedItemList;
	}
	
}






















