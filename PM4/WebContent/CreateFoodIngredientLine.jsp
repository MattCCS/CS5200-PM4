<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create FoodIngredientLine</title>
</head>
<body>
    <a href="index.jsp">Home</a>

    <h1>Create FoodIngredientLine</h1>

    <form action=createFoodIngredientLine method="post">
        <p>
            <label for="id">Id</label>
            <input id="id" name="id" value="${fn:escapeXml(param.id)}">
        </p>
        <p>
            <label for="foodId">FoodId</label>
            <input id="foodId" name="foodId" value="${fn:escapeXml(param.foodId)}">
        </p>
        <p>
            <label for="ingredientLineId">IngredientLineId</label>
            <input id="ingredientLineId" name="ingredientLineId" value="${fn:escapeXml(param.ingredientLineId)}">
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
                <th>FoodId</th>
                <th>IngredientLineId</th>                
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.getId()}" /></td>
                    <td><c:out value="${result.getFoodId()}" /></td>
                    <td><c:out value="${result.getIngredientLineId()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
