#### DSS Assignment

###Project Information

This project is a maven project, therefore, before it's run, all the dependencies need to be imported. 

I am using maven because of the following reasons:  

* I'm using the apache commons library and is imported as a dependency.
* I'm using JUnit 4 to perform more test cases other than the ones asked in the questions. 

Once the project is ready to work with: 
 
You can run the main method that is part of the Combinations class.

The main method runs two functions: 

*questionOne()*

*questionTwo()*

You can also run the other tests I have created in the *CombinationsTest* class with JUnit. 

---

#### Code Walk-through

There are two methods with different signatures. 

* `findNumberOfCombinations(int remainingAmount, int index, Integer[] coins, int[] coinCount)` 

which is used for question one. 
I did it this way because we have an array of integers that define each coin denomination and there aren't any extra 
parameters or User info. It's straight forward. Passing an array of Integers `coins` and a total amount `amount`.

* `findNumberOfCombinations(double remainingAmount, int index, Double[] coins, int[] coinCount)`

which is used for question two and is a more generic function because it takes an array of Double[] coin denominations. 
I do this because it will allow the user who inputs the string enter any number including decimals. If an integer such 
as 25 is passed, then it's casted to a double 25.0. 
 
There are some ways I can improve the above, but for simplicity and for the matter of submitting the assignment I implemented it
as described. 

I added some inline comments to the code to better explain my logic in the algorithm.

For question two, I am taking the user input, converting it to a map, and then making a denominations array with all the 
numeric values that are in the map. 

I decided to create a map instead of just an array in order to keep track of the coin name and denomination that a user 
passes. 

Towards the end, I use this same map to print the header of the table based on the coin names that are stored in the map. 

I also have a `numOfCombinations` counter as a class variable in order to keep track of the number of combinations. 


If there are any misunderstandings or if you have problems running the code, please feel free to email me at 

*crisso2292@gmail.com*


  