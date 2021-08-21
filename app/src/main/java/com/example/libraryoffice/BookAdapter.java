package com.example.libraryoffice;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private List<BookBase> list;

    public BookAdapter(List<BookBase> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookBase book = list.get(position);

        holder.title.setText(book.title);
        holder.number_id.setText(book.number_id);
        holder.status.setText(book.availability);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Delete");
                contextMenu.add(holder.getAdapterPosition(), 1, 0, "Change");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView number_id, title, status, id;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            number_id = itemView.findViewById(R.id.text_id_item);
            title = itemView.findViewById(R.id.text_title_item);
            status = itemView.findViewById(R.id.text_status_item);

        }
    }


}


/*
    TextView textViewTitle, textViewIdNumber;
    private final List<BookBase> list;

    public BookAdapter(List<BookBase> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookBase bookBase = list.get(position);

        holder.title.setText(bookBase.title);
        holder.number_id.setText(bookBase.number_id);
        holder.status.setText(bookBase.availability);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Delete");
                contextMenu.add(holder.getAdapterPosition(), 1, 0, "Change");
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Функция в стадии разработки", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


       @Override
    public int getItemCount() {
        return list.size();
    }

 */