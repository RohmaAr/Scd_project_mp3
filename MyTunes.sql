CREATE DATABASE MyTunes
GO
USE MyTunes
GO
CREATE TABLE SONGS (
    song_id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE USERS (
    user_id INT  IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE FAVORITE_SONGS (
    user_id INT,
    song_id INT,
    PRIMARY KEY (user_id, song_id),
    FOREIGN KEY (user_id) REFERENCES USERS(user_id),
    FOREIGN KEY (song_id) REFERENCES SONGS(song_id)
);

CREATE TABLE USER_PLAYLISTS (
    playlist_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    playlist_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE TABLE PLAYLIST_SONGS (
    playlist_id INT,
    song_id INT,
	Primary key(playlist_id,song_id),
    FOREIGN KEY (playlist_id) REFERENCES USER_PLAYLISTS(playlist_id),
    FOREIGN KEY (song_id) REFERENCES SONGS(song_id)
);