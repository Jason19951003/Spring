package guestbook.controller;

import java.io.IOException;

import guestbook.model.GuestBook;
import guestbook.service.GuestBookService;
import guestbook.service.GuestServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guestbook/update")
public class GuestBookUpdateController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GuestBookService service = new GuestServiceImpl();
	
	//指向修改頁面
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateId = req.getParameter("updateId");
		
		if (updateId == null) {
			return;
		}
		
		GuestBook guestBook = service.getById(Integer.parseInt(updateId));
		req.setAttribute("guestBook", guestBook);
		// 重導到修改頁面
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/guestbook_update.jsp");
		rd.forward(req, resp);
	}

	// 修改內容
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String userName = req.getParameter("userName");
		String content = req.getParameter("content");
		
		
		service.updateUserName(id, userName);
		service.updateContent(id, content);
		
		// 重導到首頁
		resp.sendRedirect("/JavaWeb/guestbook");
	}
}
