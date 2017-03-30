<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


<!-- BEGIN HEADER -->
<div id="header_PageHeader">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr style="height: 57px">
				<td width="80%"><a id="header_hlHome1" href="${webcontext}/logout"><!-- <img
						id="header_imgCorpLogo" src="${imagepath}/corp.png"
						style="border-width: 0px; margin-top: 4px; margin-left: 6px"> --></a></td>
				<td colspan="2" width="10%"><table border="0" cellpadding="0"
						cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td align="right" nowrap="nowrap"><span
									id="header_UpdatePanelLinks">
										<table style="padding: 0 4 0 4;" border="0" cellpadding="0" cellspacing="0" name="headlist" id="headlist">
											<tbody>
												<tr>
													<td>Welcome <strong>${currentUser.userCode }</strong></td>
													<td width="20px">&nbsp;</td>
													<td><a id="header_lnkLogout" class="HeaderLinkButton"
														href="${webcontext}/logout">Logoff</a></td>
													<td width="20px">&nbsp;</td>
												</tr>
											</tbody>
										</table>
								</span></td>
							</tr>
							<tr>
								<td align="right"><a id="header_hlHome2"
									href="http://matrix.cevalogistics.com/default.aspx"><img
										id="header_imgMatrixLogo" src="${imagepath}/site_logo.gif"
										style="border-width: 0px;" align="middle"></a></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
			<tr
				style="background-image:url(${imagepath}/menu_bar.png);background-repeat:repeat-x;height:26px">
				<td valign="middle">
					<!-- BEGIN MENU -->

					<div
						style="position: absolute; top: 0px; left: 0px; visibility: hidden;">
						<img src="${imagepath}/arrow_gray.gif" alt="" height="0" width="0">
					</div>
					<div id="header_MainMenu" style="height: 26px;">
						<div id="top">
							<div class="navwrap">
								  
           					 <ul id="nav">
			 					 <core:forEach items="${menuItems}" var="item">	
				  					 <li class="page_item">
				       					<!-- the first level menu -->
				       					<a href="#" class=""><core:out value="${item.menuName}"/></a>
				       						<core:if test="${item.subMenus != null}">
					      						 <!-- <ul class="menu_list"> -->
					      						 <ul class='children'>
					        						<core:forEach items="${item.subMenus}" var="subItem">
					        						
					         							 <li class="page_item">
					          								<a href="${webcontext}${subItem.url}" >
					             								<core:out value="${subItem.menuName}"/>
					         								 </a>
					          							</li>
					          						
					        						</core:forEach>
					      						 </ul>
					  						 </core:if>       
				   						</li>
			 					</core:forEach>
			 				 </ul>
			  			
					
							</div>
							<!--end navwrap-->
						</div>
					</div> 
				</td>
				<td width="2%"></td>
				<td style="width: 18%; white-space: nowrap;"></td>
			</tr>
		</tbody>
	</table>
	<!-- END HEADER -->

	<!-- BEGIN RIBBON -->
	<span id="header_UpdatePanelRibbon"> </span>
	<!-- END RIBBON -->
	<div class="bground" style="display:none;text-align:center;vertical-align:middle;">
		<img alt="" src="${imagepath}/loading.gif">
	</div>

</div>