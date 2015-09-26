package com.iam_vip.whatipay.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.iam_vip.biz.entity.EntityDailyPayment;
import com.iam_vip.c.C;
import com.iam_vip.db.i.impl.TableDailyPayment;
import com.iam_vip.util.AppHelper;
import com.iam_vip.util.DTUtil;
import com.iam_vip.whatipay.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DailyPaymentActivity extends Activity implements C, OnClickListener, OnItemClickListener, OnItemLongClickListener {

    private static final String[] from = { "type", "title", "time", "money" };
    private static final int[] to = { R.id.li_daily_payment_tv_type, R.id.li_daily_payment_tv_title, R.id.li_daily_payment_tv_time, R.id.li_daily_payment_tv_money };

    private static final int TO_NEW_DAILY_PAYMENT = 1000;

    private List< EntityDailyPayment > datas = new ArrayList< EntityDailyPayment >();
    private final TableDailyPayment tableDailyPayment = new TableDailyPayment();

    private View notifyView;
    private View popupMenuView;
    private PopupWindow popupMenuWindow;
    private ListView listView;
    private TextView tvMenu, tvTopDate, tvLeftDate, tvRightDate;

    private SimpleAdapter adapter;

    private int notifyViewHeight = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_daily_payment );

        this.doInitialComponents();

        if ( adapter == null ) adapter = new DailyPaymentSimpleAdapter( this, datas, R.layout.li_daily_payment, from, to );
        listView.setAdapter( adapter );

        this.setDate();
    }

    /**
     *
     */
    private void doInitialComponents() {

        ( notifyView = this.getWindow().getDecorView() ).setBackgroundResource( R.color.default_layout_background_color );

        listView = ( ListView ) this.findViewById( R.id.daily_payment_lv );
        {
            listView.setOnItemClickListener( this );
            listView.setOnItemLongClickListener( this );
        }
        ( tvMenu = ( TextView ) this.findViewById( R.id.daily_payment_tv_menu ) ).setOnClickListener( this );

        ( tvTopDate = ( TextView ) this.findViewById( R.id.daily_payment_tv_top_date ) ).setOnClickListener( this );
        ( ( TextView ) this.findViewById( R.id.daily_payment_tv_new ) ).setOnClickListener( this );

        ( tvLeftDate = ( TextView ) this.findViewById( R.id.daily_payment_tv_bottom_left_date ) ).setOnClickListener( this );
        ( tvRightDate = ( TextView ) this.findViewById( R.id.daily_payment_tv_bottom_right_date ) ).setOnClickListener( this );

        popupMenuView = this.getLayoutInflater().inflate( R.layout.popup_menu_daily_payment, null );
        {
            LinearLayout rootLayout = ( LinearLayout ) popupMenuView.findViewById( R.id.popup_menu_daily_payment_root );
            for ( int i = 0, l = rootLayout.getChildCount(); i < l; ++i ) {
                View v = rootLayout.getChildAt( i );
                if ( v instanceof TextView ) {
                    v.setOnClickListener( this );
                }
            }
        }
        popupMenuWindow = new PopupWindow( popupMenuView );
        {
            popupMenuWindow.setFocusable( true );
            popupMenuWindow.setTouchable( true );
            popupMenuWindow.setOutsideTouchable( true );
            popupMenuWindow.setBackgroundDrawable( new ColorDrawable( Color.rgb( 95, 209, 243 ) ) );
            int size = ( int ) ( Math.min( AppHelper.displayMetrics.widthPixels, AppHelper.displayMetrics.heightPixels ) * 0.6 ) + 5;
            popupMenuWindow.setWidth( size );
            popupMenuWindow.setHeight( ViewGroup.LayoutParams.WRAP_CONTENT );
        }
    }

    private void setDate() {
        this.setDate( new Date() );
    }

    private void setDate( Date date ) {
        Calendar c = Calendar.getInstance();
        c.setTime( date );

        String nowDay = DTUtil.parseDateFmt( c.getTime() );
        tvTopDate.setText( nowDay );

        c.add( Calendar.DAY_OF_MONTH, 1 );
        tvLeftDate.setText( DTUtil.parseDateFmt( c.getTime() ) );

        c.add( Calendar.DAY_OF_MONTH, -2 );
        tvRightDate.setText( DTUtil.parseDateFmt( c.getTime() ) );

        doRefreshData( nowDay );
    }

    // <do-refresh-listView>
    private void doRefreshData( String nowDay ) {
        String where = String.format( " WHERE time LIKE '%s%%' ORDER BY time DESC ", nowDay );
        datas.clear();
        datas.addAll( tableDailyPayment.select( where ) );
        adapter.notifyDataSetChanged();
    }
    // </do-refresh-listView>

    // <reponse-click-textView-function>
    private void refreshDateData( TextView dateView ) {
        String nowDate = dateView.getText().toString();
        Date now = new Date();
        try {
            now = DTUtil.DT_FMT_DATE.parse( nowDate );
        } catch ( ParseException e ) {
        }
        this.setDate( now );
    }
    // </reponse-click-textView-function>

    // <whether-show-popupWindow>
    private void togglePopupMenu() {
        if ( popupMenuWindow.isShowing() ) {
            popupMenuWindow.dismiss();
        } else {
            if ( notifyViewHeight == 0 ) {
                Rect rect = new Rect();
                notifyView.getWindowVisibleDisplayFrame( rect );
                notifyViewHeight = rect.top + this.findViewById( R.id.daily_payment_top_layout ).getHeight();
            }
            popupMenuWindow.showAtLocation( tvMenu, Gravity.LEFT | Gravity.TOP, 0, notifyViewHeight );
        }
    }
    // </whether-show-popupWindow>

    /*
     * (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.daily_payment_tv_menu:
                this.togglePopupMenu();
                break;
            case R.id.daily_payment_tv_top_date:
            case R.id.daily_payment_tv_bottom_left_date:
            case R.id.daily_payment_tv_bottom_right_date:
                this.refreshDateData( ( TextView ) v );
                break;
            case R.id.daily_payment_tv_new:
                this.startActivityForResult( new Intent( this, NewDailyPaymentActivity.class ), TO_NEW_DAILY_PAYMENT );
                break;

            // ** popup window menu ** //
            case R.id.popup_menu_daily_payment_today: // ** Today
                this.setDate( new Date() );
                popupMenuWindow.dismiss();
                break;
            case R.id.popup_menu_daily_payment_titles: // ** Titles
                this.startActivity( new Intent( this, TitleActivity.class ) );
                popupMenuWindow.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        switch ( resultCode ) {
            case WHAT_DONE:
                this.setDate( new Date() );
                break;
        }
    }

    /**
     * Callback method to be invoked when an item in this view has been
     * clicked and held.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need to access
     * the data associated with the selected item.
     * @param parent   The AbsListView where the click happened
     * @param view     The view within the AbsListView that was clicked
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     * @return true if the callback consumed the long click, false otherwise
     */
    @Override
    public boolean onItemLongClick( AdapterView< ? > parent, View view, int position, long id ) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
     */
    @Override
    public void onItemClick( AdapterView< ? > parent, View view, int position, long id ) {
        // TODO Auto-generated method stub

    }

    private class DailyPaymentSimpleAdapter extends SimpleAdapter implements C {

        /**
         * @param context
         * @param data
         * @param resource
         * @param from
         * @param to
         */
        public DailyPaymentSimpleAdapter( Context context, List< ? extends Map< String, ? > > data, int resource, String[] from, int[] to ) {
            super( context, data, resource, from, to );
        }

        /*
         * (non-Javadoc)
         * @see android.widget.SimpleAdapter#getCount()
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return super.getCount();
        }

        /*
         * (non-Javadoc)
         * @see android.widget.SimpleAdapter#getItem(int)
         */
        @Override
        public Object getItem( int position ) {
            // TODO Auto-generated method stub
            return super.getItem( position );
        }

        /*
         * (non-Javadoc)
         * @see android.widget.SimpleAdapter#getItemId(int)
         */
        @Override
        public long getItemId( int position ) {
            // TODO Auto-generated method stub
            return super.getItemId( position );
        }

        /*
         * (non-Javadoc)
         * @see android.widget.SimpleAdapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView( int position, View convertView, ViewGroup parent ) {
            View rootView = super.getView( position, convertView, parent );
            if ( rootView != null ) this.setColor( rootView, position );
            return rootView;
        }

        private void setColor( final View rootView, final int position ) {
            int color = this.getColor( position, ( ( TextView ) rootView.findViewById( R.id.li_daily_payment_tv_type ) ) );
            ( ( TextView ) rootView.findViewById( R.id.li_daily_payment_tv_type ) ).setTextColor( color );
            ( ( TextView ) rootView.findViewById( R.id.li_daily_payment_tv_title ) ).setTextColor( color );
            ( ( TextView ) rootView.findViewById( R.id.li_daily_payment_tv_time ) ).setTextColor( color );
            ( ( TextView ) rootView.findViewById( R.id.li_daily_payment_tv_money ) ).setTextColor( color );
        }

        private int getColor( final int position, final TextView tv ) {
            int color = Color.rgb( 0, 0, 0 );
            EntityDailyPayment edp = datas.get( position );
            switch ( edp.getTypeVal() ) {
                case TYPE_PAY_IN:
                    color = Color.rgb( 163, 21, 229 );
                    tv.setText( TYPE_PAY_101 );
                    break;
                case TYPE_PAY_OUT:
                    color = Color.rgb( 255, 0, 102 );
                    tv.setText( TYPE_PAY_102 );
                    break;
                case TYPE_PAY_BORROW_IN:
                    color = Color.rgb( 7, 111, 110 );
                    tv.setText( TYPE_PAY_103 );
                    break;
                case TYPE_PAY_BORROW_OUT:
                    color = Color.rgb( 25, 105, 237 );
                    tv.setText( TYPE_PAY_104 );
                    break;
            }
            return color;
        }

    }

}
