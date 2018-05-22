
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SwingJPanelDemo extends JFrame implements ActionListener{

    private JLabel labelSearch = new JLabel("Search on: ");
    private JLabel labelMaxResults = new JLabel("Max results: (max :10)");
    private JTextField textQuery = new JTextField(20);
    private JTextField textMaxResults = new JTextField(20);
    private JButton buttonSearch = new JButton("Search");

    public SwingJPanelDemo() {
        super("Screenshots handler");

        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelSearch, constraints);

        constraints.gridx = 1;
        newPanel.add(textQuery, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelMaxResults, constraints);

        constraints.gridx = 1;
        newPanel.add(textMaxResults, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonSearch, constraints);

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Search on Google Images"));

        // add the panel to this frame
        add(newPanel);
        buttonSearch.addActionListener(this);
        pack();
        setLocationRelativeTo(null);


    }
    public  void    actionPerformed(ActionEvent e) {
    String query = textQuery.getText();
    int maxResults = Integer.valueOf(textMaxResults.getText());
    this.setVisible(false);
    CustomGoogleSearch googleSearch = new CustomGoogleSearch(query,maxResults);

    }


    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingJPanelDemo().setVisible(true);
            }
        });
    }
}