import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    public static void bar(NaturalNumber n1, NaturalNumber n2) {
        String s = n1.toString();
        n1.transferFrom(n2);
        s = "7" + s + "7" + s + "7";
        n1 = new NaturalNumber2(s);
        n2.copyFrom(n1);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter output = new SimpleWriter1L();

        NaturalNumber num1 = new NaturalNumber2("22");
        NaturalNumber num2 = new NaturalNumber2("100");
        bar(num1, num2);

        output.println(num1);
        output.print(num2);
//        num.add(num);
//        output.print(num);
        output.close();
    }

}
