/**
 * Programmer: Rayaan Khan
 * Date: 2020-10-02
 * Project: JavaUnit3.java
 * Program Name: JavaUnit3
 */

package javaunit3;
import java.util.Scanner; // scanner class
import static java.lang.System.out; // shorten output statement

public class JavaUnit3 {
    
    // Public - scanners can be accessed in any method
    static public Scanner scanN = new Scanner(System.in);
    static public Scanner scanS = new Scanner(System.in);
    
    public static void main(String[] args) {

        int[] studentNumber = new int[8]; // stores student #
        int[] studentGrade = new int[8]; // stores grade of student

        char[][] StudentAns = 
        {
            {'A','B','A','C','C','D','E','E','A','D'}, // student 0
            {'D','B','A','B','C','A','E','E','A','D'}, // student 1
            {'E','D','D','A','C','B','E','B','A','C'}, // student 2
            {'C','B','A','E','D','C','E','E','A','A'}, // student 3
            {'A','B','D','C','C','D','B','A','A','C'}, // student 4
            {'B','B','E','C','C','D','E','E','B','B'}, // student 5
            {'B','B','A','C','C','D','E','E','A','D'}, // student 6
            {'D','B','D','C','C','D','A','E','A','D'}, // student 7
        };
        char[] answerKey = {'D','B','D','C','C','D','A','E','A','D'}; // ans key
        
        calcMark(StudentAns, answerKey, studentNumber, studentGrade);
        sortMarks(studentNumber, studentGrade);  
        searchMarks(studentNumber, studentGrade);
    }
    
    /**
     * Method Name: calcMark
     * Description: Method will compare each students’ test with the answer 
     * key, keeping track of the number of questions the student got correct          
     * @param answers
     * @param answerKey
     * @param studentNum
     * @param studentGrade 
     */
    public static void calcMark (char[][]answers, char[] answerKey, 
            int[] studentNum, int[]studentGrade) {
        
        int i,j; // i repersents row, j repersents column
        int correct; // questions that are correct                                                          
        
        out.println("UNORGINIZED MARKS");        
        /*
        The for loop system below scans the array answers, comparing each value
        to the answer key and stores the value into the array
        */
        for (i = 0; i<answers.length; i++) {
            correct = 0; // resets to 0 for each student
            for (j = 0; j<answers[i].length; j++) {
                if (answers[i][j] == answerKey[j]) 
                    correct++; // adds 1 if answer matches answer key
            }
            studentNum[i] = i; // stores student #                                                  
            studentGrade[i] = correct; // stores # correct
            out.println("student: #" + i + "'s correct count is: " + correct);
        }
    }
    
    /**
     * Method Name: sortMarks
     * Description: Sorts the students marks from the lowest to highest
     * @param student
     * @param grade
     */
    public static void sortMarks (int[] student, int[]grade) {

        int minValueS; // lowest value of student #
        int minIndexS; // index of the value (student)
        int minValueG; // lowest grade in the array
        int minIndexG; // index of the lowest grade
        
        for (int i=0; i<8-1; i++)  {
            minValueS = student[i]; // keep track of student with lowest grade
            minIndexS = i; // keep track of its index

            minValueG = grade[i]; // keep track of lowest grade
            minIndexG = i; // keep track of its index

            for (int j=i+1; j<8; j++) {
                if (grade[j] < minValueG) {
                    minValueS = student[j]; // sets smallest value equal to var
                    minIndexS = j; // smallest value's correspoinding index
                    
                    minValueG = grade[j]; // for grades
                    minIndexG = j; // for grades
                }
            }
            
            student[minIndexS] = student[i]; // bring lowest value to beginning
            student[i] = minValueS; // switch index 'i' with index of lowest val

            grade[minIndexG] = grade[i]; // for grades
            grade[i] = minValueG; // for grades
        }        
         
        out.println("\nORGINIZED MARKS");
        for (int test=0; test<8; test++) { // print marks from low to high
            out.print("student: #" + student[test] 
                    + "'s correct count is grade: " + grade[test]  + "\n");
        }        
    }
    
    /**
     * Method Name: searchMarks
     * Description: asks user what mark they want to search for and finds all
     * students who have that mark.
     * @param student
     * @param grade 
     */
    public static void searchMarks(int[] student, int[] grade) {
                
        String answer; // asks if they want to search for grade
        boolean errorTrap1; // if user enters wrong input
        boolean errorTrap2; // if user enters wrong input     
        int target; // the mark they want to search
        
        do { // for error trapping   
            errorTrap1 = false; // set trap to false to avoid infinite loop
            out.print("\nWould you like to search for a specfic "
                    + "grade? (yes/no): ");
            answer =  scanS.nextLine(); // scan line for input
        
            switch(answer.toLowerCase()) { // process input
                case "yes":
                    break; // move to loop

                case "no": // user wants to end program
                    out.println("Closing Program One Moment...");
                    out.println("Powered by Grape Inc.");
                    System.exit(0); // exit program

                default: // if wrong input entered
                    out.println("\nWrong input try again");
                    errorTrap1 = true; // makes code repeat
                    break;
            }
        } while (errorTrap1 == true);
        
        do {  // loop for error trapping
            
            errorTrap2 = false;
            out.print("\nwhat mark would you like to search for? ");
            target = scanN.nextInt();
            
            if (target>10 || target<0) { // error trapping
                out.print("\nthe maximum score possible is a 10 please enter "
                        + "\nsomething between 0 and 10\n");
                errorTrap2 = true; // makes code repeat
            }
            
        } while (errorTrap2 == true);
        
        // Binary Search Section       
        int firstIndex = 0; // The first index value
        int middleIndex; // the midpoint of the search
        int lastIndex = grade.length - 1; // The last index value
        boolean valueFound = false; // if binary search finds the right num
        boolean gradeExists = false; // for checking if target exists in array
        
        while (firstIndex <= lastIndex && !valueFound) {            
            
            middleIndex = (firstIndex+lastIndex) / 2; // find midpoint
            if (grade[middleIndex] == target) {   
                /*
                The for loop goes through the array checking for any matches to
                the target value and proceeds to print it out afterwards
                */
                for (int i = 0; i < grade.length; i++) {                    
                    if (grade[i] == target) {
                        out.println();
                        out.println("student #" + student[i] 
                                + " has a correct count of " + target); 
                    }
                }                
                out.println("\nENDING PROGRAM");
                System.exit(0); // exit program
            } 
            
            else if (grade[middleIndex] > target)
                lastIndex = middleIndex - 1; //  move the boundry of our search
           
            else if (grade[middleIndex] < target)
                firstIndex = middleIndex + 1; // move the boundry of our search
        } // binary search ends
                       
        if (gradeExists == false)
            out.println("\nNo student received a " + target + "!");       
    } // searchMark() ending
} // class ending

