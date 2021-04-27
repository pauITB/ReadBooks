package cat.itb.readbooks.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cat.itb.readbooks.Adapters.MyBooksAdapter;
import cat.itb.readbooks.Database.BookRepository;
import cat.itb.readbooks.Database.Database;
import cat.itb.readbooks.Models.Book;
import cat.itb.readbooks.R;
import cat.itb.readbooks.SwipeToDeleteCallBack;

public class BooksGridFragment extends Fragment implements MyBooksAdapter.ItemClickListener {

    BookRepository repository;

    MyBooksAdapter adapter;
    RecyclerView recyclerView;
    List<Book> books;
    FloatingActionButton floatingActionButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database database = new Database();
        repository = database.getRepository(this.getContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.books_grid_fragment,container,false);
        books = repository.getAll();
        if (books.isEmpty()){
            repository.insert(new Book("Caperucita","Nadie","Plan to read"));
            repository.insert(new Book("La historia interminable","Alguien","Reading"));
            books= repository.getAll();
        }
        recyclerView = v.findViewById(R.id.rvBooks);
        floatingActionButton = v.findViewById(R.id.floatingActionButton);
        int numOfColumns= 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),numOfColumns));
        adapter = new MyBooksAdapter(this.getContext(),books);
        adapter.setClickListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBack(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections directions = BooksGridFragmentDirections.actionBooksGridFragmentToAddBookFragment();
                Navigation.findNavController(v).navigate(directions);
            }
        });
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        books = repository.getAll();
        adapter.setBooks(books);
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this.getContext(),""+position,Toast.LENGTH_SHORT).show();
        NavDirections directions = BooksGridFragmentDirections.actionBooksGridFragmentToEditBookFragment(books.get(position));
        Navigation.findNavController(view).navigate(directions);

//        repository.delete(books.get(position));
    }



}
