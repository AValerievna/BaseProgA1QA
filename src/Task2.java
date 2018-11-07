import java.util.Arrays;
import java.util.List;

 class Task2 {
    private static final String[] FIRST = {"Alex","Dima","Kate","Galina","Ivan"};
    private static final String[] SECOND = {"Dima","Ivan","Kate"};

    static void findArraysDifferenceWithoutCollections() {
        String[] temp = new String[FIRST.length];

        int k=0;
        for(String firstEl : FIRST) {
            for(int i=0;i<SECOND.length; i++) {
                if(firstEl.equals(SECOND[i])){
                    break;
                }
                if(i==SECOND.length-1){
                    temp[k] = firstEl;
                    k++;
                }
            }
        }
        String[] result = Arrays.copyOf(temp,k);
        Task2.arrayOutput(result);
    }

    static void findArraysDifferenceWithCollections() {
        List<String> firstList = Arrays.asList(FIRST);
        List<String> secondList = Arrays.asList(SECOND);

        firstList.removeAll(secondList);
        String[] result = firstList.toArray(new String[firstList.size()]);
        Task2.arrayOutput(result);
    }

    private static void arrayOutput(String[] arr) {
        System.out.println("Result array:");
        for(String s : arr) {
            System.out.println(s);
        }
    }
}
