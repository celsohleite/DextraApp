package br.com.dextra.dextraapp.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;

import br.com.dextra.dextraapp.R;
import br.com.dextra.dextraapp.model.CardapioModel;

/**
 * Created by leite on 11/09/2018.
 */

public class MainAdapter extends BaseAdapter {

    private static final String TAG = "MainAdapter";
    private List<CardapioModel> mListCardapio;
    private Context mContext;
    private int mIdLayout;

    public MainAdapter(Context context, List<CardapioModel> mCardapio) {
        this.mListCardapio = mCardapio;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.content_item_cardapio ,null);

            holder = new ViewHolder();

            holder.iv_lancheView = convertView.findViewById(R.id.imgv_lanche);
            holder.tv_titleLanche = convertView.findViewById(R.id.txtv_title_lanche);
            holder.tv_descricaoIgredientes= convertView.findViewById(R.id.txtv_descricao_lanche);
            holder.tv_valorLanche = convertView.findViewById(R.id.txtv_valor_lanche);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        CardapioModel itemCardapio = (CardapioModel) getItem(position);
            //formata moeda local
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            Bitmap bitmap = null;
        try {
            //carregamento dos lanches on line
            Picasso.get().load(itemCardapio.getImage()).into(holder.iv_lancheView);

            //set valores do cardapio
            holder.tv_titleLanche.setText(itemCardapio.getDescricaoLanche());
            //adicionar os ingredientes
            holder.tv_descricaoIgredientes.setText(itemCardapio.getIngrediente());
            ;
            holder.tv_valorLanche.setTextSize(20);
            holder.tv_valorLanche.setTypeface(null, Typeface.BOLD);
            holder.tv_valorLanche.setText(String.valueOf(numberFormat.format(itemCardapio.getPreco())));
        }catch (Exception e){
            Log.i(TAG, "[ EXCEPTION ]"+ e);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        if(mListCardapio !=null)
            return mListCardapio.size();
         return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mListCardapio !=null)
            return mListCardapio.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mListCardapio.indexOf(getItem(position));
    }

    private class ViewHolder{
        ImageView iv_lancheView;
        TextView tv_titleLanche;
        TextView tv_descricaoIgredientes;
        TextView tv_valorLanche;
    }

}
