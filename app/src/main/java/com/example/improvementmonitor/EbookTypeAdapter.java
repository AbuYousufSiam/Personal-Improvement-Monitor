package com.example.improvementmonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grevocabularyapp.R;

import java.util.List;

public class EbookTypeAdapter extends RecyclerView.Adapter<EbookTypeAdapter.ebViewHolder> {
    private Context context;
    private List<EbookItem> ebItemList;

    public EbookTypeAdapter(Context context, List<EbookItem> itemList) {
        this.context = context;
        this.ebItemList = itemList;
    }

    // ViewHolder class that holds references to each view in the item layout
    public static class ebViewHolder extends RecyclerView.ViewHolder{
        ImageView vw_EbookTypeImage;
        TextView vw_text_description_of_type, vw_ebooktypeName;
        ImageView icon1, icon2, icon3; // rename it later if needed

        public ebViewHolder(View ref_itemView){
            super(ref_itemView);
            vw_EbookTypeImage = ref_itemView.findViewById(R.id.book_type_image);
            vw_ebooktypeName = ref_itemView.findViewById(R.id.book_type_name);
            vw_text_description_of_type = ref_itemView.findViewById(R.id.book_type_description);
            icon1 = ref_itemView.findViewById(R.id.icon1_ac_bk_type);
            icon2 = ref_itemView.findViewById(R.id.icon2_ac_bk_type);
            icon3 = ref_itemView.findViewById(R.id.icon3_ac_bk_type);
        }
    }

    // Inflates the item layout and returns a new ViewHolder
    @NonNull
    @Override
    public ebViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.act_book_type, parent, false);
        return new ebViewHolder(view);
    }

    // Binds data to the views for each item
    @Override
    public void onBindViewHolder(@NonNull EbookTypeAdapter.ebViewHolder holder, int position) {
        EbookItem obj_eb_item = ebItemList.get(position);

        // Set the image, text, and icon resources or properties
        holder.vw_EbookTypeImage.setImageResource(obj_eb_item.getImageResource());
        holder.vw_ebooktypeName.setText(obj_eb_item.getText());
        holder.vw_text_description_of_type.setText(obj_eb_item.getDescription());
        holder.icon1.setImageResource(obj_eb_item.getSmall_icon1Resource());
        holder.icon2.setImageResource(obj_eb_item.getSmall_icon2Resource());
        holder.icon3.setImageResource(obj_eb_item.getSmall_icon3Resource());
    }

    @Override
    public int getItemCount() {
        return ebItemList.size();
    }
}
