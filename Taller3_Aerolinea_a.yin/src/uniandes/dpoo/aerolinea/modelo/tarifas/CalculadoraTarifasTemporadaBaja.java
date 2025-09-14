package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
    
    protected final int COSTO_POR_KM_NATURAL = 600;
    protected final int COSTO_POR_KM_CORPORATIVO = 900;
    protected final double DESCUENTO_PEQ = 0.02;
    protected final double DESCUENTO_MEDIANAS = 0.1;
    protected final double DESCUENTO_GRANDES = 0.2;

    public CalculadoraTarifasTemporadaBaja() {}

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        String tipo = cliente.getTipoCliente();
        int costoKm = 0;

        if (tipo.equals("Natural")) {
            costoKm = COSTO_POR_KM_NATURAL;
        } 
        else if (tipo.equals("Corporativo")) {
            costoKm = COSTO_POR_KM_CORPORATIVO;
        }

        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        return distancia * costoKm;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente.getTipoCliente().equals("Corporativo")) {
            ClienteCorporativo corp = (ClienteCorporativo) cliente;
            switch (corp.getTamanoEmpresa()) {
                case ClienteCorporativo.GRANDE:
                    return DESCUENTO_GRANDES;
                case ClienteCorporativo.MEDIANA:
                    return DESCUENTO_MEDIANAS;
                case ClienteCorporativo.PEQUENA:
                    return DESCUENTO_PEQ;
            }
        }
        return 0.0; // Naturales no tienen descuento
    }
}
