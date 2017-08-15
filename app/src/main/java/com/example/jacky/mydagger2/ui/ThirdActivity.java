package com.example.jacky.mydagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jacky.mydagger2.R;
import com.example.jacky.mydagger2.di.components.ThirdComponent;
import com.example.jacky.mydagger2.di.components.ThirdUserComponent;
import com.example.jacky.mydagger2.di.data.ThirdModel;
import com.example.jacky.mydagger2.di.qualifier.ThirdQualifier;

import javax.inject.Inject;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

public class ThirdActivity extends AppCompatActivity {

    TextView mTextView;

    @Inject
    @ThirdQualifier("bad")
    ThirdModel mThirdModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mTextView = (TextView) findViewById(R.id.text);

        ThirdComponent.Initializer.init(ThirdUserComponent.Initializer.init()).inject(this);
        mTextView.setText(mThirdModel.sayHI());
    }


}
