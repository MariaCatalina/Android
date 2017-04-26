package catalina.dev.sqltutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteDataSource dataSource;
    private EditText titleET, contentET;
    private ListView listView;


    private DatabaseHelper databaseHelper = null;

    private ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleET = (EditText) findViewById(R.id.noteTitle);
        contentET = (EditText) findViewById(R.id.noteContent);
//
//        dataSource = new NoteDataSource(this);
//        dataSource.open();

        List<Note> notes = new ArrayList<>();
        try {
            notes = getHelper().getNoteDao().queryForAll();
            Log.i(MainActivity.class.getName(), "onCreate: getAll notes" + notes);
        } catch (SQLException e) {
            Log.i(MainActivity.class.getName(), "onCreate: error!!!" + e.getMessage());
        }

        adapter = new ArrayAdapter<Note>(this, R.layout.simple_list_item, notes);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


    public void addNewNote(View view) {
        String noteTitle = titleET.getText().toString();
        String noteContent = contentET.getText().toString();
//        Note note = dataSource.createNote(noteTitle, noteContent);

        Note note = new Note(noteTitle, noteContent);

        try {
            getHelper().getNoteDao().create(note);
        } catch (SQLException e) {
            Log.i(MainActivity.class.getName(), "addNewNote: create new note");
        }


        //update ui

        adapter.add(note);
        adapter.notifyDataSetChanged();
    }

    public void findNote(View view) {
       // adapter.addAll(dataSource.getNotes("MyNote"));
        //adapter.notifyDataSetChanged();
    }

    private DatabaseHelper getHelper(){
        if (databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }

        return databaseHelper;
    }
}