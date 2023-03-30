/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class number {

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {
        int num = 0;

        if (j == k) {
            num = j;
        } else if (j == Integer.MAX_VALUE - 1 || k == Integer.MAX_VALUE - 1) {
            num = Integer.MAX_VALUE - 1;
        } else if (j == Integer.MIN_VALUE + 1 || k == Integer.MIN_VALUE + 1) {
            num = Integer.MIN_VALUE + 1;
        } else {
            num = (j + k) / 2;
        }
        return num;
    }

    public static void main(String[] args) {

    }

}
