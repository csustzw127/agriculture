<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	 <link rel="stylesheet" type="text/css" href="<c:url value='/zw/css/user/regist.css'/>">
    <script type="text/javascript" src="<c:url value='/zw/js/jquery-1.7.2.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/zw/js/user/login.js'/>"></script>
  </head>
  
  <body>
	 <div id="divMain">
       <div id="divTitle">
         <span id="spanTitle">登录</span>
       </div>
       <div id="divBody">
         <form method="post" action="<c:url value='/user/Login.action'/>" id="loginForm">
         
          <table id="tableForm">
            <tr>
              <td class="tdText">用户名：</td>
              <td class="tdInput">
                  <input type="text" name="username" id="loginname" class="inputClass" value="${form.loginname }"></input>
              </td>
              <td class="tdError">
                  <label class="errorClass" id="loginnameError">${errors.loginname }</label>
              </td>
            </tr>
            <tr>
              <td class="tdText">登陆密码：</td>
              <td class="tdInput">
                  <input type="password" name="psw" id="loginpass" class="inputClass" value="${form.loginpass }"></input>
              </td>
              <td class="tdError"> 
                  <label class="errorClass" id="loginpassError">${errors.loginpass }</label>
              </td>
            </tr>
            <tr>
              <td class="tdText">图形验证码：</td>
              <td class="tdInput">
                  <input type="text" name="verifycode" id="verifyCode" class="inputClass" value="${errVcode }"></input>
              </td>
              <td class="tdError">
                  <label class="errorClass" id="verifyCodeError">${errors.verifyCode }</label>
              </td> 
            </tr>
            <tr>
              <td class="tdText"></td>
              <td class="tdInput">
                <div id="divVerifyCode">
                  <img id="imgVerifyCode" src="<c:url value='/verify/VerifyCode.action'/>">
                </div>
              </td>
              <td class="tdError">
                <label><a href="javascript:_hyz();">换一张</a></label>
              </td>
            </tr>
            <tr>
              <td class="tdText"></td>
              <td class="tdInput">
               <input type="image" src="<c:url value='/zw/image/login1.jpg'/>" id="submitBtn"></input>
              </td>
              <td class="tdError"></td>
            </tr>
          </table>
         </form>
       </div>
    </div>
  </body>
</html>
	