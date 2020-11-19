/**
 * 2020-11-17
 * Clase TestListaGenerica.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package test.testListaGenerica;
import model.structures.listaGenerica.ArregloDinamicoGenerico;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la estructura de datos arreglo din�mico generico.
 */
public class TestListaGenerica {
	
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	
	/**
	 * Representa el tama�o incial del arreglo din�mico generico.
	 */
	private static int TAMANO=100;
	
	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa la estructura de datos arreglo din�mico generico.
	 */
	private ArregloDinamicoGenerico<String> arreglo;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------	
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamicoGenerico<String>(TAMANO);
	}

	/**
	 * Segundo escenario del test.
	 */
	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.addLast(""+i);
		}
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------	
	
	/**
	 * Verifica que el arreglo sea diferente de null y que su tama�o sea igual a cero.
	 */
	@Test
	public void testArregloDinamico() {
		assertTrue(arreglo!=null);
		assertEquals(0, arreglo.size());
		
	}

	/**
	 * Agrega un elemento al principio del arreglo.
	 */
	@Test
	public void addFirst() {
		setUp2();
		for(int i = 200; i<299; i++)
		{
			String first = ""+i;
			arreglo.addFirst(first);
			assertEquals("El elemento es incorrecto",1, arreglo.isPresent(first));
		}
	}
	
	/**
	 * Agrega un elemento al final del arreglo.
	 */
	@Test
	public void addLast() {
		setUp2();
		for(int i = 200; i<299; i++)
		{
			String last = ""+i;
			arreglo.addLast(last);
			assertEquals("El elemento es incorrecto", arreglo.size(), arreglo.isPresent(last));
		}
	}
	
	/**
	 * Agrega un elemento en una posici�n v�lida.
	 */
	@Test
	public void insertElement() {
		setUp2();
		arreglo.insertElement("200", 1);
		assertEquals("El elemento encontrado no coincide", 1, arreglo.isPresent("200"));
		assertEquals("La lista fallo al correr la posici�n", 2, arreglo.isPresent("0"));

		arreglo.insertElement("201", 4);
		assertEquals("La lista fallo al correr la posici�n", 3, arreglo.isPresent("1"));
		assertEquals("El elemento encontrado no coincide", 4, arreglo.isPresent("201"));
		assertEquals("La lista fallo al correr la posici�n", 5, arreglo.isPresent("2"));
	}
	
	/**
	 * Elimina el primer elemento en el arreglo.
	 */
	@Test
	public void removefirst() {
		setUp2();
		String e = (String) arreglo.removefirst();
		assertEquals("El elemento se elimino incorrectamente",-1, arreglo.isPresent(e));
		assertEquals("Las posiciones est�n corridas incorrectamente", arreglo.isPresent("1"), 1);
	}
	
	/**
	 * Elimina el �ltimo elemento en el arreglo.
	 */
	@Test
	public void removeLast() {
		setUp2();
		String e = (String) arreglo.removefirst();
		assertEquals("El �ltimo elemento se elimin� incorrectamente",-1, arreglo.isPresent(e));
	}
	
	/**
	 * Retorna el primer elemento del arreglo.
	 */
	@Test
	public void firstElement() {
		setUp2();
		assertEquals("No retorn� el primer elemento", "0", arreglo.firstElement());
	}
	
	/**
	 * Retorna el �ltimo elemento del arreglo.
	 */
	@Test
	public void lastElement() {
		setUp2();
		assertEquals("No retorn� el �ltimo elemento", "199", arreglo.lastElement());
	}
	
 	/**
 	 * Busca el elemento que se encuentra en la posici�n dada.
 	 */
	@Test
	public void getElemento() {
		setUp2();
		for( int i = 1; i<50; i++)
		{
			int indice = i-1;
			assertEquals("El elemento no coincide",arreglo.getElemento(i),""+indice);
		}
	}
	
	/**
	 * Retorna el tama�o del arreglo.
	 */
	@Test
	public void size() {
		setUp2();
		assertEquals("El tama�o no coincide",200, arreglo.size() );
	}
	
	/**
	 * Verifica si el arreglo est� vac�o.
	 */
	@Test
	public void isEmpty() {
		assertTrue("El arreglo se encuentra vac�o", arreglo.isEmpty());
		setUp2();
		assertFalse("El arreglo no se encuentra vac�o", arreglo.isEmpty());
	}

	/**
	 * Busca un elemento y retorna su posici�n.
	 */
	@Test
	public void isPresent() {
		setUp2();
		for(int i = 0; i < arreglo.size(); i++){
			assertEquals("La posici�n no coincide", Integer.toString(i+1), Integer.toString(arreglo.isPresent(Integer.toString(i))));
		}
	}
	
	 /**
	  * Intercambia dos elementos del arreglo.
	  */
	@Test
	public void exchange() {
		setUp2();
		String elem1 = (String)arreglo.getElemento(1);
		String elem2 =(String) arreglo.getElemento(3);
		arreglo.exchange(1, 3);
		assertEquals("El intercambio no se ejecut� correctamente",elem1, arreglo.getElemento(3));
		assertEquals("El intercambio no se ejecut� correctamente", elem2, arreglo.getElemento(1));
	}
	
	 /**
	  * Cambia un elemento dada una posici�n valida en el arreglo.
	  */
	@Test
	public void changeInfo() {
		setUp2();
		arreglo.changeInfo(4,""+3);
		assertEquals("El cambio del elemento fall�", arreglo.getElemento(4),""+3);
	}



}