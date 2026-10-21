package com.example.quanlysach;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    private EditText etTitle, etAuthor, etNote;
    private Spinner spCategory;
    private RadioGroup rgStatus;
    private RatingBar rbRating;
    private TextView tvRatingValue;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etNote = findViewById(R.id.etNote);
        spCategory = findViewById(R.id.spCategory);
        rgStatus = findViewById(R.id.rgStatus);
        rbRating = findViewById(R.id.rbRating);
        tvRatingValue = findViewById(R.id.tvRatingValue);
        btnSave = findViewById(R.id.btnSaveBook);

        // Khởi tạo Spinner Thể loại
        String[] categories = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(catAdapter);

        // Cập nhật text số sao khi kéo RatingBar
        rbRating.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            tvRatingValue.setText((int) rating + " / 5 sao");
        });

        // Bấm lưu
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String note = etNote.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Tên sách không được để trống!");
                return;
            }
            if (author.isEmpty()) {
                etAuthor.setError("Tác giả không được để trống!");
                return;
            }

            String category = spCategory.getSelectedItem().toString();

            int selectedId = rgStatus.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedId);
            String status = selectedRb != null ? selectedRb.getText().toString() : "Chưa đọc";

            float rating = rbRating.getRating();

            Book newBook = new Book(title, author, category, status, rating, note);
            BookRepository.getBooks().add(newBook);

            Toast.makeText(this, "Thêm sách thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Trở về MainActivity
        });

        // XỬ LÝ NÚT MŨI TÊN QUAY VỀ:
        TextView tvBackHeader = findViewById(R.id.tvBackHeader);
        if (tvBackHeader != null) {
            tvBackHeader.setOnClickListener(v -> finish());
        }
    }
}