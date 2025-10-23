package uniandes.dpoo.hamburguesas.consola;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;

public class ConsolaBasica {

    private Restaurante restaurante;
    private Scanner scanner;

    public static void main(String[] args) {
        ConsolaBasica consola = new ConsolaBasica();
        consola.ejecutarAplicacion();
    }

    public void ejecutarAplicacion() {
        scanner = new Scanner(System.in);
        restaurante = new Restaurante();

        try {
            cargarDatos();
        } catch (Exception e) {
            System.out.println("Error cargando datos: " + e.getMessage());
            return;
        }

        boolean continuar = true;
        while (continuar) {
            mostrarMenuOpciones();
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> mostrarMenuRestaurante();
                case 2 -> iniciarPedido();
                case 3 -> agregarElementoAPedido();
                case 4 -> cerrarPedido();
                case 5 -> consultarPedidoPorId();
                case 0 -> {
                    System.out.println("Gracias por usar la aplicación.");
                    continuar = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void cargarDatos() throws HamburguesaException, IOException {
        File ingredientes = new File("./data/ingredientes.txt");
        File menu = new File("./data/menu.txt");
        File combos = new File("./data/combos.txt");
        restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
    }

    private void mostrarMenuOpciones() {
        System.out.println("\n--- Menú de opciones ---");
        System.out.println("1. Mostrar menú del restaurante");
        System.out.println("2. Iniciar nuevo pedido");
        System.out.println("3. Agregar elemento al pedido");
        System.out.println("4. Cerrar pedido y guardar factura");
        System.out.println("5. Consultar pedido por ID");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void mostrarMenuRestaurante() {
        System.out.println("\n--- Productos básicos ---");
        for (ProductoMenu producto : restaurante.getMenuBase()) {
            System.out.println(producto.getNombre() + " - $" + producto.getPrecio());
        }

        System.out.println("\n--- Combos ---");
        for (Combo combo : restaurante.getMenuCombos()) {
            System.out.println(combo.getNombre() + " - $" + combo.getPrecio());
        }
    }

    private void iniciarPedido() {
        try {
            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine();
            System.out.print("Dirección del cliente: ");
            String direccion = scanner.nextLine();
            restaurante.iniciarPedido(nombre, direccion);
            System.out.println("Pedido iniciado correctamente.");
        } catch (YaHayUnPedidoEnCursoException e) {
            System.out.println("Ya hay un pedido en curso.");
        }
    }

    private void agregarElementoAPedido() {
        Pedido pedido = restaurante.getPedidoEnCurso();
        if (pedido == null) {
            System.out.println("No hay un pedido en curso.");
            return;
        }

        System.out.println("Seleccione un producto del menú:");
        int index = 1;
        for (ProductoMenu producto : restaurante.getMenuBase()) {
            System.out.println(index++ + ". " + producto.getNombre());
        }

        int seleccion = Integer.parseInt(scanner.nextLine());
        ProductoMenu productoSeleccionado = restaurante.getMenuBase().get(seleccion - 1);
        ProductoAjustado ajustado = new ProductoAjustado(productoSeleccionado);

        System.out.println("¿Desea agregar ingredientes? (s/n)");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            for (Ingrediente ing : restaurante.getIngredientes()) {
                System.out.println("¿Agregar " + ing.getNombre() + "? (s/n)");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    ajustado.getAgregados().add(ing);
                }
            }
        }

        System.out.println("¿Desea eliminar ingredientes? (s/n)");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            for (Ingrediente ing : restaurante.getIngredientes()) {
                System.out.println("¿Eliminar " + ing.getNombre() + "? (s/n)");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    ajustado.getEliminados().add(ing);
                }
            }
        }

        pedido.agregarProducto(ajustado);
        System.out.println("Producto agregado al pedido.");
    }

    private void cerrarPedido() {
        try {
            restaurante.cerrarYGuardarPedido();
            System.out.println("Pedido cerrado y factura guardada.");
        } catch (NoHayPedidoEnCursoException e) {
            System.out.println("No hay un pedido en curso.");
        } catch (IOException e) {
            System.out.println("Error al guardar la factura: " + e.getMessage());
        }
    }

    private void consultarPedidoPorId() {
        System.out.print("Ingrese el ID del pedido: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Pedido pedido : restaurante.getPedidos()) {
            if (pedido.getIdPedido() == id) {
                System.out.println("\n--- Información del pedido ---");
                System.out.println(pedido.generarTextoFactura());
                return;
            }
        }

        System.out.println("No se encontró un pedido con ese ID.");
    }
}
