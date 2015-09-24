package com.iam_vip.whatipay.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.iam_vip.biz.entity.EntityTitle;
import com.iam_vip.db.i.impl.TableTitle;
import com.iam_vip.whatipay.R;

import java.util.ArrayList;
import java.util.List;

public class TitleActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    private static final String[] from = { "titleID", "title", "count" };
    private static final int[] to = { R.id.li_title_tv_titleID, R.id.li_title_tv_title, R.id.li_title_tv_count };

    private List<EntityTitle> datas = new ArrayList<EntityTitle>();
    private TableTitle tableTitle = new TableTitle();

    private ListView listView;
    //private TextView tvBack, tvTitle, tvNew;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_title );

        this.doInitialComponents();

        if ( adapter == null )
            adapter = new SimpleAdapter( this, datas, R.layout.li_title, from, to );
        listView.setAdapter( adapter );

        this.doRefreshData();
    }

    /**
     *
     */
    private void doInitialComponents() {

        listView = (ListView) this.findViewById( R.id.title_lv );
        {
            listView.setOnItemClickListener( this );
            listView.setOnItemLongClickListener( this );
        }
        ( (TextView) this.findViewById( R.id.title_tv_back ) ).setOnClickListener( this );
        ( (TextView) this.findViewById( R.id.title_tv_title ) ).setOnClickListener( this );
        ( (TextView) this.findViewById( R.id.title_tv_new ) ).setOnClickListener( this );
    }

    private void doRefreshData() {
        String where = " ORDER BY count DESC ";
        datas.clear();
        datas.addAll( tableTitle.select( where ) );
        adapter.notifyDataSetChanged();
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.title_tv_back:
                this.finish();
                break;
            case R.id.title_tv_title:
                this.doRefreshData();
                break;
            case R.id.title_tv_new:
                break;
        }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {

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
    public boolean onItemLongClick( AdapterView<?> parent, View view, int position, long id ) {
        return false;
    }
}