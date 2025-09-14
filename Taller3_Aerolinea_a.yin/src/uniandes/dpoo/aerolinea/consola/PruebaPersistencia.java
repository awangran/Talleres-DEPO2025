package uniandes.dpoo.aerolinea.consola;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.exceptions.*;

public class PruebaPersistencia {
	
	//PRUEBA HECHA PORQUE EL TALLER NO TRAE NINGUN TEST SI MUCHAS GRACIAS.
    public static void main(String[] args) {
        try {
            Aerolinea aerolinea = new Aerolinea();

            // 1. Cargar primero la aerolínea
            aerolinea.cargarAerolinea("datos/aerolinea.json", "JSON");
            System.out.println("Aerolinea cargada con éxito.");
            System.out.println("Rutas cargadas:");
            aerolinea.getRutas().forEach(r -> 
                System.out.println(" - " + r.getCodigoRuta() + " (" + r.getOrigen().getNombre() + " -> " + r.getDestino().getNombre() + ")")
            );

            // 2. Luego cargar tiquetes
            aerolinea.cargarTiquetes("datos/tiquetes.json", "JSON");
            System.out.println("Tiquetes cargados: " + aerolinea.getTiquetes().size());
            
            aerolinea.getTiquetes().forEach(t -> 
            System.out.println("Tiquete " + t.getCodigo() + 
                               " | Cliente: " + t.getCliente().getIdentificador() +
                               " | Ruta: " + t.getVuelo().getRuta().getCodigoRuta() + 
                               " | Fecha: " + t.getVuelo().getFecha() +
                               " | Usado: " + t.esUsado()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
