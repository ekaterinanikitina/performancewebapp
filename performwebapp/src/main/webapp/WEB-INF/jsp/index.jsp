<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.js"></script>
<meta charset="utf-8">
<title>performance page</title>
</head>
<body>
<h2>Производительность: </h2>
<script type="text/javascript" src='<c:url value="/resources/js/updatePage.js" />'></script>
<script type="text/javascript">getPerformance();</script>
<table id="table">
  <tr>
    <td>CPU: </td>
    <c:choose>
      <c:when test="${cpu < 80}">
        <td><font color="green">${cpu}</font></td>
        <td><font color="green">%</font></td>
      </c:when>
      <c:when test="${cpu >= 80}">
        <td><font color="red">${cpu}</font></td>
        <td><font color="red">%</font></td>
      </c:when>
    </c:choose> 
  </tr>
  <tr>
    <td>Memory: </td>
    <c:choose>
      <c:when test="${memory >= 20}">
        <td><font color="green">${memory}</font></td>
        <td><font color="green">%</font></td>
      </c:when>
      <c:when test="${memory < 20}">
        <td><font color="red">${memory}</font></td>
        <td><font color="red">%</font></td>
      </c:when>
    </c:choose>   
  </tr>
</table>
<table id="messageList"></table>

</body>
</html>