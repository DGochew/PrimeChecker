# Prime checker REST API
Create a Java (8 or greater) REST microservice which allows us to:
Find out if a given number is a prime number
Find out the next prime after a given number (i.e. Input: N, output: P where P >=N and P is prime)

Java version 8
Spring boot version 2.3.1
Apache Maven version 3.6.1


Create docker file and run the app
`mvn clean install`
`docker build --tag primechecker .`
`docker run -p 3333:5580 primechecker`

Complexity of the algorithms
`O(sqrt(n))` - complexity is prime algorithm
`O(x*sqrt(n))` - complexity next prime algorithm, where x is the count of times we check if the number is prime

Up to 430mb used heap memory, measured by Intellij

Program limited on both REST APIs up to 50 bit numbers, because it starts to work slow.
It works successfully with bigger numbers, but takes a lot of time to calculate.