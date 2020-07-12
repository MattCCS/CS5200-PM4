<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create RecipeIngredientLine</title>
</head>
<body>
    <a href="index.jsp">Home</a>

    <h1>Create RecipeIngredientLine</h1>

    <form action=createRecipeIngredientLine method="post">
        <p>
            <label for="id">Id</label>
            <input id="id" name="id" value="${fn:escapeXml(param.id)}">
        </p>
        <p>
            <label for="recipeId">RecipeId</label>
            <input id="recipeId" name="recipeId" value="${fn:escapeXml(param.recipeId)}">
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
                <th>RecipeId</th>
                <th>IngredientLineId</th>
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.getId()}" /></td>
                    <td><c:out value="${result.getRecipeId()}" /></td>
                    <td><c:out value="${result.getIngredientLineId()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
