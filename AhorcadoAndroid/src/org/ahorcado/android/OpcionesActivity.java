package org.ahorcado.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OpcionesActivity extends Activity{
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.opciones);
	        
	 }
	 
	 
	 @Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	
		menu.removeItem(R.id.opcionesItem); 
		return super.onPrepareOptionsMenu(menu);
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
		
}
