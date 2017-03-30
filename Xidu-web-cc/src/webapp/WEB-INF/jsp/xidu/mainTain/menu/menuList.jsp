<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Menu
  </title>
	
	<script LANGUAGE="JavaScript">		
		function search(){
			submitFormForAnji("menu_form","${webcontext}/maintain/menu/queryMenu",0);
//			$("#menu_form").submit();
		}
		//add 
		function addMenu(){
			$("#menuId").val('');
			$("#menu_form").attr("action","addOrEditMenu");
			submitFormForAnji("menu_form","${webcontext}/maintain/menu/addOrEditMenu",0);
//			$("#menu_form").submit();
		}
		//edit
        function editMenu(){
			var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#menuId").val(selMenu.val());
        	$("#menu_form").attr("action","addOrEditMenu");
			submitFormForAnji("menu_form","${webcontext}/maintain/menu/addOrEditMenu",0);
//			$("#menu_form").submit();
        }
		//delete
        function deleteMenu(){
        	var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#menuId").val(selMenu.val());
	        	$("#menu_form").attr("action","deleteMenu");
				submitFormForAnji("menu_form","${webcontext}/maintain/menu/deleteMenu",0);
//		 		$("#menu_form").submit();
		    }
        }
		//detail
        function showMenuDetail(){
        	var selMenu=$("#tableResults input:radio:checked");
        	if(selMenu.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	window.open("${webcontext}/baseInfo/MenuInfo/showMenuDetail?MenuId="+selMenu.val());
        }
		
    </script>   
</head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
	<%@include file="../../include/head.jsp"%>
	<form:form  id="menu_form" name ="menu_form" action="queryMenu" method="post" modelAttribute="queryMenuDto">
	<form:hidden path="menuId" id="menuId"/>
	<div class="main-wrap">
		<div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">菜单管理</span></div>
        </div>
		<div class="search-wrap">
            <div class="search-content">
					
					<table class="search-tab">
						<tr align="center">
							<td width="130">菜单编码:</td>
							<td><form:input path="menuCode" cssClass="userInput" class="common-text" placeholder="菜单编码" /></td>
							
							<td width="130">菜单名称:</td>
							<td><form:input path="menuName" cssClass="userInput" class="common-text" placeholder="菜单名称" /></td>
							
							<td width="130">父级菜单:</td>
							<td><form:input path="parentMenuCode" cssClass="userInput" class="common-text" placeholder="父级菜单" /></td>

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
                        <a href="javascript:void(0)" onClick="addMenu();" name="btnAdd">新增</a>
                        <a id="btnReset" href="javascript:void(0)" onClick="editMenu();">修改</a>
                        <a id="btnReset" href="javascript:void(0)" onclick="deleteMenu();">删除</a>
                    </div>
                </div>
                <div class="result-content">
					<table id="tableResults" class="result-tab" width="100%">
							<tr><td>
							<ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults"
						   items="queryMenuDto.resultList"
						   action="${webcontext}/maintain/menu/queryMenu"
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
								<ec:column alias="选择">
									 <input type='radio' name='selId' sortable="false" value='${bo.id }'/>
								</ec:column>
								<ec:column property="menuCode" title="菜单编码"></ec:column>
								<ec:column property="menuName" title="菜单名称"></ec:column>
								<ec:column property="parentMenuCode" title="父级菜单"></ec:column>
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
</body>
</html>