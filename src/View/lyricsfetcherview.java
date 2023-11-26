package lyricsfetcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontEnd {
    private JTextField artistTextField;
    private JTextField trackTextField;

    public FrontEnd() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Lyrics Fetcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Set margins

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("<html><font size='5'>🎤 Name of Artist:</font></html>"), gbc); // Microphone icon added

        gbc.gridx = 1;
        gbc.gridy = 0;
        artistTextField = new JTextField(20);
        panel.add(artistTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("<html><font size='5'>🎵 Title of the Song:</font></html>"), gbc); // Musical note icon added

        gbc.gridx = 1;
        gbc.gridy = 1;
        trackTextField = new JTextField(20);
        panel.add(trackTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Set the button to span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        JButton fetchButton = new JButton("<html><font size='5'>🎶 Get Lyrics</font></html>"); // Music note icon added
        panel.add(fetchButton, gbc);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artist = artistTextField.getText();
                String track = trackTextField.getText();

                // Call the back end to fetch lyrics
                BackEnd.fetchLyrics(artist, track);
            }
        });

        frame.getContentPane().add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
