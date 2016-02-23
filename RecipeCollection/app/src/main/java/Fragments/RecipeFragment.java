package Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bananasik.recipecollection.R;

import Adapters.TestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {


    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_recipe, container, false);

        ListView listView = (ListView)view.findViewById(R.id.recipeF_ingredients_listView);

        String[] mas = { "ingredient1", "ingredient2", "ingredient3", "ingredient4", "ingredient5", "ingredient6","ingredient7",
                "ingredient8", "ingredient9"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.test_group_view, mas);
        TestAdapter adapter = new TestAdapter(getActivity(), mas);
        listView.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(listView);

        return view;
    }

    public void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

}
