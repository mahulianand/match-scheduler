# match-scheduler

Basic REST service that generates the round robin schedule for the tournament that conforms to the following constraints
- Accept N number of teams and their location
- Each team must play against every other team once home and away
- Maximum 2 matches per day are allowed
- No team should play on consecutive days

Requirement
- Java 1.8
- Maven

How to compile, package and run tests?
- mvn compile
  To compile the java source files
  
- mvn package
  To create jar, war and execute tests
