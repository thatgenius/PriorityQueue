import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {

        System.out.println("Characters");
        printCharacters();

        System.out.println();
        System.out.println("Even numbers have higher priority then odd ones; ascending order");
        printIntegers();

        //System.out.println();
        //System.out.println("Performance comparison between my queue and java.util.PriorityQueue in filling them up with 10000 ints");
        //ComparePerformance();
    }

    static void printCharacters() {
        MyPriorityQueue<Character> mpq = new MyPriorityQueue<Character>();

        mpq.add('c');
        mpq.add('b');
        mpq.add('a');

        for (char s: mpq){
            System.out.print(mpq.poll() + " <---");
        }
    }

    static void printIntegers() {
        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<Integer>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1%2 == o2%2) {
                    return o1 < o2 ? -1 : (o1 > o2 ? 1 : 0);
                }
                else {
                    if (o1%2 == 0) return -1;
                    else return 1;
                }
            }
        });

        mpq.add(9);
        mpq.add(3);
        mpq.add(2);
        mpq.add(1);
        mpq.add(4);

        for (int s: mpq) {
            System.out.print(mpq.poll() + " <---");
        }
    }

    static void ComparePerformance() {
        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<Integer>(10);
        long end;
        long start = System.currentTimeMillis();
        for (int i = 0; i<10000; i++) {
            mpq.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("MyPriorityQueue: " + (end-start) + "ms");

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10);
        start = System.currentTimeMillis();
        for (int i = 0; i<10000; i++) {
            pq.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("java.util.PriorityQueue: " + (end-start) + "ms");
    }
}