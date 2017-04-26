package catalina.dev.sqltutorial;

import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;


/**
 * Created by cata on 22.03.2017.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String [] args){
        try {
            writeConfigFile("ormlite_config.txt");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.i(TAG, "main: " + e.getMessage());
        }
    }
}
