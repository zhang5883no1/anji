<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <span id="UpdatePanelResults">
   <form:hidden id="totalPage" path="pager.totalPages"/>
   <form:hidden id="totalSize" path="pager.totalSize"/>
  <span id="resultsGridNavigator" style="display:inline-block; width:99%;">
  <table cellspacing="0" cellpadding="0" border="0" style="width:100%;border-collapse:collapse;">
    <tr>
      <td style="white-space:nowrap;"><input name="resultsGridNavigator$ctl01" type="button" value="Select All" style="display:none;" class="SelectAllButton" /></td>
      <td class="GridNavButton" style="padding-left:5px;"><a title="Export Grid to Excel" href="javascript:__doPostBack(&#39;resultsGridNavigator$ctl02&#39;,&#39;&#39;)" style="display:none;">Export</a></td>
      <td style="white-space:nowrap;"><span class="LabelBold">Records</span></td>
      <td class="Label">&nbsp;&nbsp;</td>
      <td><span class="Label">
      	<spring:bind path="pager.currentPageFormRow">${status.value }</spring:bind>
      </span></td>
      <td class="Label">-</td>
      <td><span class="Label">
      	<spring:bind path="pager.currentPageToRow">${status.value }</spring:bind>
      <form:label path="pager.currentPageToRow"/></span></td>
      <td class="Label">&nbsp;&nbsp;</td>
      <td class="Label" style="white-space:nowrap;"><span class="LabelBold">of</span></td>
      <td class="Label">&nbsp;&nbsp;</td>
      <td><span class="Label"> 
      <spring:bind path="pager.totalSize">${status.value}</spring:bind>
      </span></td>
      <td style="width:65%;"></td>
      <td style="white-space:nowrap;"><span class="LabelBold">Page</span></td>
      <td class="Label">&nbsp;&nbsp;</td>
      <td>
      	 <form:input id="currentPage"  path="pager.currentPage" cssStyle="width:25px;text-align:right;" onchange="goToPage();"  maxlength="9"/>
      </td>
      <td class="Label">&nbsp;&nbsp;</td>
      <td style="white-space:nowrap;"><span class="LabelBold">of</span></td>
      <td class="Label">&nbsp;&nbsp;</td>
      <td><span class="Label"><spring:bind path="pager.totalPages"><core:if test="${status.value == 0}">${status.value + 1 }</core:if><core:if test="${status.value > 0}">${status.value}</core:if></spring:bind></span></td>
      <td class="Label" style="width:90px;">&nbsp;&nbsp;&nbsp;</td>
      <td class="GridNavFirst"><a id="" title="First page" href="javascript:void(0);" onclick="gotoFirst();" style="color:Black;font-family:webdings;font-size:13pt;text-Decoration:none;"><img src="${imagepath}/1.jpg" /></a></td>
      <td class="GridNavPrevious"><a id="" title="Previous page" href="javascript:void(0);" onclick="gotoPre();" style="color:Black;font-family:webdings;font-size:13pt;text-Decoration:none;"><img src="${imagepath}/2.jpg" /></a></td>
      <td class="GridNavNext"><a id="" title="Next page" href="javascript:void(0);" onclick="gotoNext();" style="color:Black;font-family:webdings;font-size:13pt;text-Decoration:none;"><img src="${imagepath}/3.jpg" /></a></td>
      <td class="GridNavLast"><a id="" title="Last page" href="javascript:void(0);" onclick="gotoLast();" style="color:Black;font-family:webdings;font-size:13pt;text-Decoration:none;"><img src="${imagepath}/4.jpg" /></a></td>
    </tr>
  </table>
  </span>