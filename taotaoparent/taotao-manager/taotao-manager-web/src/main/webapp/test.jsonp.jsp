<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String callback = request.getParameter("callback");
    out.print(callback + "({\"abc\":\"123\"})");
%>
