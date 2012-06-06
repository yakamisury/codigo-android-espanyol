package org.ahorcado.android;

import org.ahorcado.android.Menu2JugadoresActivity.MyButtonListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DosJugadoresActivity extends Activity{
	private EditText palabraIntroducidaEditText;
	private String palabras="";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.dosjugadores);
        Button aJugarButton=(Button) findViewById(R.id.aJugarButton);
        palabraIntroducidaEditText = (EditText) findViewById(R.id.palabraIntroducidaEditText);
        MyButtonListener mbl = new MyButtonListener();
        aJugarButton.setOnClickListener(mbl);     
	}
	class MyButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if (v.getId()== R.id.aJugarButton)
			{
				palabras=palabraIntroducidaEditText.getText().toString();
				
			/*	Intent intent=new Intent(v.getContext(),AhorcadoAndroidActivity.class);
				//para pasarle a la nueva actividad un parametro utilizamos el metodo putExtra
				intent.putExtra("pal", palabras);
				startActivity(intent);*/
				
				
				// Metodo 2 startActivityforResult
				
				Intent intent=getIntent();
				intent.putExtra("pal", palabras);
				setResult(RESULT_OK, intent);
				finish();
				
			}
			
		}
	}
	
	
}
