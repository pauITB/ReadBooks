package cat.itb.readbooks.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cat.itb.readbooks.Models.Book;
import cat.itb.readbooks.R;

public class EditBookFragment extends Fragment {

    TextView titulo;
    Book book;
    RatingBar starsBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.edit_book_fragment,container,false);
        titulo = v.findViewById(R.id.titulo_text_view);
        starsBar = v.findViewById(R.id.ratingStarsBar);
        if (getArguments() != null){
            book = getArguments().getParcelable("Book");
        }
        if (book != null){
            titulo.setText(book.getTitle());
            starsBar.setRating(book.getStars());
            starsBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    Toast.makeText(getContext(),""+starsBar.getRating(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        return v;
    }
}
