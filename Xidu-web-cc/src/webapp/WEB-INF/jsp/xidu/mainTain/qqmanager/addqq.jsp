<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	QQ添加
  </title>
	<script LANGUAGE="JavaScript">		
		function saveQQ(){
			submitFormForAnji("addQQ_form","${webcontext}/mainTain/qqmanager/saveQQ",null);
			
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/maintain/qqmanager/init",null);
			//location.href="${webcontext}/maintain/menu/init";
		}
		
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
     <%@include file="../../include/head.jsp"%>

	 <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">QQ分配</a><span class="crumb-step">&gt;</span><span>新增QQ</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form:form id="addQQ_form" name ="addQQ_form" action="saveQQ" method="post" modelAttribute="qqbasicInfodto">
					<form:hidden path="id"/>
                    <table class="insert-tab" width="100%" id="tableResults">
                        <tbody>
							<tr>
                                <th><i class="require-red">*</i>名称:</th>
                                <td>
                                    <form:input path="name" class="common-text" size="50" title="Menu Code" />
                                </td>
                            </tr>
                            <tr>
                                <th>QQ1:</th>
                                <td><form:input path="QQ1" class="common-text" size="50" title="Menu Name" /></td>
                            </tr>
							<tr>
                                <th>QQ2:</th>
                                <td><form:input path="QQ2" class="common-text" size="50" title="Parent Menu Code"/></td>
                            </tr>
							<tr>
                                <th>QQ3:</th>
                                <td><form:input path="QQ3" class="common-text" size="50" title="Menu Level"/></td>
                            </tr>
							<tr>
                                <th>QQ4:</th>
                                <td><form:input path="QQ4" class="common-text" size="50"  title="Menu Level"/></td>
                            </tr>
							<tr>
                                <th>QQ5:</th>
                                <td><form:input path="QQ5" class="common-text" size="50"  title="Menu Level"/></td>
                            </tr>
							<tr>
                                <th>QQ6:</th>
                                <td><form:input path="QQ6" class="common-text" size="50"  title="Menu Level"/></td>
                            </tr>
							<tr>
                                <th>房间:</th>
                                <td>
									<form:select path="roomId" title="isLeaf" class="required">
										<core:forEach items="${roomlist}" var="room">
											<form:option value="${room.id}">${room.roomName }</form:option>
										</core:forEach>
									</form:select>
								</td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveQQ()" value="提交" type="button">
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
