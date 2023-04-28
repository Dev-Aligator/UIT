
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField TextField; // main text output field
    JTextField historyField; // second field for see history operations
    JButton[] numberButtons = new JButton[10]; //0-9
    JButton[] functionButtons = new JButton[9]; //all operations buttons (+,-,*,/,.,=,c,(-))
    JButton addButton,subButton,mulButton,divButton; // buttons
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel; // main panel for buttons

    Font myFont = new Font("Times New Roman", Font.BOLD, 30); //global font

    double num1=0,num2=0,result=0;
    char operator;

    Calculator() {

        //application window
        frame = new JFrame("Calculator"); //title name
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        //output Text window
        TextField = new JTextField();
        TextField.setBounds(50, 45, 300, 50); //window location
        TextField.setFont(myFont);
        TextField.setEditable(false);
        TextField.setHorizontalAlignment(SwingConstants.RIGHT);

        // operations history window
        historyField = new JTextField();
        historyField.setBounds(50, 15, 300, 25);
        historyField.setEditable(false);
        historyField.setBorder(null); // hide frame border
        historyField.setHorizontalAlignment(SwingConstants.RIGHT); //writing switch on right

        historyField.setFont(new Font("Times New Roman", Font.PLAIN, 20)); //setfont and style of the text
        historyField.setForeground(Color.DARK_GRAY); // text color

        // all operation buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("X");
        clrButton = new JButton("C");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;


        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        //adding a buttons and setPosition
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // background for buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));
        //panel.setBackground(Color.GRAY);

        // add all buttons on the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        //add buttons and textFields on the frame
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(TextField);
        frame.add(historyField);
        frame.setVisible(true);

        frame.repaint(); // bag fix with buttons loadings


    }

    public static void main(String[] args) { //main program
        Calculator calc = new Calculator(); //create a new calculator
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) { // output only numbers
            if (e.getSource() == numberButtons[i]) { // check on pressing a number
                TextField.setText(TextField.getText().concat(String.valueOf(i))); //output
            }
        }

        // checking operation buttons clicks
        if (e.getSource() == decButton) {
            TextField.setText(TextField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(TextField.getText());
            operator = '+';
            TextField.setText("");
            historyField.setText(num1 + " + ");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(TextField.getText());
            operator = '-';
            TextField.setText("");
            historyField.setText(num1 + " - ");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(TextField.getText());
            operator = '*';
            TextField.setText("");
            historyField.setText(num1 + " * ");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(TextField.getText());
            operator = '/';
            TextField.setText("");
            historyField.setText(num1 + " / ");
        }
        if (e.getSource() == equButton) { // operation equ(=)
            num2 = Double.parseDouble(TextField.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }
            TextField.setText(String.valueOf(result));
            historyField.setText(historyField.getText().concat(String.valueOf(num2 + " =")));
            num1 = result;
        }

        // add functions clr, del, neg buttons
        if (e.getSource() == clrButton) {
            TextField.setText("");
            historyField.setText("");
        }
        if (e.getSource() == delButton) {
            String string = TextField.getText();
            TextField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                TextField.setText(TextField.getText() + string.charAt(i));
            }
        }
        if (e.getSource() == negButton) {
            if (TextField.getText().length() == 0) {
                TextField.setText("-");
            } else {
                double temp = Double.parseDouble(TextField.getText());
                temp *= -1;
                TextField.setText(String.valueOf(temp));
            }
        }
    }
}
