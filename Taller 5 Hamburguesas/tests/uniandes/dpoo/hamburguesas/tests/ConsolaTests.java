package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.consola.ConsolaBasica;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsolaTests {

    @Test
    void testConsolaBasicaCoberturaGeneral() {
        // Simular entrada del usuario con opciones válidas
        String inputSimulado = String.join("\n",
            "1", // Mostrar menú
            "2", // Iniciar pedido
            "Ashlee", // Nombre
            "Calle 2", // Dirección
            "3", // Agregar producto
            "1", // Seleccionar primer producto
            "n", // No agregar ingredientes
            "n", // No eliminar ingredientes
            "4", // Cerrar pedido
            "5", // Consultar pedido por ID
            "0", // Salir
            ""   // Línea final para evitar EOF
        );

        // Redirigir entrada estándar
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputSimulado.getBytes());
        System.setIn(inputStream);

        // Redirigir salida estándar (opcional, para evitar impresión en consola)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Ejecutar la consola
        ConsolaBasica.main(null);
    }
    
    @Test
    void testCoberturaTotalIniciarCerrarConsultar() {
        String inputSimulado = String.join("\n",
            "2",     // Iniciar pedido (válido)
            "Ashlee",
            "Calle 2",
            "2",     // Iniciar pedido otra vez → lanza YaHayUnPedidoEnCursoException
            "Otro",
            "Otra dirección",
            "4",     // Cerrar pedido (válido)
            "4",     // Cerrar pedido otra vez → lanza NoHayPedidoEnCursoException
            "5",     // Consultar pedido por ID (válido)
            "0"      // Salir
        );

        System.setIn(new ByteArrayInputStream(inputSimulado.getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        ConsolaBasica.main(null);
    }

}
