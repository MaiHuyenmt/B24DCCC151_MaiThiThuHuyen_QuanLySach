package com.example.quanlysach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Book> list;

    public BookAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        Book book = list.get(position);

        TextView tvTitle = convertView.findViewById(R.id.tvBookTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvBookAuthor);
        TextView tvCat = convertView.findViewById(R.id.tvCategoryBadge);
        TextView tvStatus = convertView.findViewById(R.id.tvStatusBadge);
        TextView tvRating = convertView.findViewById(R.id.tvRatingStars);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("Tác giả: " + book.getAuthor());
        tvCat.setText(book.getCategory());
        tvStatus.setText(book.getStatus());

        // Hiển thị số sao dạng ký tự ★
        int stars = Math.round(book.getRating());
        StringBuilder starStr = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            starStr.append(i <= stars ? "★" : "☆");
        }
        tvRating.setText(starStr.toString());

        return convertView;
    }
}