package cat.itb.readbooks.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cat.itb.readbooks.Adapters.MyBooksAdapter;
import cat.itb.readbooks.Database.AppDatabase;
import cat.itb.readbooks.Database.BookDao;
import cat.itb.readbooks.Database.BookRepository;
import cat.itb.readbooks.Models.Book;
import cat.itb.readbooks.R;

public class MainActivity extends AppCompatActivity implements MyBooksAdapter.ItemClickListener {
    AppDatabase db;
    BookDao dao;
    BookRepository repository;

    MyBooksAdapter adapter;
    RecyclerView recyclerView;
    List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db =AppDatabase.getInstance(this);
        dao = db.bookDao();
        repository =new BookRepository(dao);

        books= repository.getAll();
        if (books.isEmpty()){
            repository.insert(new Book("Caperucita","Nadie","plant to read"));
            repository.insert(new Book("La historia interminable","Alguien","Reading"));
            books= repository.getAll();
        }
        recyclerView = findViewById(R.id.rvBooks);
        int numOfColumns= 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numOfColumns));

        adapter = new MyBooksAdapter(this,books);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,""+position,Toast.LENGTH_SHORT).show();
        repository.delete(books.get(position));
        books=repository.getAll();
        adapter.setBooks(books);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(adapter.getBooks().get(info.position).getTitle()+"stars");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.stars_1:
                repository.update(this.books.get(info.position).getId(),1);
                this.books = repository.getAll();
                adapter.setBooks(books);
                return true;
            case R.id.stars_2:
                repository.update(this.books.get(info.position).getId(),2);
                this.books = repository.getAll();
                adapter.setBooks(books);
                return true;
            case R.id.stars_3:
                repository.update(this.books.get(info.position).getId(),3);
                this.books = repository.getAll();
                adapter.setBooks(books);
                return true;
            case R.id.stars_4:
                repository.update(this.books.get(info.position).getId(),4);
                this.books = repository.getAll();
                adapter.setBooks(books);
                return true;
            case R.id.stars_5:
                repository.update(this.books.get(info.position).getId(),5);
                this.books = repository.getAll();
                adapter.setBooks(books);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}