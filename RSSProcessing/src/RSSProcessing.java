import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Robbie Espinosa
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int index = -1;

        for (int i = 0; i < xml.numberOfChildren() && index == -1; i++) {
            if (xml.child(i).label().equals(tag)) {
                index = i;
            }
        }

        return index;

    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        if (item.numberOfChildren() > 0) {
            if (getChildElement(item, "title") != -1) {
                if (item.child(getChildElement(item, "title"))
                        .numberOfChildren() > 0) {
                    out.println("Title: "
                            + item.child(getChildElement(item, "title"))
                                    .child(0).label());
                } else {
                    out.println("Has title but not title child");
                }
            } else {
                if (item.child(getChildElement(item, "description"))
                        .numberOfChildren() > 0) {
                    out.println("description: "
                            + item.child(getChildElement(item, "description"))
                                    .child(0).label());
                } else {
                    out.println("Has description but not description child");
                }
            }
            if (getChildElement(item, "link") != -1) {
                out.println("link: " + item.child(getChildElement(item, "link"))
                        .child(0).label());
            }
            if (getChildElement(item, "pubDate") != -1) {
                out.println("pubDate: "
                        + item.child(getChildElement(item, "pubDate")).child(0)
                                .label());
            } else {
                out.println("<pubDate> not present");
            }
            if (getChildElement(item, "source") != -1) {
                out.println(
                        "source: " + item.child(getChildElement(item, "source"))
                                .child(0).label());
                if (item.child(getChildElement(item, "source"))
                        .hasAttribute("url")) {
                    out.println("source URL: "
                            + item.child(getChildElement(item, "source"))
                                    .attributeValue("url"));

                }
            } else {
                out.println("<source> not present");

            }

        }

        out.println("\n\n");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);
        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);
        /*
         * TODO: #2 - output title, link, and description
         */
        int title = getChildElement(channel, "title");
        out.println("Title: " + channel.child(title).child(0).label());

        int description = getChildElement(channel, "description");
        out.println(
                "description: " + channel.child(description).child(0).label());

        int link = getChildElement(channel, "link");
        out.println("Link: " + channel.child(link).child(0).label());

        /*
         * TODO: #4 - for each item, output title (or description, if title is
         * not available) and link (if available)
         */
        for (int i = 0; i < channel.numberOfChildren(); i++) {
            if (channel.child(i).label().equals("item")) {
                processItem(channel.child(i), out);
            }
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
