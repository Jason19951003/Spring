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
		// console.log(state, message, data);
		//console.log(renderFn(data[0]));
		/*if(Arrays.isArray(data)) {
			$(containerId).innerHTML = data.map(renderFn).join('');
		} else {
			$(containerId).innerHTML = renderFn(data);
		}*/
		$(containerId).innerHTML = Array.isArray(data) ? data.map(renderFn).join('') : renderFn(data);
		$("user-button").textContent = "新增";
		$("user-form").reset();
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
// 加載表單選項(學歷, 興趣)
const loadFormOptions = async() => {
	// 加載學歷選項
	const educationOptions = await fetch('http://localhost:8080/SpringMVC/mvc/rest/user/educations');
	var {state, message, data} = await educationOptions.json();
	// console.log(data);
	// 將data逐筆放入下拉選單中
	data.forEach(education => {
		const opt = document.createElement('option');
		opt.value = education.id;
		opt.textContent = education.name;
		$('educationId').appendChild(opt);
	});
	
	// 加載興趣選項
	const interestsOption = await fetch('http://localhost:8080/SpringMVC/mvc/rest/user/interests');
	var {state, message, data} = await interestsOption.json();
	data.forEach(interest => {
		const opt = document.createElement('option');
		opt.value = interest.id;
		opt.textContent = interest.name;
		$('interestIds').appendChild(opt);
	});
	// console.log(data);
};

const handleFormSubmit = async(event) => {
	event.preventDefault();	// 停止表單的預設傳送行為, 改為自訂行為, 以下是自訂行為的邏輯
	
	const formData = {
		name: $('name').value,
        age: parseInt($('age').value),
        birth: $('birth').value,
        educationId: parseInt($('educationId').value),
        genderId: parseInt(document.querySelector('input[name="genderId"]:checked').value),
        interestIds: Array.from($('interestIds').selectedOptions).map(option => parseInt(option.value)),
        resume: $('resume').value
	};
	var flag = $('user-button').textContent == '修改';
	var url = flag ? 'http://localhost:8080/SpringMVC/mvc/rest/user/'+ $('id').value : 'http://localhost:8080/SpringMVC/mvc/rest/user';
	// console.log(url);
	var method = flag ? 'PUT' : 'POST';
	const response = await fetch(url, {
		method: method,
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
	});
	
	var {state, message, data} = await response.json();
	// console.log(message);
	
	// 重新資料渲染(fetch取資料+渲染)
	fetchAndRenderData('/mvc/rest/user', 'user-list-body', renderUser);
};


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
	// 加載表單選項
	loadFormOptions();
	// 監聽UserForm
	$('user-form').addEventListener("submit", handleFormSubmit);
});

const handleUpdateUser = async(id) => { 
	const fullurl = 'http://localhost:8080/SpringMVC/mvc/rest/user/' + id;
	const response = await fetch(fullurl, {method: 'GET'});
	const {state, message, data} = await response.json();
	$('id').value = data.id;
	$('name').value = data.name;
	$('age').value = data.age;
	$('birth').value = data.birth;
	$('resume').value = data.resume;
	$('educationId').value = data.educationId;
	const radios = document.getElementsByName("genderId");
	radios.forEach(radio => {
		if (radio.value == data.genderId) {
			radio.checked = true;
		}
	});
	const select = $("interestIds");
	for (var i = 0; i < select.options.length; i++) {
		select.options[i].selected = data.interestIds.includes(parseInt(select.options[i].value));
	}
	$("user-button").textContent = "修改";
};
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
		// console.log({state, message, data});
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