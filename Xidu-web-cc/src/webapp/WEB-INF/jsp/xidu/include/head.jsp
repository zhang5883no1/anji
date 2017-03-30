<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
                <li><a id="header_hlHome2"	href="http://matrix.cevalogistics.com/default.aspx"><img id="header_imgMatrixLogo" src="${imagepath}/site_logo.gif"	style="border-width: 0px;" align="middle"></a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">当前用户：${currentUser.userCode }</a></li>
                <li><a id="header_lnkLogout" class="HeaderLinkButton"	href="${webcontext}/logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <core:forEach items="${menuItems}" var="item">	
					<li class="page_item">
						<a href="#" class=""><core:out value="${item.menuName}"/></a>
						<core:if test="${item.subMenus != null}">
							<!-- <ul class="menu_list"> -->
							<ul class='sub-menu'>
								<core:forEach items="${item.subMenus}" var="subItem">
									<li>
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
    </div>
    <!--/sidebar-->
    