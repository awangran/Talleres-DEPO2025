package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas{
	
	protected final int COSTO_POR_KM = 1000;

	public CalculadoraTarifasTemporadaAlta() {
	};

	@Override
	protected int calcularCostoBase​(Vuelo vuelo, Cliente cliente) {
		//aqui que tiene que ver el cliente? no entiendo
		int distancia = calcularDistanciaVuelo​(vuelo.getRuta());
		return distancia*COSTO_POR_KM;
	}

	@Override
	protected double calcularPorcentajeDescuento​(Cliente cliente) {
		// TODO Auto-generated method stub
		//dice que segun el tipo de cliente dar un numero entre 0 y 1, pero esto no se especifica como
		return 0;
	}

}
