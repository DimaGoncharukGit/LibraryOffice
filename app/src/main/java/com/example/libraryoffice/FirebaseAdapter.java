package com.example.libraryoffice;


import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAdapter extends Fragment {

    public final String BOOK_KEY = "books";

    DatabaseReference firebaseDatabase;
    DatabaseReference reference;

    /*
    public DatabaseReference getFireBase(){
        reference = firebaseDatabase.getReference(BOOK_KEY);
        return reference;
    }
    //FirebaseDatabase.getInstance().getReference(BOOK_KEY);


    public FirebaseDatabase getInstanceBase(){
        firebaseDatabase = FirebaseDatabase.getInstance().getReference(BOOK_KEY);
        return firebaseDatabase;
    }*/
}

/*

class Class1 {
    private String id = 1;

    public String getId() {
        return id;
    }

}

class Class2 {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

}

    public static void main(String args[]) {
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();

        String id = class1.getId();
        class2.setId(id);
    }
*/