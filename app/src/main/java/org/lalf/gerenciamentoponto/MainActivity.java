package org.lalf.gerenciamentoponto;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.lalf.gerenciamentoponto.controller.RecordController;
import org.lalf.gerenciamentoponto.controller.Controller;


public class MainActivity extends Activity {

    private Controller mController;
    private CheckTimeFragment mCheckTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            mController = new RecordController(this);
            mCheckTimeFragment = new CheckTimeFragment();
            mCheckTimeFragment.setController(mController);

            getFragmentManager().beginTransaction()
                    .add(R.id.container, mCheckTimeFragment).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mController.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mController.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            return rootView;
        }
    }
}
