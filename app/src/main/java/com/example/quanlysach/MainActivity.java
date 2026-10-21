package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvTotal, tvRead, tvUnread;
    private ListView lvBooks;
    private Button btnOpenAddBook;

    private ArrayList<Book> list;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotal = findViewById(R.id.tvTotalBooks);
        tvRead = findViewById(R.id.tvReadBooks);
        tvUnread = findViewById(R.id.tvUnreadBooks);
        lvBooks = findViewById(R.id.lvBooks);
        btnOpenAddBook = findViewById(R.id.btnOpenAddBook);

        list = BookRepository.getBooks();
        adapter = new BookAdapter(this, list);
        lvBooks.setAdapter(adapter);

        // Chuyển sang màn hình Chi tiết khi bấm vào cuốn sách
        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("BOOK_INDEX", position);
            startActivity(intent);
        });

        // Bấm nút thêm sách
        btnOpenAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mỗi khi quay lại MainActivity, làm mới adapter và cập nhật thống kê
        adapter.notifyDataSetChanged();
        updateStatistics();
    }

    private void updateStatistics() {
        int total = list.size();
        int readCount = 0;
        for (Book b : list) {
            if ("Đã đọc".equalsIgnoreCase(b.getStatus())) {
                readCount++;
            }
        }
        int unreadCount = total - readCount;

        tvTotal.setText(String.valueOf(total));
        tvRead.setText(String.valueOf(readCount));
        tvUnread.setText(String.valueOf(unreadCount));
    }
}