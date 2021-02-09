import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFACheck {
  public static boolean stateCheck(char lastState) {
    if (lastState == 'F' || lastState == 'G' || lastState == 'J') {
      return false;
    } else {
      return true;
    }
  }

  public static void main(String args[]) {
    if (args.length != 0) {
      try {
        File f = new File(args[0]);
        Scanner fileContent = new Scanner(f);
        while (fileContent.hasNextLine()) {
          String line = fileContent.nextLine();
          char[] state = new char[line.length()];
          for (int i = 0; i < line.length(); i++) {
            if (i == 0) {
              if (line.charAt(i) == '0') {
                state[i] = 'B';
              } else {
                state[i] = 'C';
              }
            } else if (line.charAt(i) == '0') {
              switch (state[i-1]) {
                case 'C':
                  state[i] = 'D';
                  break;
                case 'E':
                  state[i] = 'F';
                  break;
                case 'G':
                  state[i] = 'H';
                  break;
                case 'I':
                case 'J':
                  state[i] = 'J';
                  break;
                case 'B':
                case 'D':
                case 'F':
                case 'H':
                default:
                  state[i] = 'B';
              }
            } else {
              switch (state[i-1]) {
                case 'B':
                  state[i] = 'E';
                  break;
                case 'D':
                  state[i] = 'G';
                  break;
                case 'F':
                  state[i] = 'I';
                  break;
                case 'H':
                case 'J':
                  state[i] = 'J';
                  break;
                case 'C':
                case 'E':
                case 'G':
                case 'I':
                default:
                  state[i] = 'C';
              }
            }
          }
          if (stateCheck(state[line.length()-1])) {
            System.out.println(line + " accepted");
          } else {
            System.out.println(line + " rejected");
          }
        }
        fileContent.close();
      } catch (FileNotFoundException e) {
        System.out.println("DFACheck: the file '" + args[0] + "' could not be opened");
      }

    } else {
      System.out.println("DFACheck: no input file specified");
    }
  }
}
