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
    	<!-- <a href="ShowCaloriesForRecipe.jsp">Calories For Recipe</a><br/> -->
    	<a href="ShowIngredients.jsp">Ingredients For Recipe</a><br/>
    	<a href="ShowScaledIngredientsForRecipe.jsp">Scale Ingredients For Recipe</a><br/>
    	<!-- <a href="ShowWhatRecipesUseFood.jsp">What Recipes Use Food</a><br/> -->
    	<a href="ShowWhatRecipesUseIngredients.jsp">What Recipes Use Ingredients</a><br/>
    	<!-- <a href="ShowRecipesLessThanXCalories.jsp">Recipes Less Than X Calories</a><br/> -->
    	<!-- <a href="ShowRecipesWithoutCategory.jsp">Recipes Without Category</a><br/> -->
    	<!-- <a href="ShowHealthierRecipe.jsp">Pick Healthier Recipe</a><br/> -->
    	<!-- <a href="ShowWhatRecipesExcludeCategory.jsp">What Recipes Exclude Category</a><br/> -->
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

    <div id="RecipeIngredientLine">
        RecipeIngredientLine
        <a href="CreateRecipeIngredientLine.jsp"> C</a>
        <a href="ReadRecipeIngredientLine.jsp"> R</a>
        U D
    </div>

    <div id="IngredientLine">
        IngredientLine
        <a href="CreateIngredientLine.jsp"> C</a>
        <a href="ReadIngredientLine.jsp"> R</a>
        U D
    </div>

    <div id="FoodIngredientLine">
        FoodIngredientLine
        <a href="CreateFoodIngredientLine.jsp"> C</a>
        <a href="ReadFoodIngredientLine.jsp"> R</a>
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

    <div id="Ingredient">
        Ingredient
        <a href="CreateIngredient.jsp"> C</a>
        <a href="ReadIngredient.jsp"> R</a>
        U D
    </div>

    <div id="IngredientNutrientValue">
        IngredientNutrientValue
        <a href="CreateIngredientNutrientValue.jsp"> C</a>
        <a href="ReadIngredientNutrientValue.jsp"> R</a>
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
