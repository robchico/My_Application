package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadosActivity extends Activity {

	private String web;
	private WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultados);

		Intent intent2 = getIntent();
		web = intent2.getStringExtra(MainActivity.key_web1);

		myWebView = (WebView) this.findViewById(R.id.webView1);

		WebSettings webSettings = myWebView.getSettings();

		myWebView.getSettings().setBuiltInZoomControls(true);

		webSettings.setJavaScriptEnabled(true);
		// myWebView.getSettings().setLoadWithOverviewMode(true);
		// myWebView.getSettings().setUseWideViewPort(true);
		// myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		// myWebView.setScrollbarFadingEnabled(false);

		myWebView.setInitialScale(40);
		// Provide a WebViewClient for your WebView
		//myWebView.setWebViewClient(new MyWebViewClient());
		// myWebView.loadUrl(web);
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				myWebView.setWebViewClient(new MyWebViewClient());
				myWebView.loadUrl(web);
			}
		});

		/*
		 * myWebView.post(new Runnable() {
		 * 
		 * @Override public void run() { myWebView.loadUrl(web); } });
		 */
		// "http://www.leboro.es/resultados.aspx"
		// "http://baloncestoenvivo.feb.es/"
		// http://www.leboro.es/resultados.aspx
		// http://competiciones.feb.es/estadisticas/Equipo.aspx?i=705470
		// http://www.feb.es/Pasarela/Controles/resultados.aspx

		ImageButton reCargar = (ImageButton) findViewById(R.id.imageButtonSearch);
		reCargar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Ap�ndice de m�todo generado autom�ticamente
				myWebView.loadUrl(web);// "http://www.leboro.es/resultados.aspx"
				// http://www.feb.es/Pasarela/Controles/resultados.aspxhttp://baloncestoenvivo.feb.es/
			}
		});

	}
	/*@Override
    public void onBackPressed() {
 
        // Check if there's history
        if (this.myWebView.canGoBack())
            this.myWebView.goBack();
        else
            super.onBackPressed();
 
    }*/
	

	private class MyWebViewClient extends WebViewClient {

		private long loadTime; // Web page loading time

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			if (Uri.parse(url).getHost().equals(web)) {// "http://www.leboro.es/resultados.aspx"
				// This is my web site, so do not override; let my WebView load
				// the page
				return false;
			}

			// Otherwise, the link is not for a page on my site, so launch
			// another Activity that handles URLs //lanzo un navegador externo para completar la operacion
			/*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);*/
			//navego dentro de mi webview
			myWebView.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);

			// Save start time
			this.loadTime = System.currentTimeMillis();

			// Show a toast
			// Toast.makeText(getApplicationContext(),
			// "La pagina se esta cargando...", Toast.LENGTH_LONG).show();
			Toast toast = Toast.makeText(getApplicationContext(),
					"La pagina se esta cargando...", Toast.LENGTH_LONG);
			View textView = toast.getView();
			LinearLayout lay = new LinearLayout(getApplicationContext());
			lay.setOrientation(LinearLayout.HORIZONTAL);
			ImageView view22 = new ImageView(getApplicationContext());
			view22.setImageResource(android.R.drawable.ic_dialog_info);
			lay.addView(view22);
			lay.addView(textView);
			toast.setView(lay);
			toast.show();
			// miToast("La pagina se esta cargando...");

		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);

			// Calculate load time
			this.loadTime = System.currentTimeMillis() - this.loadTime;

			// Convert milliseconds to date format
			String time = new SimpleDateFormat("mm:ss:SSS", Locale.getDefault())
					.format(new Date(this.loadTime));

			// Show a toast
			Toast.makeText(getApplicationContext(),
					"La pagina se ha cargado en..." + time + " s.",
					Toast.LENGTH_SHORT).show();

		}
	}

	public void miToast(String texto) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.custom_toast,
				(ViewGroup) findViewById(R.id.custom_toast_layout_id));
		// set a dummy image
		ImageView image = (ImageView) layout.findViewById(R.id.image);
		image.setImageResource(android.R.drawable.ic_dialog_info);
		// set a message
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(texto);
		// Toast...
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	    switch (keyCode) {
	        case KeyEvent.KEYCODE_BACK:
	        
	        	//Intent resul = new Intent(this, MainActivity.class);
				//startActivity(resul);
	        	myWebView.stopLoading();
				this.finish();
				break;
			default:
				break;
	        
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
