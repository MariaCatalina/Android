package catalina.dev.sqltutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cata on 20.03.2017.
 */

public class NoteDataSource {
//
//    private SQLiteDatabase database;
//    private MySQLiteHelper dbHelper;
//
//    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
//                                    MySQLiteHelper.COLUMN_TITLE,
//                                    MySQLiteHelper.COLUMN_CONTENT
//                                    };
//
//    public NoteDataSource(Context context){
//        dbHelper = new MySQLiteHelper(context);
//    }
//
//    public void open() throws SQLException{
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void close(){
//        dbHelper.close();
//    }
//
//    public Note createNote(String title, String content){
//
//        ContentValues values = new ContentValues();
//
//        values.put(MySQLiteHelper.COLUMN_TITLE, title);
//        values.put(MySQLiteHelper.COLUMN_CONTENT, content);
//
//        long insertId = database.insert(MySQLiteHelper.TABLE_NOTES, null, values);
//
//        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
//        cursor.moveToFirst();
//
//        Note note = cursorToComment(cursor);
//        cursor.close();
//        return note;
//    }
//
//    public List<Note> getAllNotes(){
//        List<Note> notes = new ArrayList<>();
//
//        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES, allColumns, null, null, null, null, null);
//        cursor.moveToFirst();
//
//        while(!cursor.isAfterLast()) {
//            notes.add(cursorToComment(cursor));
//            cursor.moveToNext();
//        }
//
//        cursor.close();
//
//        return notes;
//    }
//
//    public List<Note> getNotes(String title){
//        List<Note> notes = new ArrayList<>();
//
//        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES, allColumns, MySQLiteHelper.COLUMN_TITLE + " = ?" , new String[]{title}, null, null, null);
//        cursor.moveToFirst();
//
//        while(!cursor.isAfterLast()) {
//            notes.add(cursorToComment(cursor));
//            cursor.moveToNext();
//        }
//
//        cursor.close();
//
//        return notes;
//    }
//
//    private Note cursorToComment(Cursor cursor){
//        return new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
//
//    }
}
