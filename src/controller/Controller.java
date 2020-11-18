/**
 * 2020-10-17
 * Clase Controller.
 * @author Juli�n Andr�s M�ndez
 */

package controller;
import model.logic.CitiBike;
import java.util.Scanner;
import com.opencsv.exceptions.CsvValidationException;
import view.View;

/**
 * Se encarga de controlar la entrada y salida de datos por consola.
 */
public class Controller {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "----------------------------------------------------------------------------------------------";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/** 
	 * Instancia del sistema de bicicletas CitiBike
	 */
	private CitiBike citiBike;

	/**
	 * Instancia de la Vista
	 */
	private View view;

	// -----------------------------------------------------------------
	// Construtor
	// -----------------------------------------------------------------
	/**
	 * Crea la vista y el modelo del proyecto.
	 */
	public Controller ()
	{
		view = new View();
		citiBike = new CitiBike();
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------	

	/**
	 * Se encarga de ejecutar la entrada y salida de datos por consola.
	 * @throws CsvValidationException. Se presenta una excepci�n si ocurre un error durante el proceso.
	 */
	public void run() throws CsvValidationException 
	{
		Scanner lectorStr = new Scanner(System.in);
		Scanner lectorInt = new Scanner(System.in);
		boolean fin = false;
		String res, rangoEdad;
		int ID;
		while( !fin ){
			view.printMenu();

			int option = lectorInt.nextInt();
			switch(option){

			case 1:
				view.printData();
				int optionData = lectorInt.nextInt();
				
				while( !(optionData>0 && optionData<=5)){
					view.printMessage(SEPARADOR + "\n Opcion Invalida !! \n");
					optionData = lectorInt.nextInt();
				}
				if( optionData != 5){
					citiBike.establecerFuenteDatos(optionData);
					view.printMessage(citiBike.cargarDatosDiGraph());
				}
				break;

			case 2:
				view.printMessage( SEPARADOR + "\n ID de la estaci�n de bicicletas:");
				ID = lectorStr.nextInt();
				res = citiBike.consultarGradoEntradaSalida( ID );
				view.printMessage(res);
				break;

			case 3:
				view.printMessage(SEPARADOR + "\n Primer ID estaci�n de bicicletas:");
				int IDOne = lectorStr.nextInt();

				view.printMessage(SEPARADOR + "\n Segundo ID estaci�n de bicicletas:");
				int IDTwo = lectorStr.nextInt();

				res = citiBike.cantidadDeClustersDeViajes( IDOne, IDTwo );
				view.printMessage(res);
				break;

			case 4:
				view.printMessage(SEPARADOR + "\n L�mite inicial del tiempo en minutos");
				int timeOne = lectorInt.nextInt();

				view.printMessage(SEPARADOR + "\n L�mite final del tiempo en minutos");
				int timeTwo = lectorInt.nextInt();

				view.printMessage(SEPARADOR + "\n ID de la estaci�n de bicicletas:");
				ID = lectorStr.nextInt();

				res = citiBike.rutaTuristicaCircular( timeOne, timeTwo, ID );
				view.printMessage(res);
				break;

			case 5:
				res = citiBike.estacionesCriticas( );
				view.printMessage(res);
				break;

			case 6:
				view.printMessage(SEPARADOR + "\n Tiempo m�ximo de resistencia");
				int timeResist = lectorInt.nextInt();

				view.printMessage(SEPARADOR + "\n ID estaci�n de bicicletas inicial:");
				ID = lectorStr.nextInt();

				res = citiBike.rutaTuristicaPorResistencia( timeResist, ID);
				view.printMessage(res);
				break;

			case 7:
				view.printMessage(SEPARADOR + "\n Rango de edad. Formato: n:m donde n,m son n�meros (e.g. 1:10)");
				rangoEdad = lectorStr.nextLine();

				res = citiBike.recomendadorDeRutas( rangoEdad);
				view.printMessage(res);
				break;

			case 8:
				view.printMessage(SEPARADOR + "\n Latitud del punto de ubicaci�n actual");
				Double latAct = lectorInt.nextDouble();

				view.printMessage(SEPARADOR + "\n Longitud del punto de ubicaci�n actual");				
				Double lonAct = lectorInt.nextDouble();

				view.printMessage(SEPARADOR + "\n Latitud del sitio tur�stico");
				Double latSit = lectorInt.nextDouble();

				view.printMessage(SEPARADOR + "\n Longitud del sitio tur�stico");				
				Double lonSit = lectorInt.nextDouble();

				res = citiBike.rutaDeInteresTuristico( latAct, lonAct, latSit, lonSit);
				view.printMessage(res);
				break;

			case 9: 
				view.printMessage(SEPARADOR + "\n Rango de edad. Formato: n:m donde n,m son n�meros (e.g. 1:10)");
				rangoEdad = lectorStr.nextLine();

				res = citiBike.identificacionDeEstacionesParaPublicidad( rangoEdad);
				view.printMessage(res);
				break;

			case 10: 
				view.printMessage(SEPARADOR + "\n ID de bicicleta:");
				ID = lectorStr.nextInt();

				view.printMessage(SEPARADOR + "\n Fecha a consultar. Formato DD-MM-AAAA (e.g. 03:12:2001):");
				String fech = lectorStr.nextLine();

				res = citiBike.identificacionDeEstacionesParaMantenimiento( ID,  fech);
				view.printMessage(res);
				break;

			default: 
				view.printMessage(SEPARADOR + "\n Opcion Invalida !! \n");
				break;
			}
		}
		lectorStr.close();
		lectorInt.close();
	}	
}