import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Which command would you like to do? Choose the number");
            System.out.println("  1)Task 1");
            System.out.println("  2)Task 2");
            System.out.println("  3)Task 3");
            System.out.println("  4)Exit");

            switch( in.nextInt()) {
                case 1: Task1.task1();
                    break;
                case 2:
                    System.out.println("Which version would you like to see? Choose the number");
                    System.out.println("  1)Without collections, determined input array");
                    System.out.println("  2)Without collections, input array is variable");
                    System.out.println("  2)With collections");
                    switch(in.nextInt()) {
                        case 1:
                            Task2.task21();
                            break;
                        case 2:
                            Task2.task22();
                            break;
                        case 3:
                            Task2.task23();
                            break;
                        default: System.out.println("Please, choose the number from the list");
                            continue;
                    }
                    break;
                case 3: Task3.task3();
                    break;
                case 4:
                    System.exit(0);
                default: System.out.println("Please, choose the number from the list");
            }
        }
    }
}
