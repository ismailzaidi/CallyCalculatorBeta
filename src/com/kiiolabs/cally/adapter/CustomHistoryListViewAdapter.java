package com.kiiolabs.cally.adapter;

import java.util.List;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.model.bean.ColourScheme;
import com.kiiolabs.cally.model.bean.HistoryCalculation;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomHistoryListViewAdapter extends ArrayAdapter<HistoryCalculation> {
	private List<HistoryCalculation> objects;
	private FragmentActivity context;
	private int colour;

	public CustomHistoryListViewAdapter(FragmentActivity context, List<HistoryCalculation> objects, int colour_scheme) {
		super(context, com.kiiolabs.cally.R.layout.custom_history_item, objects);
		this.objects = objects;
		this.context = context;
		this.colour = colour_scheme;

	}
	private static class ViewHolder {
		TextView historyExpressionTextView, historyAnswerTextView, historyTimeTextView;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.custom_history_item, null, false);
		ViewHolder holder = new ViewHolder();
		holder.historyExpressionTextView = (TextView) convertView.findViewById(R.id.historyExpression);
		holder.historyAnswerTextView = (TextView) convertView.findViewById(R.id.historyAnswer);
		holder.historyTimeTextView = (TextView) convertView.findViewById(R.id.historyDate);

		holder.historyExpressionTextView.setText("Expression: "+objects.get(position).getExpression());
		holder.historyAnswerTextView.setText("Answer: "+objects.get(position).getAnswer());
		holder.historyTimeTextView.setText("Date Taken: "+objects.get(position).getTime());
		holder.historyExpressionTextView.setTextColor(context.getResources().getColor(colour));
		holder.historyAnswerTextView.setTextColor(context.getResources().getColor(colour));
		holder.historyTimeTextView.setTextColor(context.getResources().getColor(colour));

		Typeface font = Typeface.createFromAsset(context.getAssets(), "MankSans-Medium.ttf");
		holder.historyExpressionTextView.setTypeface(font);
		holder.historyAnswerTextView.setTypeface(font);
		holder.historyTimeTextView.setTypeface(font);

		return convertView;
	}
}
