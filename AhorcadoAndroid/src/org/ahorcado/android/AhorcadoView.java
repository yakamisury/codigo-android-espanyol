package org.ahorcado.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AhorcadoView extends View {

	private int estado=0;

	
	public AhorcadoView(Context context) {
		super(context);
	}

	
	public AhorcadoView(Context context,AttributeSet attributeSet){
		super(context, attributeSet);
	}
	
    @Override
    protected void onDraw(Canvas canvas) {
    	super.onDraw(canvas);
    	Paint paint=new Paint();
    	paint.setColor(Color.WHITE);
    	paint.setStrokeWidth(5);
    	switch (estado) {
    	case 0:
    		dibujarhorca(canvas, paint);
    		break;
		case 1:
			dibujarhorca(canvas, paint);
			dibujarCabeza(canvas, paint);
			System.out.println("cabeza");
			
			break;
		case 2:
			dibujarhorca(canvas, paint);
			dibujarCabeza(canvas, paint);
			dibujarTorso(canvas, paint);
			System.out.println("cabeza y torso");
			break;
		case 3:
			dibujarhorca(canvas, paint);
			dibujarCabeza(canvas, paint);
			dibujarTorso(canvas, paint);
			dibujarBrazoDerecho(canvas, paint);
			System.out.println("cabeza, torso y brazo derecho");
			break;
		case 4:
			dibujarhorca(canvas, paint);
			dibujarCabeza(canvas, paint);
			dibujarTorso(canvas, paint);
			dibujarBrazoDerecho(canvas, paint);
			dibujarBrazoIzquierdo(canvas, paint);
			System.out.println("cabeza, torso, brazo derecho y brazo izquierdo");
			break;
		case 5:
			dibujarhorca(canvas, paint);
			dibujarCabeza(canvas, paint);
			dibujarTorso(canvas, paint);
			dibujarBrazoDerecho(canvas, paint);
			dibujarBrazoIzquierdo(canvas, paint);
			dibujarPiernaDerecha(canvas, paint);
			break;
		case 6:
			dibujarhorca(canvas, paint);
			dibujarCabeza(canvas, paint);
			dibujarTorso(canvas, paint);
			dibujarBrazoDerecho(canvas, paint);
			dibujarBrazoIzquierdo(canvas, paint);
			dibujarPiernaDerecha(canvas, paint);
			dibujarPiernaIzquierda(canvas, paint);
			break;
			
		}
    	
    	
    	
    	
    }
    public void setEstado(int fallo){
    	View mv=(View)findViewById(R.id.ahorcadoView);
    	estado=fallo;
    	mv.invalidate();
    	
    }
    
    private void dibujarCabeza(Canvas canvas, Paint paint){
    	canvas.drawCircle(37.5f, 40, 20, paint);
    }
    private void dibujarTorso(Canvas canvas,Paint paint){
    	canvas.drawRect(30, 60, 45, 120, paint);
    }
    private void dibujarBrazoDerecho(Canvas canvas,Paint paint){
    	canvas.drawLine(45, 60, 65, 90, paint);
    }
    private void dibujarBrazoIzquierdo(Canvas canvas,Paint paint){
    	canvas.drawLine(30, 60, 10, 90, paint);
    }
    private void dibujarPiernaIzquierda(Canvas canvas,Paint paint){
    	canvas.drawLine(30, 120, 10, 150, paint);
    }
    private void dibujarPiernaDerecha(Canvas canvas,Paint paint){
    	canvas.drawLine(45, 120, 65, 150, paint);
    }
    private void dibujarhorca(Canvas canvas, Paint paint){
    	canvas.drawLine(37.5f, 60, 37.5f, 5, paint);
    	canvas.drawLine(37.5f, 5, 99, 5, paint);
    	canvas.drawLine(99, 5, 99, 205, paint);
    	canvas.drawLine(80, 205, 120, 205, paint);
    	
    	
    }
    
}
