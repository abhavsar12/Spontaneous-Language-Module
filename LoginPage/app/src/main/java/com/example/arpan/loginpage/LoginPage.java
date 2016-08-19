package com.example.arpan.loginpage;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText passwordRepeat;
    EditText firstName;
    EditText lastName;
    EditText company;
    EditText dob;
    RadioButton male;
    RadioButton female;

    String emailText;
    String passwordText;
    String passwordRepeatText;
    String firstNameText;
    String lastNameText;
    String companyText;
    String dobText;
    Boolean maleChecked;
    Boolean femaleChecked;

    Boolean patient = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype_page);
    }

    public void userType(View view) {
        setContentView(R.layout.activity_usertype_page);
    }

    public void loginPatient(View view) {
        patient = true;
        setContentView(R.layout.activity_login_page);
    }

    public void loginPhysician(View view) {
        patient = false;
        setContentView(R.layout.activity_login_page);
    }

    public void existingLogin(View view) {
        setContentView(R.layout.activity_existinglogin_page);
        }

    public void existingLoginButtonPress(View view) {
        email = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

        emailText = email.getText().toString();
        passwordText = password.getText().toString();

        if (emailText.length() == 0 || passwordText.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Please fill out all fields";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {
            Context context = getApplicationContext();
            CharSequence text = "Login successful";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void newLogin(View view) {
        if (patient)
            setContentView(R.layout.activity_newloginpatient_page);
        else
            setContentView(R.layout.activity_newlogin_page);
    }

    public void newLoginButtonPress(View view) {
        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        passwordRepeat = (EditText) findViewById(R.id.editText5);
        firstName = (EditText) findViewById(R.id.editText6);
        lastName = (EditText) findViewById(R.id.editText7);
        company = (EditText) findViewById(R.id.editText8);
        dob = (EditText) findViewById(R.id.editText8);
        male = (RadioButton) findViewById(R.id.radioButton);
        female = (RadioButton) findViewById(R.id.radioButton2);

        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        passwordRepeatText = passwordRepeat.getText().toString();
        firstNameText = firstName.getText().toString();
        lastNameText = lastName.getText().toString();
        companyText = company.getText().toString();
        dobText = dob.getText().toString();
        maleChecked = male.isChecked();
        femaleChecked = female.isChecked();

        if (!passwordText.equals(passwordRepeatText)) {
            Context context = getApplicationContext();
            CharSequence text = "Passwords must match";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if (!patient) {
            if (emailText.length() == 0 || passwordText.length() == 0 || passwordRepeatText.length() == 0
                    || firstNameText.length() == 0 || lastNameText.length() == 0 || company.length() == 0) {
                Context context = getApplicationContext();
                CharSequence text = "Please fill out all fields";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Login successful";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else if (patient) {
            if (emailText.length() == 0 || passwordText.length() == 0 || passwordRepeatText.length() == 0
                    || firstNameText.length() == 0 || lastNameText.length() == 0 || dobText.length() == 0
                    || (!maleChecked && !femaleChecked)) {
                Context context = getApplicationContext();
                CharSequence text = "Please fill out all fields";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Login successful";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

}
