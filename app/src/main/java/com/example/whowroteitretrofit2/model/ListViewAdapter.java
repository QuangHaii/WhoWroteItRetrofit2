package com.example.whowroteitretrofit2.model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.whowroteitretrofit2.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private final List<Item> bookList;
    public ListViewAdapter(List<Item> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(), R.layout.book_view,null);
        } else view = convertView;
        Item bookInfo = (Item) getItem(position);
        ((TextView) view.findViewById(R.id.title)).setText(bookInfo.getVolumeInfo().getTitle());
        if(bookInfo.getVolumeInfo().getAuthors() != null){
            ((TextView) view.findViewById(R.id.authors)).setText(bookInfo.getVolumeInfo().getAuthors().toString());
        } else {
            ((TextView) view.findViewById(R.id.authors)).setText("Author not found");
        }
        return view;
    }
}
