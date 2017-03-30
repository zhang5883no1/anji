<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../../include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>

<%@include file="../../include/baseJsAndCss.jsp"%>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>Add International Supplier</title>
<script type='text/javascript'
	src='${jspath}/My97DatePicker/WdatePicker.js'></script>
<script LANGUAGE="JavaScript">
	function saveInfo() {
		if ($("input[name='isrowindex']").length > 0) {
			
			if ($("#checkflag").val() == "0") {
			if (validateForm()) {
					submitFormForAnji("addDomestic_Supplier_form","${webcontext}/ebooking/domesticSupplier/saveDomesticSupplier",0);
					//					$("#addDomestic_Supplier_form").submit();
				}
			}
		} else {
			addrow();
		}

	}

	function backList() {
		submitHrefForAnji("${webcontext}/ebooking/domesticSupplier/init",null);
	}

	function viewInfo() {
		$("#addDomestic_Supplier_form").attr("action", "viewinfo");
		submitFormForAnji("addDomestic_Supplier_form","${webcontext}/ebooking/domesticSupplier/viewinfo", 0);
		//		$("#addDomestic_Supplier_form").submit();
	}
	function validexist(txtbox){
		if($("#isvalue").val()==$(txtbox).val()){
			$(txtbox).parent().children(".validSpan").remove();
			$("#checkflag").val("0");
			return;
		}
		$.ajax({
			method:"GET",
			url:"${webcontext}/ebooking/domesticSupplier/validexist?code="+$(txtbox).val()+"&currTime="+(new Date()).getTime(),
			success:function(msg){
				if(msg=="isexist"){
					//alert("CustomerCode is not exist");
					$(txtbox).parent().children(".validSpan").remove();
					$(txtbox).parent().append("<span class='validSpan'>Delivery Note No is exist</span>");
					$("#checkflag").val("1");
					//$("#btnSave").hide();
				}else{
					$(txtbox).parent().children(".validSpan").remove();
					$(txtbox).next().next().val(msg);
					$("#checkflag").val("0");
				}
			}
		});
	}
	
	$(document).ready(
  			function(){
  				if("saved successfully"=="${resultInfo}"){
  					alert("${resultInfo}");
  					submitHrefForAnji("${webcontext}/ebooking/domesticSupplier/init",null);
  				}
  			});
	
	function changeInfo(button){
		$("#consigneeAddress").val($("#isconsigneeAddress"+button.selectedIndex).val());
	}
	
	function istab(button){
		$(button).parent().next().children().eq(0).focus();
	}
</script>
</head>
<body leftMargin="0" topMargin="0">
	<%@include file="../../include/head.jsp"%>
	<br />
	<form:form id="addDomestic_Supplier_form"
		name="addDomestic_Supplier_form" action="saveDomesticSupplier"
		method="post" modelAttribute="domestic">
		<form:hidden path="id" id="id" />
		<form:hidden path="omsNo" />
		<form:hidden path="carrier" />
		<form:hidden path="active" />
		<form:hidden path="shipperCode" />
		<form:hidden path="destCode" />
		<form:hidden path="userId"/>
		<input type="hidden" id="checkflag" value="0" />
		<input type="hidden" name="flag" value="1" />
		<input type="hidden" id="isvalue" value="${domestic.supplierNo }" />
		<table border="0" cellspacing="2" width="100%">
			<tbody>
				<tr>
					<td><span id="lblHeader" class="PageTitle">Add Domestic
							Supplier</span></td>
				</tr>
			</tbody>
		</table>


		<span id="UpdatePanelSearch">
			<style>
table.gridTable td {
	padding-left: 5px;
	font-size: 8pt;
}

