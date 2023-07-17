import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private final Generator generator;

    public GUI()
    {
        super("Password Generator");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        generator = new Generator();
        addComponents();
    }

    private void addComponents(){
        JTextArea passwordOutput = new JTextArea();
        passwordOutput.setEditable(false);
        passwordOutput.setBounds(35, 50, 430, 30);
        passwordOutput.setFont(new Font("Dialog", Font.PLAIN, 22));
        add(passwordOutput);

        JLabel sliderLabel = new JLabel("Password length: 15");
        sliderLabel.setBounds(35, 130, 200, 20);
        add(sliderLabel);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 30, 15);
        slider.setBounds(20, 160, 460, 20);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderLabel.setText("Password length: "+slider.getValue());
            }
        });
        add(slider);

        JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
        lowercaseToggle.setBounds(35, 230, 215, 50);
        lowercaseToggle.setSelected(true);
        add(lowercaseToggle);

        JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
        uppercaseToggle.setBounds(250, 230, 215, 50);
        add(uppercaseToggle);

        JToggleButton numbersToggle = new JToggleButton("Numbers");
        numbersToggle.setBounds(35, 280, 215, 50);
        add(numbersToggle);

        JToggleButton symbolsToggle = new JToggleButton("Special");
        symbolsToggle.setBounds(250, 280, 215, 50);
        add(symbolsToggle);

        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(35, 380, 430, 50);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isAtLeastOne = lowercaseToggle.isSelected() ||
                        uppercaseToggle.isSelected() ||
                        numbersToggle.isSelected() ||
                        symbolsToggle.isSelected();

                if(isAtLeastOne)
                {
                    int length = slider.getValue();
                    String password = generator.generate(length,
                            lowercaseToggle.isSelected(),
                            uppercaseToggle.isSelected(),
                            numbersToggle.isSelected(),
                            symbolsToggle.isSelected()
                    );
                    passwordOutput.setText(password);
                }
                else
                {
                    String message = "At least one option must be selected!";
                    JOptionPane.showMessageDialog(null, message, "Dialog", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(generateButton);
    }
}