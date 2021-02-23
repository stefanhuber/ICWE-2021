package at.stefanhuber.contactappnative;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchCallback extends ItemTouchHelper.SimpleCallback {

    public interface ItemRemovedListener {
        void onItemRemoved(int position);
    }

    protected ItemRemovedListener itemRemovedListener;

    public ItemTouchCallback() {
        super(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (itemRemovedListener != null) {
            itemRemovedListener.onItemRemoved(viewHolder.getAdapterPosition());
        }
    }

    public void setItemRemovedListener(ItemRemovedListener itemRemovedListener) {
        this.itemRemovedListener = itemRemovedListener;
    }
}
