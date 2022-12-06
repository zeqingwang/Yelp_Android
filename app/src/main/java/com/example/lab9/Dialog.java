package com.example.lab9;
import static com.example.lab9.MainActivity.EXTRA_ID;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import 	androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputEditText;

public class Dialog extends AppCompatDialogFragment{
    private TextInputEditText reserveemail,reservedate,reservetime;
    private Button cancel, submit;
//    private DialogListener listener;
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        Intent intent=getIntent();
//        id=intent.getStringExtra(EXTRA_ID);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_layout,null);
        builder.setView(view);

//               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                   }
//               })
//                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String email=reserveemail.getText().toString();
//                        String date=reservedate.getText().toString();
//                        String time=reservetime.getText().toString();
//                        Log.i("reserve",email+" "+date+" "+time);
//                        return;
//
//                    }
//                });
        reserveemail=view.findViewById(R.id.reserveemail);
        reservedate=view.findViewById(R.id.reservedate);
        reservetime=view.findViewById(R.id.reservetime);
        submit=view.findViewById(R.id.submitreserve);
        cancel=view.findViewById(R.id.cancelreserve);
        AlertDialog dialog = builder.create();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        return dialog;


//        return builder.create();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            listener=(DialogListener) context;
//        } catch (ClassCastException e) {
//
//        }
//    }
//
//    public interface DialogListener{
//        void applyTexts(String email,String date,String time);
//    }
}
