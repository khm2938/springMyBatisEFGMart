package efgMart1.service;

import java.sql.Connection;
import java.util.ArrayList;

import efgMart1.common.JDBCTemplate;
import efgMart1.model.vo.Item;
import efgMart1.repository.ItemDAO;

public class ItemService {
	//private ItemDAO idao = new ItemDAO();
	
	public ArrayList<Item> selectAllItemList() {
        Connection con = JDBCTemplate.getConnection();
        ArrayList<Item> selectAllItemList = new ItemDAO().selectAllItemList(con); // DAO에 Connection 전달하도록 수정 필요
        JDBCTemplate.close(con);
        return selectAllItemList;
    }
	
	public int insertItem(Item item) {
        Connection con = JDBCTemplate.getConnection();
        int result = new ItemDAO().insertItem(con, item);
        if (result > 0) JDBCTemplate.commit(con);
        else JDBCTemplate.rollback(con);
        JDBCTemplate.close(con);
        return result;
    }
	
	public int deleteItem(String itemId) {
        Connection con = JDBCTemplate.getConnection();
        int result = new ItemDAO().deleteItem(con, itemId);
        if (result > 0) JDBCTemplate.commit(con);
        else JDBCTemplate.rollback(con);
        JDBCTemplate.close(con);
        return result;
    }
	
	public int updateItem(Item item) {
        Connection con = JDBCTemplate.getConnection();
        int result = new ItemDAO().updateItem(con, item);
        if (result > 0) JDBCTemplate.commit(con);
        else JDBCTemplate.rollback(con);
        JDBCTemplate.close(con);
        return result;
    }
	
	public ArrayList<Item> searchedItemList(String itemId) {
        Connection con = JDBCTemplate.getConnection();
        Item searchedItem = new Item();
        searchedItem.setItemId(itemId);
        ArrayList<Item> list = new ItemDAO().searchedItemList(con, searchedItem);
        JDBCTemplate.close(con);
        return list;
    }
	
	public ArrayList<Item> selectItemListByBrand() {
        Connection con = JDBCTemplate.getConnection();
        ArrayList<Item> list = new ItemDAO().selectItemListByBrand(con);
        JDBCTemplate.close(con);
        return list;
    }
	
}














