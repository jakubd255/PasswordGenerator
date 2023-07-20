import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final Generator generator;

    public GUI() {
        super("Password Generator");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        generator = new Generator();
        addComponents();
    }

    private void addComponents() {
        //Password output
        JTextArea passwordOutput = new JTextArea();
        passwordOutput.setEditable(false);
        passwordOutput.setBounds(35, 50, 430, 30);
        passwordOutput.setFont(new Font("Dialog", Font.PLAIN, 22));
        passwordOutput.setFocusable(false);
        add(passwordOutput);

        //Password length info
        JLabel sliderLabel = new JLabel("Password length: 15");
        sliderLabel.setBounds(35, 130, 200, 20);
        add(sliderLabel);

        //Password length slider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 30, 15);
        slider.setBounds(20, 160, 460, 20);
        slider.setFocusable(false);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderLabel.setText("Password length: "+slider.getValue());
            }
        });
        add(slider);

        //Lowercase toggle button
        JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
        lowercaseToggle.setBounds(35, 230, 215, 50);
        lowercaseToggle.setSelected(true);
        lowercaseToggle.setFocusable(false);
        add(lowercaseToggle);

        //Uppercase toggle button
        JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
        uppercaseToggle.setBounds(250, 230, 215, 50);
        uppercaseToggle.setFocusable(false);
        add(uppercaseToggle);

        //Numbers toggle button
        JToggleButton numbersToggle = new JToggleButton("Numbers");
        numbersToggle.setBounds(35, 280, 215, 50);
        numbersToggle.setFocusable(false);
        add(numbersToggle);

        //Symbols toggle button
        JToggleButton symbolsToggle = new JToggleButton("Symbols");
        symbolsToggle.setBounds(250, 280, 215, 50);
        symbolsToggle.setFocusable(false);
        add(symbolsToggle);

        //Generate button
        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(35, 380, 430, 50);
        generateButton.setFocusable(false);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isAtLeastOne = lowercaseToggle.isSelected() ||
                        uppercaseToggle.isSelected() ||
                        numbersToggle.isSelected() ||
                        symbolsToggle.isSelected();

                if(isAtLeastOne) {
                    int length = slider.getValue();
                    String password = generator.getPassword(length,
                            lowercaseToggle.isSelected(),
                            uppercaseToggle.isSelected(),
                            numbersToggle.isSelected(),
                            symbolsToggle.isSelected()
                    );
                    passwordOutput.setText(password);
                }
                else {
                    String message = "At least one option must be selected!";
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(generateButton);
    }
}