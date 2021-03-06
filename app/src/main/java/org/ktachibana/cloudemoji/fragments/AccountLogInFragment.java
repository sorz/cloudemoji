package org.ktachibana.cloudemoji.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import org.ktachibana.cloudemoji.BaseFragment;
import org.ktachibana.cloudemoji.R;
import org.ktachibana.cloudemoji.utils.CredentialsValidator;
import org.ktachibana.cloudemoji.utils.NonCancelableProgressMaterialDialogBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountLogInFragment extends BaseFragment {
    @Bind(R.id.username)
    EditText usernameEditText;
    @Bind(R.id.password)
    EditText passwordEditText;
    @Bind(R.id.log_in)
    Button logInButton;

    public AccountLogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account_log_in, container, false);
        ButterKnife.bind(this, rootView);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        return rootView;
    }

    private void logIn() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!CredentialsValidator.username(username)) {
            usernameEditText.setError(getString(R.string.empty_username));
            showSnackBar(R.string.empty_username);
            return;
        }
        if (!CredentialsValidator.password(password)) {
            passwordEditText.setError(getString(R.string.empty_password));
            showSnackBar(R.string.empty_password);
            return;
        }

        final MaterialDialog dialog = new NonCancelableProgressMaterialDialogBuilder(getActivity())
                .title(R.string.please_wait)
                .content(R.string.logging_in)
                .show();
    }
}
