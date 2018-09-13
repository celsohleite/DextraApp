package br.com.dextra.dextraapp.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.dextra.dextraapp.Service.CardapioService;
import br.com.dextra.dextraapp.model.CardapioModel;
import br.com.dextra.dextraapp.model.IngredienteModel;
import br.com.dextra.dextraapp.model.LancheModel;
import br.com.dextra.dextraapp.model.PedidoModel;
import br.com.dextra.dextraapp.model.PromocaoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leite on 11/09/2018.
 */

public class MainPresenter extends AsyncTask<String, Integer, String> {

    private final static String TAG = "LancheInterector";

    private final MainView<ErrorType, ActionsType> iMainView;

    private List<IngredienteModel> ingredientes;
    private List<LancheModel> lanches;
    private List<PedidoModel> pedidos;
    private List<PromocaoModel> promocoes;
    private List<CardapioModel> cardapio;

    public MainPresenter(Context context, MainView<ErrorType, ActionsType> mainView) {
        iMainView = mainView;
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        getLanches();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    public enum ErrorType {

    }

    public enum ActionsType {
        onCarregaListaLanches
    }

    public void getLanches() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(MainView.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final CardapioService service = retrofit.create(CardapioService.class);
        Call<List<LancheModel>> requestLanches = service.doListLanches();

        requestLanches.enqueue(new Callback<List<LancheModel>>() {
            @Override
            public void onResponse(Call<List<LancheModel>> call, Response<List<LancheModel>> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "[ ERRO LANCHES ]" + response.code());
                } else {
                    //lista de lanches
                    lanches = response.body();

                    //carrega informações dos ingredientes
                    Call<List<IngredienteModel>> requestIngredientes = service.doListIngredientes();
                    requestIngredientes.enqueue(new Callback<List<IngredienteModel>>() {
                        @Override
                        public void onResponse(Call<List<IngredienteModel>> call, Response<List<IngredienteModel>> response) {
                            if (!response.isSuccessful()) {
                                Log.i(TAG, "[ ERRO INGREDIENTES ]" + response.code());
                            } else {
                                CardapioModel itemCardapio = null;
                                cardapio = new ArrayList<CardapioModel>();
                                ingredientes = response.body();
                                for (LancheModel lanche : lanches) {
                                    itemCardapio = new CardapioModel();
                                    String itensIngrediente = "";

                                    itemCardapio.setImage(lanche.getImage());
                                    itemCardapio.setDescricaoLanche(lanche.getName());
                                    //monta ingrediente
                                    Double valorLanche = 0D;
                                    for (int i = 0; i < lanche.getIngredients().size(); i++) {
                                        for (IngredienteModel ingrediente : ingredientes) {
                                            if (ingrediente.getId() == lanche.getIngredients().get(i)) {
                                                itensIngrediente += ingredientes.get(i).getName().concat(", ");
                                                valorLanche += ingredientes.get(i).getPrice();
                                                itemCardapio.setIngrediente(itensIngrediente);
                                                itemCardapio.setPreco(valorLanche);
                                            }
                                        }

                                    }
                                    cardapio.add(itemCardapio);
                                }
                                iMainView.doResultBusiness(ActionsType.onCarregaListaLanches, cardapio);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<IngredienteModel>> call, Throwable t) {
                            Log.i(TAG, "[ ERRO ]" + t);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<LancheModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
