/**
 * 2020-11-17
 * Clase Lista.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.listaComparable;

/**
 * Representa la interfaz de Lista.
 */
public interface Lista <T extends Comparable<T>> {
	
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------
	
	/**
	 * Agrega un elemento al principio del arreglo.
	 * @param T element. Elemento a agregar.
	 */
	public void addFirst( T element);
	
	/**
	 * Agrega un elemento al final del arreglo.
	 * @param T element. Elemento a agregar.
	 */
	public void addLast( T element );
	
	/**
	 * Agrega un elemento en una posici�n v�lida.
	 * @param T element. Elemento a agregar.
	 * @param int pos. Posici�n en donde se va a agregar el elemento.
	 * @throws Exception 
	 */
	public void insertElement(T element, int pos);
	
	/**
	 * Elimina el primer elemento en el arreglo.
	 * @return Retorna el elemento eliminado.
	 */
	T removefirst( );
	
	/**
	 * Elimina el �ltimo elemento en el arreglo.
	 * @return Retorna el elemento eliminado.
	 */
	 T removeLast( );
	 
	/**
	 * Elimina el elemento de una posici�n v�lida.
	 * @param int pos. La posici�n del elemento a eliminar.
	 * @return Retorna el elemento eliminado.
	 */
	 T deleteElement(int pos);
	 
	/**
	 * Busca el primer elemento del arreglo.
	 * @return Retorna el primer elemento del arreglo.
	 */
     T firstElement( );
     
    /**
 	 * Busca el �ltimo elemento del arreglo.
 	 * @return Retorna el �ltimo elemento del arreglo.
 	 */
 	 T lastElement( );
 	 
 	/**
 	 * Busca el elemento que se encuentra en la posici�n dada.
 	 * @param int i. La posici�n del elemento a buscar.
 	 * @return Retorna el elemento en la posici�n dada.
 	 */
 	 T getElemento(int i);
	
	/**
	 * Calcula el tama�o del arreglo.
	 * @return Retorna el tama�o del arreglo.
	 */
	 int size( );
	
	/**
	 * Verifica si el arreglo est� vac�o.
	 * @return Retorna true si el arreglo se encuentra vac�o. De lo contrario retorna false.
	 */
	 boolean isEmpty( );
	 
	/**
	 * Busca un elemento y retorna su posici�n.
	 * @param T element. El elemento a buscar. 
	 * @return Retorna la Posici�n del elemento buscado. Si no se encuentra retorna -1.
	 */
	 int isPresent( T element);
	 
	 /**
	  * Intercambia dos elementos del arreglo.
	  * @param int pos1. La posici�n del primer elemento a intercambiar.
	  * @param int pos1. La posici�n del segundo elemento a intercambiar.
	  */
	 public void exchange( int pos1, int pos2);
	 
	 /**
	  * Cambia un elemento dada una posici�n valida en el arreglo.
	  * @param int pos1. La posici�n del elemento a cambiar.
	  * @param T elem. El elemento para cambiar la informaci�n.
	  */
	 public void changeInfo( int pos, T elem);	 
}
