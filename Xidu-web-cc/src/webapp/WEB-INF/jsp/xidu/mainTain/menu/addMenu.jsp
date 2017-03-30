<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	Menu Info
  </title>
	<script LANGUAGE="JavaScript">		
		function saveMenu(){
			submitFormForAnji("addMenu_form","${webcontext}/maintain/menu/saveMenu",null);
			
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/maintain/menu/init",null);
			//location.href="${webcontext}/maintain/menu/init";
		}
		
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
     <%@include file="../../include/head.jsp"%>

	 <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">菜单管理</a><span class="crumb-step">&gt;</span><span>新增菜单</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form:form id="addMenu_form" name ="addMenu_form" action="saveMenu" method="post" modelAttribute="menuDto">
					<form:hidden path="id"/>
                    <table class="insert-tab" width="100%" id="tableResults">
                        <tbody>
							<tr>
                                <th><i class="require-red">*</i>菜单编码:</th>
                                <td>
                                    <form:input path="menuCode" class="common-text" size="50" title="Menu Code" />
                                </td>
                            </tr>
                            <tr>
                                <th>菜单名称:</th>
                                <td><form:input path="menuName" class="common-text" size="50" title="Menu Name" /></td>
                            </tr>
							<tr>
                                <th>父级菜单:</th>
                                <td><form:input path="parentMenuCode" class="common-text" size="50" title="Parent Menu Code"/></td>
                            </tr>
							<tr>
                                <th>菜单等级:</th>
                                <td><form:input path="menuLevel" class="common-text" size="50" title="Menu Level"/></td>
                            </tr>
							<tr>
                                <th>对应连接:</th>
                                <td><form:input path="url" class="common-text" size="50"  title="Menu Level"/></td>
                            </tr>
							<tr>
                                <th>是否节点:</th>
                                <td>
									<form:select path="isLeaf" title="isLeaf" class="required">
										<form:option value="">- 未选择 -</form:option>
									    <form:option value="1"></form:option>
									    <form:option value="0"></form:option>
									</form:select>
								</td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveMenu();" value="提交" type="button">
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
