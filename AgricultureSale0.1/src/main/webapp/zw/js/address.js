window.onload = function() {
	var totalNode = document.getElementById("totalPrice");
	var tds = document.getElementsByTagName("td");
	var total = new Number(0);
	var sub;
	for(var i=0;i<tds.length;i++) {
	  if(tds[i].attributes['class'] === undefined) continue;
	  if(tds[i].attributes['class'].value =="subtotal" ) {
		  sub = new Number(tds[i].innerHTML);
		  total+=sub;
	  }
	}
	totalNode.innerHTML += total;
}

/*
 * 隐藏填写地址的div
 */
function hideThis() {
	var item = document.getElementById("addNewAddress");
	item.style.display = "none";
}
/*
 * 显示
 */
function show() {
	var item = document.getElementById("addNewAddress");
	item.style.display = "block";
	getAllProvince();
}

var xmlhttp;
/*
 * 创建xmlhttp实例
 */
function createXmlHttpReq() {
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
}
/*
 * 发送请求所有省份
 */
function getAllProvince() {
	var url = "address/getAllProvince.action"; 
	createXmlHttpReq();
	if (xmlhttp) {//create xmlhttp object successfully
		xmlhttp.onreadystatechange = setProvince;
		xmlhttp.open("get", url,true);
		xmlhttp.send();
	}
}
/*
 * 请求省份响应回调方法
 */
function setProvince() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    var res = xmlhttp.responseText;
	    var result = JSON.parse(res);
		setSelectItem(result,document.all.province);
	}
}

/*
 * 加到select中
 */
function setSelectItem(data,selectItem) {
	//清除之前的，换成现在的数据
	selectItem.length = 0;
	//遍历数组,将所有的元素添加到select上面去
	var nullOption = document.createElement("option");
	nullOption.innerHTML = "请选择";
	nullOption.value = "0";
	selectItem.appendChild(nullOption);
	for(var x=0; x<data.length; x++){
		var oOptNode = document.createElement("option");
		oOptNode.innerHTML = data[x].text;
		oOptNode.value = data[x].id;
		
		selectItem.appendChild(oOptNode);
	}
}

//发送请求，根据选择的provinceId获取对应的城市
function getCityByProId(){
	var id = document.all.province.value;
	//如果是空就返回
	if(id == null || id=="0") return;
	var url = "address/getCityByProvinceId.action?"; 
	createXmlHttpReq();
	if (xmlhttp) {//create xmlhttp object successfully
		xmlhttp.onreadystatechange = setCity;
		xmlhttp.open("post", url,true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("provinceId="+id);
	}	
}
/*
 * 将获得的城市json添加到city的select中去
 */
function setCity(){
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    var res = xmlhttp.responseText;
	    var result = JSON.parse(res);
		setSelectItem(result,document.all.city);
	}
}

//发送请求，根据选择的cityId获取对应的area
function getAreaByCityId(){
	var id = document.all.city.value;
	//如果是空就返回
	if(id == null || id=="0") return;
	var url = "address/getAreaByCityId.action?"; 
	createXmlHttpReq();
	if (xmlhttp) {//create xmlhttp object successfully
		xmlhttp.onreadystatechange = setArea;
		xmlhttp.open("post", url,true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("cityId="+id);
	}	
}
/*
 * 将获得的area市json添加到area的select中去
 */
function setArea(){
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    var res = xmlhttp.responseText;
	    var result = JSON.parse(res);
		setSelectItem(result,document.all.area);
	}
}

/*
 * 添加新地址
 */
function addAddress() {
		var indexPro = document.all.province.selectedIndex;
		var indexCity = document.all.city.selectedIndex;
		var indexArea = document.all.area.selectedIndex;
		var province = document.all.province[indexPro].text;
		var city = document.all.city[indexCity].text;
		var area = document.all.area[indexArea].text;
		var detailAddress = document.all.detailAddress.value;
		var consignee = document.all.consignee.value;
		var telephone = document.all.telephone.value;
		
		if(province == "请选择") {
			alert("请选择省份");
			return false;
		}
		if(city == "请选择") {
			alert("请选择城市");
			return false;
		}
		if(area == "请选择") {
			alert("请选择地区");
			return false;
		}
		
		if(detailAddress == "") {
			alert("请填写详细地址");
			return false;
		}
		
		if(consignee == "") {
			alert("请填写收货人");
			return false;
		}
		if(telephone == "") {
			alert("请填写收货人电话");
			return false;
		}
		
		var url = "address/add.action?"; 
		var data = "";
		
		data = "province="+province+"&city="+city+"&area="+area+"&detailAddress="+
        detailAddress+"&consignee="+consignee+"&telephone="+telephone;
		
		createXmlHttpReq();
		if (xmlhttp) {//create xmlhttp object successfully
			xmlhttp.onreadystatechange = callBack;
			xmlhttp.open("post", url,true);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlhttp.send(data);
		}
	
}

/*
 * 清空
 */

function clear() {
	 document.all.detailAddress.value = "";
	 document.all.consignee.value = "";
	 document.all.telephone.value = "";
	
}

function callBack() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		    var res = xmlhttp.responseText;
			addAddressToTable(res);
			alert("添加成功!");
			clear();
	}
}

/*
 * 将新地址添加到table里去
 */
function addAddressToTable(res){
	var result = JSON.parse(res);
	var table = document.getElementById("addressTable");
	var tr = document.createElement("tr");
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var inputRadio = document.createElement("input");
	var td3A = document.createElement("a");
	inputRadio.type = "radio";
	inputRadio.name = "address";
	td1.appendChild(inputRadio);
	td2.innerHTML = result.res;
	td3A.innerHTML = "删除";
	td3A.href = "javascript:void(0);";
	td3A.id = result.id;
	td3A.onclick = deleteAddress;
	td3.appendChild(td3A);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	table.appendChild(tr);
}
/*
 * 删除一个地址
 */
var deleteAddressId;

function deleteAddress() {
	if(confirm("确定删除选择的地址?") == false) return;
	var url = "address/deleteAddress.action";
	var data = "addressId="+this.id;
	deleteAddressId=this.id;
	createXmlHttpReq();
	if (xmlhttp) {//create xmlhttp object successfully
		xmlhttp.onreadystatechange = deleteResult;
		xmlhttp.open("post", url,true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(data);
	}
}
/*
 *删除结果
 */
function deleteResult(){
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    deleteHtmlAddress();
		alert("删除成功!");
    }
}
/*
 *删除页面上的地址
 */
function deleteHtmlAddress() {
	var nodeA = document.getElementById(deleteAddressId);
	var trOfA = nodeA.parentNode.parentNode;
	var table = trOfA.parentNode;
	table.removeChild(trOfA);
}

function submit() {
	var address = document.all.addressId;
	var cartIds = document.all.cartId;
	var addressId;
	var _cartIds="";
	for(var i=0;i<address.length;i++) {
		if(address[i].checked == true)
			addressId = address[i].value;
	}
	if(addressId === undefined) {
		alert("请选择收货地址");
		return;
	}
	if(cartIds.length === undefined) {
		_cartIds = cartIds.value;
	} else {
		for(var i=0;i<cartIds.length;i++)
			_cartIds += cartIds[i].value+",";
	}
	location="order/placeOrder.action?cartIds="+_cartIds+"&addressId="+addressId;
	return;
}

