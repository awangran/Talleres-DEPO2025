package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	
	private List<Tiquete> tiquetesSinUsar;
	
	private List<Tiquete> tiquetesUsados;
	
	public Cliente() {
	}
	
	public abstract java.lang.String getTipoCliente();
	
	public abstract java.lang.String getIdentificador();
	
	public void agregarTiquete​(Tiquete tiquete) {
		if (!tiquete.esUsado()) {
			tiquetesSinUsar.add(tiquete);
		}
		
	}
	
	public int calcularValorTotalTiquetes() {
		int valor = 0;
		
		for (Tiquete tiquete : tiquetesSinUsar) {
			int tarifa = tiquete.getTarifa();
			valor += tarifa;
		}
		
		return valor;
	}

	public void usarTiquetes​(Vuelo vuelo) {
		Collection<Tiquete> tiquetes = vuelo.getTiquetes();
		
		for(Tiquete tiquete: tiquetes) {
			tiquete.marcarComoUsado();
			int index = tiquetesSinUsar.indexOf(tiquete);
			tiquetesSinUsar.remove(index);
			tiquetesUsados.add(tiquete);
		}
		
	}

	
}
