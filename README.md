# Priority Message Retriever
A custom Java logging package which retrieves logs according to their priority.
Priority is determined by log level (e.g. _ERROR_ > is higher priority than _WARN_ > is higher priority than _INFO_),
followed by their earliest insertion time. There must be a separation of concerns between the classes that write logs (named `Logger`)
and the classes that retrieve the logs (named `LogReader`).

This implementation is thread-safe, and these logs are kept in memory (not stored in files).

## How to run tests
The tests are under the _test.org.mnwani_ package (not included in the JAR file),
and are print statements that are executed by running each test classes _main_ method

## How to import the JAR file to your project
If you are using IntelliJ IDEA, the following steps might be the easiest:
1. Create a root folder in your project named _lib_
2. Copy and paste _michael-nwani.jar_ into that folder
3. Right click on the file under the folder, and select the "Add as Library" option.
4. You should now be able to import classes from the JAR.
