package lehilti.samplevolley.repositories.remote;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lehi on 18/04/2017.
 */

public class CityRepository implements CityDataSource {

    final String urlget = "http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/BuscaTodasCidades";
    final String urlpost = "http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/BuscaPontos";

    private Context context;


    public CityRepository(Context context) {
        this.context = context;
    }

    @Override
    public void getCities(final ListCityServerCallBack callBack) {

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, urlget, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray jsonArray = response;
                        List<City> cityList = new ArrayList<City>();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            try {
                                JSONObject row = jsonArray.getJSONObject(i);
                                String cidade = row.getString("Nome");
                                String estado = row.getString("Estado");

                                City city = new City(cidade,estado);
                                cityList.add(city);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (cityList.isEmpty()){
                            callBack.onNoSuccess();
                        }else{
                            callBack.onSuccess(cityList);
                        }

                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        queue.add(getRequest);
    }

    @Override
    public void getCityPoint(String city, final PointsCityServerCallBack callBack) {
        // Post sample with jSon as param
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Nome", city);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlpost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    callBack.onSuccess(response);
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callBack.onNoSuccess();
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
