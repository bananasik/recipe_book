package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bananasik.recipecollection.R;

/**
 * Created by Олег on 22.02.2016.
 */
public class TestAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater lInflater;

    private String[] list;

    public TestAdapter(Context _context, String[] _list){
        context = _context;
        list = _list;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = lInflater.inflate(R.layout.test_group_view, parent, false);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.text1);
        textView.setText(list[position]);
        return convertView;
    }
}
