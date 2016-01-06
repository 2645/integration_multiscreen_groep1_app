package be.ehb.funinthequeue.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Avatar;

/**
 * Created by Dieter on 6/01/2016.
 */

public class AvatarRecyclerAdapter extends RecyclerView.Adapter<AvatarRecyclerAdapter.AvatarViewHolder> {

    private List<Avatar> avatars;

    public AvatarRecyclerAdapter(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    @Override
    public void onBindViewHolder(AvatarViewHolder avatarViewHolder, int i) {
        Avatar a = avatars.get(i);
        avatarViewHolder.name.setText(a.getName());
        avatarViewHolder.image.setImageBitmap(a.getBitmap());
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_avatar, viewGroup, false);

        return new AvatarViewHolder(itemView);
    }

    public void swap(ArrayList<Avatar> data){
        avatars.clear();
        avatars.addAll(data);
        notifyDataSetChanged();
    }

    public static class AvatarViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected ImageView image;

        public AvatarViewHolder(View v) {
            super(v);
            name =  (TextView) v.findViewById(R.id.avatar_item_name);
            image = (ImageView)  v.findViewById(R.id.avatar_item_img);
        }
    }


}
