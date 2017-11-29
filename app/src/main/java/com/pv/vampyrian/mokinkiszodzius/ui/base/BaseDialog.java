package com.pv.vampyrian.mokinkiszodzius.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;
import com.pv.vampyrian.mokinkiszodzius.util.InjectorUtils;
import com.pv.vampyrian.mokinkiszodzius.viewmodel.SharedViewModel;
import com.pv.vampyrian.mokinkiszodzius.viewmodel.SharedViewModelFactory;

public abstract class BaseDialog extends DialogFragment {

    private static final String LOG_TAG = BaseDialog.class.getSimpleName();
    protected SharedViewModel sharedViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedViewModelFactory factory= InjectorUtils.provideSharedViewModelFactory(getContext());
        sharedViewModel = ViewModelProviders.of(getActivity(),factory).get(SharedViewModel.class);
    }

    protected void hideKeyboard () {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).hideKeyboard();
        }
    }

}
