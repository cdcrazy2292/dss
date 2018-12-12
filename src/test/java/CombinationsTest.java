import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class CombinationsTest {
    Combinations combinations;
    @Before
    public void setUp() throws Exception {
        combinations = new Combinations();
    }

    @Test
    public void allCombinationsFor10When2536()
    {
        int amount = 10;
        Integer[] coinArr = {2,5,3,6};
        int[] coinCounts = new int[coinArr.length];
        combinations.findNumberOfCombinations(amount, 0, coinArr, coinCounts);
        int result = combinations.getNumOfCombinations();
        assertEquals(5, result);
        System.out.println("Total Combinations " + result);

    }


    @Test
    public void test100CentsWithUSCoins() {
        int amount = 100;
        Integer[] coinArr = {25,10, 5, 1};
        int[] coinCounts = new int[coinArr.length];
        System.out.printf("%10s %4s %7s %4s", "Quarter", "Dime", "Nickel", "Penny");
        System.out.println();
        combinations.findNumberOfCombinations(amount, 0, coinArr, coinCounts);
        int result = combinations.getNumOfCombinations();
        System.out.println("Total Combinations " + result);
        assertEquals(242, result);

    }

    @Test
    public void test100CentsWithUSCoinsUnsorted() {
        int amount = 100;
        Integer[] coinArr = {25, 1, 5, 10};
        int[] coinCounts = new int[coinArr.length];
        System.out.printf("%10s %4s %7s %4s", "Quarter", "Dime", "Nickel", "Penny");
        System.out.println();
        combinations.findNumberOfCombinations(amount, 0, coinArr, coinCounts);
        int result = combinations.getNumOfCombinations();
        System.out.println("Total Combinations " + result);
        assertEquals(242, result);
    }

    @Test
    public void testWithEmptyArray() {
        int amount = 100;
        Integer[] coinArr = {};
        int[] coinCounts = new int[coinArr.length];
        combinations.findNumberOfCombinations(amount, 0, coinArr, coinCounts);
        assertEquals(0, combinations.getNumOfCombinations());
    }

    @Test
    public void testWhenNoCombinationsExist() {
        int amount = 1;
        Integer[] coinArr = {5, 10, 20};
        int[] coinCounts = new int[coinArr.length];
        combinations.findNumberOfCombinations(amount, 0, coinArr, coinCounts);
        assertEquals(0, combinations.getNumOfCombinations());
    }



    /**
     *Tests for question 2 are below, including utilities I created
     **/

    @Test
    public void testMapCreation() {
        System.out.println(combinations.createCurrencyMap("Quarter,25,Dime,10,Nickel,5,Penny,1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapExceptionWhenMissingParameters() {
        combinations.createCurrencyMap("Quarter,25,Dime,10,Nickel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapExceptionWhenMoneyParamIsNotANumber() {
        combinations.createCurrencyMap("Quarters,rt,Dime,10");
    }

    @Test
    public void testCurrencyArrayCreation() {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("Quarter", (double)25);
        map.put("Dime", (double)10);
        map.put("Nickel", (double)5);
        Double[] currArr = combinations.createCurrencyDenominationArr(map);

        for (int i = 0; i< currArr.length; i++) {
            System.out.println(currArr[i]);
        }

    }

    @Test
    public void testCustomCurrencyCombination() {
        double amount = 3;
        Double[] currArr = {1.5, 3.0};
        int[] coinCount = new int[currArr.length];
        combinations.findNumberOfCombinations(amount, 0, currArr, coinCount);
        System.out.println(combinations.getNumOfCombinations());

    }

    @Test
    public void testCustomCurrencyCombinationWithUSCois() {
        double amount = 100.0;
        Double[] currArr = {1.0, 5.0, 25.0, 10.0, 50.0, 150.0};
        int[] coinCount = new int[currArr.length];
        combinations.findNumberOfCombinations(amount, 0, currArr, coinCount);
        System.out.println(combinations.getNumOfCombinations());
    }
}