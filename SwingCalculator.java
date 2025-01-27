import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Calculator");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create display panel
        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        // Create button panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Calculator buttons
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if (cmd.equals("=")) {
                        try {
                            display.setText(evaluate(display.getText()));
                        } catch (Exception ex) {
                            display.setText("Error");
                        }
                    } else if (cmd.equals("C")) {
                        display.setText("");
                    } else {
                        display.setText(display.getText() + cmd);
                    }
                }
            });
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Method to evaluate expressions
    private static String evaluate(String expression) {
        // Simple evaluation using ScriptEngine
        try {
            return String.valueOf(new javax.script.ScriptEngineManager()
                    .getEngineByName("JavaScript").eval(expression));
        } catch (Exception e) {
            return "Error";
        }
    }
}
