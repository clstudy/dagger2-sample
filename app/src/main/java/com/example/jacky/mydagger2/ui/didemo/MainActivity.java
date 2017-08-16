package com.example.jacky.mydagger2.ui.didemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jacky.mydagger2.R;
import com.example.jacky.mydagger2.di.components.MainComponent;
import com.example.jacky.mydagger2.di.data.Main2Model;
import com.example.jacky.mydagger2.di.data.MainModel;
import com.example.jacky.mydagger2.framework.BaseActivity;
import com.example.jacky.mydagger2.ui.mvpdemo.FoodActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

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

    public void gomvp(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }
}
