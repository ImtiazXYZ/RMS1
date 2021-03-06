package com.avijit.rms1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.rms1.R;
import com.avijit.rms1.data.remote.model.UserType;
import com.avijit.rms1.utils.AppUtils;
import com.avijit.rms1.viewmodel.UserTypeViewModel;

import java.util.List;

/**
 * Created by Avijit Acharjee on 7/17/2020 at 11:15 PM.
 * Email: avijitach@gmail.com.
 */
public class UserTypeRecyclerViewAdapter extends RecyclerView.Adapter<UserTypeRecyclerViewAdapter.ViewHolder> {
    private List<UserType> userTypeList;
    private UserTypeViewModel viewModel;
    AppUtils appUtils;
    Context context;
    public UserTypeRecyclerViewAdapter(List<UserType> userTypeList, Context context){
        this.userTypeList=userTypeList;
        this.context=context;
        viewModel= ViewModelProviders.of((com.avijit.rms1.ui.UserType)context).get(UserTypeViewModel.class);
        appUtils=new AppUtils(context);
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_type_list,parent,false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserType userType = userTypeList.get(position);
        holder.nameTextView.setText(userTypeList.get(position).getName());
        holder.updateButton.setText(userType.getStatus().equals("On")?"Disable":"Enable");
        holder.updateButton.setOnClickListener(v -> {
            Toast.makeText(context, "Abcd", Toast.LENGTH_SHORT).show();
            appUtils.dialog.show();
            userType.setStatus(userType.getStatus().equals("On")?"Off":"On");
            viewModel.update(userTypeList.get(position).getId(),userType).observe((LifecycleOwner) context, response->{
                appUtils.dialog.dismiss();
                if(response.isNetworkIsSuccessful()){
                    holder.updateButton.setText(!userType.getStatus().equals("On")?"Disable":"Enable");
                }
                else {
                    Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return userTypeList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        ImageView deleteButton;
        TextView updateButton;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.name_text_view);
            updateButton=itemView.findViewById(R.id.update_button);
            deleteButton=itemView.findViewById(R.id.delete_button);
        }
    }
}
