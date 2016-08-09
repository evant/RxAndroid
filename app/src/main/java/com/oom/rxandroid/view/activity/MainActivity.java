package com.oom.rxandroid.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.oom.rxandroid.R;
import com.oom.rxandroid.adapter.MainUserAdapter;
import com.oom.rxandroid.databinding.ActivityMainBinding;
import com.oom.rxandroid.model.Person;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    
    Person person;
    ArrayList< Person > persons = new ArrayList<>();
    MainUserAdapter adapter;
    
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        ActivityMainBinding binding = DataBindingUtil.setContentView( this, R.layout.activity_main );
        
        person = new Person();
        person.setName( "HelloDataBinding!" );

//        binding.setPerson( person );
        binding.setVariable( BR.person, person );
        binding.setPresenter( new Presenter() );
        
        adapter = new MainUserAdapter( binding.rvpTabHost, persons, R.layout.item_main_user );
        binding.rvpTabHost.setHasFixedSize( true );
        LinearLayoutManager layout = new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, true );
        binding.rvpTabHost.setLayoutManager( layout );
        binding.rvpTabHost.setAdapter( adapter );
        binding.rvpTabHost.addOnPageChangedListener( ( oldPosition, newPosition ) -> {
            ( adapter.getItem( oldPosition ) ).setColor( "#ff5500" );
            ( adapter.getItem( newPosition ) ).setColor( "#ffffff" );
            Toast.makeText( this, oldPosition + "\t" + newPosition, Toast.LENGTH_SHORT ).show();
        } );
        binding.rvpTabHost.scrollToPosition( 5 );
        binding.rvpTabHost.addOnScrollListener( new OnScrollListener() {
            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy ) {
                int childCount = binding.rvpTabHost.getChildCount();
                int width = binding.rvpTabHost.getChildAt( 0 ).getWidth();
                int padding = ( binding.rvpTabHost.getWidth() - width ) / 2;
                for ( int j = 0; j < childCount; j++ ) {
                    View v = recyclerView.getChildAt( j );
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if ( v.getLeft() <= padding ) {
                        if ( v.getLeft() >= padding - v.getWidth() ) {
                            rate = ( padding - v.getLeft() ) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY( 1 - rate * 0.1f );
                        v.setScaleX( 1 - rate * 0.1f );
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if ( v.getLeft() <= recyclerView.getWidth() - padding ) {
                            rate = ( recyclerView.getWidth() - padding - v.getLeft() ) * 1f / v.getWidth();
                        }
                        v.setScaleY( 0.9f + rate * 0.1f );
                        v.setScaleX( 0.9f + rate * 0.1f );
                    }
                }
            }
        } );
        binding.rvpTabHost.addOnLayoutChangeListener( ( v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ) -> {
            if ( binding.rvpTabHost.getChildCount() < 3 ) {
                if ( binding.rvpTabHost.getChildAt( 1 ) != null ) {
                    if ( binding.rvpTabHost.getCurrentPosition() == 0 ) {
                        View v1 = binding.rvpTabHost.getChildAt( 1 );
                        v1.setScaleY( 0.9f );
                        v1.setScaleX( 0.9f );
                    } else {
                        View v1 = binding.rvpTabHost.getChildAt( 0 );
                        v1.setScaleY( 0.9f );
                        v1.setScaleX( 0.9f );
                    }
                }
            } else {
                if ( binding.rvpTabHost.getChildAt( 0 ) != null ) {
                    View v0 = binding.rvpTabHost.getChildAt( 0 );
                    v0.setScaleY( 0.9f );
                    v0.setScaleX( 0.9f );
                }
                if ( binding.rvpTabHost.getChildAt( 2 ) != null ) {
                    View v2 = binding.rvpTabHost.getChildAt( 2 );
                    v2.setScaleY( 0.9f );
                    v2.setScaleX( 0.9f );
                }
            }
        } );
        
        for ( int i = 0; i < 10; i++ ) {
            Person person = new Person();
            person.setName( "第" + i + "周" );
            persons.add( person );
        }
        adapter.refresh( persons );

        String string = "";
        int[] ints = new int[]{
                1, 2, 3, 4, 5, 7, 11, 14, 16, 17, 18
        };
        for ( int i = 0; i < 11; i++ ) {
            for ( int j = 1; j < 41; j++ ) {
                string += "\u0014["+ ints[i] + "|" + j + "|" + "嗨" + "] ";
            }
            string += "\n";
        }
        Log.e( "CcYang", string );
    }
    
    public class Presenter {
        
        public void addPerson() {
            Person person = new Person();
            person.setName( "第" + new Random().nextInt( 100 ) + "周" );
            persons.add( person );
            adapter.notifyDataSetChanged();
        }
        
        public void toastString( String words ) {
            Toast.makeText( MainActivity.this, words, Toast.LENGTH_SHORT ).show();
        }
    }
}
