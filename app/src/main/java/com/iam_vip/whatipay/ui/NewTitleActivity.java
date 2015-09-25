package com.iam_vip.whatipay.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.iam_vip.c.C;
import com.iam_vip.whatipay.R;

public class NewTitleActivity extends Activity implements C, View.OnClickListener {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_title );

        this.doInitialComponents();
    }

    /**
     *
     */
    private void doInitialComponents() {

        this.findViewById( R.id.new_title_tv_back ).setOnClickListener( this );
        this.findViewById( R.id.new_title_tv_save ).setOnClickListener( this );
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.new_title_tv_back:
                this.setResult( WHAT_CANCLE );
                this.finish();
                break;
            case R.id.new_title_tv_save:

                break;
        }
    }
}
