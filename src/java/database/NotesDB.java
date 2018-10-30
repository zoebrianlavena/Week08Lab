package database;

import models.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class NotesDB {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
    }

    public int update(Note note) throws NotesDBException {
        
    }

    public List<Note> getAll() throws NotesDBException, ParseException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM notes;");
            rs = ps.executeQuery();
            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                notes.add(new Note(rs.getInt("noteId"),
                                    rs.getDate("dateCreated"),
                                    rs.getString("content")));
            }
            pool.freeConnection(connection);
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    public Note getNote(int noteId) throws NotesDBException, ParseException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String selectSQL = "SELECT * FROM notes WHERE noteId = ?";
        
        DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(selectSQL);
            ps.setString(1, String.format("%s", noteId));
            rs = ps.executeQuery();

            Note note = null;
            while (rs.next()) {
                note = new Note(rs.getInt("noteId"),
                                rs.getDate("dateCreated"),
                                rs.getString("content"));
            }
            pool.freeConnection(connection);
            return note;
        } catch (SQLException ex) {
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Users");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    public int delete(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "DELETE FROM notes WHERE noteId = ?";
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, String.format("%s", note.getNoteId()));
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting User");
        } finally {
            pool.freeConnection(connection);
        }
    }
}
