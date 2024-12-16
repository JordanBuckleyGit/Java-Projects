# Car Program in Java

This repository contains a Java program for managing vehicles, specifically cars and buses. The program consists of four classes: `Main`, `Vehicle`, `Car`, and `Bus`.

### Classes:

1. **Main**: This class contains the main method and serves as the entry point of the program. It initializes instances of `Car` and `Bus`, and demonstrates their functionality.

2. **Vehicle**: The base class for all vehicles. It contains common attributes and methods shared by both cars and buses, such as make, model, year, and methods for setting and retrieving these attributes.

3. **Car**: A subclass of `Vehicle` representing a car. It inherits attributes and methods from the `Vehicle` class and adds specific functionalities related to cars, such as calculating fuel efficiency, checking fuel level, and displaying car-specific information.

4. **Bus**: Another subclass of `Vehicle`, representing a bus. Similar to the `Car` class, it inherits from `Vehicle` and implements functionalities specific to buses, such as calculating passenger capacity, checking available seats, and displaying bus-related information.

### How to Use:

2. Compile the Java files using a Java compiler:
   ```
   javac Main.java
   ```

3. Run the program:
   ```
   java Main
   
   ```

4. Follow the on-screen prompts to interact with the program, which will demonstrate the functionalities of cars and buses.

### Contribution:

Contributions are welcome! Feel free to fork this repository, make changes, and submit pull requests. Bug fixes, feature enhancements, and code refactoring are all appreciated.
