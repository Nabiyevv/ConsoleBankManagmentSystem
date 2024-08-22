import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {


    public static void main(String[] args) throws IOException {
        
        ConsoleHelper ch = new ConsoleHelper();
        
        Scanner sc = new Scanner(System.in);

        ch.printAuthMenu();
        
        sc.close();
    }

}
