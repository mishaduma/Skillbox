import javax.swing.*;
import java.awt.*;

public class MainForm {
    private JPanel mainPanel;
    private JPanel firstPanel;
    private JButton collapseButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel secondPanel;
    private JButton expandButton;
    private JTextField textField4;
    private String input;

    public MainForm() {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());

        collapseButton.addActionListener(e -> {
            String text1 = textField1.getText().trim();
            String text2 = textField2.getText().trim();
            String text3 = textField3.getText().trim();
            if (text1.length() != 0 && text2.length() != 0) {
                input = text1 + " " + text2 + " " + text3;
                cl.next(mainPanel);
                textField4.setText(input);
            } else {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Incorrect input!",
                        "Error!",
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });

        expandButton.addActionListener(e -> {
            input = textField4.getText().trim();
            String[] expandedInput = input.split(" ");
            if (expandedInput.length < 2) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Incorrect input!",
                        "Error!",
                        JOptionPane.PLAIN_MESSAGE
                );
            } else {
                textField1.setText(expandedInput[0]);
                textField2.setText(expandedInput[1]);
                if (expandedInput.length > 2) {
                    textField3.setText(expandedInput[2]);
                }
                cl.next(mainPanel);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
