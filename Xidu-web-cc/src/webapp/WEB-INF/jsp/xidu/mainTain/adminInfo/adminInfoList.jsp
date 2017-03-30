<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xidu.framework.usermgnt.constant.WorkbenchConstant"%>
<%@page import="com.xidu.framework.usermgnt.dto.SessionUserDto"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	管理员管理
  </title>
	<script LANGUAGE="JavaScript">		
	var id="";
		function search(){
			submitFormForAnji("custom_form","${webcontext}/mainTain/adminInfo/queryCustom",0);
		}
		
		//add 
		function addCustomer(){
			$("#id").val("");
			$("#role_form").attr("action","addOrEditCustom");
			submitFormForAnji("custom_form","${webcontext}/mainTain/adminInfo/addOrEditCustom",0);
//			$("#role_form").submit();
		}
		//edit
        function editCustomer(){
			var selrole=$("#tableResults input:radio:checked");
        	if(selrole.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#id").val(selrole.val());
        	$("#role_form").attr("action","addOrEditCustom");
			submitFormForAnji("custom_form","${webcontext}/mainTain/adminInfo/addOrEditCustom",0);
//			$("#role_form").submit();
        }
		//delete
        function deleteCustomer(){
        	var selrole=$("#tableResults input:radio:checked");
        	if(selrole.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#id").val(selrole.val());
	        	$("#role_form").attr("action","deleteCustom");
				submitFormForAnji("custom_form","${webcontext}/mainTain/adminInfo/deleteCustom",0);
//		 		$("#role_form").submit();
		    }
        }
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 <%SessionUserDto dto=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)); %>
  <%@include file="../../include/head.jsp"%>
   <form:form id="custom_form" name ="custom_form" action="queryCustom" method="post" modelAttribute="querycustomerBasicInfoDto">
 	<form:hidden path="id" id="id"/>
	<div class="main-wrap">
		<div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">客户管理</span></div>
        </div>
		<div class="search-wrap">
            <div class="search-content">
					
					<table class="search-tab">
					<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130">昵称:</td>
							<td width="130">
								<form:input path="nickName" cssClass="userInput" class="common-text" placeholder="昵称" />
							</td>
							<td width="130">状态:</td>
							<td width="130">
								<form:select path="status" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<form:option value="normal">正常</form:option>
									<form:option value="block">锁定</form:option>
								</form:select>
							</td>
							<td width="130">等级:</td>
							<td width="130">
								<form:select path="level" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<form:option value="100">巡官</form:option>
									<form:option value="99">管理员</form:option>
									<form:option value="91">会员</form:option>
									<form:option value="92">VIP</form:option>
									<form:option value="93">铂金</form:option>
									<form:option value="94">钻石</form:option>
									<form:option value="95">皇冠</form:option>
									<form:option value="96">金冠</form:option>
								</form:select>
							</td>
						<tr>
						<tr align="center">
							<td width="130">房间:</td>
							<td width="130">
								<form:select path="roomId" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<core:forEach items="${querycustomerBasicInfoDto.rooms}" var="roomlist">
										<form:option value="${roomlist.id }">${roomlist.roomName }</form:option>
									</core:forEach>
								</form:select>
							</td>
						<tr>
						</table>
						<td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">返回行数:</span>
								<form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;"/>
								<input name="btnSearch" value="查询" onClick="search();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
								<input name="btnSearch" value="清空" onClick="clearAll();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
							</td>

						</tr>
					</table>
            </div>
        </div>
		<div class="result-wrap">
				<div class="result-title">
                    <div class="result-list">
                        <a href="javascript:void(0)" onClick="addCustomer();" name="btnAdd">新增</a>
                        <a id="btnReset" href="javascript:void(0)" onClick="editCustomer();">修改</a>
                        <a id="btnReset" href="javascript:void(0)" onclick="deleteCustomer();">删除</a>
                    </div>
                </div>
                <div class="result-content">
					<table class="result-tab" width="100%" id="tableResults">
							<tr><td>
							<ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" form="custom_form"
					   items="querycustomerBasicInfoDto.resultList"
					   action="${webcontext}/mainTain/customerinfo/queryCustom"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
					   sortable="true"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>
						 	<ec:column alias="选择" sortable="false">
						 		 <input type='radio' name='selId' value='${bo.id }'/>
						 	</ec:column>
						    <ec:column property="userName" title="用户名"/>
						    <ec:column property="nickName" title="昵称"/>
						    <ec:column property="createDate" title="注册时间"/>
						    <ec:column alias="类型" sortable="false">
					        	<select class="userInput" data-type="level"> 
					        		<option value="99" <core:if test="${bo.level=='99'}">selected</core:if>>业务员</option>
					        		<option value="100" <core:if test="${bo.level=='100'}">selected</core:if>>巡官</option>
					        		
					        		<option value="91" <core:if test="${bo.level=='91'}">selected</core:if>>会员</option>
					        		<option value="92" <core:if test="${bo.level=='92'}">selected</core:if>>VIP</option>
					        		<option value="93" <core:if test="${bo.level=='93'}">selected</core:if>>铂金</option>
					        		<option value="94" <core:if test="${bo.level=='94'}">selected</core:if>>钻石</option>
					        		<option value="95" <core:if test="${bo.level=='95'}">selected</core:if>>皇冠</option>
					        		<option value="96" <core:if test="${bo.level=='96'}">selected</core:if>>金冠</option>
					        	</select>
						    </ec:column>
						    <ec:column alias="状态" sortable="false">
						    	<select class="userInput" data-type="status"> 
					        		<option value="normal" <core:if test="${bo.status=='normal'}">selected</core:if>>正常</option>
					        		<option value="block" <core:if test="${bo.status=='block'}">selected</core:if>>锁定</option>
					        	</select>
						    </ec:column>
						     <ec:column alias="房间" sortable="false">
					        		<core:if test="${bo.roomNo=='1'}">凯旋直播间</core:if>
					        		<core:if test="${bo.roomNo=='2'}">2房</core:if>
					        		<core:if test="${bo.roomNo=='3'}">3房</core:if>
					        		<core:if test="${bo.roomNo=='4'}">4房</core:if>
						    </ec:column>
						 </ec:row>
					</ec:table>
					</td></tr>
							
					</table>
					<div class="list-page">
					<%@include file="../../include/page.jsp"%>
					</div>
				</div>
		</div>
	</form:form>
	</div>
	
  <span id="PageMessageUpdatePanel"></span> </td>
      </tr>
    </tbody>
  </table>
  </span> 
 
 <div id="iframe_div" style="display:none">
	 <iframe src="#"></iframe>
 </div>

<script LANGUAGE="JavaScript">	
  		$(document).ready(
  			function(){
  				$("#tableResults select").change(function(){
  					$.ajax({
  						method:"GET",
  						url:"${webcontext}/mainTain/adminInfo/updateInfo?id="+$(this).parent().parent().find("input:radio").val()+"&name="+$(this).attr("data-type")+"&value="+$(this).val()+"&currTime="+(new Date()).getTime(),
  						success:function(msg){
  							
  						}
  					});
  				});
  			}
  		);
  </script> 
</body>
</html>