package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
    
    private List<Tiquete> tiquetesSinUsar;
    private List<Tiquete> tiquetesUsados;
    
    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<>();
        this.tiquetesUsados = new ArrayList<>();
    }
    
    public abstract String getTipoCliente();
    
    public abstract String getIdentificador();
    
    public void agregarTiquete(Tiquete tiquete) {
        if (!tiquete.esUsado()) {
            tiquetesSinUsar.add(tiquete);
        }
    }
    
    public int calcularValorTotalTiquetes() {
        int valor = 0;
        for (Tiquete tiquete : tiquetesSinUsar) {
            valor += tiquete.getTarifa();
        }
        return valor;
    }

    public void usarTiquetes(Vuelo vuelo) {
        for (Tiquete tiquete : new ArrayList<>(tiquetesSinUsar)) {
            if (vuelo.getTiquetes().contains(tiquete)) {
                tiquete.marcarComoUsado();
                tiquetesSinUsar.remove(tiquete);
                tiquetesUsados.add(tiquete);
            }
        }
    }
}
