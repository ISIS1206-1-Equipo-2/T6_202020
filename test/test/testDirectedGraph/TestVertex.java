/**
 * 2020-11-17
 * Clase TestDirectedGraph.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package test.testDirectedGraph;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Test de la estructura de datos de grafo dirigido.
 */
public class TestVertex {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa el v�rtice A, v�rtice B, v�rtice C, v�rtice, D y el v�rtice E respectivamente.
	 */
	private Vertex<String, Integer, String> vertexA, vertexB, vertexC, vertexD, vertexE;

	/**
	 * Representa el Arco de AB, Arco de AC, Arco de AD, Arco de DC, Arco de CE y el Arco de BE respectivamente.
	 */
	private Edge<String, Integer, String> edgeAB, edgeAC, edgeAD, edgeDC, edgeCE, edgeBE;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		vertexA = new Vertex<String, Integer, String>( "A", 1);
		vertexB = new Vertex<String, Integer, String>( "B", 2);
		vertexC = new Vertex<String, Integer, String>( "C", 3);
		vertexD = new Vertex<String, Integer, String>( "D", 4);
		vertexE = new Vertex<String, Integer, String>( "E", 5);

		edgeAB = new Edge<String, Integer, String>(vertexA, vertexB, 20.0, "EdgeAB");
		edgeAC = new Edge<String, Integer, String>(vertexA, vertexC, 30.0, "EdgeAC");
		edgeAD = new Edge<String, Integer, String>(vertexA, vertexD, 40.0, "EdgeAD");
		edgeDC = new Edge<String, Integer, String>(vertexD, vertexC, 70.0, "EdgeDC");
		edgeCE = new Edge<String, Integer, String>(vertexC, vertexE, 140.0, "EdgeCE");
		edgeBE = new Edge<String, Integer, String>(vertexB, vertexE, 70.0, "EdgeBE");
	}
	
	/**
	 * Segundo escenario del test.
	 */
	public void setUp2(){
		vertexA.addEddge(edgeAB);
		vertexA.addEddge(edgeAC);
		vertexA.addEddge(edgeAD);
		vertexD.addEddge(edgeDC);
		vertexC.addEddge(edgeCE);
		vertexB.addEddge(edgeBE);

		vertexB.addEddge(edgeAB);
		vertexC.addEddge(edgeAC);
		vertexD.addEddge(edgeAD);
		vertexC.addEddge(edgeDC);
		vertexE.addEddge(edgeCE);
		vertexE.addEddge(edgeBE);
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------
	
	/**
	 * Calcula el identificador del v�rtice.
	 */
	@Test
	public void getId( ){
		assertEquals("Error: El identificador del v�rtice A: A", "A", vertexA.getId());
		assertEquals("Error: El identificador del v�rtice B: B", "B", vertexB.getId());
		assertEquals("Error: El identificador del v�rtice C: C", "C", vertexC.getId());
		assertEquals("Error: El identificador del v�rtice D: D", "D", vertexD.getId());
		assertEquals("Error: El identificador del v�rtice E: E", "E", vertexE.getId());
	}
	
	/**
	 * Busca el valor asociado al v�rtice.
	 */
	@Test
	public void getValue( ){
		assertTrue("Error: El valor del v�rtice A es 1", 1 == vertexA.getValue());
		assertTrue("Error: El valor del v�rtice B es 2", 2 == vertexB.getValue());
		assertTrue("Error: El valor del v�rtice C es 3", 3 == vertexC.getValue());
		assertTrue("Error: El valor del v�rtice D es 4", 4 == vertexD.getValue());
		assertTrue("Error: El valor del v�rtice E es 5", 5 == vertexE.getValue());
	}
	/**
	 * Verifica si el v�rtice se encuentra marcado o no.
	 */
	@Test
	public void getMark( ){
		assertFalse("Error: El valor inicial de marcado del vertice A debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice B debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice C debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice D debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice E debe ser falso", vertexA.getMark());
	}
	
	/**
	 * Agrega un arco adyacente al v�rtice.
	 */
	@Test
	public void addEdge( ){
		vertexA.addEddge(edgeAB);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexA.edgesOut().isPresent(edgeAB));
		vertexA.addEddge(edgeAC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexA.edgesOut().isPresent(edgeAC));
		vertexA.addEddge(edgeAD);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexA.edgesOut().isPresent(edgeAD));
		vertexD.addEddge(edgeDC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexD.edgesOut().isPresent(edgeDC));
		vertexC.addEddge(edgeCE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexC.edgesOut().isPresent(edgeCE));
		vertexB.addEddge(edgeBE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexB.edgesOut().isPresent(edgeBE));
	
		vertexB.addEddge(edgeAB);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexB.edgesIn().isPresent(edgeAB));
		vertexC.addEddge(edgeAC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexC.edgesIn().isPresent(edgeAC));
		vertexD.addEddge(edgeAD);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexD.edgesIn().isPresent(edgeAD));
		vertexC.addEddge(edgeDC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexC.edgesIn().isPresent(edgeDC));
		vertexE.addEddge(edgeCE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexE.edgesIn().isPresent(edgeCE));
		vertexE.addEddge(edgeBE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexE.edgesIn().isPresent(edgeBE));
	}
	
	/**
	 * Marca el v�rtice.
	 */
	@Test
	public void mark( ){
		vertexA.mark();
		assertFalse("Error: El valor del marcado del v�rtice A deber�a actualizarce a true", !vertexA.getMark());
		
		vertexB.mark();
		assertFalse("Error: El valor del marcado del v�rtice B deber�a actualizarce a true", !vertexB.getMark());
		
		vertexC.mark();
		assertFalse("Error: El valor del marcado del v�rtice C deber�a actualizarce a true", !vertexC.getMark());
		
		vertexD.mark();
		assertFalse("Error: El valor del marcado del v�rtice D deber�a actualizarce a true", !vertexD.getMark());
		
		vertexE.mark();
		assertFalse("Error: El valor del marcado del v�rtice E deber�a actualizarce a true", !vertexE.getMark());
	}
	
	/**
	 * Desmarca el v�rtice.
	 */
	@Test
	public void unmark( ){
		vertexA.unmark();
		assertFalse("Error: El valor del marcado del v�rtice A deber�a actualizarce a false", vertexA.getMark());
		
		vertexB.unmark();
		assertFalse("Error: El valor del marcado del v�rtice B deber�a actualizarce a false", vertexB.getMark());
		
		vertexC.unmark();
		assertFalse("Error: El valor del marcado del v�rtice C deber�a actualizarce a false", vertexC.getMark());
		
		vertexD.unmark();
		assertFalse("Error: El valor del marcado del v�rtice D deber�a actualizarce a false", vertexD.getMark());
		
		vertexE.unmark();
		assertFalse("Error: El valor del marcado del v�rtice E deber�a actualizarce a false", vertexE.getMark());
	}
	
	/**
	 * Calcula el n�mero de arcos (salientes) del v�rtice.
	 * @return Retorna n�mero de arcos (salientes) del v�rtice.
	 */
	@Test
	public void outDegree( ){
		setUp2();
		assertEquals("Error: El n�mero de arcos salientes del v�rtice A deber�a ser 3", 3, vertexA.outDegree());
		assertEquals("Error: El n�mero de arcos salientes del v�rtice B deber�a ser 1", 1, vertexB.outDegree());
		assertEquals("Error: El n�mero de arcos salientes del v�rtice C deber�a ser 1", 1, vertexC.outDegree());
		assertEquals("Error: El n�mero de arcos salientes del v�rtice D deber�a ser 1", 1, vertexD.outDegree());
		assertEquals("Error: El n�mero de arcos salientes del v�rtice E deber�a ser 0", 0, vertexE.outDegree());
	}
	
	/**
	 * Calcula el n�mero de arcos (entrantes) del v�rtice.
	 */
	@Test
	public void InDegree( ){
		setUp2();
		assertEquals("Error: El n�mero de arcos entrantes del v�rtice A deber�a ser 0", 0, vertexA.inDegree());
		assertEquals("Error: El n�mero de arcos entrantes del v�rtice B deber�a ser 1", 1, vertexB.inDegree());
		assertEquals("Error: El n�mero de arcos entrantes del v�rtice C deber�a ser 2", 2, vertexC.inDegree());
		assertEquals("Error: El n�mero de arcos entrantes del v�rtice D deber�a ser 1", 1, vertexD.inDegree());
		assertEquals("Error: El n�mero de arcos entrantes del v�rtice E deber�a ser 2", 2, vertexE.inDegree());
	}
	
	/**
	 * Calcula el arco con el v�rtice vertex (si existe). Retorna null si no existe.
	 */
	@Test
	public void getEdge( ){
		setUp2();
		assertSame("Error: El arco que conduce al v�rtice B desde el v�rtice A deber�a ser edgeAB", edgeAB, vertexA.getEdge(vertexB.getId()));
		assertSame("Error: El arco que conduce al v�rtice C desde el v�rtice A deber�a ser edgeAC", edgeAC, vertexA.getEdge(vertexC.getId()));
		assertSame("Error: El arco que conduce al v�rtice D desde el v�rtice A deber�a ser edgeAD", edgeAD, vertexA.getEdge(vertexD.getId()));
		assertSame("Error: El arco que conduce al v�rtice C desde el v�rtice D deber�a ser edgeDC", edgeDC, vertexD.getEdge(vertexC.getId()));
		assertSame("Error: El arco que conduce al v�rtice E desde el v�rtice C deber�a ser edgeCE", edgeCE, vertexC.getEdge(vertexE.getId()));
		assertSame("Error: El arco que conduce al v�rtice E desde el v�rtice B deber�a ser edgeBE", edgeBE, vertexB.getEdge(vertexE.getId()));
	}
	
	/**
	 * Devuelve una lista con sus v�rtices adyacentes (salientes).
	 */
	@Test
	public void vertexOut( ){
		setUp2();
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutA = vertexA.vertexOut();
		assertNotEquals( "Error: El v�rtice saliente B deber�a estar en la lista de v�rtices adyacentes salientes del v�rtice A", -1, vertexOutA.isPresent(vertexB));
		assertNotEquals( "Error: El v�rtice saliente C deber�a estar en la lista de v�rtices adyacentes salientes del v�rtice A", -1, vertexOutA.isPresent(vertexC));         
		assertNotEquals( "Error: El v�rtice saliente D deber�a estar en la lista de v�rtices adyacentes salientes del v�rtice A", -1, vertexOutA.isPresent(vertexD));

		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutB = vertexB.vertexOut();
		assertNotEquals( "Error: El v�rtice saliente E deber�a estar en la lista de v�rtices adyacentes salientes del v�rtice B", -1, vertexOutB.isPresent(vertexE));
		
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutC = vertexC.vertexOut();
		assertNotEquals( "Error: El v�rtice saliente E deber�a estar en la lista de v�rtices adyacentes salientes del v�rtice C", -1, vertexOutC.isPresent(vertexE));
		
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutD = vertexD.vertexOut();
		assertNotEquals( "Error: El v�rtice saliente C deber�a estar en la lista de v�rtices adyacentes salientes del v�rtice D", -1, vertexOutD.isPresent(vertexC));
		
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutE = vertexE.vertexOut();
		assertTrue( "Error: La lista de v�rtices adyacentes salientes del v�rtice D deber�a estar vac�a", vertexOutE.isEmpty());
	}
	
	/**
	 * Devuelve una lista con sus arcos adyacentes (salientes).
	 */
	@Test
	public void edgesOut( ){
		setUp2();
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutA = vertexA.edgesOut();
		assertNotEquals( "Error: El arco edgeAB deber�a estar en la lista de arcos adyacentes salientes del v�rtice A", -1, edgesOutA.isPresent(edgeAB));
		assertNotEquals( "Error: El arco edgeAC deber�a estar en la lista de arcos adyacentes salientes del v�rtice A", -1, edgesOutA.isPresent(edgeAC));         
		assertNotEquals( "Error: El arco edgeAD deber�a estar en la lista de arcos adyacentes salientes del v�rtice A", -1, edgesOutA.isPresent(edgeAB));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutB = vertexB.edgesOut();
		assertNotEquals( "Error: El arco edgeBC deber�a estar en la lista de arcos adyacentes salientes del v�rtice B", -1, edgesOutB.isPresent(edgeBE));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOuC = vertexC.edgesOut();
		assertNotEquals( "Error: El arco edgeCE deber�a estar en la lista de arcos adyacentes salientes del v�rtice C", -1, edgesOuC.isPresent(edgeCE));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutD = vertexD.edgesOut();
		assertNotEquals( "Error: El arco edgeDC deber�a estar en la lista de arcos adyacentes salientes del v�rtice D", -1, edgesOutD.isPresent(edgeDC));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutE = vertexE.edgesOut();
		assertTrue( "Error: La lista de arcos adyacentes salientes del v�rtice E deber�a estar vac�a", edgesOutE.isEmpty());
	}
	
	/**
	 * Devuelve una lista con sus arcos adyacentes (entrantes).
	 */
	public void edgesIn(){
		setUp2();
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInA = vertexA.edgesIn();
		assertTrue( "Error: La lista de arcos adyacentes entrantes del v�rtice A deber�a estar vac�a", edgesInA.isEmpty());

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInB = vertexB.edgesIn();
		assertNotEquals( "Error: El arco edgeAB deber�a estar en la lista de arcos adyacentes entrantes del v�rtice B", -1, edgesInB.isPresent(edgeAB));

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInC = vertexC.edgesIn();
		assertNotEquals( "Error: El arco edgeAC deber�a estar en la lista de arcos adyacentes entrantes del v�rtice C", -1, edgesInC.isPresent(edgeAC));

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInD = vertexD.edgesIn();
		assertNotEquals( "Error: El arco edgeAD deber�a estar en la lista de arcos adyacentes entrantes del v�rtice D", -1, edgesInD.isPresent(edgeAC));

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInE = vertexE.edgesIn();
		assertNotEquals( "Error: El arco edgeCE deber�a estar en la lista de arcos adyacentes entrantes del v�rtice E", -1, edgesInE.isPresent(edgeAC));
	}
	
	/**
	 * Compara un v�rtice con otro dado por par�matro.
	 */
	public void equals(){
		assertTrue("Error: El vertice A es igual al vertice A", vertexA.equals(vertexA.getId()));
		assertFalse("Error: El vertice A es diferente al vertice B", vertexA.equals(vertexB.getId()));
		assertTrue("Error: El vertice B es igual al vertice B", vertexB.equals(vertexB.getId()));
		assertFalse("Error: El vertice B es diferente al vertice A", vertexB.equals(vertexA.getId()));
	}
}