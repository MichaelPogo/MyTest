package misha.mytest.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import misha.mytest.R;
import misha.mytest.dataModels.Item;

/**
 * Created by micha on 6/16/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {
    private Activity activity;
    private List<Item> itemsList;

    public ItemsAdapter(Activity activity,List<Item> itemsList){
        this.itemsList = itemsList;
        this.activity = activity;
    }

    @Override
    public ItemsAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemHolder holder, int position) {
        Item item = itemsList.get(position);
        holder.tvFullName.setText(item.getFullName());
        int level = item.getLevel();
        if(level>=0) { // if the level item is less then 0 then something's wrong,
            // I decided to still show the Item but not to change the background color

            if (level < 10) { // item for noobs
                holder.itemView.setBackgroundColor(Color.RED);
            } else if (level >= 10 && level < 50) { // fine item
                holder.itemView.setBackgroundColor(Color.YELLOW);
            } else if (level > 50) {// great item!
                holder.itemView.setBackgroundColor(Color.GREEN);
            }
        }

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        private TextView tvFullName;
        private View itemView;
        ItemHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvFullName = itemView.findViewById(R.id.tv_full_name);
        }

    }


}
