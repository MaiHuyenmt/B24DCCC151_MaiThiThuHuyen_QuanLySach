package com.example.quanlysach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvAuthorCat, tvStatus, tvNote;
    private RatingBar rbRating;
    private Button btnMarkRead, btnBack, btnDelete;
    private int bookIndex = -1;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvAuthorCat = findViewById(R.id.tvDetailAuthorCat);
        tvStatus = findViewById(R.id.tvDetailStatus);
        tvNote = findViewById(R.id.tvDetailNote);
        rbRating = findViewById(R.id.rbDetailRating);

        btnMarkRead = findViewById(R.id.btnMarkAsRead);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDeleteBook);

        bookIndex = getIntent().getIntExtra("BOOK_INDEX", -1);
        if (bookIndex != -1 && bookIndex < BookRepository.getBooks().size()) {
            currentBook = BookRepository.getBooks().get(bookIndex);

            // Gán dữ liệu lên View
            tvTitle.setText(currentBook.getTitle());
            tvAuthorCat.setText(currentBook.getAuthor() + "  ·  " + currentBook.getCategory());
            tvStatus.setText(currentBook.getStatus());
            rbRating.setRating(currentBook.getRating());
            tvNote.setText(currentBook.getNote().isEmpty() ? "Không có ghi chú" : currentBook.getNote());

            // Nếu đã là "Đã đọc" thì vô hiệu hoá hoặc ẩn nút
            if ("Đã đọc".equalsIgnoreCase(currentBook.getStatus())) {
                btnMarkRead.setEnabled(false);
                btnMarkRead.setAlpha(0.5f);
            }

            TextView tvDetailBackHeader = findViewById(R.id.tvDetailBackHeader);
            tvDetailBackHeader.setOnClickListener(v -> finish());
        }

        // Thay đổi đánh giá sao trực tiếp
        rbRating.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser && currentBook != null) {
                currentBook.setRating(rating);
                Toast.makeText(this, "Đã cập nhật đánh giá!", Toast.LENGTH_SHORT).show();
            }
        });

        // Đánh dấu đã đọc
        btnMarkRead.setOnClickListener(v -> {
            if (currentBook != null) {
                currentBook.setStatus("Đã đọc");
                tvStatus.setText("Đã đọc");
                btnMarkRead.setEnabled(false);
                btnMarkRead.setAlpha(0.5f);
                Toast.makeText(this, "Đã đổi trạng thái sang 'Đã đọc'", Toast.LENGTH_SHORT).show();
            }
        });

        // Xóa sách
        btnDelete.setOnClickListener(v -> {
            if (bookIndex != -1 && bookIndex < BookRepository.getBooks().size()) {
                BookRepository.getBooks().remove(bookIndex);
                Toast.makeText(this, "Đã xóa sách khỏi thư viện!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Quay lại
        btnBack.setOnClickListener(v -> finish());
    }
}