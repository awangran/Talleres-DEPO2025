package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;

    public void correrAplicacion( )
    {
        try
        {
            unaAerolinea = new Aerolinea();

            // Archivos de prueba
            String archivoAerolinea = "./datos/aerolinea.json";
            String archivoTiquetes = "./datos/tiquetes.json";

            // 1. Cargar aerolínea
            unaAerolinea.cargarAerolinea(archivoAerolinea, CentralPersistencia.JSON);
            System.out.println("Aerolinea cargada con éxito.");
            System.out.println("Rutas cargadas:");
            unaAerolinea.getRutas().forEach(r -> 
                System.out.println(" - " + r.getCodigoRuta() + " (" + r.getOrigen().getNombre() + " -> " + r.getDestino().getNombre() + ")")
            );

            // 2. Cargar tiquetes
            unaAerolinea.cargarTiquetes(archivoTiquetes, CentralPersistencia.JSON);
            System.out.println("Tiquetes cargados: " + unaAerolinea.getTiquetes().size());

            // 3. Mostrar detalle de tiquetes cargados
            for (Tiquete t : unaAerolinea.getTiquetes()) {
                System.out.println("Tiquete " + t.getCodigo()
                        + " | Cliente: " + t.getCliente().getIdentificador()
                        + " | Ruta: " + t.getVuelo().getRuta().getCodigoRuta()
                        + " | Fecha: " + t.getVuelo().getFecha()
                        + " | Usado: " + t.esUsado());
            }

        }
        catch (TipoInvalidoException | IOException | InformacionInconsistenteException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ConsolaArerolinea ca = new ConsolaArerolinea();
        ca.correrAplicacion();
    }
}
