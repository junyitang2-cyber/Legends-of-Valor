//class to parse scanner inputs. All functions are static
import java.util.Random;
import java.util.Scanner;

public class ScannerParser {
    private static Colors colors = new Colors();
    private static Scanner sc = new Scanner(System.in);

    public ScannerParser(){

    }

    public static String parseString(){
        String s;
        while(true){
            try {
                sc = new Scanner(System.in);
                s = sc.next();
                break;
            }catch (Exception e){
                AudioUtility.playSound(AudioUtility.ERROR);

                System.out.println(colors.addColor("red", "Please choose the given option to make your choice: "));
            }
        }
        return s;
    }

    public static int parseInt(){
        int i;
        while(true){
            try {
                sc = new Scanner(System.in);
                i = sc.nextInt();
                break;
            }catch (Exception e){
                AudioUtility.playSound(AudioUtility.ERROR);
                System.out.println(colors.addColor("red", "Input not valid! Please try again:"));
            }
        }
        return i;
    }

    public static boolean parseBoolean(){
        boolean b = false;
        String s = parseString();
        while (true){
        if(s.equals("Y") || s.equals("y")){
            b= true;
            break;
        }
        else if (s.equals("N") || s.equals("n")){
            b= false;
            break;
        }
        else {
            System.out.println(colors.addColor("red", "Please input Y/y or N/n to make your choice: "));
            s = parseString();
        }}
        return b;
    }

    public static int tryInt(){
        System.out.println(colors.addColor("red", "Input not valid! Please try again:"));
        int newint = parseInt();
        return newint;
    }

    public static String tryString(){
        String newString;
        while(true){
            try {
                newString = parseString();
                break;
            }catch (Exception e){
                System.out.println(colors.addColor("red", "Input not valid! Please try again:"));
            }
        }
        return newString;
    }

    public static int getRandNum(int bound){
        Random r = new Random();
        int n = r.nextInt(bound);
        return n;
    }


}
