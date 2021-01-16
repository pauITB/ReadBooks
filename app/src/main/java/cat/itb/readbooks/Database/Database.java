package cat.itb.readbooks.Database;

import android.content.Context;

public class Database {
    AppDatabase db;
    BookDao dao;
    BookRepository repository;

    public BookRepository getRepository(Context context) {
        db = AppDatabase.getInstance(context);
        dao =db.bookDao();
        repository = new BookRepository(dao);
        return repository;
    }
}
