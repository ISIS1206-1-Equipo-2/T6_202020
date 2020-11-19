/**
 * 2020-11-17
 * Clase TestDirectedGraph.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package test.testDirectedGraph;
import static org.junit.Assert.*;
import model.structures.directedGraph.Vertex;
import model.structures.directedGraph.Edge;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la estructura de datos de grafo dirigido.
 */
public class TestEdge {

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

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Calcula el v�rtice de origen.
	 */
	@Test
	public void getSource( ){
		assertEquals("Error: Se esperaba el v�rtice A", vertexA, edgeAB.getSource());
		assertEquals("Error: Se esperaba el v�rtice A", vertexA, edgeAC.getSource());
		assertEquals("Error: Se esperaba el v�rtice A", vertexA, edgeAD.getSource());
		assertEquals("Error: Se esperaba el v�rtice D", vertexD, edgeDC.getSource());
		assertEquals("Error: Se esperaba el v�rtice C", vertexC, edgeCE.getSource());
		assertEquals("Error: Se esperaba el v�rtice B", vertexB, edgeBE.getSource());
	}

	/**
	 * Calcula el v�rtice de salida.
	 */
	@Test
	public void getDest( ){
		assertEquals("Error: Se esperaba el v�rtice B", vertexB, edgeAB.getDest());
		assertEquals("Error: Se esperaba el v�rtice C", vertexC, edgeAC.getDest());
		assertEquals("Error: Se esperaba el v�rtice D", vertexD, edgeAD.getDest());
		assertEquals("Error: Se esperaba el v�rtice C", vertexC, edgeDC.getDest());
		assertEquals("Error: Se esperaba el v�rtice E", vertexE, edgeCE.getDest());
		assertEquals("Error: Se esperaba el v�rtice E", vertexE, edgeBE.getDest());
	}

	/**
	 * Calcula el peso del arco.
	 */
	@Test
	public void getWeight( ){
		assertTrue("Error: Se esperaba el peso de 20.0", 20.0==edgeAB.getWeight());
		assertTrue("Error: Se esperaba el peso de 30.0", 30.0==edgeAC.getWeight());
		assertTrue("Error: Se esperaba el peso de 40.0", 40.0==edgeAD.getWeight());
		assertTrue("Error: Se esperaba el peso de 70.0", 70.0==edgeDC.getWeight());
		assertTrue("Error: Se esperaba el peso de 140.0", 140.0==edgeCE.getWeight());
		assertTrue("Error: Se esperaba el peso de 70.0", 70.0==edgeBE.getWeight());
	}
	
	/**
	 * Calcula la informaci�n asociada a un arco.
	 */
	@Test
	public void getInfo( ){
		assertEquals("Error: Se esperaba la informaci�n: EdgeAB", "EdgeAB", edgeAB.getInfo());
		assertEquals("Error: Se esperaba la informaci�n: EdgeAC", "EdgeAC", edgeAC.getInfo());
		assertEquals("Error: Se esperaba la informaci�n: EdgeAD", "EdgeAD", edgeAD.getInfo());
		assertEquals("Error: Se esperaba la informaci�n: EdgeDC", "EdgeDC", edgeDC.getInfo());
		assertEquals("Error: Se esperaba la informaci�n: EdgeCE", "EdgeCE", edgeCE.getInfo());
		assertEquals("Error: Se esperaba la informaci�n: EdgeBE", "EdgeBE", edgeBE.getInfo());
	}
	
	/**
	 * Modifica el peso del arco.
	 */
	@Test
	public void setWeight( ){
		edgeAB.setWeight(30.0);
		assertTrue("Error: Se esperaba el peso de 30.0", 30.0==edgeAB.getWeight());

		edgeAC.setWeight(40.0);
		assertTrue("Error: Se esperaba el peso de 40.0", 40.0==edgeAC.getWeight());

		edgeAD.setWeight(50.0);
		assertTrue("Error: Se esperaba el peso de 50.0", 50.0==edgeAD.getWeight());

		edgeDC.setWeight(80.0);
		assertTrue("Error: Se esperaba el peso de 80.0", 80.0==edgeDC.getWeight());

		edgeCE.setWeight(150.0);
		assertTrue("Error: Se esperaba el peso de 150.0", 150.0==edgeCE.getWeight());

		edgeBE.setWeight(80.0);
		assertTrue("Error: Se esperaba el peso de 80.0", 80.0==edgeBE.getWeight());
	}
	
	/**
	 * Modifica la informaci�n del arco.
	 */
	@Test
	public void setInfo( ){
		edgeAB.setInfo("NewEdgeAB");
		assertEquals("Error: Se esperaba la informaci�n: newEdgeAB", "NewEdgeAB", edgeAB.getInfo());

		edgeAC.setInfo("NewEdgeAC");
		assertEquals("Error: Se esperaba la informaci�n: newEdgeAB", "NewEdgeAC", edgeAC.getInfo());

		edgeAD.setInfo("NewEdgeAD");
		assertEquals("Error: Se esperaba la informaci�n: newEdgeAB", "NewEdgeAD", edgeAD.getInfo());

		edgeDC.setInfo("NewEdgeDC");
		assertEquals("Error: Se esperaba la informaci�n: newEdgeAB", "NewEdgeDC", edgeDC.getInfo());

		edgeCE.setInfo("NewEdgeCE");
		assertEquals("Error: Se esperaba la informaci�n: newEdgeAB", "NewEdgeCE", edgeCE.getInfo());

		edgeBE.setInfo("NewEdgeBE");
		assertEquals("Error: Se esperaba la informaci�n: newEdgeAB", "NewEdgeBE", edgeBE.getInfo());
	}
}







