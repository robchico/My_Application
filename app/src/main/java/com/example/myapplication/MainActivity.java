package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.recursos.Partidos;
import com.example.myapplication.recursos.XMLParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends Activity implements OnClickListener {

	// Variables de la notificacion
	private NotificationManager nm;
	private Notification notif;
	int icono_r = R.drawable.miraflores3;
	static String ns = Context.NOTIFICATION_SERVICE;
	private Partidos p = new Partidos();
	static int contador = 0;

	public static final String key_web1 = "value";
	public static final String DATA_TITLE = "T";
	public static final String DATA_LINK = "L";
	public static final String DATA_DATE = "D";
	public static LinkedList<HashMap<String, String>> data;
	static String feedUrl = "http://www.leboro.es/servicios/rss.aspx?c=3";
	//private ProgressDialog progressDialog;
	private ListView lv;
	private SimpleAdapter sAdapter;

	private MiTareaAsincrona tarea;
	private CheckBox checkActivarRss;
	private ImageView imgTodos;
	private final Handler progressHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {

			if (msg.obj != null) {

				data = (LinkedList<HashMap<String, String>>) msg.obj;
				setData(data);
			}
			//progressDialog.dismiss();
			tarea.cancel(true);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btVirtual = (Button) findViewById(R.id.ButtonVirtual);
		Button btCalendario = (Button) findViewById(R.id.ButtonCalendario);
		Button btPlantilla = (Button) findViewById(R.id.ButtonPlantilla);
		Button btInfo = (Button) findViewById(R.id.ButtonInfo);
		Button btClasf = (Button) findViewById(R.id.ButtonClasif);
		Button btRanking = (Button) findViewById(R.id.ButtonRankigs);
		
		lv = (ListView) findViewById(R.id.lstData);
		checkActivarRss=(CheckBox)findViewById(R.id.checkBox1);
		
		boolean acultar=loadSavedPreferences();
		if (!acultar) {
			lv.setBackgroundResource(R.drawable.imagentodos);
		}
		checkActivarRss.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				savePreferences("KEY", checkActivarRss.isChecked());
				if(checkActivarRss.isChecked() && isOnline()){
					
					lv.setBackgroundResource(R.drawable.borde3);
					loadData();
				}
			}
		});
		
		if (!isOnline()) {
			btRanking.setEnabled(false);
			// btRanking.setBackgroundResource(R.drawable.button_selector);
			btVirtual.setEnabled(false);
			btClasf.setEnabled(false);
			/*btRanking.setBackgroundColor(R.drawable.button_selector);
			btClasf.setBackgroundColor(R.drawable.button_selector);
			btVirtual.setBackgroundColor(R.drawable.button_selector);*/
			
		}
		btRanking.setOnClickListener(this);
		btClasf.setOnClickListener(this);
		btVirtual.setOnClickListener(this);

		btCalendario.setOnClickListener(this);
		btPlantilla.setOnClickListener(this);
		btInfo.setOnClickListener(this);
		
		if (isOnline()) {
			if (lv.getAdapter() != null) {
				/*
				 * AlertDialog.Builder builder = new AlertDialog.Builder(
				 * MainActivity.this); builder.setMessage("loading...?")
				 * .setCancelable(false) .setPositiveButton("Si", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface dialog, int
				 * which) { loadData(); } }) .setNegativeButton("No", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * }).create().show();
				 */
			} else {
				if(loadSavedPreferences()){
					lv.setBackgroundResource(R.drawable.borde3);
					loadData();
				}else{
					Toast.makeText(getBaseContext(), "Noticias desactivadas", Toast.LENGTH_SHORT).show();
				}
				
			}

			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> av, View v,
						int position, long id) {
					HashMap<String, String> entry = data.get(position);

					Intent intent1 = new Intent(MainActivity.this,
							ResultadosActivity.class);
					intent1.putExtra(key_web1, entry.get(DATA_LINK));
					startActivityForResult(intent1, 3);

				}
			});

		} else {

			/*final String[] sinConexion = { "Sin Conexi�n",
					"Para disfrutar de toda la funcionalidad de la app",
					"Activa la conectividad y reinicia la app" };*/
			/*final String[] sinConexion = {""};
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
					android.R.layout.select_dialog_item, sinConexion);

			// asociamos el adaptador a la vista.
			ListView myListView = (ListView) findViewById(R.id.lstData);
			myListView.setBackgroundResource(R.drawable.cb_miraflores4);
			myListView.setAdapter(adaptador);*/
			lv.setBackgroundResource(R.drawable.cb_miraflores4);
			Toast.makeText(getBaseContext(), "Sin conexi�n", Toast.LENGTH_SHORT).show();
		}

		Date fecha = new Date();
		SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");

		for (int i = 0; i < p.getPartidos().length; i++) {

			if (mdyFormat.format(fecha).equals(p.getFecha(i))) {
				if (contador == 0) {
					// Inicio el servicio de notificaciones accediendo al
					// servicio
					nm = (NotificationManager) getSystemService(ns);
					// Realizo una notificacion por medio de un metodo hecho por
					// mi
					notificacion(icono_r, "Jornada " + (i + 1),
							p.getEquipos(i), " Fecha " + p.getFecha(i));
					// Lanzo la notificacion creada en el paso anterior
					nm.notify(1, notif);
				}
				break;
			}
		}
	}

	private boolean loadSavedPreferences() {
		
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			boolean checkBoxValue = sharedPreferences.getBoolean("KEY", true);
			
			if (checkBoxValue) {
				checkActivarRss.setChecked(true);
			} else {
				checkActivarRss.setChecked(false);
			}
			return checkBoxValue;
	}
	private void savePreferences(String key, boolean value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
		
	

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.ButtonRankigs) {
			if (!isOnline()) {
				Toast.makeText(getApplicationContext(),
						"Necesitas conexion para acceder a los rankings",
						Toast.LENGTH_SHORT).show();
			} else {
				Intent intent1 = new Intent(MainActivity.this,
						ResultadosActivity.class);
				intent1.putExtra(key_web1,
						" http://www.leboro.es/rankings.aspx");//http://competiciones.feb.es/estadisticas/Rankings.aspx
				startActivityForResult(intent1, 2);

			}

		}
		if (v.getId() == R.id.ButtonCalendario) {
			Intent resul = new Intent(this, C_Activity.class);
			startActivity(resul);

			// this.finish();
		}
		if (v.getId() == R.id.ButtonClasif) {
			if (!isOnline()) {
				Toast.makeText(getApplicationContext(),
						"Necesitas conexion para acceder a la clasificaci�n",
						Toast.LENGTH_SHORT).show();
			} else {
				Intent intent1 = new Intent(MainActivity.this,
						ResultadosActivity.class);
				intent1.putExtra(key_web1,
						"http://www.leboro.es/clasificacion.aspx");
				//"http://competiciones.feb.es/estadisticas/Resultados.aspx?g=1&t=2015"
				startActivityForResult(intent1, 0);
				// this.finish();
			}
		}
		if (v.getId() == R.id.ButtonPlantilla) {
			Intent resul = new Intent(this, PlantillaActivity.class);
			startActivity(resul);
			// this.finish();
		}
		if (v.getId() == R.id.ButtonVirtual) {
			if (!isOnline()) {
				Toast.makeText(getApplicationContext(),
						"Necesitas conexion para acceder a la jornada virtual",
						Toast.LENGTH_SHORT).show();
			} else {
				Intent intent1 = new Intent(MainActivity.this,
						ResultadosActivity.class);
				intent1.putExtra(key_web1, "http://baloncestoenvivo.feb.es/");// http://www.leboro.es/resultados.aspx
				startActivityForResult(intent1, 1);
				// this.finish();
			}
		}
		if (v.getId() == R.id.ButtonInfo) {
			creaDialogo();

		}
	}

	private void creaDialogo() {

		Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.infor);
		dialog.setTitle("C.B. Miraflores Burgos");

		dialog.setCancelable(true);

		ImageView img = (ImageView) dialog.findViewById(R.id.imageView1Info);
		img.setImageResource(R.drawable.cb_miraflores4);
		img.setBackgroundResource(R.drawable.borde);
		dialog.setCancelable(true);


		dialog.show();
	}

	public void notificacion(int icon, CharSequence textoEstado,
			CharSequence titulo, CharSequence texto) {
		
		// Capturo la hora del evento
		long hora = System.currentTimeMillis();

		contador = 1;

		// Definimos la accion de la pulsacion sobre la notificacion (esto es
		// opcional)
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, ClendarActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		// Defino la notificacion, icono, texto y hora
		notif = new Notification(icon, textoEstado, hora);
		//notif.setLatestEventInfo(context, titulo, texto, contentIntent);

		// Defino que la notificacion sea permamente
		// notif.flags = Notification.FLAG_ONGOING_EVENT; //para cancelar
		// nm.cancel(1);
		notif.flags = Notification.FLAG_AUTO_CANCEL;
	}

	private void setData(LinkedList<HashMap<String, String>> data) {

	/*	sAdapter = new SimpleAdapter(getApplicationContext(), data,
				android.R.layout.two_line_list_item, new String[] { DATA_TITLE,
						DATA_DATE }, new int[] { android.R.id.text1,
						android.R.id.text2 });*/
		//poner mi layout personalizado
		sAdapter = new SimpleAdapter(getApplicationContext(), data,
				R.layout.row_list, new String[] { DATA_TITLE,
						DATA_DATE }, new int[] { R.id.textView1ListRow,
						R.id.textView2ListRow });
		
		lv = (ListView) findViewById(R.id.lstData);

		lv.setAdapter(sAdapter);

	}

	private void loadData() {
		//progressDialog = ProgressDialog.show(MainActivity.this, "",	"Por favor espere mientras se cargan los datos...", true);

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				XMLParser parser = new XMLParser(feedUrl);
				Message msg = progressHandler.obtainMessage();
				msg.obj = parser.parse();
				progressHandler.sendMessage(msg);
			}
		}).start();*/
		try{
			tarea=new MiTareaAsincrona();
			tarea.execute();
		}catch(Exception e){
			tarea.cancel(true);
		}
		
		
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	@Override
	public void onPause() {
		super.onPause(); // Always call the superclass method first
		contador = 1;
	}

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		contador = 1;
	}

	@Override
	public void onStop() {
		super.onStop(); // Always call the superclass method first
		contador = 1;
	}

	@Override
	public void onStart() {
		super.onStart(); // Always call the superclass method first
		contador = 1;
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		contador = 1;
	}

	private class MiTareaAsincrona extends AsyncTask<Void , Integer, Boolean>{

		public ProgressDialog progressDialog;
		@Override
		protected Boolean doInBackground(Void... params) {
			
			try{
    			XMLParser parser = new XMLParser(feedUrl);
    			Message msg = progressHandler.obtainMessage();
    			msg.obj = parser.parse();
    			progressHandler.sendMessage(msg);
    			
    		}catch(Exception e){
    			progressDialog.dismiss();
    			tarea.cancel(true);
    		}finally{
    			progressDialog.dismiss();
    			tarea.cancel(true);
    		}
						
			return true;
		}
		@Override
    	protected void onPreExecute() {
    		try{
    			progressDialog = ProgressDialog.show(MainActivity.this, "",	"Por favor espere mientras se cargan los datos...", true);
    			progressDialog.setCancelable(true);
    		}catch(Exception e){
    			progressDialog.dismiss();
    		}
			
    	}
		
		@Override
    	protected void onPostExecute(Boolean result) {
    		if(result)
    			//Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
    			progressDialog.dismiss();
    	}
    	
    	@Override
    	protected void onCancelled() {
    		//Toast.makeText(MainActivity.this, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
    		tarea.cancel(true);
    		progressDialog.dismiss();
    	}

		
	}
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	    switch (keyCode) {
	        case KeyEvent.KEYCODE_BACK:
	        	if(tarea!=null){
	        		
	        		//Log.e("keydaw", ""+true);
	        		//progressDialog.dismiss();
	        		tarea.cancel(true);
	        		
	        	}
	        	break;
	        default:
	        	break;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
