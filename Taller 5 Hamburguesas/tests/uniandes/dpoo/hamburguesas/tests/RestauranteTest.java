package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @AfterEach
    void tearDown() {
        restaurante = null;
    }

    @Test
    void testConstructorInicializaListasVacias() {
        assertNotNull(restaurante.getPedidos());
        assertNotNull(restaurante.getIngredientes());
        assertNotNull(restaurante.getMenuBase());
        assertNotNull(restaurante.getMenuCombos());

        assertEquals(0, restaurante.getPedidos().size());
        assertEquals(0, restaurante.getIngredientes().size());
        assertEquals(0, restaurante.getMenuBase().size());
        assertEquals(0, restaurante.getMenuCombos().size());
    }

    @Test
    void testIniciarPedidoCorrectamente() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Ashlee", "Calle 2");
        Pedido pedido = restaurante.getPedidoEnCurso();

        assertNotNull(pedido);
        assertEquals("Ashlee", pedido.getNombreCliente());
    }

    @Test
    void testIniciarPedidoCuandoYaHayUno() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Ashlee", "Calle 2");

        YaHayUnPedidoEnCursoException exception = assertThrows(
            YaHayUnPedidoEnCursoException.class,
            () -> restaurante.iniciarPedido("Otro", "Otra dirección")
        );

        assertTrue(exception.getMessage().contains("Ashlee"));
    }

    @Test
    void testCerrarYGuardarPedidoCorrectamente() throws Exception {
        restaurante.iniciarPedido("Ashlee", "Calle 2");

        ProductoMenu producto = new ProductoMenu("corral queso", 16000);
        restaurante.getPedidoEnCurso().agregarProducto(producto);

        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso());

        File carpeta = new File("./data/facturas/");
        assertTrue(carpeta.exists());

        File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("factura_") && name.endsWith(".txt"));
        assertNotNull(archivos);
        assertTrue(archivos.length > 0);

        File factura = archivos[archivos.length - 1];
        Scanner scanner = new Scanner(factura);
        String contenido = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        scanner.close();

        assertTrue(contenido.contains("Cliente: Ashlee"));
        assertTrue(contenido.contains("corral queso"));
        assertTrue(contenido.contains("Precio Total"));

        factura.delete();
    }

    @Test
    void testCerrarPedidoSinHaberIniciado() {
        NoHayPedidoEnCursoException exception = assertThrows(
            NoHayPedidoEnCursoException.class,
            () -> restaurante.cerrarYGuardarPedido()
        );

        assertEquals("Actualmente no hay un pedido en curso", exception.getMessage());
    }

    @Test
    void testGetMenuBase() {
        ArrayList<ProductoMenu> menu = restaurante.getMenuBase();
        assertNotNull(menu);
        assertEquals(0, menu.size());
    }

    @Test
    void testGetMenuCombos() {
        ArrayList<Combo> combos = restaurante.getMenuCombos();
        assertNotNull(combos);
        assertEquals(0, combos.size());
    }

    @Test
    void testGetIngredientes() {
        ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
        assertNotNull(ingredientes);
        assertEquals(0, ingredientes.size());
    }

    @Test
    void testGetPedidos() {
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertNotNull(pedidos);
        assertEquals(0, pedidos.size());
    }
    
    @Test
    void testCargarIngredientesCorrectamente() throws IOException, HamburguesaException {
        restaurante.cargarInformacionRestaurante(
            new File("./data/ingredientes.txt"),
            new File("./data/menu.txt"),
            new File("./data/combos.txt")
        );
        assertEquals(15, restaurante.getIngredientes().size(), "No se cargaron todos los ingredientes.");
        assertTrue(restaurante.getIngredientes().stream().anyMatch(i -> i.getNombre().equals("lechuga")));
    }

    @Test
    void testCargarMenuCorrectamente() throws IOException, ProductoRepetidoException, HamburguesaException {
    	restaurante.cargarInformacionRestaurante(
                new File("./data/ingredientes.txt"),
                new File("./data/menu.txt"),
                new File("./data/combos.txt")
            );
        assertEquals(22, restaurante.getMenuBase().size(), "No se cargaron todos los productos del menú.");
        assertTrue(restaurante.getMenuBase().stream().anyMatch(p -> p.getNombre().equals("corral queso")));
    }

    @Test
    void testCargarCombosCorrectamente() throws IOException, HamburguesaException, ProductoRepetidoException, ProductoFaltanteException {
    	restaurante.cargarInformacionRestaurante(
                new File("./data/ingredientes.txt"),
                new File("./data/menu.txt"),
                new File("./data/combos.txt")
            );
        assertEquals(4, restaurante.getMenuCombos().size(), "No se cargaron todos los combos.");
        assertTrue(restaurante.getMenuCombos().stream().anyMatch(c -> c.getNombre().equals("combo especial")));
    }

    @Test
    void testIngredienteRepetido() throws IOException {
        Restaurante r = new Restaurante();
        File archivo = new File("./data/ingredientes_repetidos.txt"); 

        assertThrows(IngredienteRepetidoException.class, () -> {
            r.cargarInformacionRestaurante(archivo, new File("./data/menu.txt"), new File("./data/combos.txt"));
        });
    }

    @Test
    void testProductoRepetido() throws IOException {
        Restaurante r = new Restaurante();
        File archivo = new File("./data/menu_repetido.txt"); 

        assertThrows(ProductoRepetidoException.class, () -> {
            r.cargarInformacionRestaurante(new File("./data/ingredientes.txt"), archivo, new File("./data/combos.txt"));
        });
    }

    @Test
    void testComboConProductoFaltante() throws IOException {
        Restaurante r = new Restaurante();
        File archivo = new File("./data/combos_producto_faltante.txt"); 

        assertThrows(ProductoFaltanteException.class, () -> {
            r.cargarInformacionRestaurante(new File("./data/ingredientes.txt"), new File("./data/menu.txt"), archivo);
        });
    }
}
