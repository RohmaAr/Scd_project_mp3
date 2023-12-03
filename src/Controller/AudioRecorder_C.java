/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.AudioRecorder_V;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AudioRecorder_C {
    private AudioRecorder_V view;
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private Clip audioClip;

    private static final String API_KEY = "acae7e56a789a5dca411f79bb94164c5"; // Replace with your Audd API key

    public AudioRecorder_C(AudioRecorder_V view) throws LineUnavailableException {
        this.view = view;
        audioFormat = getAudioFormat();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;

        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public void startRecording() {
        view.setStatusLabelText("Status: Recording");
        captureAudio();
    }

    public void stopRecording() {
        view.setStatusLabelText("Status: Idle");

        targetDataLine.stop();
        targetDataLine.close();

        // Call the method to send the recorded audio file to Audd API for recognition
        view.setResult(sendAudioForRecognition("recordedAudio.wav"));
    }

    private void captureAudio() {
        try {
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            Thread captureThread = new Thread(new CaptureThread());
            captureThread.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    class CaptureThread implements Runnable {
        @Override
        public void run() {
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            File audioFile = new File("recordedAudio.wav");

            try {
                AudioSystem.write(new AudioInputStream(targetDataLine), fileType, audioFile);
                view.setStatusLabelText("Status: Recording complete");
            } catch (IOException e) {
                e.printStackTrace();
                view.setStatusLabelText("Status: Recording failed");
            }
        }
    }

    public String sendAudioForRecognition(String audioFilePath) {
        try {
            String result = recognizeSong(audioFilePath);
            view.setStatusLabelText("Status: Recognition complete");

            // Use HTML formatting for the JOptionPane message with custom fonts and music icons
            String formattedMessage = "<html><body style='font-family: Arial, sans-serif;'>" +
                    "<h2>Song Recognition Result</h2>" +
                    "<h3 style='width:300px;'>&#127925; Song Name: " + result + "</h3></body></html>"; // &#127926; is the HTML entity for a music note

          //  JOptionPane.showMessageDialog(null, "Song recognition result:\n" + formattedMessage);
          return formattedMessage;
        } catch (IOException ex) {
            ex.printStackTrace();
            view.setStatusLabelText("Status: Recognition failed");
            
            // Use HTML formatting for the error message in JOptionPane
            String errorMessage = "<html><body style='color: red; font-family: Arial, sans-serif;'>Error: " + ex.getMessage() + "</body></html>";
           JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
         return " ";
        }
    }

    private String recognizeSong(String audioFilePath) throws IOException {
        String apiUrl = "https://api.audd.io/recognize";

        // Create the request URL
        URL url = new URL(apiUrl);

        // Open connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set request headers
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=boundary");

        // Create the request body
        try (OutputStream os = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {

            // Write api_token parameter
            writer.append("--boundary\r\n");
            writer.append("Content-Disposition: form-data; name=\"api_token\"\r\n\r\n");
            writer.append(API_KEY).append("\r\n");

            // Write file parameter
            writer.append("--boundary\r\n");
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(new File(audioFilePath).getName()).append("\"\r\n");
            writer.append("Content-Type: audio/wav\r\n\r\n");
            writer.flush();

            // Write the audio file
            try (FileInputStream fis = new FileInputStream(audioFilePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }

            // Write the end of the boundary
            writer.append("\r\n--boundary--\r\n");
        }

        // Get response code
        int responseCode = connection.getResponseCode();

        // Read response
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }

    public void playRecordedAudio() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("recordedAudio.wav"));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            audioClip.start();
            view.setStatusLabelText("Status: Playing recorded audio");
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            view.setStatusLabelText("Status: Playback failed");
        }
    }
}

