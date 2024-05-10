package session05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import session05.bean.GuestBook;
import session05.dao.GuestBookDao;

@Service
public class GuestBookServiceImpl implements GuestBookService {
		
	@Autowired
	//@Qualifier("gbDao")
	private GuestBookDao guestBookDao;
	
	@Override
	public boolean add(String userName, String content) {
		GuestBook guestBook = new GuestBook(userName, content);
		return guestBookDao.create(guestBook) == 1;
	}

	@Override
	public GuestBook getById(Integer id) {
		return guestBookDao.findById(id);
	}

	@Override
	public List<GuestBook> queryAll() {		
		return guestBookDao.findAll();
	}

	@Override
	public boolean updateUserName(Integer id, String userName) {
		GuestBook guestBook = getById(id);
		if (guestBook == null)
			return false;
		guestBook.setUserName(userName);
		return guestBookDao.update(guestBook) == 1;
	}

	@Override
	public boolean updateContent(Integer id, String content) {
		GuestBook guestBook = getById(id);
		if (guestBook == null)
			return false;
		guestBook.setContent(content);
		return guestBookDao.update(guestBook) == 1;
	}

	@Override
	public boolean removeById(Integer id) {
		return guestBookDao.deleteById(id) == 1;
	}

}
