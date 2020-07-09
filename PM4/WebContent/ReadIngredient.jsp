<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read Ingredient</title>
</head>
<body>
    <a href="index.jsp">Home</a>

    <h1>Find an Ingredient by Name</h1>

    <form action=readIngredient method="get">
        <p>
            <label for="name">Name</label>
            <input id="name" name="name" value="${fn:escapeXml(param.name)}">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
    </form>
    <div>
        <h1>Results</h1>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.getId()}" /></td>
                    <td><c:out value="${result.getName()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
