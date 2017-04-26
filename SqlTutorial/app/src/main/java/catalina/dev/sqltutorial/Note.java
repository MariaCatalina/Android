package catalina.dev.sqltutorial;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cata on 20.03.2017.
 */

@DatabaseTable(tableName = "notes")
public class Note {
    @DatabaseField(generatedId = true)
    private long _id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String content;

    public Note(){

    }


    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + _id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
