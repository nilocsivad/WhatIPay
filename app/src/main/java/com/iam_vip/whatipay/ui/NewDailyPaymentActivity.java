package com.iam_vip.whatipay.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.iam_vip.whatipay.R;

public class NewDailyPaymentActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_daily_payment );

        this.doInitialComponents();
    }

    /**
     *
     */
    private void doInitialComponents() {
        this.findViewById( R.id.new_daily_payment_tv_back ).setOnClickListener( this );
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.new_daily_payment_tv_back:
                this.finish();
                break;
        }
    }
}
