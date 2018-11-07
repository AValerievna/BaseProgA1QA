import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Which command would you like to do? Choose the number");
            System.out.println("  1)Find the most fresh files with necessary extension");
            System.out.println("  2)Find arrays difference");
            System.out.println("  3)Cut random strings from one file to other");
            System.out.println("  4)Exit");

            switch( in.nextInt()) {
                case 1: Task1.findTheMostFreshFilesWithExtension();
                    break;
                case 2:
                    System.out.println("Which version would you like to see? Choose the number");
                    System.out.println("  1)Without collections");
                    System.out.println("  2)With collections");
                    switch(in.nextInt()) {
                        case 1:
                            Task2.findArraysDifferenceWithoutCollections();
                            break;
                        case 2:
                            Task2.findArraysDifferenceWithCollections();
                            break;
                        default: System.out.println("Please, choose the number from the list");
                            continue;
                    }
                    break;
                case 3: Task3.cutRandomStringsFromOneFileToOther();
                    break;
                case 4:
                    System.exit(0);
                default: System.out.println("Please, choose the number from the list");
            }
        }
    }
}
