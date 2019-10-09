package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import recursos.CustomGridViewAdapter;
import recursos.Item;

public class PlantillaActivity extends Activity {

	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	CustomGridViewAdapter customGridAdapter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plantilla);
		
		// set grid view item
		Bitmap imaEscudo = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.miraflores3);
		Bitmap imaEntrenador = BitmapFactory.decodeResource(
				this.getResources(), R.drawable.fotoentre2);
		Bitmap ima2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto2);
		Bitmap ima5 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto5);
		Bitmap ima7 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto7);
		Bitmap ima8 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto8);
		Bitmap ima9 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto9);
		Bitmap ima10 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto10);
		Bitmap ima12 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto12);
		Bitmap ima13 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto13);
		Bitmap ima15 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto15);
		Bitmap ima19 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.foto19);

		gridArray.add(new Item(imaEscudo, "C.B. Miraflores"));
		gridArray.add(new Item(imaEntrenador, "Andreu Casadevall Guinart"));
		gridArray.add(new Item(ima2, "Matija Poscic"));
		gridArray.add(new Item(ima5, "Rafael Huertas Navajas"));
		gridArray.add(new Item(ima7, "Roger Vilanova Pi"));
		gridArray.add(new Item(ima8, "Anton Maresch"));
		gridArray.add(new Item(ima9, "Javier Vega Merayo"));
		gridArray.add(new Item(ima10, "Justas Sinica "));
		gridArray.add(new Item(ima12, "Augustas \nPeciukevicius"));
		gridArray.add(new Item(ima13, "Filip Toncinic"));
		gridArray.add(new Item(ima15, "Eduardo Martinez Balmaseda"));
		gridArray.add(new Item(ima19, "Roger Fornas Llado"));

		gridView = (GridView) findViewById(R.id.gridViewPlantilla);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,gridArray);
		
		gridView.setAdapter(customGridAdapter);
	
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				//onCreateDialog();
				 switch (position) {
				 	case 0:
				 		Toast.makeText(getBaseContext(), "Consulta la informacion del club", Toast.LENGTH_SHORT).show();
				 		break;
		            case 1:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.fotoentre2aspx, "Andreu Casadevall Guinart",
		            			"01/01/1962 \nSanta Coloma de Gramenet (Barcelona)", R.array.entre_trayec,
		            			R.array.entre_palmares);
		            	break;
		            case 2:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto2, "Matija Poscic",
		            			"Posici�n: P�vot\n"
		            					+ "Dorsal: 2\n"
		            					+ "02/12/1985 Croacia\n"
		            					+ "Altura: 196 cm\n"
		            					+ "Peso: - Kg", R.array.j2_trayec,R.array.vacio);
		            	break;
		            case 3:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto5, "Rafael Huertas Navajas", "Posici�n: Escolta\n"
            					+ "Dorsal: 5\n"
            					+ "02/08/1984 C�rdoba\n"
            					+ "Altura: 191 cm\n"
            					+ "Peso: - Kg",R.array.j5_trayec,R.array.j5_palmares);
			            	break;
		            case 4:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto7, "Roger Vilanova Pi", "Posici�n: Base\n"
            					+ "Dorsal: 7\n"
            					+ "07/07/1987 Olesa (Barcelona)\n"
            					+ "Altura: 180 cm\n"
            					+ "Peso: - Kg",R.array.j7_trayec,R.array.j7_palmares);
			            
			            	break;
		            case 5:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto8, "Anton Maresch", "Posici�n: Escolta\n"
            					+ "Dorsal: 8\n"
            					+ " 08/08/1991 Graz (Austria)\n"
            					+ "Altura: 191 cm\n"
            					+ "Peso: - Kg",R.array.j8_trayec,R.array.j8_palmares);
			            
			            	break;
		            case 6:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto9, "Javi Vega Merayo", "Posici�n: A-P�vot\n"
            					+ "Dorsal: 9\n"
            					+ "05/01/1988 Legan�s (Madrid)\n"
            					+ "Altura: 205 cm\n"
            					+ "Peso: 98 Kg",R.array.j9_trayec,R.array.j9_palmares);
			            
			            	break;
		            case 7:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto10, "Justas Sinica", "Posici�n: Alero\n"
            					+ "Dorsal: 10\n"
            					+ "31/05/1985 Zarasai (Lituania)\n"
            					+ "Altura: 203 cm\n"
            					+ "Peso: 94 Kg",R.array.j10_trayec,R.array.vacio);
			            
			            	break;
		            case 8:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto12, "Augustas Peciukevicius", "Posici�n: Base\n"
            					+ "Dorsal: 12\n"
            					+ "02/12/1991 Vilna (Lituania)\n"
            					+ "Altura: 192cm\n"
            					+ "Peso: 94Kg",R.array.j12_trayec,R.array.vacio);
			           
			            	break;
		            case 9:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto13, "Filip Toncinic", "Posici�n: P�vot\n"
            					+ "Dorsal: 13\n"
            					+ "13/11/1984 (Croacia)\n"
            					+ "Altura: 208 cm\n"
            					+ "Peso: 113 Kg",R.array.j13_trayec,R.array.vacio);
			            
			            	break;
		            case 10:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto15, "Eduardo Mart�nez Balmaseda",  "Posici�n: Alero\n"
            					+ "Dorsal: 15\n"
            					+ "05/05/1990 Logro�o (La Rioja)\n"
            					+ "Altura: 202 cm\n"
            					+ "Peso: - Kg",R.array.j15_trayec,R.array.j15_palmares);
			        
			            	break;
		            case 11:
		            	creaDialogo2(R.layout.layout_plantilla, R.drawable.foto19, "Roger Fornas Llado",  "Posici�n: A-P�vot\n"
            					+ "Dorsal: 19\n"
            					+ "24/10/1982 Barcelona\n"
            					+ "Altura: 202 cm\n"
            					+ "Peso: - Kg",R.array.j19_trayec,R.array.j19_palmares);
			           
			            	break;
		            default:
		                break;
				 }   	
			}
		});		
	}
		
	private void creaDialogo2(int layout,int imagen,String nombre,String text,int arr1,int arr2) {
		Dialog dialog = new Dialog(PlantillaActivity.this);
		dialog.setContentView(layout);
		dialog.setTitle(nombre);
		dialog.setCancelable(true);
	
		TextView text1 = (TextView) dialog.findViewById(R.id.textView1Plan);
		text1.setText(text);
		
		//set up image view
		ImageView img = (ImageView) dialog.findViewById(R.id.imageView1Plan);
		img.setImageResource(imagen);
		
		
		ListView list2=(ListView)dialog.findViewById(R.id.listView2Plan);
		Resources res = this.getResources();
		
		String[] trayect = res.getStringArray(arr1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, trayect);
		list2.setAdapter(adaptador);
		
		ListView list3=(ListView)dialog.findViewById(R.id.listView3Plan);
		Resources res2 = this.getResources();
		String[] palm = res2.getStringArray(arr2);
		ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, palm);
		list3.setAdapter(adaptador2);
		
		dialog.setCancelable(true);
		
		//now that the dialog is set up, it's time to show it    
		dialog.show();
	}
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	    switch (keyCode) {
	        case KeyEvent.KEYCODE_BACK:
	        {
	        	//Intent resul = new Intent(this, MainActivity.class);
				//startActivity(resul);
				this.finish();
	        }
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
