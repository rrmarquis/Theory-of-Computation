import java.util.*;
import java.io.*;

class NFAConvert {

    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("NFAConvert: no input files specified");
        } else {            
            try {
                //file input (fin) definition
                FileInputStream fin = new FileInputStream(args[0]);
                //scanner definition (fin file for loop)
                Scanner sc = new Scanner(fin);
                int numOfStates = 10;
                int numOfVars = 2;
                int numOfFinals = 7;
                int numOfDelta = 20;

                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    char[] statesArr = new char[numOfStates];
                    if (line.startsWith("% Q")) {
                        System.out.println("% Q");
                        line = sc.nextLine();
                        for (int i = 0; i < numOfStates; i++) {
                            statesArr[i] = line.charAt(0);
                            System.out.println("{" + statesArr[i] + "}");
                            line = sc.nextLine();
                        }
                    }
                    char[] sigmaVars = new char[numOfVars];
                    if (line.startsWith("% Sigma")) {
                        System.out.println("% Sigma");
                        line = sc.nextLine();
                        for (int i = 0; i < numOfVars; i++) {
                            sigmaVars[i] = line.charAt(0);
                            System.out.println(sigmaVars[i]);
                            line = sc.nextLine();
                        }
                    }
                    char[] finalsArr = new char[numOfFinals];
                    if (line.startsWith("% F")) {
                        System.out.println("% F");
                        line = sc.nextLine();
                        for (int i = 0; i < numOfFinals; i++) {
                            finalsArr[i] = line.charAt(0);
                            System.out.println("{" + finalsArr[i] + "}");
                            line = sc.nextLine();
                        }
                    }
                    char startState = 0;
                    if (line.startsWith("% Q0")) {
                        System.out.println("% Q0");
                        line = sc.nextLine();
                        startState = line.charAt(0);
                        System.out.println("{" + startState + "}");
                    }
                    
                    if (line.startsWith("% Delta")) {
                        System.out.println("% Delta");
                        line = sc.nextLine();
                        String[] deltaVals = new String[numOfDelta];
                        char[] deltaFront = new char[numOfDelta];
                        char[] deltaMiddle = new char[numOfDelta];
                        char[] deltaEnd = new char[numOfDelta];
                        for (int i = 0; i < numOfDelta; i++) {
                            deltaVals[i] = line;
                            deltaFront[i] = line.charAt(0);
                            deltaMiddle[i] = line.charAt(2);
                            deltaEnd[i] = line.charAt(4);
                            System.out.println("{" + deltaFront[i] + "} " 
                                    + deltaMiddle[i] +
                                    " {" + deltaEnd[i] + "}");
                            if (sc.hasNextLine()) {
                                line = sc.nextLine();
                            }
                        }  
                    }
                }
            } //catch message for file not found error  
            catch (FileNotFoundException e) {
                System.out.println("NFAConvert: the file \'" + args[0] + "\' could not be opened");
            }
        }
    }
}
