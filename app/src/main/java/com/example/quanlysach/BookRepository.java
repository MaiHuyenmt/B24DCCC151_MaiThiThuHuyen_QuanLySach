package com.example.quanlysach;

import java.util.ArrayList;

public class BookRepository {
    private static ArrayList<Book> bookList;

    public static ArrayList<Book> getBooks() {
        if (bookList == null) {
            bookList = new ArrayList<>();
            // Dữ liệu mẫu ban đầu theo đề bài
            bookList.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4.0f, "Sách dành cho người mới bắt đầu"));
            bookList.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5.0f, "Cuốn sách kinh điển"));
            bookList.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0.0f, "Tác phẩm tuổi thơ"));
        }
        return bookList;
    }
}