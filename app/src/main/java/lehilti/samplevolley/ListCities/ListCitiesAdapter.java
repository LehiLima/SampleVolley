package lehilti.samplevolley.ListCities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lehilti.samplevolley.CityScore.CityScore;
import lehilti.samplevolley.R;
import lehilti.samplevolley.repositories.remote.City;

public class ListCitiesAdapter extends RecyclerView.Adapter<ListCitiesAdapter.MyViewHolder>  {

    private ArrayList<City> list_item_city;

    public Context mcontext;

    public ListCitiesAdapter(ArrayList<City> cities, Context context) {
        list_item_city = cities;
        mcontext = context;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @Override
    public ListCitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a layout
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, null);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position ) {

        viewHolder.cidade.setText(list_item_city.get(position).getCidade().toString());
        viewHolder.estado.setText(list_item_city.get(position).getEstado().toString());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CityScore.class);
                intent.putExtra("cidade",list_item_city.get(position).getCidade().toString());
                mcontext.startActivity(intent);
            }
        });
    }

    // initializes some private fields to be used by RecyclerView.
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView cidade;
        public TextView estado;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            cidade = (TextView) itemLayoutView.findViewById(R.id.resultado_cidade);
            estado = (TextView) itemLayoutView.findViewById(R.id.resultado_estado);

        }
    }

    //Returns the total number of items in the data set hold by the adapter.
    @Override
    public int getItemCount() {
        return list_item_city.size();
    }

}