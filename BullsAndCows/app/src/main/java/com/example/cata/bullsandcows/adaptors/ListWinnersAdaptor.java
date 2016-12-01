package com.example.cata.bullsandcows.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cata.bullsandcows.R;
import com.example.cata.bullsandcows.fonts.CustomTextView;
import com.example.cata.bullsandcows.models.User;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.List;

/**
 * Created by Cata on 11/23/2016.
 */

public class ListWinnersAdaptor extends RecyclerView.Adapter<ListWinnersAdaptor.MyViewHolder>{

    private List<User> usersList;
    private Context context;

    public ListWinnersAdaptor(List<User> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private MaterialLetterIcon icon;
        private CustomTextView userName, userScore;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.icon = (MaterialLetterIcon) itemView.findViewById(R.id.icon);
            this.userName = (CustomTextView) itemView.findViewById(R.id.userName);
            this.userScore = (CustomTextView) itemView.findViewById(R.id.userScore);
        }
    }

    @Override
    public ListWinnersAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.winners_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListWinnersAdaptor.MyViewHolder holder, int position) {
        User user = usersList.get(position);

        holder.icon.setLetter(user.getLetter());
        holder.userName.setText(user.getUserName());
        holder.userScore.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

}
