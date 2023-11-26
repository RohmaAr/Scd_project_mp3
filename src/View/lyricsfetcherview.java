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
                fetchLyrics(artist, track);
            }
        });

        frame.getContentPane().add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setArtistAndTrack(String artist, String track) {
        artistTextField.setText(artist);
        trackTextField.setText(track);

        // Trigger the fetch button action programmatically
        fetchLyrics(artist, track);
    }

    public static void fetchLyrics(String artist, String track) {
        String apiKey = "9c8638720a5869a6bb9fdebf4a57fe68";

    try {
        artist = URLEncoder.encode(artist, StandardCharsets.UTF_8.toString());
        track = URLEncoder.encode(track, StandardCharsets.UTF_8.toString());

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.musixmatch.com/ws/1.1/matcher.lyrics.get?format=json&apikey="
                        + apiKey + "&q_artist=" + artist + "&q_track=" + track))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String lyricsJson = response.body();

        // Extract the lyrics from the JSON response
        String lyrics = extractLyrics(lyricsJson);

        // Display the fetched lyrics (replace with your desired logic)
        JOptionPane.showMessageDialog(null, "Lyrics:\n" + lyrics, "Fetched Lyrics", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

private static String extractLyrics(String json) {
    int startIndex = json.indexOf("\"lyrics_body\":\"");
    int endIndex = json.indexOf("\",\"script_tracking_url\"");

    if (startIndex != -1 && endIndex != -1) {
        String lyrics = json.substring(startIndex + 15, endIndex);

        // Remove the disclaimer line
        lyrics = lyrics.replaceAll("\\\\n", System.lineSeparator());
        lyrics = lyrics.replace("******* This Lyrics is NOT for Commercial use *******", "");

        return lyrics.trim();  // Trim any leading/trailing spaces
    } else {
        return "Lyrics not found.";
    }
    }
}
