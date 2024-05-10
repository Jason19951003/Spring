package guestbook.service;

import java.util.List;

import guestbook.dao.GuestBookDao;
import guestbook.dao.GuestBookDaoMySQL;
import guestbook.model.GuestBook;

public class GuestServiceImpl implements GuestBookService {

	private GuestBookDao dao = new GuestBookDaoMySQL();
	
	@Override
	public boolean add(String userName, String content) {
		GuestBook gb = new GuestBook(userName, content);
		int rowCount = dao.create(gb);
		return rowCount == 1;
	}

	@Override
	public GuestBook getById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<GuestBook> queryAll() {
		return dao.findAll();
	}

	@Override
	public boolean updateUserName(Integer id, String userName) {
		GuestBook gb = dao.findById(id);
		if (gb == null) return false;
		gb.setUserName(userName);
		int rowCount = dao.update(gb);
		return rowCount == 1;
	}

	@Override
	public boolean updateContent(Integer id, String content) {
		GuestBook gb = dao.findById(id);
		if (gb == null) return false;
		gb.setContent(content);
		int rowCount = dao.update(gb);
		return rowCount == 1;
	}

	@Override
	public boolean removeById(Integer id) {
		int rowCount = dao.deleteById(id);
		return rowCount == 1;
	}

}
