package org.ahorcado.android;

import org.ahorcado.android.MenuActivity.MyButtonListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu2JugadoresActivity extends Activity {
	private int recogerPalabra=0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu2jugadores);
        Button jugarSolobutton=(Button) findViewById(R.id.jugarSoloButton);
        Button dosJugadoresbutton=(Button) findViewById(R.id.dosJugadoresButton);
        MyButtonListener mbl = new MyButtonListener();
        jugarSolobutton.setOnClickListener(mbl);
        dosJugadoresbutton.setOnClickListener(mbl);
       
        
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==recogerPalabra){
			Intent intent=new Intent(getApplicationContext(),AhorcadoAndroidActivity.class);
			intent.putExtra("pal", data.getExtras().getString("pal"));
			startActivity(intent);
		}
	}
	
	class MyButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if (v.getId()== R.id.jugarSoloButton)
			{
				Intent intent=new Intent(v.getContext(),AhorcadoAndroidActivity.class);
				startActivity(intent);
			}
			if (v.getId()== R.id.dosJugadoresButton)
			{
				Intent intent=new Intent(v.getContext(),DosJugadoresActivity.class);
				startActivityForResult(intent, recogerPalabra);
			}
		}
	}
}
