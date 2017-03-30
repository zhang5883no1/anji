<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>Add International Supplier</title>
<script type='text/javascript' src='${jspath}/My97DatePicker/WdatePicker.js'></script>
<script LANGUAGE="JavaScript">		
		function saveInfo(){
			if($("input[name='isrow']").length>0){
				if(validateForm()){
					submitFormForAnji("addInfo_form","${webcontext}/ebooking/internationalsupplier/saveInterInfo",0);
//					$("#addInfo_form").submit();
				}		
			}else{
				alertForAnji("please add Pallet info",null);
			}
			
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/ebooking/internationalsupplier/init",null);
		}
		
		function viewInfo(){
			$("#addInfo_form").attr("action","viewInterInfo");
			submitFormForAnji("addInfo_form","${webcontext}/ebooking/internationalsupplier/viewInterInfo",0);
//			$("#addInfo_form").submit();
		}
		
		$(document).ready(
	  			function(){
	  				if("saved successfully"=="${resultInfo}"){
	  					alert("${resultInfo}");
	  					submitHrefForAnji("${webcontext}/ebooking/internationalsupplier/init",null);
	  				}
	  			}
		);
		
		function getInfo(selcetbox){
			$("#destplantcode").val($("#isTel"+selcetbox.selectedIndex).val());
			$("#contactperson").val($("#isContactPerson"+selcetbox.selectedIndex).val());
		}
		

    </script>

</head>
<body leftMargin="0" topMargin="0">
  <%@include file="../../include/head.jsp"%>
  <br/>
	<form:form id="addInfo_form" name="addInfo_form" action="saveInterInfo"
		method="post" modelAttribute="intersup">
		<form:hidden path="id" id="id" />
		<form:hidden path="shipperCode"/>
		<form:hidden path="internationSupplierNo"/>
		<form:hidden path="omsNo"/>
		<form:hidden path="userId"/>
		<form:hidden path="active" />
			<table border="0" cellspacing="2" width="100%">
				<tbody>
					<tr>
						<td><span id="lblHeader" class="PageTitle">Add
								International Supplier</span></td>
					</tr>
				</tbody>
			</table>
			<style>
table.gridTable td {
	padding-left: 5px;
	font-family: tahoma, verdana, arial, helvetica;
}

