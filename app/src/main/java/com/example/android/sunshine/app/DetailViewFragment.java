package com.example.android.sunshine.app;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.example.android.sunshine.app.R;

public class DetailViewFragment extends Fragment {

    public final String LOG_TAG = DetailViewFragment.class.getSimpleName();
    private String mForecast;
    private ShareActionProvider mShareActionProvider;

    public DetailViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        setHasOptionsMenu(true);

        // Get the message from the intent
        Intent intent = getActivity().getIntent();
        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            mForecast = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.detail_text))
                    .setText(mForecast);
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detailviewfragment, menu);
        mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.action_share)
                .getActionProvider();
        setShareIntent();
    }

    private void setShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mForecast + " #SunshineApp");
        mShareActionProvider.setShareIntent(intent);
        Log.d(LOG_TAG, "setShareIntent: " + mForecast + " #SunshineApp");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Log.d(LOG_TAG, "Clicked Settings in DetailViewFragment");
            Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}