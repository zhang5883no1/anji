<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>Add Premium</title>
<script type='text/javascript'
	src='${jspath}/My97DatePicker/WdatePicker.js'></script>
<script LANGUAGE="JavaScript">
	function saveInfo() {
		if ($("input[name='isindex']").length > 0) {
			if ($("#checkflag").val() == 0) {
				if (validateForm()) {
					submitFormForAnji("addpremium_form","${webcontext}/ebooking/premium/savePremium", 0);
					//					$("#addpremium_form").submit();
				}
			}
		} else {
			addrow();
		}

	}

	function backList() {
		submitHrefForAnji("${webcontext}/ebooking/premium/init",null);
	}

	function viewInfo() {
		$("#addpremium_form").attr("action", "viewinfo");
		submitFormForAnji("addpremium_form","${webcontext}/ebooking/premium/viewinfo", 0);
		//		$("#addpremium_form").submit();
	}

	function validexist(txtbox) {
		if ($("#isvalue").val() == $(txtbox).val()) {
			$(txtbox).parent().children(".validSpan").remove();
			$("#checkflag").val("0");
			return;
		}
		$.ajax({
					method : "GET",
					url : "${webcontext}/ebooking/premium/validexist?code="+ $(txtbox).val()+"&currTime="+(new Date()).getTime(),
					success : function(msg) {
						if (msg == "isexist") {
							//alert("CustomerCode is not exist");
							$(txtbox).parent().children(".validSpan").remove();
							$(txtbox).parent().append("<span class='validSpan'>PTA Number is exist</span>");
							$("#checkflag").val("1");
							//$("#btnSave").hide();
						} else {
							$(txtbox).parent().children(".validSpan").remove();
							$(txtbox).next().next().val(msg);
							$("#checkflag").val("0");
						}
					}
				});
	}
