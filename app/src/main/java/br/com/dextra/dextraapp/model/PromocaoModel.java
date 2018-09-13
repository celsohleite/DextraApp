package br.com.dextra.dextraapp.model;

/**
 * Created by leite on 10/09/2018.
 */

public class PromocaoModel {

    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromocaoModel promocaoModel = (PromocaoModel) o;

        if (id != null ? !id.equals(promocaoModel.id) : promocaoModel.id != null) return false;
        if (name != null ? !name.equals(promocaoModel.name) : promocaoModel.name != null) return false;
        return description != null ? description.equals(promocaoModel.description) : promocaoModel.description == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
