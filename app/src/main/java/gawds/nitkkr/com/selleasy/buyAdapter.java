package gawds.nitkkr.com.selleasy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by palak.garg on 01-10-2016.
 */

public class buyAdapter extends RecyclerView.Adapter<buyAdapter.ViewHolder> {
    Context c;
    ArrayList<models> arr;
    public buyAdapter(Context c,ArrayList<models> arr) {
        this.c=c;
        this.arr=arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.price.setText(""+arr.get(position).getPrice());
        holder.pname.setText(""+arr.get(position).getPname());
        holder.seller.setText(""+arr.get(position).getName());
        Glide.with(c).load("http://www.almerston.com/excalibur/images/"+arr.get(position).getImage()).placeholder(R.drawable.buy_icon).crossFade().into(holder.image);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_details.detailedObject=arr.get(position);
                Intent in=new Intent(c,product_details.class);
                c.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView pname,price,seller;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.product_list_item);
            image=(ImageView)itemView.findViewById(R.id.product_image);
            pname=(TextView)itemView.findViewById(R.id.product_name);
            price=(TextView)itemView.findViewById(R.id.product_price);
            seller=(TextView)itemView.findViewById(R.id.product_seller);
        }
    }
}
