package com.uhpdgames.it_camp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener{

    final String LOG = "LoginActivity";
    EditText user, pass;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.id_user);
        pass = (EditText)findViewById(R.id.id_pass);
        btnLogin = (Button)findViewById(R.id.id_login);
        btnRegister = (Button)findViewById(R.id.id_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_login){
            Toast.makeText(this, "Đăng Nhập", Toast.LENGTH_LONG).show();
            HashMap postData = new HashMap();
            String username = user.getText().toString();
            String password = pass.getText().toString();

            postData.put("txtUsername", username);
            postData.put("txtPassword", password);
            PostResponseAsyncTask loginTask = new PostResponseAsyncTask(this, postData);
            loginTask.execute("http://10.0.3.2/android/qlsv/login.php");
        }else if(v.getId() == R.id.id_register){
            Toast.makeText(this, "Đăng Ký", Toast.LENGTH_LONG).show();
            Intent e = new Intent(this, RegisterActivity.class);
            startActivity(e);
        }
    }

    @Override
    public void processFinish(String rs) {
        Log.d(LOG, rs);
        if(rs.contains("DangNhapThanhCong")){
            Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();

            Intent helloAndroidIntent = new Intent(this, HelloAndroid.class);
            startActivity(helloAndroidIntent);
        }else{
            Toast.makeText(this, "Đăng Nhập Thất Bại", Toast.LENGTH_LONG).show();
        }
    }
}
