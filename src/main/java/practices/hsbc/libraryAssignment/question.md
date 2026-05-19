# Question
Stephen runs a small library that has N number of students patrons. Each student member has a unique studentID. The library has a certain number of books on M different subjects. The teacher has given each student an individual assignment for which they will need to consult several different books. Prior to the assignment, the library had already issued some books to the students. The students may still take additional books from the library to complete their respective assignments. After completing their assignments, each student returns the books that they have borrowed. Only when a book has been returned, another student can borrow that book. When assigning books, Stephen begins with the student with the smallest studentID and then proceeds with the IDs in increasing order. When he reaches the student with the largest studentID, he then goes back to the student with the smallest studentID who has not yet borrowed the book. Then the process continues in this way. Stephen wishes to find the sequence of studentIDs most optimal for the students to complete their assignment.

Write an algorithm to help Stephen find the sequence of studentIDs most optimal for the students to complete their assignments. If it is not possible for all the students to complete their assignments, output a list of length of 1 with content -1.

# Input
The first line of the input consists of an integer - booksNum, representing the number of different subjects(M).
The second line consists of M space-seperated integers - avail[0],avail[1],..., avail[M-1],representing the books in the library that have not been issued to any student.
The third line consists of two space-seperated integers - studentNum and reqBooks, representing the number of students(N) and number of different books required by each student, respectively.
The next N lines consist of M space-seperated integers representing the books required by the students to complete their assignments.


# Example
Input:
3
2 2 3
3 3
2 4 0
0 0 1
0 1 3
3 3
3 5 4
1 3 4
2 3 5

Output:
2 0 1

Explanation:
The available books = [2 2 3]
studentID   Issued Books    Required Books  Needs
0           2 4 0           3 5 4           1 1 4
1           0 0 1           1 3 4           1 3 3
2           0 1 3           2 3 5           2 2 2

The needs of the student with studentId 2 can be met directly as they need only 2,2,2 different books and the available books are 2,2,3. So after the completion of their assignments, books returned, [2,2,3] + [0,1,3] = [2 3 6]