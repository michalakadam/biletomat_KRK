# biletomat_KRK
Ticket machine software of a public transport company in Cracow, Poland. 
Sells tickets, gives change and informs when it cannot do so.

This code provides user interface and following functionalities:
- machine can sell official tickets for public transportation listed here: 
http://www.mpk.krakow.pl/pl/bilety2/rodzaje-biletow/
- tickets can be bought using coins in Polish złotych (zł)
- up to 10 tickets of different types can be bought with one payment
- change is calculated and given to the customer from machine coins repository
- if there is not enough coins in the machine repository, customer is asked to pay with exact amount
- if there is paper for less than 10 tickets customer is informed about the amount of tickets they can buy
- if there is no paper left ticket machine is out of order

Project developed to master object oriented design and practice unit testing with JUnit. 
Recently I've read densely packed book about unit testing "Testowanie aplikacji Java przy użyciu JUnit" by R. Sokół 
and I want to use those concepts in practice. Maven was used to give code proper structure. Project developed in Intellij Idea.
