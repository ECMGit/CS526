package hw5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class InfixEvaluation {
    public static void main(String[] args) throws IOException {
//        String s = "2 + 5 * 3 - 6 / 2";
        Scanner fileInput = new Scanner(new File("src/hw5/hw5_input.txt"));

        //read file, calculate and print result;
        while(fileInput.hasNext()){
            String s = fileInput.nextLine();
            int pp = new Calculator().calculate_1(s);
            System.out.println("The value of "+ s + " is "+ pp);
        }


    }
}
