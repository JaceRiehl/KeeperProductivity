package com.example.jaceriehl.keeperproductivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewListOverlay extends AppCompatDialogFragment {
    //final Button checkBox;
    private EditText enterBoardName;
    private EnterListNameListener listener;
    //final Button cancelBox;
    //final Intent returnIntent = new Intent();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_new_list, null);

        builder.setView(view).setTitle("New List").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = enterBoardName.getText().toString().trim();
                listener.passText(name);
            }
        });

        //checkBox = view.findViewById(R.id.newListCheck);
        enterBoardName = view.findViewById(R.id.enterBoardName);
        //Button cancelBox = view.findViewById(R.id.closeNewList);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (EnterListNameListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "Must Implement EnterListNameListener");
        }
    }

    public interface EnterListNameListener{
        void passText(String name);
    }
}