.xl132,.xl145,.xl107 {
	background-color: #dfdfdf;
	color: #000;
	font-weight: bold;
}
</style>
			<span id="UpdatePanelSearch">
				<table cellpadding="3" cellspacing="1" width="100%">
					<tbody>
						<tr>
							<td><fieldset>
									<legend id="legSearchCriteria" class="legend">Info</legend>
									<table class="gridTable" width="900" cellspacing="0"
										cellpadding="0" border="1"
										style="border-collapse: collapse; table-layout: fixed; width: 900px; font-size: 8pt;">
										<colgroup>
											<col width="120"
												style="mso-width-source: userset; mso-width-alt: 3840; width: 90pt"
												class="xl68">
											<col width="84"
												style="mso-width-source: userset; mso-width-alt: 2688; width: 63pt"
												class="xl68">
											<col width="72" style="width: 54pt" class="xl68">
											<col width="27"
												style="mso-width-source: userset; mso-width-alt: 864; width: 20pt"
												class="xl68">
											<col width="49"
												style="mso-width-source: userset; mso-width-alt: 1568; width: 37pt"
												class="xl68">
											<col width="70"
												style="mso-width-source: userset; mso-width-alt: 2240; width: 53pt"
												class="xl68">
											<col width="149"
												style="mso-width-source: userset; mso-width-alt: 4768; width: 112pt"
												class="xl68">
											<col width="105" span="2"
												style="mso-width-source: userset; mso-width-alt: 3360; width: 99pt"
												class="xl68">
											<col width="55"
												style="mso-width-source: userset; mso-width-alt: 1760; width: 89pt"
												class="xl68">
											<col width="65"
												style="mso-width-source: userset; mso-width-alt: 2080; width: 69pt"
												class="xl68">
										</colgroup>
										<tbody>
											<tr height="78"
												style="mso-height-source: userset; height: 58.5pt">
												<td width="901" height="78"
													style="height: 58.5pt; width: 317pt" class="xl127"
													colspan="6"><h1>STANDARD BASIC SHIPMENT DETAILS</h1></td>
												<td width="901" valign="top" height="78" align="left"
													style="height: 58.5pt; width: 360pt" colspan="5"><img
													src="${imagepath}/image0111.png" style="margin: 10px 0 0 20px;" /></td>
											</tr>
											<tr height="28"
												style="mso-height-source: userset; height: 21.0pt">
												<td height="28" style="height: 21.0pt" class="xl132"
													colspan="11">► Section 1 - Supplier Details</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Shipper<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl135" colspan="6">
													<select onchange="changeUserInfo(this)" >
													<core:forEach var="userlist" items="${userlist}">
													<core:if test="${user.id==userlist.id}"><option value="${userlist.id}" selected>${userlist.supplierCode}</option></core:if>
													<core:if test="${user.id!=userlist.id}"><option value="${userlist.id}" >${userlist.supplierCode}</option></core:if>
													</core:forEach>
													</select><input type="hidden" name="isshipperCode" id="isshipperCode" value="${user.id}" />
													</td>
												<td style="border-top: none; border-left: none" class="xl71">Supplier
													Code<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl135" colspan="3"><input
													disabled="disabled" style="width: 200px;" id="supplierCode" type="text"
													value="${user.supplierCode}" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Pick up Address:<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl137" colspan="6"><input
													style="width: 400px;" type="text"
													value="${user.pickUpAddress}" id="pickUpAddress" disabled="disabled" /></td>
												<td style="border-top: none; border-left: none" class="xl83">Invoice
													No<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl140" colspan="3"><input
													style="width: 200px;" type="text" value="${user.invoiceNo}"
													disabled="disabled" id="invoiceNo" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Zip Code<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-top: none; border-left: none" class="xl71"
													colSpan="2"><input disabled="disabled"
													style="width: 110px;" type="text" value="${user.zipCode}" id="zipCode"/></td>
												<td style="border-top: none" class="xl78" colSpan="2">Country<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl137" colspan="2"><input
													disabled="disabled" style="width: 150px;" type="text"
													value="${user.country}" id="country"/></td>
												<td style="border-top: none; border-left: none" class="xl71">City<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-top: none" class="xl78" colSpan="3"><input
													style="width: 140px;" type="text" disabled="disabled"
													value="${user.city}" id="city" /></td>
											</tr>
											<tr height="56"
												style="mso-height-source: userset; height: 42.0pt">
												<td height="56" style="height: 42.0pt; border-top: none"
													class="xl70">Contact person<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl135" colspan="5"><input
													style="width: 280px;" type="text"
													value="${user.contactPerson}" id="contactPerson" disabled="disabled" /></td>
												<td style="border-left: none" class="xl149" colspan="2">Tel.
													No<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td width="901" style="border-left: none; width: 169pt"
													class="xl150" colspan="3"><input style="width: 200px;"
													type="text" value="${user.telNo}" id="telNo" disabled="disabled" /></td>
											</tr>
											<tr height="41"
												style="mso-height-source: userset; height: 30.75pt">
												<td height="41" style="height: 30.75pt; border-top: none"
													class="xl70">Email addr.<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-left: none; width: 227pt"
													class="xl153" colspan="5"><input style="width: 280px;"
													type="text" value="${user.email}" id="email" disabled="disabled" /></td>
												<td style="border-left: none" class="xl149" colspan="2">Fax
													No<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl135" colspan="3"><input
													style="width: 200px;" type="text" value="${user.faxNo}"
													disabled="disabled" id="faxNo"/></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl145"
													colspan="11">► Section 2 - Destination Plant Details</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="58" style="height: 43.5pt; border-top: none"
													class="xl184">CNEE<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-bottom: .5pt solid black" class="xl186"
													colspan="6">
													<form:select id="typeSel" path="cnee" cssClass="userInput" style="width:300px;" onchange="getInfo(this)">
														<core:forEach items="${destlist}" var="dest">
														<form:option value="${dest.plantNameCn }">${dest.plantNameCn }</form:option>
														</core:forEach>
													</form:select>
												<td style="border-top: none; border-left: none" class="xl72">Contact
													person<span style="font-weight: bold; color: #F00;">*</span>
												</td><%int contaceindex=0; %>
												<td style="border-left: none" class="xl86" colspan="3">
														<core:forEach items="${destlist}" var="dest">
														<input id="isContactPerson<%=contaceindex++ %>" value="${dest.contactPerson }" type="hidden"/>
														</core:forEach>
													<core:if test="${intersup.id!=null }">
														<input id="contactperson" name="contactperson" cssClass="userInput" style="width:300px;background:#DFDFDF"  value="${intersup.contactperson }" readonly="readonly" />
													</core:if>
													<core:if test="${intersup.id==null }">
													<%int isindex=0; %>
													<core:forEach items="${destlist}" var="dest">
													<%if(isindex==0){ %>
														<input id="contactperson" name="contactperson" cssClass="userInput" style="width:300px;background:#DFDFDF"  value="${dest.contactPerson }" readonly="readonly" />
													<%isindex++;
													} %>
													</core:forEach>
													</core:if>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt"
												class="xl69">
												<td height="58" style="height: 43.5pt; border-top: none"
													class="xl184">Call Off number<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-bottom: .5pt solid black" class="xl186"
													colspan="6"><form:input style="width: 200px;"
														type="text" path="desttel" cssClass="RequiredField userInput" maxlength="30"/></td>
												<td height="29"
													style="height: 21.75pt; border-top: none; border-left: none; border-bottom: .5px solid black"
													class="xl74">Contact number<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td
													style="border-left: none; border-bottom: .5px solid black"
													class="xl72" colspan="3">
													<%int telindex=0; %>
														<core:forEach items="${destlist}" var="dest">
														<input type="hidden" id="isTel<%=telindex++ %>" value="${dest.telNo }"  />
														</core:forEach>
												<core:if test="${intersup.id!=null }">
													<input id="destplantcode" name="destplantcode" cssClass="userInput" value="${intersup.destplantcode }" style="width:300px;background:#DFDFDF"  readonly="readonly"/>
												</core:if>
												<core:if test="${intersup.id==null }">
													<%int isindexx=0; %>
													<core:forEach items="${destlist}" var="dest">
													<%if(isindexx==0){ %>
													<input id="destplantcode" name="destplantcode" cssClass="userInput" value="${dest.telNo }" style="width:300px;background:#DFDFDF"  readonly="readonly"/>
													<%isindexx++;
													} %>
													</core:forEach>
												</core:if>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt"
												class="xl69">
												<td height="29" style="height: 21.75pt" class="xl145"
													colspan="11">► Section 3 - Cargo Information</td>
											</tr>
											<tr height="30"
												style="mso-height-source: userset; height: 22.5pt"
												class="xl69">
												<td height="30" style="height: 22.5pt; border-top: none"
													class="xl73">Port of Loading<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td colspan="2" class="xl88"
													style="border-top: none; border-left: none"><form:input
														style="width: 130px;" type="text" path="pol" cssClass="RequiredField userInput" maxlength="30"/></td>
												<td style="border-left: none" class="xl172" colspan="3">Port
													of Discharge<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-top: none; border-left: none" class="xl86"><form:input
														style="width: 130px;" type="text" path="pod" cssClass="RequiredField userInput" maxlength="30"/></td>
												<td style="border-left: none" class="xl172" colspan="2">Transshipment
													port</td>
												<td style="border-left: none" class="xl176" colspan="2"><form:input
														style="width: 100px;" type="text"
														path="transshipment_port" maxlength="30"/></td>
											</tr>
											<tr height="30"
												style="mso-height-source: userset; height: 22.5pt"
												class="xl69">
												<td height="30" style="height: 22.5pt; border-top: none"
													class="xl84">HS Code</td>
												<td class="xl178" colspan="2"><form:input
														style="width: 130px;" type="text" path="hsCode" /></td>
												<td class="xl86" colspan="3">Package type<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="" class="xl178" colspan="2"><form:input
														style="width: 130px;" type="text" path="package_type" cssClass="RequiredField userInput" maxlength="30"/></td>
												<td style="border-top: none; border-left: none" class="xl86">Remarks<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl179" colspan="2"><form:input
														style="width: 100px;" type="text" path="remarks" cssClass="RequiredField userInput" maxlength="30"/></td>
											</tr>
											<tr height="41"
												style="mso-height-source: userset; height: 30.75pt">
												<td height="41" style="height: 30.75pt; border-top: none"
													class="xl70">Delivery date in Call off<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-left: none; width: 227pt"
													class="xl153" colspan="5"><form:input
														style="width: 280px;" type="text" path="deliveryDate1" onfocus="WdatePicker()" readonly='readonly' cssClass="RequiredField userInput"/></td>
												<td style="border-left: none" class="xl149" colspan="2">
												</td>
												<td style="border-left: none" class="xl135" colspan="3"></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl107"
													colspan="11">► Section 4 - Loading Information</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Cargo ready date<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl171" colspan="4"><form:input
														style="width: 130px;" type="text" path="readyDate1" onfocus="WdatePicker()" readonly='readonly' cssClass="RequiredField userInput"/></td>
												<td style="border-left: none" class="xl135" colspan="2">Pick
													up date<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl137" colspan="4">
													<form:input style="width: 130px;" type="text"
														path="pickUpDate1" onfocus="WdatePicker()" readonly='readonly' cssClass="RequiredField userInput"/>
												</td>
											</tr>
											<tr height="48"
												style="mso-height-source: userset; height: 36.0pt">
												<td width="901" height="48"
													style="height: 36.0pt; width: 153pt" class="xl196"
													colspan="2">Arrival Date to CFS / CY</td>
												<td width="901" style="width: 164pt" class="xl174"
													colspan="4"><form:input style="width: 130px;"
														type="text" path="arrivalDate1" onfocus="WdatePicker()" readonly='readonly'/></td>
												<td width="901"
													style="border-top: none; border-left: none; width: 112pt"
													class="xl75">Transportation Mode</td>
												<td width="901" valign="top" height="48" align="left"
													style="height: 36.0pt; width: 248pt" colspan="4">
													 <span style="mso-ignore: vglayout2">
														<table cellspacing="0" cellpadding="0">
															<tbody>
																<tr>
																	<td width="330" height="48"
																		style="height: 36.0pt; border-left: none; width: 248pt"
																		class="xl150" colspan="4"><form:input
																			style="width: 130px;" type="text"
																			path="transportationMode" maxlength="30"/></td>
																</tr>
															</tbody>
														</table>
												</span>
												</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Service level</td>
												<td style="border-left: none" class="xl137" colspan="4"><form:input
														style="width: 130px;" type="text" path="serviceLevel" maxlength="30"/></td>
												<td width="901" style="border-left: none; width: 165pt"
													class="xl75" colspan="2">Dangerous goods<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-left: none; width: 158pt"
													class="xl150" colspan="2"><input type="radio"
													checked="checked" name="dangerousGoods" value="0"
													style="vertical-align: middle"
													<core:if test="${intersup.dangerousGoods==0 }">checked</core:if> />
													Yes</td>
												<td style="border-left: none" class="xl137" colspan="2"><input
													name="dangerousGoods" value="1" type="radio"
													style="vertical-align: middle"
													<core:if test="${intersup.dangerousGoods==1 }">checked</core:if> />
													No</td>
											</tr>
											<tr height="38"
												style="mso-height-source: userset; height: 28.5pt">
												<td height="38" style="height: 28.5pt; border-top: none"
													class="xl70">If FCL:</td>
												<td colspan="2"><form:input type="text" path="fcl" maxlength="30"/></td>
												<td style="border-top: none; border-left: none" class="xl76"
													colspan="2">Container No.:</td>
												<td width="901" style="width: 111pt" class="xl168"
													colspan="2"><form:input style="width: 130px;"
														type="text" path="container" maxlength="30"/></td>
												<td class="xl71" style="border-top: none; border-left: none">Type
													of container:<span style="display: none">tainer:</span>
												</td>
												<td style="border-left: none" class="xl143" colspan="4"><form:input
														style="width: 130px;" type="text" path="containerType" maxlength="30"/></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl169"
													colspan="2">Total pallets/packages:<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-top: none; border-left: none" class="xl77"><form:input
														style="width: 50px;" type="text" path="totalPallets" cssClass="RequiredField userInput" maxlength="30"/></td>
												<td style="" class="xl193" colspan="3">Total Gross
													Weight/kg:<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl193" colspan="2"><form:input
														style="width: 130px;" type="text" path="totalGross" cssClass="RequiredField userInput" maxlength="30"/></td>
												<td style="border-top: none; border-left: none" class="xl71">Total
													CBM<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl137" colspan="2"><form:input
														style="width: 100px;" type="text" path="totalCbm" cssClass="RequiredField userInput" maxlength="30"/></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td width="901" valign="top" height="29" align="left"
													style="height: 21.75pt; width: 677pt" colspan="11"><span
													style="mso-ignore: vglayout2">
														<table cellspacing="0" cellpadding="0">
															<tbody>
																<tr>
																	<td width="900" height="29" colspan="11" class="xl139"
																		style="height: 21.75pt; width: 647pt">Please
																		circle the level of stackability of your Pallets?<span
																		style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp; </span> <input
																		type="radio" checked="checked" name="palletsLevel"
																		value="1" style="vertical-align: middle"
																		<core:if test="${intersup.palletsLevel==1 }">checked</core:if> />
																		1<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="2" type="radio"
																		style="vertical-align: middle"
																		<core:if test="${intersup.palletsLevel==2 }">checked</core:if> />
																		2<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="3" type="radio"
																		style="vertical-align: middle"
																		<core:if test="${intersup.palletsLevel==3 }">checked</core:if> />
																		3<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="4" type="radio"
																		style="vertical-align: middle"
																		<core:if test="${intersup.palletsLevel==4 }">checked</core:if> />
																		4<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="5" type="radio"
																		style="vertical-align: middle"
																		<core:if test="${intersup.palletsLevel==5 }">checked</core:if> />
																		5
																	</td>
																</tr>
															</tbody>
														</table>
												</span></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="58" style="height: 43.5pt" class="xl155"
													colspan="7">Unit: kg and mm<br> The frist package
													information
												</td>
												<td width="901" style="width: 360pt" class="xl81"
													colspan="4">Part Number and quantity
											</tr>
											<tr height="42"
												style="mso-height-source: userset; height: 31.5pt">
												<td><span style="float: left"><input
														type="button" value="add" onclick="addrow()" /><input
														type="button" value="del" onclick="delrow()" /></span></td>
												<td height="42" style="height: 31.5pt" class="xl181"
													colspan="1">Pallet qty<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl135" colspan="2">Length<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-top: none; width: 112pt"
													class="xl81">Width<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901"
													style="border-top: none; border-left: none; width: 79pt"
													class="xl79">Height<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901"
													style="border-top: none; border-left: none; width: 79pt"
													class="xl79">Weight<br>/Pallet<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901"
													style="border-top: none; border-left: none; width: 41pt"
													class="xl79">P/N<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901"
													style="border-top: none; border-left: none; width: 49pt"
													class="xl80">QTY <span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="width: 360pt; text-align: center;"
													class="xl81" colspan="2"></td>
											</tr>
											<%
												int i = 1;
												int j=1;
											%>
											<%
												int index = 1;
											%>
											<core:forEach items="${palletinfos}" var="palletinfo">
												<%
													index = 1;
													++i;
												%>
												<core:forEach items="${palletinfo.nqlist}" var="lists">
													<tr id="row<%out.print(i); %>" height="29"
														style="mso-height-source: userset; height: 21.75pt">
														<%
															if (index == 1) {
														%>
														<td id="child7<%out.print(i);%>"
															rowspan="${palletinfo.size}"><input type="radio"
															name="isrow" value="<%out.print(i);%>" /><input
															type="hidden" value="<%out.print(i);%>" name="hiddenrow" /></td>
														<td id="child1<%out.print(i);%>" height="29"
															style="height: 21.75pt" class="xl143" colspan="1"
															rowspan="${palletinfo.size}"><input
															style="width: 60px;" type="text" maxlength="30"
															name="palletqty<%out.print(i);%>"
															value="${palletinfo.palletQty}" class="RequiredField userInput"/></td>
														<td id="child2<%out.print(i);%>" style="border-left: none"
															class="xl143" colspan="2" rowspan="${palletinfo.size}"><input
															style="width: 70px;" type="text" maxlength="30"
															name="palletlength<%out.print(i);%>"
															value="${palletinfo.length}"  class="RequiredField userInput"/></td>
														<td id="child3<%out.print(i);%>" style="border-top: none"
															class="xl90" rowspan="${palletinfo.size}"><input
															style="width: 30px;" type="text" maxlength="30"
															name="palletwidth<%out.print(i);%>"
															value="${palletinfo.width}"  class="RequiredField userInput"/></td>
														<td id="child4<%out.print(i);%>"
															style="border-top: none; border-left: none" class="xl91"
															rowspan="${palletinfo.size}"><input
															style="width: 50px;" type="text" maxlength="30"
															name="palletheight<%out.print(i);%>"
															value="${palletinfo.height}"  class="RequiredField userInput"/></td>
														<td id="child5<%out.print(i);%>"
															style="border-top: none; border-left: none" class="xl91"
															rowspan="${palletinfo.size}"><input
															style="width: 90px;" type="text" maxlength="30"
															name="palletweight<%out.print(i);%>"
															value="${palletinfo.weightPallet}"  class="RequiredField userInput" /></td>
														<%
															}
														%>
														<td style="border-top: none; border-left: none"
															class="xl91"><input style="width: 70px;" type="text" maxlength="30"
															name="pnchildraido<%out.print(++j);%>" value="${lists.pn}"  class="userInput RequiredField" /></td>
														<td style="border-top: none; border-left: none"
															class="xl92"><input style="width: 30px;" type="text"
															name="qtychildraido<%out.print(j);%>" maxlength="30"
															value="${lists.qty}"  class="RequiredField userInput"/></td>
														<td style="border-top: none; border-left: none"
															class="xl92"><input type="radio"
															id="ischild<%out.print(i);%>"
															name="ischild<%out.print(i);%>"
															value="childraido<%out.print(j);%>" /><input
															type="hidden" value="childraido<%out.print(j);%>"
															name="hiddenchild<%out.print(i);%>" /></td>
														<%
															if (index == 1) {
														%>
														<td id="child6<%out.print(i);%>"
															rowspan="${palletinfo.size}"><nobr>
																<input id="<%out.print(i);%>" style="width: 40px;"
																	type="button" value="add" onclick="addchild(this)" /><input
																	id="<%out.print(i);%>" style="width: 40px;"
																	type="button" value="del" onclick="removechild(this)" />
															</nobr><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
														<%
															}
														%>
													</tr>
													<%
														index++;
													%>
												</core:forEach>

											</core:forEach>
											<tr height="54"
												style="mso-height-source: userset; height: 40.5pt"
												id="isbegin">
												<td height="54" style="height: 40.5pt" class="xl169"
													colspan="2">Special instructions:</td>
												<td style="border-left: none" class="xl135" colspan="9"><textarea
														style="width: 600px;" 
														name="special_instructions" maxlength="80" >${intersup.special_instructions}</textarea></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt"
												class="xl69">
												<td height="29" style="height: 21.75pt" class="xl145"
													colspan="11">► Section 5 - Operation inforamtion</td>
											</tr>
											<tr height="30"
												style="mso-height-source: userset; height: 22.5pt"
												class="xl69">
												<td height="30" style="height: 22.5pt; border-top: none"
													class="xl73">Operation Mode</td>
												<td style="border-left: none" class="xl182" colspan="2"><form:input
														style="width: 130px;" type="text" path="operation_mode" maxlength="80"/></td>
												<td style="border-left: none" class="xl179" colspan="2">MBL</td>
												<td style="border-left: none" class="xl72" colspan="2"><form:input
														style="width: 130px;" type="text" path="mbl1" maxlength="80" /></td>
												<td style="border-left: none" class="xl179" colspan="2">HBL</td>
												<td style="border-left: none" class="xl176" colspan="2"><form:input
														style="width: 100px;" type="text" path="hbl1" maxlength="80" /></td>
											</tr>
											<tr height="30"
												style="mso-height-source: userset; height: 22.5pt"
												class="xl69">
												<td height="30" style="height: 22.5pt; border-top: none"
													class="xl84">MBL</td>
												<td class="xl178" colspan="2"><form:input
														style="width: 130px;" type="text" path="mbl2" maxlength="80"/></td>
												<td class="xl72" colspan="2">HBL</td>
												<td colspan="2" class="xl87" style="border-top: none"><form:input
														style="width: 130px;" type="text" path="hbl2" maxlength="80"/></td>
												<td colspan="2" class="xl85" style="border-top: none">&#12288;Remarks</td>
												<td style="border-left: none" class="xl179" colspan="2"><form:input
														style="width: 100px;" type="text" path="remarks1" maxlength="80" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl107"
													colspan="11">► Section 6 - Cargo Receipt with
													Signature</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td width="901" height="29"
													style="height: 21.75pt; width: 207pt" class="xl110"
													colspan="3">Supplier Signature for cargo hand-over to
													LSP</td>
												<td style="border-left: none" class="xl93" colspan="4">LSP
													Signature for receipt</td>
												<td style="border-left: none" class="xl93" colspan="4">Customer
													Signature</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td width="901" height="29"
													style="height: 21.75pt; width: 207pt" class="xl111"
													colspan="3">Cargo hand-over to LSP Date &amp; Time</td>
												<td style="border-left: none" class="xl95" colspan="4">Cargo
													received date &amp; Time</td>
												<td style="border-left: none" class="xl95" colspan="4">Cargo
													received date &amp; Time</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="87" style="height: 65.25pt" class="xl113"
													colspan="3"><textarea
														style="width: 250px; height: 100px;" 
														name="cargolsp_date" maxlength="80">${intersup.cargolsp_date} </textarea></td>
												<td class="xl122" colspan="4"><textarea
														style="width: 250px; height: 100px;" 
														name="cargoreceipt_date" maxlength="80">${intersup.cargoreceipt_date} </textarea></td>
												<td style="" class="xl98" colspan="4"><textarea
														style="width: 250px; height: 100px;"
														name="cargocust_date" maxlength="80">${intersup.cargocust_date} </textarea></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl169"
													colspan="2">Anji-Ceva Contact:</td>
												<td style="" class="xl138" colspan="5"><form:input
														style="width: 130px;" type="text" path="cargoanjicontant" maxlength="80"/></td>
												<td style="border-left: none" class="xl71">Tel No.</td>
												<td style="border-left: none" class="xl137" colspan="3"><form:input
														style="width: 130px;" type="text" path="cargotel" maxlength="20"/></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl160"
													colspan="2">Email addr.</td>
												<td style="" class="xl162" colspan="5"><form:input
														style="width: 130px;" type="text" path="cargoemail" maxlength="80"/></td>
												<td style="border-top: none; border-left: none" class="xl82">Fax
													No.</td>
												<td style="border-left: none" class="xl165" colspan="3"><form:input
														style="width: 130px;" type="text" path="cargofax" maxlength="30"/></td>
											</tr>
											<%
												String type = (String) request.getSession().getAttribute(
															"USERTYPE");
													if ("IT".equals(type)||"OPERATION".equals(type)) {
											%>
												<tr height="29"
													style="mso-height-source: userset; height: 21.75pt"> 
													<td height="29" style="height: 21.75pt" class="xl160" colspan="1">
													Status
													</td>
													<td height="29" style="height: 21.75pt" class="xl160" colspan="10">
													<form:select style="width: 130px;" type="text" path="status" >
									                  	<option value="PENDING">Pending</option>
									                  	<option value="APPROVED">Approved</option>
									                  	<option value="DISAPPROVED">Disapproved</option>
									                  	<option value="CANCELLED">Cancelled</option>
													</form:select>
													</td>
												</tr>
											<%}else{ %>
													<form:hidden path="status"/>
													<%} %>
											
											<tr height="0" style="display: none">
												<td width="901" style="width: 90pt"></td>
												<td width="901" style="width: 63pt"></td>
												<td width="901" style="width: 54pt"></td>
												<td width="901" style="width: 20pt"></td>
												<td width="901" style="width: 37pt"></td>
												<td width="901" style="width: 53pt"></td>
												<td width="901" style="width: 112pt"></td>
												<td width="901" style="width: 79pt"></td>
												<td width="901" style="width: 79pt"></td>
												<td width="901" style="width: 41pt"></td>
												<td width="901" style="width: 49pt"></td>
											</tr>
										</tbody>
									</table>
									<hr style="width: 99%;">
									<table border="0" cellpadding="1" cellspacing="1" width="100%">
										<tbody>
											<tr>
												<td style="padding-left: 2px"></td>
												<td style="padding-right: 2px;" align="right"
													nowrap="nowrap"><input name="btnDetails"
													value="Review" onClick="viewInfo()"
													id="btnDetails" class="Button" type="button" 
													style="width: 100px; text-align: center;"> <input
													name="btnAdd" value="save" onclick="saveInfo()" id="btnAdd"
													class="Button" type="button"  style="width: 90px; text-align: center;">
													<input name="btnDetails" value="Back"
													onClick="backList()" id="btnDetails" class="Button" type="button" 
													style="width: 100px; text-align: center;">
													<input type="button" value="exportToExcel" style="width: 100px; text-align: center;"  class="Button" onclick="exportExcel()" />
													</td>
											</tr>
										</tbody>
									</table>
								</fieldset></td>
						</tr>
					</tbody>
			  </table>
  </span> <span id="PageMessageUpdatePanel"></span> 

			<script LANGUAGE="JavaScript">
