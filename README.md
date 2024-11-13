# Study Vault
***
A console app that allows you to find and access resources such as books, papers, and more. 
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
```shell
[1] Login
[2] Register
[x] Exit
Enter choice:
```
