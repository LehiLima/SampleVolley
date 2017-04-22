package lehilti.samplevolley.repositories.remote;

import java.io.Serializable;

/**
 * Created by Lehi on 18/04/2017.
 */

public class City implements Serializable {

    private String cidade;
    private String estado;

    public City() {

    }

    public City(String cidade, String estado) {
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
