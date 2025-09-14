package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

public class ClienteCorporativo extends Cliente {
    public static final String CORPORATIVO = "Corporativo";
    public static final int GRANDE = 1;
    public static final int MEDIANA = 2;
    public static final int PEQUENA = 3;

    private String nombreEmpresa;
    private int tamanoEmpresa;

    public ClienteCorporativo(String nombreEmpresa, int tamano) {
        if (tamano != GRANDE && tamano != MEDIANA && tamano != PEQUENA) {
            throw new IllegalArgumentException("Tamaño de empresa inválido: " + tamano);
        }
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public int getTamanoEmpresa() {
        return this.tamanoEmpresa;
    }

    @Override
    public String getTipoCliente() {
        return CORPORATIVO;
    }

    @Override
    public String getIdentificador() {
        return this.nombreEmpresa;
    }

    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente) {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tam = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tam);
    }

    public JSONObject salvarEnJSON() {
        JSONObject jobject = new JSONObject();
        jobject.put("nombreEmpresa", this.nombreEmpresa);
        jobject.put("tamanoEmpresa", this.tamanoEmpresa);
        jobject.put("tipo", CORPORATIVO);
        return jobject;
    }
}
