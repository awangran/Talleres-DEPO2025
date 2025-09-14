package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;

public class Aeropuerto {
    private String nombre;
    private String codigo;
    private String nombreCiudad;
    private double latitud;
    private double longitud;

    private static Set<String> codigosUtilizados = new HashSet<>();

    private static final int RADIO_TERRESTRE = 6371;

    public Aeropuerto(String nombre,
                      String codigo,
                      String nombreCiudad,
                      double latitud,
                      double longitud) throws AeropuertoDuplicadoException {
        if (codigosUtilizados.contains(codigo)) {
            throw new AeropuertoDuplicadoException("Ya existe un aeropuerto con el código: " + codigo);
        }
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.latitud = latitud;
        this.longitud = longitud;

        codigosUtilizados.add(codigo);
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getNombreCiudad() {
        return this.nombreCiudad;
    }

    public double getLatitud() {
        return this.latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public static int calcularDistancia(Aeropuerto aeropuerto1, Aeropuerto aeropuerto2) {
        double lat1 = Math.toRadians(aeropuerto1.getLatitud());
        double lon1 = Math.toRadians(aeropuerto1.getLongitud());
        double lat2 = Math.toRadians(aeropuerto2.getLatitud());
        double lon2 = Math.toRadians(aeropuerto2.getLongitud());

        double deltaX = (lon2 - lon1) * Math.cos((lat1 + lat2) / 2);
        double deltaY = (lat2 - lat1);

        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY) * RADIO_TERRESTRE;
        return (int) Math.round(distancia);
    }
}
