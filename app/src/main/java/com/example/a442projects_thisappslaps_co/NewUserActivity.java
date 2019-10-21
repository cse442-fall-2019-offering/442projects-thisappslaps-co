package com.example.a442projects_thisappslaps_co;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mSubmitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        initializeViewVariables();
        setListeners();
    }

    @Override
    public void onClick(View view) {
//        if(mUsernameEditText.getText().toString() != null && mPasswordEditText.getText() != null){
//
//        }
    }

    private void initializeViewVariables() {
        mUsernameEditText = findViewById(R.id.username_editText);
        mPasswordEditText = findViewById(R.id.password_editText);
        mSubmitButton = findViewById(R.id.login_btn);
    }

    private void setListeners() {
        mSubmitButton.setOnClickListener(this);
    }

}
