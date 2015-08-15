package com.kiiolabs.cally.adapter;

import java.util.List;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.model.bean.ColourScheme;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomColourListViewAdapter extends ArrayAdapter<ColourScheme> {
	private List<ColourScheme> objects;
	private FragmentActivity context;
	private int colour_scheme;

	public CustomColourListViewAdapter(FragmentActivity context, List<ColourScheme> objects,int colour_scheme) {
		super(context, com.kiiolabs.cally.R.layout.drawer_list_item, objects);
		this.objects = objects;
		this.context = context;
		this.colour_scheme=colour_scheme;

	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.custom_list_item_color, null, false);
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		ImageView imgTick = (ImageView) convertView.findViewById(R.id.tick);
		imgIcon.setImageResource(objects.get(position).getIcon());
		txtTitle.setText(objects.get(position).getTitle());
		txtTitle.setTextColor(context.getResources().getColor(objects.get(position).getColor()));
		imgTick.setImageResource(objects.get(position).getTick());
		Typeface font = Typeface.createFromAsset(context.getAssets(), "MankSans-Medium.ttf");
		txtTitle.setTypeface(font);
		if(position==colour_scheme){
			imgTick.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
}
