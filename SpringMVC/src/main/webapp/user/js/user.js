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
		if(Arrays.isArray(data)) {
			$(containerId).innerHTML = data.map(renderFn).join('');
		} else {
			$(containerId).innerHTML = renderFn(data);
		}
		
	} catch(e) {
		console.error(e);
	}
};

// 渲染 user 資料配置
const renderUser = ({id, name, gender, age, birth, education,interestNames, resume}) => `
	<tr>
		<td>${id}</td><td>${name}</td><td>${gender.name}</td><td>${age}</td><td>${birth}</td>
		<td>${education.name}</td><td>${interestNames}</td><td>${resume}</td>
		<td>修改</td>
		<td>刪除</td>
	</tr>
`;

// 待 DOM 加載完成之後再執行
document.addEventListener("DOMContentLoaded", async() => {
	// 加上await 關鍵字等待 loadHTML 函數完成才會進行下一個程序
	await loadHTML('/user/user-form.html', 'user-form-container');
	await loadHTML('/user/user-list.html', 'user-list-container');
	
	// 資料渲染(fetch取資料+渲染)
	fetchAndRenderData('/mvc/rest/user', 'user-list-body', renderUser);	
});