<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="application/msexcel" %>
<!-- 以上这行设定本网页为excel格式的网页 -->
<%
   response.setHeader("Content-disposition","inline; filename=domesticSupplier.xls");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档
%>
<html>
<head>
<title>Excel档案呈现方式</title>
</head>
<body>
            <table class="gridTable" width="100%" cellspacing="0" cellpadding="0" border="1" style="border-collapse:
 collapse;table-layout:fixed;width:900pt" id="viewtable" rules="none">
 <tbody><tr height="56" style="mso-height-source:userset;height:42.0pt">
   <td width="1530" valign="top" height="155" align="left" style="
  height:116.25pt;width:173pt" rowspan="2" colspan="2"></td>
   <td colspan="13" class="xl76"  style="
  width:1148pt; text-align:center; font-size:20px; font-weight:bold; background-color:#284799; color:#FFF;">Domestic Supplier 发运单</td>
   <td width="1530" style="width:128pt; background-color:#F60; font-size:20px; color:#fff; text-align:center;" class="xl131">V1.1</td>
 </tr>
 <tr height="70" style="mso-height-source:userset;height:52.5pt;" >
   <td width="1530" height="70" style="height:52.5pt;width:113pt" class="xl91">Pickup Date<br>
     计划提货时间<span style="font-weight:bold; color:#F00;">*</span></td>
   <td colspan="3" class="xl69" style="width:138pt">${domestic.pickupDate1} </td>
   <td width="1530" style="width:131pt" class="xl92">Delivery Note No.<br>
     发运单编号<span style="font-weight:bold; color:#F00;">*</span></td>
   <td class="xl109" colspan="2">&#12288;${domestic.supplierNo }</td>
   <td width="1530" style="width:92pt" class="xl73">Invoice No.<br>
     发票号</td>
   <td class="xl109" colspan="2">${domestic.invoiceNo }</td>
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
  <td width="1530" style="width:551pt" class="xl97" colspan="5">${domestic.consignee }</td>
  <td width="1530" style="width:113pt" class="xl70">Consignee Address<br>
    收货方地址</td>
  <td width="1530" style="
  width:725pt" class="xl105" colspan="8">${domestic.consigneeAddress }
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
	  <td height="53" style="height:39.75pt;text-align:left;" class="xl85"><span name="isindex"><%out.print((++i));%></span></td>
	  <td  class="xl86" style="text-align:left;">${demand.partNo}</td>
	  <td  class="xl87" style="text-align:left;">${demand.callingDate}</td>
	  <td  class="xl88" style="text-align:left;">${demand.description}</td>
	  <td  class="xl89" style="text-align:left;">${demand.totalQtyA}</td>
	  <td  class="xl89" style="text-align:left;">${demand.packingType}</td>
	  <td  class="xl89" style="text-align:left;">${demand.posPallet}</td>
	  <td  class="xl90" style="text-align:left;">${demand.totalPalletsA}</td>
	  <td  class="xl89" style="text-align:left;">${demand.totalQtyB}</td>
	  <td  class="xl89" style="text-align:left;">${demand.totalPalletsB}</td>
	  <td  class="xl90" style="text-align:left;">${demand.weight}</td>
	  <td  class="xl85"  colspan="2" style="text-align:left;">${demand.totalPalletsC}</td>
	  <td  class="xl85"  colspan="2" style="text-align:left;">${demand.totalPalletsD}</td>
	  <td  class="xl90" style="text-align:left;">${demand.comments}</td>
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
  <tr  style="mso-height-source:userset">
  <td  style="background-color:#CCC;border: 1px;" class="xl128" colspan="16">&#12288;</td>
 </tr>
 <tr style="mso-height-source:userset;">
 <td colspan="5" align="center" style="border:solid 1px;">Supplier confirm<br/>供应商确认</td>
 <td colspan="5" align="center" style="border:solid 1px;">LSP confirm<br/>物流服务承运商确认</td>
 <td colspan="6" align="center" style="border:solid 1px;">Receiver Confirm<br/>收货人确认 </td>
 </tr>
  <tr height="73" style="mso-height-source:userset;">
 <td colspan="5" rowspan="2" style="border:solid 1px;">Signature:<br/>签名:</td>
 <td colspan="5" rowspan="2" style="border:solid 1px;">Signature:<br/>签名:</td>
 <td colspan="6" rowspan="2" style="border:solid 1px;">Signature:<br/>签名:</td>
 </tr>
 <tr></tr>
 <tr></tr>
   <tr style="mso-height-source:userset;">
 <td colspan="5" style="border:solid 1px;">Date:<br/>时间:</td>
 <td colspan="5" style="border:solid 1px;">Date:<br/>时间:</td>
 <td colspan="6" style="border:solid 1px;">Date:<br/>时间:</td>
 </tr>
</tbody></table>

</body>
</html> 