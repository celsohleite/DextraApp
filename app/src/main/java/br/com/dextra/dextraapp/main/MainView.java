package br.com.dextra.dextraapp.main;

import java.util.EventListener;

/**
 * Created by leite on 11/09/2018.
 */

public interface MainView<T, L> extends EventListener {

    public final static String URL_BASE="http://192.168.1.14:8080/api/";

    void onErrorBusiness(T type, String errorMessage);

    void onErrorBusiness(Throwable error);

    void doResultBusiness(L type, Object object);


}
