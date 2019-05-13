$(function() {
	showTotal();//显示合计
	
	/*
	  获取该页共有多少个table，因为table里的各种js操作的标签后缀为1,2....
	 */
	var length = $("table").length;
	
	for(var i=1;i<=length;i++) {
		// 给全选按钮添加点击事件
		$("#selectAll"+i).click(function() {
			var all = this
			var flag = all["checked"];//获取全选的状态
			var index = all["id"].substring(9, 10);
			setAll(flag,index);//让所有条目复选框与全选同步
			setJieSuanStyle(flag,index);//让结算按钮与全选同步
		});
		
		// 给条目复选框添加事件
		$(":checkbox[name=checkboxBtn"+i+"]").click(function() {
			var index = this["name"].substring(11, 12);
			var nodes = $(":checkbox[name=checkboxBtn"+index+"]");
			var selectedCount=0;
			for(var i=0;i<nodes.length;i++) {
				if(nodes[i]["checked"])
					selectedCount++;
			}
			//var selectedCount = $(":checkbox[name=checkboxBtn"+index+"][checked=true]").length;//被勾选复选框个数
			var allCount = $(":checkbox[name=checkboxBtn"+index+"]").length;//所有条目复选框个数
			var selectAll = $("#selectAll"+index);
			if(selectedCount == allCount) {//全选了
				selectAll[0]["checked"] = true;//勾选全选复选框
				setJieSuanStyle(true,index);//使结算按钮可用
			} else if(selectedCount == 0) {//全撤消了
				selectAll.attr('checked', false);//撤消全选复选框
				setJieSuanStyle(false,index);//使结算按钮不可用			
			} else {//未全选
				selectAll.attr('checked', false);//撤消全选复选框
				setJieSuanStyle(true,index);//使结算按钮可用
			}
			showTotal();//重新计算合计
		});
	}
	
	
	// 给jia、jian添加事件
	$(".jian").click(function() {
		var index = this["id"].substring(9,10);
		var cartItemId = $(this).attr("id").substring(0, 5);
		var quantity = Number($("#" + cartItemId + "Quantity"+index).val());
		if(quantity == 1) {
			if(confirm("您是否真要删除该条目？")) {
				alert("删除成功！");		
			}
		} else {
			sendUpdate(cartItemId, quantity-1,index);
		}
	});
	$(".jia").click(function() {
		var index = this["id"].substring(8,9);
		var cartItemId = $(this).attr("id").substring(0, 5);
		var quantity = Number($("#" + cartItemId + "Quantity"+index).val());
		sendUpdate(cartItemId, quantity+1,index);
	});
});

// 异步请求，修改数量
function sendUpdate(cartItemId, quantity,index) {
	/*
	 1. 通过cartItemId找到输入框元素
	 2. 通过cartItemId找到小计元素
	*/
	var input = $("#" + cartItemId + "Quantity"+index);
	var subtotal = $("#" + cartItemId + "Subtotal"+index);
	var currPrice = $("#" + cartItemId + "CurrPrice"+index);

	input.val(quantity);
	subtotal.text(round(currPrice.text() * quantity, 2));
	showTotal();
}

// 设置所有条目复选框
function setAll(flag,index) {
    var ele = 	$(":checkbox[name=checkboxBtn"+index+"]");
	for (var i = 0; i < ele.length; i++) {
		var checkEle = ele[i];
		checkEle["checked"]= flag ;
	}
	showTotal();//重新设置合计
}

// 设置结算按钮的样式
function setJieSuanStyle(flag,index) {
	if(flag) {// 有效状态
		$("#jiesuan"+index).removeClass("kill").addClass("jiesuan");//切换样式
		$("#jiesuan"+index).unbind("click");//撤消“点击无效”
	} else {// 无效状态
		$("#jiesuan"+index).removeClass("jiesuan").addClass("kill");//切换样式
		$("#jiesuan"+index).click(function() {//使其“点击无效”
			return false;
		});
	}
}

// 显示合计
function showTotal() {
	var total = 0;//创建total，准备累加
	/*
	  获取该页共有多少个table，因为table里的各种js操作的标签后缀为1,2....
	 */
	var length = $("table").length;
	for(var i=1;i<=length;i++) {
	    var name = "checkboxBtn"+i;
		/*
		1. 获取每个table中所有被勾选的复选框，遍历之
		*/
	    var allCheckBox = $("input[type='checkbox'][name='"+name+"']");
	    for (var j = 0; j < allCheckBox.length; j++) {
			/*
		 2. 通过复选框找到小计
		 */
			if (allCheckBox[j]["checked"] == true) {
				var subtotal = Number($("#" + allCheckBox[j].value + "Subtotal"+i).text());
				total += subtotal;
			}
		}
		/*
		3. 设置合计
		*/
		$("#total"+i).text(round(total, 2));
		total = 0;
	}

}
function getCartIds(index) {
	var allCheckBox = $("input[type='checkbox'][name=checkboxBtn"+index+"]");
	var id="";
	for (var j = 0; j < allCheckBox.length; j++) {
		 if (allCheckBox[j]["checked"] == true) 
			id+=allCheckBox[j]["id"]
	}
	$("#cartItemIds").value = id;
}
function deleteCarts(ele,index) {
	var allCheckBox = $("input[type='checkbox'][name=checkboxBtn"+index+"]");
	var id="";
	for (var j = 0; j < allCheckBox.length; j++) {
		 if (allCheckBox[j]["checked"] == true) 
			id+=allCheckBox[j]["id"]+",";
	}
	alert(ele.href );
	ele.href += "cartIds="+id;
	alert(ele.href );
}
