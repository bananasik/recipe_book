package Fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;

import com.bananasik.recipecollection.MainActivity;
import com.bananasik.recipecollection.R;

import java.util.ArrayList;

import Adapters.SectionsListAdapter;
import Data.DataManager;
import Model.Section;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment implements ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupExpandListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener, AddSectionsDialogFragment.AddSectionButtonClickedDialogListener {

    SectionsListAdapter adapter;
    private FloatingActionButton fab;
    private ExpandableListView expandableListView;
    private int lastFirstVisibleItem=0;

    public SectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*ArrayList<Section> group = new ArrayList<>();
        group.add(new Section(0, "Все"));
        group.add(new Section(1, "Раздел1"));
        group.add(new Section(2, "Раздел2"));
        group.add(new Section(3, "Раздел3"));
        group.add(new Section(4, "Раздел4"));
        group.add(new Section(5, "Раздел5"));
        group.add(new Section(6, "Раздел6"));
        group.add(new Section(7, "Прочее"));*/

        DataManager.getInstence().init(getActivity());
        ArrayList<Section> sections = DataManager.getInstence().getSections();

        ArrayList<ArrayList<Section>> subsections = new ArrayList<>();
        for(int i = 0; i < sections.size(); i++){
            subsections.add(DataManager.getInstence().getSubsections(sections.get(i).id));
        }

        sections.add(0, new Section(-1, "Все"));
        sections.add(new Section(-1, "Прочее"));
        subsections.add(0, new ArrayList<Section>());
        subsections.add(new ArrayList<Section>());

        /*elements.get(1).add(new Section(0, "Подраздел1.1"));
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
        elements.get(6).add(new Section(18, "Подраздел6.3"));*/

        adapter = new SectionsListAdapter(getActivity(), sections, subsections);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sections, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.sections_list);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_section);

        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupExpandListener(this);

        fab.setOnClickListener(this);

        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

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

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        ((MainActivity) getActivity()).fragmentManager
                .replaceFragment(R.id.fragment_container, new RecipesListFragment(), "RecipesListFragment", true);
        Log.v("MyLog", "group=" + groupPosition + "  child=" + childPosition);
        return false;
    }

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab_add_section:
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                PopupMenu popupMenu = new PopupMenu(getActivity(), fab);
                popupMenu.getMenu().add(Menu.NONE, 1, Menu.NONE, "Добавить раздел");
                popupMenu.getMenu().add(Menu.NONE, 2, Menu.NONE,"Добавить подраздел");
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.show();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Bundle arguments = new Bundle();
        arguments.putInt("ItemId", item.getItemId());
        ArrayList<String> groupsNamesList = new ArrayList<>();
        for (Section s: adapter.getListGroups()) {
            groupsNamesList.add(s.name);
        }
        groupsNamesList.remove(groupsNamesList.size()-1);
        groupsNamesList.remove(0);
        arguments.putStringArrayList("SectionsList", groupsNamesList);
        AddSectionsDialogFragment dialog = new AddSectionsDialogFragment();
        dialog.setArguments(arguments);
        dialog.setListener(SectionsFragment.this);
        dialog.show(getActivity().getFragmentManager(), "AddSectionsDialogFragment");
        return false;
    }

    @Override
    public void onAddSection(Section section) {
        DataManager.getInstence().addSection(section);
        adapter.getListGroups().add(adapter.getGroupCount() - 1, section);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAddSubsection(Section subsection, int parentIndex) {
        DataManager.getInstence().addSubsection(subsection, adapter.getListGroups().get(parentIndex).id);
        adapter.getGroupChildList(parentIndex).add(subsection);
        adapter.notifyDataSetChanged();
    }
}
