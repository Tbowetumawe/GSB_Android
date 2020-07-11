package bts.sio.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    EditText password;
    //Declaration Button
    Button b1;

    DaoBase daoBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        /*daoBase = new DaoBase(this);
        initCreateAccountTextView();*/

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.pwd);

        b1.setOnClickListener(new OnClickListener(){

            public void onClick(View v){
                if(ed1.getText().toString().equals("diane") && password.getText().toString().equals("mdp")){
                    Toast.makeText(getApplicationContext(), "Vous etes bien authentifié", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /* b1.setOnClickListener(new OnClickListener(){
        public void onclick(View view){
            if(validate()){
                String Login = editTextLogin.getText().toString();
                String Password = editTextPassword.getText().toString();

                //Visiteur visiteur = DaoBase.java;
            }
        }
    })

   public void login(View v){
        EditText username = (EditText)findViewById(R.id.textInputEditTextLogin);
        EditText password = (EditText)findViewById(R.id.textInputEditTextPassword);

        if (username.getText().toString().equals("diane") && password.getText().toString().equals("mdp")){
            Toast.makeText(this, "Vous etes bien authentifié", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_LONG).show();
        }
    }*/
}
