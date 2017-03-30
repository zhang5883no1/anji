<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>

  </title>
	<script LANGUAGE="JavaScript">		
		function backtoList(){
			window.close();
		}
    </script>   
	
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">
  <br>
  <table border="0" cellspacing="2" width="100%">
    <tbody>
      <tr>
        <td><span id="lblHeader" class="PageTitle">International Supplier Detail</span></td>
      </tr>
    </tbody>
  </table>
  <span id="UpdatePanelSearch">
  <table cellpadding="3" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><fieldset>
            <legend id="legSearchCriteria" class="legend">Details</legend>
            <table border="0" cellpadding="1" cellspacing="1" width="100%">
              <tbody>
                <tr>
                  <td align="right" nowrap="nowrap" width="120px"><span id="lblLocationType" class="LabelBold">Pickup Date:</span></td>
                  <td nowrap="nowrap"><span class="Label">${interSupplier.pickUpDate }</span>
                  
                  </td>
                  <td align="right" nowrap="nowrap"><span id="lblIsHub" class="LabelBold">Oms No:</span></td>
                  <td nowrap="nowrap"><span class="Label"></span></td>
                  <td align="right" nowrap="nowrap"><span id="lblCity" class="LabelBold">International Supplier No:</span></td>
                  <td nowrap="nowrap"><span class="Label">${interSupplier.totalGross }</span>
                    </td>
                </tr>
                <tr>
                  
                  <td align="right" nowrap="nowrap"><span id="lblAliasType" class="LabelBold">Status:</span></td>
                  <td nowrap="nowrap"><span class="Label">${interSupplier.status }</span>
                    </td>
                  
                  <td align="right" nowrap="nowrap"><span id="lblAlias" class="LabelBold">Date Type:</span></td>
                  <td nowrap="nowrap"><span class="Label">${interSupplier.package_type }</span>
                    </td>
                    <td align="right" nowrap="nowrap"></td>
                  <td nowrap="nowrap"></td>
                </tr>
                
              </tbody>
            </table>
            
            
          </fieldset></td>
      </tr>
    </tbody>
  </table>
  </span> 

  <table id="tableResults" cellpadding="1" cellspacing="1" width="100%">
    <tbody>
      <tr>
        <td><table border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody>
              <tr>
                <td colspan="2">
                <div id="resizableDiv" class="DivResultsGrid" style="OVERFLOW: auto;border-top:solid 1px silver">
                    <ec:table styleClass="DataGrid"  style="width:100%;" border="0" cellpadding="2" cellspacing="2" tableId="tableResults"
					   items="palletinfos"
					   imagePath="${webcontext}/images/table/*.gif"
					   filterable="false"
  					   showPagination="false"
  					   autoIncludeParameters="false"
  					   showTitle="false"
  					   showStatusBar="false"
  					   var="bo"
					   >
						 <ec:row>
						    <ec:column property="pn" title="Part Number"/>
						    <ec:column property="qty" title="Quantity"/>
						    <ec:column property="length" title="Length"/>
						    <ec:column property="width" title="Width"/>
						    <ec:column property="height" title="Height"/>
						    <ec:column property="weightPallet" title="Weight"/>
						    <ec:column property="remark" title="Remarks"/>
						 </ec:row>
					</ec:table>
                  </div></td>
              </tr>
              
            </tbody>
          </table>
          <table style="width:100%">
            <tr>
              <td align="right"><input style="width:90px; text-align:center;" class="Button" type="button"  onClick="backtoList();" value="Close" name="btnAdd">
              </td>
            </tr>
          </table>
          </td>
      </tr>
    </tbody>
  </table>
   </span>
  <span id="PageMessageUpdatePanel"></span> 
</body>
</html>