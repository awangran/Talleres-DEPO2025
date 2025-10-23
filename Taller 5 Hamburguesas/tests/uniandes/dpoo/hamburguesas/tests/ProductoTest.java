package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoTest {
	 private ProductoMenu producto ;
	 private ProductoAjustado productoAjustado;
	 private Ingrediente tomate = new Ingrediente("tomate", 12000);
	 private Ingrediente lechuga = new Ingrediente("lechuga", 2000);

	    @BeforeEach
	    void setUp( ) throws Exception
	    {
	        producto = new ProductoMenu( "corral queso", 16000 );
	        productoAjustado = new ProductoAjustado(producto);
	   	 	productoAjustado.getAgregados().add(tomate);
	   	 	productoAjustado.getEliminados().add(lechuga);

	    }

	    @AfterEach
	    void tearDown( ) throws Exception
	    {
	    }

	    @Test
	    void testGetNombre( )
	    {
	        assertEquals( "corral queso", producto.getNombre( ), "El nombre del producto no es el esperado." );
	    }

	    @Test
	    void testGetPrecio( )
	    {
	        assertEquals( 16000, producto.getPrecio( ), "El costo del producto no es el esperado." );
	    }

		@Test
		void generarTextoFactura( )
		{
		String esperado = "corral queso 16000".replaceAll("\\s+", "");
		String actual = producto.generarTextoFactura().replaceAll("\\s+", "");

		assertEquals(esperado, actual, "La factura generada no es la esperada.");
		}

		@Test
		void getNombre() 
		{
	        assertEquals( "corral queso", productoAjustado.getNombre( ), "El nombre del producto no es el esperado." );

		}

		@Test
		void getPrecio() 
		{
	        assertEquals( 28000, productoAjustado.getPrecio( ), "El costo del producto no es el esperado." );

		}

		@Test
		void generarTextoFactura2( )
		{
		String esperado = "corral queso 16000 +tomate 12000 -lechuga 28000".replaceAll("\\s+", "");
		String actual = productoAjustado.generarTextoFactura().replaceAll("\\s+", "");

		assertEquals(esperado, actual, "La factura generada no es la esperada.");
		}
}

