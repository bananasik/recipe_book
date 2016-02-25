package Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bananasik.recipecollection.R;

import Model.Section;

/**
 * Created by Катарина on 24.02.2016.
 */
public class AddSectionsDialogFragment extends DialogFragment {

    private View dialogView;
    private AddSectionButtonClickedDialogListener listener;
    private EditText inputText;
    private Spinner sectionsListSpinner;
    private int dialogType;


    public AddSectionsDialogFragment() {}

    public void setListener(AddSectionButtonClickedDialogListener _listener){
        listener = _listener;
    }

    public interface AddSectionButtonClickedDialogListener {
        void onAddSection(Section section);
        void onAddSubsection(Section subsection, int parentIndex);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialogType = getArguments().getInt("ItemId");
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setNegativeButton("Отменить", null)
                .setPositiveButton("Добавить", null);

        switch (dialogType){
            case 1:
                dialogView = inflater.inflate(R.layout.add_section_dialog_fragment,null);
                inputText = (EditText)dialogView.findViewById(R.id.new_section_name_editText);
                break;
            case 2:
                dialogView = inflater.inflate(R.layout.add_subsection_dialog_fragment,null);
                inputText = (EditText)dialogView.findViewById(R.id.new_subsection_name_editText);
                sectionsListSpinner = (Spinner) dialogView.findViewById(R.id.sections_list_spinner_dialog);
                sectionsListSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getArguments().getStringArrayList("SectionsList")));
                break;
        }
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = inputText.getText().toString();
                        if(name.isEmpty()){
                            Toast.makeText(getActivity(), "Название не может быть пустым!", Toast.LENGTH_LONG).show();
                            return;
                        }

                        switch (dialogType){
                            case 1:
                                for(String groupName : (getArguments().getStringArrayList("SectionsList"))){
                                    if(name.contentEquals(groupName)){
                                        Toast.makeText(getActivity(), "Раздел с таким названием уже существует!", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                Section s = new Section(name);
                                listener.onAddSection(s);
                                break;
                            case 2:
                                int position = sectionsListSpinner.getSelectedItemPosition();
                                for(Section subsection : ((SectionsFragment)listener).adapter.getGroupChildList(position + 1)){
                                    if(name.contentEquals(subsection.name)){
                                        Toast.makeText(getActivity(), "В данном разделе уже существует подраздел с таким названием!", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                Section ss = new Section(name);
                                listener.onAddSubsection(ss, position + 1);
                                break;

                        }
                        dialog.dismiss();
                    }

                });


            }
        });


       return  dialog;
    }
}













