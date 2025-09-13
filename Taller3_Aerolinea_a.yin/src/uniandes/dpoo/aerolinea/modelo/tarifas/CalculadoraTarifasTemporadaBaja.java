package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	protected final int COSTO_POR_KM_NATURAL= 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;

	public CalculadoraTarifasTemporadaBaja() {};

	@Override
	protected int calcularCostoBase​(Vuelo vuelo, Cliente cliente) {
		String tipo = cliente.getTipoCliente();
		int costokm = 0;
		
		if (tipo == "Natural") {
			costokm = COSTO_POR_KM_NATURAL; 
		} else if (tipo == "Corporativo") {
			costokm = COSTO_POR_KM_CORPORATIVO;
		}
		
		int distancia = calcularDistanciaVuelo​(vuelo.getRuta());
		
		return distancia*costokm;
	}

	@Override
	protected double calcularPorcentajeDescuento​(Cliente cliente) {
		String tipo = cliente.getTipoCliente();
		if (tipo == "Corporativo") {
			//como saco el tamaño si es del cliente extendido????
			cliente.getTamanoEmpresa();
		}
		return 0;
	}

}
