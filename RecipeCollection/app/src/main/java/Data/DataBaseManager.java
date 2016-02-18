package Data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Section;

public class DataBaseManager extends SQLiteOpenHelper{

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

        db.execSQL("insert into [Section] (name) values " +
                "('Раздел1')," +
                "('Раздел2')," +
                "('Раздел3')," +
                "('Раздел4')," +
                "('Раздел5')," +
                "('Разделблабла')");
        db.execSQL("insert into [Subsection] (name, id_section) values" +
                "('Подраздел1.1', 1)," +
                "('Подраздел1.2', 1)," +
                "('Подраздел1.3', 1)," +
                "('Подраздел1.4', 1)," +
                "('Подраздел1.5', 1)," +
                "('Подраздел1.6', 1)," +
                "('Подраздел2.1', 2)," +
                "('Подраздел2.2', 2)," +
                "('Подраздел2.3', 2)," +
                "('Подраздел3.1', 3)," +
                "('Подраздел3.2', 3)," +
                "('Подраздел4.1', 4)," +
                "('Подраздел4.2', 4)," +
                "('Подраздел5.1', 5)," +
                "('Подраздел5.2', 5)," +
                "('Подраздел5.3', 5)," +
                "('Подраздел5.4', 5)," +
                "('Подраздел5.5', 5)," +
                "('Подраздел5.6', 5)," +
                "('Подраздел5.7', 5)," +
                "('Подраздел6.1', 6)," +
                "('Подраздел6.2', 6)," +
                "('Подраздел6.3', 6)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Section> getSection(){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select _id, name from [Section]", null);
        ArrayList<Section> sections = new ArrayList<>();
        for (int i=0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            sections.add(new Section(cursor.getInt(0), cursor.getString(1)));
        }
        return sections;
    }

    /*public ArrayList<ArrayList<Section>> getAllSubsection(){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select _id, name from [Section]", null);
    }*/

    public ArrayList<Section> getSubsections(int idSection){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select _id, name from [Subsection] where id_section=" + idSection, null);
        ArrayList<Section> subsections = new ArrayList<>();
        for (int i=0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            subsections.add(new Section(cursor.getInt(0), cursor.getString(1)));
        }
        return subsections;
    }

}
