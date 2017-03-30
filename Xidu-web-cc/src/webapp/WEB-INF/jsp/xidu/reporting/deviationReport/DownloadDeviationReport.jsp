<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Location Entity Details</title>
<%@include file="../../include/baseJsAndCss.jsp"%>
<script type='text/javascript'
	src='${jspath}/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#submitReport')
								.click(
										function() {
											$("#isErrorInfo").html("");
											submitFormForAnji(
													'uploadDeviationReport',
													'${webcontext}'
															+ '/reporting/DeviationReport/downloadDeviationReport',"2")
										});

					});
</script>
</HEAD>
<body leftMargin="0" topMargin="0">
	<form name="uploadDeviationReport" method="post"
		action="uploadDeviationReport" id="uploadDeviationReport"
		enctype="multipart/form-data">
		<%@include file="../../include/head.jsp"%>
		<table cellSpacing="1" cellpadding="1" width="100%" border="0">
			<tr>
				<td><span id="lblPageTile" class="PageTitle">DownLoad
						Deviation Report</span></td>
			</tr>
		</table>
		<table cellspacing="1" cellpadding="1" border="0" width="100%">
			<tr>
				<td><fieldset>
						<legend class="legend">General Information</legend>
						<table cellspacing="1" cellpadding="1" width="100%">
							<tbody>
								<tr>

									<td><span class="LabelBold" id="lblAliasType">Year:</span></td>
									<td><input type="text" id="year" name="year"
										onclick="WdatePicker({dateFmt:'yyyy'})" value="${year}"
										class="RequiredField userInput" /></td>
									<td><span class="LabelBold" id="lblAliasType">Month:</span></td>
									<td><select name="month" id="month" style="width: 190px;"
										class="RequiredField userInput">
											<option>---select month---</option>
											<option value="01"
												<core:if test="${month==1}">
											selected
											</core:if>>Jan.</option>
											<option value="02"
												<core:if test="${month==2}">
											selected
											</core:if>>Feb.</option>
											<option value="03"
												<core:if test="${month==3}">
											selected
											</core:if>>Mar.</option>
											<option value="04"
												<core:if test="${month==4}">
											selected
											</core:if>>Apr.</option>
											<option value="05"
												<core:if test="${month==5}">
											selected
											</core:if>>May</option>
											<option value="06"
												<core:if test="${month==6}">
											selected
											</core:if>>Jun.</option>
											<option value="07"
												<core:if test="${month==7}">
											selected
											</core:if>>Jul.</option>
											<option value="08"
												<core:if test="${month==8}">
											selected
											</core:if>>Aug.</option>
											<option value="09"
												<core:if test="${month==9}">
											selected
											</core:if>>Sep.</option>
											<option value="10"
												<core:if test="${month==10}">
											selected
											</core:if>>Oct.</option>
											<option value="11"
												<core:if test="${month==11}">
											selected
											</core:if>>Nov.</option>
											<option value="12"
												<core:if test="${month==12}">
											selected
											</core:if>>Dec.</option>
									</select></td>
								</tr>

								<tr>
									<td><span class="LabelBold" id="isErrorInfo">${errorinfo}</span></td>
								</tr>
							</tbody>
						</table>
					</fieldset></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>

		<div style="float: right;">
			<input style="width: 100px; text-align: center;" class="Button"
				id="submitReport" value="download" name="btnCancel">
		</div>
		<span id="PageMessageUpdatePanel"></span>
	</form>
</body>
</HTML>
