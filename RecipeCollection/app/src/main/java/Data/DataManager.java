package Data;


import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import Model.Section;

public class DataManager {
    private static DataManager dataManager;
    public static DataManager getInstence(){
        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }


    private DataBaseManager dataBaseManager;

    private DataManager(){
        dataBaseManager = null;
    }


    public DataManager init(Context c){
        dataBaseManager = new DataBaseManager(c, "RecipeCollection.db", null, 1);
        return this;
    }

    public ArrayList<Section> getSections(){
        return dataBaseManager.getSection();
    }

    public ArrayList<Section> getSubsections(int idSection){
        return dataBaseManager.getSubsections(idSection);
    }
}
