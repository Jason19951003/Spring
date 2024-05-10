package session05.dao;

import java.util.List;

import session05.bean.GuestBook;

public interface GuestBookDao {
	//新增
	int create(GuestBook guestBook);
	//單筆查詢
	GuestBook findById(Integer id);
	//多筆查詢
	List<GuestBook> findAll();
	//修改
	int update(GuestBook guestBook);
	//刪除
	int deleteById(Integer id);
}
