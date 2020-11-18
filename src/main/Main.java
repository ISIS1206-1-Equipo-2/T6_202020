/**
 * 2020-10-17
 * Clase Controller.
 * @author Juli�n Andr�s M�ndez
 */

package main;
import com.opencsv.exceptions.CsvValidationException;
import controller.Controller;

/**
 * Permite la ejecuci�n del programa en el compilador de Java.
 */
public class Main {
	
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------	

	/**
	 * Ejecuta el programa.
	 * @param String[] args. Cadena de caracteres que permite la ejecuci�n del programa.
	 */
	public static void main(String[] args) 
	{
		Controller controler = new Controller();
		try {
			controler.run();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}
	}
}