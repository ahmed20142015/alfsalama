package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marvel.android.a1000salama.R;

import java.util.ArrayList;

import Model.Tick;

/**
 * Created by ahmedpc on 25/5/2018.
 */

public class TicksAdapter extends RecyclerView.Adapter<TicksAdapter.myviewHolder> {

    private ArrayList<Tick> ticks;
    View view;

    public TicksAdapter(ArrayList<Tick> ticks) {
        this.ticks = ticks;
    }


    @Override
    public myviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticks_item, parent, false);

        return new myviewHolder(view);
    }


    @Override
    public void onBindViewHolder(myviewHolder holder, int position) {
        Tick tick = ticks.get(position);
        holder.tickSubject.setText(tick.getSubject());
        holder.tickMessage.setText(tick.getMessage());
    }

    @Override
    public int getItemCount() {

        return ticks.size();
    }


    public class myviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tickSubject,tickMessage;
        public myviewHolder(View itemView) {
            super(itemView);
            tickSubject = itemView.findViewById(R.id.tick_subject);
            tickMessage = itemView.findViewById(R.id.tick_message);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }

    }
}