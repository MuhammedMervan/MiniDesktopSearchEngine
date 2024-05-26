import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MiniDesktopSearchEngineGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
       
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Mini Desktop Search Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        frame.add(panel);
        placeComponents(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel contentLabel = new JLabel("Content of the input file:");
        contentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(contentLabel);

        JTextArea contentText = new JTextArea(8, 40);
        contentText.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane contentScrollPane = new JScrollPane(contentText);
        panel.add(contentScrollPane);

        JLabel searchMethodLabel = new JLabel("Select search method:");
        searchMethodLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(searchMethodLabel);

        JComboBox<String> searchMethodDropdown = new JComboBox<>(new String[]{"In-order", "Pre-order", "Post-order"});
        searchMethodDropdown.setFont(new Font("Arial", Font.PLAIN, 12)); 
        panel.add(searchMethodDropdown);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(51, 153, 255)); 
        searchButton.setFocusPainted(false); 
        panel.add(searchButton);

        JTextArea resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        panel.add(resultScrollPane);

        
        Map<String, Integer> ignoredWords = new HashMap<>();
        ignoredWords.put("a", 1);
        ignoredWords.put("the", 1);
        

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String content = contentText.getText();
                String selectedMethod = (String) searchMethodDropdown.getSelectedItem();
                StringBuilder result = new StringBuilder();

                
                WordFrequencyBST bst = new WordFrequencyBST(ignoredWords);
                String[] words = content.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        bst.insert(word.toLowerCase());
                    }
                }

                result.append("Content of the input file:\n").append(content).append("\n\n");

                switch (selectedMethod) {
                    case "In-order":
                        result.append("In-order traversal:\n");
                        bst.inOrderTraversal(result);
                        break;
                    case "Pre-order":
                        result.append("Pre-order traversal:\n");
                        bst.preOrderTraversal(result);
                        break;
                    case "Post-order":
                        result.append("Post-order traversal:\n");
                        bst.postOrderTraversal(result);
                        break;
                }

                resultArea.setText(result.toString());
            }
        });
    }
}
