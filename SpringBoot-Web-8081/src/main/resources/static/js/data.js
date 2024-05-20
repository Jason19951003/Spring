const $ = (id) => document.getElementById(id);

// 待 DOM 加載完成之後再執行
document.addEventListener("DOMContentLoaded", async () => {
	// 監聽 xxxbutton 是否有被點擊
	$('todayBtn').addEventListener("click", async (event) => {
		console.log('todayBtn 被按下');
		const response = await fetch('http://localhost:8081/data/today');
		const { state, message, data } = await response.json();
		console.log({ state, message, data });
		$('result').innerText = data;
	});

	$('lottoBtn').addEventListener("click", async (event) => {
		console.log('lottoBtn 被按下');
		const response = await fetch('http://localhost:8081/data/lotto');
		const { state, message, data } = await response.json();
		console.log({ state, message, data });
		$('result').innerText = data;
	});

	$('shipBtn').addEventListener("click", async (event) => {
		console.log('shipBtn 被按下');
		const response = await fetch('http://localhost:8081/data/ship');
		const { state, message, data } = await response.json();
		console.log({ state, message, data });
		$('result').innerHTML = `名稱: ${data.name}<br>
								 種類: ${data.type}<br>
								 長度: ${data.length}<br>
								 寬度: ${data.width}<br>`;
	});

	$('shipByIdBtn').addEventListener("click", async (event) => {
		console.log('shipByIdBtn 被按下');
		const id = window.prompt('請輸入 ID');
		const response = await fetch(`http://localhost:8081/data/ship/${id}`);
		const { state, message, data } = await response.json();
		console.log({ state, message, data });
		if (state) {
			$('result').innerHTML = `名稱: ${data.name}<br>
								 種類: ${data.type}<br>
								 長度: ${data.length}<br>
								 寬度: ${data.width}<br>`;
		} else {
			$('result').innerHTML = message;
		}
	});

	$('bmiBtn').addEventListener("click", async (event) => {
		const h = window.prompt('請輸入身高');
		const w = window.prompt('請輸入體重');
		const response = await fetch(`http://localhost:8081/data/bmi?h=${h}&w=${w}`);
		const { state, message, data } = await response.json();
		console.log({ state, message, data });
		if (state) {
			$('result').innerHTML = `身高: ${data.h}<br>
								 體重: ${data.w}<br>
								 BMI: ${data.bmi}<br>
								 Result: ${data.result}<br>`;
		} else {
			$('result').innerHTML = message;
		}
	});


	$('shipsBtn').addEventListener("click", async (event) => {
		console.log('shipsBtn 被按下');
		const response = await fetch('http://localhost:8081/data/ships');
		const { state, message, data } = await response.json();
		console.log({ state, message, data });
		const rows = data.map(obj =>
			`<tr>
					<td>${obj.name}</td>
					<td>${obj.type}</td>
					<td>${obj.length}</td>
					<td>${obj.width}</td>
				</tr>`).join('');
		// console.log(rows);
		$('result').innerHTML = `
			<table border='1'>
				<tr><td>名稱</td><td>種類</td><td>長度</td><td>寬度</td></tr>
				${rows}
			</table>`;

	});
});