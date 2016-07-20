package com.oom.rxandroid.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.oom.rxandroid.R;
import com.oom.rxandroid.databinding.ActivityMainBinding;
import com.oom.rxandroid.model.Person;

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
