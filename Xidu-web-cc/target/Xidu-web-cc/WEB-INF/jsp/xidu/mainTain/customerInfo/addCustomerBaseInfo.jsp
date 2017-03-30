<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Customer Base Info
  </title>
	<script LANGUAGE="JavaScript">		
		function saveCUstomer(){
			submitFormForAnji("addCustomer_form","${webcontext}/mainTain/customerinfo/saveCustomer",0);
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/mainTain/customerinfo/init",null);
			//location.href="${webcontext}/mainTain/customerinfo/init";
		}
		
		//valid
		function valid(txtbox){
			$.ajax({
				method:"GET",
				url:"${webcontext}/mainTain/customerinfo/valid?code="+$(txtbox).val()+"&currTime="+(new Date()).getTime(),
				success:function(msg){
					if(msg!="false"){
						//alert("CustomerCode is not exist");
						$(txtbox).parent().children(".validSpan").remove();
						$(txtbox).parent().append("<span class='validSpan'>CustomerCode is exist</span>");
						$("#flag").val("1");
						//$("#btnSave").hide();
					}else{
						$(txtbox).parent().children(".validSpan").remove();
						$(txtbox).next().next().val(msg);
						$("#flag").val("0");
					}
				}
			});
		}
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
   <form:form id="addCustomer_form" name ="customer_form" action="saveCustomer" method="post" modelAttribute="customerDto">
     <form:hidden path="id"/>
     <form:hidden path="ip"/>
     <form:hidden path="usedVideoTime"/>
	<!-- BEGIN HEADER -->
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
	    <tr style="height:40px">
	        <td width="80%">
	            <img id="header_imgMatrixLogo" src="${imagepath}/site_logo.gif" align="middle" style="border-width:0px;" />
	        </td>
	        <td>
	            <table cellpadding="0" cellspacing="0" width="100%" border="0" style="padding: 0 4 0 4;">
	                <tr >
	                    <td align="right" nowrap="nowrap">
	                        <span id="header_upLinks">                            
	                            <table cellpadding="4" cellspacing="0" border="0"><tr>
	                                <td><a id="header_lnkHelp" disabled="disabled" class="HeaderLinkButton" onMouseOver="this.classname=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.classname=&#39;HeaderLinkButton&#39;;">Help</a></td>
	                                <td class="HeaderLinkButton">|</td>
	                                <td><span id="header_lblClose" class="HeaderLinkButton" onMouseOver="this.className=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.className=&#39;HeaderLinkButton&#39;;" onClick="ClosePage();">Close Dialog</span></td>
			                        <td style="width:20px;text-align:center" onClick="AbortRequestHandler();"><img id="imgUpdating" alt="" src="${imagepath}/updating.gif" style="border-width:0px;visibility:hidden;cursor:hand;" /></td>
	                            </tr></table>
	                        </span>
	                    </td>
	                </tr>
	            </table>
	        </td>
	    </tr>
	    <tr style="background-image:url(${imagepath}/menu_bar.png);background-repeat:repeat-x;height:10px">
	        <td colspan="2"></td>
	    </tr>
	</table>
     <!-- END HEADER -->

			<table cellSpacing="1" cellPadding="1" width="100%">
				<tr width="100%">
					<td><span id="lblheader" class="PageTitle">客户基本信息</span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30">
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">详情</legend>
							        <table cellspacing="1" cellpadding="1" width="100%" >
								        <tbody><tr width="100%">
									        <td align="right" nowrap=""><span class="LabelBold" id="lblDimType">用户名:</span></td>
									        <td nowrap="">
									             <form:input path="userName" cssStyle="width:190px;" title="用户名" cssClass="RequiredField userInput" maxlength="100" />
											 </td>
									        <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">昵称:</span></td>
									        <td nowrap="">
									        	<form:input path="nickName" cssStyle="width:190px;" cssClass="userInput " maxlength="15"/>
									        </td>
								        </tr>
								        <tr>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblLabel">性别:</span></td>
									        <td nowrap="">
									             <form:select path="sex" cssClass="userInput ">
									        		<form:option value="man">男</form:option>
									        		<form:option value="woman">女</form:option>
									        	</form:select>
									        </td>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblWidth">手机号码:</span></td>
									        <td nowrap="">
									        	<form:input path="mobile" cssStyle="width:190px;" cssClass="userInput " maxlength="100"/>
									        </td>
								        </tr>
								          <tr>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblMultiplier"> qq:</span></td>
									        <td nowrap="">
									            <form:input path="qq" cssStyle="width:190px;" cssClass="userInput " maxlength="100"/>
									          </td>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">聊天间隔:</span></td>
									        <td nowrap="">
									            <form:input path="chatTime" cssStyle="width:190px;" cssClass="userInput " maxlength="100"/>
									         </td>
								        </tr>
								        
								        <tr>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblMultiplier"> 可观看视频时间:</span></td>
									        <td nowrap="">
									            <form:input path="videoTime" cssStyle="width:190px;" cssClass="userInput " maxlength="100"/>秒
									          </td>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblHeight">禁言:</span></td>
									        <td nowrap="">
									        	<form:select path="isChat" cssClass="userInput ">
									        		<form:option value="0">正常</form:option>
									        		<form:option value="1">禁言</form:option>
									        	</form:select>
									        </td>
								        </tr>
								        <tr>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">所属业务员:</span></td>
									        <td nowrap="">
									        	 <form:input path="userId" cssStyle="width:190px;" cssClass="userInput" maxlength="100" readonly="readonly"/>
									        </td>
									         <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">状态:</span></td>
									        <td nowrap="">
									        	<form:select path="status" cssClass="userInput ">
									        		<form:option value="normal">正常</form:option>
									        		<form:option value="block">锁定</form:option>
									        	</form:select>
									        </td>
								        </tr>
                                        <tr>
									        <td align="right" nowrap="email"><span class="LabelBold" id="lblStackQty">头像:</span></td>
									        <td nowrap="">
									        	 <form:input path="faceImg" cssStyle="width:190px;" cssClass="userInput" maxlength="100"/>
									        </td>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">会员类型:</span></td>
									        <td nowrap="">
									        	 <form:select path="type_id" cssClass="userInput ">
									        	 	<form:option value="0">游客</form:option>
									        		<form:option value="1">试用会员</form:option>
									        		<form:option value="2">VIP</form:option>
									        		
									        		<form:option value="6">钻石VIP</form:option>
									        		<form:option value="7">至尊VIP</form:option>
									        	</form:select>
									        </td>
								        </tr>	
								        
                                        <tr>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">交易状态:</span></td>
									        <td nowrap="">
									        	<form:select path="business" cssClass="userInput ">
									        		<form:option value="0">未成交</form:option>
									        		<form:option value="1">已成交</form:option>
									        	</form:select>
									        </td>
									         <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">开户状态:</span></td>
									        <td nowrap="">
									        	<form:select path="openAccount" cssClass="userInput ">
									        		<form:option value="0">未开户</form:option>
									        		<form:option value="1">开户未签约</form:option>
									        		<form:option value="2">开户已签约</form:option>
									        		<form:option value="3">开户入金</form:option>
									        	</form:select>
									        </td>
								        </tr>
								        <tr>
                                            <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">开户金额:</span></td>
									        <td nowrap="">
									        	 <form:input path="accountPrice" cssStyle="width:190px;" cssClass="userInput" maxlength="100"/>
									        </td>
									        <td align="right" nowrap=""><span class="LabelBold" id="lblStackQty">密码:</span></td>
									        <td nowrap="">
									        	 <form:input path="pwd" cssStyle="width:190px;" cssClass="userInput" maxlength="100"/>
									        </td>
								        </tr>
							        </tbody></table>
						        </fieldset>
	                </td>
	            </tr>
	           
	        </table>
            <table style="width:100%;">
              <tr>
					        <td nowrap=""><span id="imgRequiredField"><img style="border-width:0px;" src="${imagepath}/required.gif" id="imgRequired">&nbsp;<span class="Label">Required Field</span>&nbsp;</span></td>
					        <td align="right">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnDone" onClick="saveCUstomer();" value="Save" name="btnDone">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnClose" onClick="backList();" value="Return" name="btnReturn"/>
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>
	<script LANGUAGE="JavaScript">	
  		$(document).ready(
  				function(){
  					$("#business").change(function(){
  						if($("#business").val()=="1"){
  							$("#openAccount").val("3");
  							$("#openAccount").attr("disabled","disabled");
  						}else{
  							$("#openAccount").removeAttr("disabled");
  						}
  					});
  					
  				}
  		);
  	</script> 
		</form:form>
    </body>
</html>
