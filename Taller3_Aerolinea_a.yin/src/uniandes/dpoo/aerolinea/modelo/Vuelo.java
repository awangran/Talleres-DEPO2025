package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private Avion avion;
	
	private java.lang.String fecha;
	
	private Ruta ruta;
	
	private Map<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
	    this.ruta = ruta;
	    this.fecha = fecha;
	    this.avion = avion;
	    this.tiquetes = new HashMap<String, Tiquete>();
	}

	
	public Ruta getRuta() {
		return this.ruta;
	}
	
	public java.lang.String getFecha() {
		return this.fecha;
	}
	
	public Avion getAvion() {
		return this.avion;
	}
	
	//porque me lo pide como collection si arriba lo tengo como map? lo deje como mapa mientras
	public Collection<Tiquete> getTiquetes() {
	    return this.tiquetes.values();
	}

	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Vuelo vuelo = (Vuelo) obj;
	    return fecha.equals(vuelo.fecha) && ruta.equals(vuelo.ruta);
	}


	public int venderTiquetes​(Cliente cliente,
			CalculadoraTarifas calculadora,
			int cantidad) throws VueloSobrevendidoException {
		int vendidos = tiquetes.size();
	    int capacidad = avion.getCapacidad();

	    if (vendidos + cantidad > capacidad) {
	        throw new VueloSobrevendidoException("El vuelo está sobrevendido");
	    }

	    int total = 0;
	    for (int i = 0; i < cantidad; i++) {
	        int tarifa = calculadora.calcularTarifa(this, cliente);

	        Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
	        GeneradorTiquetes.registrarTiquete(tiquete);

	        tiquetes.put(tiquete.getCodigo(), tiquete);

	        cliente.agregarTiquete(tiquete);

	        total += tarifa;
	    }
	    return total;
	}

	
	
}
