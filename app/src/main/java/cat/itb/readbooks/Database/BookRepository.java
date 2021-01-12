package cat.itb.readbooks.Database;

import java.util.List;

import cat.itb.readbooks.Models.Book;

public class BookRepository {
    BookDao bookDao;

    public BookRepository(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAll(){
        return this.bookDao.getAll();
    }

    public void insert(Book book){
        bookDao.insert(book);
    }

    public void update(int id, String status){
        bookDao.update(id, status);
    }

    public void delete(Book book){
        bookDao.delete(book);
    }

    public void update(int id, float stars){
        bookDao.update(id, stars);
    }
}
