package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_0 extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup contenedor,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_clendar, contenedor, false);
		
		return v;
	}

	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		//View V = getView();
	
	}

}
