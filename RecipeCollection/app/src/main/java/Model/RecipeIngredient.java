package Model;

/**
 * Created by Катарина on 18.02.2016.
 */
public class RecipeIngredient {
    public int id;
    public String name;
    public Measurement measurement;
    public double amount;

    public RecipeIngredient(int id, String name, double amount,  Measurement measurement) {
        this.amount = amount;
        this.id = id;
        this.measurement = measurement;
        this.name = name;
    }
}
