package cat.itb.readbooks.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cat.itb.readbooks.Database.AppDatabase;
import cat.itb.readbooks.Database.BookDao;
import cat.itb.readbooks.Database.BookRepository;
import cat.itb.readbooks.Models.Book;
import cat.itb.readbooks.R;

public class EditBookFragment extends Fragment {
    AppDatabase db;
    BookDao dao;
    BookRepository repository;

    TextView titulo;
    Book book;
    Button editButton;
    RatingBar starsBar;
    Spinner statusSpinner;
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
        View v =inflater.inflate(R.layout.edit_book_fragment,container,false);
        titulo = v.findViewById(R.id.titulo_text_view);
        starsBar = v.findViewById(R.id.ratingStarsBar);
        starsBar.setVisibility(View.INVISIBLE);
        statusSpinner = v.findViewById(R.id.status_spinner);
        editButton = v.findViewById(R.id.edit_button);

        if (getArguments() != null){
            book = getArguments().getParcelable("Book");
        }
        if (book != null){
            String[]  statusOptions = {"plan to read","reading","read","on hold","dropped"};
            titulo.setText(book.getTitle());
            for (int i = 0; i < statusOptions.length; i++) {
                if (book.getStatus().equalsIgnoreCase(statusOptions[i])){
                    statusSpinner.setSelection(i);
                    if (i==2)starsBar.setVisibility(View.VISIBLE);
                }
            }

            statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==2){
                        starsBar.setVisibility(View.VISIBLE);
                    }else starsBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            starsBar.setRating(book.getStars());
            starsBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                    Toast.makeText(getContext(),""+starsBar.getRating(),Toast.LENGTH_SHORT).show();
                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repository.update(book.getId(),starsBar.getRating());
                    repository.update(book.getId(),statusSpinner.getSelectedItem().toString());
                }
            });
        }

        return v;
    }
}
