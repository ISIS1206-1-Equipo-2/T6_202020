/**
 * 2020-10-17
 * Clase CitiBike.
 * @author Juli�n Andr�s M�ndez
 */
package model.logic;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el sistema de bicicletas CitiBike.
 */
public class CitiBike {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "----------------------------------------------";


	/**
	 * Constante que representa la ruta de datos del primer mes.
	 */
	public static final String PRIMER_MES = "data/201801-1-citibike-tripdata.csv";

	/**
	 * Constante que representa la ruta de datos del segundo mes.
	 */
	public static final String SEGUNDO_MES = "data/201801-2-citibike-tripdata.csv";

	/**
	 * Constante que representa la ruta de datos del tercer mes.
	 */
	public static final String TERCER_MES = "data/201801-3-citibike-tripdata.csv";

	/**
	 * Constante que representa la ruta de datos del cuarto mes.
	 */
	public static final String CUARTO_MES = "data/201801-4-citibike-tripdata.csv";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Representa la estructura de datos de grafo dirigido.
	 */
	public DiGraph<Integer, String, Integer> DiGraph;

	/**
	 * Representa los datos fuentes a ser cargados en el sistema.
	 */
	public String data;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea la clase que representa el sistema de bicicletas CitiBike. 
	 */
	public CitiBike(){
		DiGraph = new DiGraph<Integer, String, Integer>();
		data = "None";
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Establece el archivo que el usario desea revisar. 
	 * @param int data. n�mero de archivo a cargar.
	 */
	public void establecerFuenteDatos( int data ){
		switch(data){
		case 1:
			this.data = "PRIMER_MES";
			break;

		case 2:
			this.data = "PRIMER_MES,SEGUNDO_MES";
			break;

		case 3:
			this.data = "PRIMER_MES,SEGUNDO_MES,TERCER_MES";
			break;

		case 4:
			this.data = "PRIMER_MES,SEGUNDO_MES,TERCER_MES,CUARTO_MES";
		}
	}

	/**
	 * Establece el archivo en el bufferReader.
	 * @param Reader reader. Objeto Reader para leer.
	 */
	private Reader reader(Reader reader, String data)
	{
		try{
			switch(data){
			case "PRIMER_MES":
				reader = Files.newBufferedReader(Paths.get(PRIMER_MES));
				break;

			case "SEGUNDO_MES":
				reader = Files.newBufferedReader(Paths.get(SEGUNDO_MES));
				break;

			case "TERCER_MES":
				reader = Files.newBufferedReader(Paths.get(TERCER_MES));
				break;

			case "CUARTO_MES":
				reader = Files.newBufferedReader(Paths.get(CUARTO_MES));
				break;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return reader;
	}

	/**
	 * Se encarga de cargar los datos a la estructura de datos de grafo dirigido.
	 * @throws CsvValidationException Lanza excepci�n si se presenta un problema al leer el archivo CSV.
	 * @return Retorna el n�mero de viajes le�dos de los archivos.
	 * Retorna el n�mero total de estaciones (v�rtices).
	 * Retorna el n�mero total de arcos entre estaciones (arcos totales del grafo).
	 * Retorna el arco con peso m�nimo, los v�rtices que conecta y su valor de peso.
	 * Retorna el arco con el peso m�ximo, los v�rtices que conecta y su valor de peso.
	 */
	public String cargarDatosDiGraph( ) throws CsvValidationException{
		long now = 0;
		long start = System.currentTimeMillis();
		String[ ] data = this.data.split(",");
		int load = 0;
		int numViajes = 0; 

		while( load<data.length ){

			try{
				//Create csv reader
				Reader reader =null; 
				reader = reader(reader, data[load]);

				CSVParser parser = new CSVParserBuilder()
						.withSeparator(',')
						.build();

				CSVReader csvReader = new CSVReaderBuilder(reader)
						.withSkipLines(1)
						.withCSVParser(parser)
						.build();

				//read one record at a time.
				String[] line = csvReader.readNext();

				while( line != null){
					double tripDuration = Double.parseDouble( line[0]);
					int stationSource = Integer.parseInt(line[3]);
					String nameStationS = line[4];
					int stationDest = Integer.parseInt(line[7]);
					String nameStationD = line[8];

					DiGraph.insertVertex(stationSource, nameStationS);
					DiGraph.insertVertex(stationDest, nameStationD);

					Edge<Integer, String, Integer> edge = DiGraph.getEdge(stationSource, stationDest);
					if(edge != null){
						int times = edge.getInfo();
						edge.setInfo( times+1 );
						edge.setWeight( ( (edge.getWeight()*times)+tripDuration) /edge.getInfo() );
						DiGraph.actualizarMaxMinWeight(edge);			
					}
					else{
						DiGraph.addEdge(stationSource, stationDest, tripDuration, 1);
					}					
					line = csvReader.readNext();
					numViajes++;
				}
				csvReader.close();
				reader.close();
				load++;	
			}
			catch(IOException e) {e.printStackTrace();}
		}
		now = System.currentTimeMillis();
		return toStringDiGraph( now-start, numViajes);
	}

	/**
	 * Informa el grado de entrada y salida para una estaci�n de bicicletas.
	 * @param int iD. ID de la estaci�n de bicibletas.
	 * @return Retorna la informaci�n del grado de entrada y de salida de la estaci�n de bicicletas.
	 */
	public String consultarGradoEntradaSalida(int iD) {
		if(DiGraph.containsVertex(iD)){
			int gradoEntrada = DiGraph.indegree(iD);
			int gradoSalida = DiGraph.outdegree(iD);
			return "\nGrado de entrada: " + Integer.toString(gradoEntrada)
			+ "\nGrado de salida: " + Integer.toString(gradoSalida);
		}
		else return "La identificaci�n de la estaci�n no existe en el sistema.";
	}

	/**
	 * Calcula el total de clusters presentes en el grafo.
	 * Informa si dos identificaciones de dos estaciones pertenecen o no al mismo cl�ster.
	 * @param int iDOne. Primera ID  de una estaci�n de bicicletas.
	 * @param int iDTwo. Segunda ID de una estaci�n de bicicletas.
	 * @return Retorna el total de clusters presentes en el grafo.
	 * Retorna si las dos estaciones pertenecen o no al mismo cluster
	 */
	public String cantidadDeClustersDeViajes(int iDOne, int iDTwo) {
		return "Incompleto";
	}

	/**
	 * Informa sobre el n�mero de rutas circulares existentes que se puede realizar en un rango de tiempo.
	 * @param int timeOne. L�mite inferior del tiempo dado.
	 * @param int timeTwo. L�mite superior del tiempo dado.
	 * @param int iD. ID de la estaci�n de bicibletas.
	 * @return Retorna el n�mero de rutas circulares encontradas.
	 * Retorna la impresi�n de las rutas circulares disponibles.
	 * Retorna nombre de estaci�n inicial, final y duraci�n estimada por cada segmento de ruta.
	 */
	public String rutaTuristicaCircular(int timeOne, int timeTwo, int iD) {
		return "Incompleto";
	}

	/**
	 * Informa sobre las estaciones de llegada TOP, de salida TOP y las estaciones menos utilizadas.
	 * @return Los nomres de las 3 estaciones Top de llegada.
	 * Los nombrs de las 3 estaciones Top de salida.
	 * Los nombres de las 3 estaciones menos utilizadas.
	 */
	public String estacionesCriticas() {
		return "Incompleto";
	}

	/**
	 * Informa sobre las rutas que puede hacer el turista desde la estaci�n de salida junto con la informaci�n de cada segmento de ruta.
	 * @param int timeResist. Tiempo m�ximo de resistencia en minutos.
	 * @param int iD. ID de la estaci�n de bicibletas.
	 * @return Retorna las rutas que puede hacer el turista desde la estaci�n de salida.
	 * Retorna nombre de estaci�n inicial, final y duraci�n estimada por cada segmento de ruta.
	 */
	public String rutaTuristicaPorResistencia(int timeResist, int iD) {
		return "Incompleto";
	}

	/**
	 * Informa al turista la estaci�n desde la cual las personas en dicho rango de edad incian m�s viajes.
	 * @param String rangoEdad. Rango de edad del turista.
	 * @return Retorna la ruta de inicio, final y la lista de estaciones en la ruta.
	 */
	public String recomendadorDeRutas(String rangoEdad) {
		return "Incompleto";
	}

	/**
	 * Informa al turista la estaci�n de inicio m�s cercana a su localizaci�n.
	 * Informa la estaci�n final m�s cercana al punto de inter�s al que desea llegar.
	 * Indica la ruta con menor tiempo para llegar al destino.
	 * @param Double latAct. Latitud del punto de ubicaci�n actual.
	 * @param Double lonAct. Longitud del punto de ubicaci�n actual. 
	 * @param Double latSit. Latitud del sitio tur�stico de destino.
	 * @param Double lonSit. Longitud del sitio tur�stico de destino. 
	 * @return Retorna la estaci�n m�s cercana al sitio tur�stico que el turista quiere visitar.
	 * Retorna la estaci�n m�s cercana al sitio turistico que el turista quiere visitar.
	 * Retorna el tiempo estimado de viaje.
	 * Retorna la lista de estacione en la ruta.
	 */
	public String rutaDeInteresTuristico(Double latAct, Double lonAct, Double latSit, Double lonSit) {
		return "Incompleto";
	}

	/**
	 * Indica cu�l es el par de estaciones adyacentes (inicio y llegada) que las personas de ese grupo de edad, con suscripci�n de 3 d�as m�s realizan.
	 * @param String rangoEdad. Rango de edad del grupo dde turistas.
	 * @return Retorna las estaciones adyacentes que m�s utilizan las personas del grupo de edad.
	 * Retorna el total de viajes registrados en el sistemma.
	 * Retorna las parejas de las estaciones que cumplen la condici�n.
	 */
	public String identificacionDeEstacionesParaPublicidad(String rangoEdad) {
		return "Incompleto";
	}

	/**
	 * Informa todas las estaciones por las que la bibicleta ha pasado.
	 * Calcula el tiempo total de uso y el tiempo total de estacionada.
	 * @param int iD. ID de la estaci�n de bicibletas.
	 * @param String fech. Fecha a consultar.
	 * @return Retorna la lista de estaciones por las que ha pasado la bicicleta.
	 * Retorna el tiempo total de uso y el tiempo total de estacionada.
	 */
	public String identificacionDeEstacionesParaMantenimiento(int iD, String fech) {
		return "Incompleto";
	}

	/**
	 * Se encarga de realizar el reporte al momento de cargar los datos RBT.
	 * @param long time. El tiempo en el que se realiz� la carga de datos.
	 * @param int numViajes. El n�mero de viajes que se concret� al momento de realizar la carga de datos.
	 * @return Retorna el n�mero de viajes le�dos de los archivos.
	 * Retorna el n�mero total de estaciones (v�rtices).
	 * Retorna el n�mero total de arcos entre estaciones (arcos totales del grafo).
	 * Retorna el arco con peso m�nimo, los v�rtices que conecta y su valor de peso.
	 * Retorna el arco con el peso m�ximo, los v�rtices que conecta y su valor de peso.
	 */
	private String toStringDiGraph( long time, int numViajes ){
		ArregloDinamicoGenerico< Vertex<Integer, String, Integer>> estaciones = DiGraph.vertex();
		ArregloDinamicoGenerico< Edge<Integer, String, Integer>> trayectorias = DiGraph.edges();
		Edge<Integer, String, Integer> minWeight = DiGraph.minWeight(); 
		Edge<Integer, String, Integer> maxWeight = DiGraph.maxWeight();

		return SEPARADOR +" REPORTE " +SEPARADOR
				+"\n\nN�mero de viajes le�dos de los archivos: " + numViajes
				+ "\nN�mero total de estaciones (v�rtices): " + estaciones.size()
				+ "\nN�mero total de arcos entre estaciones (arcos totales del grafo): " + trayectorias.size()
				+ "\n\nArco con el peso m�nimo: \nId de estaci�n de origen: " + minWeight.getSource().getId() + " - Nombre: " + minWeight.getSource().getValue()
				+ "\nId de estaci�n de destino: " + minWeight.getDest().getId() + " - Nombre: "+ minWeight.getDest().getValue() 
				+ "\nPeso: "+ minWeight.getWeight()
				+ "\n\nArco con el peso m�ximo: \nId de estaci�n de origen: " + maxWeight.getSource().getId() + " - Nombre: " + maxWeight.getSource().getValue()
				+ "\nId de estaci�n de destino: " + maxWeight.getDest().getId() + " - Nombre: "+ maxWeight.getDest().getValue() 
				+ "\nPeso: "+ maxWeight.getWeight()				
				+ "\n\nTiempo de ejecuci�n: " + (time) + "mseg\n";
	}

}
