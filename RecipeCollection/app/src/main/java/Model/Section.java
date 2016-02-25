package Model;

/**
 * Created by Олег on 16.02.2016.
 */
public class Section {
    public int id;
    public String name;

    public Section(String _name){
        id = 0;
        name = _name;
    }

    public Section(int _id, String _name){
        id = _id;
        name = _name;
    }

}
