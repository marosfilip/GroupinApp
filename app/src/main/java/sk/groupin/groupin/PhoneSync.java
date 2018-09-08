package sk.groupin.groupin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class PhoneSync extends AppCompatActivity {

    public TextView usernameView;
    public TextView passwrodView;
    public Button syncPhoneButton;
    public static String username = "";
    public static String password = "";
    SharedPreferences mShare = getPreferences(MODE_PRIVATE);
    SharedPreferences.Editor prefsEditor = mShare.edit();

    public void syncPhone() {
        username = usernameView.getText().toString();
        password = passwrodView.getText().toString();
        Log.i("USERNAME",username);
        Log.i("PASSWORD", password);
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_sync);

        usernameView = findViewById(R.id.usernameView);
        passwrodView = findViewById(R.id.passwordView);
        Button syncPhoneButton = findViewById(R.id.syncPhoneButton);





    }
}
