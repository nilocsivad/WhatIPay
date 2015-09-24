package com.iam_vip.whatipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iam_vip.c.C;
import com.iam_vip.util.AppHelper;
import com.iam_vip.whatipay.ui.DailyPaymentActivity;

public class WelcomeActivity extends Activity implements C, View.OnClickListener {

    private static final long DELAY_WELCOME = 2000;

    private TextView welcome;

    private boolean isJumped = false;

    @SuppressLint("HandlerLeak")
    private Handler delayHandler = new Handler() {
        @Override
        public void handleMessage( Message msg ) {
            if ( !isJumped )
                onWelcomeDone();
        }
    };

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );

        // ** initial the application helper
        AppHelper.init( this.getApplicationContext(), this.getWindowManager() );

        this.doInitialComponents();

        delayHandler.sendEmptyMessageDelayed( WHAT_DONE, DELAY_WELCOME );
    }

    /**
     *
     */
    private void doInitialComponents() {

        RelativeLayout layout = (RelativeLayout) this.findViewById( R.id.welcome_root );
        animationDrawable = (AnimationDrawable) layout.getBackground();

        welcome = (TextView) layout.getChildAt( 0 );
        {
            welcome.setOnClickListener( this );
            {
                int size = (int) ( Math.min( AppHelper.displayMetrics.widthPixels, AppHelper.displayMetrics.heightPixels ) * 0.75 ) + 5;
                // ** the background image's size is 400x400
                size = Math.min( size, 400 );
                ViewGroup.LayoutParams rangle = welcome.getLayoutParams();
                rangle.height = size;
                rangle.width = size;
                welcome.setLayoutParams( rangle );
            }
        }
    }

    @Override
    public void onWindowFocusChanged( boolean hasFocus ) {
        if ( hasFocus && animationDrawable != null && !animationDrawable.isRunning() )
            animationDrawable.start();
    }

    /**
     * when finish to show the welcome panel, call this function
     */
    private void onWelcomeDone() {
        this.finish();

        this.startActivity( new Intent( this, DailyPaymentActivity.class ) );
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick( View v ) {
        isJumped = true;

        this.onWelcomeDone();
    }
}