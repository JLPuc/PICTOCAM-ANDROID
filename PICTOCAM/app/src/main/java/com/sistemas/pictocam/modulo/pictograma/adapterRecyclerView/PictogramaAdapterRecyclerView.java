package com.sistemas.pictocam.modulo.pictograma.adapterRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sistemas.pictocam.Constantes;
import com.sistemas.pictocam.R;
import com.sistemas.pictocam.base.BaseFragment;
import com.sistemas.pictocam.modulo.pictograma.pojo.PictogramaPojo;

import java.util.ArrayList;

/**
 * Created by Luis Puc on 03/03/2018.
 */

public class PictogramaAdapterRecyclerView  extends RecyclerView.Adapter<PictogramaAdapterRecyclerView.PictureViewHolder> {

    private int resource;
    private BaseFragment fragment;
    ArrayList<PictogramaPojo> objPictogramaPajo;
    public PictogramaAdapterRecyclerView(ArrayList<PictogramaPojo> objPictogramaPajo, int resource, BaseFragment fragment) {
        this.objPictogramaPajo = objPictogramaPajo;
        this.resource = resource;
        this.fragment = fragment;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }
    //Se encarga de asignar los nombre y variables necesarias
    public void onBindViewHolder(final PictureViewHolder holder, final int position) {
        final PictogramaPojo pictograma = objPictogramaPajo.get(position);

        //Ecarga decodificar el arreglo de Bit Para poder Mostrarlo como imagen final.
        holder.imagenPictureCard.setImageBitmap(Constantes.decodificarImagen(pictograma.getRuta()));

        //Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);
        //Evento que se inicia cuando se selecciona el CardView Para el detalle del Sitio
        holder.imagenPictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*
                LayoutInflater layoutInflater = (LayoutInflater) fragment.getActivity().getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.principal_detallesitioimgview, null);
                ImageView newImageView = (ImageView) addView.findViewById(R.id.sampleimageview);
                newImageView.setImageBitmap(Constantes.decodificarImagen(objSitio.getFoto()));;
                mPopField.popView(holder.pictureCard, addView, true);
                /**
                 * @setFotoSesion agrega la Foto en una sesión para evitar consumor más datos.
                 */
              //  SesionSitioPojo sessionDeviceBean = new SesionSitioPojo();
               // sessionDeviceBean.setFoto(objSitio.getFoto());
                //SessionManager.setFotoSession(sessionDeviceBean);

                /**
                 * Se realiza la petición para obtener los datos.
                 */
                //ConsultarDetalleSitios(fragment,objSitio);

            }
        });
    }

    @Override
    public int getItemCount() {
        return objPictogramaPajo.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagenPictureCard;

        public PictureViewHolder(View itemView) {
            super(itemView);
            imagenPictureCard = (ImageView) itemView.findViewById(R.id.imgPictureCard);

        }
    }
}
