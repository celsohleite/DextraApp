package br.com.dextra.dextraapp.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.dextra.dextraapp.R;
import br.com.dextra.dextraapp.meulanche.MeuLancheActivity;
import br.com.dextra.dextraapp.model.CardapioModel;
import br.com.dextra.dextraapp.pagamento.PagamentoActivity;
import br.com.dextra.dextraapp.pedido.PedidoActivity;
import br.com.dextra.dextraapp.promocao.PromocaoActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainView<MainPresenter.ErrorType, MainPresenter.ActionsType> {

    //Adapters
    private MainAdapter mainAdapter;
    //Components
    private ListView mListaLanches;

    private List<CardapioModel> mListCardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mListCardapio = new ArrayList<CardapioModel>();
        onPermissao();
        //inicializar presenter
        doExecutePresenter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
    implementações futuras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      try {
          switch (item.getItemId()) {
              case R.id.action_settings:
                  return true;
              default:
                  return super.onOptionsItemSelected(item);
          }
      }catch (Exception e){
          e.printStackTrace();
      }

    }
    */


    private void doExecutePresenter(){
        new MainPresenter(this, this).execute();
    }

    private void doCarregarListViewLanches(Object object){
        mListCardapio = (List<CardapioModel>) object;
        mListaLanches = findViewById(R.id.listv_item_cardapio);
        mainAdapter = new MainAdapter(this, mListCardapio);
        mListaLanches.setAdapter(mainAdapter);
    }

    @Override
    public void onErrorBusiness(MainPresenter.ErrorType type, String errorMessage) {

    }

    @Override
    public void onErrorBusiness(Throwable error) {

    }

    @Override
    public void doResultBusiness(MainPresenter.ActionsType type, Object object) {
        switch (type){
            case onCarregaListaLanches :
                doCarregarListViewLanches(object);
                break;

        }
    }

    public void onPermissao() {

        String permissaoGPS = "this.checkCallingOrSelfPermission";

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
            this.checkCallingOrSelfPermission(permissaoGPS);

        }
    }

    private void adicionaPedido_onClick(View view){
        Intent intent = new Intent(this, PedidoActivity.class);
        startActivity(intent);
    }

    //menu laterial
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_promocao:
                break;

            case R.id.nav_montar_meu_lanche:
                break;

            case R.id.nav_pedido:
                break;

            case R.id.nav_efetuar_pagamento:
                break;

            default:
                break;

        }

        return false;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
