function Search () {
	this.box = document.querySelector('#datails');
	this.inputText = '';
	this.inputBtn = document.querySelector('#btn');
	this.totalList = [];
	this.filterList = [];
	this.render();			//调用render
}

// 方法：render
Search.prototype.render = function () {
	this.setTplFilter();	//注册模板引擎过滤器
	this.getListData();		//获取数据
	this.filterListData();	//点击搜索根据输入框内容对数据进行过滤并渲染
	this.clickButton();		//注册按钮点击事件
	this.renderData();		//渲染数据
}

// 方法：获取数据
Search.prototype.getListData = function () {
	var _this = this;
	var obj = new Object();
	obj.ckuid = sessionStorage.getItem('ckuid');
	obj.cksid = sessionStorage.getItem('cksid');
	obj.m_type = '4';
	loadShow();
	$.ajax({
		url: http + "listMessage1Bygroup",
		type: "post",
		contentType: "application/json",
		headers: {
			'Content-type': 'application/json;charset=UTF-8'
		},
		data: JSON.stringify(obj),
		cache: false,
		async: false,
		success: function (data) {
			removeload();

			// 讲数据添加到this.totalList中
			data.object.forEach(function (item) {
				item.list.forEach(function (item2, index2) {
					_this.totalList.push(item2);
				})
			})
		}
	});
}

// 方法：搜索按钮点击事件
Search.prototype.clickButton = function () {
	var _this = this;
	
	_this.inputBtn.onclick = function () {	// 点击搜索按钮
		_this.filterListData();	//点击搜索根据输入框内容对数据进行过滤并渲染	
	}
}

// 方法：根据输入框内容筛选数据
Search.prototype.filterListData = function () {
	var _this = this;
	
	// 将filterList置空
	_this.filterList = [];
	
	// 获取输入框内容，并同步到数据中
	_this.inputText = document.querySelector('#input').value.trim();
	
	// 如果输入框去空格为空，则return
	if(!_this.inputText) return;
	
	// 如果输入框内容不为空，则筛选
	_this.totalList.forEach(function (item) {
		if(item.m_title.indexOf(_this.inputText) >= 0){
			_this.filterList.push(item);
		}
	}) 
	
	if(!_this.filterList.length){
		this.box.innerHTML = '<div style="text-align: center;font-size:16px;">没有符合搜索条件的结果...</div>';
		return;
	}
	
	//渲染数据
	_this.renderData();		
	
}

// 方法: 注册模板引擎过滤器
Search.prototype.setTplFilter = function () {
	// 过滤日期
	template.defaults.imports.dateFormat = function(value){
		var time = new Date(value * 1000);
		var n = time.getFullYear();
		var y = time.getMonth() + 1;
		var r = time.getDate();
		var h = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
		var m = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
		var s = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();
		var result = n + '年' + y + '月' + r + '日';
		return result;
	};
	// 过滤title
	template.defaults.imports.titleFormat = value => value = value.length > 25 ? value.substr(0, 25) + '...' : value;
	// 过滤content
	template.defaults.imports.contentFormat = value => value = value.length > 50 ? value.substr(0, 50) + '...' : value;
}

// 方法：使用模板引擎渲染数据(参数:数据，HTML选择器，模板ID)
Search.prototype.renderData = function () {
	var _this = this;
	var html=template('tpl-search', {list: _this.filterList});
	_this.box.innerHTML=html;
}

var search = new Search();