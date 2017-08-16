package com.example.jacky.mydagger2.ui.didemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jacky.mydagger2.R;
import com.example.jacky.mydagger2.di.components.SecondComponent;
import com.example.jacky.mydagger2.di.data.SecondModel;
import com.example.jacky.mydagger2.di.qualifier.SeondQualifier;
import com.example.jacky.mydagger2.framework.BaseActivity;

import javax.inject.Inject;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

public class SecondActivity extends BaseActivity {

    TextView mTextView;

    @Inject
    @SeondQualifier("good")
    SecondModel mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextView = (TextView) findViewById(R.id.text);

        SecondComponent.Initializer.init().inject(this);
        mTextView.setText(mProduct.sayHI());
    }

    public void thirdActivity(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}
