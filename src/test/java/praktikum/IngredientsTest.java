package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientsTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientsTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{index}: Ingredient type: {0}, name: {1}, price: {2}")
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.FILLING, "Salad", 10.10f},
                {IngredientType.SAUCE, "Ketchup", 5.50f},
                {IngredientType.FILLING, "", 0.0f}, // Пустое имя
                {IngredientType.SAUCE, "Mayonnaise", -10.0f} // Отрицательная цена
        };
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Цена ингредиента рассчитана неверно", price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Имя ингредиента не соответствует ожиданиям", name, ingredient.getName());
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Тип ингредиента не соответствует ожиданиям", type, ingredient.getType());
    }
}
