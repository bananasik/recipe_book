package Model;

import java.util.ArrayList;

/**
 * Created by Катарина on 18.02.2016.
 */
public class RecipeIngredientCollection {
    private ArrayList<RecipeIngredient> ingredients;

    public RecipeIngredientCollection(ArrayList<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int size(){
        return ingredients.size();
    }

    public RecipeIngredientCollection() {
        ingredients = new ArrayList<>();
    }

    public void add(RecipeIngredient recipeIngredient){
        ingredients.add(recipeIngredient);
    }

    public RecipeIngredient get(int position){
        return ingredients.get(position);
    }

    public RecipeIngredient findByID(int id){
        int size = ingredients.size();
        for (int i = 0; i < size; i++) {
            if(ingredients.get(i).id == id){
                return ingredients.get(i);
            }
        }
        return null;
    }
}
