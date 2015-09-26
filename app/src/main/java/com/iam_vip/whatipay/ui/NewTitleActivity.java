package com.iam_vip.whatipay.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.iam_vip.biz.entity.EntityTitle;
import com.iam_vip.c.C;
import com.iam_vip.db.i.impl.TableTitle;
import com.iam_vip.util.AppToast;
import com.iam_vip.util.EmptyUtil;
import com.iam_vip.whatipay.R;

public class NewTitleActivity extends Activity implements C, View.OnClickListener {

    private final TableTitle tableTitle = new TableTitle();

    private EditText etTitle;

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

        etTitle = ( EditText ) this.findViewById( R.id.new_title_et_title );

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
                etTitle.setText( "" );
                this.setResult( WHAT_CANCLE );
                this.finish();
                break;
            case R.id.new_title_tv_save:
                this.doNewTitle();
                break;
        }
    }

    private void doNewTitle() {
        String title = etTitle.getText().toString();
        if ( EmptyUtil.isEmpty( title ) ) {
            AppToast.ShowShort( R.string.toast_new_title_save_null );
        } else {
            EntityTitle instance = new EntityTitle( title );
            tableTitle.insert( instance );
            etTitle.setText( "" );
            this.setResult( WHAT_DONE );
            this.finish();
        }
    }
}
