package moer.intervalclick.sample;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import moer.intervalclick.R;
import moer.intervalclick.api.IntervalClick;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    FloatingActionButton fab;

    @AfterViews
    void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(moer.intervalclick.R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @IntervalClick
    void testButton() {
        Toast.makeText(this, "Click!!", Toast.LENGTH_SHORT).show();
    }

}
