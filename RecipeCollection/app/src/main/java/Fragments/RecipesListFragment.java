package Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bananasik.recipecollection.MainActivity;
import com.bananasik.recipecollection.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Adapters.RecipesListAdapter;
import Model.Measurement;
import Model.Recipe;
import Model.RecipeCollection;
import Model.RecipeIngredient;
import Model.RecipeIngredientCollection;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesListFragment extends Fragment {

    private RecipesListAdapter adapter;
    private FloatingActionButton fab;
    private ListView recipesList;
    private int lastFirstVisibleItem=0;

    public RecipesListFragment() {
        // Required empty public constructor
    }

    public Bitmap loadImageFromAsset(String name) {// левый метод для тестирования.
        InputStream is = null;
        Bitmap b = null;
        try {
             is = getActivity().getAssets().open(name);
             b = BitmapFactory.decodeStream(is);
        }
        catch(IOException ex) {
        }
        finally {
            if(is != null ){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setDrawerState(false); // вызов метода для блокировки левого меню

        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<RecipeIngredient> ing = new ArrayList<>();
        ing.add(new RecipeIngredient(0, "мясо", 900, new Measurement(0,"г")));
        ing.add(new RecipeIngredient(1, "сок лимона", 0.5 , new Measurement(1,"ст")));
        ing.add(new RecipeIngredient(2, "чеснок", 3, new Measurement(2,"шт")));
        ing.add(new RecipeIngredient(3, "специи", 0 , null));
        RecipeIngredientCollection ric = new RecipeIngredientCollection(ing);


        recipes.add(new Recipe(1,"Recipe 1", 3, 15, loadImageFromAsset("1.jpg"), 0, 4.5, ric ));


        ing = new ArrayList<>();
        ing.add(new RecipeIngredient(4, "куринное филе", 500, new Measurement(0,"г")));
        ing.add(new RecipeIngredient(5, "шампиньоны", 500 , new Measurement(0,"г")));
        ing.add(new RecipeIngredient(6, "картошка", 400, new Measurement(0,"г")));
        ing.add(new RecipeIngredient(7, "лук", 2, new Measurement(2,"шт")));
        ing.add(new RecipeIngredient(8, "морковь", 150, new Measurement(0,"г")));
        ing.add(new RecipeIngredient(9, "плавленный сыр", 300, new Measurement(0,"г")));
        ric = new RecipeIngredientCollection(ing);

        recipes.add(new Recipe(2,"Recipe 2", 1, 35, loadImageFromAsset("2.jpg"), 0, 2.3, ric ));

        ing = new ArrayList<>();
        ric = new RecipeIngredientCollection(ing);

        recipes.add(new Recipe(3,"Recipe 3", 0, 20, loadImageFromAsset("3.jpg"), 0, 3.1, ric ));

        ing = new ArrayList<>();
        ing.add(new RecipeIngredient(4, "яйцо", 3, new Measurement(2,"шт")));
        ing.add(new RecipeIngredient(5, "сметана", 200 , new Measurement(0,"г")));
        ing.add(new RecipeIngredient(6, "молоко", 1, new Measurement(4,"л")));
        ing.add(new RecipeIngredient(7, "мука", 4, new Measurement(5,"ст. л.")));
        ing.add(new RecipeIngredient(8, "сахар", 150, new Measurement(1,"ст")));
        ric = new RecipeIngredientCollection(ing);

        recipes.add(new Recipe(4,"Recipe 4", 1, 0, loadImageFromAsset("4.jpg"), 0, 5.0, ric ));

        ing = new ArrayList<>();
        ing.add(new RecipeIngredient(4, "рыба", 3, new Measurement(2,"шт")));
        ing.add(new RecipeIngredient(5, "соль", 0.2, new Measurement(0,"г")));
        ric = new RecipeIngredientCollection(ing);

        recipes.add(new Recipe(5,"Recipe 5", 3, 0, loadImageFromAsset("5.jpg"), 0, 3.8, ric ));

        ing = new ArrayList<>();
        ing.add(new RecipeIngredient(4, "сметана", 700, new Measurement(0,"г")));
        ing.add(new RecipeIngredient(5, "сахар", 1, new Measurement(1,"ст")));
        ric = new RecipeIngredientCollection(ing);

        recipes.add(new Recipe(6,"Recipe 6", 0, 15, null, 0, 4.7, ric ));

        ing = new ArrayList<>();
        ing.add(new RecipeIngredient(4, "желе", 2, new Measurement(2,"шт")));
        ing.add(new RecipeIngredient(5, "вода", 300, new Measurement(6,"мл")));
        ric = new RecipeIngredientCollection(ing);
        recipes.add(new Recipe(7,"Recipe 7", 1, 15, null, 0, 4.2, ric ));

        // конец создания коллекции рецептов для тестирования, подлежит уничтожению
        // после того, как будет сделана подгрузка рецептов из БД

        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        recipesList = (ListView) view.findViewById(R.id.recipes_list);
        adapter = new RecipesListAdapter(getActivity(), new RecipeCollection(recipes));
        recipesList.setAdapter(adapter);

        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_recipe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recipesList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int btn_initPosY = fab.getScrollY();
                if (firstVisibleItem > lastFirstVisibleItem) {
                    fab.animate().cancel();
                    fab.animate().translationYBy(150);
                } else {
                    if (firstVisibleItem < lastFirstVisibleItem) {
                        fab.animate().cancel();
                        fab.animate().translationY(btn_initPosY);
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

        recipesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).fragmentManager
                        .replaceFragment(R.id.fragment_container, new RecipeFragment(), "RecipeFragment", true);
            }
        });

        return  view;
    }


}
