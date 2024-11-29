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

  In the package `FinalProject.Model.Data`, certain classes are designed to extend the functionalities of the Material
  class in order to minimize redundancy and improve code efficiency. Additionally, there are specific classes, such as the
  LoginView class, which serve as a foundation for the RegisterView class, thereby allowing the latter to inherit
  properties and methods while providing its own unique features. This structure promotes reusability and maintains a
  cleaner, more organized codebase

- **Polymorphism**

  Classes such as Book, Paper, and others that inherit from the Material class
  override the `toString()`, `printData()`, and `updateData(Scanner in)` methods 
  to suit their specific functionalities. These functions can adapt and change based on the required operations.
  Polymorphism allows the developer to use the same function but get a different results
 
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
