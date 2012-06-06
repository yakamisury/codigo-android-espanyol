package org.ahorcado.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AhorcadoAndroidActivity extends Activity {
	private EditText letraEditText;
	private TextView estadoTextView;
	private TextView palabraTextView;
	private TextView fallosTextView;
	private Set<String> letrasAcertadas = new HashSet();
	private Set<String> letrasErroneas = new HashSet<String>();
	private ArrayList<String> palabras = new ArrayList<String>();
	private String palabraSeleccionada;
	private Integer fallos = 0;
	private AhorcadoView ahorcadoView;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.letraButton);
		letraEditText = (EditText) findViewById(R.id.letraEditText);
		// estadoTextView = (TextView) findViewById(R.id.estadoView);
		palabraTextView = (TextView) findViewById(R.id.palabraView);
		fallosTextView = (TextView) findViewById(R.id.fallosView);
		ahorcadoView = (AhorcadoView) findViewById(R.id.ahorcadoView);
		MyClickListener l = new MyClickListener();
		button.setOnClickListener(l);
		//creamos un objeto de tipo intetnt para guardar la informacion del intent
		Intent intent = getIntent();
		//si al crear la actividad no se ha llamado al metodo intent.putExtra para pasar informacion
		//intent.getExtra sera nulo
		if (intent.getExtras() != null) {
			//guardamos en la variable palabra el valor asociado al identificador pal de los extras del item
			String palabra = (String) intent.getExtras().get("pal");
			palabraSeleccionada=palabra;
		} else {
			cargarFichero();
			Random r = new Random();
			int pos = r.nextInt(palabras.size());
			palabraSeleccionada = palabras.get(pos);
			}
		String palabraTransformada = transformarPalabra();
		palabraTextView.setText(palabraTransformada);
		fallosTextView.setText("");
	
	}

	private void cargarFichero() {
		Scanner scannerFichero;
		try {
			// Creamos un scanner a partir de un fichero utilzando el
			// constructor y la clase File

			scannerFichero = new Scanner(getAssets().open("palabras2.txt"));// ojo!!!!!!!
			// creamos un bucle para ir obteniendo las palabras del fichero y
			// añadirlas a la lista de palabras
			while (scannerFichero.hasNext()) {
				palabras.add(scannerFichero.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_ahorcado, menu);
		return true;
	}

	@Override
	// creamos los botones a seleccionar mientras estas jugando
	public boolean onOptionsItemSelected(MenuItem item) {
		// se crea el boton de nuevo juego(por si quieres cambiar la palabra a
		// adivinar)
		if (item.getItemId() == R.id.nuevoJuegoItem) {
			Intent intent = new Intent(getApplicationContext(),
					AhorcadoAndroidActivity.class);
			finish();
			startActivity(intent);
		}
		// entramos en el apartado de informacion del juego la partida se
		// cerrara
		if (item.getItemId() == R.id.informacionItem) {
			Intent intent = new Intent(getApplicationContext(),
					InformacionActivity.class);
			startActivity(intent);
		}
		// entramos en el apartado opciones
		if (item.getItemId() == R.id.opcionesItem) {
			Intent intent = new Intent(getApplicationContext(),
					OpcionesActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * metodo con el cual transformamos la palabra del juego a guiones bajos.
	 * 
	 * @return el resultado
	 */
	public String transformarPalabra() {
		String resultado = "";
		// recorremos la plabra seleccionada con un bucle for
		for (int x = 0; x < palabraSeleccionada.length(); x++) {
			String caracter = "" + palabraSeleccionada.charAt(x);
			// creamos una variable booleana para faber si se ha encontrado un
			// caracter de la palabra seleccionada
			boolean encontradoCaracter = false;
			// hacemos un bucle for each en el que se comprueba si realmente
			// esta la letra introducida
			// en la palabra seleccionada.
			for (String letra : letrasAcertadas) {
				if (letra.equals(caracter)) {
					encontradoCaracter = true;
				}
			}
			// si la letra introducida no esta en la palabra se coloca un "_" en
			// el lugar correspondiente
			if (encontradoCaracter) {
				resultado += caracter + " ";
			} else {
				resultado += "_ ";
			}
		}
		return resultado;
	}

	// generamos un arraylist para guardar las letras que se han fallado
	// que posteriormente se muestran al jugador.
	public String generarCadenaFallos() {
		String resultado = "";
		// separamos las letras erroneas con una ", y un espacio".
		for (String letra : letrasErroneas) {
			resultado += letra + ", ";
		}
		return resultado;
	}

	/**
	 * metodo que compueba la letra introducida y la letra que hay en la palabra
	 * 
	 */
	public void comprobarLetra(String letra) {
		boolean encontradoCaracter = false;
		// recorremos la palabra en busca del caracter introducido
		for (int x = 0; x < palabraSeleccionada.length(); x++) {
			String caracter = "" + palabraSeleccionada.charAt(x);
			// si el caracter es igual a la letra introducidapondremos el
			// boolean
			// encontradoCaracter a "true"
			if (caracter.equals(letra)) {
				encontradoCaracter = true;
			}
		}
		// si la letra esta en la palabra la añadimos
		if (encontradoCaracter) {
			letrasAcertadas.add(letra);
			// si la palabra no contiene "_" nos saldra un mensaje de victoria
			if (!transformarPalabra().contains("_")) {
				// creamos un objeto de clase alertdialog
				AlertDialog dialog = new AlertDialog.Builder(this).create();
				// mostramos un mensaje para que aparezca cuando ganamos
				dialog.setTitle("Quueeeee Façiiiill!!!!!!");
				// creamos un boton que al pulsarlo termina el juego
				dialog.setButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					// metodo que se ejecuta cuando presionamos el boton del
					// alertdialog
					public void onClick(DialogInterface dialog, int which) {
						// salimos del juego
						System.exit(0);
					}
				});
				// mostramos todo lo que contiene el dialogo
				dialog.show();
			}
			// si la letra no se encuentra en la palabrala añadira al arraylist
			// de letras erroneas
		} else {
			letrasErroneas.add(letra);
			fallos++;
			ahorcadoView.setEstado(fallos);
		}

		// aqui establecemos el valor de palabraTextView al pararlo por el
		// metodo transformarPalabra();
		palabraTextView.setText(transformarPalabra());
		// establecemos el valor de fallosTextViewal pasarlo por el metodo
		// generarCadenaFallos();
		fallosTextView.setText(generarCadenaFallos());

		// estadoTextView.setText("" + fallos);

		// ahorcadoView.setEstado(fallos);

		// dibujamos en el view con canvas
		ahorcadoView.refreshDrawableState();
		// si el numero de fallos es = a 6 aparece un alertdialog indicando
		// que has perdido
		if (fallos == 6) {
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			dialog.setTitle("Has perdido la palabra era: "
					+ palabraSeleccionada);
			dialog.setButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					System.exit(0);
				}
			});
			dialog.show();
		}
	}

	class MyClickListener implements OnClickListener {

		@Override
		// metodo con el cual hacemos que el boton validar letra funcione y
		// ponga de nuevo el campo de texto en blanco para recibir la siguiente
		// letra
		public void onClick(View arg0) {
			String aux = letraEditText.getText().toString();
			comprobarLetra(aux);
			letraEditText.setText("");

		}
	}
}