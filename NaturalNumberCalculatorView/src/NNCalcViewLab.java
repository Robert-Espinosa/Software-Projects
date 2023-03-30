import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Test class for NaturalNumber calculator GUI (view in MVC).
 *
 * @author Put your name here
 */
public final class NNCalcViewLab extends JFrame implements ActionListener {

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1, THREE = 3, FOUR = 4, FIVE = 5, SIX = 6,
            SEVEN = 7, EIGHT = 8, NINE = 9;

    /**
     * No-argument constructor.
     */
    public NNCalcViewLab() {

        super("Natural Number Calculator");

        this.tTop = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        this.bMultiply = new JButton("*");
        this.bDivide = new JButton("/");

        this.bPower = new JButton("power");
        this.bRoot = new JButton("root");

        this.bClear = new JButton("clear");
        this.bSwap = new JButton("swap");
        this.bEnter = new JButton("enter");

        this.bDigits = new JButton[DIGIT_BUTTONS];

        for (int i = 0; i < DIGIT_BUTTONS; i++) {
            this.bDigits[i] = new JButton(String.valueOf(i));
        }

        // Set up the GUI widgets --------------------------------------------
        this.tTop.setWrapStyleWord(true);
        this.tTop.setEditable(false);
        this.tTop.setLineWrap(true);

        this.tBottom.setWrapStyleWord(true);
        this.tBottom.setEditable(false);
        this.tBottom.setLineWrap(true);

        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */

        this.bDivide.setEnabled(false);
        this.bRoot.setEnabled(false);

        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */

        JScrollPane scroll1 = new JScrollPane(this.tTop);
        JScrollPane scroll2 = new JScrollPane(this.tBottom);

        /*
         * Create main button panel
         */

        JPanel buttonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));
        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */
        buttonPanel.add(this.bDigits[SEVEN]);
        buttonPanel.add(this.bDigits[EIGHT]);
        buttonPanel.add(this.bDigits[NINE]);
        buttonPanel.add(this.bAdd);
        buttonPanel.add(this.bDigits[FOUR]);
        buttonPanel.add(this.bDigits[FIVE]);
        buttonPanel.add(this.bDigits[SIX]);
        buttonPanel.add(this.bSubtract);
        buttonPanel.add(this.bDigits[1]);
        buttonPanel.add(this.bDigits[2]);
        buttonPanel.add(this.bDigits[THREE]);
        buttonPanel.add(this.bMultiply);
        buttonPanel.add(this.bDigits[0]);
        buttonPanel.add(this.bPower);
        buttonPanel.add(this.bRoot);
        buttonPanel.add(this.bDivide);
        /*
         * Create side button panel
         */
        JPanel side = new JPanel(new GridLayout(SIDE_BUTTON_PANEL_GRID_ROWS,
                SIDE_BUTTON_PANEL_GRID_COLUMNS));
        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */
        side.add(this.bClear);
        side.add(this.bSwap);
        side.add(this.bEnter);
        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */
        JPanel both = new JPanel(new FlowLayout());
        /*
         * Add the other two button panels to the combined button panel
         */

        both.add(buttonPanel);
        both.add(side);

        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));

        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */

        this.add(scroll1);
        this.add(scroll2);
        this.add(both);

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);

        for (int i = 0; i < DIGIT_BUTTONS; i++) {
            this.bDigits[i].addActionListener(this);
        }

        // Set up the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(this,
                "You pressed: " + event.getActionCommand());
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        NNCalcViewLab view = new NNCalcViewLab();
    }

}
