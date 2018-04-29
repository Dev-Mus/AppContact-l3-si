package com.beta.version.contact;

/**
 * Created by aa on 29/04/2018.
 */


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    Context context;
    List<Contact> lists;

    ContactAdapter(Context context, List<Contact> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic;
        TextView fullname;
        TextView num_tel;
        TextView type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.fullname = (TextView) convertView
                    .findViewById(R.id.fullname);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_pic);
            holder.num_tel = (TextView) convertView.findViewById(R.id.num_tel);
            holder.type = (TextView) convertView
                    .findViewById(R.id.type);

            Contact contact = lists.get(position);

            holder.profile_pic.setImageResource(contact.getId());
            holder.fullname.setText(contact.getFullname());
            holder.num_tel.setText(contact.getNum_tel());
            holder.type.setText(contact.getType());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
