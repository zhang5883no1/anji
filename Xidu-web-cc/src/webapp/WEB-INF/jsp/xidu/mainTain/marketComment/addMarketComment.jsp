<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>
<script type="text/javascript" src="${jspath}/xheditor-zh-cn.min.js?v=1.1.5"></script>
  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	MarketComment Base Info
  </title>
	<script LANGUAGE="JavaScript">		
		function saveCUstomer(){
			submitFormForAnji("addMarketComment_form","${webcontext}/mainTain/marketComment/saveMarketComment",0);
		}
		
		function backList(){
			submitHrefForAnji("${webcontext}/mainTain/marketComment/init",null);
			//location.href="${webcontext}/mainTain/marketComment/init";
		}
    </script>   
 </head>
<body leftMargin="0" topMargin="0">
   <form:form id="addMarketComment_form" name ="marketComment_form" action="saveMarketComment" method="post" modelAttribute="marketCommentDto">
     <form:hidden path="id"/>
	<!-- BEGIN HEADER -->
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
	    <tr style="height:40px">
	        <td width="80%">
	            <img id="header_imgMatrixLogo" src="${imagepath}/site_logo.gif" align="middle" style="border-width:0px;" />
	        </td>
	        <td>
	            <table cellpadding="0" cellspacing="0" width="100%" border="0" style="padding: 0 4 0 4;">
	                <tr >
	                    <td align="right" nowrap="nowrap">
	                        <span id="header_upLinks">                            
	                            <table cellpadding="4" cellspacing="0" border="0"><tr>
	                                <td><a id="header_lnkHelp" disabled="disabled" class="HeaderLinkButton" onMouseOver="this.classname=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.classname=&#39;HeaderLinkButton&#39;;">Help</a></td>
	                                <td class="HeaderLinkButton">|</td>
	                                <td><span id="header_lblClose" class="HeaderLinkButton" onMouseOver="this.className=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.className=&#39;HeaderLinkButton&#39;;" onClick="ClosePage();">Close Dialog</span></td>
			                        <td style="width:20px;text-align:center" onClick="AbortRequestHandler();"><img id="imgUpdating" alt="" src="${imagepath}/updating.gif" style="border-width:0px;visibility:hidden;cursor:hand;" /></td>
	                            </tr></table>
	                        </span>
	                    </td>
	                </tr>
	            </table>
	        </td>
	    </tr>
	    <tr style="background-image:url(${imagepath}/menu_bar.png);background-repeat:repeat-x;height:10px">
	        <td colspan="2"></td>
	    </tr>
	</table>
     <!-- END HEADER -->

			<table cellSpacing="1" cellPadding="1" width="100%">
				<tr width="100%">
					<td><span id="lblheader" class="PageTitle">市场评论</span></td>
				</tr>
			</table>			
	        <table cellSpacing="1" cellPadding="1" width="100%" height="30">
	            <tr>
	                <td>
	                    <fieldset><legend name="Legend1" class="legend" id="Legend1">详细</legend>
							        <table cellspacing="1" cellpadding="1" width="100%">
								        <tbody><tr width="100%">
									        <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">标题:</span></td>
									        <td nowrap="">
									        	<form:input path="title" cssStyle="width:190px;" cssClass="userInput " maxlength="50"/>
									        </td>
									        <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">星级:</span></td>
									        <td nowrap="">
									        	<form:input path="level" cssStyle="width:190px;" cssClass="userInput " maxlength="15"/>
									        </td>
									         <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">老师名字:</span></td>
									        <td nowrap="">
									        	<form:input path="teacherName" cssStyle="width:190px;" cssClass="userInput " maxlength="15"/>
									        </td>
									         <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">发布时间:</span></td>
									        <td nowrap="">
									        	<form:input path="date" cssStyle="width:190px;" cssClass="userInput Wdate" maxlength="40" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss' })"/>
									        </td>
								        </tr>
                                        <tr>
									         <td align="right" nowrap=""><span style="display:inline-block;" class="LabelBold" id="lblLength">内容:</span></td>
									        <td nowrap="">
									        	<textarea  name="content" class="userInput xheditor" rows="30" cols="200">${marketCommentDto.content } </textarea>
									        </td>
								        </tr>	
							        </tbody></table>
						        </fieldset>
	                </td>
	            </tr>
	           
	        </table>
            <table style="width:100%;">
              <tr>
					        <td nowrap=""><span id="imgRequiredField"><img style="border-width:0px;" src="${imagepath}/required.gif" id="imgRequired">&nbsp;<span class="Label">Required Field</span>&nbsp;</span></td>
					        <td align="right">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnDone" onClick="saveCUstomer();" value="保存" name="btnDone">
						        <input type="button" style="width:100px;" class="Button" type="button"  id="btnClose" onClick="backList();" value="返回" name="btnReturn"/>
					        </td>
				        </tr>
            </table>			    
        <span id="PageMessageUpdatePanel"></span>
	<script LANGUAGE="JavaScript">	
  		$(document).ready(
  				function(){
  					
  				}
  		);
  	</script> 
		</form:form>
    </body>
</html>
