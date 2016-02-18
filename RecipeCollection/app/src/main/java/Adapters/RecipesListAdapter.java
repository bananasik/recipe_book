package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bananasik.recipecollection.R;

import java.util.ArrayList;

import Model.Recipe;
import Model.RecipeCollection;

/**
 * Created by Катарина on 17.02.2016.
 */
public class RecipesListAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater lInflater;

    private RecipeCollection listRecipes;

    public RecipesListAdapter(Context _context, RecipeCollection _listRecipes){
        context = _context;
        listRecipes = _listRecipes;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return listRecipes.size();
    }

    @Override
    public Object getItem(int position) {
        return listRecipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listRecipes.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = lInflater.inflate(R.layout.cle_recipe, parent, false);
        }
        Recipe recipe = listRecipes.get(position);

        TextView recipeNameTextView = (TextView) convertView.findViewById(R.id.recipe_name_recipes_list);
        recipeNameTextView.setText(recipe.name);
        TextView recipeIngredientsTextView = (TextView) convertView.findViewById(R.id.recipe_ingredients_recipes_list);
        recipeIngredientsTextView.setText(recipe.getIngredientsLine());
        TextView recipeTimeTextView = (TextView) convertView.findViewById(R.id.recipe_time_recipes_list);
        recipeTimeTextView.setText(recipe.getTime());
        TextView recipeRatingTextView = (TextView) convertView.findViewById(R.id.recipe_rating_recipes_list);
        recipeRatingTextView.setText(recipe.rating + "");

        ImageView recipeImageView = (ImageView) convertView.findViewById(R.id.main_recipe_image_recipes_list);
        if(recipe.mainRecipeImage != null){
            recipeImageView.setImageBitmap(recipe.mainRecipeImage);
        }
        else{
            recipeImageView.setImageBitmap(null);
            recipeImageView.setImageResource(R.drawable.nophoto);
        }

        return convertView;
    }
}
























