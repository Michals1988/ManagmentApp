package com.example.pracainzynierska.clients;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pracainzynierska.R;
import com.example.pracainzynierska.projects.Project;
import com.example.pracainzynierska.projects.ShowOneProjectActivity;

import java.util.ArrayList;

public class ClientsAdapter extends RecyclerView.Adapter {


    private ArrayList<Client> mClient = new ArrayList<>();

    private RecyclerView mRecyclerView;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameSurname;
        public TextView mPhoneNumber;
        public ImageView mAvatar;

        public MyViewHolder(View pItem) {
            super(pItem);
            mNameSurname = (TextView) pItem.findViewById(R.id.textViewNameSurnameClient);
            mPhoneNumber = (TextView) pItem.findViewById(R.id.textViewPhoneNumberClient);
            mAvatar = (ImageView) pItem.findViewById(R.id.imageViewAvatarClient);
        }
    }

    public ClientsAdapter(ArrayList<Client> pClient, RecyclerView pRecyclerView) {
        mClient = pClient;
        mRecyclerView = pRecyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clients_list_listview, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = new Client();
                int positionToOpen = mRecyclerView.getChildAdapterPosition(v);

                client = mClient.get(positionToOpen);

                String idClient = String.valueOf(client.getDocumentId());

                Intent intentOpenContact = new Intent(v.getContext(), ShowOneClientActivity.class);
                String documentID = client.getDocumentId();

                intentOpenContact.putExtra("documentID", documentID);

                v.getContext().startActivity(intentOpenContact);


            }
        });


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Client client = mClient.get(position);

        ((MyViewHolder) holder).mNameSurname.setText(client.getClientName() + "" + client.getClientSurname());
        ((MyViewHolder) holder).mPhoneNumber.setText(client.getClientPhoneNumber());
        ((MyViewHolder) holder).mAvatar.setImageResource(R.drawable.man1);
    }

    @Override
    public int getItemCount() {
        return mClient.size();
    }

}
