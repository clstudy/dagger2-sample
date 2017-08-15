package com.example.jacky.mydagger2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jacky.mydagger2.R;
import com.example.jacky.mydagger2.di.components.MainComponent;
import com.example.jacky.mydagger2.di.data.Main2Model;
import com.example.jacky.mydagger2.di.data.MainModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    @Inject
    MainModel mUserModel;
    @Inject
    Main2Model mUser2Model;
    @Inject
    Main2Model mUser2Model2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

        MainComponent.Initializer.init().inject(this);

        mTextView.setText(mUserModel.getName() +
                ", " + mUser2Model.sayHI()
        );
    }

    public void secondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
