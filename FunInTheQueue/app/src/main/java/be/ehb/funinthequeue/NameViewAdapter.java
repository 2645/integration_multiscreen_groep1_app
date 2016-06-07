package be.ehb.funinthequeue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.ehb.funinthequeue.model.Attraction;

/**
 * Created by Clara on 5/01/2016.
 */
//Gebaseerd op: http://stackoverflow.com/questions/22512833/create-listview-in-fragment-android
public class NameViewAdapter extends BaseAdapter {
    private ArrayList<Attraction> items;
    private LayoutInflater mInflater;

    public NameViewAdapter(Context QueueFragment, ArrayList<Attraction> results){
        items = results;
        mInflater = LayoutInflater.from(QueueFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return items.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.row_queue, null);
            holder = new ViewHolder();
            holder.txtNaam = (TextView) convertView.findViewById(R.id.textView7);
            holder.txtWachttijd = (TextView) convertView.findViewById(R.id.txtWachttijd);
            holder.imgAttractie = (ImageView) convertView.findViewById(R.id.imageView14);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNaam.setText(items.get(position).getName());
        holder.txtWachttijd.setText(items.get(position).getQueuetime() + " min");
        byte[] decodedStringAttractie = Base64.decode(items.get(position).getImg(), Base64.DEFAULT);
        Bitmap decodedByteAttractie = BitmapFactory.decodeByteArray(decodedStringAttractie, 0, decodedStringAttractie.length);
        holder.imgAttractie.setImageBitmap(decodedByteAttractie);

        return convertView;
    }

    static class ViewHolder{
        TextView txtNaam, txtWachttijd;
        ImageView imgAttractie;
    }


}
