import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2 {
    static String[] first = {"Alex","Dima","Kate","Galina","Ivan"};
    static String[] second = {"Dima","Ivan","Kate"};

    static void task21(){

        String[] result = new String[first.length-second.length];

        int k=0;
        for(String firstEl : first) {
            if(!Arrays.asList(second).contains(firstEl)){
                result[k] = firstEl;
                k++;
            }
        }
        Task2.arrayOutput(result);
    }

    static void task22() {
        String[] temp = new String[first.length];

        int k=0;
        for(String firstEl : first) {
            if(!Arrays.asList(second).contains(firstEl)){
                temp[k] = firstEl;
                k++;
            }
        }
        String[] result = Arrays.copyOf(temp,k);
        Task2.arrayOutput(result);
    }

    static void task23() {
        List<String> firstList = new ArrayList<String>(Arrays.asList(first));
        List<String> secondList = new ArrayList<String>(Arrays.asList(second));

        firstList.removeAll(secondList);
        String[] result = firstList.toArray(new String[firstList.size()]);
        Task2.arrayOutput(result);
    }

    static void arrayOutput(String[] arr) {
        System.out.println("Result array:");
        for(String s : arr) {
            System.out.println(s);
        }
    }
}
