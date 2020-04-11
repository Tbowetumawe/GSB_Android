package bts.sio.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void login(View v){
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);

        if (username.getText().toString().equals("diane") && password.getText().toString().equals("mdp")){
            Toast.makeText(this, "Vous etes bien authentifié", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
