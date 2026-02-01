package efgMart1.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import efgMart1.common.JDBCTemplate;
import efgMart1.model.vo.Item;
import efgMart1.model.vo.Product;
import efgMart1.repository.ProductDAO;

public class ProductService {

	public int insertStock(Item addStockItem, int addCount) {
		Connection con = JDBCTemplate.getConnection();

		String itemId = addStockItem.getItemId();
		int currentStock = addStockItem.getStockCount();

		int countInserted = 0; // 성공한 횟수 카운트

		try {
			// 커밋 설정 수동 (트랜잭션)
			con.setAutoCommit(false);

			for (int i = 1; i <= addCount; i++) {
				int nextOrder = currentStock + i;
				String serialNo = itemId + String.format("%03d", nextOrder);

				countInserted += new ProductDAO().insertStock(con, serialNo, itemId, nextOrder);
			}

			if (countInserted == addCount) {
				JDBCTemplate.commit(con);
				return 1; // 성공
			} else {
				JDBCTemplate.rollback(con);
				return 0; // 실패
			}

		} catch (SQLException e) {
			JDBCTemplate.rollback(con);
			e.printStackTrace();
			return 0;
		} finally {
			JDBCTemplate.close(con); // 마지막에 연결 반납
		}
	}

	public ArrayList<Product> searchedProductList(String itemId) {
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDAO().searchedProductList(con, itemId);
		
		JDBCTemplate.close(con);
		
		return list;
	}
}




















