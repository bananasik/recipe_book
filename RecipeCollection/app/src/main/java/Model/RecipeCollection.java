package Model;

import java.util.ArrayList;

/**
 * Created by Катарина on 18.02.2016.
 */
public class RecipeCollection {
    private ArrayList<Recipe> recipes;

    public RecipeCollection(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public int size(){
        return recipes.size();
    }

    public void add(Recipe recipe){
        recipes.add(recipe);
    }

    public Recipe get(int position){
        return recipes.get(position);
    }

    public Recipe findById(int id){
        int size = recipes.size();
        for (int i = 0; i < size ; i++) {
            if(recipes.get(i).id == id){
                return recipes.get(i);
            }
        }
        return null;
    }
}
