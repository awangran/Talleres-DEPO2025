package uniandes.dpoo.aerolinea.persistencia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.*;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject raiz = new JSONObject();

        // Aviones
        JSONArray arrAviones = new JSONArray();
        for (Avion a : aerolinea.getAviones()) {
            JSONObject obj = new JSONObject();
            obj.put("nombre", a.getNombre());
            obj.put("capacidad", a.getCapacidad());
            arrAviones.put(obj);
        }
        raiz.put("aviones", arrAviones);

        // Aeropuertos
        JSONArray arrAeropuertos = new JSONArray();
        for (Ruta r : aerolinea.getRutas()) {
            JSONObject o1 = new JSONObject();
            o1.put("codigo", r.getOrigen().getCodigo());
            o1.put("nombre", r.getOrigen().getNombre());
            o1.put("ciudad", r.getOrigen().getNombreCiudad());
            o1.put("lat", r.getOrigen().getLatitud());
            o1.put("lon", r.getOrigen().getLongitud());
            arrAeropuertos.put(o1);

            JSONObject o2 = new JSONObject();
            o2.put("codigo", r.getDestino().getCodigo());
            o2.put("nombre", r.getDestino().getNombre());
            o2.put("ciudad", r.getDestino().getNombreCiudad());
            o2.put("lat", r.getDestino().getLatitud());
            o2.put("lon", r.getDestino().getLongitud());
            arrAeropuertos.put(o2);
        }
        raiz.put("aeropuertos", arrAeropuertos);

        // Rutas
        JSONArray arrRutas = new JSONArray();
        for (Ruta r : aerolinea.getRutas()) {
            JSONObject obj = new JSONObject();
            obj.put("codigoRuta", r.getCodigoRuta());
            obj.put("origen", r.getOrigen().getCodigo());
            obj.put("destino", r.getDestino().getCodigo());
            obj.put("horaSalida", r.getHoraSalida());
            obj.put("horaLlegada", r.getHoraLlegada());
            arrRutas.put(obj);
        }
        raiz.put("rutas", arrRutas);

        // Vuelos
        JSONArray arrVuelos = new JSONArray();
        for (Vuelo v : aerolinea.getVuelos()) {
            JSONObject obj = new JSONObject();
            obj.put("fecha", v.getFecha());
            obj.put("codigoRuta", v.getRuta().getCodigoRuta());
            obj.put("avion", v.getAvion().getNombre());
            arrVuelos.put(obj);
        }
        raiz.put("vuelos", arrVuelos);

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(raiz.toString(4));
        }
    }

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
        try (FileReader reader = new FileReader(archivo)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject raiz = new JSONObject(tokener);

            // Aviones
            JSONArray arrAviones = raiz.getJSONArray("aviones");
            for (int i = 0; i < arrAviones.length(); i++) {
                JSONObject obj = arrAviones.getJSONObject(i);
                aerolinea.agregarAvion(new Avion(obj.getString("nombre"), obj.getInt("capacidad")));
            }

            // Aeropuertos
            Map<String, Aeropuerto> mapaAeropuertos = new HashMap<>();
            JSONArray arrAeropuertos = raiz.getJSONArray("aeropuertos");
            for (int i = 0; i < arrAeropuertos.length(); i++) {
                JSONObject obj = arrAeropuertos.getJSONObject(i);
                Aeropuerto ap = new Aeropuerto(
                        obj.getString("nombre"),
                        obj.getString("codigo"),
                        obj.getString("ciudad"),
                        obj.getDouble("lat"),
                        obj.getDouble("lon")
                );
                mapaAeropuertos.put(ap.getCodigo(), ap);
            }

            // Rutas
            JSONArray arrRutas = raiz.getJSONArray("rutas");
            for (int i = 0; i < arrRutas.length(); i++) {
                JSONObject obj = arrRutas.getJSONObject(i);
                Aeropuerto origen = mapaAeropuertos.get(obj.getString("origen"));
                Aeropuerto destino = mapaAeropuertos.get(obj.getString("destino"));

                if (origen == null || destino == null) {
                    throw new InformacionInconsistenteException("Ruta con aeropuertos no registrados.");
                }

                aerolinea.agregarRuta(new Ruta(
                        origen,
                        destino,
                        obj.getString("horaSalida"),
                        obj.getString("horaLlegada"),
                        obj.getString("codigoRuta")
                ));
            }

            // Vuelos
            JSONArray arrVuelos = raiz.getJSONArray("vuelos");
            for (int i = 0; i < arrVuelos.length(); i++) {
                JSONObject obj = arrVuelos.getJSONObject(i);

                String fecha = obj.getString("fecha");
                String codigoRuta = obj.getString("codigoRuta");
                String nombreAvion = obj.getString("avion");

                aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
            }
        } catch (Exception e) {
            if (e instanceof InformacionInconsistenteException) throw (InformacionInconsistenteException) e;
            if (e instanceof IOException) throw (IOException) e;
            throw new InformacionInconsistenteException("Error cargando aerolínea: " + e.getMessage());
        }
    }
}
