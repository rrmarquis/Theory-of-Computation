/**
 * @author Roger_Marquis
 * Project 1: DFA Acceptance
 * Instructor: Gregory Gelfond
 * Class: CSCI 3660 - Theory of Computation
 */
import java.util.*;
import java.io.*;

class DFACheck
{
    public static void main(String args[]){
        //args[0] = input.txt declared in the command line:
        try{
            //file input (fin) stream definition
            FileInputStream fin = new FileInputStream(args[0]);
            //scanner definition (fin file for loop)
            Scanner sc = new Scanner(fin);

            int [][] dfa={
                            {1,2}, //state 0(A). if 0 -> state = 1; if 1 -> state = 2 
                            {2,4}, //state 1(B). if 0 -> state = 2; if 1 -> state = 4
                            {3,2}, //state 2(C). if 0 -> state = 3; if 1 -> state = 2
                            {1,6}, //state 3(D). if 0 -> state = 1; if 1 -> state = 6
                            {5,2}, //state 4(E). if 0 -> state = 5; if 1 -> state = 2
                            {1,8}, //state 5(F). if 0 -> state = 1; if 1 -> state = 8
                            {7,2}, //state 6(G). if 0 -> state = 7; if 1 -> state = 2
                            {1,9}, //state 7(H). if 0 -> state = 1; if 1 -> state = 9
                            {9,2}, //state 8(I). if 0 -> state = 9; if 1 -> state = 2
                            {9,9}  //state 9(J). if 0 -> state = 9; if 1 -> state = 9
                        };
            //while input.txt has next line            
            while(sc.hasNext()){
                String str = sc.nextLine();
                int state=0;
                for(int i=0;i<str.length();i++){
                    state=dfa[state][str.charAt(i)-'0'];
                }
                System.out.print(str);

                //reject states 5, 6, and 9
                if(state == 5 || state == 6 || state == 9){
                    System.out.println(" rejected");
                }
                //accept states 0, 1, 2, 3, 4, 7, 8
                else{
                    System.out.println(" accepted");
                }
            }
       }
        //catch message for file not found error  
        catch(FileNotFoundException e){
            System.out.println("DFACheck: the file \'"+ args[0] +"\' could not be opened");
        }   
        //catch message for no input file specified error  
        catch (java.lang.ArrayIndexOutOfBoundsException e){
                System.out.println("DFACheck: no input file specified");
        }
    }
}