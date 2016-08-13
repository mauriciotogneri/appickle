package com.mauriciotogneri.appickle.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.Session;
import com.mauriciotogneri.appickle.resources.FileContent;
import com.mauriciotogneri.appickle.storage.SessionsIndexStorage;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewSessionActivity extends BaseActivity
{
    private static final int SELECT_FILE_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle(R.string.screen_new_title);

        ButterKnife.bind(this);
    }

    private void openIntro(Uri uri)
    {
        try
        {
            FileContent fileContent = new FileContent(uri);
            Session session = Session.fromJson(fileContent.content());

            SessionsIndexStorage sessionsIndexStorage = new SessionsIndexStorage(this);
            sessionsIndexStorage.saveSession(session);

            Bundle parameters = new Bundle();
            parameters.putString(IntroSessionActivity.PARAMETER_SESSION_ID, session.id());
            openActivity(IntroSessionActivity.class, parameters);

            finish();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            errorDialog(R.string.screen_new_button_file_error);
        }
    }

    @OnClick(R.id.screen_new_button_file)
    public void onButtonFromFile()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/json");
        startActivityForResult(intent, SELECT_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ((requestCode == SELECT_FILE_REQUEST_CODE) && (resultCode == RESULT_OK))
        {
            Uri uri = data.getData();
            openIntro(uri);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_new;
    }
}