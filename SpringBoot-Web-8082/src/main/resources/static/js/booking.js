document.addEventListener("DOMContentLoaded", () => {
	// 按兩下可以切換小月曆
	document.querySelectorAll('.date-text').forEach((span) => {
		span.addEventListener('dblclick', (event) => {
			span.style.display = 'none';
			//span.nextSibling.style.display = 'inline';
			const bookingId = event.target.dataset.bookingId;
			const input = document.querySelector(`.date-input[data-booking-id="${bookingId }"]`);
			input.style.display = 'inline';
		});
	});
	// 按右鍵可以還原成文字
	document.querySelectorAll('.date-input').forEach((input) => {
		// 監聽日期是否有被改變
		input.addEventListener('change', async (event) => {
			const bookingId = event.target.dataset.bookingId;
			const bookingDate = event.target.value;
			
			console.log(`bookingId=${bookingId}, bookingDate=${bookingDate}`);
			
			// 透過fetch 來變更資料庫的資訊
			const ch = await fetch('');
			ch.json();
		});
		
		input.addEventListener('contextmenu', (event) => {
			event.preventDefault();
			const bookingId = event.target.dataset.bookingId;
			const span = document.querySelector(`.date-text[data-booking-id="${bookingId }"]`);
			span.style.display = 'inline';
			input.style.display = 'none';
		})
	});
});