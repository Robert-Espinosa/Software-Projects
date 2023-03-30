import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

//        Set<Integer> s = new Set1L<>();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        s.add(4);
//        s.add(5);
//        Set<Integer> s1 = new Set1L<>();
//
//        s1.add(4);
//        s1.add(5);
//        s1.add(6);
//        s1.add(7);
//
//        s.add(s1);
//
//        out.print(s.toString());
//        out.print(s1.toString());

//        Queue1L<Integer> q = new Queue1L<>();
//        q.enqueue(4);
//        q.enqueue(8);
//        q.enqueue(6);
//        q.enqueue(3);
//        q.enqueue(1);
//
//        int x = q.replaceFront(3);

//        out.print(88 % 2);

//        Queue<String> q = new Queue1L<>();
//        q.enqueue("Ohio");
//        q.enqueue("State");
//
//        food(q, "university");
//
//        Map<String,String> s= new Map1L<>();
//
//
//        Map<String,String> temp = s.newInstance();
//        temp.transferFrom(s);
//
//        Map.Pair<String, String> p = temp.removeAny();
//
//        temp.has
//
//        out.print(q);
//        out.println(n1);
//        out.print(n2);

        //fifo
//        Queue<Integer> q = new Queue1L<>();
//        q.enqueue(1);
//        q.enqueue(4);
//        q.enqueue(5);
//        int p = q.replaceFront(9);
//
//        out.print(q.front());

        NaturalNumber n = new NaturalNumber2(123450);
        int pos = 0;

        out.print(digitAt(n, pos));

        out.print(isDivisableBy5(n));
    }

    private static int digitAt(NaturalNumber n1, int pos) {

        int answer = n1.divideBy10();
        if (pos > 0) {
            answer = digitAt(n1, pos - 1);
        }
        n1.multiplyBy10(answer);
        return answer;

    }

    private static boolean isDivisableBy5(NaturalNumber n) {
        int remainder = n.divideBy10();
        boolean divisable = remainder % 5 == 0;
        n.multiplyBy10(remainder);

        return divisable;
    }

}
