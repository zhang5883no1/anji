<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>Add International Supplier</title>
<script type='text/javascript' src='${jspath}/jquery.jqprint-0.3.js'></script>
<script type='text/javascript' src='${jspath}/jquery-1.9.1.min.js'></script>

<script LANGUAGE="JavaScript">		
		
		function backList(){
			//window.history.back(-1); 
			submitFormForAnji("addInfo_form","${webcontext}/ebooking/internationalsupplier/returnadd",0);
//			$("#addInfo_form").submit();
		}
		
		function printWeb(){
			//$("#isbody").jqprint();
			bdhtml=window.document.body.innerHTML;
			starstr="<!--startprint-->"
			endstr="<!--isendprint-->"
			prnhtml=bdhtml.substr(bdhtml.indexOf(starstr)+17);
			prnhtml=prnhtml.substr(0,prnhtml.indexOf(endstr));
			window.document.body.innerHTML=prnhtml;
			window.print();
			window.document.body.innerHTML=bdhtml;
		}
		
    </script>
        <object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height="0" width="0"> 
	</object> 
</head>
<body leftMargin="0" topMargin="0" id="isbody">
	<form:form id="addInfo_form" name="addInfo_form" action="returnadd"
		method="post" modelAttribute="intersupplier">
		<form:hidden path="id" id="id" />
		<form:hidden path="status"/>
		<form:hidden path="shipperCode"/>
		<form:hidden path="internationSupplierNo"/>
		<form:hidden path="omsNo"/>
			<form:hidden path="userId"/>
			<form:hidden path="active" />
		<input type="hidden" value="${user.id}" name="isshipperCode"/>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr style="height: 40px">
				<td width="80%"><img id="header_imgMatrixLogo"
					src="${imagepath}/site_logo.gif" align="middle"
					style="border-width: 0px;" /></td>
				<td>
					<table cellpadding="0" cellspacing="0" width="100%" border="0"
						style="padding: 0 4 0 4;">
						<tr>
							<td align="right" nowrap="nowrap"><span id="header_upLinks">
									<table cellpadding="4" cellspacing="0" border="0">
										<tr>
											<td><a id="header_lnkHelp" disabled="disabled"
												class="HeaderLinkButton"
												onMouseOver="this.classname=&#39;HeaderLinkButtonItemOver&#39;;"
												onMouseOut="this.classname=&#39;HeaderLinkButton&#39;;">Help</a></td>
											<td class="HeaderLinkButton">|</td>
											<td><span id="header_lblClose" class="HeaderLinkButton"
												onMouseOver="this.className=&#39;HeaderLinkButtonItemOver&#39;;"
												onMouseOut="this.className=&#39;HeaderLinkButton&#39;;"
												onClick="ClosePage();">Close Dialog</span></td>
											<td style="width: 20px; text-align: center"
												onClick="AbortRequestHandler();"><img id="imgUpdating"
												alt="" src="${imagepath}/updating.gif"
												style="border-width: 0px; visibility: hidden; cursor: hand;" /></td>
										</tr>
									</table>
							</span></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr
				style="background-image:url(${imagepath}/menu_bar.png);background-repeat:repeat-x;height:10px">
				<td colspan="2"></td>
			</tr>
			
			<!--startprint--><table border="0" cellspacing="2" width="100%">
				<tbody>
					<tr>
						<td><span id="lblHeader" class="PageTitle">View
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
												style="mso-width-source: userset; mso-width-alt: 3360; width: 79pt"
												class="xl68">
											<col width="55"
												style="mso-width-source: userset; mso-width-alt: 1760; width: 41pt"
												class="xl68">
											<col width="65"
												style="mso-width-source: userset; mso-width-alt: 2080; width: 49pt"
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
												<td style="border-left: none" class="xl135" colspan="6">${user.supplierNameEn}</td>
												<td style="border-top: none; border-left: none" class="xl71">Supplier
													Code<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl135" colspan="3">${user.supplierCode}</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Pick up Address:<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl137" colspan="6">${user.pickUpAddress}</td>
												<td style="border-top: none; border-left: none" class="xl83">Invoice
													No<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl140" colspan="3">${user.invoiceNo}</td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt; border-top: none"
													class="xl70">Zip Code<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-top: none; border-left: none" class="xl71"
													colSpan="2">${user.zipCode}<form:hidden path="id" /></td>
												<td style="border-top: none" class="xl78" colSpan="2">Country<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl137" colspan="2">${user.country}</td>
												<td style="border-top: none; border-left: none" class="xl71">City<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-top: none" class="xl78" colSpan="3">${user.city}</td>
											</tr>
											<tr height="56"
												style="mso-height-source: userset; height: 42.0pt">
												<td height="56" style="height: 42.0pt; border-top: none"
													class="xl70">Contact person<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl135" colspan="5">${user.contactPerson}</td>
												<td style="border-left: none" class="xl149" colspan="2">Tel.
													No<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td width="901" style="border-left: none; width: 169pt"
													class="xl150" colspan="3">${user.telNo}</td>
											</tr>
											<tr height="41"
												style="mso-height-source: userset; height: 30.75pt">
												<td height="41" style="height: 30.75pt; border-top: none"
													class="xl70">Email addr.<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-left: none; width: 227pt"
													class="xl153" colspan="5">${user.email}</td>
												<td style="border-left: none" class="xl149" colspan="2">Fax
													No<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl135" colspan="3">${user.faxNo}</td>
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
													colspan="6">${intersupplier.cnee}<form:hidden path="cnee" /></td>
												<td style="border-top: none; border-left: none" class="xl72">Contact
													person<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl86" colspan="3">${intersupplier.contactperson}<form:hidden path="contactperson" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt"
												class="xl69">
												<td height="58" style="height: 43.5pt; border-top: none"
													class="xl184">Call Off number<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-bottom: .5pt solid black" class="xl186"
													colspan="6">${intersupplier.desttel}<form:hidden path="desttel" /></td>
												<td height="29"
													style="height: 21.75pt; border-top: none; border-left: none; border-bottom: .5px solid black"
													class="xl74">Contact number<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td
													style="border-left: none; border-bottom: .5px solid black"
													class="xl72" colspan="3">${intersupplier.destplantcode}<form:hidden path="destplantcode" /></td>
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
													style="border-top: none; border-left: none">${intersupplier.pol}<form:hidden path="pol" /></td>
												<td style="border-left: none" class="xl172" colspan="3">Port
													of Discharge<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-top: none; border-left: none" class="xl86">${intersupplier.pod}<form:hidden path="pod" /></td>
												<td style="border-left: none" class="xl172" colspan="2">Transshipment
													port</td>
												<td style="border-left: none" class="xl176" colspan="2">${intersupplier.transshipment_port}<form:hidden path="transshipment_port" /></td>
											</tr>
											<tr height="30"
												style="mso-height-source: userset; height: 22.5pt"
												class="xl69">
												<td height="30" style="height: 22.5pt; border-top: none"
													class="xl84">HS Code</td>
												<td class="xl178" colspan="2">${intersupplier.hsCode}<form:hidden path="hsCode" /></td>
												<td class="xl86" colspan="3">Package type<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="" class="xl178" colspan="2">${intersupplier.package_type}<form:hidden path="package_type" /></td>
												<td style="border-top: none; border-left: none" class="xl86">Remarks<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl179" colspan="2">${intersupplier.remarks}<form:hidden path="remarks" /></td>
											</tr>
											<tr height="41"
												style="mso-height-source: userset; height: 30.75pt">
												<td height="41" style="height: 30.75pt; border-top: none"
													class="xl70">Delivery date in Call off<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-left: none; width: 227pt"
													class="xl153" colspan="5">${intersupplier.deliveryDate1}<form:hidden path="deliveryDate1" /></td>
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
												<td style="border-left: none" class="xl171" colspan="4">${intersupplier.readyDate1}<form:hidden path="readyDate1" /></td>
												<td style="border-left: none" class="xl135" colspan="2">Pick
													up date<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl137" colspan="4">
													${intersupplier.pickUpDate1}<form:hidden path="pickUpDate1" />
												</td>
											</tr>
											<tr height="48"
												style="mso-height-source: userset; height: 36.0pt">
												<td width="901" height="48"
													style="height: 36.0pt; width: 153pt" class="xl196"
													colspan="2">Arrival Date to CFS / CY</td>
												<td width="901" style="width: 164pt" class="xl174"
													colspan="4">${intersupplier.arrivalDate1}<form:hidden path="arrivalDate1" /></td>
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
																		class="xl150" colspan="4">${intersupplier.transportationMode}<form:hidden path="transportationMode" /></td>
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
												<td style="border-left: none" class="xl137" colspan="4">${intersupplier.serviceLevel}<form:hidden path="serviceLevel" /></td>
												<td width="901" style="border-left: none; width: 165pt"
													class="xl75" colspan="2">Dangerous goods<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-left: none; width: 158pt"
													class="xl150" colspan="2"><input type="radio" disabled="disabled"
													checked="checked" name="dangerousGoods" value="0"
													style="vertical-align: middle"
													<core:if test="${intersupplier.dangerousGoods==0 }">checked</core:if> />
													Yes</td>
												<td style="border-left: none" class="xl137" colspan="2"><input
													name="dangerousGoods" value="1" type="radio" disabled="disabled"
													style="vertical-align: middle"
													<core:if test="${intersupplier.dangerousGoods==1 }">checked</core:if> />
													No<form:hidden path="dangerousGoods" /></td>
											</tr>
											<tr height="38"
												style="mso-height-source: userset; height: 28.5pt">
												<td height="38" style="height: 28.5pt; border-top: none"
													class="xl70">If FCL:</td>
												<td colspan="2">${intersupplier.fcl}<form:hidden path="fcl" /></td>
												<td style="border-top: none; border-left: none" class="xl76"
													colspan="2">Container No.:</td>
												<td width="901" style="width: 111pt" class="xl168"
													colspan="2">${intersupplier.container}<form:hidden path="container" /></td>
												<td class="xl71" style="border-top: none; border-left: none">Type
													of container:<span style="display: none">tainer:</span>
												</td>
												<td style="border-left: none" class="xl143" colspan="4">${intersupplier.containerType}<form:hidden path="containerType" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl169"
													colspan="2">Total pallets/packages:<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-top: none; border-left: none" class="xl77">${intersupplier.totalPallets}<form:hidden path="totalPallets" /></td>
												<td style="" class="xl193" colspan="3">Total Gross
													Weight/kg:<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl193" colspan="2">${intersupplier.totalGross}<form:hidden path="totalGross" /></td>
												<td style="border-top: none; border-left: none" class="xl71">Total
													CBM<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td style="border-left: none" class="xl137" colspan="2">${intersupplier.totalCbm}<form:hidden path="totalCbm" /></td>
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
																		type="radio" disabled="disabled" checked="checked" name="palletsLevel"
																		value="1" style="vertical-align: middle"
																		<core:if test="${intersupplier.palletsLevel==1 }">checked</core:if> />
																		1<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="2" type="radio" disabled="disabled"
																		style="vertical-align: middle"
																		<core:if test="${intersupplier.palletsLevel==2 }">checked</core:if> />
																		2<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="3" type="radio" disabled="disabled"
																		style="vertical-align: middle"
																		<core:if test="${intersupplier.palletsLevel==3 }">checked</core:if> />
																		3<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="4" type="radio" disabled="disabled"
																		style="vertical-align: middle"
																		<core:if test="${intersupplier.palletsLevel==4 }">checked</core:if> />
																		4<span style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</span> <input name="palletsLevel" value="5" type="radio" disabled="disabled"
																		style="vertical-align: middle"
																		<core:if test="${intersupplier.palletsLevel==5 }">checked</core:if> />
																		5<form:hidden path="palletsLevel" />
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
												<td height="42" style="height: 31.5pt" class="xl181"
													colspan="2">Pallet qty<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td style="border-left: none" class="xl135" colspan="2">Length<span
													style="font-weight: bold; color: #F00;">*</span></td>
												<td width="901" style="border-top: none; width: 112pt" colspan="2"
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
												<td width="901" colspan="2"
													style="border-top: none; border-left: none; width: 49pt"
													class="xl80">QTY <span
													style="font-weight: bold; color: #F00;">*</span></td>
											</tr>
											<%
												int i = 1;
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
													<tr id="row<%out.print(i);%>" height="29"
														style="mso-height-source: userset; height: 21.75pt">
														<%
															if (index == 1) {
														%>
														<td id="child7<%out.print(i);%>" colspan="2"
															rowspan="${palletinfo.size}"><input type="hidden"
															name="isrow" value="<%out.print(i);%>" /><input
															type="hidden" value="<%out.print(i);%>" name="hiddenrow" />
														<input
															style="width: 60px;" type="hidden"
															name="palletqty<%out.print(i);%>"
															value="${palletinfo.palletQty}" />${palletinfo.palletQty}</td>
														<td id="child2<%out.print(i);%>" style="border-left: none"
															class="xl143" colspan="2" rowspan="${palletinfo.size}"><input
															style="width: 70px;" type="hidden"
															name="palletlength<%out.print(i);%>"
															value="${palletinfo.length}" />${palletinfo.length}</td>
														<td id="child3<%out.print(i);%>" style="border-top: none" colspan="2"
															class="xl90" rowspan="${palletinfo.size}"><input
															style="width: 30px;" type="hidden"
															name="palletwidth<%out.print(i);%>"
															value="${palletinfo.width}" />${palletinfo.width}</td>
														<td id="child4<%out.print(i);%>"
															style="border-top: none; border-left: none" class="xl91"
															rowspan="${palletinfo.size}"><input
															style="width: 50px;" type="hidden"
															name="palletheight<%out.print(i);%>"
															value="${palletinfo.height}" />${palletinfo.height}</td>
														<td id="child5<%out.print(i);%>"
															style="border-top: none; border-left: none" class="xl91"
															rowspan="${palletinfo.size}"><input
															style="width: 90px;" type="hidden"
															name="palletweight<%out.print(i);%>"
															value="${palletinfo.weightPallet}" />${palletinfo.weightPallet}</td>
														<%
															}
														%>
														<td style="border-top: none; border-left: none"
															class="xl91"><input style="width: 70px;" type="hidden"
															name="pnchildraido<%out.print(i);%>" value="${lists.pn}" />${lists.pn}</td>
														<td style="border-top: none; border-left: none" colspan="2"
															class="xl92"><input style="width: 30px;" type="hidden"
															name="qtychildraido<%out.print(i);%>"
															value="${lists.qty}" />${lists.qty}
														<input type="hidden"
															id="ischild<%out.print(i);%>"
															name="ischild<%out.print(i);%>"
															value="childraido<%out.print(i);%>" /><input
															type="hidden" value="childraido<%out.print(i);%>"
															name="hiddenchild<%out.print(i);%>" /></td>
														<%
															if (index == 1) {
														%>
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
												<td style="border-left: none" class="xl135" colspan="9">${intersupplier.special_instructions}<form:hidden path="special_instructions" /></td>
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
												<td style="border-left: none" class="xl182" colspan="2">${intersupplier.operation_mode}<form:hidden path="operation_mode" /></td>
												<td style="border-left: none" class="xl179" colspan="2">MBL</td>
												<td style="border-left: none" class="xl72" colspan="2">${intersupplier.mbl1}<form:hidden path="mbl1" /></td>
												<td style="border-left: none" class="xl179" colspan="2">HBL</td>
												<td style="border-left: none" class="xl176" colspan="2">${intersupplier.hbl1}<form:hidden path="hbl1" /></td>
											</tr>
											<tr height="30"
												style="mso-height-source: userset; height: 22.5pt"
												class="xl69">
												<td height="30" style="height: 22.5pt; border-top: none"
													class="xl84">MBL</td>
												<td class="xl178" colspan="2">${intersupplier.mbl2}<form:hidden path="id" /></td>
												<td class="xl72" colspan="2">HBL</td>
												<td colspan="2" class="xl87" style="border-top: none">${intersupplier.hbl2}<form:hidden path="hbl2" /></td>
												<td colspan="2" class="xl85" style="border-top: none">Remarks</td>
												<td style="border-left: none" class="xl179" colspan="2">${intersupplier.remarks1}<form:hidden path="remarks1" /></td>
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
													colspan="3">${intersupplier.cargolsp_date}<form:hidden path="cargolsp_date" /></td>
												<td class="xl122" colspan="4">${intersupplier.cargoreceipt_date} <form:hidden path="cargoreceipt_date" /></td>
												<td style="" class="xl98" colspan="4">${intersupplier.cargocust_date}<form:hidden path="cargocust_date" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl169"
													colspan="2">Anji-Ceva Contact:</td>
												<td style="" class="xl138" colspan="5">${intersupplier.cargoanjicontant}<form:hidden path="cargoanjicontant" /></td>
												<td style="border-left: none" class="xl71">Tel No.</td>
												<td style="border-left: none" class="xl137" colspan="3">${intersupplier.cargotel}<form:hidden path="cargotel" /></td>
											</tr>
											<tr height="29"
												style="mso-height-source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl160"
													colspan="2">Email addr.</td>
												<td style="" class="xl162" colspan="5">${intersupplier.cargoemail}<form:hidden path="cargoemail" /></td>
												<td style="border-top: none; border-left: none" class="xl82">Fax
													No.</td>
												<td style="border-left: none" class="xl165" colspan="3">${intersupplier.cargofax}<form:hidden path="cargofax" /></td>
											</tr>
											<core:forEach items=""></core:forEach>
										
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
									</table><!--isendprint-->
									<hr style="width: 99%;">
									<table border="0" cellpadding="1" cellspacing="1" width="100%">
										<tbody>
											<tr>
												<td style="padding-left: 2px"></td>
												<td style="padding-right: 2px;" align="right"
													nowrap="nowrap"><input
													name="btnAdd" value="print" onclick="printWeb()" id="btnAdd"
													class="Button" type="button"  style="width: 90px; text-align: center;">
													<input name="btnDetails" value="Back"
													onClick="backList()" id="btnDetails" class="Button" type="button" 
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
			</form:form>
</body>
</html>
