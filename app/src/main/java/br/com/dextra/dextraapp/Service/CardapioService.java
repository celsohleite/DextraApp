package br.com.dextra.dextraapp.Service;

import java.util.List;

import br.com.dextra.dextraapp.model.IngredienteModel;
import br.com.dextra.dextraapp.model.LancheModel;
import br.com.dextra.dextraapp.model.PedidoModel;
import br.com.dextra.dextraapp.model.PromocaoModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by leite on 10/09/2018.
 */

public interface CardapioService {

    public final static String URL_BASE="http://192.168.1.14:8080/api/";

    @GET("ingrediente")
    Call<List<IngredienteModel>> doListIngredientes();

    @GET("lanche")
    Call<List<LancheModel>> doListLanches();

    @GET("promocao")
    Call<List<PromocaoModel>> doListPromocao();

    @GET("pedido")
    Call<List<PedidoModel>> doListPedido();

}
