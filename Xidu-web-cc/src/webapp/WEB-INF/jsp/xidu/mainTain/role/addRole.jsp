<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Role
  </title>
	<script LANGUAGE="JavaScript">		
		function saveRole(){
			var selrole=$("#tableResults input:checkbox:checked");
        	if(selrole.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
			if(validateForm()){
				submitFormForAnji("addRole_form","${webcontext}/maintain/role/saverole",0);
//				$("#addRole_form").submit();
			}
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/maintain/role/init",null);
			//location.href="${webcontext}/maintain/role/init";
		}
		
		function parentcheck(button){
			if($(button).children("input:checkbox:checked").length>0){
				for(var i=0;i<$("dd[id='"+button.id+"']").length;i++){
					$("dd[id='"+button.id+"']:eq("+i+")").children("input:checkbox").attr("checked",true);
				}
			}else{
				for(var i=0;i<$("dd[id='"+button.id+"']").length;i++){
					$("dd[id='"+button.id+"']:eq("+i+")").children("input:checkbox").removeAttr("checked");
				}
			}
		}
		
		function childcheck(button){
			var index=0;
			for(var i=0;i<$("dd[id='"+button.id+"']").length;i++){
				if($("dd[id='"+button.id+"']:eq("+i+")").children("input:checkbox:checked").length>0){
					index++;
				}
			}
			//if(index==$("dd[id='"+button.id+"']").length){
			if(index>0){	
				$("#"+button.id).children("input:checkbox").attr("checked",true);
			}else if(index==0){
				$("#"+button.id).children("input:checkbox").removeAttr("checked");
			}
		}
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
	<%@include file="../../include/head.jsp"%>

	 <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">角色管理</a><span class="crumb-step">&gt;</span><span>新增角色</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form:form id="addRole_form" name ="Role_form" action="saverole" method="post" modelAttribute="roleDto">
				<form:hidden path="id"/>
                    <table class="insert-tab" width="100%" id="tableResults">
                        <tbody>
							<tr>
                                <th><i class="require-red">*</i>角色编码:</th>
                                <td>
                                    <form:input path="roleCode" class="common-text" size="50" title="Menu Code" />
                                </td>
                            </tr>
                            <tr>
                                <th>角色名称:</th>
                                <td><form:input path="roleName" class="common-text" size="50" title="Menu Name" /></td>
                            </tr>
							<tr>
                                <th>角色描述:</th>
                                <td><form:input path="roleDesc" class="common-text" size="50" title="Parent Menu Code"/></td>
                            </tr>
							<tr>
                                <th>角色菜单:</th>
                                <td>
									<div>
									<dl style="float:left">
										<core:forEach items="${menuList1 }" var="menulist">
											<dt>
											<input type="checkbox" id="check_${menulist.id }" name="menucheckbox" value="${menulist.id}"
											<core:forEach items="${isSelectMenu}" var="isselectmenu">
												<core:if test="${menulist.id==isselectmenu.id }">checked</core:if>
											</core:forEach>  
											/>
											<dt>${menulist.menuName}</dt>
											<core:forEach items="${menulist.list}" var="menu">
												<dd> 
												<input type="checkbox" id="check_${menu.id }" name="menucheckbox" value="${menu.id}"
												<core:forEach items="${isSelectMenu}" var="isselectmenu">
													<core:if test="${menu.id==isselectmenu.id }">checked</core:if>
												</core:forEach> 
												/>
												<dt>${menu.menuName}</dd>
											</core:forEach>
										</core:forEach>
									</dl>
									</div>
								</td>
                            </tr>						
                            <tr>
                                <th></th>
                                <td>
                                    <input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveRole();" value="提交" type="button">
                                    <input id="btnClose" class="btn btn6" onClick="backList();" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form:form>
            </div>
        </div>

    </div>

    </body>
</html>