</script>
<style type="text/css">
input {
	margin-top: 5px;
	margin-bottom: 5px;
}
</style>
</head>
<body leftMargin="0" topMargin="0">
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@include file="../../include/head.jsp"%>
	<br />
	<form:form id="addpremium_form" name="addpremium_form"
		action="savePremium" method="post" modelAttribute="premiumInfo">
		<form:hidden path="id" id="id" />
		<form:hidden path="omsno" />
		<form:hidden path="supplierCode" />
		<input type="hidden" name="checkflag" id="checkflag" value="0" />
		<input type="hidden" name="flag" value="1" />
		<input type="hidden" id="isvalue" value="${premiumInfo.ptaNumber }" />
		<form:hidden path="active" />
		<table border="0" cellspacing="2" width="100%">
			<tbody>
				<tr>
					<td><span id="lblHeader" class="PageTitle">Add Premium</span></td>
				</tr>
			</tbody>
		</table>


		<table cellpadding="3" cellspacing="1" width="100%"
			style="font-size: 8pt;">
			<tbody>
				<tr>
					<td><fieldset>
							<legend id="legSearchCriteria" class="legend">Info</legend>
							<div style="width: 100%; overflow: auto;">
								<table class="gridTable" width="2080" cellspacing="0"
									cellpadding="0" border="1"
									style="border-collapse: collapse; table-layout: fixed; width: 1000pt">

									<colgroup>
										<col width="61"
											style="mso-width-source: userset; mso-width-alt: 1952; width: 126pt"
											class="xl65">
										<col width="169"
											style="mso-width-source: userset; mso-width-alt: 5408; width: 80pt"
											class="xl65">
										<col width="150"
											style="mso-width-source: userset; mso-width-alt: 4800; width: 113pt"
											class="xl65">
										<col width="184"
											style="mso-width-source: userset; mso-width-alt: 5888; width: 108pt"
											class="xl65">
										<col width="113"
											style="mso-width-source: userset; mso-width-alt: 3616; width: 85pt"
											class="xl65">
										<col width="112"
											style="mso-width-source: userset; mso-width-alt: 3584; width: 84pt"
											class="xl65">
										<col width="175"
											style="mso-width-source: userset; mso-width-alt: 5600; width: 101pt"
											class="xl65">
										<col width="175"
											style="mso-width-source: userset; mso-width-alt: 5600; width: 101pt"
											class="xl65">
										<col width="175"
											style="mso-width-source: userset; mso-width-alt: 5600; width: 101pt"
											class="xl65">
									</colgroup>
									<tbody>
										<tr style="mso-height-source: userset;">
											<td colspan="8" align="center"
												style="font-size: 20pt; font-weight: bold;">PREMIUM
												TRANSPORT AUTHORISATION (PTA)</td>
											<td align="center"><img v:shapes="Picture_x0020_1"
												src="${imagepath}/image002.png" /></td>
										</tr>


										<tr>
											<td colspan="9" align="left" style="font-size: 13pt;"><div
													style="margin: 11px; font-weight: bold;">> Section 1
													- Premium Freight Instructions</div></td>
										</tr>
										<tr>
											<td colspan="4" align="left" rowspan="3"><span
												style="width: 200px; float: left; margin: 10px;">Date
													of Request*: <br /> <br />Telephone number*:<br /> <br />Destination
													Port:
											</span> <span style="width: 50px;"><form:input
														path="dateRequest" cssClass="RequiredField userInput"
														onfocus="WdatePicker()" /><br />
												<form:input path="tel" cssClass="RequiredField userInput" /><br />
												<form:input path="destinationPort"
														cssClass="RequiredField userInput" /></span></td>
											<td colspan="5" align="left" rowspan="3"><span
												style="width: 200px; float: left; margin: 10px;">Name
													of Requestor*: <br /> <br />Email address*:<br /> <br />Destination
													Plant*:
											</span> <span style="width: 50px;"><form:input path="name"
														cssClass="RequiredField userInput" /><br />
												<form:input path="email" cssClass="RequiredField userInput" /><br />
												<form:input path="destinationPlant"
														cssClass="RequiredField userInput" /></span></td>
										</tr>
										<tr></tr>
										<tr></tr>
										<tr>
											<td align="center"><input type="button" value="add"
												onclick="addrow()" /> <input type="button" value="del"
												onclick="delrow()" /></td>
											<td colspan="3" align="center" style="font-size: 12px;">Part
												Number*&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td colspan="3" align="center" style="font-size: 12px;">Description*
												&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td colspan="2" align="center" style="font-size: 12px;">Quantity
												Requested* &nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>

										<%
											int i = 0;
										%>
										<core:forEach items="${freightlist}" var="freight">
											<tr>
												<td align="center"><input type="radio" name="isradio" />
													<input type="hidden" name="isindex"
													value="<%out.print(++i);%>" /></td>
												<td colspan="3" align="center" style="font-size: 16px;"><input
													class="RequiredField userInput" type="text"
													name="pn<%out.print(i);%>" value="${freight.partNumber}"
													onkeyup="valid(this)" /><input type="hidden" value="" /></td>
												<td colspan="3" align="center" style="font-size: 16px;"><input
													class="RequiredField userInput" type="text"
													name="des<%out.print(i);%>" value="${freight.description}" />
												</td>
												<td colspan="2" align="center" style="font-size: 16px;"><input
													class="RequiredField userInput" type="text"
													name="qr<%out.print(i);%>"
													value="${freight.quantityRequested}" /></td>
											</tr>
										</core:forEach>

										<tr id="lastrow" style="height: 30pt;">
											<td colspan="4" align="left"><span
												style="width: 50px; margin-left: 10px">MRD*: </span>&nbsp;&nbsp;&nbsp;&nbsp;<form:input
													path="mrd" cssClass="RequiredField userInput"
													onfocus="WdatePicker()" /></td>
											<td colspan="5" align="left"><span
												style="width: 50px; margin-left: 10px">PTA Number:</span>
												&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="ptaNumber"
													onblur="validexist(this)"
													cssClass="RequiredField userInput" /></td>
										</tr>
										<tr style="height: 30pt;">
											<td colspan="4" align="left"><span
												style="width: 50px; margin-left: 10px">Cargo ready
													date*: </span>&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="readyDate"
													cssClass="RequiredField userInput" onfocus="WdatePicker()" />
											</td>
											<td colspan="5" align="left"><span
												style="width: 50px; margin-left: 10px">VOLVO Call-off
													number: </span> &nbsp;&nbsp;&nbsp;&nbsp;<form:input
													path="volvoNumber" /></td>
										</tr>
										<tr style="height: 30pt;">
											<td colspan="9" align="left"><span
												style="width: 50px; margin-left: 10px">PO number*:</span>&nbsp;&nbsp;&nbsp;&nbsp;<form:input
													path="poNumber" cssClass="RequiredField userInput" /></td>
										</tr>
										<tr style="height: 50pt;">
											<td colspan="9" align="left"><span
												style="width: 50px; margin-left: 10px">Service Level*</span>&nbsp;&nbsp;&nbsp;&nbsp;
												<br /> <span style="margin-left: 10px"><input
													type="checkbox" value="1" name="serviceLevel"
													<core:if test="${fn:substring(premiumInfo.serviceLevel, 2, 3)=='1'}">checked</core:if> />Ocean
													to Air &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
													type="checkbox" value="2" name="serviceLevel"
													<core:if test="${fn:substring(premiumInfo.serviceLevel, 1, 2)=='1'}">checked</core:if> />Barge
													to Truck &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
													type="checkbox" value="4" name="serviceLevel"
													<core:if test="${fn:substring(premiumInfo.serviceLevel, 0, 1)=='1'}">checked</core:if> />Return
													from "Destination Plant" to "supplier"
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></td>
										</tr>
										<tr>
											<td colspan="9" align="left" style="font-size: 13pt;"><div
													style="margin: 11px; font-weight: bold;">> Section 2
													- Supplier Details</div></td>
										</tr>
										<tr>
											<td colspan="4" align="left" rowspan="9"><span
												style="width: 150px; float: left; margin: 10px;">
													Supplier Name*: <br />
												<br /> Pick up add*: <br />
												<br /> Country*: <br />
												<br /> Contact Name*: <br />
												<br /> Date of Shipment:<br />
												<br /> Pick up Window:<br />
												<br /> Total boxes / pallets*<br />
												<br /> Dimensions*
											</span> <span
												style="width: 250px; float: right; margin: 10px 100px 0px 0px;">
													${user.supplierNameEn }<br />
												<br /> ${user.pickUpAddress }<br />
												<br /> ${user.country }<br />
												<br /> ${user.contactPerson }<br /> <form:input
														path="dateofshipment" onfocus="WdatePicker()" /><br /> <form:input
														path="pickupdate" onfocus="WdatePicker()" /><br /> <form:input
														path="totalbox" cssClass="RequiredField userInput" /><br />
													<form:input path="dimensions"
														cssClass="RequiredField userInput" />
											</span>
											<td colspan="5" align="left" rowspan="9"><span
												style="width: 150px; float: left; margin: 10px;">
													Supplier code*:<br />
												<br /> Email*: <br />
												<br /> Tel*: <br />
												<br /> Fax: <br />
												<br /> Email:<br />
												<br /> Total Weight <br />
												<br /> Hazardous material*
											</span> <span
												style="width: 250px; float: right; margin: 10px 100px 0px 0px;">
													${user.supplierCode }<br />
												<br /> ${user.email }<br />
												<br /> ${user.telNo}<br />
												<br /> ${user.faxNo }<br />
												<br /> ${user.email }<br /> <span><form:input
															path="totalweight" cssClass="RequiredField userInput"
															onkeyup="checkweight()" /></span><br /> <form:input
														path="material" cssClass="RequiredField userInput" /><br />
												<br />
											</span></td>
										</tr>

										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr>
											<td colspan="4" align="center" style="height: 20pt;">VCC
												APPLICANT</td>
											<td colspan="5" align="center" style="height: 20pt;">APPROVED
												BY</td>
										</tr>
										<tr style="height: 60pt;">
											<td colspan="4" align="center" rowspan="4"><textarea
													style="width: 200px;" name="vccApplicant">${premiumInfo.vccApplicant}</textarea></td>
											<td colspan="5" align="center" rowspan="4"><span
												style="float: left;">SCC mgr</span> <textarea
													style="width: 200px;" name="approvedBy">${premiumInfo.approvedBy}</textarea></td>
										</tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<tr></tr>
										<%
											String type = (String) request.getSession().getAttribute("USERTYPE");
												if ("IT".equals(type)) {
										%>
										<tr height="29"
											style="mso-height-source: userset; height: 21.75pt">
											<td height="29" style="height: 21.75pt" class="xl160"
												colspan="1">Status</td>
											<td height="29" style="height: 21.75pt" class="xl160"
												colspan="8"><form:select style="width: 130px;"
													type="text" path="status">
													<option value="PENDING">Pending</option>
													<option value="APPROVED">Approved</option>
													<option value="DISAPPROVED">Disapproved</option>
													<option value="CANCELLED">Cancelled</option>
												</form:select></td>
										</tr>
										<%
											} else {
										%>
										<form:hidden path="status" />
										<%
											}
										%>
									</tbody>
								</table>
							</div>


							<hr style="width: 99%;">
							<table border="0" cellpadding="1" cellspacing="1" width="100%">
								<tbody>
									<tr>
										<td style="padding-left: 2px" align="left" nowrap="nowrap">
										</td>
										<td style="padding-right: 2px;" align="right" nowrap="nowrap"><input
											name="btnDetails" value="Review" onClick="viewInfo()"
											id="btnDetails" class="Button" type="button"
											style="width: 100px; text-align: center;"> <input
											name="btnAdd" value="save" onclick="saveInfo()" id="btnAdd"
											class="Button" type="button"
											style="width: 90px; text-align: center;"> <input
											name="btnDetails" value="Back" onClick="backList()"
											id="btnDetails" class="Button" type="button"
											style="width: 100px; text-align: center;"></td>
									</tr>
								</tbody>
							</table>
						</fieldset></td>
				</tr>
			</tbody>
		</table>
		</span>
		<span id="PageMessageUpdatePanel"></span>

		<script language="JavaScript">
			var index = 999;
			function addrow() {
				$("#lastrow").before(
								"<tr>"
										+ "<td  align='center'><input type='radio' name='isradio'/><input type='hidden' name='isindex' value='"
										+ (++index)
										+ "' /></td>"
										+ "<td colspan='3' align='center' style='font-size:16px;'><input class='RequiredField userInput'  type='text' name='pn"
										+ index
										+ "' onkeyup='valid(this)'/><input type='hidden' /></td>"
										+ "<td colspan='3' align='center' style='font-size:16px;'><input class='RequiredField userInput' type='text' name='des"+index+"'/> </td>"
										+ "<td colspan='2' align='center' style='font-size:16px;'><input class='RequiredField userInput' type='text' name='qr"+index+"'/> </td>"
										+ "</tr>");
			}

			function delrow() {
				var selctntype = $("input[name='isradio']:radio:checked");
				if (selctntype.length == 0) {
					alertForAnji('Please select one record.',null);
					return;
				}
				selctntype.parent().parent().remove();

			}

			function valid(txtbox) {
				$("#checkflag").val("1");
				$
						.ajax({
							method : "GET",
							url : "${webcontext}/ebooking/domesticSupplier/valid?code="
									+ $(txtbox).val()+"&currTime="+(new Date()).getTime(),
							success : function(msg) {
								if (msg == "false") {
									//alert("CustomerCode is not exist");
									$(txtbox).parent().children(".validSpan").remove();
									$(txtbox).parent().append("<span class='validSpan'> is not exist</span>");
									$(txtbox).parent().children("input:hidden").val("");
									//$("#btnSave").hide();
								} else {
									$(txtbox).parent().children(".validSpan").remove();
									$(txtbox).next().next().val(msg);
									$(txtbox).parent().children("input:hidden").val(msg);
									checkweight();
								}
							}
						});

			}

			function checkweight() {
				$("#checkflag").val("1");

				$(totalweight).parent().children(".validSpan").remove();

				var weight = $("#totalweight").val();
				var list = $("input[name='isindex']");
				var allweight = 0;

				$.each(list,function(i, n) {
									var isweight = $(n).parent().next().children("input:hidden").val();
									if (isweight == "") {
										valid($(n).parent().next().children("input:hidden"));
										return;
									}
									var isqty = $(n).parent().next().next().next().children("input:text").val();
									allweight += ((parseFloat(isweight * 100) / 100) * (parseFloat(isqty * 100) / 100));
								});
				if (weight != allweight) {
					$(totalweight).parent().append(
							"<span class='validSpan'> weight is error</span>");
				} else {
					$("#checkflag").val("0");
				}
			}
		</script>
	</form:form>
</body>
</html>