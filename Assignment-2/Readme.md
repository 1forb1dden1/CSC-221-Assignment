# Student Record Management System

The Student Record Management System is a Java program that enables efficient management of student records. You can perform various operations such as adding new students, deleting students, viewing student information, editing student records, and exporting/importing data to/from CSV files.

**Warning**: Exporting will override existing data.

**Start The Program**: This program will start up without automatically importing. To quickly start up, utilize function 8 from the main menu to import all student records from the CSV. 

## Main Menu

The main menu in the driver class `StudentTerminal.java` offers several options for managing student records:

1. **Add New Student**: Add a new student to the system, providing their name and grades.
2. **Delete Student**: Delete a student by specifying their roll number.
3. **Delete All Student**: Delete every single single student record.
4. **Print all Student Records**: View information for all students in the system.
5. **Print A Student's Record**: Search for a specific student's information by roll number.
6. **Edit Student Record**: Edit a student's information, including their name and grades.
7. **Sort all roll numbers**: Sort the roll number of every student from the record in ascending order. 
8. **Export to CSV**: Export student records to a CSV file named "StudentRecords.txt" in the project folder.
9. **Import from CSV**: Import student records from a CSV file (format: Name, RollNumber, Marks) named "StudentRecords.txt" in the project folder.
10. **Exit the program**: Close the program.

## Usage

1. **Adding a New Student:**  
   To add a new student, select option 1 from the main menu. Follow the prompts to input the student's name, grades, and any additional information required by the new functionality.

2. **Deleting a Student:**  
   To delete a student, choose option 2 from the main menu and enter the student's roll number.

3. **Deleting All Students:**  
   Option 3 allows you to delete all student records from the system.

4. **Viewing Student Records:**  
   Option 4 allows you to view information for all students in the system.

5. **Searching for a Student:**  
   Use option 5 to search for a specific student's information by entering their roll number.

6. **Editing Student Records:**  
   Option 6 enables you to edit a student's information, including their name, grades, and any additional fields introduced by the new functionality.

7. **Sorting Roll Numbers:**  
   Option 7 enables you to sort all student roll numbers in ascending order.

8. **Exporting to CSV:**  
   You can export student records to a CSV file by selecting option 8. The exported file will be named "StudentRecords.txt" and will reside in the project folder.

9. **Importing from CSV:**  
   Option 9 permits you to import student records from a CSV file. Ensure that the CSV file adheres to the format: Name, RollNumber, Marks, and includes any new fields introduced by the new functionality. The file should be named "StudentRecords.txt" and located within the project folder.

10. **Exiting the Program:**  
    Select option 10 to exit the program.
