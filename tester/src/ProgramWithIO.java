import components.binarytree.BinaryTree;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {

        NaturalNumber next = new NaturalNumber1L(n);
        next.multiply(n);

        n.copyFrom(next);
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        Boolean inTree = false;
        int compared = x.compareTo(t.root());

        if (compared == 0) {
            inTree = true;
        } else if (compared < 0) {
            t.disassemble(t, t);
            isInTree(t, x);
        } else {
            t.disassemble(t, t);
            isInTree(t, x);
        }

        return inTree;

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedLists = new int[nums1.length + nums2.length];

//        int counter = 0;
//        boolean even = true;
//        if (nums1.length > nums2.length) {
//            counter = nums2.length;
//            even = false;
//        } else if (nums2.length > nums1.length) {
//            counter = nums1.length;
//            even = false;
//        } else {
//            counter = nums1.length;
//        }
//
//        while(nums1.length>0 || nums2.length>0) {
//
//        }
//        for (int i = 0; i < counter; i++) {
//            if (even) {
//                for (int j = 0; j < counter; j++) {
//                    if (nums1[j] < nums2[j]) {
//                        mergedLists[j] = nums1[j];
//                        mergedLists[j + 1] = nums2[j];
//                    } else {
//                        mergedLists[j] = nums2[j];
//                        mergedLists[j + 1] = nums1[j];
//                    }
//                }
//            }
//        }

//        ArrayList<Integer> number1 = new ArrayList<>();
//        ArrayList<Integer> number2 = new ArrayList<>();
//        ArrayList<Integer> merged = new ArrayList<>();
//
//        for(int i = 0; i<nums1.length;i++){
//            number1.add(nums1[i]);
//        }
//        for(int j = 0; j<nums2.length; j++) {
//            number2.add(nums2[j]);
//        }
//        while(number1.size()>0 && number2.size()>0) {
//            if(number1.)
//        }

        System.out.println(mergedLists[0]);
        System.out.println(mergedLists[1]);
        System.out.println(mergedLists[2]);
        System.out.println(mergedLists[3]);

        return 17.0;
    }

    private static void foo(NaturalNumber n1, NaturalNumber n2) {
        n2 = n1.divide(n2);
        n2 = new NaturalNumber2(7);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        Sequence<Integer> s = new Sequence1L<>();

        String x = "1";

        output.print(x.length());

        input.close();
        output.close();
    }

}
