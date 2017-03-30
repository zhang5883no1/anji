<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Location Entity Details</title>
<%@include file="../../include/baseJsAndCss.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#submitReport').click(
			function (){
				submitFormForAnji('uploadDeviationReport','${webcontext}' + '/reporting/deviationReport/uploadDeviationReport')
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
				<td><span id="lblPageTile" class="PageTitle">Upload Call
						off</span></td>
			</tr>
		</table>
		<table cellspacing="1" cellpadding="1" border="0" width="100%">
			<tr>
				<td><fieldset>
						<legend class="legend">General Information</legend>
						<table cellspacing="1" cellpadding="1" width="100%">
							<tbody>
								<tr>
									<td align="right" width="20%"><span class="LabelBold"
										id="lblName">Upload File:</span></td>
									<td width="30%" nowrap="nowrap"><span class="Label"
										id="lblNameValue"><input name="multipartFile"
											type="file" /> <input type="button"
											style="text-align: center;" class="Button" value="Upload" id="submitReport"/></span></td>
									<td align="right" width="20%"><span class="LabelBold"
										id="lblEntityId" style="color: red">${errorMsg}</span></td>
									<td width="30%"><span class="Label" id="lblEntityIdValue">${successMsg}</span></td>
								</tr>
								<tr>
									<td><span class="LabelBold" id="lblAliasType">CustomerCode
											Code:</span></td>
									<td><input type="text" style="width: 190px"
										id="customerCode" name="customerCode" /><input id="customerNameEn" name="customerNameEn" type="hidden"> <input id="customerId" name="customerId" type="hidden"><a	href="javascript:void(0);" onclick="showQuery('query_customer','customerCode,customerNameEn,customerId');"><img
											src="${imagepath}/search.gif" align="absmiddle" />
											<td><span class="LabelBold" id="lblAliasType">Month:</span></td>
											<td><select id="selectedMonth" name="selectedMonth" style="width:190px;"><option value="01">Jan.</option><option value="02">Feb.</option><option value="03">Mar.</option><option value="04">Apr.</option><option value="05">May</option><option value="06">Jun.</option><option value="07">July.</option><option value="08">Aug.</option><option value="09">Sept.</option><option value="10">Oct.</option><option value="11">Nov.</option><option value="12">Dec.</option></select></td>
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
				id="btnCancel" onClick="window.close();" value="Close"
				name="btnCancel">
		</div>
		<span id="PageMessageUpdatePanel"></span>
	</form>
</body>
</HTML>
