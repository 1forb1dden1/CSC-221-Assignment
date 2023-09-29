import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class StudentTerminal {
    
    //For formatting.
    public static void printNewLine(){
        System.out.println("-------------------------------");
    }
    
    //Display the menu for the user.
    public static void printMenu(){
        printNewLine();
        System.out.println("1. Add New Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Get All Student Records");
        System.out.println("4. Search for Student Record");
        System.out.println("5. Edit Student Record");
        System.out.println("6. Export to CSV");
        System.out.println("7. Import from CSV");
        System.out.println("8. Exit the program.");
        System.out.print("Enter Your Choice: ");
    }
    
    //Adds a new Student to the list.
    public static void addNewStudent(ArrayList<Student> studentList, Scanner scanner){
        try{
            System.out.print("Enter Student Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();
        
            System.out.print("Enter Number of grades: ");
            int numGrades = scanner.nextInt();

            if(numGrades <= 0){
                throw new IllegalArgumentException("Number of grades must be greater than zero");
            }
            int[] marks = new int[numGrades];
            /*
            Random random = new Random();
            System.out.println(numGrades + " grades are generated for " + name + ".");
            for(int i = 0; i < numGrades; i++){
                marks[i] = random.nextInt(100);
            }
            */

            for (int i = 0; i < numGrades; i++) {
                System.out.print("Enter the grade " + (i + 1) + ": ");
                int current_grade = scanner.nextInt(); 
                if(current_grade > 100 && current_grade < 0){
                    System.out.print("Enter a valid grade between 0 and 100: ");
                    current_grade = scanner.nextInt();
                }
                marks[i] = current_grade;
            }
            
            
            Student newStudent = new Student(name, Student.getInstanceCount(), marks);
            studentList.add(newStudent);
            System.out.println("Student added successfully.");
            //user inputs non integer input, wrong typing.
        } catch (InputMismatchException e){
            System.err.println("Input error: Invalid Input Format");
            //User input wrong amount of grades
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    //Delete Student from the List.
    public static void deleteStudent(ArrayList<Student> studentList, Scanner scanner) {
        boolean deleted = false;
        Student deletedStudent = null;
        System.out.print("Enter the Student's Roll number that you would like to delete: ");
        int rollNumber;

        try {
            rollNumber = scanner.nextInt();
        } catch (InputMismatchException e){
            System.err.println("Invalid input format. Please enter a valid roll number (an integer)");
            scanner.nextLine();
            return;
        }

        //Deletes the student if it is found in the array.
        for (int i = 0; i < studentList.size() && deleted == false; i++) {
            if ((studentList.get(i).getRollNumber() == rollNumber) && deleted == false) {
                deletedStudent = studentList.remove(i);
                deleted = true;
            }
        }

        //Sorts the list of student roll numbers in ascending order with an increment of 1.
        for(int i = 0; i <= studentList.size()-1; i++){
            studentList.get(i).setRollNumber(i+1);
        }
        
        //Tell the user if the user has been deleted or not.
        if (deleted) {
            System.out.println("The Student has been deleted and rollnumber's have been shifted.");
            System.out.println("Here was the student's information:");
            deletedStudent.DisplayInfo();
        } else {
            System.out.println("The student does not exist.");
        }
    }
    
    //Display all of the Student's information from the List. 
    public static void printAllStudentInfo(ArrayList<Student> studentList){
        for(int i = 0; i < studentList.size(); i++){
            try{
                studentList.get(i).DisplayInfo();
                printNewLine();
            } catch (Exception e){
                System.err.println("Error while displaying student information:");
                e.printStackTrace();
            }
        }
        System.out.println("Finished Printing all student data.");
    }
    
    //Display the information of a single student from the list.
    public static void printStudentInfo(ArrayList<Student> studentList, Scanner scanner){
        boolean exist = false;
        System.out.println("Enter the student's RollNumber: ");
        int StudentRollNumber = scanner.nextInt();
        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getRollNumber() == StudentRollNumber){
                try{
                    studentList.get(i).DisplayInfo();
                    exist = true;
                    break;
                } catch (Exception e){
                    System.err.println("Error while displaying the student's informtion:");
                    e.printStackTrace();
                }
            }
        }
        if(exist == false){
            System.out.println("The Student does not exist.");
        }
    }
    
    //Display the information of a single Student from the list. 
    public static void printStudentInfo(ArrayList<Student> studentList, int StudentRollNumber){
        
        boolean exist = false;
        
        //Search for the student until it is either found or not in the list.
        for(int i = 0; i < studentList.size(); i++){
            //student has been found
            if(studentList.get(i).getRollNumber() == StudentRollNumber){
                studentList.get(i).DisplayInfo();
                exist = true;
                break;
            }
        }

        //Tells the user that the student does not exist.
        if(exist == false){
            System.out.println("The Student does not exist.");
        }

        printNewLine();
    }
    
    //Display menu for editing student records. 
    public static void editStudentMenu(){
        System.out.println("1. Print Student Information");
        System.out.println("2. Edit Name.");
        System.out.println("3. Edit Student marks");
        System.out.println("4. Finish Editing Student Records.");
        System.out.print("Enter Your Choice: ");
    }
    
    //Changes the Student's grades.
    public static void editGrades(Student student, Scanner scanner) {
        int[] grades = student.getMarks();
        System.out.println("All grades for the student: ");

        //display grades to the users.
        for (int j = 0; j < grades.length; j++) {
            System.out.println((j + 1) + ". " + grades[j]);
        }
        
        //total grades.
        int numGrades = grades.length;
        
        //exit option on the display.
        System.out.println((numGrades + 1) + ". Exit the program");

        int choice = 0;
        System.out.print("Enter Your Choice: ");
        choice = scanner.nextInt();
        
        //Go back to the previous page if condition is true.
        if(choice == numGrades +1){
            printNewLine();

        //Change the student's grade.
        } else {
            
            //Allows the user to choose a new grade to replace the old grade with.
            System.out.print("Enter a new grade: ");
            int newGrade = scanner.nextInt();
            
            //Confirms if the new grade is valid. 
            while (newGrade > 100 || newGrade < 0) {
                System.out.print("Enter a valid grade between 0 and 100: ");
                newGrade = scanner.nextInt();
            }
            
            //Changes the old grade to the new grade. 
            grades[choice-1] = newGrade;
            student.setMarks(grades);
        }
    }

    //Edit Student Records. 
    public static void editStudentInfo(ArrayList<Student> studentList, Scanner scanner){
       boolean exists = false;
       System.out.print("Enter the Student's roll Number: ");
       int rollNumber = scanner.nextInt();
       int studentIdx = 0;
       
       //find the index of the student from the array
       for(int i = 0; i < studentList.size() && exists == false; i++){
           if(rollNumber == studentList.get(i).getRollNumber()){
              exists = true;
              studentIdx = i;
              System.out.println("Student has been found.");
           }
       }
       
       //Open Menu for the user if the student Exists. 
       if(exists == true){
         int choice = 0;
         while(choice != 4){
            try{
                editStudentMenu();
                choice = scanner.nextInt();
                printNewLine();
                switch(choice){
                    case 1:
                        printStudentInfo(studentList, rollNumber);
                        break;
                    case 2:
                        System.out.print("Enter New Name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        studentList.get(studentIdx).setName(name);
                        System.out.println("The student's name is now: " + studentList.get(studentIdx).getName());
                        break;
                    case 3:
                        editGrades(studentList.get(studentIdx), scanner);
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid choice, Please enter a valid option.");
                }
            } catch (InputMismatchException e){
                    System.err.println("Invalid input format. Please enter a value choice.");
                    scanner.nextLine();
                } 
            }
        }
        if(exists == false){
          System.out.println("The student does not exist.");
        }
    }
    public static void exportCSV(ArrayList<Student> studentList){
        
        //Initialize the data we will export to the CSV. 
        String dataToExport = "Name, RollNumber, Marks\n";

        //Build the dataToExport string by filling it with data from the list of students.
        for(int i = 0; i < studentList.size(); i++){
            dataToExport += (String.valueOf(studentList.get(i).getName()) + ", ");
            dataToExport += (String.valueOf(studentList.get(i).getRollNumber() + ", "));
            int marks[] = studentList.get(i).getMarks(); 
            for(int j = 0; j < marks.length; j++){
                dataToExport += (marks[j] + " ");
            }
            dataToExport += "\n";
        }

        //Output File Name.
        String fileName = "StudentRecords.txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(dataToExport);

            bufferedWriter.close();

            System.out.println("Data has been exported to " + fileWriter);
        } catch (IOException e){
            e.printStackTrace();
        }
        //System.out.println(dataToExport);
    }
    public static void importCSV(ArrayList<Student> studentList, String filePath, Scanner scanner){
        try{
            File file = new File(filePath);
            scanner = new Scanner(file);

            //ignore the first line
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            
            //parse until there is no more lines. 
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data = line.split(",");

                //If we have three parameters or commas splitting the data.
                if(data.length == 3){

                    //The first element contains the name. 
                    String name = data[0].trim();
                    
                    //The second element contains the rollNumber.
                    int rollNumber = Integer.parseInt(data[1].trim());

                    //The third element contains the marks data.
                    String[] marksData = data[2].trim().split(" ");
                    int[] marks = new int[marksData.length];

                    for(int i = 0; i < marksData.length; i++){
                        marks[i] = Integer.parseInt(marksData[i]);
                    }

                    Student student = new Student(name, rollNumber, marks);
                    studentList.add(student);
                } else {
                    System.err.println("The CSV is formatted incorrectly: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.err.println("File not found: " + filePath);
        } catch (NumberFormatException e){
            System.err.println("Error parsing data in CSV. ");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        while(true){
            printMenu();
            int choice = scanner.nextInt();
            printNewLine();

            switch(choice) {
                case 1:
                    addNewStudent(studentList, scanner);
                    break;
                case 2:
                    deleteStudent(studentList, scanner);
                    break;
                case 3: 
                    printAllStudentInfo(studentList);
                    break;
                case 4: 
                    printStudentInfo(studentList, scanner);
                    break;
                case 5: 
                    editStudentInfo(studentList, scanner);
                    break;
                case 6: 
                    exportCSV(studentList);
                    break;
                case 7: 
                    importCSV(studentList, "StudentRecords.txt", scanner);
                    break;
                case 8:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default: 
                    System.out.println("Invalid choice, Please enter a valid option.");
            }
        }
    }
}
