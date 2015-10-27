package com.example.unist.myapplication;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by mintaewon on 2015. 7. 24..
 */
public class Res_menu_adapter extends ArrayAdapter<Res_menu_item> {
    private Context context;
    private ArrayList<Res_menu_item> items;
    int layoutResId;
    private Typeface mTypeface;
    private Typeface mBoldTypeFace;

    public Res_menu_adapter(Context context, int textViewResourceId, ArrayList<Res_menu_item> items){
        super(context,textViewResourceId,items);
        this.layoutResId = textViewResourceId;
        this.context=context;
        this.items = items;
        //mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Questrial_Regular.otf");
        //mBoldTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand_Bold.otf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        ResListHolder holder = null;
        if(v==null){
            LayoutInflater vi =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResId,parent,false);
            holder = new ResListHolder();
            holder.food_name = (TextView) v.findViewById(R.id.food_name);
            holder.price = (TextView) v.findViewById(R.id.price);

            v.setTag(holder);

        }
        else{
            holder = (ResListHolder)v.getTag();
        }

        Log.e("GETVIEW",": ");
        int width_image = (int) parent.getWidth();
        int height_image = (int) parent.getHeight();
        Res_menu_item res_item = items.get(position);
        Log.e("SIZE",":"+width_image+" "+height_image+" "+v.getWidth());

        if(res_item!=null){
            holder.food_name.setText(res_item.food_name);
            holder.price.setText(String.valueOf(res_item.price));
//            Picasso.with(this.context).load(res_item.res_imgurl).fit().centerCrop().into(holder.res_image);
        }
        //setGlobalFont(parent);

        return v;
    }

    void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView)
                ((TextView)child).setTypeface(mTypeface);
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child);
        }
    }

    static class ResListHolder
    {
        TextView food_name;
        TextView price;

    }
}
