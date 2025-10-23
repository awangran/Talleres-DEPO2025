package uniandes.dpoo.hamburguesas.tests;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PedidoTest {
	private Pedido pedido;
	private Producto producto;
	
	 @BeforeEach
	    void setUp( ) throws Exception
	    {
		 	Pedido.reiniciarContador();
	    	pedido = new Pedido("ashlee", "calle2");
			producto = new ProductoMenu( "corral queso", 16000 );
			
 			pedido.agregarProducto(producto); 


	    	
	    }

	    @AfterEach
	    void tearDown( ) throws Exception
	    {
	    	pedido = null;
	    }

	    
	    @Test
	    void testGetId( )
	    {
	        assertEquals( 0, pedido.getIdPedido( ), "El id del pedido no es el esperado." );
	    }

		@Test
	    void testGetNombreCliente( )
	    {
	        assertEquals( "ashlee", pedido.getNombreCliente( ), "El cliente del pedido no es el esperado." );
	    }

		@Test
	    void testAgregarProducto( )
	    {

        	assertEquals(1, pedido.getProductos().size(), "El producto no fue agregado correctamente.");
        	assertTrue(pedido.getProductos().contains(producto), "El producto no está en la lista.");	    }

		@Test
	    void getPrecioTotalPedido( )
	    {
			
	        assertEquals( 19040, pedido.getPrecioTotalPedido( ), "El precio total del pedido no es el esperado." );
	    }

		@Test
		void testGenerarTextoFactura() {
			String factura = pedido.generarTextoFactura().replaceAll("\\s+", "").toLowerCase();

			assertTrue(factura.contains("cliente:ashlee"));
			assertTrue(factura.contains("dirección:calle2"));
			assertTrue(factura.contains("corralqueso16000"));
			assertTrue(factura.contains("precioneto:16000"));
			assertTrue(factura.contains("iva:3040"));
			assertTrue(factura.contains("preciototal:19040"));
		}


		@Test
		void testGuardarFactura() throws FileNotFoundException {
			File tempFile = new File("factura_test.txt");
			pedido.guardarFactura(tempFile);

			Scanner scanner = new Scanner(tempFile);
			StringBuilder contenido = new StringBuilder();
			while (scanner.hasNextLine()) {
				contenido.append(scanner.nextLine()).append("\n");
			}
			scanner.close();

			String facturaGenerada = contenido.toString();
			assertTrue(facturaGenerada.contains("Cliente: ashlee"));
			assertTrue(facturaGenerada.contains("Dirección: calle2"));
			assertTrue(facturaGenerada.contains("corral queso"));
			assertTrue(facturaGenerada.contains("Precio Total: 19040"));

			// Limpieza del archivo
			tempFile.delete();
		}


		

		

		

}
