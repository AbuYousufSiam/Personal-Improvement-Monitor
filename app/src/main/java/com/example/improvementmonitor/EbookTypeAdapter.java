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

import java.util.ArrayList;
import java.util.List;

public class EbookTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<EbookItem> ebItemList;
    private static final int TYPE_MAIN = 0;
    private static final int TYPE_SUB = 1;

    public EbookTypeAdapter(Context context, List<EbookItem> itemList) {
        this.context = context;
        this.ebItemList = new ArrayList<>(itemList);
    }

    @Override
    public int getItemViewType(int position) {
        EbookItem item = ebItemList.get(position);
        return (item.getSubItems() == null) ? TYPE_SUB : TYPE_MAIN;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_MAIN) {
            View view = LayoutInflater.from(context).inflate(R.layout.book_item_main_category, parent, false);
            return new MainViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.book_item_sub_category, parent, false);
            return new SubViewHolder(view);
        }
    }

    // Binds data to the views for each item
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EbookItem currentItem = ebItemList.get(position);

        if (holder.getItemViewType() == TYPE_MAIN) {
            MainViewHolder mainHolder = (MainViewHolder) holder;
            mainHolder.bind(currentItem);
            mainHolder.itemView.setOnClickListener(v -> {
                if (currentItem.isExpanded()) {
                    collapseSubItems(position);
                } else {
                    expandSubItems(position, currentItem.getSubItems());
                }
                currentItem.setExpanded(!currentItem.isExpanded());
            });
        } else {
            SubViewHolder subHolder = (SubViewHolder) holder;
            subHolder.bind(currentItem);
        }
    }
    // Expand subcategories right below the main category
    private void expandSubItems(int position, List<EbookItem> subItems) {
        if (subItems == null || subItems.isEmpty()) return;

        int insertPosition = position + 1;  // Insert right after the main item
        ebItemList.addAll(insertPosition, subItems);
        notifyItemRangeInserted(insertPosition, subItems.size());
    }

    // Collapse subcategories and remove them from the list
    private void collapseSubItems(int position) {
        EbookItem item = ebItemList.get(position);
        List<EbookItem> subItems = item.getSubItems();
        if (subItems == null || subItems.isEmpty()) return;

        int removePosition = position + 1;
        ebItemList.subList(removePosition, removePosition + subItems.size()).clear();
        notifyItemRangeRemoved(removePosition, subItems.size());
    }

    @Override
    public int getItemCount() {
        return ebItemList.size();
    }


    // class within root class
    static class MainViewHolder extends RecyclerView.ViewHolder {
        private final ImageView bookTypeImage;
        private final TextView bookTypeName;
        private final TextView bookTypeDescription;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTypeImage = itemView.findViewById(R.id.book_type_image);
            bookTypeName = itemView.findViewById(R.id.book_type_name);
            bookTypeDescription = itemView.findViewById(R.id.book_type_description);
        }

        public void bind(EbookItem ebookItem) {
            bookTypeImage.setImageResource(ebookItem.getImageResource());
            bookTypeName.setText(ebookItem.getText());
            bookTypeDescription.setText(ebookItem.getDescription());
        }
    }

    static class SubViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookTypeName;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTypeName = itemView.findViewById(R.id.book_type_name);
        }

        public void bind(EbookItem ebookItem) {
            bookTypeName.setText(ebookItem.getText());
        }
    }

}
