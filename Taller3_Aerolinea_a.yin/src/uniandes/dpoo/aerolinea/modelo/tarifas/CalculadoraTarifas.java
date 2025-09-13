package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	
	public static final double IMPUESTO = 0.28;
	
	public CalculadoraTarifas() {
	};
	
	public int calcularTarifa​(Vuelo vuelo, Cliente cliente) {
		
		int costoBase = calcularCostoBase​(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento​(cliente);
		int impuesto = calcularValorImpuestos​(costoBase);
		
		//editar lo del calculo del descuento, si es substract o si hay q aplicarlo sobre el costo base directo
		return (int) ((costoBase-(costoBase*descuento))+impuesto);
		
	}
	
	protected abstract int calcularCostoBase​(Vuelo vuelo,Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento​(Cliente cliente);
	
	protected int calcularDistanciaVuelo​(Ruta ruta) {
		Aeropuerto origen = ruta.getOrigen();
		Aeropuerto destino = ruta.getDestino();
		
		//asi llamaba a un metodode otra clase?? no se puede fallar.
		int distancia = Aeropuerto.calcularDistancia(origen, destino);
		
		return distancia;
	}
	
	protected int calcularValorImpuestos​(int costoBase) {
		return (int) (costoBase*IMPUESTO);
	}


	
}
