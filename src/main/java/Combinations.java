import java.util.*;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;

public class Combinations {


    private int numOfCombinations = 0;

    /**ALGORITHM IMPLEMENTATION**/

    /**
     * We need to create a recursive function that calculates the number of
     * combinations for a given amount when given a set of coin denominations.
     *
     * @param remainingAmount: The initial, or left amount that needs to be calculated.
     * @param index:           The index of the current coin in the array of coins.
     * @param coins:           The array with the coin denominations.
     * @param coinCount:       Array that keeps a count of each coin's amount for the combination.
     *
     *                         The array of coins should be sorted!
     */

    public void findNumberOfCombinations(int remainingAmount, int index, Integer[] coins, int[] coinCount) {

        if (coins.length > 0) {
            // Sorting the array in descending order
            Arrays.sort(coins, Collections.reverseOrder());
        } else {
            return;
        }

        if (index < coins.length - 1) { // making sure we go through coin array up until the last one

            if (remainingAmount > 0) { // if we have a remaining amount, We still have money to compute
                int coinValue = coins[index]; // assigning coin value based on index

                if (coinValue <= remainingAmount) { //if the coin denomination is less than the remaining amount

                    for (int i = 0; i <= (remainingAmount / coinValue); i++) { // Try every number of combinations for the coin
                        coinCount[index] = i;
                        findNumberOfCombinations(remainingAmount - (coinValue * i), index + 1, coins, coinCount);
                    }
                    coinCount[index] = 0;
                } else { // If there is a coin that's greater than the amount
                    findNumberOfCombinations(remainingAmount, index + 1, coins, coinCount); //Find more combinations
                }
            } else { // we have nothing left
                printCoinsCombination(coins, coinCount);
                numOfCombinations++;
            }
        } else { // Last coin
            if (remainingAmount > 0) {
                int coinValue = coins[index];
                if (coinValue <= remainingAmount) { //If the coin is still less than the amount, we can check if
                    if (remainingAmount % coinValue == 0) { //it evenly divides to the amount
                        coinCount[index] = remainingAmount / coinValue;
                        printCoinsCombination(coins, coinCount);
                        coinCount[index] = 0;
                        numOfCombinations++;
                    }
                }
            } else {
                printCoinsCombination(coins, coinCount);
                numOfCombinations++;
            }
        }
    }


    /**Method overloading to handle Double type parameters**/

    public void findNumberOfCombinations(double remainingAmount, int index, Double[] coins, int[] coinCount) {

        if (coins.length > 0) {
            // Sorting the array in descending order
            Arrays.sort(coins, Collections.reverseOrder());
        } else {
            return;
        }

        if (index < coins.length - 1) { // making sure we go through coin array up until the last one

            if (remainingAmount > 0) { // if we have a remaining amount, We still have money to compute
                double coinValue = coins[index]; // assigning coin value based on index

                if (coinValue <= remainingAmount) { //if the coin denomination is less than the remaining amount

                    for (int i = 0; i <= (remainingAmount / coinValue); i++) { // Try every number of combinations for the coin
                        coinCount[index] = i;
                        findNumberOfCombinations(remainingAmount - (coinValue * i), index + 1, coins, coinCount);
                    }
                    coinCount[index] = 0;
                } else { // If there is a coin that's greater than the amount
                    findNumberOfCombinations(remainingAmount, index + 1, coins, coinCount); //Find more combinations
                }
            } else { // we have nothing left
                printCoinsCombination(coins, coinCount);
                numOfCombinations++;
            }
        } else { // Last coin
            if (remainingAmount > 0) {
                double coinValue = coins[index];
                if (coinValue <= remainingAmount) {
                    if (remainingAmount % coinValue == 0) {
                        coinCount[index] = (int) (remainingAmount / coinValue);
                        printCoinsCombination(coins, coinCount);
                        coinCount[index] = 0;
                        numOfCombinations++;
                    }
                    else {
                    }
                }
            } else {
                printCoinsCombination(coins, coinCount);
                numOfCombinations++;
            }
        }
    }



    public static void main(String[] args) {
      questionOne();
      questionTwo();
    }


    /**Separating questions into functions**/

    public static void questionOne() {
        int totalAmount = 100;
        Integer[] coinDenominationArr = {1, 5, 10, 25};
        int[] coinCombinationCount = new int[coinDenominationArr.length];
        int initialIndex = 0;
        Combinations combinations = new Combinations();
        System.out.printf("%10s %4s %7s %4s", "Quarter", "Dime", "Nickel", "Penny");
        System.out.println();
        combinations.findNumberOfCombinations(totalAmount, initialIndex, coinDenominationArr, coinCombinationCount);
        System.out.println("Count " + combinations.getNumOfCombinations());
    }

    public static void questionTwo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Currency denominations");
        String userInput = scanner.nextLine();
        Combinations combinations = new Combinations();
        Map<String, Double> currencyMap = combinations.createCurrencyMap(userInput);
        Double[] currencyArr = combinations.createCurrencyDenominationArr(currencyMap);
        double amount = 100.0; //Assuming the same total value as question 1
        int[] coinCount = new int[currencyArr.length];
        printCustomCurrencyHeader(currencyArr, currencyMap);
        combinations.findNumberOfCombinations(amount, 0, currencyArr, coinCount);
        System.out.println("Count " + combinations.getNumOfCombinations());
    }

    /**Miscellaneous Utility Functions**/
    private void printCoinsCombination(Integer[] coins, int[] coinCount) {
        for (int i = 0; i < coins.length; i++) {
            System.out.printf("%7s", coinCount[i]);
        }
        System.out.println();
    }

    private void printCoinsCombination(Double[] coins, int[] coinCount) {
        for (int i = 0; i < coins.length; i++) {
            System.out.printf("%7s", coinCount[i]);
        }
        System.out.println();
    }

    public int getNumOfCombinations() {
        return numOfCombinations;
    }

    public Map<String, Double> createCurrencyMap(String userInput) {
        String[] inputParams = userInput.split(",");
        Map<String, Double> map = new HashMap<String, Double>();
        if (inputParams.length % 2 != 0) {
            throw new IllegalArgumentException("User input is missing parameters");
        }
        for (int i = 0; i < inputParams.length; i += 2) {
            if (isCreatable(inputParams[i + 1])) {
                map.put(inputParams[i], toDouble(inputParams[i + 1]));
            } else throw new IllegalArgumentException("One or more money parameters are not a number");
        }
        return map;
    }

    public Double[] createCurrencyDenominationArr(final Map<String, Double> currencyMap) {
        final Double[] coins = new Double[currencyMap.size()];
        int i = 0;
        Iterator it = currencyMap.entrySet().iterator();

        while (it.hasNext()) {
            if (i < currencyMap.size()) {
                Map.Entry pair = (Map.Entry) it.next();
                coins[i] = (Double) pair.getValue();
                i++;
            }
        }
        return coins;
    }

    public static void printCustomCurrencyHeader(Double[] arr, Map<String, Double> map) {
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                if (entry.getValue() == arr[i]) {
                    System.out.printf("%7s",entry.getKey());
                }
            }
        }
        System.out.println();
    }
}
