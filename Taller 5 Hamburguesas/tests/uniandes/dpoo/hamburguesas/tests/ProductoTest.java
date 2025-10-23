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

	    @BeforeEach
	    void setUp( ) throws Exception
	    {
	        producto = new ProductoMenu( "corral queso", 16000 );
	        productoAjustado = new ProductoAjustado(producto);
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
}
