DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

CREATE TABLE notes (
    noteId INT,
    dateCreated DATE,
    content VARCHAR(100),
    CONSTRAINT PK_noteId PRIMARY KEY (noteId));

