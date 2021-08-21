package com.example.libraryoffice;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;

public class ViewDialog extends FirebaseAdapter{

    public DatabaseReference mDatabase;
    private EditText editTextTitle, editTextNumberId;

    String[] countries = { "Детектив", "Фантастика", "Приключения","Роман",
            "Научные книги", "Фольклор", "Юмор", "Справочная книга",
            "Поэзия", "проза", "Детская книга", "Документальная литература",
            "Деловая литература", "Религиозная литература", "Учебная книга",
            "Книги о психологии", "Хобби, досуг",
            "Зарубежная", "Pусская литература", "Техника" };


    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custon_dialog);


        mDatabase = FirebaseDatabase.getInstance().getReference("book");
        editTextNumberId = dialog.findViewById(R.id.editText_dialog_number_id);
        editTextTitle = dialog.findViewById(R.id.editText_dialog_title);

        Spinner spinner = dialog.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(dialog.getContext(), android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button dialogButton = (Button) dialog.findViewById(R.id.button_custom_add_book);
        Button dialogButtonClose = (Button)dialogButton.findViewById(R.id.button_custom_clothe);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTitle.length() == 0 && editTextNumberId.length() == 0) {
                    Toast.makeText(v.getContext(), "Заполните поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    SaveDatabase();
                    dialog.dismiss();
                }
            }
        });

        /*
        dialogButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }

    public void SaveDatabase(){
        String id = mDatabase.getKey();
        String title = editTextTitle.getText().toString();
        String number_id = editTextNumberId.getText().toString();
        String availability = "Есть в наличии";

        BookBase newBook = new BookBase(id, title, number_id, availability);
        mDatabase.push().setValue(newBook);
    }

}