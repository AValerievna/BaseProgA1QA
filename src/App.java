import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        while (true) {
            System.out.println("Which command would you like to do? Choose the number");
            System.out.println("  1)Find the most fresh files with necessary extension");
            System.out.println("  2)Find arrays difference");
            System.out.println("  3)Cut random strings from one file to other");
            System.out.println("  4)Exit");

            switch (in.nextInt()) {
                case 1:
                    doFindMostFreshFileOption(in);
                    break;
                case 2:
                    doFindArraysDiffOption(in);
                    break;
                case 3:
                    doCutRandomStringsOption(in);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Please, choose the number from the list");
                    break;
            }
        }
    }

    private static void doCutRandomStringsOption(Scanner in) {
        System.out.println("Input a file path:");
        String path = in.nextLine();
        System.out.println("Would you like to use \"_res\" as result file ending?");
        System.out.println("  1)yes");
        System.out.println("  2)no");
        Task3 task3;
        switch (in.nextInt()) {
            case 1:
                task3 = new Task3();
                break;
            case 2:
                System.out.println("Input your result file ending");
                String resEnd = in.nextLine();
                task3 = new Task3(resEnd);
                break;
            default:
                System.out.println("Please, choose the option from the list");
                return;
        }
        System.out.println("Which version would you like to see? Choose the number");
        System.out.println("  1)Only path");
        System.out.println("  2)With number of string");
        String res = null;
        switch (in.nextInt()) {
            case 1:
                res = task3.selectCases(path);
                break;
            case 2:
                System.out.println("Input a number of strings:");
                int num = in.nextInt();
                if (num < 0) {
                    System.out.println("Please, input natural number");
                    break;
                }
                res = task3.selectCases(path, num);
                break;
            default:
                System.out.println("Please, choose the number from the list");
                return;
        }
        System.out.println(res);
    }

    private static void doFindArraysDiffOption(Scanner in) {
        System.out.println("Which version would you like to see? Choose the number");
        System.out.println("  1)Without collections");
        System.out.println("  2)With collections");
        Task2 task2 = new Task2();

        switch (in.nextInt()) {
            case 1:
                task2.findArraysDifferenceWithoutCollections();
                break;
            case 2:
                task2.findArraysDifferenceWithCollections();
                break;
            default:
                System.out.println("Please, choose the number from the list");
                break;
        }
    }

    private static void doFindMostFreshFileOption(Scanner in) {
        System.out.println("Input a directory path:");
        String path = in.nextLine();
        System.out.println("Input a file extension:");
        String ext = "." + in.nextLine();
        Task1 task1 = new Task1(path, ext);
        task1.findMostFreshFilesWithExtension();
    }
}
