package com.mauriciotogneri.appickle.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.Session;
import com.mauriciotogneri.appickle.resources.FileContent;
import com.mauriciotogneri.appickle.storage.SessionsIndexStorage;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewSessionActivity extends BaseActivity
{
    private static final int SELECT_FILE_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.screen_new_title);

        ButterKnife.bind(this);

        sessionFromInternet();
    }

    // TODO: remove
    private void sessionFromInternet()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder().url("http://zeronest.com/appickle/session.json").build();

                    Response response = client.newCall(request).execute();

                    openIntro(Session.fromJson(response.body().string()));
                }
                catch (Exception e)
                {
                    errorDialog(R.string.screen_new_button_file_error);
                }
            }
        });
        thread.start();
    }

    private Session sessionFromUri(Uri uri) throws IOException
    {
        FileContent fileContent = new FileContent(uri);

        return Session.fromJson(fileContent.content());
    }

    private void openIntro(Session session)
    {
        SessionsIndexStorage sessionsIndexStorage = new SessionsIndexStorage(this);
        sessionsIndexStorage.saveSession(session);

        Bundle parameters = new Bundle();
        parameters.putString(IntroSessionActivity.PARAMETER_SESSION_ID, session.id());
        openActivity(IntroSessionActivity.class, parameters);

        finish();
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

            try
            {
                Session session = sessionFromUri(uri);
                openIntro(session);
            }
            catch (Exception e)
            {
                e.printStackTrace();

                errorDialog(R.string.screen_new_button_file_error);
            }
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_new;
    }
}