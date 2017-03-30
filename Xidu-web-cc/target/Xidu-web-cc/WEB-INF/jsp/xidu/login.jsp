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
	Welcome to MLV
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
<body style="background-color:#ffffff; margin-left:0px; margin-top:0px; margin-right:0px;" marginheight="0" marginwidth="0" text="#000000" >
    <form:form id="login_form" name ="login_form" action="login" method="post" modelAttribute="loginDto">
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
    


</form:form>

<script language="JavaScript"> 
             if (window != top) 
                       top.location.href = location.href; 
</script>
</body>

</html>