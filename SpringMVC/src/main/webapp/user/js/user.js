const $ = (id) => document.getElementById(id);
// 定義一個非同步函數來加載HTML
const loadHTML = async(url, contianerId) => {
	const fullurl = 'http://localhost:8080/SpringMVC' + url;
	try {
		const response = await fetch(fullurl); // 等待fetch 請求完成
		const data = await response.text(); // 等待回應本文內容
		$(contianerId).innerHTML = data; // 將資料加入到指定容器中
	} catch(e) {
		console.error(e);
	}
};

const fetchAndRenderData = async(url, containerId, renderFn) => {
	const fullurl = 'http://localhost:8080/SpringMVC' + url;
	try {
		const response = await fetch(fullurl); // 等待fetch 請求完成
		const {state, message, data} = await response.json(); // 等待回應本文內容
		//console.log(state, message, data);
		//console.log(renderFn(data[0]));
		/*if(Arrays.isArray(data)) {
			$(containerId).innerHTML = data.map(renderFn).join('');
		} else {
			$(containerId).innerHTML = renderFn(data);
		}*/
		$(containerId).innerHTML = Array.isArray(data) ? data.map(renderFn).join('') : renderFn(data);
	} catch(e) {
		console.error(e);
	}
};

// 渲染 user 資料配置
const renderUser = ({id, name, gender, age, birth, education,interestNames, resume}) => `
	<tr>
		<td>${id}</td><td>${name}</td><td>${gender.name}</td><td>${age}</td><td>${birth}</td>
		<td>${education.name}</td><td>${interestNames}</td><td>${resume}</td>
		<td title="修改">
			<span class="button-update pure-button update-user-button" data-id=${id}>修改</span
		</td>
		<td title="刪除">
			<span class="button-delete pure-button delete-user-button" data-id=${id}>刪除</span>
		</td>
	</tr>
`;

// 待 DOM 加載完成之後再執行
document.addEventListener("DOMContentLoaded", async() => {
	// 加上await 關鍵字等待 loadHTML 函數完成才會進行下一個程序
	await loadHTML('/user/user-form.html', 'user-form-container');
	await loadHTML('/user/user-list.html', 'user-list-container');
	
	// 資料渲染(fetch取資料+渲染)
	fetchAndRenderData('/mvc/rest/user', 'user-list-body', renderUser);
	// 監聽UserList 是否有被點擊
	$('user-list-table').addEventListener("click", async(event) => {
		// 處理事件
		await handleEvent(event, 'update-user-button', handleUpdateUser);
		await handleEvent(event, 'delete-user-button', handleDeleteUser);
	});
});

const handleUpdateUser = (id) => { console.log('按下修改' + id) };
const handleDeleteUser = async(id) => {	
	const result = await Swal.fire({
		title: '確定要刪除嗎?',
		text: '刪除後無法恢復',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: '是的, 刪除它',
		cancelButtonText: '取消'
	});
	
	if(result.isConfirmed) {
		const fullurl = 'http://localhost:8080/SpringMVC/mvc/rest/user/' + id;	
		const response = await fetch(fullurl, {method : 'DELETE'}); // 等待fetch 請求完成
		const {state, message, data} = await response.json(); // 等待回應本文內容
		console.log({state, message, data});
		fetchAndRenderData('/mvc/rest/user', 'user-list-body', renderUser);
	}
};

const handleEvent = async(event, className, callback) => {
	if(!event.target.classList.contains(className)) {
		return;
	}
	const id = event.target.getAttribute('data-id');
	callback(id);
};