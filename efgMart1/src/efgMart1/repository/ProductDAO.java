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
import efgMart1.model.vo.Product;


public class ProductDAO {
	private Properties prop = new Properties();
	
	public ProductDAO() {
        try {
            prop.loadFromXML(new FileInputStream("resources/product-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/*
	/**
	 * Item테이블의 stockCount증가(update)와 product테이블의 serialNo생성 및 삽입(insert) 모두 진행
	 * 두개의 테이블에 대한 두가지 작업을 오라클 트리거를 통해 데이터 무결성 보장
	 * @param addStockItem 재고를 추가하고자하는 품목
	 * @param addCount 사용자가 요청한 재고 추가수량
	 * @return result 재고 수량 추가의 성공유무 결과값
	 */
	
	/* 자바 메서드로 서비스 처리
	public int insertStockCount(Item addStockItem, int addCount) {
		//해당 메서드로 Item테이블의 stockCount증가(update)와 product테이블의 serialNo생성 및 삽입(insert) 모두 진행
		//두개의 테이블에 대한 두가지 작업을 트랜잭션을 통해 데이터 무결성 보장
		Connection con = null;
		PreparedStatement pstmtP = null; // Product용
		PreparedStatement pstmtI = null; // Item용
		int result = 0;
		String itemId = addStockItem.getItemId();
		int currentStock = addStockItem.getStockCount();
		
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false); // 수동 커밋으로 설정 (트랜잭션 시작)
			
			int countInserted = 0; // Product테이블에서 insert 진행된 횟수
			
			// 작업 1: Product 테이블에 시리얼번호 생성 및 삽입
			String sqlP = "INSERT INTO PRODUCT (serial_no, item_id, stock_order) VALUES (?, ?, ?)";
			pstmtP = con.prepareStatement(sqlP);

			for (int i = 1; i <= addCount; i++) {
				int nextOrder = currentStock + i; // 기존 재고(제일 마지막에 입고된 재고) 다음 번호부터
				String serialNo = itemId + String.format("%03d", nextOrder);

				pstmtP.setString(1, serialNo);
				pstmtP.setString(2, itemId);
				pstmtP.setInt(3, nextOrder);
				
				countInserted += pstmtP.executeUpdate(); // insert 실행 될때마다 +1
			}

			// 작업 2: ITEM 테이블의 재고 수량 업데이트 (증가)
			String sqlI = "UPDATE ITEM SET stock_count = stock_count + ? WHERE item_id = ?";
			pstmtI = con.prepareStatement(sqlI);
			pstmtI.setInt(1, addCount);
			pstmtI.setString(2, itemId);
			int resultUpdate = pstmtI.executeUpdate();

			/**
			 * 트랜잭션 성공 여부 확인
			 * 작업1 : countInserted == addCount => insert 성공횟수 일치하면 성공
			 * 작업2 : resultUpdate > 0 => 양수로 변하면 업데이트 성공
			 * 작업1 + 작업2 모두 성공하면 데이터 무결성 확보
			 */
			/*
			  if (countInserted == addCount  && resultUpdate > 0) {
				con.commit(); // 둘 다 성공 시 확정!
				result = 1;
			} else {
				con.rollback(); // 실패 시 롤백
			}

		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (Exception ex) {
			}
			e.printStackTrace();
		} finally {
			// 자원 반납 (close)
			try {
		        if (pstmtP != null) pstmtP.close();
		        if (pstmtI != null) pstmtI.close();
		        if (con != null) con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		return result;
	}
	*/
	
	
	public ArrayList<Product> searchedProductList(Connection con, String itemId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; //select할때 필요한 필드 rset
		ArrayList<Product> searchedProductList = new ArrayList<>();
		String query = prop.getProperty("searchedProductList");
		
		try {
			//4.쿼리문 전달
			pstmt = con.prepareStatement(query);
			//5.입력값 매칭
			pstmt.setString(1, itemId);
			//6.쿼리문 실행
			rset = pstmt.executeQuery();
			//7. rset에 있는 레코드를 추출해서 ArrayList<Product>에 담는다.
			while (rset.next()) {
				String serialNo = rset.getString("SERIAL_NO");
				String itemId_ = rset.getString("ITEM_ID");
				String modelName = rset.getString("MODEL_NAME");
				String color = rset.getString("COLOR");
				int stockOrder = rset.getInt("STOCK_ORDER");
				String isSoldStr = rset.getString("IS_SOLD");
				char isSold = (isSoldStr != null) ? isSoldStr.charAt(0) : 'N'; // 판매여부가 null이 아니면 첫번째 글자를 char타입으로 변환, null이면 'N'
				String inDate = rset.getString("IN_DATE");
				String outDate = rset.getString("OUT_DATE");
				Product product = new Product(serialNo, itemId_, modelName, color, stockOrder, isSold, inDate, outDate);
				searchedProductList.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
		}
		return searchedProductList;
	}

	
	public int insertStock(Connection con, String serialNo, String itemId, int nextOrder) {
		//1. Statement 선언
		PreparedStatement pstmt = null;
		//2. insert,update,delete : int result
		//2. select : ResultSet rset
		int result = 0;
		//3. 쿼리문 작성
		String query = prop.getProperty("insertStock");
        
		try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, serialNo);
            pstmt.setString(2, itemId);
            pstmt.setInt(3, nextOrder);
            
            result = pstmt.executeUpdate(); // product 추가됨과 동시에 DB 트리거 발동하면서 해당하는 품목에 재고수량 +1
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt); 
        }
        return result;
	
	}

}
