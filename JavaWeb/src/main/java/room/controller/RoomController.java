package room.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import room.service.RoomService;

@WebServlet("/room")
public class RoomController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RoomService roomService = new RoomService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deleteId = req.getParameter("deleteId");
		if (deleteId != null) {
			Integer id = Integer.valueOf(deleteId);
			roomService.deleteRoom(id);
		}
		
		req.setAttribute("rooms", roomService.getRooms());
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/room.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer roomId = Integer.valueOf(req.getParameter("roomId"));
		String roomName = req.getParameter("roomName");
		
		roomService.addRoom(roomId, roomName);
		
		resp.sendRedirect(req.getContextPath() + "/room"); //resp.sendRedirect("/JavaWeb/room");
	}

}
