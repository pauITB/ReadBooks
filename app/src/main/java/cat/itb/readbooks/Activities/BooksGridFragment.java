package cat.itb.readbooks.Activities;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.readbooks.Adapters.MyBooksAdapter;
import cat.itb.readbooks.Database.AppDatabase;
import cat.itb.readbooks.Database.BookDao;
import cat.itb.readbooks.Database.BookRepository;
import cat.itb.readbooks.Models.Book;
import cat.itb.readbooks.R;

public class BooksGridFragment extends Fragment implements MyBooksAdapter.ItemClickListener {
    AppDatabase db;
    BookDao dao;
    BookRepository repository;

    MyBooksAdapter adapter;
    RecyclerView recyclerView;
    List<Book> books;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(this.getContext());
        dao =db.bookDao();
        repository = new BookRepository(dao);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.books_grid_fragment,container,false);
        books = repository.getAll();
        if (books.isEmpty()){
            repository.insert(new Book("Caperucita","Nadie","plan to read"));
            repository.insert(new Book("La historia interminable","Alguien","Reading"));
            books= repository.getAll();
        }
        recyclerView = v.findViewById(R.id.rvBooks);
        int numOfColumns= 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),numOfColumns));

        adapter = new MyBooksAdapter(this.getContext(),books);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        return v;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this.getContext(),""+position,Toast.LENGTH_SHORT).show();
        NavDirections directions = BooksGridFragmentDirections.actionBooksGridFragmentToEditBookFragment(books.get(position));
        Navigation.findNavController(view).navigate(directions);

//        repository.delete(books.get(position));
//        books=repository.getAll();
//        adapter.setBooks(books);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(adapter.getBooks().get(info.position).getTitle()+"stars");

        MenuInflater inflater = getActivity().getMenuInflater();
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
