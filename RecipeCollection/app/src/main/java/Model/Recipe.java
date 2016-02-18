package Model;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;

/**
 * Created by Катарина on 17.02.2016.
 */
public class Recipe {
    public int id;
    public String name;
    public String description;
    public int timeHours;
    public int timeMinutes;
    public Bitmap mainRecipeImage;
    public int idSubsection;
    public double rating;
    public RecipeIngredientCollection ingredients;
    public ArrayList<Image> recipeImages;

    public Recipe(int id, String name, int timeHours, int timeMinutes, Bitmap mainRecipeImage, int idSubsection, double rating, RecipeIngredientCollection ingredients) {
        this.id = id;
        this.idSubsection = idSubsection;
        this.mainRecipeImage = mainRecipeImage;
        this.name = name;
        this.timeHours = timeHours;
        this.timeMinutes = timeMinutes;
        this.ingredients = ingredients;
        this.rating = rating;
        this.description = null;
        this.recipeImages = new ArrayList<>();
    }

    public Recipe(int id, String name, int timeHours, int timeMinutes, Bitmap mainRecipeImage, int idSubsection, double rating, RecipeIngredientCollection ingredients, String description, ArrayList<Image> recipeImages) {
        this.id = id;
        this.idSubsection = idSubsection;
        this.mainRecipeImage = mainRecipeImage;
        this.name = name;
        this.timeHours = timeHours;
        this.timeMinutes = timeMinutes;
        this.ingredients = ingredients;
        this.rating = rating;
        this.description = description;
        this.recipeImages = recipeImages;
    }

    public String getTime(){
        if(timeHours == 0){
            return timeMinutes + " мин.";
        }
        if(timeMinutes == 0){
            return timeHours + " ч.";
        }
        return timeHours + " ч." + timeMinutes + " мин.";
    }

    public String getIngredientsLine(){
        String ingredientsLine = "";
        int size = ingredients.size();
        for (int i = 0; i < size ; i++) {
            ingredientsLine = ingredientsLine + ingredients.get(i).name;
            if(i != size - 1){
                ingredientsLine = ingredientsLine + ", ";
            }
        }
        return ingredientsLine;
    }
}
