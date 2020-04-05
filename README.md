# OODP-CA
 project programming
 
Singleton
I chose to apply the Singleton design pattern in the DbConnect class Singleton to specifies that only one instance of the class can exist, and it will be used by the entire application. Thus, there is only one central access point to that instance of the class.
By using Singleton I have more control over access to the properties and methods of a class, and this pattern also reduce the consumption of unnecessary memory by using several unnecessary instances of a class.

Builder
I decided to use the builder pattern to get a more modular desing to my code and bring this modular approach to solve the problems. Using builder I can define methods to assign the values to the attributes that, using a different construction design, I might have to pass them all in the contructor.By using the builder class I am free to call the methods indepently and deifine values to the methods that will not be called. So if I have many attributes in a class and that is the case in my country class, then it is a good idea to apply the builder pattern concept.


Dao (Data Access Object Patern)
I chose to utilize this pattern to keep the encapsulation and the single obligations safe. So to do not have one class responsible to handle all tasks. I apply this desing pattern to share resposibilities among the classes. In this way, each class knows simply enough. So if something happen to turn out badly I would realize where I would need go to fix the issue, so I abstain from spreading SQLs all over the place, making upkeep and development of a framework a nightmare.
