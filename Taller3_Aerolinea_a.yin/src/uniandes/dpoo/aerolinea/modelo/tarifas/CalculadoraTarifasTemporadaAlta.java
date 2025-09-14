package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
    
    protected final int COSTO_POR_KM = 1000;

    public CalculadoraTarifasTemporadaAlta() {}

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        return distancia * COSTO_POR_KM;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
		/* no me especifica la documentacion si tiene o no descuento bro ughhh
		 * if (cliente.getTipoCliente().equals("Natural")) { return 0.0; } else if
		 * (cliente.getTipoCliente().equals("Corporativo")) { ClienteCorporativo corp =
		 * (ClienteCorporativo) cliente; switch (corp.getTamanoEmpresa()) { case
		 * ClienteCorporativo.GRANDE: return 0.2; case ClienteCorporativo.MEDIANA:
		 * return 0.1; case ClienteCorporativo.PEQUENA: return 0.0; } }
		 */
        return 0.0;
    }
}
