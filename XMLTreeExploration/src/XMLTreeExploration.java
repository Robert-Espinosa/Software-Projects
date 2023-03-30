import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {

        if (xt.numberOfChildren() > 0) {
            int middleIndex = xt.numberOfChildren() / 2;
            XMLTree middleChild = xt.child(middleIndex);
            out.println(middleChild.label());
            if (middleChild.isTag()) {
                out.println("middleChild is a tag");
            } else {
                out.println("middleChild isnt a tag");
            }
            if (middleChild.numberOfChildren() > 0) {
                XMLTree doubleMiddleChild = middleChild.child(0);
                if (doubleMiddleChild.isTag()) {
                    out.println("The middle childs label is a tag");
                } else {
                    out.println("The middle childs label isnt a tag");

                }
            }
            out.println("the middle child has " + middleChild.numberOfChildren()
                    + " children");
        }
    }

    /**
     * Output all attributes names and values for the root of the given
     * {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose root's attributes are to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and out.is_open
     * @ensures <pre>
     * out.content =
     *   #out.content *  [the name and value of each attribute of the root of xt]
     * </pre>
     */
    private static void printRootAttributes(XMLTree xt, SimpleWriter out) {
//        int[] a = new int[5];
        for (String name : xt.attributeNames()) {
            out.print(name + " ---> " + xt.attributeValue(name));
        }

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

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");

        output.println(xml.child(0).child(0).child(2).child(0).label());
//        output.print(xml.toString());
        xml.display();
        if (xml.isTag()) {
            output.println("the root of the xml document is a tag");
        } else {
            output.println("the root of the xml document is a text node");
        }
        output.println("the label of the root node is " + xml.label());

        XMLTree results;
        XMLTree channel;
        XMLTree title;
        XMLTree astronomy;

        XMLTree titleText;
        if (xml.numberOfChildren() > 0) {
            results = xml.child(0);
            if (results.numberOfChildren() > 0) {
                channel = results.child(0);
                output.println("The number of childern of channel are "
                        + channel.numberOfChildren());
                if (channel.numberOfChildren() > 0) {
                    title = channel.child(1);
                    if (title.numberOfChildren() > 0) {
                        titleText = title.child(0);
                        output.println("Text title is " + titleText.label());
                    }
                }

                astronomy = channel.child(10);
                if (astronomy.hasAttribute("midday")) {
                    output.println(
                            "the tree astronomy has the attribute midday");
                } else {
                    output.println(
                            "the tree astronomy doesnt have the attribute midday");

                }
                if (astronomy.hasAttribute("sunset")) {
                    output.println(
                            "the tree astronomy has the attribute sunset");
                } else {
                    output.println(
                            "the tree astronomy doesnt have the attribute sunset");

                }

                output.println("value of sunrise is "
                        + astronomy.attributeValue("sunrise"));
                output.println("value of sunset is "
                        + astronomy.attributeValue("sunset"));

                printMiddleNode(channel, output);

                XMLTree item = channel.child(12);
                XMLTree forecast = item.child(9);

                printRootAttributes(forecast, output);

            }
        }

        input.close();
        output.close();
    }

}
