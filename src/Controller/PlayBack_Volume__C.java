/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.AllSongs_M;
import Model.Player_M;
import Model.User_M;
import View.Player_V;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class PlayBack_Volume__C {
    Player_M player;
    Player_V playerScreen; 
    FrontEnd frontEnd;//for lyrics front end by fatima
        int i = 1;
    public PlayBack_Volume__C(User_M user,Player_V p,Player_M pm,FrontEnd frontEnd)
    {
        if(pm==null){
            System.out.println("pm is null");
        AllSongs_M allSongs=AllSongs_M.getAllSongs();
        player=new Player_M(allSongs);
        }
        else
        {
            player=pm;
        }
         this.frontEnd = frontEnd;//for lyrics front end by fatima
        playerScreen=p;
         playerScreen.setSong(player.getCurrentSong());
       playerScreen.nextListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playerScreen.setSong(player.next());
                playerScreen.progressReset();
                i=0;
            }
        });
        playerScreen.muteListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {player.volumeControl(0.0); }
        });
        playerScreen.playListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(player.isPaused()){
                player.play();
                playerScreen.pausePlayIconChange("Icons\\pause_dark.png");
                    SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
    @Override
    protected Void doInBackground() throws Exception {
        int dura=player.getDuration();
        playerScreen.progressSetMax(dura);
        
        for (; i <= dura && !player.isPaused(); i++) {
            try {
                Thread.sleep(1000);
                publish();
                } catch (InterruptedException ex) {
                Logger.getLogger(PlayBack_Volume__C.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
            }

            @Override
            protected void process(List<Void> chunks) {
            playerScreen.progressAdd();
            }

                };
                sw.execute();
            
                }else
                {
                    player.pause();
                playerScreen.pausePlayIconChange("Icons\\play_dark.png");
                
                }
            }
            
        });
        playerScreen.previousListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playerScreen.setSong(player.previous());
            playerScreen.progressReset();
            i=0;
            }
        });
        playerScreen.volumeDownListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { player.volumeDownControl(0.1); }
        });
        playerScreen.volumeUpListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { player.volumeUpControl(0.1);}
        });
        playerScreen.progressControll(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            JProgressBar bar=(JProgressBar)e.getSource();
            if(bar.getPercentComplete()==1)
            {
                System.out.println("Song complete");
                bar.setValue(0);
                playerScreen.setSong(player.implicitNext());
            }
            };
        
        });
        playerScreen.lyricsListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String song=player.getCurrentSong();
                Scanner scan=new Scanner(song);
                scan.useDelimiter("-|\\.mp3");
                String artist=scan.next();
                String track=scan.next();
                System.out.println(artist +" "+track);
                frontEnd.setArtistAndTrack(artist, track);//for lyrics front end by fatima
                
                 // Backend logic integrated directly from Fatima
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

            // Display lyrics or perform other actions with the lyrics as needed
            System.out.println("Lyrics: " + lyrics);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
               //till there from Fatima
            }
            
        });
        //lyrics extractor by fatima
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
    //till there
    }
}
