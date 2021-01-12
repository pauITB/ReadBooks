package cat.itb.readbooks.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cat.itb.readbooks.Models.Book;

@Dao
public interface BookDao {

    @Query("Select * from Book")
    public List<Book> getAll();

    @Insert
    public void insert(Book book);

    @Query("Update Book set status = :status where id = :id ")
    public void update(int id, String status);

    @Query("Update book set stars =:score where id =:id")
    public void update(int id, float score);

    @Delete
    public void delete(Book book);

}
