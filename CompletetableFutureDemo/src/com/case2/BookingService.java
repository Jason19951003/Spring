package com.case2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 開發一個服務來預訂酒店與航班
// 我們希望這兩個任務可並行
// 當任務都完成後給用戶一個確認
// 使用CompletableFuture 非同步來調用來執行任務
public class BookingService {
	
	// 非同步模擬預定酒店的操作
	public CompletableFuture<String> bookHotel() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("酒店預訂完成");
			return "Hotel booked";
		});
	}
	
	// 非同步模擬預定酒店的操作
	public CompletableFuture<String> bookFilght() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("航班預定完成");
			return "Flight booked";
		});
	}
	
	// 非同步預定酒店與航班
	public void bookTrip() {
		long start = System.currentTimeMillis();
		
		CompletableFuture<String> hotelFuture = bookHotel(); // 開始預定酒店
		CompletableFuture<String> flightFuture = bookFilght(); // 開始預定航班
		
		// 等待兩個預定都操作完成
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(hotelFuture, flightFuture);
		
		// 當兩個操作都完成後, 執行
		combinedFuture.thenRun(() -> {
			try {
				System.out.println(hotelFuture.get());
				System.out.println(flightFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
			long end = System.currentTimeMillis();
			System.out.printf("Total time: %d ms %n", (end-start));
		}).join();
	}
	
	public static void main(String[] args) throws InterruptedException {
		new BookingService().bookTrip();
	}
}