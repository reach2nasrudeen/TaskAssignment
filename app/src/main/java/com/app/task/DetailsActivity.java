package com.app.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView textName,textEmail,textPhone,textAddress,textSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        Model.User user = (Model.User)getIntent().getSerializableExtra("user");
        if(user!=null) {
            textName.setText(user.name);
            textEmail.setText("Email : "+user.email);
            textPhone.setText("Phone : "+user.phone);
            textAddress.setText(user.address.street +"\n\n"+user.address.city+"\n\n"+user.address.zipcode);
            textSite.setText("Website : "+user.website);
        }
    }
    private void initView() {
        textName = (TextView) findViewById(R.id.textName);
        textEmail = (TextView) findViewById(R.id.textEmail);
        textPhone = (TextView) findViewById(R.id.textPhone);
        textAddress = (TextView) findViewById(R.id.textAddress);
        textSite = (TextView) findViewById(R.id.textSite);
    }
}
