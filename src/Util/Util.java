package Util;

import java.util.Scanner;

public class Util {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void log_info(Object message){
        System.out.println(message);
    }
    public static void log_success(Object message){
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }
    public static void log_error(Object message){
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static Object getFromInput(String type,String message){
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true){
            System.out.print(message);
            input = scanner.nextLine();

            if(type.equals("string")){
                return input;
            }
            else if(type.equals("double")){
                try {
                    return Double.parseDouble(input);
                }catch (Exception e){
                    System.out.println("input is not a number");
                }
            }
            else if (type.equals("int")){
                try{
                    return Integer.parseInt(input);
                }
                catch (Exception e){
                    System.out.println("input is not a number");
                }
            }
        }
    }

}
