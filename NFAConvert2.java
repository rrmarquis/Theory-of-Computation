import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NFAConvert {

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
          case "Q0":
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

  public static void nfaConvert() {
    q0 = "{" + q0 + "}";
    for (int i = 0; i < q.size(); i++) {
      String temp = "{" + q.get(i) + "}";
      q.set(i, temp);
    }
    for (int i = 0; i < f.size(); i++) {
      String temp = "{" + f.get(i) + "}";
      f.set(i, temp);
    }
    for (int i = 0; i < delta.size(); i++) {
      if (!delta.get(i).contains(" E ")) {
        String d[] = delta.get(i).split(" ");
        String temp = "{" + d[0] + "}";
        String temp2 = "{" + d[2] + "}";
        String full = temp + " " + d[1] + " " + temp2;
        delta.set(i, full);
      } else {
        delta.remove(i);
        i--;
      }
    }
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
    if (args.length == 1) {
      File dfa;
      Scanner dfaContent = new Scanner("");
      boolean validFile = false;

      try {
        dfa = new File(args[0]);
        dfaContent = new Scanner(dfa);
        validFile = true;
      } catch (FileNotFoundException e) {
        System.out.println("NFAConvert: the file '" + args[0] + "' could not be opened");
        validFile = false;
      }

      if (validFile) {
        dfaGenerator(dfaContent);
        nfaConvert();
        System.out.println("% Q");
        for (int i = 0; i < q.size(); i++) {
          System.out.println(q.get(i));
        }
        System.out.println("% Sigma");
        for (int i = 0; i < sigma.size(); i++) {
          System.out.println(sigma.get(i));
        }
        System.out.println("% F");
        for (int i = 0; i < f.size(); i++) {
          System.out.println(f.get(i));
        }
        System.out.println("% Q0");
        System.out.println(q0);
        System.out.println("% Delta");
        for (int i = 0; i < delta.size(); i++) {
          System.out.println(delta.get(i));
        }
      }

    } else {
      System.out.println("NFAConvert: no input file specified");
    }
  }
}
