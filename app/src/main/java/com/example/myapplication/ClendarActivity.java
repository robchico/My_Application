package com.example.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import recursos.NavigationDrawerFragment;
import recursos.Partidos;

public class ClendarActivity extends FragmentActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	static TextView[] textViewArray;
	static int pulsado;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clendar);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
		/*
		 * android.support.v4.app.Fragment fragment=null;
		 * 
		 * switch (position) { // BOTON PLAY, PRIMERA POSICION = 0 case 0:
		 * Toast.makeText(getApplicationContext(), "Informacion de los Colores:"
		 * + "\n\tAzul: partido local" + "\n\tBlanco-naranja: partido visitante"
		 * + "\n\tGris: partido pasado" + "\n\tRojo: dia de partido",
		 * Toast.LENGTH_SHORT).show(); fragment = new Fragment_0(); break; case
		 * 1: fragment = new Fragment_0(); break; case 2: fragment = new
		 * Fragment_1(); break; case 3: fragment = new Fragment_2(); break;
		 * 
		 * 
		 * }
		 * 
		 * //HACEMOS USO DE LA LIBRERIA android.support.v4.app.FragmentManager
		 * fragmentManager=getSupportFragmentManager(); //REEMPLAZAMOS
		 * fragmentManager.beginTransaction().replace(R.id.container,
		 * fragment).commit();
		 */

	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			Toast.makeText(getApplicationContext(), "Informacion colores:"
					+ "\n\tGris: partidos pasados"
					+ "\n\tAzul: partidos locales"
					+ "\n\tBlanco-naranja: partidos visitantes"
					+ "\n\tRojo: dia de partido", Toast.LENGTH_SHORT).show();;
			break;
		case 2:
			pulsado = 2;

			mTitle = getString(R.string.title_section1);
			break;
		case 3:
			pulsado = 3;

			mTitle = getString(R.string.title_section2);
			break;
		case 4:
			pulsado = 4;

			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			// getMenuInflater().inflate(R.menu.clendar, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		/*
		 * if (id == R.id.action_settings) { return true; }
		 */
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_clendar,
					container, false);
			
			Partidos p = new Partidos();
			textViewArray = new TextView[p.getPartidos().length];
			
			int uno = R.id.textView1;
			for (int i = 0; i < p.getPartidos().length; i++) {
				textViewArray[i] = (TextView) rootView.findViewById(uno + i);
			}
			
			if (pulsado == 3) {
				for (int i = 0; i < p.getPartidos().length; i++) {
					if (i == 0 || i == 2 || i == 4 || i == 6 || i == 9
							|| i == 11 || i == 13 || i == 16 || i == 18
							|| i == 20 || i == 22 || i == 23 || i == 25
							|| i == 27 || i == 29) {
						textViewArray[i].setVisibility(View.VISIBLE);

					} else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8
							|| i == 10 || i == 12 || i == 14 || i == 15
							|| i == 17 || i == 19 || i == 21 || i == 23
							|| i == 24 || i == 26 || i == 28 || i == 30) {
						textViewArray[i].setVisibility(View.GONE);
					}
				}
			}
			if (pulsado == 4) {
				for (int i = 0; i < p.getPartidos().length; i++) {
					if (i == 0 || i == 2 || i == 4 || i == 6 || i == 9
							|| i == 11 || i == 13 || i == 16 || i == 18
							|| i == 20 || i == 22 || i == 23 || i == 25
							|| i == 27 || i == 29) {
						textViewArray[i].setVisibility(View.GONE);

					} else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8
							|| i == 10 || i == 12 || i == 14 || i == 15
							|| i == 17 || i == 19 || i == 21 || i == 23
							|| i == 24 || i == 26 || i == 28 || i == 30) {
						textViewArray[i].setVisibility(View.VISIBLE);
					}
				}
			}
			Date fecha = new Date();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");

			try {
				for (int i = 0; i < p.getPartidos().length; i++) {
					Date fechaPartido = mdyFormat.parse(p.getFecha(i));
					if (fechaPartido.before(fecha)) {
						textViewArray[i].setBackgroundResource(R.drawable.fecha_pasada);
					}
				}
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}

			for (int i = 0; i < p.getPartidos().length; i++) {
				if (mdyFormat.format(fecha).equals(p.getFecha(i))) {
					textViewArray[i].setBackgroundResource(R.drawable.fechactual);
				}

			}
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((ClendarActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}
	}
}
