package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bananasik.recipecollection.R;

import java.util.ArrayList;

import Model.Section;


public class SectionsListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater lInflater;

    private ArrayList<Section> listGroups;
    private ArrayList<ArrayList<Section>> listElements;

    public SectionsListAdapter(Context _context, ArrayList<Section> _listGroups, ArrayList<ArrayList<Section>> _listElements){
        context = _context;
        listGroups = _listGroups;
        listElements = _listElements;

        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listElements.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = lInflater.inflate(R.layout.cle_section, parent, false);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.textViewSection);
        ImageView indicantor = (ImageView)convertView.findViewById(R.id.imageViewIndicator);

        textView.setText(listGroups.get(groupPosition).name);

        if(groupPosition == 0 || groupPosition == listGroups.size()-1){
            indicantor.setVisibility(View.INVISIBLE);
        }
        else{
            indicantor.setVisibility(View.VISIBLE);
        }

        if(groupPosition != 0 && groupPosition != listGroups.size()-1) {
            if (isExpanded) {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundChoose));
                ((ImageView) convertView.findViewById(R.id.imageViewIndicator)).setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            } else {
                convertView.setBackgroundColor(0x0);
                ((ImageView) convertView.findViewById(R.id.imageViewIndicator)).setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
            }
        }
        ImageView buttonMore = (ImageView)convertView.findViewById(R.id.image_button_more);
        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonMore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setAlpha(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setAlpha(0.5f);
                        break;
                }
                return false;
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = lInflater.inflate(R.layout.cle_subsection, parent, false);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.textViewSubsection);
        textView.setText(listElements.get(groupPosition).get(childPosition).name);
        ImageView buttonMore = (ImageView)convertView.findViewById(R.id.image_button_more);
        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonMore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        v.setAlpha(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setAlpha(0.5f);
                        break;
                }
                return false;
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public ArrayList<Section> getListGroups(){
        return listGroups;
    }

    public  ArrayList<Section> getGroupChildList(int index){
        return listElements.get(index);
    }
}