.xl149 {
	background-color: #F90;
	color: #fff;
	font-weight: bold;
}
</style>
			<table cellpadding="3" cellspacing="1" width="100%">
				<tbody>
					<tr>
						<td><fieldset>
								<legend id="legSearchCriteria" class="legend">Info</legend>
								<div style="width: 100%; overflow: auto;">
									<table id=zhangcheng"" class="gridTable" width="1000" cellspacing="0"
										cellpadding="0" border="1"
										style="border-collapse: collapse; table-layout: fixed; width: 1000pt">
										<colgroup>
											<col width="61"
												style="mso-width-source: userset; mso-width-alt: 1952; width: 30pt"
												class="xl65">
											<col width="169"
												style="mso-width-source: userset; mso-width-alt: 5408; width: 60pt"
												class="xl65">
											<col width="150"
												style="mso-width-source: userset; mso-width-alt: 4800; width: 60pt"
												class="xl65">
											<col width="184"
												style="mso-width-source: userset; mso-width-alt: 5888; width: 60pt"
												class="xl65">
											<col width="113"
												style="mso-width-source: userset; mso-width-alt: 3616; width: 60pt"
												class="xl65">
											<col width="112"
												style="mso-width-source: userset; mso-width-alt: 3584; width: 60pt"
												class="xl65">
											<col width="175"
												style="mso-width-source: userset; mso-width-alt: 5600; width: 60pt"
												class="xl65">
											<col width="151"
												style="mso-width-source: userset; mso-width-alt: 4832; width: 60pt"
												class="xl65">
											<col width="111"
												style="mso-width-source: userset; mso-width-alt: 3552; width: 60pt"
												class="xl65">
											<col width="123"
												style="mso-width-source: userset; mso-width-alt: 3936; width: 60pt"
												class="xl65">
											<col width="105"
												style="mso-width-source: userset; mso-width-alt: 3360; width: 60pt"
												class="xl65">
											<col width="113"
												style="mso-width-source: userset; mso-width-alt: 3616; width: 25pt"
												class="xl65">
											<col width="114"
												style="mso-width-source: userset; mso-width-alt: 3648; width: 40pt"
												class="xl65">
											<col width="115"
												style="mso-width-source: userset; mso-width-alt: 3680; width: 16pt"
												class="xl65">
											<col width="114"
												style="mso-width-source: userset; mso-width-alt: 3648; width: 46pt"
												class="xl65">
											<col width="170"
												style="mso-width-source: userset; mso-width-alt: 5440; width: 60pt"
												class="xl65">

										</colgroup>
										<tbody>
											<tr height="56"
												style="mso-height- source: userset; height: 42.0pt">
												<td width="1530" valign="top" height="155" align="left"
													style="height: 116.25pt; width: 173pt" rowspan="2"
													colspan="2"><span
													style="mso- ignore: vglayout; position: absolute; z-index: 1;  margin-top: 9px; width: 113px; height: 107px"><img
														width="113" height="107" v:shapes="Picture_x0020_1"
														src="${imagepath}/image002.png" /></span><span
													style="mso- ignore: vglayout2">
														<table cellspacing="0" cellpadding="0">


															<tbody>


																<tr>


																	<td width="230" height="155"
																		style="height: 116.25pt; width: 173pt" class="xl124"
																		rowspan="3" colspan="2"><a name="Print_Area">&#12288;</a></td>


																</tr>


															</tbody>
														</table>
												</span></td>
												<td colspan="13" class="xl76"
													style="width: 1148pt; text-align: center; font-size: 20px; font-weight: bold; background-color: #284799; color: #FFF;">Domestic
													Supplier 发运单</td>
												<td width="1530"
													style="width: 128pt; background-color: #F60; font-size: 20px; color: #fff; text-align: center;"
													class="xl131">V1.1</td>
											</tr>
											<tr height="70"
												style="mso-height- source: userset; height: 52.5pt">
												<td width="1530" height="70"
													style="height: 52.5pt; width: 113pt" class="xl91">Pickup
													Date<br> 计划提 货时间<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td colspan="3" class="xl69" style="width: 138pt"><form:input
														cssClass="RequiredField userInput" type="text"
														path="pickupDate1" onfocus="WdatePicker()" /></td>
												<td width="1530" style="width: 131pt" class="xl92">Delivery Note No.<br> 发运单编号<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td class="xl109" colspan="2">&#12288;<form:input
														cssClass="RequiredField userInput" path="supplierNo" maxlength="30"
														onblur="validexist(this)" /></td>
												<td width="1530" style="width: 92pt" class="xl73">Invoice
													No.<br> 发票号
												</td>
												<td class="xl109" colspan="2"><form:input
														cssClass="userInput" path="invoiceNo" maxlength="30"/></td>
												<td width="1530" style="width: 86pt" class="xl73">Carrier<br>
													物流承运商
												</td>
												<td width="1530" style="width: 300pt" class="xl97"
													colspan="3">Anji-CEVA<br> 安吉汽车零部件物流有限公司
												</td>
											</tr>
											<tr height="13"
												style="mso-height- source: userset; height: 9.95pt">
												<td height="13"
													style="height: 9.95pt; background-color: #CCC;"
													class="xl128" colspan="16">&#12288;</td>
											</tr>
													
											<tr height="48"
												style="mso-height- source: userset; height: 36.0pt">
												<td height="48" style="height: 36.0pt;" class="xl71">&#12288;</td>
												<td width="1530" style="width: 127pt" class="xl93">Supplier
													<br> 供应商<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td colspan="5" class="xl74" style="width: 113pt">
												<select onchange="changeUserInfo(this)" >
													<core:forEach var="userlist" items="${userlist}">
													<core:if test="${user.id==userlist.id}"><option value="${userlist.id}" selected>${userlist.supplierCode}</option></core:if>
													<core:if test="${user.id!=userlist.id}"><option value="${userlist.id}" >${userlist.supplierCode}</option></core:if>
													</core:forEach>
													</select><input type="hidden" name="isshipperCode" id="isshipperCode" value="${user.id}" />
													<input type="text" id="supplierNameCn" name="supplierNameCn" value="${user.supplierNameCn}" disabled="disabled" /></td>
												<td width="1530" style="width: 113pt" class="xl93">Supplier<span
													style="mso- spacerun: yes">&nbsp; </span>Adderss<br>
													供应商地址<span style="font-weight: bold; color: #F00;">*</span></td>
												<td width="1530" style="width: 725pt" class="xl129"
													colspan="8">
													<input type="text" id="addressCn" name="addressCn" value="${user.addressCn}" disabled="disabled" style="width:300px;"/>
													</td>
											</tr>
											<tr height="48"
												style="mso-height- source: userset; height: 36.0pt">
												<td width="1530" height="48"
													style="height: 36.0pt; width: 46pt" class="xl72">&#12288;</td>
												<td width="1530" style="width: 127pt" class="xl94">Supplier
													Code<br> 供应商 编号<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td colspan="5" class="xl70" style="width: 113pt">
													<input type="text" id="supplierCode" name="supplierCode" value="${user.supplierCode}" disabled="disabled" />
													</td>
												<td width="1530" style="width: 113pt" class="xl94">Supplier
													Contact<br> 供应 商联系人<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 254pt" class="xl105"
													colspan="3">
													<input type="text" id="contactPerson" name="contactPerson" value="${user.contactPerson}" disabled="disabled" />
													</td>
												<td width="1530" style="width: 171pt" class="xl107"
													colspan="2">Supplier Tel<br> 供应商电话<span
													style="font- weight: bold; color: #F00;">*</span></td>
												<td width="1530" style="width: 300pt" class="xl105"
													colspan="3">
													<input type="text" id="telNo" name="telNo" value="${user.telNo}" disabled="disabled" />
													</td>
											</tr>
											<tr height="64"
												style="mso-height- source: userset; height: 48.0pt">
												<td height="64" style="height: 48.0pt" class="xl66">&#12288;</td>
												<td width="1530" style="width: 127pt" class="xl70">Consignee<br>
													收货方
												</td>
												<td width="1530" style="width: 551pt" class="xl97"
													colspan="5">
													<form:select id="typeSel" path="consignee" cssClass="userInput" style="width:300px;" onchange="changeInfo(this)">
														<core:forEach items="${destlist}" var="dest">
														<form:option value="${dest.plantNameCn }">${dest.plantNameCn }</form:option>
														</core:forEach>
													</form:select>
													<input type="hidden" value="" /></td>
												<td width="1530" style="width: 113pt" class="xl70">Consignee
													Address<br> 收货 方地址
												</td>
												<td width="1530" style="width: 725pt" class="xl105"
													colspan="8">
													<%int consigneeAddressindex=0; %>
														<core:forEach items="${destlist}" var="dest">
														<input type="hidden" id="isconsigneeAddress<%=consigneeAddressindex++ %>" value="${dest.addressCn }" />
														</core:forEach>
													<core:if test="${domestic.id!=null}">
													<input name="consigneeAddress" id="consigneeAddress" value="${domestic.consigneeAddress }"  cssClass="userInput" style="width:300px;;background:#DFDFDF" readonly="readonly"/>
													</core:if>
													<core:if test="${domestic.id==null}">
														<%int isindex=0; %>
														<core:forEach items="${destlist}" var="dest">
														<%if(isindex==0){ %>
															<input name="consigneeAddress" id="consigneeAddress" value="${dest.addressCn }"  cssClass="userInput" style="width:300px;;background:#DFDFDF" readonly="readonly"/>
														<%isindex++;
														} %>
														</core:forEach>
													</core:if>
												</td>
											</tr>
											<tr height="13"
												style="mso-height- source: userset; height: 9.95pt">
												<td height="13"
													style="height: 9.95pt; background-color: #CCC;"
													class="xl128" colspan="16">&#12288;</td>
											</tr>
											
											<tr height="48"
												style="mso-height- source: userset; height: 36.0pt">
												<td width="1530" height="48"
													style="height: 36.0pt; width: 837pt; background-color: #dfdfdf; font-weight: bold;"
													class="xl100" colspan="8">Call-off Demands<br>
													订单需求<span style="font-weight: bold; color: #F00;">*</span></td>
												<td width="1530"
													style="width: 254pt; background-color: #dfdfdf; font-weight: bold;"
													class="xl100" colspan="3">Booking<br> 供应商确认量
												</td>
												<td width="1530"
													style="width: 171pt; background-color: #dfdfdf; font-weight: bold;"
													class="xl95" colspan="2">LSP<br> 承运商接收数量
												</td>
												<td width="1530"
													style="width: 172pt; background-color: #dfdfdf; font-weight: bold;"
													class="xl99" colspan="2">Receiver<br> 工厂接收量
												</td>
												<td width="1530"
													style="width: 128pt; background-color: #dfdfdf; font-weight: bold;"
													class="xl75">Comment<br> 备注
												</td>
											</tr>
											<tr height="86"
												style="mso-height- source: userset; height: 64.5pt"
												class="xl67">
												<td width="1530" height="86"
													style="height: 64.5pt; width: 46pt" class="xl81">IND<br>
													序号
												</td>
												<td width="1530" style="width: 127pt" class="xl82">Part
													No.<br> 零件号 <span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 113pt" class="xl82">Calling
													Date<span style="font-weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 138pt" class="xl82">Description<br>
													零件名<span style="font-weight: bold; color: #F00;">*</span></td>
												<td width="1530" style="width: 85pt" class="xl82">Total
													Qty.<br> 零件数 量<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 84pt" class="xl82">Packing
													Type<br> 料箱种 类<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 131pt" class="xl82">Pcs/pallet<br>
													件数/托盘<span style="font-weight: bold; color: #F00;">*</span></td>
												<td width="1530" style="width: 113pt" class="xl83">Total
													Pallets<br> 托盘 数量<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 83pt" class="xl82">Total
													Qty.<br> 零件数 量<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 92pt" class="xl82">Total
													Pallets<br> 托盘 数量<span
													style="font- weight: bold; color: #F00;">*</span>
												</td>
												<td width="1530" style="width: 79pt" class="xl83">Weight(KG)<br>
													总重量<span style="font-weight: bold; color: #F00;">*</span><br>
													（千克）
												</td>
												<td width="1530" style="width: 171pt" class="xl81"
													colspan="2">Total Pallets<br> 托盘数量
												</td>
												<td width="1530" style="width: 172pt" class="xl81"
													colspan="2">Total Pallets<br> 托盘数量
												</td>
												<td class="xl84">&#12288;</td>
											</tr>
											<%
												int i = 0;
											%>
											<core:forEach items="${demands}" var="demand">
												<tr height="53"
													style="mso- height-source: userset; height: 39.75pt">
													<td height="53" style="height: 39.75pt;" class="xl85"><span
														name="isindex"> <%
 	out.print((++i));
 %>
													</span><input type="radio" name="isrowi" value="<%out.print(i);%>"><input
														type="hidden" value="<%out.print(i);%>" name="isrowindex" /></td>
													<td class="xl86"><input type="text"
														name="partNo<%out.print(i);%>" value="${demand.partNo}"
														class="RequiredField userInput" onkeyup="valid(this)" style="width:70px;" /><input
														type="hidden" id="isweight" value="" /></td>
													<td class="xl87"><input type="text"
														name="callingDate<%out.print(i);%>"
														value="${demand.callingDate}" readonly="readonly" 
														class="RequiredField userInput" style="width:70px;background:#DFDFDF" onfocus="istab(this)"/></td>
													<td class="xl88"><input type="text"
														name="description<%out.print(i);%>"
														value="${demand.description}"
														class="RequiredField userInput" style="width: 70px;"/></td>
													<td class="xl89"><input style="width: 50px;"
														type="text" name="totalQtyA<%out.print(i);%>"
														value="${demand.totalQtyA}"
														class="RequiredField userInput" /></td>
													<td class="xl89"><input style="width: 60px;"
														type="text" name="packingType<%out.print(i);%>"
														value="${demand.packingType}"
														class="RequiredField userInput" /></td>
													<td class="xl89"><input type="text"
														name="posPallet<%out.print(i);%>"
														value="${demand.posPallet}"
														class="RequiredField userInput" style="width: 70px;" /></td>
													<td class="xl90"><input type="text"
														name="totalPalletsA<%out.print(i);%>"
														value="${demand.totalPalletsA}"
														class="RequiredField userInput" style="width: 70px;"/></td>
													<td class="xl89"><input style="width: 60px;"
														type="text" name="totalQtyB<%out.print(i);%>"
														value="${demand.totalQtyB}"
														class="RequiredField userInput" id="totalQty" /></td>
													<td class="xl89"><input style="width: 60px;"
														type="text" name="totalPalletsB<%out.print(i);%>"
														value="${demand.totalPalletsB}"
														class="RequiredField userInput" /></td>
													<td class="xl90"><input style="width: 60px;"
														type="text" name="weight<%out.print(i);%>"
														value="${demand.weight}" class="RequiredField userInput"
														onkeyup="validWeight(this)" /></td>
													<td class="xl85" colspan="2"><input type="text"
														name="totalPalletsC<%out.print(i);%>"
														value="${demand.totalPalletsC}" class="userInput" style="width: 70px;"/></td>
													<td class="xl85" colspan="2"><input type="text"
														name="totalPalletsD<%out.print(i);%>"
														value="${demand.totalPalletsD}" class="userInput" style="width: 70px;"/></td>
													<td class="xl90"><input type="text"
														name="comments<%out.print(i);%>"
														value="${demand.comments}" class="userInput" style="width: 70px;"/></td>
												</tr>
											</core:forEach>

											<tr height="0" style="display: none" id="lastrow">
												<td width="1530" style="width: 46pt"></td>
												<td width="1530" style="width: 127pt"></td>
												<td width="1530" style="width: 113pt"></td>
												<td width="1530" style="width: 138pt"></td>
												<td width="1530" style="width: 85pt"></td>
												<td width="1530" style="width: 84pt"></td>
												<td width="1530" style="width: 131pt"></td>
												<td width="1530" style="width: 113pt"></td>
												<td width="1530" style="width: 83pt"></td>
												<td width="1530" style="width: 92pt"></td>
												<td width="1530" style="width: 79pt"></td>
												<td width="1530" style="width: 85pt"></td>
												<td width="1530" style="width: 86pt"></td>
												<td width="1530" style="width: 86pt"></td>
												<td width="1530" style="width: 86pt"></td>
												<td width="1530" style="width: 128pt"></td>
											</tr>
											<%
												String type = (String)

													request.getSession().getAttribute("USERTYPE");
													if ("IT".equals(type)||"OPERATION".equals(type)) {
											%>
											<tr height="29"
												style="mso-height- source: userset; height: 21.75pt">
												<td height="29" style="height: 21.75pt" class="xl160"
													colspan="1">Status</td>
												<td height="29" style="height: 21.75pt" class="xl160"
													colspan="10"><form:select style="width: 130px;"
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



									<hr style="width: 99%;">
									<table border="0" cellpadding="1" cellspacing="1" width="100%">
										<tbody>
											<tr>
												<td style="padding-left: 2px" align="left" nowrap="nowrap">
													<input type="button" value="addrow" onclick="addrow()" />
													<input type="button" value="delrow" onclick="delrow()" />
												</td>
												<td style="padding- right: 2px;" align="right"
													nowrap="nowrap"><input name="btnDetails"
													value="Review" onClick="viewInfo()" id="btnDetails"
													class="Button" type="button"
													style="width: 100px; text-align: center;"> <input
													name="btnAdd" value="save" onclick="saveInfo()" id="btnAdd"
													class="Button" type="button"
													style="width: 90px; text-align: center;"> <input
													name="btnDetails" value="Back" onClick="backList()"
													id="btnDetails" class="Button" type="button"
													style="width: 100px; text-align: center;"><input type="button" value="exportToExcel" onclick="exportExcel()" style="width: 100px; text-align: center;"  class="Button"/></td>
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
				$("#lastrow").before(" <tr height='53' style='mso-height-source:userset;height:39.75pt'>"+ "<td height='53' style='height:39.75pt;' class='xl85'><span name='isindex'>"
										+ (++index)
										+ "</span><input type='radio' name='isrowi' value='"+index+"'><input type='hidden' value='"+index+"' name='isrowindex' /></td>"
										+ "<td  class='xl86'><input type='text' name='partNo"
										+ index+ "'  class='RequiredField userInput' onkeyup='valid(this)' style='width:70px;'/><input type='hidden' id='isweight' value=''/></td>"
										+ "<td  class='xl87'><input type='text' name='callingDate"
										+ index+ "' class='RequiredField userInput' value='${isTime}' style='width:70px;background:#DFDFDF' readonly='readonly' onfocus='istab(this)'/></td>"
										+ "<td  class='xl88'><input type='text' name='description"+index+"'  class='RequiredField userInput' style='width:70px;'/></td>"
										+ "<td  class='xl89'><input style='width:50px;' type='text' name='totalQtyA"+index+"'  class='RequiredField userInput' /></td>"
										+ "<td  class='xl89'><input style='width:60px;' type='text' name='packingType"+index+"'  class='RequiredField userInput'/></td>"
										+ "<td  class='xl89'><input type='text' name='posPallet"+index+"'  class='RequiredField userInput' style='width:70px;'/></td>"
										+ "<td  class='xl90'><input type='text' name='totalPalletsA"+index+"'  class='RequiredField userInput' style='width:70px;'/></td>"
										+ "<td  class='xl89'><input style='width:60px;' type='text' name='totalQtyB"+index+"'  class='RequiredField userInput'/></td>"
										+ "<td  class='xl89'><input style='width:60px;' type='text' name='totalPalletsB"+index+"'  class='RequiredField userInput'/></td>"
										+ "<td  class='xl90'><input style='width:60px;' type='text' name='weight"
										+ index
										+ "'  class='RequiredField userInput' onkeyup='validWeight(this)' /></td>"
										+ "<td  class='xl85' colspan='2'><input type='text' name='totalPalletsC"+index+"'  class='userInput' style='width:70px;'/></td>"
										+ "<td  class='xl85' colspan='2'><input type='text' name='totalPalletsD"+index+"'  class='userInput' style='width:70px;'/></td>"
										+ "<td  class='xl90'><input type='text' name='comments"+index+"'  class='userInput' style='width:70px;'/></td>"
										+ "</tr>");

				sortIndex();
			}

			function delrow() {
				var selctntype = $("input:radio:checked");
				if (selctntype.length == 0) {
					alert('Please select one record.');
					return;
				}
				selctntype.parent().parent().remove();

				sortIndex();
			}

			function sortIndex() {
				var spanlist = $("span[name='isindex']");
				spanlist.each(function(i) {
					$(this).html(i + 1);
				});
			}

			$(document).ready(function() {
				$("#pickupDate").val($("#isdate").val());
			});

			function valid(txtbox) {
				$("#checkflag").val("0");
			}
			function valid1(txtbox) {
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
									validWeight($(txtbox).parent().parent().children().eq(10).children().eq(0));
								}
							}
						});

			}
			function validWeight(button) {
				$("#checkflag").val("0");
			}
			function validWeight1(button) {
				$("#checkflag").val("1");
				if($(button).parent().parent().children().eq(1).children().eq(1).val()==""){
					valid($(button).parent().parent().children().eq(1).children().eq(0));
				}
			
				$(button).parent().children(".validSpan").remove();
				//alert($(button).parent().parent().children().eq(1).children().eq(1).val());
				var weight = $(button).parent().parent().children().eq(1)
						.children().eq(1).val();
				var qty = $(button).parent().prev().prev().children().eq(0)
						.val();
				if (weight != "") {
					if ((parseFloat(weight * 100) / 100)* (parseFloat(qty * 100) / 100) != $(button).val()) {
						$(button).parent().append("<span class='validSpan'>Weight is error</span>");
					} else {
						$("#checkflag").val("0");
					}
				}

			}

			function validatecode(txtbox) {
				$("#checkflag").val("1");
				$.ajax({
							method : "GET",
							url : "${webcontext}/ebooking/domesticSupplier/validcode?code="
									+ $(txtbox).val()+"&currTime="+(new Date()).getTime(),
							success : function(msg) {
								if (msg == "false") {
									//alert("CustomerCode is not exist");
									$(txtbox).parent().children(".validSpan").remove();
									$(txtbox).parent().append("<span class='validSpan'> is not exist</span>");
									$(txtbox).parent().next().next().children(":text").val("");
									//$("#btnSave").hide();
								} else {
									$(txtbox).parent().children(".validSpan")
											.remove();
									$(txtbox).parent().next().next().children(":text").val(msg);
									$("#checkflag").val("0");
								}
							}
						});
			}
			
			function changeUserInfo(box){
				$.ajax({
					method:"GET",
					url:"${webcontext}/ebooking/domesticSupplier/getUserInfo?code="+$(box).val()+"&currTime="+(new Date()).getTime(),
					dataType:"json",
					contentType:"application/x-www-form-urlencoded;charset=UTF-8", 
					success:function(msg){
						$("#supplierCode").val(unescape(msg.supplierCode));
						$("#supplierNameCn").val(unescape(msg.supplierNameCn));
						$("#addressCn").val(unescape(msg.addressCn));
						$("#contactPerson").val(unescape(msg.contactPerson));
						$("#telNo").val(unescape(msg.telNo));
					}
					});
				$("#isshipperCode").val($(box).val());
				//	alert(msg.id);
				}
			
			function exportExcel(){
				submitFormForAnji("addDomestic_Supplier_form","${webcontext}/ebooking/domesticSupplier/export","2");
			}
			

		</script>
	</form:form>
</body>
</html>