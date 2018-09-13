package br.com.dextra.dextraapp.model;

import java.util.List;

/**
 * Created by leite on 10/09/2018.
 */

public class CardapioModel {

    private Long id;
    private String image;
    private String descricaoLanche;
    private String ingrediente;
    private Double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescricaoLanche() {
        return descricaoLanche;
    }

    public void setDescricaoLanche(String descricaoLanche) {
        this.descricaoLanche = descricaoLanche;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardapioModel that = (CardapioModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (descricaoLanche != null ? !descricaoLanche.equals(that.descricaoLanche) : that.descricaoLanche != null)
            return false;
        if (ingrediente != null ? !ingrediente.equals(that.ingrediente) : that.ingrediente != null)
            return false;
        return preco != null ? preco.equals(that.preco) : that.preco == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (descricaoLanche != null ? descricaoLanche.hashCode() : 0);
        result = 31 * result + (ingrediente != null ? ingrediente.hashCode() : 0);
        result = 31 * result + (preco != null ? preco.hashCode() : 0);
        return result;
    }
}
