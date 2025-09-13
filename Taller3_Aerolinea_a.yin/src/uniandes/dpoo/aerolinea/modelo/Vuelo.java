package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private Avion avion;
	
	private java.lang.String fecha;
	
	private Ruta ruta;
	
	private Map<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta,
			java.lang.String fecha,
			Avion avion) {
		
		tiquetes = new HashMap<String, Tiquete>();
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
	public java.util.Collection<Tiquete> getTiquetes()
	{
		return (Collection<Tiquete>) this.tiquetes;
	}
	
	public boolean equals​(java.lang.Object obj) {
		return false; //TODO
	}

	public int venderTiquetes​(Cliente cliente,
			CalculadoraTarifas calculadora,
			int cantidad) throws VueloSobrevendidoException {
		// todo la calculadora primero
		
		return 0;
	}

	
	
}
