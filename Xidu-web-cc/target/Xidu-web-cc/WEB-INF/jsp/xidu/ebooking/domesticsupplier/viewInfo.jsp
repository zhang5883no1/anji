<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@include file="../../../include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>Review Domestic Supplier</title>
<script type='text/javascript'
	src='${jspath}/My97DatePicker/WdatePicker.js'></script>
	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=3></OBJECT>   
</head>
<body leftMargin="0" topMargin="0">

	<br />
	<form:form id="addDomestic_Supplier_form" name="addDomestic_Supplier_form" action="viewinfo"
		method="post" modelAttribute="domestic">
		<form:hidden path="id" id="id" />
		<form:hidden path="omsNo"/>
		<form:hidden path="carrier"/>
		<form:hidden path="active"/>
		<form:hidden path="shipperCode"/>
		<form:hidden path="status"/>
		<form:hidden path="destCode"/>
		<form:hidden path="userId"/>
		<input type="hidden" value="${user.id}" name="isshipperCode"/>
		<input type="hidden" name="flag" value="0" />
		<table border="0" cellspacing="2" width="100%">
			<tbody>
				<tr>
					<td><span id="lblHeader" class="PageTitle" >Review Domestic Supplier</span></td>
				</tr>
			</tbody>
		</table>


 <span id="UpdatePanelSearch">
  <style>
  table.gridTable td{ padding-left:5px; font-size:8pt; }
  .xl149{ background-color:#F90; color:#fff; font-weight:bold; }
  </style>
  <table cellpadding="3" cellspacing="1" width="100%" >
    <tbody>
      <tr>
        <td><fieldset id="isfileset" >
            <legend id="legSearchCriteria" class="legend" >Info</legend>
            <div style="width:100%; overflow:auto;">
            <table class="gridTable" width="100%" cellspacing="0" cellpadding="0" border="1" style="border-collapse:
 collapse;table-layout:fixed;width:900pt" id="viewtable" rules="none">
 <colgroup><col width="61" style="mso-width-source:userset;mso-width-alt:1952;
 width:20pt" class="xl65">
 <col width="169" style="mso-width-source:userset;mso-width-alt:5408;
 width:50pt" class="xl65">
 <col width="150" style="mso-width-source:userset;mso-width-alt:4800;
 width:50pt" class="xl65">
 <col width="184" style="mso-width-source:userset;mso-width-alt:5888;
 width:50pt" class="xl65">
 <col width="113" style="mso-width-source:userset;mso-width-alt:3616;
 width:50pt" class="xl65">
 <col width="112" style="mso-width-source:userset;mso-width-alt:3584;
 width:50pt" class="xl65">
 <col width="175" style="mso-width-source:userset;mso-width-alt:5600;
 width:50pt" class="xl65">
 <col width="151" style="mso-width-source:userset;mso-width-alt:4832;
 width:50pt" class="xl65">
 <col width="111" style="mso-width-source:userset;mso-width-alt:3552;
 width:50pt" class="xl65">
 <col width="123" style="mso-width-source:userset;mso-width-alt:3936;
 width:50pt" class="xl65">
 <col width="105" style="mso-width-source:userset;mso-width-alt:3360;
 width:50pt" class="xl65">
 <col width="113" style="mso-width-source:userset;mso-width-alt:3616;
 width:25pt" class="xl65">
 <col width="114" style="mso-width-source:userset;mso-width-alt:3648;
 width:30pt" class="xl65">
 <col width="115" style="mso-width-source:userset;mso-width-alt:3680;
 width:16pt" class="xl65">
 <col width="114" style="mso-width-source:userset;mso-width-alt:3648;
 width:36pt" class="xl65">
 <col width="170" style="mso-width-source:userset;mso-width-alt:5440;
 width:40pt" class="xl65">

 </colgroup><tbody><tr height="56" style="mso-height-source:userset;height:42.0pt">
   <td width="1530" valign="top" height="155" align="left" style="
  height:116.25pt;width:173pt" rowspan="2" colspan="2"><span style="mso-ignore:vglayout;
  position:absolute;z-index:1;margin-top:9px;width:113px;
  height:107px"><img width="113" height="107" v:shapes="Picture_x0020_1" src="${imagepath}/image002.png" /></span><span style="mso-ignore:vglayout2">
     <table cellspacing="0" cellpadding="0">
       <tbody><tr>
         <td width="230" height="155" style="height:116.25pt;width:173pt" class="xl124" rowspan="3" colspan="2"><a name="Print_Area">&#12288;</a></td>
         </tr>
         </tbody></table>
     </span></td>
   <td colspan="13" class="xl76"  style="
  width:1148pt; text-align:center; font-size:20px; font-weight:bold; background-color:#284799; color:#FFF;">Domestic Supplier 发运单</td>
   <td width="1530" style="width:128pt; background-color:#F60; font-size:20px; color:#fff; text-align:center;" class="xl131">V1.1</td>
 </tr>
 <tr height="70" style="mso-height-source:userset;height:52.5pt;" >
   <td width="1530" height="70" style="height:52.5pt;width:113pt" class="xl91">Pickup Date<br>
     计划提货时间<span style="font-weight:bold; color:#F00;">*</span></td>
   <td colspan="3" class="xl69" style="width:138pt">${domestic.pickupDate1} <input type="hidden" name="pickupDate1" value="${domestic.pickupDate1 }"/></td>
   <td width="1530" style="width:131pt" class="xl92">Delivery Note No.<br>
     发运单编号<span style="font-weight:bold; color:#F00;">*</span></td>
   <td class="xl109" colspan="2">&#12288;${domestic.supplierNo }<form:hidden  path="supplierNo" /></td>
   <td width="1530" style="width:92pt" class="xl73">Invoice No.<br>
     发票号</td>
   <td class="xl109" colspan="2">${domestic.invoiceNo }<form:hidden  path="invoiceNo" /></td>
   <td width="1530" style="width:86pt" class="xl73">Carrier<br>
     物流承运商</td>
   <td width="1530" style="
  width:300pt" class="xl97" colspan="3">Anji-CEVA<br>
     安吉汽车零部件物流有限公司</td>
 </tr>
 <tr height="13" style="mso-height-source:userset;height:9.95pt;border-style:solid none solid none;border-width:1px;">
  <td height="13" style="
  height:9.95pt; background-color:#CCC;" class="xl128" colspan="16">&#12288;</td>
 </tr>
 <tr height="48" style="mso-height-source:userset;height:36.0pt">
  <td height="48" style="height:36.0pt;" class="xl71">&#12288;</td>
  <td width="1530" style="width:127pt" class="xl93">Supplier <br>
    供应商<span style="font-weight:bold; color:#F00;">*</span></td>
  <td colspan="5" class="xl74" style="width:113pt">${user.supplierNameCn }</td>
  <td width="1530" style="width:113pt" class="xl93">Supplier<span style="mso-spacerun:yes">&nbsp; </span>Adderss<br>
    供应商地址<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="
  width:725pt" class="xl129" colspan="8">${user.addressCn }</td>
 </tr>
 <tr height="48" style="mso-height-source:userset;height:36.0pt">
  <td width="1530" height="48" style="height:36.0pt;width:46pt" class="xl72">&#12288;</td>
  <td width="1530" style="width:127pt" class="xl94">Supplier Code<br>
    供应商编号<span style="font-weight:bold; color:#F00;">*</span></td>
  <td colspan="5" class="xl70" style="width:113pt">${user.supplierCode }</td>
  <td width="1530" style="width:113pt" class="xl94">Supplier Contact<br>
    供应商联系人<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:254pt" class="xl105" colspan="3">${user.contactPerson }</td>
  <td width="1530" style="width:171pt" class="xl107" colspan="2">Supplier Tel<br>
    供应商电话<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="
  width:300pt" class="xl105" colspan="3">${user.telNo }</td>
 </tr>
 <tr height="64" style="mso-height-source:userset;height:48.0pt">
  <td height="64" style="height:48.0pt" class="xl66">&#12288;</td>
  <td width="1530" style="width:127pt" class="xl70">Consignee<br>
    收货方</td>
  <td width="1530" style="width:551pt" class="xl97" colspan="5">${domestic.consignee }<form:hidden  path="consignee" /></td>
  <td width="1530" style="width:113pt" class="xl70">Consignee Address<br>
    收货方地址</td>
  <td width="1530" style="
  width:725pt" class="xl105" colspan="8">${domestic.consigneeAddress }<form:hidden  path="consigneeAddress" />
</td>
 </tr>
 <tr height="13" style="mso-height-source:userset;height:9.95pt;border-style:solid none solid none;border-width:1px;">
  <td height="13" style="
  height:9.95pt; background-color:#CCC;" class="xl128" colspan="16">&#12288;</td>
 </tr>
 <tr height="48" style="mso-height-source:userset;height:36.0pt">
  <td width="1530" height="48" style="
  height:36.0pt;width:837pt; background-color:#dfdfdf; font-weight:bold;" class="xl100" colspan="8">Call-off Demands<br>
    订单需求<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="
  width:254pt; background-color:#dfdfdf; font-weight:bold;border-left-style:solid;border-width:1px;border-right-style:solid" class="xl100" colspan="3">Booking<br>
    供应商确认量</td>
  <td width="1530" style="
  width:171pt; background-color:#dfdfdf; font-weight:bold;border-left-style:solid;border-width:1px;border-right-style:solid" class="xl95" colspan="2">LSP<br>
    承运商接收数量</td>
  <td width="1530" style="
  width:172pt; background-color:#dfdfdf; font-weight:bold;border-left-style:solid;border-width:1px;border-right-style:solid" class="xl99" colspan="2">Receiver<br>
    工厂接收量</td>
  <td width="1530" style="width:128pt; background-color:#dfdfdf; font-weight:bold;border-left-style:solid;border-width:1px;border-right-style:solid" class="xl75">Comment<br>
    备注</td>
 </tr>
 <tr height="86" style="mso-height-source:userset;height:64.5pt" class="xl67">
  <td width="1530" height="86" style="height:64.5pt;width:46pt;border-style:solid none solid none;border-width:1px;" class="xl81">IND<br>
    序号</td>
  <td width="1530" style="width:127pt;border-style:solid none solid none;border-width:1px;" class="xl82">Part No.<br>
    零件号<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:113pt;border-style:solid none solid none;border-width:1px;" class="xl82">Calling Date<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:138pt;border-style:solid none solid none;border-width:1px;" class="xl82">Description<br>
    零件名<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:85pt;border-style:solid none solid none;border-width:1px;" class="xl82">Total Qty.<br>
    零件数量<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:84pt;border-style:solid none solid none;border-width:1px;" class="xl82">Packing Type<br>
    料箱种类<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:131pt;border-style:solid none solid none;border-width:1px;" class="xl82">Pcs/pallet<br>
    件数/托盘<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:113pt;border-style:solid none solid none;border-width:1px;" class="xl83">Total Pallets<br>
    托盘数量<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:83pt;border-style:solid none solid solid;border-width:1px;" class="xl82">Total Qty.<br>
    零件数量<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:92pt;border-style:solid none solid none;border-width:1px;" class="xl82" >Total Pallets<br>
    托盘数量<span style="font-weight:bold; color:#F00;">*</span></td>
  <td width="1530" style="width:79pt;border-width:1px;border-style:solid solid solid none" class="xl83">Weight(KG)<br>
    总重量<span style="font-weight:bold; color:#F00;">*</span><br>
    （千克）</td>
  <td width="1530" style="
  width:171pt;border-left-style:solid;border-width:1px;border-style:solid" class="xl81" colspan="2">Total Pallets<br>
    托盘数量</td>
  <td width="1530" style="
  width:172pt;border-left-style:solid;border-width:1px;border-style:solid" class="xl81" colspan="2">Total Pallets<br>
    托盘数量</td>
  <td class="xl84" style="border-left-style:solid;border-width:1px;border-style:solid">&#12288;</td>
 </tr>
 <%int i=0;%>
 <core:forEach items="${demands}" var="demand" >
	 <tr height="53" style="mso-height-source:userset;height:39.75pt">
	  <td height="53" style="height:39.75pt;" class="xl85"><span name="isindex"><%out.print((++i));%></span><input type="hidden" value="<%out.print(i);%>" name="isrowindex" /></td>
	  <td  class="xl86"><input type="hidden" name="partNo<%out.print(i);%>" value="${demand.partNo}" style="width:70px;"/>${demand.partNo}</td>
	  <td  class="xl87"><input type="hidden" name="callingDate<%out.print(i);%>" value="${demand.callingDate}" style="width:70px;" />${demand.callingDate}</td>
	  <td  class="xl88"><input type="hidden" name="description<%out.print(i);%>" value="${demand.description}" style="width:70px;"/>${demand.description}</td>
	  <td  class="xl89"><input style="width:80px;" type="hidden" name="totalQtyA<%out.print(i);%>" value="${demand.totalQtyA}" style="width: 50px;"/>${demand.totalQtyA}</td>
	  <td  class="xl89"><input style="width:80px;" type="hidden" name="packingType<%out.print(i);%>" value="${demand.packingType}" style="width: 60px;"/>${demand.packingType}</td>
	  <td  class="xl89"><input type="hidden" name="posPallet<%out.print(i);%>" value="${demand.posPallet}" style="width: 70px;"/>${demand.posPallet}</td>
	  <td  class="xl90" ><input type="hidden" name="totalPalletsA<%out.print(i);%>" value="${demand.totalPalletsA}" style="width: 70px;"/>${demand.totalPalletsA}</td>
	  <td  class="xl89" style="border-left-style:solid;border-width:1px;"><input style="width:80px;" type="hidden" name="totalQtyB<%out.print(i);%>" value="${demand.totalQtyB}" style="width: 60px;"/>${demand.totalQtyB}</td>
	  <td  class="xl89"><input style="width:80px;" type="hidden" name="totalPalletsB<%out.print(i);%>" value="${demand.totalPalletsB}" style="width: 60px;"/>${demand.totalPalletsB}</td>
	  <td  class="xl90" style="border-width:1px;border-right-style:solid"><input style="width:80px;" type="hidden" name="weight<%out.print(i);%>" value="${demand.weight}" style="width: 60px;"/>${demand.weight}</td>
	  <td  class="xl85" style="border-left-style:solid;border-width:1px;border-right-style:solid" colspan="2"><input type="hidden" name="totalPalletsC<%out.print(i);%>" value="${demand.totalPalletsC}" style="width: 70px;"/>${demand.totalPalletsC}</td>
	  <td  class="xl85" style="border-left-style:solid;border-width:1px;border-right-style:solid" colspan="2"><input type="hidden" name="totalPalletsD<%out.print(i);%>" value="${demand.totalPalletsD}" style="width: 70px;"/>${demand.totalPalletsD}</td>
	  <td  class="xl90" style="border-left-style:solid;border-width:1px;border-right-style:solid"><input type="hidden" name="comments<%out.print(i);%>" value="${demand.comments}" style="width: 50px;"/>${demand.comments}</td>
	 </tr>
  </core:forEach>
 
 <tr height="0" style="display:none" id="lastrow">
  <td width="1530" style="width:46pt"></td>
  <td width="1530" style="width:127pt"></td>
  <td width="1530" style="width:113pt"></td>
  <td width="1530" style="width:138pt"></td>
  <td width="1530" style="width:85pt"></td>
  <td width="1530" style="width:84pt"></td>
  <td width="1530" style="width:131pt"></td>
  <td width="1530" style="width:113pt"></td>
  <td width="1530" style="width:83pt"></td>
  <td width="1530" style="width:92pt"></td>
  <td width="1530" style="width:79pt"></td>
  <td width="1530" style="width:85pt"></td>
  <td width="1530" style="width:86pt"></td>
  <td width="1530" style="width:86pt"></td>
  <td width="1530" style="width:86pt"></td>
  <td width="1530" style="width:128pt"></td>
 </tr>
</tbody></table>


		<hr style="width: 99%;">
		<table border="0" cellpadding="1" cellspacing="1" width="100%">
			<tbody>
				<tr>
					 <td style="padding-left:2px" align="left" nowrap="nowrap">
				  </td>
					<td style="padding-right: 2px;" align="right" nowrap="nowrap"><input
						name="btnAdd" value="print" onclick="printPage()" id="btnAdd"
						class="Button" type="button"  style="width: 90px; text-align: center;"> <input
						name="btnDetails" value="Back" onClick="viewInfo()"
						id="btnDetails" class="Button" type="button" 
						style="width: 100px; text-align: center;"></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		</td>
		</tr>
		</tbody>
		</table>
		</span>
		<div style="display:none"><span id="PageMessageUpdatePanel"></span>
<!--startprint--><br />
	  <style>
  table.gridTable td{ padding-left:5px; font-size:8pt; }
  .xl149{ background-color:#F90; color:#fff; font-weight:bold; }
  </style>
 <span >
  <table cellpadding="3" cellspacing="1" width="100%" >
    <tbody>
      <tr>
        <td><fieldset  style="border:none" >
            <legend class="legend" style="display:none">Info</legend>
            <div style="width:100%; overflow:auto;">
            <table class="gridTable" width="100%" cellspacing="0" cellpadding="0"  style="table-layout:fixed;width:900pt"rules="none">
 <colgroup><col width="61" style="mso-width-source:userset;mso-width-alt:1952;
 width:20pt" class="xl65">
 <col width="169" style="mso-width-source:userset;mso-width-alt:5408;
 width:35pt" class="xl65">
 <col width="150" style="mso-width-source:userset;mso-width-alt:4800;
 width:35pt" class="xl65">
 <col width="184" style="mso-width-source:userset;mso-width-alt:5888;
 width:60pt" class="xl65">
 <col width="113" style="mso-width-source:userset;mso-width-alt:3616;
 width:35pt" class="xl65">
 <col width="112" style="mso-width-source:userset;mso-width-alt:3584;
 width:35pt" class="xl65">
 <col width="175" style="mso-width-source:userset;mso-width-alt:5600;
 width:35pt" class="xl65">
 <col width="151" style="mso-width-source:userset;mso-width-alt:4832;
 width:40pt" class="xl65">
 <col width="111" style="mso-width-source:userset;mso-width-alt:3552;
 width:27pt" class="xl65">
 <col width="123" style="mso-width-source:userset;mso-width-alt:3936;
 width:27pt" class="xl65">
 <col width="105" style="mso-width-source:userset;mso-width-alt:3360;
 width:27pt" class="xl65">
 <col width="113" style="mso-width-source:userset;mso-width-alt:3616;
 width:25pt" class="xl65">
 <col width="114" style="mso-width-source:userset;mso-width-alt:3648;
 width:30pt" class="xl65">
 <col width="115" style="mso-width-source:userset;mso-width-alt:3680;
 width:16pt" class="xl65">
 <col width="134" style="mso-width-source:userset;mso-width-alt:3648;
 width:36pt" class="xl65">
 <col width="140" style="mso-width-source:userset;mso-width-alt:5440;
 width:40pt" class="xl65">

 </colgroup><tbody><tr height="56" style="mso-height-source:userset;height:42.0pt">
   <td width="1530" valign="top" height="155" align="left" style="
  height:116.25pt;width:173pt" rowspan="2" colspan="2"><span style="mso-ignore:vglayout;
  position:absolute;z-index:1;margin-top:9px;width:113px;
  height:107px"></span><span style="mso-ignore:vglayout2">
     <table cellspacing="0" cellpadding="0">
       <tbody><tr>
         <td width="230" height="155" style="height:116.25pt;width:173pt" class="xl124" rowspan="3" colspan="2"><a>&#12288;</a></td>
         </tr>
         </tbody></table>
     </span></td>
   <td colspan="13" class="xl76"  style="
  width:1148pt; text-align:center; font-size:20px; font-weight:bold; background-color:#284799; color:#FFF;"></td>
   <td width="1530" style="width:128pt; background-color:#F60; font-size:20px; color:#fff; text-align:center;" class="xl131"></td>
 </tr>
 <tr style="mso-height-source:userset;" >
   <td width="1530"  style="width:113pt;height:10px" class="xl91">
     <span style="font-weight:bold; color:#F00;">*</span></td>
   <td colspan="3" class="xl69" style="width:138pt">${domestic.pickupDate1} </td>
   <td width="1530" style="width:131pt" class="xl92">
    <span style="font-weight:bold; color:#F00;">*</span></td>
   <td class="xl109" colspan="2">&#12288;${domestic.supplierNo }</td>
   <td width="1530" style="width:92pt" class="xl73">
     </td>
   <td class="xl109" colspan="2">${domestic.invoiceNo }</td>
   <td width="1530" style="width:86pt" class="xl73"><br>
     </td>
   <td width="1530" style="
  width:300pt" class="xl97" colspan="3"></td>
 </tr>
 <tr  style="mso-height-source:userset;">
  <td height="48" style="height:8px" class="xl71">&#12288;</td>
  <td width="1530" style="width:127pt" class="xl93"></td>
  <td colspan="5" class="xl74" style="width:113pt">${user.supplierNameCn }</td>
  <td width="1530" style="width:113pt" class="xl93">
    </td>
  <td width="1530" style="
  width:725pt" class="xl129" colspan="8">${user.addressCn }</td>
 </tr>
 <tr height="48" style="mso-height-source:userset;">
  <td width="1530" style=";width:46pt;height:8px" class="xl72">&#12288;</td>
  <td width="1530" style="width:127pt" class="xl94">
 </td>
  <td colspan="5" class="xl70" style="width:113pt">${user.supplierCode }</td>
  <td width="1530" style="width:113pt" class="xl94">
    </td>
  <td width="1530" style="width:254pt" class="xl105" colspan="3">${user.contactPerson }</td>
  <td width="1530" style="width:171pt" class="xl107" colspan="2">
  </td>
  <td width="1530" style="
  width:300pt" class="xl105" colspan="3">${user.telNo }</td>
 </tr>
 <tr  style="mso-height-source:userset;">
  <td  style="height:48.0pt;height:8px" class="xl66">&#12288;</td>
  <td width="1530" style="width:127pt" class="xl70">
    </td>
  <td width="1530" style="width:551pt" class="xl97" colspan="5">${Consignee}</td>
  <td width="1530" style="width:113pt" class="xl70">
    </td>
  <td width="1530" style="
  width:725pt" class="xl105" colspan="8">${ConsigneeAddress}
</td>
 </tr>
 <tr  style="mso-height-source:userset;">
  <td  style="
   background-color:#CCC;" class="xl128" colspan="16">&#12288;</td>
 </tr>
 <tr height="48" style="mso-height-source:userset;height:36.0pt">
  <td width="1530" height="48" style="
  height:36.0pt;width:837pt; background-color:#dfdfdf; font-weight:bold;" class="xl100" colspan="8"><br>
    <br/></td>
  <td width="1530" style="
  width:254pt; background-color:#dfdfdf; font-weight:bold;" class="xl100" colspan="3"><br/></td>
  <td width="1530" style="
  width:171pt; background-color:#dfdfdf; font-weight:bold;" class="xl95" colspan="2"><br/></td>
  <td width="1530" style="
  width:172pt; background-color:#dfdfdf; font-weight:bold;" class="xl99" colspan="2"><br/></td>
  <td width="1530" style="width:128pt; background-color:#dfdfdf; font-weight:bold;" class="xl75"><br/></td>
 </tr>
 <tr  style="mso-height-source:userset;" class="xl67">
  <td width="1530" height="86" style="height:18px;width:46pt;" class="xl81"><br/></td>
  <td width="1530" style="width:127pt; " class="xl82"><br/></td>
  <td width="1530" style="width:113pt; " class="xl82"></td>
  <td width="1530" style="width:138pt; " class="xl82"><br/></td>
  <td width="1530" style="width:85pt; " class="xl82"><br/></td>
  <td width="1530" style="width:84pt; " class="xl82"><br/></td>
  <td width="1530" style="width:131pt; " class="xl82"><br/></td>
  <td width="1530" style="width:113pt; " class="xl83"><br/></td>
  <td width="1530" style="width:83pt;" class="xl82"><br/></td>
  <td width="1530" style="width:92pt; " class="xl82" ><br/></td>
  <td width="1530" style="width:79pt;" class="xl83"><br/><br>
    </td>
  <td width="1530" style="
  width:171pt;" class="xl81" colspan="2"><br/></td>
  <td width="1530" style="
  width:172pt;" class="xl81" colspan="2"><br/></td>
  <td class="xl84">&#12288;</td>
 </tr>
 <core:forEach items="${demands}" var="demand" >
	 <tr style="mso-height-source:userset;">
	  <td  style="height:37px;" class="xl85"><span ></span></td>
	  <td  class="xl86">${demand.partNo}</td>
	  <td  class="xl87">${demand.callingDate}</td>
	  <td  class="xl88">${demand.description}</td>
	  <td  class="xl89">${demand.totalQtyA}</td>
	  <td  class="xl89">${demand.packingType}</td>
	  <td  class="xl89">${demand.posPallet}</td>
	  <td  class="xl90" >${demand.totalPalletsA}</td>
	  <td  class="xl89" >${demand.totalPalletsA}</td>
	  <td  class="xl89">${demand.totalPalletsB}</td>
	  <td  class="xl90" >${demand.weight}</td>
	  <td  class="xl85" colspan="2">${demand.totalPalletsC}</td>
	  <td  class="xl85"  colspan="2">${demand.totalPalletsD}</td>
	  <td  class="xl90" >${demand.comments}</td>
	 </tr>
  </core:forEach>
 
 <tr height="0" style="display:none" >
  <td width="1530" style="width:46pt"></td>
  <td width="1530" style="width:127pt"></td>
  <td width="1530" style="width:113pt"></td>
  <td width="1530" style="width:138pt"></td>
  <td width="1530" style="width:85pt"></td>
  <td width="1530" style="width:84pt"></td>
  <td width="1530" style="width:131pt"></td>
  <td width="1530" style="width:113pt"></td>
  <td width="1530" style="width:83pt"></td>
  <td width="1530" style="width:92pt"></td>
  <td width="1530" style="width:79pt"></td>
  <td width="1530" style="width:85pt"></td>
  <td width="1530" style="width:86pt"></td>
  <td width="1530" style="width:86pt"></td>
  <td width="1530" style="width:86pt"></td>
  <td width="1530" style="width:128pt"></td>
 </tr>
</tbody></table><!--isendprint--></div>
</form:form>
	
	
<script LANGUAGE="JavaScript">

	function printPage() {
			//$("#isbody").jqprint();
			bdhtml=window.document.body.innerHTML;
			starstr="<!--startprint-->"
			endstr="<!--isendprint-->"
			prnhtml=bdhtml.substr(bdhtml.indexOf(starstr)+17);
			prnhtml=prnhtml.substr(0,prnhtml.indexOf(endstr));
			window.document.body.innerHTML=prnhtml;
			
			//if(navigator.userAgent.indexOf("IE") != -1){
			//	printPage1();
			//}else{
				window.print();
			//}
		
			window.document.body.innerHTML=bdhtml;
	}
	
	function printPage1(){
		wb.execwb(8,1); 
		wb.execwb(7,1);
	}

	function viewInfo() {
		$("#addDomestic_Supplier_form").attr("action", "viewinfo");
		submitFormForAnji("addDomestic_Supplier_form","${webcontext}/ebooking/domesticSupplier/viewinfo",0);
//		$("#addDomestic_Supplier_form").submit();
	}
	
  			
</script>
</body>
</html>