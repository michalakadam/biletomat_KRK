# biletomat_KRK
Ticket machine software of a public transport company in Cracow, Poland. 
Sells tickets, gives change and informs when it cannot do so.

How to run the code:
This project was developed using Intellij IDEa and was procided with Maven structure so it's most convenient to use it as well to open this project. In Intellij IDEa go to File -> New -> Project from Version Control -> Git and paste URL of this repository.

This code provides user interface and following functionalities:
- machine can sell official tickets for public transportation listed here: 
http://www.mpk.krakow.pl/pl/bilety2/rodzaje-biletow/
- tickets can be bought using coins in Polish złotych (zł)
- as many tickets as machine can provide of different types can be bought with one payment
- change is calculated and given to the customer from machine coins repository
- if there is not enough coins in the machine repository, customer is asked to pay with exact amount
- if there is not enough paper to print the amount of tickets customer ordered he/she is informed about the amount of tickets they  can buy
- if there is no paper left ticket machine is out of order

Project developed to master object oriented design and practice unit testing with JUnit. 
Recently I've read densely packed book about unit testing "Testowanie aplikacji Java przy użyciu JUnit" by R. Sokół 
and I want to use those concepts in practice. Maven was used to give code proper structure. Project developed in Intellij Idea.
