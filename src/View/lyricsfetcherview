package lyricsfetcher;

import javax.swing.*;
import java.awt.*;

public class LyricsView {
    private JFrame frame;
    private JTextField artistTextField;
    private JTextField trackTextField;
    private JButton fetchButton;
    private JTextArea lyricsDisplay;
    private JButton goBackButton;
    private JScrollPane lyricsScrollPane;

    public LyricsView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Lyrics Fetcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("<html><font size='5'>🎤 Name of Artist:</font></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        artistTextField = new JTextField(20);
        panel.add(artistTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("<html><font size='5'>🎵 Title of the Song:</font></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        trackTextField = new JTextField(20);
        panel.add(trackTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        fetchButton = new JButton("<html><font size='5'>🎶 Get Lyrics</font></html>");
        panel.add(fetchButton, gbc);

        // Additional UI components
        gbc.gridx = 0;
        gbc.gridy = 3;
        lyricsDisplay = new JTextArea(20, 40);
        lyricsDisplay.setEditable(false);
        lyricsDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));

        lyricsScrollPane = new JScrollPane(lyricsDisplay);
        panel.add(lyricsScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        goBackButton = new JButton("<html><font size='5'>🔙 Go Back</font></html>");
        panel.add(goBackButton, gbc);

        frame.getContentPane().add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getArtistTextField() {
        return artistTextField;
    }

    public JTextField getTrackTextField() {
        return trackTextField;
    }

    public JButton getFetchButton() {
        return fetchButton;
    }

    public JTextArea getLyricsDisplay() {
        return lyricsDisplay;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JScrollPane getLyricsScrollPane() {
        return lyricsScrollPane;
    }
}
