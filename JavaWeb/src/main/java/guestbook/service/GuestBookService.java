package guestbook.service;

import java.util.List;

import guestbook.model.GuestBook;

public interface GuestBookService {
	//新增
	boolean add(String userName, String content);
	//單筆查詢
	GuestBook getById(Integer id);
	//多筆查詢
	List<GuestBook> queryAll();
	//修改使用者名稱
	boolean updateUserName(Integer id,String userName);
	//修改留言內容
	boolean updateContent(Integer id, String content);
	//刪除
	boolean removeById(Integer id);
}
