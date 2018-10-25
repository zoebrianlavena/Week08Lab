package services;

import database.UserDB;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Note;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public Note get(int noteId) throws Exception {
        return userDB.getNote(noteId);
    }

    public List<Note> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(int noteId, String content) throws Exception {
        Note note = new Note(noteId, (java.sql.Date) new Date(), content);
        return userDB.update(note);
    }

    public int delete(int noteId) throws Exception {
        Note deletednote = userDB.getNote(noteId);
        return userDB.delete(deletednote);
    }

    public int insert(int noteId, Date date, String content) throws Exception {
        Note note = new Note(noteId, (java.sql.Date) date, content);
        return userDB.insert(note);
    }
}
