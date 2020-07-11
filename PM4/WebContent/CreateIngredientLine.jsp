<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create IngredientLine</title>
</head>
<body>
    <a href="index.jsp">Home</a>

    <h1>Create IngredientLine</h1>

    <form action=createIngredientLine method="post">
        <p>
            <label for="id">Id</label>
            <input id="id" name="id" value="${fn:escapeXml(param.id)}">
        </p>
        <p>
            <label for="amountNumerator">AmountNumerator</label>
            <input id="amountNumerator" name="amountNumerator" value="${fn:escapeXml(param.amountNumerator)}">
        </p>
        <p>
            <label for="amountDenominator">AmountDenominator</label>
            <input id="amountDenominator" name="amountDenominator" value="${fn:escapeXml(param.amountDenominator)}">
        </p>
        <p>
            <label for="unitOfMeasurement">UnitOfMeasurement</label>
            <input id="unitOfMeasurement" name="unitOfMeasurement" value="${fn:escapeXml(param.unitOfMeasurement)}">
        </p>
        <p>
            <label for="foodId">FoodId</label>
            <input id="foodId" name="foodId" value="${fn:escapeXml(param.foodId)}">
        </p>
        <p>
            <label for="ingredientId">IngredientId</label>
            <input id="ingredientId" name="ingredientId" value="${fn:escapeXml(param.ingredientId)}">
        </p>
        <p>
            <label for="description">Description</label>
            <input id="description" name="description" value="${fn:escapeXml(param.description)}">
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
                <th>AmountNumerator</th>
                <th>AmountDenominator</th>
                <th>UnitOfMeasurement</th>
                <th>FoodId</th>
                <th>IngredientId</th>
                <th>Description</th>
                
            </tr>
            <c:forEach items="${results}" var="result" >
                <tr>
                    <td><c:out value="${result.getId()}" /></td>
                    <td><c:out value="${result.getAmountNumerator()}" /></td>
                    <td><c:out value="${result.getAmountDenominator()}" /></td>
                    <td><c:out value="${result.getUnitOfMeasurement()}" /></td>
                    <td><c:out value="${result.getFoodId()}" /></td>
                    <td><c:out value="${result.getIngredientId()}" /></td>
                    <td><c:out value="${result.getDescription()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
