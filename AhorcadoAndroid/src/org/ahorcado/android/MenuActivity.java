package org.ahorcado.android;

import android.app.Activity;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity  extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button juegobutton=(Button) findViewById(R.id.juegoNuevoButton);
        Button opcionesbutton=(Button) findViewById(R.id.opcionesButton);
        Button infobutton=(Button) findViewById(R.id.informacionButton);
        MyButtonListener mbl = new MyButtonListener();
        juegobutton.setOnClickListener(mbl);
        opcionesbutton.setOnClickListener(mbl);
        infobutton.setOnClickListener(mbl);
        
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu_ahorcado, menu);
		return true;		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId()== R.id.nuevoJuegoItem)
		{
			Intent intent=new Intent(getApplicationContext(),AhorcadoAndroidActivity.class);
			startActivity(intent);
		}
		if (item.getItemId()== R.id.informacionItem)
		{
			Intent intent=new Intent(getApplicationContext(),InformacionActivity.class);
			startActivity(intent);
		}
		if (item.getItemId()== R.id.opcionesItem)
		{
			Intent intent=new Intent(getApplicationContext(),OpcionesActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	class MyButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if (v.getId()== R.id.juegoNuevoButton)
			{
				Intent intent=new Intent(v.getContext(),Menu2JugadoresActivity.class);
				System.out.println("nuevo juevo");
				startActivity(intent);
			}
			if (v.getId()== R.id.informacionButton)
			{
				Intent intent=new Intent(v.getContext(),InformacionActivity.class);
				System.out.println("informacion");
				startActivity(intent);
			}
			if (v.getId()== R.id.opcionesButton)
			{
				Intent intent=new Intent(v.getContext(),OpcionesActivity.class);
				System.out.println("opciones");
				startActivity(intent);
			}
		}
		
	}
	
}
