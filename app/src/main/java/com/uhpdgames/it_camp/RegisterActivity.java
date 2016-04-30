package com.uhpdgames.it_camp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener{
    final String LOG = "RegisterActivity";
    EditText user, pass,pass2, email;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = (EditText)findViewById(R.id.id_user);
        pass = (EditText)findViewById(R.id.id_pass);
        pass2 = (EditText)findViewById(R.id.id_pass2);
        email = (EditText)findViewById(R.id.id_email);
        btnRegister = (Button)findViewById(R.id.id_register);
        btnRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
            HashMap postData = new HashMap();
            String username = user.getText().toString();
            String Email = email.getText().toString();
            String password = pass.getText().toString();
            String password2 = pass2.getText().toString();
            if (username.matches("")) {}

            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(password)) {
                user.setError("Vui lòng nhập thông tin!");
                return;
            }

            postData.put("txtUsername", username);
            postData.put("txtEmail", Email);
            postData.put("txtPassword", password);
            postData.put("txtPassword2", password2);
            postData.put("mobile", "android");

            PostResponseAsyncTask loginTask = new PostResponseAsyncTask(this, postData);
            loginTask.execute("http://10.0.3.2/android/qlsv/register.php");
    }

    @Override
    public void processFinish(String rs) {
        Log.d(LOG, rs);
        if(rs.contains("LOI_PASS")){
            Toast.makeText(this, "Mật khẩu nhắc lại không chính xác!", Toast.LENGTH_LONG).show();
        }else if(rs.contains("OK")){
            Toast.makeText(this, "Đăng Ký Thành Công", Toast.LENGTH_LONG).show();

            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }else{
            Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_LONG).show();
        }
    }
}
