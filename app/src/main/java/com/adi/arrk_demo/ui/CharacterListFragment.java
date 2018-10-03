package com.adi.arrk_demo.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.adi.arrk_demo.R;
import com.adi.arrk_demo.adapter.StarWarsAdapter;
import com.adi.arrk_demo.databinding.FragmentCharacterListBinding;
import com.adi.arrk_demo.service.model.ApiResponse;
import com.adi.arrk_demo.viewmodel.StarWarsViewModel;


public class CharacterListFragment extends Fragment {
    public static final String TAG = CharacterListFragment.class.getSimpleName();
    private StarWarsAdapter starWarsAdapter;
    private FragmentCharacterListBinding binding;
    StarWarsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false);

        starWarsAdapter = new StarWarsAdapter();
        binding.projectList.setAdapter(starWarsAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StarWarsViewModel.Factory factory = new StarWarsViewModel.Factory(
                getActivity().getApplication());
        viewModel = ViewModelProviders.of(getActivity(), factory).get(StarWarsViewModel.class);
        binding.setIsLoading(true);
        observeViewModel(viewModel);
        viewModel.callApi(1);

    }

    private void observeViewModel(StarWarsViewModel viewModel) {
        viewModel.getObservableProject().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse) {
                if (apiResponse != null) {
                    binding.setIsLoading(false);
                    starWarsAdapter.setProjectList(apiResponse.getResults());
                } else {
                    showRetryDilog();
                }
            }
        });
    }

    void showRetryDilog() {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.error_occurred)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        binding.setIsLoading(true);
                        viewModel.callApi(1);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        getActivity().finish();
                    }
                }).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }
}
