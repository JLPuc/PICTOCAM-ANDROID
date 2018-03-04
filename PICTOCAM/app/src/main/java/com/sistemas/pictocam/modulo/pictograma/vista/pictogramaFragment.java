package com.sistemas.pictocam.modulo.pictograma.vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sistemas.pictocam.R;
import com.sistemas.pictocam.base.BaseFragment;
import com.sistemas.pictocam.modulo.pictograma.adapterRecyclerView.PictogramaAdapterRecyclerView;
import com.sistemas.pictocam.modulo.pictograma.pojo.PictogramaPojo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class pictogramaFragment extends BaseFragment {

    View view;
    public pictogramaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pictograma, container, false);
        return view;
    }




    @Override
    protected void OnClickView(View view) {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onError(Object error) {

    }

    @Override
    public void isOffline(Object data) {

    }



    private void llenarGrid(ArrayList<PictogramaPojo> pictogramaPojo) {
        PictogramaAdapterRecyclerView pictogramaAdapterRecyclerView =
                new PictogramaAdapterRecyclerView(pictogramaPojo, R.layout.pictograma_cardview, this);

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);
        picturesRecycler.setLayoutManager(new GridLayoutManager(getActivity(),3));

    }


}
