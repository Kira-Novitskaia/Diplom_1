package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BunTest {
    private String bunName;
    private float bunPrice;

    @Before
    public void setUp() {
        Faker faker = new Faker(new java.util.Locale("en-US")); // Устанавливаем локаль
        bunName = faker.funnyName().name();

        // Генерируем строку цены
        String priceString = faker.commerce().price();
        try {
            priceString = priceString.replace(",", "."); // Убираем возможные запятые
            bunPrice = Float.parseFloat(priceString); // Парсим строку в float
        } catch (NumberFormatException e) {
            bunPrice = 10.0f; // Значение по умолчанию
        }
    }


    /**
     * Проверяет корректность метода getName().
     */
    @Test
    public void testGetName() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals("Имя булочки не соответствует ожиданиям", bunName, bun.getName());
    }

    /**
     * Проверяет корректность метода getPrice().
     */
    @Test
    public void testGetPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals("Цена булочки не соответствует ожиданиям", bunPrice, bun.getPrice(), 0.001f);
    }

    /**
     * Проверяет работу метода getPrice() для цены 0.
     */
    @Test
    public void testGetPriceWithZero() {
        Bun bun = new Bun(bunName, 0.0f);
        Assert.assertEquals("Цена булочки должна быть равна 0", 0.0f, bun.getPrice(), 0.001f);
    }

    /**
     * Проверяет работу метода getPrice() для отрицательной цены.
     */
    @Test
    public void testGetPriceWithNegativeValue() {
        Bun bun = new Bun(bunName, -10.0f);
        Assert.assertEquals("Цена булочки должна быть отрицательной", -10.0f, bun.getPrice(), 0.001f);
    }
}
