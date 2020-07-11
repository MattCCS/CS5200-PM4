<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create IngredientNutrientValue </title>
</head>
<body>
    <a href="index.jsp">Home</a>

    <h1>Create IngredientNutrientValue </h1>

    <form action=createIngredientNutrientValue  method="post">
        <p>
            <label for="id">Id</label>
            <input id="id" name="id" value="${fn:escapeXml(param.id)}">
        </p>
        <p>
            <label for="ingredientId">IngredientId</label>
            <input id="ingredientId" name="ingredientId" value="${fn:escapeXml(param.ingredientId)}">
        </p>
        <p>
            <label for="nutrientCodeId">NutrientCodeId</label>
            <input id="nutrientCodeId" name="nutrientCodeId" value="${fn:escapeXml(param.nutrientCodeId)}">
        </p>
        <p>
            <label for="nutrientValue">NutrientValue</label>
            <input id="nutrientValue" name="nutrientValue" value="${fn:escapeXml(param.nutrientValue)}">
        </p>
        <p>
            <label for="nutrientValueUnit">NutrientValueUnit</label>
            <input id="nutrientValueUnit" name="nutrientValueUnit" value="${fn:escapeXml(param.nutrientValueUnit)}">
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
                <th>IngredientId</th>
                <th>NutrientCodeId</th>
                <th>NutrientValue</th>
                <th>NutrientValueUnit</th>
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.getId()}" /></td>
                    <td><c:out value="${result.getIngredientId()}" /></td>
                    <td><c:out value="${result.getNutrientCodeId()}" /></td>
                    <td><c:out value="${result.getNutrientValue()}" /></td>
                    <td><c:out value="${result.getNutrientValueUnit()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
