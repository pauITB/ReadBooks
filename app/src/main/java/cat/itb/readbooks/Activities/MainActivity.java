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

public class MainActivity extends AppCompatActivity {
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
    }
}