package com.kiiolabs.cally.view.Fragments;

import com.kiiolabs.cally.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class AboutFragment extends DialogFragment {
	private Context context;
	private String credit1 = "<a href=\"http://ismailzd.co.uk/\">Ismail Zaidi - Developer</a>";
	private String credit2 = "<a href=\"http://jahitjanberk.com//\">Jahit Janberk - Designer</a>";
	private TextView aboutTextView,link1TextView,link2TextView;
	private static String Colour_Key = "com.kiiolabs.cally.colour.about";
	public static AboutFragment InstanceOf(int colour_scheme) {
		AboutFragment fragment = new AboutFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(Colour_Key, colour_scheme);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View aboutDialog = inflater.inflate(com.kiiolabs.cally.R.layout.about_dialog_fragment, container, false);
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = getActivity().getApplicationContext();
		aboutTextView = (TextView) aboutDialog.findViewById(R.id.aboutTextView);
		link1TextView = (TextView) aboutDialog.findViewById(R.id.link1);
		link2TextView = (TextView) aboutDialog.findViewById(R.id.link2);
		link1TextView.setText(Html.fromHtml(credit1));
		link1TextView.setMovementMethod(LinkMovementMethod.getInstance());
		link2TextView.setText(Html.fromHtml(credit2));
		link2TextView.setMovementMethod(LinkMovementMethod.getInstance());
		return aboutDialog;
	}


}
