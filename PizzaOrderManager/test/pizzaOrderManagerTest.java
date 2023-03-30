import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class pizzaOrderManagerTest {

    @Test
    public void getPriceMap() {

        String file = "data/sizes.txt";
        Map<String, Integer> map = new Map1L<>();
        Map<String, Integer> empty = new Map1L<>();
        PizzaOrderManager.getPriceMap(file, empty);

        map.add("small", 795);
        map.add("medium", 995);
        map.add("large", 1295);
        map.add("biggie", 1595);
        map.add("great biggie", 1995);

        assertEquals(map, empty);
    }

    @Test
    public void getPriceMap1() {

        String file = "data/toppings.txt";
        Map<String, Integer> map = new Map1L<>();
        Map<String, Integer> empty = new Map1L<>();
        PizzaOrderManager.getPriceMap(file, empty);

        map.add("extra cheese", 75);
        map.add("onion", 95);
        map.add("potato", 95);
        map.add("ham", 120);
        map.add("pineapple", 95);
        map.add("tomato", 95);
        map.add("anchovy", 150);
        map.add("pepperoni", 120);
        map.add("sausage", 120);
        map.add("mushroom", 95);
        map.add("green pepper", 95);

        assertEquals(map, empty);
    }

    @Test
    public void getOneOrder() {

        SimpleReader in = new SimpleReader1L("data/orders.txt");
        Map<String, Integer> sizeMap = new Map1L<>();
        Map<String, Integer> toppingMap = new Map1L<>();

        PizzaOrderManager.getPriceMap("data/sizes.txt", sizeMap);
        PizzaOrderManager.getPriceMap("data/toppings.txt", toppingMap);

        int price = PizzaOrderManager.getOneOrder(in, sizeMap, toppingMap);
        assertEquals(price, 1715);
    }

}
