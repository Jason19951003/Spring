package room.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import room.service.BookingRoomService;

@WebServlet("/bookingroom/update")
public class BookingRoomUpdateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingRoomService bookingRoomService = new BookingRoomService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer bookingroomId = Integer.parseInt(req.getParameter("updateId"));
		
		req.setAttribute("bookingroom", bookingRoomService.getBookingRoom(bookingroomId));
		
		req.getRequestDispatcher("/WEB-INF/jsp/bookingroom_update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer bookingroomId = Integer.parseInt(req.getParameter("bookingRoomId"));
		Integer roomId = Integer.parseInt(req.getParameter("roomId"));
		Integer userId = Integer.parseInt(req.getParameter("userId"));
		String checkinDate = req.getParameter("checkinDate");
		bookingRoomService.updateBookingRoom(bookingroomId, roomId, userId, checkinDate);
		
		resp.sendRedirect(req.getContextPath() + "/bookingroom");
	}
}
