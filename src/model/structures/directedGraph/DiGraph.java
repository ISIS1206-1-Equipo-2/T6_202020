/**
 * 2020-11-17
 * Clase Digraph.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.directedGraph;
import model.structures.listaGenerica.ArregloDinamicoGenerico;
import model.structures.listaComparable.ArregloDinamicoComparable;


/**
 * Representa la estructura de datos de grafo dirigido.
 */
public class DiGraph<K extends Comparable<K>, V, A> {

	// -----------------------------------------------------------------
	// Constantes.
	// -----------------------------------------------------------------

	public static final int TAMA�O_INICIAL = 30;

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Lista de adyacencia de vertices.
	 */
	private ArregloDinamicoGenerico< Vertex<K, V, A> > vertexGraph;

	/**
	 * Lista de adyacencia de identificador.
	 */
	private ArregloDinamicoGenerico< Edge<K, V, A> >edgesGraph;

	/**
	 * Lista de adyacencia de identificador.
	 */
	private ArregloDinamicoComparable< K > vertexIdGraph;

	/**
	 * Representa el arco con peso m�nimo en el grafo.
	 */
	private Edge<K, V, A> minWeight;

	/**
	 * Representa el arco con mayor peso en el grafo.
	 */
	private Edge<K, V, A> maxWeight;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea una estructura de datos de grafo dirigido.
	 */
	public DiGraph( ){
		vertexGraph = new ArregloDinamicoGenerico< Vertex<K, V, A> >(TAMA�O_INICIAL);
		edgesGraph = new ArregloDinamicoGenerico< Edge<K, V, A> > (TAMA�O_INICIAL);
		vertexIdGraph = new ArregloDinamicoComparable< K >(TAMA�O_INICIAL);
		minWeight = null;
		maxWeight = null;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Verifica si el vertice con id suministrado est� en el grafo 
	 * @param K id. Vertice suministrado.
	 * @return Retorna true si el vertice se encuentra en el grafo. De lo contrario, retorna false. 
	 */
	public boolean containsVertex(K id){
		return (vertexIdGraph.isPresent(id)==-1)?false:true;
	}

	/**
	 * Calcula el n�mero de vertices que se encuentran en el grafo. 
	 * @return El valor de vertices en el grafo.
	 */
	public int numVertices( ){
		return vertexIdGraph.size();
	}

	/**
	 * Calcula el n�mero de arcos en el grafo.
	 * @return El n�mero de arcos en el grafo. 
	 */
	public int numEdges( ){
		return edgesGraph.size();
	}

	/**
	 * Devuelve el arco con mayor peso en el grafo.
	 * @return Retorna el arco con mayor peso en el grafo.
	 */
	public Edge<K, V, A> maxWeight( ){
		return maxWeight;
	}

	/**
	 * Devuelve el arco con menor peso en el grafo.
	 * @return Retorna el arco con mayor peso en el grafo.
	 */
	public Edge<K, V, A> minWeight( ){
		return minWeight;
	}

	/**
	 * A�ade un v�rtice al grafo con su identificador y valor.
	 * @param K id. Vertice suministrado.
	 * @param V value. El valor del vertice suministrado. 
	 */
	public void insertVertex( K id, V value){
		if(!containsVertex( id )){
			Vertex< K, V, A> newVertex = new Vertex<K, V, A>(id, value);
			vertexGraph.addLast( newVertex );
			vertexIdGraph.addLast( id );
		}
	}

	/**
	 * A�ade un arco dirigido pesado entre el v�rtice origen y destino, con peso weight. 
	 * Si el arco ya existe se modifica su peso.
	 * @param K source. Vertice de origen.
	 * @param K dest. Vertice de origen.
	 * @param double weight. Peso del arco. 
	 * @param A info. Informaci�n asociada a un arco.
	 */
	public void addEdge( K source, K dest, double weight , A info){
		if(containsVertex( source ) && containsVertex( dest )){
			Edge< K, V, A> presentEdge = getEdge(source, dest);
			if(presentEdge == null){
				Vertex<K, V, A> vertexSource = getVertex( source );
				Vertex<K, V, A> vertexDest = getVertex( dest );
				Edge< K, V, A> edge = new Edge<K, V, A>(vertexSource, vertexDest, weight, info);
				vertexSource.addEddge(edge);
				vertexDest.addEddge(edge);
				edgesGraph.addLast(edge);
				actualizarMaxMinWeight(edge);
			}
			else{
				presentEdge.setInfo(info);
				presentEdge.setWeight(weight);
				actualizarMaxMinWeight(presentEdge);
			}
		}
	}

	/**
	 * Calcula el v�rtice a partir de su indentificador �nico.
	 * @param K id. Identificador �nico del vertice.
	 * @return Retorna el v�rtice perteneciente al identificador �nico.
	 */
	public Vertex<K, V, A> getVertex( K id){
		int index =  vertexIdGraph.isPresent(id);
		if (index == -1) return null;
		else return vertexGraph.getElemento(index);
	}

	/**
	 * Calcula el arco entre los v�rtices idS y idD (si existe). Retorna null si no existe.
	 * @param K idS. Identificaci�n del �rtice de origen.
	 * @param k idD. Identificaci�n del V�rtice de destino.
	 * @return Retorna el arco perteneciente al vertice de origen y de destino �nico.
	 */
	public Edge<K, V, A> getEdge( K ids, K idD){
		Vertex<K, V, A> vertex = getVertex(ids);
		return vertex.getEdge(idD);
	}

	/**
	 * Devuelve una lista de arcos adyacentes (salientes) al v�rtice con id. 
	 * @param K id. Identificador �nico del vertice.
	 * @return Retorna la lista de arcos adyacentes (salientes) al vertice con id.
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> adjacentEdges( K id){
		Vertex<K, V, A> vertex = getVertex( id );
		return vertex.edgesOut();
	}

	/**
	 * Devuelve una lista de v�rtices adyacentes (salientes) al v�rtice con id. 
	 * @param K id. Identificador �nico del vertice.
	 * @return Retorna la lista de v�rtices adyacentes (salientes) al vertice con id.
	 */
	public ArregloDinamicoGenerico<Vertex<K, V, A>> adjacentVertex( K id){
		Vertex<K, V, A> vertex = getVertex( id );
		return vertex.vertexOut();
	}

	/**
	 * Calcula el grado de entrada del vertice vertex (n�mero de arcos entrantes).
	 * @param K vertex. Vertice a calular.
	 * @return Retorna el grado de entrada del vertice vertex.
	 */
	public int indegree (K vertex){
		Vertex<K, V, A> vertexToKnow = getVertex( vertex );
		return vertexToKnow.inDegree();
	}

	/**
	 * Calcula el grado de salida del vertice vertex (n�mero de arcos salientes).
	 * @param K vertex. Vertice a calular.
	 * @return Retorna el grado de salida del vertice vertex.
	 */
	public int outdegree (K vertex){
		Vertex<K, V, A> vertexToKnow = getVertex( vertex );
		return vertexToKnow.outDegree();
	}

	/**
	 * Devuelve una lista de todos los arcos del grafo. 
	 * @return Retorna la lista de todos los arcos del grafo. 
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> edges(){
		return edgesGraph;
	}

	/**
	 * Devuelve una lista de todos los vertices del grafo. 
	 * @return Retorna la lista de todos los vertices del grafo. 
	 */
	public ArregloDinamicoGenerico<Vertex<K, V, A>> vertex( ){
		return vertexGraph;
	}

	/**
	 * Calcula el nuevo max weight y min weight de los arcos en el grafo.
	 */
	public void actualizarMaxMinWeight( Edge<K, V, A> edge){
		if(minWeight!=null) {if(edge.getWeight()<minWeight.getWeight()) minWeight = edge;}
		else {minWeight = edge;}

		if(maxWeight!=null) {if(edge.getWeight()>maxWeight.getWeight()) maxWeight = edge;}	
		else {maxWeight = edge;}
	}


}
