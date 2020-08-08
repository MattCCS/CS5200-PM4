<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
</head>
<body>
    <h1>Hello JSP and Servlet!</h1>
    
    <div id="Custom">
    	Custom Queries:<br/>
    	<a href="ShowWhatRecipesUseIngredients.jsp">What Recipes Use Ingredients</a><br/>
    	<a href="ShowIngredients.jsp">Ingredients For Recipe</a><br/>
    	<a href="ShowScaledIngredientsForRecipe.jsp">Scale Ingredients For Recipe</a><br/>
    	<a href="ShowNutrients.jsp">Nutrients For Recipe</a><br/>
    	<!-- <a href="ShowAllergens.jsp">Allergens For Recipe</a><br/> -->
    </div>
    
    <br/>
    CRUD OPERATIONS:

    <div id="Recipe">
        Recipe
        <a href="CreateRecipe.jsp"> C</a>
        <a href="ReadRecipe.jsp"> R</a>
        <a href="UpdateRecipe.jsp"> U</a>
        D
    </div>

    <div id="RecipeLine">
        RecipeLine
        <a href="CreateRecipeLine.jsp"> C</a>
        <a href="ReadRecipeLine.jsp"> R</a>
        U D
    </div>

    <div id="Food">
        Food
        <a href="CreateFood.jsp"> C</a>
        <a href="ReadFood.jsp"> R</a>
        U D
    </div>

    <div id="Category">
        Category
        <a href="CreateCategory.jsp"> C</a>
        <a href="ReadCategory.jsp"> R</a>
        U D
    </div>

    <div id="FoodNutrientValue">
        FoodNutrientValue
        <a href="CreateFoodNutrientValue.jsp"> C</a>
        <a href="ReadFoodNutrientValue.jsp"> R</a>
        U D
    </div>

    <div id="FoodGroup">
        FoodGroup
        <a href="CreateFoodGroup.jsp"> C</a>
        <a href="ReadFoodGroup.jsp"> R</a>
        U D
    </div>

    <div id="Nutrient">
        Nutrient
        <a href="CreateNutrient.jsp"> C</a>
        <a href="ReadNutrient.jsp"> R</a>
        U D
    </div>

</body>
</html>
