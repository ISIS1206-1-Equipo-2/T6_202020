/**
 * 2020-10-17
 * Clase View.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package view;
import model.logic.CitiBike;

/**
 * Se encarga de mostrar el men� de opciones en el programa.
 */
public class View 
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	
	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "---------------------------";
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Se encarga de crear el visor de opciones.
	 */
	public View()
	{

	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------
	
	/**
	 * Muestra las opciones que tiene el usuario para escoger en el programa.
	 */
	public void printMenu()
	{
		System.out.println(SEPARADOR + " BIENVENIDO AL SISTEMA DE BICICLETAS CITIBIKE " + SEPARADOR + "\n" );
		System.out.println("1. Realizar la carga de datos en el sistema de bicicletas CitiBikes.");
		System.out.println("2. Consultar el grado de entrada y salida de una estaci�n de bicicletas.");
		System.out.println("3. Cantidad de clusters de viajes.");
		System.out.println("4. Ruta tur�stica circular.");
		System.out.println("5. Estaciones cr�ticas.");
		System.out.println("6. Ruta tur�stica por resistencia.");
		System.out.println("7. Recomendador de rutas.");
		System.out.println("8. Ruta de inter�s tur�stico.");
		System.out.println("9. Identificaci�n de estaciones para publicidad.");
		System.out.println("10. Identificaci�n de bicicletas para mantenimiento.");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	/**
	 * Muestra un mensaje en el visor del programa.
	 * @param String mensaje. Mensaje a mostrar en consola.
	 */
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}		
	
	/**
	 * Muestra un mensaje en el visor del programa con las opciones de cargar archivos.
	 */
	public void printData( ){
		System.out.println(SEPARADOR + "--- CARGA DE DATOS EN EL SISTEMA -------------" + SEPARADOR + "\n" );

		System.out.println("1. Cargar los datos del primer mes en el sistema.");
		System.out.println("2. Cargar los datos de los primeros dos meses en el sistema.");
		System.out.println("3. Cargar los datos de los primeros tres meses en el sistema.");
		System.out.println("4. Cargar los datos de los primeros cuatro meses en el sistema.");
		System.out.println("5. Salir.");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}
}
