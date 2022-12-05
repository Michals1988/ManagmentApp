package com.example.pracainzynierska.projects;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pracainzynierska.R;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter {
    private ArrayList<Project> mProject = new ArrayList<>();

    private RecyclerView mRecyclerView;


    private class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mProjectName;
        public TextView mProjectInvestorNameSurname;
        public TextView mProjectAddress;
        public TextView mProjectPlotNumber;

        public MyViewHolder(View pItem) {

            super(pItem);

            mProjectName = (TextView) pItem.findViewById(R.id.textViewProjectName);
            mProjectInvestorNameSurname = (TextView) pItem.findViewById(R.id.textViewProjectInvestorNameSurname);
            mProjectAddress = (TextView) pItem.findViewById(R.id.textViewProjectAdress);
            mProjectPlotNumber = (TextView) pItem.findViewById(R.id.textViewProjectPlotNumber);


        }
    }

    public ProjectAdapter(ArrayList<Project> pProject, RecyclerView pRecyclerView) {
        mProject = pProject;
        mRecyclerView = pRecyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.project_list_listview, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Project project = new Project();
                int positionToOpen = mRecyclerView.getChildAdapterPosition(v);

                project = mProject.get(positionToOpen);

                String idProject = String.valueOf(project.getDocumentID());

                Intent intentOpenContact = new Intent(v.getContext(), ShowOneProjectActivity.class);
                String documentID = project.getDocumentID();

                intentOpenContact.putExtra("documentID", documentID);

                v.getContext().startActivity(intentOpenContact);


            }
        });

        return new ProjectAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Project project = mProject.get(position);

        ((MyViewHolder) holder).mProjectName.setText(project.getProjectName());
        ((MyViewHolder) holder).mProjectInvestorNameSurname.setText(project.getProjectInvestorName() + " " + project.getProjectInvestorSurname());
        ((MyViewHolder) holder).mProjectAddress.setText(project.getProjectCity());
        ((MyViewHolder) holder).mProjectPlotNumber.setText(project.getProjectPlotNumber());
    }

    @Override
    public int getItemCount() {
        return mProject.size();
    }

}