var i=999;
var j=999;
function addrow(){
$("#isbegin").before("<tr id='row"+(++i)+"' height='29' style='mso-height-source:userset;height:21.75pt'>"+
				  "<td id='child7"+i+"' rowspan='1'><input type='radio' name='isrow' value='"+i+"'/><input type='hidden' value='"+i+"' name='hiddenrow' /></td>"+
                  "<td id='child1"+i+"' height='29' style='height:21.75pt' class='xl143' colspan='1' rowspan='1'><input style='width:60px;' type='text'  name='palletqty"+i+"'  class='RequiredField userInput'/></td>"+
                  "<td id='child2"+i+"' style='border-left:none' class='xl143' colspan='2' rowspan='1'><input style='width:60px;' type='text' name='palletlength"+i+"' class='RequiredField userInput'/></td>"+
                  "<td id='child3"+i+"' style='border-top:none' class='xl90' rowspan='1'><input style='width:30px;' type='text' name='palletwidth"+i+"' class='RequiredField userInput'/></td>"+
                  "<td id='child4"+i+"' style='border-top:none;border-left:none' class='xl91' rowspan='1'><input style='width:50px;' type='text' name='palletheight"+i+"' class='RequiredField userInput'/></td>"+
                  "<td id='child5"+i+"' style='border-top:none;border-left:none' class='xl91' rowspan='1'><input style='width:90px;' type='text' name='palletweight"+i+"' class='RequiredField userInput'/></td>"+
                  "<td style='border-top:none;border-left:none' class='xl91'><input style='width:70px;' type='text' name='pnchildraido"+(++j)+"' class='RequiredField userInput'/></td>"+
                  "<td style='border-top:none;border-left:none' class='xl92'><input style='width:30px;' type='text' name='qtychildraido"+j+"' class='RequiredField userInput'/></td>"+
				  "<td style='border-top:none;border-left:none' class='xl92'><input type='radio' id='ischild"+i+"' name='ischild"+i+"' value='childraido"+(j)+"'/><input type='hidden' value='childraido"+j+"' name='hiddenchild"+i+"' /></td>"+
				  "<td id='child6"+i+"' rowspan='1'><nobr><input id='"+i+"' style='width:40px;' type='button' value='add' onclick='addchild(this)' />"+
				  "<input id='"+i+"' style='width:40px;' type='button' value='del' onclick='removechild(this)'/></nobr></td>"+
                "</tr>");			
}

