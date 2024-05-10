package room.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import room.service.RoomService;

@WebServlet("/room/update")
public class RoomUpdateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RoomService roomService = new RoomService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer roomId = Integer.parseInt(req.getParameter("updateId"));
		
		req.setAttribute("room", roomService.getRoom(roomId));
		
		req.getRequestDispatcher("/WEB-INF/jsp/room_update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer roomId = Integer.parseInt(req.getParameter("roomId"));
		String roomName = req.getParameter("roomName");
		roomService.updateRoom(roomId, roomName);
		
		resp.sendRedirect(req.getContextPath() + "/room");
	}
}
