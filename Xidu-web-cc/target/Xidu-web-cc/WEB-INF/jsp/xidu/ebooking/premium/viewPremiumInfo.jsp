<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>View Premium</title>
<script type='text/javascript' src='${jspath}/My97DatePicker/WdatePicker.js'></script>
<script LANGUAGE="JavaScript">
	function viewInfo() {
		$("#addpremium_form").attr("action", "viewinfo");
		submitFormForAnji("addpremium_form","${webcontext}/ebooking/premium/viewinfo",0);
//		$("#addpremium_form").submit();
	}
	
	function printPage(){
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
<style type="text/css">
	input{
	margin-top:5px;
	margin-bottom:5px;
	}
</style>
</head>
<body leftMargin="0" topMargin="0">
	<br />
	<!--startprint--><form:form id="addpremium_form" name="addpremium_form" action="savePremium"
		method="post" modelAttribute="premiumInfo">
		<form:hidden path="id" id="id" />
		<form:hidden path="omsno"/>
		<form:hidden path="status"/>
		<form:hidden path="supplierCode"/>
		<form:hidden path="active" />
		<input type="hidden" name="flag" value="0" />
		<input type="hidden" name="checkflag" value="" />
		<table border="0" cellspacing="2" width="100%">
			<tbody>
				<tr>
					<td><span id="lblHeader" class="PageTitle">View
							Premium</span></td>
				</tr>
			</tbody>
		</table>


  <table cellpadding="3" cellspacing="1" width="100%" style="font-size:8pt;">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Info</legend>
            <div style="width:100%; overflow:auto;"><table class="gridTable" width="2080" cellspacing="0" cellpadding="0" border="1" style="border-collapse:
 collapse;table-layout:fixed;width:1000pt">

 <colgroup><col width="61" style="mso-width-source:userset;mso-width-alt:1952;
 width:126pt" class="xl65">
 <col width="169" style="mso-width-source:userset;mso-width-alt:5408;
 width:80pt" class="xl65">
 <col width="150" style="mso-width-source:userset;mso-width-alt:4800;
 width:113pt" class="xl65">
 <col width="184" style="mso-width-source:userset;mso-width-alt:5888;
 width:108pt" class="xl65">
 <col width="113" style="mso-width-source:userset;mso-width-alt:3616;
 width:85pt" class="xl65">
 <col width="112" style="mso-width-source:userset;mso-width-alt:3584;
 width:84pt" class="xl65">
 <col width="175" style="mso-width-source:userset;mso-width-alt:5600;
 width:101pt" class="xl65">
  <col width="175" style="mso-width-source:userset;mso-width-alt:5600;
 width:101pt" class="xl65">
   <col width="175" style="mso-width-source:userset;mso-width-alt:5600;
 width:101pt" class="xl65">
  </colgroup>
  <tbody>
  <tr  style="mso-height-source:userset;">
  <td colspan="8"  align="center" style="font-size:20pt;font-weight:bold">PREMIUM TRANSPORT AUTHORISATION (PTA)</td>
   <td align="center">
   <img v:shapes="Picture_x0020_1" src="${imagepath}/image002.png" />
   </td>
 </tr>
 

 <tr><td colspan="9" align="left" style="font-size:13pt;"><div style="margin:11px;font-weight:bold;"> > Section 1 - Premium Freight Instructions</div></td></tr>
 <tr>
	<td colspan="4" align="left"  rowspan="3">
	<span style="width:200px;float:left;margin:10px;" >Date of Request*:<br/><br/>Telephone number*:<br/><br/>Destination Port:</span>
	<span style="width:50px;float: right; margin-top: 10px; margin-right: 350px; margin-bottom: 10px; margin-left: 0px;"><form:hidden path="dateRequest" />${premiumInfo.dateRequest }<br/><br/><form:hidden path="tel" />${premiumInfo.tel }<br/><br/><form:hidden path="destinationPort" />${premiumInfo.destinationPort }</span>
	</td>
	<td colspan="5" align="left"  rowspan="3">
	<span style="width:200px;float:left;margin:10px;" >Name of Requestor*: <br/><br/>Email address*:<br/><br/>Destination Plant*:</span>
	<span style="width:50px;float: right; margin-top: 10px; margin-right: 350px; margin-bottom: 10px; margin-left: 0px;"><form:hidden path="name" />${premiumInfo.name }<br/><br/><form:hidden path="email" />${premiumInfo.email }<br/><br/><form:hidden path="destinationPlant" />${premiumInfo.destinationPlant }</span>
	</td>
 </tr>
<tr></tr><tr></tr>
  <tr style="height:20pt;">
	<td colspan="3" align="center" style="font-size:12px;">Part Number*&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td colspan="3" align="center" style="font-size:12px;">Description* &nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td colspan="3" align="center" style="font-size:12px;">Quantity Requested* &nbsp;&nbsp;&nbsp;&nbsp; </td>
 </tr>

<%int i=0;%>
<core:forEach items="${freightlist}" var="freight">
    <tr style="height:20pt;">
	<td colspan="3" align="center" ><input type="hidden" name="isindex" value="<%out.print(++i);%>" /><input type="hidden" name="pn<%out.print(i);%>" value="${freight.partNumber}"/>${freight.partNumber}  </td>
	<td colspan="3" align="center" ><input type="hidden" name="des<%out.print(i);%>" value="${freight.description}"/>${freight.description} </td>
	<td colspan="3" align="center" ><input type="hidden" name="qr<%out.print(i);%>" value="${freight.quantityRequested}"/>${freight.quantityRequested} </td>
 </tr>
</core:forEach>

  <tr id="lastrow" style="height:20pt;">
	<td colspan="4" align="left" ><span style="width:50px;margin-left:10px">MRD*: </span>&nbsp;&nbsp;&nbsp;&nbsp;<form:hidden path="mrd" />${premiumInfo.mrd} </td>
	<td colspan="5" align="left" ><span style="width:50px;margin-left:10px">PTA Number:</span>   &nbsp;&nbsp;&nbsp;&nbsp;<form:hidden path="ptaNumber" />${premiumInfo.ptaNumber}  </td>
 </tr>
   <tr style="height:20pt;">
	<td colspan="4" align="left" ><span style="width:50px;margin-left:10px">Cargo ready date*: </span>&nbsp;&nbsp;&nbsp;&nbsp;<form:hidden path="readyDate" />${premiumInfo.readyDate} </td>
	<td colspan="5" align="left"><span style="width:50px;margin-left:10px">VOLVO Call-off number: </span>   &nbsp;&nbsp;&nbsp;&nbsp;<form:hidden path="volvoNumber" />${premiumInfo.volvoNumber} </td>
 </tr>
   <tr style="height:20pt;">
	<td colspan="9" align="left" ><span style="width:50px;margin-left:10px">PO number*:</span>&nbsp;&nbsp;&nbsp;&nbsp;<form:hidden path="poNumber" />${premiumInfo.poNumber} </td>
 </tr>
    <tr>
	<td colspan="9" align="left"><br/><span style="width:50px;margin-left:10px">Service Level*</span>&nbsp;&nbsp;&nbsp;&nbsp;
	<br/><span style="margin-left:10px">
	<input type="checkbox" value="1" name="serviceLevel" <core:if test="${fn:substring(premiumInfo.serviceLevel, 2, 3)=='1'}">checked</core:if> onclick="return false;" />Ocean to Air &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="checkbox" value="2" name="serviceLevel" <core:if test="${fn:substring(premiumInfo.serviceLevel, 1, 2)=='1'}">checked</core:if> onclick="return false;"/>Barge to Truck &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="checkbox" value="4" name="serviceLevel" <core:if test="${fn:substring(premiumInfo.serviceLevel, 0, 1)=='1'}">checked</core:if> onclick="return false;"/>Return from "Destination Plant" to "supplier" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span></td>
 </tr>

 <tr><td colspan="9" align="left"  style="font-size:13pt;"><div style="margin:11px;font-weight:bold;">> Section 2 - Supplier Details</div></td></tr>
 <tr>
	<td colspan="4" align="left" rowspan="9">
		<span style="width:150px;float:left;margin:10px;" >
			Supplier Name*:  <br/><br/>
			Pick up add*:  <br/><br/>
			Country*: <br/><br/>
			Contact Name*: <br/><br/>
			Date of Shipment:<br/><br/>
			Pick up Window:<br/><br/>
			Total boxes / pallets*<br/><br/>
			Dimensions*
		</span>
		<span style="width:250px;float:right;margin:10px 100px 0px 0px;">
			${user.supplierNameEn }<br/><br/>
			${user.pickUpAddress }<br/><br/>
			${user.country }<br/><br/>
			${user.contactPerson }<br/><br/>
			<form:hidden path="dateofshipment"/>${premiumInfo.dateofshipment }<br/><br/>
			<form:hidden path="pickupdate"/>${premiumInfo.pickupdate }<br/><br/>
			<form:hidden path="totalbox"/>${premiumInfo.totalbox }<br/><br/>
			<form:hidden path="dimensions"/>${premiumInfo.dimensions }
		</span>
	<td colspan="5" align="left"  rowspan="9">
	<span style="width:200px;float:left;margin:20px 10px 10pt 10px" >
		Supplier code*:<br/><br/>
		Email*: <br/><br/>
		Tel*:  <br/><br/>
		Fax: <br/><br/>
		Email:<br/><br/>
		Total Weight <br/><br/>
		Hazardous material*
	</span>
	<span style="width:250px;float:right;margin:20px 100px 0px 0px;">
		${user.supplierCode }<br/><br/>
		${user.email }<br/><br/>
		${user.telNo}<br/><br/>
		${user.faxNo }<br/><br/>
		${user.email }<br/><br/>
		<form:hidden path="totalweight"/>${premiumInfo.totalweight }<br/><br/>
		<form:hidden path="material"/>${premiumInfo.material }<br/><br/><br/><br/><br/>
	</span>
	</td>
 </tr>

<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
<tr>
<td colspan="4" align="center" style="height:20pt;">VCC APPLICANT</td>
<td colspan="5" align="center" style="height:20pt;">APPROVED BY</td>
</tr>
<tr style="height:40pt;">
<td colspan="4" align="center" rowspan="4">
<form:hidden path="vccApplicant"/>
${premiumInfo.vccApplicant}</td>
<td colspan="5" align="center" rowspan="4">
<span style="float:left;">SCC mgr</span>
<form:hidden path="approvedBy"/>
${premiumInfo.approvedBy}</td>
</tr>
</tbody></table>
            </div><!--isendprint-->  


		<hr style="width: 99%;">
		<table border="0" cellpadding="1" cellspacing="1" width="100%">
			<tbody>
				<tr>
					 <td style="padding-left:2px" align="left" nowrap="nowrap">
				  </td>
					<td style="padding-right: 2px;" align="right" nowrap="nowrap">
					<input name="btnDetails" value="back" onClick="viewInfo()" id="btnDetails" class="Button" type="button"  style="width: 100px; text-align: center;">
					<input name="btnAdd" value="print" onclick="printPage()" id="btnAdd" class="Button" type="button"  style="width: 90px; text-align: center;"></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		</td>
		</tr>
		</tbody>
		</table>
		</span>
		<span id="PageMessageUpdatePanel"></span>
	</form:form>
</body>
</html>