package com.pune.androidexam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pune.androidexam.R;
import com.pune.androidexam.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> mDataset;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Person person);
    }

    public PersonAdapter(List<Person> dataset, OnItemClickListener listener) {
        mDataset = dataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.bind(mDataset.get(position), listener);

        Person person = mDataset.get(position);
        holder.textViewName.setText(person.getName());
        holder.textViewEmail.setText(person.getEmailAddress());
        holder.textViewPhone.setText(person.getMobileNumber());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewPhone;
        TextView textViewEmail;

        PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.person_name);
            textViewEmail = itemView.findViewById(R.id.person_email);
            textViewPhone = itemView.findViewById(R.id.person_phone);
        }

        void bind(final Person person, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(person));
        }
    }
}
