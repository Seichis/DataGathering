package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.Constants;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends ActionBarActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    protected static final String TAG = "main-activity";

    protected static final String ADDRESS_REQUESTED_KEY = "address-request-pending";
    protected static final String LOCATION_ADDRESS_KEY = "location-address";
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected boolean mAddressRequested;

    protected String mAddressOutput;
    private AddressResultReceiver mResultReceiver;
    Button attention1Button, attention2Button, coordinationButton, fluencyButton, longTermMemoryButton, tapSpeedButton, reactionTimeButton, selectiveAttentionButton, speedButton, statsButton, settingsButton, resultsButton;
    public static String taskLocation;
    boolean pressedTest = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultReceiver = new AddressResultReceiver(new Handler());
        // Set defaults, then update using values stored in the Bundle.
        mAddressRequested = false;
        mAddressOutput = "";
        updateValuesFromBundle(savedInstanceState);

        //updateUIWidgets();
        buildGoogleApiClient();

        if (mGoogleApiClient.isConnected() && mLastLocation != null) {
            startIntentService();
        }

        mAddressRequested = true;

        taskLocation="Ballerup";
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        attention1Button = (Button) findViewById(R.id.attention1_button);
        attention2Button = (Button) findViewById(R.id.attention2_button);
        fluencyButton = (Button) findViewById(R.id.fluency_button);
        speedButton = (Button) findViewById(R.id.speed_button);
        coordinationButton = (Button) findViewById(R.id.coordination_button);
        statsButton = (Button) findViewById(R.id.stats_button);
        longTermMemoryButton = (Button) findViewById(R.id.longtermmemory_button);
        tapSpeedButton = (Button) findViewById(R.id.tapspeed_button);
        reactionTimeButton = (Button) findViewById(R.id.reactiontime_button);
        selectiveAttentionButton = (Button) findViewById(R.id.selectiveattention_button);
        settingsButton = (Button) findViewById(R.id.settings_button);
        resultsButton = (Button) findViewById(R.id.results_button);


        attention1Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DigitSpan.class));

            }
        });
        attention2Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DigitOrder.class));

            }
        });
        fluencyButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ConnectDotsOneShotActivity.class));


            }
        });
        speedButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TrailMakingActivity.class));


            }
        });
        coordinationButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, BubbleActivity.class));


            }
        });
        statsButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlotList.class));

            }
        });
        longTermMemoryButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Words.class));
            }
        });
        tapSpeedButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TapActivity.class));
            }
        });
        reactionTimeButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorTapActivity.class));
            }
        });
        selectiveAttentionButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelectiveActivity.class));
            }
        });

        resultsButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResultsActivity.class));
            }
        });

    }

    /**
     * Updates fields based on data stored in the bundle.
     */
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Check savedInstanceState to see if the address was previously requested.
            if (savedInstanceState.keySet().contains(ADDRESS_REQUESTED_KEY)) {
                mAddressRequested = savedInstanceState.getBoolean(ADDRESS_REQUESTED_KEY);
            }
            // Check savedInstanceState to see if the location address string was previously found
            // and stored in the Bundle. If it was found, display the address string in the UI.
            if (savedInstanceState.keySet().contains(LOCATION_ADDRESS_KEY)) {
                mAddressOutput = savedInstanceState.getString(LOCATION_ADDRESS_KEY);
                displayAddressOutput();
            }
        }
    }

    /**
     * Builds a GoogleApiClient. Uses {@code #addApi} to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            // Determine whether a Geocoder is available.
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, R.string.no_geocoder_available, Toast.LENGTH_LONG).show();
                return;
            }
            // It is possible that the user presses the button to get the address before the
            // GoogleApiClient object successfully connects. In such a case, mAddressRequested
            // is set to true, but no attempt is made to fetch the address (see
            // fetchAddressButtonHandler()) . Instead, we start the intent service here if the
            // user has requested an address, since we now have a connection to GoogleApiClient.
            if (mAddressRequested) {
                startIntentService();
            }
        }
    }

    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    /**
     * Updates the address in the UI.
     */
    protected void displayAddressOutput() {
        taskLocation = mAddressOutput.replace("\n",", ");

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save whether the address has been requested.
        savedInstanceState.putBoolean(ADDRESS_REQUESTED_KEY, mAddressRequested);

        // Save the address string.
        savedInstanceState.putString(LOCATION_ADDRESS_KEY, mAddressOutput);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Receiver for data sent from FetchAddressIntentService.
     */
    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         *  Receives data sent from FetchAddressIntentService and updates the UI in Feedback.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            displayAddressOutput();

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                // showToast(getString(R.string.address_found));
            }

            // Reset. Enable the Fetch Address button and stop showing the progress bar.
            mAddressRequested = false;
            //updateUIWidgets();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,GlobalSettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

