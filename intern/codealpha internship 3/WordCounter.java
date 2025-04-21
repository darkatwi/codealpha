import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounter extends JFrame {
    private JTextArea textArea;
    private JButton countButton;
    private JLabel resultLabel;

    public WordCounter() {
        setTitle("Word Counter");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        countButton = new JButton("Count Words");
        countButton.setFont(new Font("Arial", Font.BOLD, 16));
        countButton.setBackground(new Color(45, 140, 240));
        countButton.setForeground(Color.WHITE);
        countButton.setFocusPainted(false);
        countButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        countButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(countButton);
        add(buttonPanel, BorderLayout.SOUTH);

        resultLabel = new JLabel("Word Count: 0");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setForeground(new Color(45, 140, 240));
        add(resultLabel, BorderLayout.NORTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void countWords() {
        String text = textArea.getText().trim();
        if (text.isEmpty()) {
            resultLabel.setText("Word Count: 0");
            return;
        }
        String[] words = text.split("\\s+");
        resultLabel.setText("Word Count: " + words.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordCounter());
    }
}
