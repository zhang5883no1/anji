<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<core:set var ="webcontext" scope="request" value="${pageContext.request.contextPath}"/>
<core:set var ="imagepath" scope="request" value="${webcontext}/images"/>
<core:set var ="csspath" scope="request" value="${webcontext}/css"/>
<core:set var ="jspath" scope="request" value="${webcontext}/scripts"/>
<core:set var ="jsppath" scope="request" value="${webcontext}/jsp"/>
<core:set var ="htmlpath" scope="request" value="${webcontext}/html"/>
<core:set var ="easyuipath" scope="request" value="${webcontext}/resource/easyui"/>

<core:set var ="anjijspath" scope="request" value="${webcontext}/js"/>
<core:set var ="cms1url" scope="request" value="@token-cms1-server-url@"/>
<%java.util.Calendar cal=java.util.Calendar.getInstance();cal.setTime(new java.util.Date());cal.add(java.util.Calendar.DATE, -1); java.util.Date yesterday = cal.getTime(); cal.add(java.util.Calendar.DATE, + 2); java.util.Date tomorrow = cal.getTime();%>
