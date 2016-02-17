package Data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager extends SQLiteOpenHelper{

    private String name = "";
    private int version;

    public DataBaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE [Section] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [name] VARCHAR(50) NOT NULL);");

        db.execSQL("CREATE TABLE [Subsection] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [name] VARCHAR(50) NOT NULL, \n" +
                "  [id_section] INTEGER NOT NULL CONSTRAINT [id_section] REFERENCES [Section]([_id]) ON DELETE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
