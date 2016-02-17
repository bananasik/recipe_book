package Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;

import com.bananasik.recipecollection.R;

import java.util.ArrayList;

import Adapters.SectionsListAdapter;
import Model.Section;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment {

    private SectionsListAdapter adapter;
    private  FloatingActionButton fab;
    private int lastFirstVisibleItem=0;

    public SectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Section> group = new ArrayList<>();
        group.add(new Section(0, "Все"));
        group.add(new Section(1, "Раздел1"));
        group.add(new Section(2, "Раздел2"));
        group.add(new Section(3, "Раздел3"));
        group.add(new Section(4, "Раздел4"));
        group.add(new Section(5, "Раздел5"));
        group.add(new Section(6, "Раздел6"));
        group.add(new Section(7, "Прочее"));

        ArrayList<ArrayList<Section>> elements = new ArrayList<>();
        for(int i = 0; i < group.size(); i++){
            elements.add(new ArrayList<Section>());
        }
        elements.get(1).add(new Section(0, "Подраздел1.1"));
        elements.get(1).add(new Section(1, "Подраздел1.2"));
        elements.get(1).add(new Section(19, "Подраздел1.3"));
        elements.get(1).add(new Section(20, "Подраздел1.4"));
        elements.get(1).add(new Section(21, "Подраздел1.5"));
        elements.get(1).add(new Section(22, "Подраздел1.6"));
        elements.get(2).add(new Section(2, "Подраздел2.1"));
        elements.get(2).add(new Section(3, "Подраздел2.2"));
        elements.get(2).add(new Section(4, "Подраздел2.3"));
        elements.get(3).add(new Section(5, "Подраздел3.1"));
        elements.get(3).add(new Section(6, "Подраздел3.1"));

        elements.get(4).add(new Section(7, "Подраздел4.1"));
        elements.get(4).add(new Section(8, "Подраздел4.2"));
        elements.get(5).add(new Section(9, "Подраздел5.1"));
        elements.get(5).add(new Section(10, "Подраздел5.2"));
        elements.get(5).add(new Section(11, "Подраздел5.3"));
        elements.get(5).add(new Section(12, "Подраздел5.4"));
        elements.get(5).add(new Section(13, "Подраздел5.5"));
        elements.get(5).add(new Section(14, "Подраздел5.6"));
        elements.get(5).add(new Section(15, "Подраздел5.7"));

        elements.get(6).add(new Section(16, "Подраздел6.1"));
        elements.get(6).add(new Section(17, "Подраздел6.2"));
        elements.get(6).add(new Section(18, "Подраздел6.3"));


        adapter = new SectionsListAdapter(getActivity(), group, elements);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sections, container, false);

        final ExpandableListView expandableListView = (ExpandableListView)view.findViewById(R.id.sections_list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.v("MyLog", "group=" + groupPosition + "  child=" + childPosition);
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    if (i == groupPosition) {
                        continue;
                    }
                    if (expandableListView.isGroupExpanded(i)) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                PopupMenu popupMenu = new PopupMenu(getActivity(), fab);
                popupMenu.getMenu().add("Добавить раздел");
                popupMenu.getMenu().add("Добавить подраздел");
                popupMenu.show();
            }
        });

        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /*int btn_initPosY = fab.getScrollY();
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    fab.animate().cancel();
                    fab.animate().translationYBy(150);
                } else {
                    fab.animate().cancel();
                    fab.animate().translationY(btn_initPosY);
                }*/
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

        return view;
    }

}