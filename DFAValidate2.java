import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DFAValidate {

  public static ArrayList<String> q;
  public static ArrayList<String> sigma;
  public static ArrayList<String> f;
  public static String q0;
  public static ArrayList<String> delta;

  public static void dfaGenerator(Scanner in) {
    q = new ArrayList<String>();
    sigma = new ArrayList<String>();
    f = new ArrayList<String>();
    delta = new ArrayList<String>();
    String arr = "";
    while (in.hasNextLine()) {
      String line = in.nextLine();
      if (line.contains("%")) {
        arr = line.replace("% ", "");
      } else {
        switch (arr) {
          case "Q":
            q.add(line);
            break;
          case "Sigma":
            sigma.add(line);
            break;
          case "F":
            f.add(line);
            break;
          case "Delta":
            delta.add(line);
            break;
          default:
            q0 = line;
        }
      }
    }
    in.close();
  }

  public static void state(Scanner stringIn) {
    while (stringIn.hasNextLine()) {
      ArrayList<String> states = new ArrayList<String>();
      states.add(q0);
      String lastState = states.get(states.size()-1);
      String line = stringIn.nextLine();

      for (int l = 0; l < line.length(); l++) {
        lastState = states.get(states.size()-1);
        for (int d = 0; d < delta.size(); d++) {
          String chara = line.charAt(l) + "";
          String[] t = delta.get(d).split(" ");
          if(lastState.equals(t[0])) {
            if (chara.equals(t[1])) {
              states.add(t[2]);
              break;
            }
          }
        }
      }

      lastState = states.get(states.size()-1);

      if(f.contains(lastState)) {
        System.out.println(line + " accepted");
      } else {
        System.out.println(line + " rejected");
      }
    }
    stringIn.close();
  }

  public static void main(String args[]) {
    if (args.length == 2) {
      File dfa;
      File strings;
      Scanner dfaContent = new Scanner("");
      Scanner stringContent = new Scanner("");
      boolean validFiles = false;

      try {
        dfa = new File(args[0]);
        dfaContent = new Scanner(dfa);
        validFiles = true;
      } catch (FileNotFoundException e) {
        System.out.println("DFACheck: the file '" + args[0] + "' could not be opened");
        validFiles = false;
      }

      try {
        strings = new File(args[1]);
        stringContent = new Scanner(strings);
        validFiles = true;
      } catch (FileNotFoundException e) {
        System.out.println("DFACheck: the file '" + args[1] + "' could not be opened");
        validFiles = false;
      }

      if (validFiles) {
        dfaGenerator(dfaContent);
        state(stringContent);
      }

    } else if(args.length == 1) {
      System.out.println("DFACheck: invalid usage - the program must be given two files as input");
    } else {
      System.out.println("DFACheck: no input files specified");
    }
  }
}
