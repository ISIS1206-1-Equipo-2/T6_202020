/**
 * 2020-10-17
 * Clase Vertex.
 * @author Julián Andrés Méndez
 */

package model.structures.directedGraph;

import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el objeto vertice en la estructura de datos de grafo dirigido.
 */
public class Vertex<K extends Comparable<K>,V, A>{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	public static final int TAMAÑO_ADJ = 30;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Representa el identifiacador único del vertice.
	 */
	private K id;

	/**
	 * Representa el valor asociado al vertice.
	 */
	private V value;

	/**
	 * Representa la marca en un vertice.
	 */
	private boolean mark;

	/**
	 * Representa la lista de arcos adjacentes salientes a un vertice.
	 */
	private ArregloDinamicoGenerico< Edge<K, V, A> > adjEdgeOut;
	
	/**
	 * Representa la lista de arcos adjacentes entrantes al vertice.
	 */
	private ArregloDinamicoGenerico< Edge<K, V, A> > adjEdgeIn;
	
	/**
	 * Representa la lista de vértices adjacentes salientes a un vertice.
	 */
	private ArregloDinamicoGenerico< Vertex<K, V, A> > adjVertex;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea un vértice con identificador único y valor (información asociada), el vertice inicia desmarcado.
	 * @param K id. Identificador único del vértice.
	 * @param V value. Valor suministrado para el vértice.
	 */
	public Vertex( K id, V value){
		this.id = id;
		this.value = value;
		this.mark = false;
		this.adjEdgeOut = new ArregloDinamicoGenerico< Edge<K, V, A> >( TAMAÑO_ADJ );
		this.adjEdgeIn = new ArregloDinamicoGenerico< Edge<K, V, A> >( TAMAÑO_ADJ );
		this.adjVertex = new ArregloDinamicoGenerico< Vertex<K, V, A> >( TAMAÑO_ADJ );
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Calcula el identificador del vértice.
	 * @return Retorna el identificador del vértice.
	 */
	public K getId( ){
		return id;
	}

	/**
	 * Busca el valor asociado al vértice.
	 * @return Retorna el valor asociado al vértice.
	 */
	public V getValue( ){
		return value;
	}
	/**
	 * Verifica si el vértice se encuentra marcado o no.
	 * @return Retorna true si el vértice se encuentra marcado. De lo contrario, retorna false.
	 */
	public boolean getMark( ){
		return mark;
	}

	/**
	 * Agrega un arco adyacente al vértice.
	 * @param Edge<K, V, A> edge. Arco adyancente al vértice.
	 */
	public void addEddge( Edge<K, V, A> edge){
		Vertex<K, V, A> dest = edge.getDest();
		Vertex<K, V, A> source = edge.getSource();
		if( dest.equals(id) && source.equals(id)){
			adjEdgeIn.addLast(edge);
			adjEdgeOut.addLast(edge);
			adjVertex.addLast(dest);
		}
		
		else if(dest.equals(id) && !source.equals(id)){
			adjEdgeIn.addLast(edge);
		}
		
		else{
			adjEdgeOut.addLast(edge);
			adjVertex.addLast(dest);
		}
	}

	/**
	 * Marca el vértice.
	 */
	public void mark( ){
		this.mark = true;
	}

	/**
	 * Desmarca el vértice.
	 */
	public void unmark( ){
		this.mark = false; 
	}

	/**
	 * Calcula el número de arcos (salientes) del vértice.
	 * @return Retorna número de arcos (salientes) del vértice.
	 */
	public int outDegree( ){
		return adjEdgeOut.size();
	}

	/**
	 * Calcula el número de arcos (entrantes) del vértice.
	 * @return Retorna número de arcos (entrantes) del vértice.
	 */
	public int inDegree( ){
		return adjEdgeIn.size();
	}

	/**
	 * Calcula el arco con el vértice vertex (si existe). Retorna null si no existe.
	 * @param K vertex. Arco asociado al vértice.
	 * @return Retorna el arco con el vértice vertex (si existe. De lo contrario, retorna null.
	 */
	public Edge<K, V, A> getEdge(K vertex){
		boolean cent = false;
		Edge<K, V, A> res = null;
		for(int i = 0; i<adjEdgeOut.size() && !cent; i++){
			Edge<K, V, A> act =adjEdgeOut.getElemento(i+1);
			Vertex<K, V, A> dest = act.getDest();
			if(dest.equals(vertex)){
				res = act;
				cent = true;
			}
		}
		return res;
	}

	/**
	 * Devuelve una lista con sus vértices adyacentes (salientes).
	 * @return Retorna una lista con sus vertices adyancentes (salientes).
	 */
	public ArregloDinamicoGenerico< Vertex<K, V, A>> vertexOut(){
		return adjVertex;
	}

	/**
	 * Devuelve una lista con sus arcos adyacentes (salientes).
	 * @return Retorna una lista con sus vertices adyancentes (salientes).
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> edgesOut(){
		return adjEdgeOut;
	}
	
	/**
	 * Devuelve una lista con sus arcos adyacentes (entrantes).
	 * @return Retorna una lista con sus vertices adyancentes (entrantes).
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> edgesIn(){
		return adjEdgeIn;
	}
	
	/**
	 * Compara un vértice con otro dado por parámatro.
	 * @param Vertex<K, V, A> vertex. Vertice a comparar.
	 * @return Retorna true si los dos vertices son iguales. De lo contrario, retorna false.
	 */
	public boolean equals(K pId){
		return (pId.compareTo(id)==0)?true:false;
	}
}