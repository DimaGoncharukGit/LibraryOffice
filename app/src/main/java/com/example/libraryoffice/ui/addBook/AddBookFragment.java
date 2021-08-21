package com.example.libraryoffice.ui.addBook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryoffice.BookAdapter;
import com.example.libraryoffice.BookBase;
import com.example.libraryoffice.FirebaseAdapter;
import com.example.libraryoffice.MainActivity;
import com.example.libraryoffice.ViewDialog;
import com.example.libraryoffice.databinding.FragmentAddBookBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddBookFragment extends FirebaseAdapter {

    private AddBookViewModel addBookViewModel;
    private FragmentAddBookBinding binding;

    private RecyclerView recyclerView;
    private List<BookBase> result;
    private BookAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    private TextView textViewNonBook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addBookViewModel =
                new ViewModelProvider(this).get(AddBookViewModel.class);

        binding = FragmentAddBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton button_add = binding.addBookButton;
        textViewNonBook = binding.textNonBook;

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("book");

        result = new ArrayList<>();
        recyclerView = binding.bookList;
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new BookAdapter(result);
        recyclerView.setAdapter(adapter);


        //ifIsEmpty();


        updateList();

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity(), "Error");
            }
        });

        return root;
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Toast.makeText(getContext(), "Remove", Toast.LENGTH_SHORT).show();
                removeBook(item.getGroupId());
                break;
            case 1:
                Toast.makeText(getContext(), "Change", Toast.LENGTH_SHORT).show();
                changeBook(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void updateList(){
      reference.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
              result.add(snapshot.getValue(BookBase.class));
              adapter.notifyDataSetChanged();
              //ifIsEmpty();
          }

          @Override
          public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
              BookBase bookBase = snapshot.getValue(BookBase.class);

              int index = getItemIndex(bookBase);
              result.set(index, bookBase);
              adapter.notifyItemChanged(index);
          }

          @Override
          public void onChildRemoved(@NonNull DataSnapshot snapshot) {
              BookBase bookBase = snapshot.getValue(BookBase.class);

              int index = getItemIndex(bookBase);
              result.remove(index);
              adapter.notifyItemRemoved(index);

              //ifIsEmpty();
          }

          @Override
          public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

          @Override
          public void onCancelled(@NonNull DatabaseError error) {}

      });
    }


    private int getItemIndex(BookBase bookBase){
        int index = -1;

        for (int i = 0; i < result.size(); i++){
            if(result.get(i).id.equals(bookBase.id)){
                index = i;
                break;
            }
        }
        return index;
    }

    private void removeBook(int position){

        reference.child(result.get(position).id).removeValue();
    }

   private void changeBook(int position){
        BookBase bookBase = result.get(position);
        bookBase.availability = "Нет в наличии";

       Map<String, Object> bookValue = bookBase.toMap();
       Map<String, Object> newBook = new HashMap<>();

       newBook.put(bookBase.id, bookValue);
       reference.updateChildren(newBook);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public void setFirebaseDatabase(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }
}