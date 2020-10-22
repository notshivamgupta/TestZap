package com.example.testzap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import static android.view.View.NO_ID;

public class GetMessageAdapter extends RecyclerView.Adapter<GetMessageAdapter.GetMessageHolder> {

    private static final int SENT = 0;
    private static final int RECEIVED = 1;

    private String userId;
    private List<GetMessageModel> messages;

    public GetMessageAdapter(List<GetMessageModel> messages, String userId) {

        this.messages = messages;
        this.userId = userId;
    }

    @NonNull
    @Override
    public GetMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;

        if (viewType == SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.rightchatrecycler, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.leftchatrecycler, parent, false);
        }
        return new GetMessageHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getSender().contentEquals(userId)) {
            return SENT;
        } else {
            return RECEIVED;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull GetMessageHolder holder, int position) {

      holder.bind(messages.get(position));

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class GetMessageHolder extends RecyclerView.ViewHolder{
       TextView message;
        public GetMessageHolder(@NonNull View itemView) {
            super(itemView);
              message=itemView.findViewById(R.id.chatmessage);
        }
        public void bind(GetMessageModel chat) {
            message.setText(chat.getMessage());
        }

    }

}
