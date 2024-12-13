# Study Vault

***

## Description

A console app that allows you to find and access resources such as books, papers and more.
The program uses OOP principles to make the code base more modular and easier to scale,
check [OOP](#oop) for more info.
The program implements SDG goal 4, check [SDG Implentation](#sdg-implementation) for more info.

## SDG Implementation

This program implements SDG goal 4 which is quality education.
The program lets user have access to a database of materials for education
The program also lets user to *favorite*/*save* materials they want into
their account for later access.
The program allows user to gain access to multitudes of resources to learn from.

## OOP

As of now the program only have class that models a book and papers such as research paper.
But thanks with OOP the program can grow easily thanks to it being modular.

- **Inheritance**

  The program uses inheritance to allow classes to inherit properties and methods from other classes.
  This allows for the program to be more modular and easier to scale. For example the `Material` class
  is inherited by the `Book` class and `Paper` class. This allows the `Book` and `Paper` class to have 
  the same properties and methods as the `Material` class.
- **Polymorphism**

  The program uses polymorphism to allow classes to have different implementations of the same method.
  An example of this is the `Material` class has a function that returns a string representation of the class.
  This allows the subclasses to override the method and return a different and more specific string representation.
- **Abstraction**

  The `Material` class is an abstract class that can be used by inheriting it.
  This allows to hide its functionalities and allows other object to use it.
  Being an abstract class the `Material` class only allows it to be inherited and not instantiated for type safety.
 
  The program also hides most of the functionalities to other classes making it easier to modify and scale the program.
  This allows for the developer to focus on the higher level functionalities rather than the low level functions the lives inside
  the Database class and such
- **Encapsulation**

  All the class Encapsulates all member variables allowing for safer way of accessing data on each class.
  Some functions are private for it is deemed unnecessary for it to be accessed outside its scope. Encapsulation allows
  developer to focus on the function that is allowed publicly rather than the complexity of the class

## Installation

### Pre-Requisite

- Gradle
- Java 21 JDK
- MySQL development server

### Building

Setting up mysql server. Please follow the installation process of mysql for your computer.

Enter mysql command line interface and run the following

```shell
source sql/init.sql
```

Run the following command to build the program

```shell
./gradlew app:build
```

**_NOTE_**: When using cmd use the `./gradlew.bat` file

### Running

Run the following command to run the program

```shell
./gradlew app:run -q --console=plain
```

## Usage

When selecting options please follow the characters within the `[]`

### Example

```
[1] Login
[2] Register
[x] Exit
Enter choice: 1 # To login
```
