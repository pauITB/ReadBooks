package cat.itb.readbooks.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cat.itb.readbooks.Database.BookRepository;
import cat.itb.readbooks.Database.Database;
import cat.itb.readbooks.Models.Book;
import cat.itb.readbooks.R;

public class AddBookFragment extends Fragment {
    BookRepository repository;

    EditText tituloEditText, autorEditText;
    Button addButton;
    Spinner statusSpinner;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database database = new Database();
        repository = database.getRepository(this.getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_book_fragment,container,false);
        tituloEditText = v.findViewById(R.id.titulo_edit_text);
        autorEditText = v.findViewById(R.id.autor_edit_text);
        addButton = v.findViewById(R.id.add_button);
        statusSpinner = v.findViewById(R.id.status_spinner);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(tituloEditText.getText().toString(),autorEditText.getText().toString(),statusSpinner.getSelectedItem().toString());
                repository.insert(book);
                getActivity().onBackPressed();
            }
        });
        return v;
    }
}
