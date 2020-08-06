<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Scaled Ingredients For Recipe</title>
</head>
<body>
    <a href="index.jsp">Home</a>

    <h1>Show Scaled Ingredients For Recipe</h1>

    <form action=showScaledIngredientsForRecipe method="get">
        <p>
            <label for="name">Recipe Name</label>
            <input id="name" name="name" value="${fn:escapeXml(param.id)}">
        </p>
        <p>
            <label for="scaleUp">Scale Up Factor</label>
            <input id="scaleUp" name="scaleUp" value="${fn:escapeXml(param.id)}">
        </p>
        <p>
            <label for="scaleDown">Scale Down Factor</label>
            <input id="scaleDown" name="scaleDown" value="${fn:escapeXml(param.id)}">
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
                <th>numerator</th>
                <th>denominator</th>
                <th>unitOfMeasurement</th>
                <th>foodName</th>
                <th>foodDescription</th>
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.get(0)}" /></td>
                    <td><c:out value="${result.get(1)}" /></td>
                    <td><c:out value="${result.get(2)}" /></td>
                    <td><c:out value="${result.get(3)}" /></td>
                    <td><c:out value="${result.get(4)}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
