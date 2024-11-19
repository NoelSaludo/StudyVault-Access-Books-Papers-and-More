# Study Vault
***
## Description
A console app that allows you to find and access resources such as books and papers.
The program uses OOP principles to make the code base more modular and easier to scale,
check [OOP](#oop) for more info.
The program implements SDG goal 4, check [SDG Implentation](#sdg-implementation) for more info.
## SDG Implementation
This program implements SDG goal 4 which is quality education. 
The program lets user have access to a database of materials for education
The program also lets user to *favorite*/*save* materials they want into 
their account for later access.
The program allows 
## OOP
As of now the program only have class that models a book and papers such as research paper. 
But thanks with OOP the program can grow easily thanks to it being modular.
- **Inheritance**
  
    Some classes in the package `FinalProject.Model.Data` extends from the Material class
    to reduce redundancy.
- **Polymorphism**

    Classes that inherits overrides the `toString()` method to suite the objects String conversion. 
    The classes will have different output when being printed thanks to this principle.
- **Abstraction**

    The `Material` class is an abstract class that can be used by inheriting it. 
    This allows to hide its functionalities and allows other object to use it.
    Being an abstract class the `Material` class only allows it to be inherited and not instantiated for type safety.
- **Encapsulation**

    The class Encapsulates all member variables allowing for safer way of accessing data on each class.
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
