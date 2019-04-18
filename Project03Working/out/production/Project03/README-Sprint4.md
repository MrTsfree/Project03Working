CSCI 234 - Software Engineering
Spring 2019
Project - Sprint 4

I ABSOLUTELY EXPECT THAT EACH TEAM MEMBER WRITES CLOSE TO AN EQUAL NUMBER OF CODE LINES.
I WANT ALL SOURCE CODE DOCUMENTED SO THAT I CAN IDENTIFY THE PERSON OR PERSONS THAT 
WROTE EACH PART OF THE CODE. IF YOU GET HELP WITH YOUR CODE, I EXPECT THE DOCUMENTATION TO DETAIL
THE NATURE OF THE HELP YOU WERE GIVEN AND FROM WHOM THE HELP WAS GIVEN.

Due: Monday, April 22, at 11:00am
Be prepared to demo your project in class on the due date.

Sprint 4 Goal: Complete all backlog items. Change all time to regular time. Refactor code to use Observer design pattern.

Sprint 4 :
-      Finish all current backlog items

-      Change all time units to hours, minutes, seconds. 
       o      Assume truck with travel 30 miles an hour. 
       o      Assume that locations are 0.03 miles apart.
       o      The values for these assumptions should be easy to change.

-      refactor code so that you apply the Observer design pattern to your solution. 
Your GUI display is one observer. If you have not already done so, create another observer 
that will print the truck location, time, and "Order filled" (if location is for a current order)
to standard output as it changes. 
The subject of the observers is the truck location, time, and order information.  Your code should be 
able to easily handle the creation of more than one truck. 
