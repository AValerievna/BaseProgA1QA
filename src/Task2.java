import java.util.Arrays;
import java.util.List;

 class Task2 {
    private String[] FIRST;
    private String[] SECOND;
    private String[] result;


     public Task2() {
         FIRST = new String[]{"Alex", "Dima", "Kate", "Galina", "Ivan"};
         SECOND = new String[]{"Dima", "Ivan", "Kate"};
     }

     void findArraysDifferenceWithoutCollections() {
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
        result = Arrays.copyOf(temp,k);
        arrayOutput(result);
    }

     void findArraysDifferenceWithCollections() {
        List<String> firstList = Arrays.asList(FIRST);
        List<String> secondList = Arrays.asList(SECOND);

        firstList.removeAll(secondList);
        result = firstList.toArray(new String[firstList.size()]);
        arrayOutput(result);
    }

    private void arrayOutput(String[] arr) {
        System.out.println("Result array:");
        for(String s : arr) {
            System.out.println(s);
        }
    }
}
