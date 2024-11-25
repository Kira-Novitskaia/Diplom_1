package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Faker faker;
    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        faker = new Faker();
        burger = new Burger();
    }

    /**
     * Проверяет установку булочки.
     */
    @Test
    public void testSetBuns() {
        String bunName = faker.funnyName().name();
        Mockito.when(mockBun.getName()).thenReturn(bunName);
        burger.setBuns(mockBun);
        Assert.assertEquals("Булочка не установилась корректно", bunName, burger.bun.getName());
    }

    /**
     * Проверяет добавление ингредиента.
     */
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient);
        Assert.assertTrue("Ингредиент не добавлен в бургер", burger.ingredients.contains(mockIngredient));
    }

    /**
     * Проверяет удаление ингредиента.
     */
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);
        Assert.assertEquals("Ингредиент не был удален", initialSize - 1, burger.ingredients.size());
    }

    /**
     * Проверяет перемещение ингредиента.
     */
    @Test
    public void testMoveIngredient() {
        Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "Cheese", 50.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE, "Ketchup", 20.0f);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);
        Assert.assertEquals("Ингредиенты не поменялись местами", ingredient1, burger.ingredients.get(1));
    }

    /**
     * Проверяет расчет стоимости бургера.
     */
    @Test
    public void testGetPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);

        float expectedPrice = 2 * 100.0f + 50.0f;
        Assert.assertEquals("Цена бургера рассчитана неверно", expectedPrice, burger.getPrice(), 0.001f);
    }

    /**
     * Проверяет создание чека.
     */
    @Test
    public void testGetReceipt() {
        Mockito.when(mockBun.getName()).thenReturn("Test Bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        burger.setBuns(mockBun);

        Mockito.when(mockIngredient.getName()).thenReturn("Test Ingredient");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        Assert.assertTrue("Чек должен содержать название булочки", receipt.contains("Test Bun"));
        Assert.assertTrue("Чек должен содержать ингредиент", receipt.contains("Test Ingredient"));
    }
}
