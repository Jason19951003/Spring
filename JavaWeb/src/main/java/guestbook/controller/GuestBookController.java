package guestbook.controller;

import java.io.IOException;
import java.util.List;

import guestbook.model.GuestBook;
import guestbook.service.GuestBookService;
import guestbook.service.GuestServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guestbook")
public class GuestBookController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GuestBookService service = new GuestServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deleteId = req.getParameter("deleteId");
		if (deleteId != null) {
			int id = Integer.parseInt(deleteId);
			service.removeById(id);
		}
		
		// 取得所有留言紀錄
		List<GuestBook> guestBooks = service.queryAll();
		
		req.setAttribute("guestBooks", guestBooks);
		// 跳轉到guestbook.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/guestbook.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String content = req.getParameter("content");

		boolean state = service.add(userName, content);
		req.setAttribute("state", state);

		// 跳轉到guestbook.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/guestbook_result.jsp");
		rd.forward(req, resp);
	}
}
