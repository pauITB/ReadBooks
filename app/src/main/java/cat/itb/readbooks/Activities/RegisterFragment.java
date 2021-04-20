package cat.itb.readbooks.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputLayout;

import cat.itb.readbooks.R;


public class RegisterFragment extends Fragment {

    TextInputLayout username, password, fpassword, correu;
    CheckBox checkBox;
    Button register, login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setErrorEnabled(false);
                password.setErrorEnabled(false);
                fpassword.setErrorEnabled(false);
                correu.setErrorEnabled(false);
                boolean allRight= true;
                if(username.getEditText().getText().toString().equals("")){
                    username.setError("Camp obligatori");
                    allRight = false;
                }

                if(!password.getEditText().getText().toString().equals("")){
                    if(password.getEditText().getText().length() < 7) {
                        password.setError("Contrasenya curta");
                        allRight = false;
                    }
                } else {
                    allRight = false;
                    password.setError("Camp obligatori");
                }

                if(!fpassword.getEditText().getText().toString().equals("")){
                    if(!fpassword.getEditText().getText().toString().equals(password.getEditText().getText().toString())){
                        System.out.println("passa per aqui\n"+fpassword.getEditText().getText()+"\n"+password.getEditText().getText());
                        fpassword.setError("Les contrasenyes no coincideixen");
                        allRight = false;
                    }
                } else {
                    allRight = false;
                    fpassword.setError("Camp obligatori");
                }

                if(correu.getEditText().getText().toString().equals("")){
                    correu.setError("Camp obligatori");
                    allRight = false;
                }
                if(allRight && checkBox.isChecked()){
                    NavDirections directions = RegisterFragmentDirections.actionRegisterFragmentToBooksGridFragment();
                    Navigation.findNavController(v).navigate(directions);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections directions = RegisterFragmentDirections.actionRegisterFragmentToLogInFragment();
                Navigation.findNavController(v).navigate(directions);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        username = v.findViewById(R.id.username_register);
        password = v.findViewById(R.id.password_register);
        fpassword = v.findViewById(R.id.password_register2);
        correu = v.findViewById(R.id.email);
        checkBox = v.findViewById(R.id.contract);
        register = v.findViewById(R.id.register_button);
        login = v.findViewById(R.id.register_login_button);
        return v;
    }


}