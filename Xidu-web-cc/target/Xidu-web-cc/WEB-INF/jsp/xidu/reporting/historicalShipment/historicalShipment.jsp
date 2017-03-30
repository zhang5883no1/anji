<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>

  </title>
  <%@include file="../../include/baseJsAndCss.jsp"%>
  
	<script type='text/javascript' src='${jspath}/My97DatePicker/WdatePicker.js'></script>

<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
 
  <%@include file="../../include/head.jsp"%>
   
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">Historical Shipment</span></td>
      </tr>
    </tbody>
  </table>
  <form:form id="report_form" name ="report_form" action="queryInfo" method="post" modelAttribute="queryDto">
 	<form:hidden path="id" id="id"/>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Search Criteria</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Transportation Mode:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:select path="transportation_mode" onchange="setVaild(this)">
                  		<form:option value="Domestic">Domestic Transport</form:option>
                  		<form:option value="Air">Airfreight</form:option>
                  		<form:option value="Ocean">Seafreight</form:option>
                  	</form:select>
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Ship Date:</nobr></span></td>
                  <td nowrap="nowrap"><table height="30" cellspacing="1" cellpadding="1" border="0">
                    <tbody>
                      <tr>
                        <td nowrap=""><span id="OMSSearchCtl1_dcSearchFrom" style="display:inline-block;cursor:hand;">
                          <table cellspacing="0" cellpadding="0" border="0" style="border-width:0px;border-collapse:collapse;">
                            <tbody>
                              <tr>
                                <td style="white-space:nowrap;">
                                <input type="text" name="ship_date_from" value="${queryDto.ship_date_from }" id="shipDateFrom" class="userInput Wdate" readonly="readonly" onfocus="WdatePicker()" style="width:80px;">
                                  </td>
                              </tr>
                            </tbody>
                          </table>
                        </span></td>
                        <td nowrap=""><span id="OMSSearchCtl1_Label2" class="LabelBold">To</span></td>
                        <td nowrap=""><span id="OMSSearchCtl1_dcSearchTo" style="display:inline-block;cursor:hand;">
                          <table cellspacing="0" cellpadding="0" border="0" style="border-width:0px;border-collapse:collapse;">
                            <tbody>
                              <tr>
                                <td style="white-space:nowrap;"><input type="text" name="ship_date_to"  value="${queryDto.ship_date_to}"  id="shipDateTo" readonly="readonly" class="userInput Wdate" onfocus="WdatePicker()" style="width:80px;">
                                  &nbsp;</td>
                              </tr>
                            </tbody>
                          </table>
                        </span></td>
                      </tr>
                    </tbody>
                  </table></td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Container Number:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path="container_number"  cssClass="userInput"/> 
                  </td>
                </tr>
                                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Invoice Number:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path="invoice_number"  cssClass="userInput"/> 
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Part Number:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path="part_number"  cssClass="userInput" /> 
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>BL:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path="bl"  cssClass="userInput"/> 
                  </td>
                </tr>
                                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>AWB:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path="awb"  cssClass="userInput"/> 
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Domestic Supplier Number:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path=""  cssClass="userInput" /> 
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>International Supplier Number:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path=""  cssClass="userInput" /> 
                  </td>
                </tr>
                                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Deviation:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path=""  cssClass="userInput" maxLength="5"/> 
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Supplier Code:</nobr></span></td>
                  <td nowrap="nowrap">
                  	<form:input path="supplier_code"  cssClass="userInput" id="supplierCode" /> <a href="javascript:void(0);" onclick="showQuery('query_supplier','supplierCode');"><img src="${imagepath}/search.gif" align="absmiddle" />
                  </td>
                  
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold"><nobr>Dest Plant Code:</nobr></span></td>
                  <td nowrap="nowrap">
                   <core:if test="${queryDto.transportation_mode!='Ocean' }">
                  	<form:input path="destPlantCode"  cssClass="userInput" id="destPlantCode" /> <a href="javascript:void(0);" onclick="showQuery('query_destPlant','destPlantCode');">  <img src="${imagepath}/search.gif" align="absmiddle" />
                  </core:if>
                  <core:if test="${queryDto.transportation_mode=='Ocean' }">
                  	<form:input path="destPlantCode"  cssClass="userInput RequiredField" id="destPlantCode" /> <a href="javascript:void(0);" onclick="showQuery('query_destPlant','destPlantCode');">  <img src="${imagepath}/search.gif" align="absmiddle" />
                  </core:if>
                  </td>
                </tr>
              </tbody>
            </table>
            <hr style="width:99%;">
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td style="padding-left:2px"><span><span class="Label" style="color:Red;">* will match one or more words.</span>&nbsp;<img  src="${imagepath}/i.gif" style="border-width:0px;"></span></td>
                  <td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">Rows Returned:</span>
                    <form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;" class="RequiredField userInput integer"/>
                    <input name="btnSearch" value="Search" onClick="search();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    <input name="btnSearch" value="Reset" onClick="clearAll();" id="btnSearch" class="Button" type="button"  style="width:90px;" type="button"/>
                    </td>
                </tr>
              </tbody>
            </table>
          </fieldset></td>
      </tr>
    </tbody>
  </table>
  </span> 
  
  <%@include file="../../include/page.jsp"%>

  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2">
                <div id="resizableDiv" class="DivResultsGrid" style="OVERFLOW: auto;border-top:solid 1px silver">
                 <core:if test="${queryDto.transportation_mode=='Domestic'}">
                 <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" 
					   items="queryDto.resultList"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
					   sortable="false"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>			
						 	<ec:column value="${ROWCOUNT}" alias="序号 Item"/>		 	
						    <ec:column property="supplier_code" title="供应商代码  Shp Code"/>
						    <ec:column property="supplier_name" title="供应商名称 Supplier Name"/>
						    <ec:column property="ctiy" title="提货城市 Pick up City"/>
						    <ec:column property="part_no" title="零件号  PN"/>
						    <ec:column property="part_name" title="零件名称 Description"/>
						    <ec:column property="part_quantity" title="订单数量 Call off Qty"/>
						    <ec:column property="actual_quantity" title="实提数量 Pick up Qty"/>
						    <ec:column property="variance" title="提货数量差异 Variance"/>
						    <ec:column property="delivery_date" title="订单提货时间 Calling Date"/>
						    <ec:column property="pickup_date" title="实际提货时间 Pick up Date"/>
						    <ec:column property="eta" title="预计到达 VCCD时间    ETA VCCD"/>
						    <ec:column property="ata" title="实际到达VCCD时间ATA VCCD"/>
						    <ec:column property="transport_time" title="陆运周期 TTL L/T"/>
						    <ec:column property="comments" title="备注 Remark"/>
						    
						 </ec:row>
					</ec:table>
					</core:if>   
					
					<core:if test="${queryDto.transportation_mode=='Air'}">
                 <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" 
					   items="queryDto.resultList"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
					   sortable="false"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>				
						 	<ec:column value="${ROWCOUNT}" alias="Item"/>		 	
						    <ec:column property="service_level" alias="service level"/>
						    <ec:column property="booking_date" alias="Booking  Date/Green Light"/>
						    <ec:column property="pickup_date" title="Pick up Date "/>
						    <ec:column property="order_no" title="PO No. "/>
						    <ec:column property="supplier_name" title="Shipper "/>
						    <ec:column property="plant_name" title="Consignee"/>
						    <ec:column property="origin" title="Origin"/>
						    <ec:column property="destination" title="Destination"/>
						    <ec:column property="incoterm" alias="Incoterm"/>
						    <ec:column property="package_quantity" title="Package Quantity"/>
						    <ec:column property="gross_weight" title="Gross Weight/kg"/>
						    <ec:column property="chargable_weight" title="Chargable Weight/kg"/>
						    <ec:column property="volume" title="Volume cbm"/>
						    <ec:column property="hawb" title="Hawb No. "/>
						    <ec:column property="mawb" title="Mawb No."/>
						    <ec:column property="flight" alias="Flight "/>
						    <ec:column property="etd" title="1ST ETD "/>
						    <ec:column property="atd" title="ATD"/>
						    <ec:column property="eta" title="ETA"/>
						    <ec:column property="ata" title="ATA "/>
						    <ec:column property="atavccd" alias="ATA  VCCD "/>
						    <ec:column property="clearance_time" alias="Clearance Time (Days)"/>
						    <ec:column property="comment" title="Comments"/>
						    
						 </ec:row>
					</ec:table>
					</core:if>
					
					<core:if test="${queryDto.transportation_mode=='Ocean'&&queryDto.destPlantCode!='CEP'}">
                 <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" 
					   items="queryDto.resultList"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
					   sortable="flase"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>				
						 	<ec:column value="${ROWCOUNT}" alias="Item"/>		 	
						    <ec:column property="name" alias="Name货物名品" />
						    <ec:column property="poNo" title="PO No.采购订单"/>
						    <ec:column property="invoiceNo" title="Invoice No.   发票号"/>
						    <ec:column property="terms" alias="Terms条款"/>
						    <ec:column property="portofloading" title="Port of loading启运港"/>
						    <ec:column property="portofDischarge" title="Port of Discharge目地港"/>
						    <ec:column property="bLNo" title="B/L Number提 单 号 "/>
						    <ec:column property="container_40HG" alias="Container type"/>
						    <ec:column property="package_NO" alias="箱号"/>
						    <ec:column property="pickupAdd" title="Pick up Add.提货地"/>
						    <ec:column property="pickuptime" title="Pick up time装箱时间"/>
						    <ec:column property="customs" alias="Customs报关时间"/>
						    <ec:column property="etd" title="ETD预定海船期"/>
						    <ec:column property="vessel" title="Vessel船名航次"/>
						    <ec:column property="atd" title="实际启运日期 ATD"/>
						    <ec:column property="eta" title="ETA SH Port预计到洋山日期"/>
						    <ec:column property="ata" title="ATA SH Port 实际抵达洋山日期"/>
						    <ec:column property="customs_clearance_Finish_Date" alias="Customs clearance Finish Date"/>
						    <ec:column property="despatch_SH_Port" alias="Despatch SH Port 离开上海港日期"/>
						    <ec:column property="arrival_WH" alias="Arrival WH 经过武汉日期"/>
						    <ec:column property="arrival_LZ" title="Arrival LZ 达泸州日期"/>
						    <ec:column property="despatch_LZ" title="Despatch LZ 离开泸州日期"/>
						    <ec:column property="arrive_VCCD" title="Arrive VCCD 到达工厂日期"/>
						    <ec:column property="barge_seal_number" title="seal number"/>
						    <ec:column property="comments" title="Remark 备注"/>
						 </ec:row>
						 
						 
						
					</ec:table>
					</core:if>
					
					<core:if test="${queryDto.transportation_mode=='Ocean'&&queryDto.destPlantCode=='CEP'}">
                 <ec:table styleClass="DataGrid"  style="height:25px;width:100%;" border="1"  cellpadding="0" cellspacing="0" tableId="tableResults" 
					   items="queryDto.resultList"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
					   sortable="flase"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>				
						 	<ec:column value="${ROWCOUNT}" alias="Item"/>		 	
						    <ec:column property="name" alias="Name货物名品" />
						    <ec:column property="poNo" title="PO No.采购订单"/>
						    <ec:column property="invoiceNo" title="Invoice No.   发票号"/>
						    <ec:column property="terms" alias="Terms条款"/>
						    <ec:column property="portofloading" title="Port of loading启运港"/>
						    <ec:column property="portofDischarge" title="Port of Discharge目地港"/>
						    <ec:column property="bLNo" title="B/L Number提 单 号 "/>
						    <ec:column property="container_40HG" alias="Container type"/>
						    <ec:column property="package_NO" alias="箱号"/>
						    <ec:column property="pickupAdd" title="Pick up Add.提货地"/>
						    <ec:column property="pickuptime" title="Pick up time装箱时间"/>
						    <ec:column property="customs" alias="Customs报关时间"/>
						    <ec:column property="etd" title="ETD预定海船期"/>
						    <ec:column property="vessel" title="Vessel船名航次"/>
						    <ec:column property="atd" title="实际启运日期 ATD"/>
						    <ec:column property="eta" title="ETA Port预计到日期"/>
						    <ec:column property="ata" title="ATA Port 实际抵达日期"/>
						    <ec:column property="customs_clearance_Finish_Date" alias="Customs clearance Finish Date"/>
						    <ec:column property="arrive_VCCD" title="Dest Plant Code"/>
						    <ec:column property="comments" title="Remark 备注"/>
						 </ec:row>
						 
						 
						
					</ec:table>
					</core:if>
					
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
              <td align="right"><input style="width:120px; text-align:center;" class="Button" type="button"  onClick="exportToExcel();" value="Export to Excel" name="btnAdd">
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
   </span>
  <span id="PageMessageUpdatePanel"></span> 
 <script type="text/javascript">
 function exportToExcel(){
	 submitFormForAnji("report_form","${webcontext}/reporting/historicalShipment/exportExcel","2");
	// $("#report_form").attr("action","exportExcel");
	// $("#report_form").submit();
 }
 
 function search(){
	 submitFormForAnji("report_form","${webcontext}/reporting/historicalShipment/queryInfo",null);
	// $("#report_form").attr("action","queryInfo");
	// $("#report_form").submit();
 }
 
 function setVaild(button){
	 if($(button).val()=="Ocean"){
		 $("#destPlantCode").attr("class","RequiredField userInput");
	 }else{
		 $("#destPlantCode").attr("class","");
		 $(".validSpan").remove();
	 }
	 
 }
 </script>
  </form:form>
</body>
</html>