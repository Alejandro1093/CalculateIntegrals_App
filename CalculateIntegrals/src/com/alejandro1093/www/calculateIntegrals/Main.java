package com.alejandro1093.www.calculateIntegrals;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {

	//Declaro variables de entrada
	String intervaloInf, intervaloSup, subintervalos = null;
	
	//Declaro las areas de entrada y el area de salida
	EditText IntervaloInferior, IntervaloSuperior, Subintervalos = null;
	TextView Resultado = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Se asigna la interfaz
		setContentView(R.layout.main);

		// Se declaran los botones
		Button Trapezoidal = (Button) findViewById(R.id.botonTrapezoidal);
		Button Simpson = (Button) findViewById(R.id.botonSimpson);
		Trapezoidal.setOnClickListener(this);
		Simpson.setOnClickListener(this);
		
		//Les asigno su valor de vista a las areas de entrada y el area de salida
		IntervaloInferior = (EditText) findViewById(R.id.EditText1);
		IntervaloSuperior = (EditText) findViewById(R.id.EditText2);
		Subintervalos = (EditText) findViewById(R.id.EditText3);
		Resultado = (TextView) findViewById(R.id.Resultado);

	}

	//Menu de Android, ignorar.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		//Declaro las variables que voy a usar para los calculos
		
		subintervalos = Subintervalos.getText().toString();			
		int n = Integer.parseInt(subintervalos);
		
		intervaloSup = IntervaloSuperior.getText().toString();
		double b = Double.parseDouble(intervaloSup);
		
		intervaloInf = IntervaloInferior.getText().toString();
		double a = Double.parseDouble(intervaloInf);
		
		double h = (b-a)/n;
		
		//En I se almacenara el resultado total.
		double  I= 0.0;		
		
		switch (v.getId()) {
		case R.id.botonTrapezoidal:
			//AL SER PRESIONADO EL TRAPEZOIDAL	
			I = evaluarFuncion(a) + evaluarFuncion(b);
			
			for (double j = a+h; j < b; j=j+h) {
				I = I + 2*evaluarFuncion(j);
			} 
			
			I = I *(h/2);
			
			//Se despliega el resultado
			Resultado.setText(""+I);
			
			break;
			
		case R.id.botonSimpson:
			// AL SER PRESIONADO EL SIMPSON
			//contador
			int i = 1;

			//Verifica que sea numero par el numero de subintervalos sea par.
			if (n%2 != 0){
				//Despliega error
				Resultado.setText("El num de subintervalos debe ser par");
			}
			
			else{
				
				I = evaluarFuncion(a) + evaluarFuncion(b);				
				
				for (double j = a+h; j < b; j=j+h) {
					if(i%2 != 0){
						I = I + 4*evaluarFuncion(j);	
					}
					else{ I = I + 2*evaluarFuncion(j);}
					i++;
				} 
			
				I = I*(h/3);
				
				//Se despliega el resultado
				Resultado.setText("" + I);
			}
			break;
		}

	}
	
	//Aqui se evalua la funcion de f(x)
	public double evaluarFuncion(double X){
		double valor = X*X*X + 3*(X*X)-10*X+20;
		
		return valor;						
	}
}
