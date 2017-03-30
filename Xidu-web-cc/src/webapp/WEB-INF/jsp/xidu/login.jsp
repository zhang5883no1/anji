<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <meta http-equiv="Expires" CONTENT="0">
  <meta http-equiv="Cache-Control" CONTENT="no-cache">
  <meta http-equiv="Pragma" CONTENT="no-cache">
  
  <title>
	Welcome to ML
  </title>
	<script type="text/javascript">
		document.onkeydown=function(event) { 
			e = event ? event :(window.event ? window.event : null); 
		    if(e.keyCode==13){ 
	         	login();
		 	} 
	    } 
		
		function login(){
			
			if(document.getElementById("userCode").value==''){
				document.getElementById("errMsg").innerHTML='User name is null';
				return;
			}
			if(document.getElementById("passwd").value==''){
				document.getElementById("errMsg").innerHTML='Password is null';
				return;
			}
			document.getElementById("login_form").submit();
		}
		
  
	　　  //block browser back
	　　		javascript:window.history.forward(1);   

	</script>       
	<style type="text/css">
	        .style1
	        {
	            height: 6px;
	            width: 27px;
	        }
	        .style2
	        {
	            width: 27px;
	        }
	        .validSpan{
				color:red;	
			}
	    </style>
	     
</head>
<body style="padding: 0px;margin: 0px;" >

<div style="width: auto;min-height: 70px;line-height: 70px;border-bottom: medium solid #39A631;">
	<div>
		<span class="title_con" style='font-family:"微软雅黑";font-style: normal;font-size: 2em;font-weight:800 ;color: #39A631;padding-left: 1em;'>后台系统</span>
	</div>
</div>

<div style="width: auto;height:28em;text-align: center;background-image: url(img/xgxt_login_bg.jpg);background-size: 100% 100%;">
	<center>
		<form:form id="login_form" name ="login_form" action="login" method="post" modelAttribute="loginDto">

		<div style="position:relative; top:220px;left:80px; z-index: 10px;"><span id="errMsg" class="validSpan"><form:errors path="*"/></span></div>

		<div style="width: 35%;height: 20em;margin-top:4em;border: thin solid #8EC172;">
		<div style="background-color: #8EC172;width: auto;height: 3.5em;line-height: 3.5em;text-align: center;">
			<span style='font-family: "微软雅黑";font-size: 1.5em;font-weight: 800;color: #FFF;'>欢迎登录直播间后台系统</span>
		</div>
		<div class="con_panel">
			<div style="margin: 2em 0 1em 0;">
				<span style='font-family: "微软雅黑";font-size: 1em;font-weight: bold;color: #333;'>用户名：</span><form:input path="userCode" value="" placeholder="账号" style="width: 15em;padding: 0.5em 1em;border: 1px solid #bbb;" />
			</div>
			<div style="margin: 2em 0 1em 0;">
				<span style='font-family: "微软雅黑";font-size: 1em;font-weight: bold;color: #333;'>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><form:password path="passwd" style="width: 15em;padding: 0.5em 1em;border: 1px solid #bbb;" placeholder="密码"/>
			</div>
			<input type="submit" value="登    录" style='width: 8em;height: 2em;background-color: #62ab00;border-radius: 4px;border: 0px;color: #fff;font-family:"微软雅黑";font-size: 1em;font-weight: bold;'/>
		</div>
	</div>
	</form:form>
	</center>
</div>
<!--<form:form id="login_form" name ="login_form" action="login" method="post" modelAttribute="loginDto">
    <div style="width:100%;height:100%;border:0px solid red;text-align:center;padding-top:10px;background-color:#ffffff;">
    	<div style="position:relative; top:220px;left:80px; z-index: 10px;"><span id="errMsg" class="validSpan"><form:errors path="*"/></span></div>
        <table style="border:0px solid red;margin:auto;">
            	<tr>
                    <td style="height:554px; width:1022px; background:url(${imagepath}/login.png);">
                         <table style="border:0px solid red;margin:auto; width:1022px;" border="0" cellpadding="0" cellspacing="0">
                             	<tr>
                            	  <td style="width:215px;" valign="bottom"></td>
                                  <td>
                                 	 <table style="border:0px solid red;margin:auto; width:590px;" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                           <td style="height:16px;" valign="bottom">
                                            <table style="border:0px solid red;margin:auto; width:512px;" border="0" cellpadding="0" cellspacing="0">
                                        
                                                  <tr><td style="height:15px;" valign="bottom">&nbsp; </td></tr>
                                                  <tr>
                                                     <td style="width:220px;" valign="bottom"></td>
                                                     <td valign="bottom"> <form:input path="userCode" value=""  cssStyle="width:100px;"/></td>
                                                  </tr>
                                                  <tr>
                                                      <td style="width:400px; height:13px;" valign="bottom"></td>
                                                  </tr>
                                                  <tr>
	                                                 <td valign="bottom"></td>
	                                                 <td style="width:180px;" valign="bottom">
	                                                    	<form:password path="passwd"  cssStyle="width:100px;"/>
	                                                    	
                                                    </td>
                                                   </tr>
                                             </table>
                                           </td>
                                           <td style="width:200px;" valign="bottom">
                                              <table style="border:0px solid red;margin:auto; width:64px;" border="0" cellpadding="0" cellspacing="0">
                                               <tr><td class="style1" valign="bottom"></td></tr>
                                               <tr>
                                                <td class="style2" valign="bottom">
	                                               <span id="btnLogon" onClick="login();" style="display:inline-block;height:64px;width:60px;cursor:pointer"></span>
                                                </td>
                                               </tr>
                                              </table>
                                            </td>
                                            </tr>
                                      </table>
                                    </td>  
                                    <td style="width:215px;" valign="bottom"></td>
                          		</tr>
                          
                         		
                          </table>       
                       </td>                      
                    </tr>               
        </table>
    </div>
</form:form>-->

<script language="JavaScript"> 
	if (window != top)
		top.location.href = location.href; 
</script>
</body>

</html>