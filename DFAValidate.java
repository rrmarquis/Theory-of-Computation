
import java.util.*;
import java.io.*;

class DFAValidate {

    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("DFAValidate: no input files specified");
        } else if (args.length != 2) {
            System.out.println("DFAValidate: invalid usage - the program must be given two files as input");
        } else {
            try {
                //file input (fin) definition
                FileInputStream fin1 = new FileInputStream(args[0]);
                //scanner definition (fin file for loop)
                Scanner sc1 = new Scanner(fin1);
                int numOfStates = 10;
                int numOfVars = 2;
                int numOfFinals = 7;
                int numOfDelta = 20;

                while (sc1.hasNext()) {
                    String line = sc1.nextLine();
                    char[] statesArr = new char[numOfStates];
                    if (line.startsWith("% Q")) {
                        line = sc1.nextLine();
                        for (int i = 0; i < numOfStates; i++) {
                            statesArr[i] = line.charAt(0);
                            line = sc1.nextLine();
                        }
                    }
                    char[] sigmaVars = new char[numOfVars];
                    if (line.startsWith("% Sigma")) {
                        line = sc1.nextLine();
                        for (int i = 0; i < numOfVars; i++) {
                            sigmaVars[i] = line.charAt(0);
                            line = sc1.nextLine();
                        }
                    }
                    char[] finalsArr = new char[numOfFinals];
                    if (line.startsWith("% F")) {
                        line = sc1.nextLine();
                        for (int i = 0; i < numOfFinals; i++) {
                            finalsArr[i] = line.charAt(0);
                            line = sc1.nextLine();
                        }
                    }
                    char startState = '0';
                    if (line.startsWith("% Q")) {
                        line = sc1.nextLine();
                        startState = line.charAt(0);
                    }
                    int startInt = 0;
                    if (startState == 'A') {
                              
                            } else if (startState == 'A') {
                                startInt = 1;
                            } else if (startState == 'B') {
                                startInt = 2;
                            } else if (startState == 'C') {
                                startInt = 3;
                            } else if (startState == 'D') {
                                startInt = 4;
                            } else if (startState == 'E') {
                                startInt = 5;
                            } else if (startState == 'F') {
                                startInt = 6;
                            } else if (startState == 'G') {
                                startInt = 7;
                            } else if (startState == 'H') {
                                startInt = 8;
                            } else if (startState == 'I') {
                                startInt = 9;
                            }
                            
                        

                    if (line.startsWith("% Delta")) {
                        line = sc1.nextLine();
                        char[] deltaVals = new char[numOfDelta];
                        for (int i = 0; i < numOfDelta; i++) {
                            deltaVals[i] = line.charAt(4);
                            if (sc1.hasNextLine()) {
                                line = sc1.nextLine();
                            }
                        }
                        int[] deltaInts = new int[numOfDelta];
                        for (int i = 0; i < numOfDelta; i++) {
                            if (String.valueOf(deltaVals[i]).equals("A")) {
                                deltaInts[i] = 0;
                            } else if (String.valueOf(deltaVals[i]).equals("B")) {
                                deltaInts[i] = 1;
                            } else if (String.valueOf(deltaVals[i]).equals("C")) {
                                deltaInts[i] = 2;
                            } else if (String.valueOf(deltaVals[i]).equals("D")) {
                                deltaInts[i] = 3;
                            } else if (String.valueOf(deltaVals[i]).equals("E")) {
                                deltaInts[i] = 4;
                            } else if (String.valueOf(deltaVals[i]).equals("F")) {
                                deltaInts[i] = 5;
                            } else if (String.valueOf(deltaVals[i]).equals("G")) {
                                deltaInts[i] = 6;
                            } else if (String.valueOf(deltaVals[i]).equals("H")) {
                                deltaInts[i] = 7;
                            } else if (String.valueOf(deltaVals[i]).equals("I")) {
                                deltaInts[i] = 8;
                            } else if (String.valueOf(deltaVals[i]).equals("J")) {
                                deltaInts[i] = 9;
                            }
                        }
                        int[][] dfa = {
                            {deltaInts[0], deltaInts[1]}, //state 0(A). if 0 -> state = 1; if 1 -> state = 2 
                            {deltaInts[2], deltaInts[3]}, //state 1(B). if 0 -> state = 2; if 1 -> state = 4
                            {deltaInts[4], deltaInts[5]}, //state 2(C). if 0 -> state = 3; if 1 -> state = 2
                            {deltaInts[6], deltaInts[7]}, //state 3(D). if 0 -> state = 1; if 1 -> state = 6
                            {deltaInts[8], deltaInts[9]}, //state 4(E). if 0 -> state = 5; if 1 -> state = 2
                            {deltaInts[10], deltaInts[11]}, //state 5(F). if 0 -> state = 1; if 1 -> state = 8
                            {deltaInts[12], deltaInts[13]}, //state 6(G). if 0 -> state = 7; if 1 -> state = 2
                            {deltaInts[14], deltaInts[15]}, //state 7(H). if 0 -> state = 1; if 1 -> state = 9
                            {deltaInts[16], deltaInts[17]}, //state 8(I). if 0 -> state = 9; if 1 -> state = 2
                            {deltaInts[18], deltaInts[19]} //state 9(J). if 0 -> state = 9; if 1 -> state = 9
                        };

                        try {
                            //file input (fin) definition
                            FileInputStream fin2 = new FileInputStream(args[1]);
                            //scanner definition (fin file for loop)
                            Scanner sc2 = new Scanner(fin2);

                            //while input.txt has next line            
                            while (sc2.hasNext()) {
                                String str = sc2.nextLine();
                                int state = startInt;
                                for (int i = 0; i < str.length(); i++) {
                                    state = dfa[state][str.charAt(i) - '0'];
                                }
                                System.out.print(str);

                                //reject states 5, 6, and 9
                                if (state == 5 || state == 6 || state == 9) {
                                    System.out.println(" rejected");
                                } //accept states 0, 1, 2, 3, 4, 7, 8
                                else {
                                    System.out.println(" accepted");
                                }
                            }

                        } //catch message for file not found error  
                        catch (FileNotFoundException e) {
                            System.out.println("DFAValidate: the file \'" + args[1] + "\' could not be opened");
                        }
                    }
                }

            } //catch message for file not found error  
            catch (FileNotFoundException e) {
                System.out.println("DFAValidate: the file \'" + args[0] + "\' could not be opened");
            }
        }
    }
}
