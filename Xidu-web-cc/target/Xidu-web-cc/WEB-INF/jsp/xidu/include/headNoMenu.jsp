  <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
  
  <!-- BEGIN HEADER -->
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr style="height:40px">
      <td width="80%"><img id="header_imgMatrixLogo" src="${imagepath}/site_logo.gif" align="middle" style="border-width:0px;" /></td>
      <td><table cellpadding="0" cellspacing="0" width="100%" border="0" style="padding: 0 4 0 4;">
          <tr >
            <td align="right" nowrap="nowrap"><span id="header_upLinks">
              <table cellpadding="4" cellspacing="0" border="0">
                <tr>
                  <td><a id="header_lnkHelp" disabled="disabled" class="HeaderLinkButton" onMouseOver="this.classname=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.classname=&#39;HeaderLinkButton&#39;;">Help</a></td>
                  <td class="HeaderLinkButton">|</td>
                  <td><span id="header_lblClose" class="HeaderLinkButton" onMouseOver="this.className=&#39;HeaderLinkButtonItemOver&#39;;" onMouseOut="this.className=&#39;HeaderLinkButton&#39;;" onClick="window.close();">Close Dialog</span></td>
                  <td style="width:20px;text-align:center" onClick="AbortRequestHandler();"><img id="imgUpdating" alt="" src="images/updating.gif" style="border-width:0px;visibility:hidden;cursor:hand;" /></td>
                </tr>
              </table>
              </span></td>
          </tr>
        </table></td>
    </tr>
    <tr style="background-image:url(${imagepath}/menu_bar.png);background-repeat:repeat-x;height:10px">
      <td colspan="2"></td>
    </tr>
  </table>
  <!-- END HEADER -->
  <div class="bground" style="display:none;text-align:center;vertical-align:middle;">
		<img alt="" src="${imagepath}/loading.gif">
	</div>