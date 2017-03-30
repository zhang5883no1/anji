<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	底部公告
  </title>
  <script LANGUAGE="JavaScript">
		//query
		function search(){
			$("#foot_form").attr("modelAttribute","queryKCBInfoDto");
			submitFormForAnji("foot_form","${webcontext}/mainTain/footgonggao/queryfoot",0);
		}
		//save
		function savefoot(){
			$("#btnDone").css("display","none");
			$("#foot_form").attr("action","editfoot");
			submitFormForAnji("foot_form","${webcontext}/mainTain/footgonggao/editfoot",null);			
		}
		//edit
        function editfoot(){
			$("#btnDone").css("display","");
        }
	</script>
 </head>
<body leftMargin="0" topMargin="0">
<%@include file="../../include/head.jsp"%>
	<form:form  id="foot_form" name ="foot_form" action="queryfoot" method="post">
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">底部公告</span></div>
		</div>
		<div class="search-wrap">
            <div class="search-content">
					<table class="search-tab">
					<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130" style="text-align:right;">房间:</td>
							<td width="130">
								<select name="roomId">
									<core:forEach items="${footrooms}" var="roomlist">
										<option value="${roomlist.id }">${roomlist.roomName}</option>
									</core:forEach>
								</select>
							</td>
						<tr>
						</table>
						</td>
						<td style="padding-right:2px;" align="right" nowrap="nowrap">
								<input name="btnSearch" value="查询" onClick="search();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
								<input name="btnSearch" value="清空" onClick="clearAll();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
						</td>

						</tr>
					</table>
            </div>
        </div>
		<div class="result-title">
		<div class="result-wrap">
			<div class="result-content">
				<table class="insert-tab" width="100%" id="tableResults">
					<tbody>
						<tr><td colspan="6">
							<div class="result-list">
								<a id="btnReset" href="javascript:void(0)" onclick="editfoot()">修改</a>
							</div>
						</td></tr>
						<tr style="background-color:#909090">
							<td style="text-align:right;">公告:</td>
							<td>
								<input id="footcontent" name="footcontent" style="width:1200px;height:30px;" value="${footgonggao}"/>
							</td>
						</tr>
						<tr>
							<td colspan="6" style="text-align:right;">
								<input id="btnDone" class="btn btn-primary btn6 mr10" onClick="savefoot()" value="提交" type="button" style="display:none;">
							</td>
                        </tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</form:form>
</body>
</html>