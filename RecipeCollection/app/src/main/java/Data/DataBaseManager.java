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

        db.execSQL("CREATE TABLE [Recipe] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [name] VARCHAR(50) NOT NULL, \n" +
                "  [description] TEXT, \n" +
                "  [rating] DECIMAL NOT NULL DEFAULT 0, \n" +
                "  [time] TIME, \n" +
                "  [image] BLOB, \n" +
                "  [id_subsection] INTEGER CONSTRAINT [id_subsectin] REFERENCES [Subsection]([_id]) ON DELETE SET NULL, \n" +
                "  CONSTRAINT [rating] CHECK(rating > 0 and rating < 5));");

        db.execSQL("CREATE TABLE [Ingredient] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [name] VARCHAR(50) NOT NULL);");

        db.execSQL("CREATE TABLE [Measurement] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [name] VARCHAR(10) NOT NULL);");

        db.execSQL("CREATE TABLE [Ingredient_Measurement] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [id_ingredient] INTEGER NOT NULL CONSTRAINT [id_ingredient] REFERENCES [Ingredient]([_id]) ON DELETE CASCADE, \n" +
                "  [id_measurement] INTEGER NOT NULL CONSTRAINT [id_measurement] REFERENCES [Measurement]([_id]) ON DELETE CASCADE);");

        db.execSQL("CREATE TABLE [Ingredient_Recipe] (\n" +
                "  [_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  [id_ingredient] INTEGER NOT NULL CONSTRAINT [id_ingredient] REFERENCES [Ingredient]([_id]) ON DELETE NO ACTION, \n" +
                "  [id_recipe] INTEGER NOT NULL CONSTRAINT [id_recipe] REFERENCES [Recipe]([_id]) ON DELETE CASCADE, \n" +
                "  [id_measurement] INTEGER NOT NULL CONSTRAINT [id_measurement] REFERENCES [Measurement]([_id]) ON DELETE NO ACTION, \n" +
                "  [count] DECIMAL NOT NULL, \n" +
                "  [group] VARCHAR(30));");

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

        db.execSQL("insert into [Ingredient] (name) values " +
                "('Мясо')," +
                "('Сок лимона')," +
                "('Чеснок')," +
                "('Специи')," +
                "('Куринное филе')," +
                "('Шампиньоны')," +
                "('Картошка')," +
                "('Лук')," +
                "('Морковь')," +
                "('Яйцо')," +
                "('Сметана')," +
                "('Молоко')," +
                "('Мука')," +
                "('Сахар')," +
                "('Рыба')," +
                "('Соль')," +
                "('Желе')," +
                "('Вода'),");

        db.close();

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
        readableDatabase.close();
        return sections;
    }

    public ArrayList<Section> getSubsections(int idSection){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select _id, name from [Subsection] where id_section=" + idSection, null);
        ArrayList<Section> subsections = new ArrayList<>();
        for (int i=0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            subsections.add(new Section(cursor.getInt(0), cursor.getString(1)));
        }
        readableDatabase.close();
        return subsections;
    }

    public void addSection(Section section){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String query = "insert into [Section] (name) values ('" + section.name + "')";
        writableDatabase.execSQL(query);
        Cursor cursor = writableDatabase.rawQuery("last_insert_rowid()", null);
        cursor.moveToNext();
        section.id = cursor.getInt(0);
        writableDatabase.close();
    }

    public void addSubsection(Section subsection, int idSection){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String query = "insert into [Subsection] (name, id_section) values ('" + subsection.name + "', " + idSection + ")";
        writableDatabase.execSQL(query);
        Cursor cursor = writableDatabase.rawQuery("last_insert_rowid()", null);
        cursor.moveToNext();
        subsection.id = cursor.getInt(0);
        writableDatabase.close();
    }
}
