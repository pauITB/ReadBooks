package cat.itb.readbooks.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import cat.itb.readbooks.R;


public class LogInFragment extends Fragment {

    TextInputLayout username, password;
    Button register, login;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_in, container, false);
        username = v.findViewById(R.id.username_login);
        password = v.findViewById(R.id.password_login);
        register = v.findViewById(R.id.login_register_button);
        login = v.findViewById(R.id.login_button_login);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getEditText().getText().toString().equals("")){
                    username.setError("Camp obligatori");
                }

                if(!password.getEditText().getText().toString().equals("")){
                    if(password.getEditText().getText().length() < 7) {
                        password.setError("Contrasenya curta");
                    }

                } else {
                    password.setError("Camp obligatori");
                }
                if(!username.getEditText().getText().toString().equals("") && !password.getEditText().getText().toString().equals("") ){
                    NavDirections directions = LogInFragmentDirections.actionLogInFragmentToBooksGridFragment();
                    Navigation.findNavController(v).navigate(directions);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections directions = LogInFragmentDirections.actionLogInFragmentToRegisterFragment();
                Navigation.findNavController(v).navigate(directions);
            }
        });
    }
}