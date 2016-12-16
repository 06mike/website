<%@ taglib uri='/WEB-INF/tld/fmt.tld' prefix='fmt' %>
<%@ taglib uri='/WEB-INF/tld/c.tld' prefix='c' %>
<%@ taglib uri='/WEB-INF/tld/fn.tld' prefix='fn' %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="tpath" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<%response.setHeader("Pragma","No-cache");response.setHeader("Cache-Control","no-cache");response.setDateHeader("Expires",0);%>
<%request.setAttribute("rEnter", "\r\n"); %>