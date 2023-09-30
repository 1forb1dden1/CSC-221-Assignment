public class Student {

    // class Attributes
    private String Name; 
    private int RollNumber;
    private int[] Marks;
    
    // Constructor for initialization of the attributes
    public Student(String name, int rollnumber, int[] marks){
        this.Name = name; 
        this.RollNumber = rollnumber;
        this.Marks = marks;
    }

    // Method to caluclate the average marks of the student
    public double CalculateAverageMarks() {
        double sum = 0;
        for (int mark : Marks) {
            sum += mark;
        }
        
        // Calculate the unrounded average
        double unroundedAverage = sum / Marks.length;
    
        // Round the average to the nearest tenth place
        double roundedAverage = Math.round(unroundedAverage * 10.0) / 10.0;
    
        return roundedAverage;
    }
    
    // Method to print out all of the information for the student
    public void DisplayInfo(){
        System.out.println("RollNumber: " + RollNumber);
        System.out.println("Name: " + Name);
        System.out.print("Marks: ");
        for(int i = 0; i < Marks.length; i++){
            //format the output so the last mark creates a newline
            if(i!=Marks.length-1){
                System.out.print(Marks[i] + " ");
            } else {
                System.out.println(Marks[i]);
            }
        }
        System.out.println("Average Marks: " + CalculateAverageMarks());
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public int getRollNumber(){
        return RollNumber;
    }

    public void setRollNumber(int rollNumber){
        this.RollNumber = rollNumber;
    }

    public int[] getMarks(){
        return Marks; 
    }

    public void setMarks(int[] marks){
        this.Marks = marks; 
    }
}
