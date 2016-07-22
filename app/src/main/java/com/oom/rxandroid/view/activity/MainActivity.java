package com.oom.rxandroid.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.oom.rxandroid.R;
import com.oom.rxandroid.adapter.MainUserAdapter;
import com.oom.rxandroid.databinding.ActivityMainBinding;
import com.oom.rxandroid.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Person person;

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

        ArrayList< Person > persons = new ArrayList<>();
        MainUserAdapter adapter = new MainUserAdapter( binding.rvMain, persons, R.layout.item_main_user );
        binding.rvMain.setHasFixedSize( true );
        binding.rvMain.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, true ) );
        binding.rvMain.setAdapter( adapter );


        for ( int i = 0; i < 10; i++ ) {
            Person person = new Person();
            person.setName( "name : " + i );
            persons.add( person );
        }
        adapter.refresh( persons );
    }

    public class Presenter {

        public void onTextChanged( CharSequence s, int start, int before, int count ) {
            person.setName( s.toString() );
            Log.e( "CcYang", person.getName() );
        }

        public void onClick( View view ) {
            Toast.makeText( MainActivity.this, "点到了", Toast.LENGTH_SHORT ).show();
        }

        public void onClickListenerBinding( Person p ) {
            Toast.makeText( MainActivity.this, p.getName() + "\n" + person.getName(), Toast.LENGTH_SHORT ).show();
            person.setName( "Hello onClickListener!" );
        }

        public void toastString(String words) {
            Toast.makeText( MainActivity.this, words, Toast.LENGTH_SHORT ).show();
        }
    }
}
