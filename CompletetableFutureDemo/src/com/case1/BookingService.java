package com.case1;

// 開發一個服務來預訂酒店與航班
// 我們希望這兩個任務可並行
// 當任務都完成後給用戶一個確認
public class BookingService {

	// 模擬預定酒店的操作
	public String bookHotel() throws InterruptedException {
		Thread.sleep(2000);
		return "Hotel booked!";
	}
	
	// 模擬預定酒店的操作
	public String bookFlight() throws InterruptedException {
		Thread.sleep(3000);
		return "Flight booked!";
	}
	
	// 同步預定酒店與航班
	public void bookTrip() throws InterruptedException {
		long start = System.currentTimeMillis();
		
		// 預定酒店
		String hotelBooking = bookHotel();
		System.out.println(hotelBooking); 
		
		// 預定航班
		String flightBooking = bookFlight();
		System.out.println(flightBooking); 
		
		long end = System.currentTimeMillis();
		System.out.printf("Total time: %d ms%n", (end - start));
	}
	
	public static void main(String[] args) throws InterruptedException {
		new BookingService().bookTrip();
	}
}