function addchild(button){
var id=button.id;
var str="tr[id='row"+id+"']";
$(str).eq($(str).length-1).after("<tr id='row"+id+"' height='29' style='mso-height-source:userset;height:21.75pt'>"+
                    "<td style='border-top:none;border-left:none' class='xl91'><input style='width:70px;' type='text' name='pnchildraido"+(++j)+"' class='RequiredField userInput'/></td>"+
                  "<td style='border-top:none;border-left:none' class='xl92'><input style='width:30px;' type='text' name='qtychildraido"+j+"' class='RequiredField userInput'/></td>"+
				  "<td style='border-top:none;border-left:none' class='xl92'><input type='radio' id='ischild"+id+"' name='ischild"+id+"' value='childraido"+(j)+"'/><input type='hidden' value='childraido"+j+"' name='hiddenchild"+id+"' /></td>"+
                "</tr>");
var index=parseInt($("#child1"+id).attr("rowspan"))+1;
$("#child1"+id).attr("rowspan",index);
$("#child2"+id).attr("rowspan",index);
$("#child3"+id).attr("rowspan",index);
$("#child4"+id).attr("rowspan",index);
$("#child5"+id).attr("rowspan",index);
$("#child6"+id).attr("rowspan",index);
$("#child7"+id).attr("rowspan",index);
}

