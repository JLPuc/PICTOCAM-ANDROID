package com.sistemas.pictocam.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luis Puc on 03/03/2018.
 */

public abstract class BaseFragment extends DialogFragment {


    private boolean esDialogo = false;
    private boolean ocultarDialogoAuto = true;
    protected BaseFragment contextoPadre;
    protected int tareasEjecutadas;
    protected int tareasEjecutadasError;
    protected int tareasEjecutadasExito;
    protected String tareaEjecutada;
    /**
     * Variable seteada en {@link #setFragmentListener(BaseActivity)}
     * Indispensable para la comunicacion activity - fragment.
     */
    protected BaseActivity fragmentListener;

    /**
     * Actividad contenedora seteada en {@link #onViewCreated},
     * utiliza esta variable cuando requieras un Contexto.
     */
    protected Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    /**
     * Setea la interface indispensable para la comunicacion entre fragment
     * y su actividad contenedora.
     *
     * @param fragmentListener se pasa una instancia de la interfaz {@link BaseActivity},
     *                         esta interfaz, siempre debe implementarse en un activity.
     */
    public void setFragmentListener(BaseActivity fragmentListener) {
        this.fragmentListener = fragmentListener;
    }

    /**
     * No eliminar el casteo (BaseActivity), ya que es importante para la navegacion de la app.
     */
    @SuppressWarnings("RedundantCast")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((BaseActivity) activity).onBackPressed();
                break;
        }
        return true;
    }

    /**
     * Metodo para inflar el layout del fragment
     *
     * @return Retorna el layout para inflar el fragment, ejemplo:
     * return R.layout.my_fragment;
     */

    /**
     * Metodo para setear un titulo al action bar
     *
     * @return Retorna un String con el titulo.
     */

    /**
     * Metodo para implementar la libreria ButterKnife, en los fragment
     *
     * @param view se pasa el view del fragment hijo.
     */
    private void injectViews(final View view) {
        ButterKnife.bind(this, view);
    }


    /**
     * No remover la anotacion @OnClick, es utilizada por la libreria butterknife.
     *
     * @param view recibe el view al que se hizo click, con switch, compara el id del view y ejecuta
     *             la funcion correspondiente.
     */
    @OnClick
    protected abstract void OnClickView(View view);


    public abstract void onSuccess(Object data);

    public abstract void onError(Object error);

    public abstract void isOffline(Object data);
    /**
     * Shows the progress UI and hides the login form.
     * Muesta el View de Progreso y desactiva el View contenedor.
     *
     * @param show     variable boolean, true para activar el progreso y viceversa.
     * @param view     recibe el view contenedor.
     * @param progress recibe el view de progreso (ProgressBar).
     */
    protected void showProgressView(final boolean show, final View view, final View progress) {
        if (getHost() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                view.setVisibility(show ? View.GONE : View.VISIBLE);
                view.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                progress.setVisibility(show ? View.VISIBLE : View.GONE);
                progress.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progress.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                progress.setVisibility(show ? View.VISIBLE : View.GONE);
                view.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        esDialogo = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (esDialogo == true && ocultarDialogoAuto == true) {
            dismiss();
        }
    }

    public void setOcultarDialogAuto(boolean ocultarDialogAuto) {
        this.ocultarDialogoAuto = ocultarDialogAuto;
    }

    public void ocultarDialogo() {
        if (esDialogo == true && ocultarDialogoAuto == true) {
            dismiss();
        }
    }

    /**
     *
     *  Para implementar un menu en el actionbar, dentro de los fragments que extienden de {@link BaseFragment},
     *  Incluye los siguiente metodos, remplazando el R.menu.my_menu con tu propio menu
     *
     *  IMPORTANTE: No remover la linea: super.onOptionsItemSelected(item);
     *  es importante para la navegacion.
     */
    /**
     * @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
     * menu.clear(); //call the clear method
     * inflater.inflate(R.menu.my_menu, menu);
     * }
     * @Override public boolean onOptionsItemSelected(MenuItem item) {
     * super.onOptionsItemSelected(item);
     * switch (item.getItemId()) {
     * case R.id.my_itemm_id:
     * <p>
     * Toast.makeText(activity, "Hola", Toast.LENGTH_SHORT).show();
     * <p>
     * break;
     * }
     * return true;
     * }
     **/

    public int getTareasEjecutadas() {
        return this.tareasEjecutadasError + this.tareasEjecutadasExito;
    }

    public BaseFragment getContextoPadre() {
        return contextoPadre;
    }

    public void setContextoPadre(BaseFragment contextoPadre) {
        this.contextoPadre = contextoPadre;
    }

    public void setActivityPadre(BaseActivity activityPadre) {
        this.fragmentListener = activityPadre;
    }
}