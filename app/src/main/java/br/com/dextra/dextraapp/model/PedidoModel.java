package br.com.dextra.dextraapp.model;

/**
 * Created by leite on 10/09/2018.
 */

public class PedidoModel {

    private int idPedido;


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoModel pedidoModel = (PedidoModel) o;

        return idPedido == pedidoModel.idPedido;

    }

    @Override
    public int hashCode() {
        return idPedido;
    }
}
