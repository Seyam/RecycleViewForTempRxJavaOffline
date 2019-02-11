package com.example.recycleviewfortemprxjavaoffline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycleviewfortemprxjavaoffline.Database.TempSensorData;
import com.example.recycleviewfortemprxjavaoffline.R;

import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<com.example.recycleviewfortemprxjavaoffline.Database.TempSensorData> dataList;
    private Context context;

    public EmployeeAdapter(List<TempSensorData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, final int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtEmpName.setText(dataList.get(position).getLocation());
        holder.txtEmpEmail.setText(dataList.get(position).getTempValue());
        holder.txtEmpPhone.setText(dataList.get(position).getTimestamp());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked: "+dataList.get(position).getLocation(), Toast.LENGTH_SHORT).show();
                Log.e("Tag: ", "Clicked");

            }
        });

//        if(dataList.get(position).getTempValue() > 20.0){
//            holder.linearLayout.setBackgroundColor(Color.RED);
//        }
//        else {
//            holder.linearLayout.setBackgroundColor(Color.GREEN);
//        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtEmpName, txtEmpEmail, txtEmpPhone;
        LinearLayout linearLayout;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txt_id_field);
            txtEmpName = (TextView) itemView.findViewById(R.id.txt_employee_name);
            txtEmpEmail = (TextView) itemView.findViewById(R.id.txt_employee_email);
            txtEmpPhone = (TextView) itemView.findViewById(R.id.txt_employee_phone);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout_field);
        }
    }
}