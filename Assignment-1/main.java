import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void printNewLine(){
        System.out.println("-------------------------------");
    }
    public static void Task1(){
        int number = 15;
        double number2 = 8.32;
        String name = "Kevin";
        System.out.println("Task 1: Variables and Types(10 points)");
        printNewLine();
        System.out.println("Number: " + number + "\n" + "Double: " + number2 + "\n"+ "Name: " + name);
        printNewLine();
    }
    public static void Task2(){
        System.out.println("Task 2: Control Flow and Functions(15 points)");
        printNewLine();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Your Age: ");
        int Age = scan.nextInt();
        if(Age>= 18){
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a minor.");
        }
        printNewLine();
    }
    public static void Task3(){
        System.out.println("Task 3: Conditional Statements and Loops (20 points).");
        printNewLine();
        System.out.print("Loop from 1 to 20: ");
        for(int i = 1; i <= 20; i++){
            System.out.print(i + " ");
        }
        System.out.print("\nLoop odd number between 1 and 50: ");
        for(int j = 1; j <=50; j++){
            if(j % 2 == 1){
                System.out.print(j + " ");
            }
        }
        System.out.print("\n");
        printNewLine();
    }
    public static void Task4(int height, int width, boolean print, int example){
        if(print == true){
            System.out.println("Task 4: Defining and Calling Functions (15 points)");
            printNewLine();
        }
        System.out.println("Example Number: " + example);
        System.out.println("The height is " + height);
        System.out.println("The width is: " + width);
        int Area = 0;
        Area = height*width;
        System.out.println("The Area is: " + Area);
        printNewLine();
    }
    public static int Task5(int n){
        System.out.println("Task 5: Parameter Passing and return Values (20 points)");
        printNewLine();
        int Factorial = 1;
        for(int i = 1; i <= n; i++){
            Factorial = Factorial*i;
        }
        System.out.println("The factorial of " + n + " is: " + Factorial);
        printNewLine();
        return Factorial;
    }
    public static void handleOperation(double num1, double num2, char operator){
        if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
            System.out.println("The operator is invalid.");
        } else if(operator == '+'){
            double result = num1+num2;
            System.out.println(num1 + " + " + num2 + " = " + result);
        } else if (operator =='-'){
            double result = num1-num2;
            System.out.println(num1 + " - " + num2 + " = " + result);
        } else if(operator == '/'){
            if(num2 != 0){
                double result = num1/num2;
                System.out.println(num1 + " / " + num2 + " = " + result);
            } else{
                System.out.println(num1 + " / " + num2 + " = undefined");
            }
        }
        else {
            double result = num1*num2;
            System.out.println(num1 + " * " + num2 + " = " + result);
        }
    }
    public static void Bonus_Task(){
        System.out.println("Bonus Task (10 points)");
        printNewLine();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number 1: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter Number 2: ");
        double num2 = scanner.nextDouble();
        Set<Character> Set = new HashSet<Character>();
        Set.add('+'); Set.add('-'); Set.add('*'); Set.add('/');
        char operation = 'x';
        System.out.print("Enter the mathematical operator: ");
        operation = scanner.next().charAt(0);
        while(Set.contains(operation) == false){
            System.out.print("Enter a VALID mathematical operator: ");
            operation = scanner.next().charAt(0);
        }
        handleOperation(num1, num2, operation);
        printNewLine();
    }
    public static void main(String[] args){
        Task1();
        Task2();
        Task3();
        Task4(3,4, true, 1);
        Task4(12,6, false, 2);
        Task4(7,8, false, 3);
        Task5(6);
        Bonus_Task();
    }
}