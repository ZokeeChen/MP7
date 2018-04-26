package edu.illinois.cs.cs125.lab12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    JsonObject rates;

    double amountof = 1;
    String currentBase = "USD";
    String aimCur = "CNY";

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);

        final Button usd = findViewById(R.id.getRate);
        usd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get Exchange Rates");
                startAPICall();
            }
        });
        final Button can = findViewById(R.id.can);
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get CAN");
                getCAD();
            }
        });
        final Button CNY = findViewById(R.id.TOCNYB);
        CNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get CNY");
                getCNY();
            }
        });
        final Button jpy = findViewById(R.id.tojpyb);
        jpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get CNY");
                getJPY();
            }
        });
        final Button aud = findViewById(R.id.button3);
        aud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get CNY");
                getAUD();
            }
        });
        final Button gbp = findViewById(R.id.button4);
        gbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get CNY");
                getGBP();
            }
        });

        final Button tochange = findViewById(R.id.button);
        tochange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To exchange");
                getGBP();
                getAUD();
                getCNY();
                getCAD();
                getJPY();
                getAim();
            }
        });
        final EditText amount = findViewById(R.id.editText);
        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To get CNY");
                String tem = amount.getText().toString();
                amountof = Double.parseDouble(tem);
                Toast.makeText(MainActivity.this, "Amount to change =" + tem, Toast.LENGTH_SHORT).show();
            }
        });
        final EditText base = findViewById(R.id.editText2);
        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To set base");
                currentBase = base.getText().toString().toUpperCase();
                Toast.makeText(MainActivity.this, "Set base as" + currentBase, Toast.LENGTH_SHORT).show();
            }
        });
        final EditText aim = findViewById(R.id.editText3);
        aim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "To set base");
                aimCur = aim.getText().toString().toUpperCase();
                Toast.makeText(MainActivity.this, "Set aim as" + aimCur, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    private EditText toedit;

    void buOnClick(View v) {
        Button button = (Button) v;
        toedit = (EditText) findViewById(R.id.editText);
        Toast.makeText(this, "to do the edit", Toast.LENGTH_SHORT).show();
    }

    void getRates(JSONObject response) {
        if (response == null) {
            return;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(response.toString()).getAsJsonObject();
        rates = result.getAsJsonObject("rates");
        TextView text = findViewById(R.id.textView3);
        text.setText(rates.toString());
    }
    void getAim() {
        if (rates == null) {
            return;
        }
        if (!rates.has(aimCur)) {
            Toast.makeText(this, "No aim Currency Found", Toast.LENGTH_SHORT).show();
            return;
        }
        Double num = rates.get(aimCur).getAsDouble();
        Double aa = num * amountof;
        Toast.makeText(this, "To " +aimCur+" is "+ aa.toString(), Toast.LENGTH_SHORT).show();
    }
    void getCAD() {
        if (rates == null) {
            return;
        }
        if (!rates.has("CAD")) {
            return;
        }
        Double num = rates.get("CAD").getAsDouble();
        Double aa = num * amountof;
        TextView text = findViewById(R.id.textView4);
        text.setText(aa.toString());
    }
    void getCNY() {
        if (rates == null) {
            return;
        }
        if (!rates.has("CNY")) {
            return;
        }
        Double num = rates.get("CNY").getAsDouble();
        Double aa = num * amountof;
        TextView text = findViewById(R.id.TOCNYT);
        text.setText(aa.toString());
    }
    void getGBP() {
        if (rates == null) {
            return;
        }
        if (!rates.has("GBP")) {
            return;
        }
        Double num = rates.get("GBP").getAsDouble();
        Double aa = num * amountof;
        TextView text = findViewById(R.id.textView6);
        text.setText(aa.toString());
    }
    void getJPY() {
        if (rates == null) {
            return;
        }
        if (!rates.has("JPY")) {
            return;
        }
        Double num = rates.get("JPY").getAsDouble();
        Double aa = num * amountof;
        TextView text = findViewById(R.id.textView2);
        text.setText(aa.toString());
    }
    void getAUD() {
        if (rates == null) {
            return;
        }
        if (!rates.has("AUD")) {
            return;
        }
        Double num = rates.get("AUD").getAsDouble();
        Double aa = num * amountof;
        TextView text = findViewById(R.id.textView5);
        text.setText(aa.toString());
    }

    /**
     * Make a call to the weather API.
     */
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://exchangeratesapi.io/api/latest?base=" + currentBase
                            + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(5));
                                getRates(response);

                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
