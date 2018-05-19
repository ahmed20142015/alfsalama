package Model;

import android.database.Cursor;

import com.marvel.android.a1000salama.DataBase.Contract;

/**
 * Created by ahmed on 26/01/18.
 */

public class SystemMessage {
    String id ;
    String Message;
    private static final String[] MOVIE_COLUMNS = {
            Contract. alfsalamaEntry._ID,
            Contract.alfsalamaEntry.COLUMN_SystemMessages_ID,
            Contract.alfsalamaEntry.COLUMN_SystemMessages_message,

    };
    public  static final int COL_ID = 0;
    public static final int COL_System_Message_ID = 1;
    public static final int COL_System_Message = 2;
    public SystemMessage(SystemMessage movie) {
        this.id = movie.getId();
        this.Message = movie.getMessage();


    }

    public SystemMessage(Cursor cursor) {
        this.id = cursor.getString(SystemMessage.COL_System_Message_ID);
        this.Message = cursor.getString(SystemMessage.COL_System_Message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
