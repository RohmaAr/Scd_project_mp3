myTunes Java Swing Project
Overview
Welcome to myTunes, a Java Swing project for managing and playing your music collection! This README provides instructions on how to download, set up, and run the project.

Prerequisites
Make sure you have the following prerequisites installed on your machine:

Java Development Kit (JDK)
Eclipse, IntelliJ IDEA, or any Java IDE of your choice
Setup Instructions
Download the Project:

Clone or download the myTunes project from the repository.
Add Jar Files:

Navigate to the lib folder in the project.
Add the necessary JAR files (e.g., javax.swing.jar, httpclient, jlayer.jar, etc.) to your project's build path.
Model Configuration:

Locate the AllSounds_M file in the model folder.
Set the data member rootDir to the path of the folder containing your music collection.
MP3 File Naming Convention:

For lyrics feature support, ensure your MP3 files follow the structure: artist - track.mp3.
Using IntelliJ IDEA
If you prefer IntelliJ IDEA, follow these additional steps:

Open the Project in IntelliJ IDEA:

Open IntelliJ IDEA.
Choose "Import Project".
Configure Project Settings:

Make sure the JDK is configured in the project settings.
Run the Main Class:

Locate the main class, for example, Main.java.
Right-click on the main class and select "Run."
Running the Project
Open the Project in Eclipse or IntelliJ:

Open your preferred IDE.
Import or open the myTunes project.
Run the Main Class:

Locate the main class, for example, MyTunesMain.java.
Right-click on the main class and select "Run."
Explore myTunes:

The myTunes application window should appear.
Use the features to manage and play your music collection.
Features
Music Player:

Play, pause, skip, and control the volume, add to favourites , song detection.
Lyrics Feature:

View lyrics by selecting a song with the proper naming convention (artist - track.mp3).
Troubleshooting
AudD API Service
If you encounter issues with the AudD API service, make sure:

Your API key is correctly configured in the project.
Your internet connection is stable.
Musixmatch Lyrics Fetcher API
For Musixmatch API issues:

Check your API key and ensure it's properly configured.
Confirm your internet connection is stable.
Dependencies and Jar Files
If you face issues related to JLayer, HTTP connection, or other dependencies:

Ensure the JAR files in the lib folder are correctly added to your project's build path.
Double-check the versions of the dependencies to match the project requirements.
Contributing
If you'd like to contribute to myTunes, feel free to submit a pull request.