package Data;


import java.util.Collection;
import java.util.LinkedList;

public class DataManager {
    private static DataManager dataManager;

    private DataManager(){
        super();
    }

    public static DataManager getInstence(){
        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public Collection getSections(){
        return new LinkedList<String>();
    }
}