function delrow(){
	var selctntype=$("input[name='isrow']:radio:checked");
    if(selctntype.length==0){
    	alertForAnji('Please select one record.');
        return;
    }
	var id=selctntype.val();
	var trlist=$("tr #row"+id);
	$("tr #row"+id).each(function(){
	$(this).remove();
	});
}


function removechild(button){
var inflag=1;
var id=button.id;
var name="ischild"+button.id;
	var selctntype=$("input[name="+name+"]:radio:checked");
    if(selctntype.length==0){
    	alertForAnji('Please select one record.');
        return;
    }
if(selctntype.parent().parent().children().length!=3){
	//if($("#isbegin").length>0){
	var isid=selctntype.val();
	if(isid.indexOf("childraido")!=-1){
		var idvalue=isid.substring(10,isid.length);
		var index1=parseInt(idvalue)+(inflag++);
		var value2="childraido"+index1++;
		while(true){
			if($("input[id="+selctntype.attr("id")+"][value="+value2+"]:radio").length>0){
				if($("input[value="+value2+"]:radio").attr("name")!=selctntype.attr("name")){
					selctntype.parent().parent().remove();
					return;
				}
				var inputvalue1=$("input[value="+value2+"]:radio").parent().prev().children("input:text").val();
				var inputvalue2=$("input[value="+value2+"]:radio").parent().prev().prev().children("input:text").val();
				selctntype.parent().prev().children("input:text").val(inputvalue1);
				selctntype.parent().prev().prev().children("input:text").val(inputvalue2);
				$("input[value="+value2+"]:radio").parent().parent().remove();
				var index=parseInt($("#child1"+id).attr("rowspan"))-1;
				$("#child1"+id).attr("rowspan",index);
				$("#child2"+id).attr("rowspan",index);
				$("#child3"+id).attr("rowspan",index);
				$("#child4"+id).attr("rowspan",index);
				$("#child5"+id).attr("rowspan",index);
				$("#child6"+id).attr("rowspan",index);
				$("#child7"+id).attr("rowspan",index);
				return ;
			}
			if(index1>10000){
			return;
			}
			value2="childraido"+index1++;
		}
	}
}else{
	selctntype.parent().parent().remove();
	var index=parseInt($("#child1"+id).attr("rowspan"))-1;
	$("#child1"+id).attr("rowspan",index);
	$("#child2"+id).attr("rowspan",index);
	$("#child3"+id).attr("rowspan",index);
	$("#child4"+id).attr("rowspan",index);
	$("#child5"+id).attr("rowspan",index);
	$("#child6"+id).attr("rowspan",index);
	$("#child7"+id).attr("rowspan",index);
}
}


function changeUserInfo(box){
	$.ajax({
		method:"GET",
		url:"${webcontext}/ebooking/internationalsupplier/getUserInfo?code="+$(box).val()+"&currTime="+(new Date()).getTime(),
		dataType:"json",
		success:function(msg){
			$("#supplierCode").val(unescape(msg.supplierCode));
			$("#pickUpAddress").val(unescape(msg.pickUpAddress));
			$("#invoiceNo").val(unescape(msg.invoiceNo));
			$("#zipCode").val(unescape(msg.zipCode));
			$("#country").val(unescape(msg.country));
			$("#city").val(unescape(msg.city));
			$("#contactPerson").val(unescape(msg.contactPerson));
			$("#telNo").val(unescape(msg.telNo));
			$("#email").val(unescape(msg.email));
			$("#faxNo").val(unescape(msg.faxNo));
			
		}
		});
	$("#isshipperCode").val($(box).val());
	//	alert(msg.id);
	}

function exportExcel(){
	submitFormForAnji("addInfo_form","${webcontext}/ebooking/internationalsupplier/export",2);
}
</script>
			</form:form>
</body>
</html>
