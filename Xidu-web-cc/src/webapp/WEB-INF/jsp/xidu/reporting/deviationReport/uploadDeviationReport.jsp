<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>上传图片</title>
<%@include file="../../include/baseJsAndCss.jsp"%>
	<script LANGUAGE="JavaScript">
		function saveUpload(){
			document.forms["uploadDeviationReport"].submit();
			//submitFormForAnji('uploadDeviationReport','${webcontext}' + '/mainTain/upload/uploadDeviationReport')；
		}
	</script>
</head>
<body leftMargin="0" topMargin="0">
<%@include file="../../include/head.jsp"%>

<form name="uploadDeviationReport" method="post" action="uploadDeviationReport" id="uploadDeviationReport"	enctype="multipart/form-data">
<div class="main-wrap">
	<div class="crumb-wrap">
		<div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">上传图片</span></div>
	</div>
	<div class="search-wrap">
		<div class="search-content">
			<table class="search-tab">
				<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130" style="text-align:right;">房间:</td>
							<td width="130">
								<select name="roomId" cssClass="userInput">
									<option value="1">--未选择--</option>
									<core:forEach items="${RoomsList}" var="roomlist">
										<option value="${roomlist.id }">${roomlist.roomName }</option>
									</core:forEach>
								</select>
							</td>
						<tr>
					</table>
					<td style="padding-right:2px;" align="right" nowrap="nowrap">
						<input name="btnSearch" value="切换房间" onClick="search();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
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
						<tr>
							<th><i class="require-red">*</i>登录时弹出图片:</th>
							<td>
								<input name="loginimg" type="file" class="common-text" size="50" title="登录时弹出图片" />
							</td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>5分钟时弹出图片:</th>
							<td>
								<input name="fiveimg" type="file" class="common-text" size="50" title="5分钟时弹出图片" />
							</td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>公告1图片:</th>
							<td>
								<input name="oneimg" type="file" class="common-text" size="50" title="公告1图片" />
							</td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>公告2图片:</th>
							<td>
								<input name="twoimg" type="file" class="common-text" size="50" title="公告2图片" />
							</td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>公告3图片:</th>
							<td>
								<input name="threeimg" type="file" class="common-text" size="50" title="公告3图片" />
							</td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>公告4图片:</th>
							<td>
								<input name="fourimg" type="file" class="common-text" size="50" title="公告4图片" />
							</td>
						</tr>
						<tr>
							<th></th>
							<td>
								<input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveUpload()" value="提交" type="button">
								<input id="btnClose" class="btn btn6" onClick="backList();" value="返回" type="button">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<span id="PageMessageUpdatePanel"></span>
</form>
</body>
</HTML>
