package uniandes.dpoo.hamburguesas.tests;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private ArrayList<ProductoMenu> itemsCombo;
	private ProductoMenu corral;
	private ProductoMenu papas;
    private Combo combo;

	    @BeforeEach
	    void setUp( ) throws Exception
	    {
	    	itemsCombo = new ArrayList<ProductoMenu>();
	    	corral = new ProductoMenu("corral queso", 12000);
	    	papas = new ProductoMenu("papas", 3000);

	    	itemsCombo.add(corral);
	    	itemsCombo.add(papas);
	    	
	    	combo = new Combo("combo corral", 0.1, itemsCombo);
	    	
	    }

	    @AfterEach
	    void tearDown( ) throws Exception
	    {
	    }

	    @Test
	    void testGetNombre( )
	    {
	        assertEquals( "combo corral", combo.getNombre( ), "El nombre del combo no es el esperado." );
	    }

        @Test
        void testGetPrecio( )
	    {
	        assertEquals( 13500, combo.getPrecio( ), "El precio del combo no es el esperado." );
	    }

        @Test
		void generarTextoFactura( )
		{
		String esperado = "Combo combo corral Descuento: 0.1 13500".replaceAll("\\s+", "");
		String actual = combo.generarTextoFactura().replaceAll("\\s+", "");

		assertEquals(esperado, actual, "La factura generada no es la esperada.");
		}
}
