package com.mauriciotogneri.appickle.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.network.GetRequest;
import com.mauriciotogneri.appickle.resources.FileContent;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.uibinder.annotations.OnClick;

public class NewSessionActivity extends BaseActivity
{
    private static final int SELECT_FILE_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.screen_new_title);
    }

    private void sessionFromInternet(final String url)
    {
        final ProgressDialog progressDialog = progressDialog();

        new AsyncTask<Void, Void, String>()
        {
            @Override
            protected String doInBackground(Void... voids)
            {
                try
                {
                    GetRequest getRequest = new GetRequest(url);
                    return getRequest.content();
                }
                catch (Exception e)
                {
                    e.printStackTrace();

                    return null;
                }
            }

            @Override
            protected void onPostExecute(String content)
            {
                super.onPostExecute(content);

                if (content != null)
                {
                    try
                    {
                        Session session = Session.fromJsonString(content);

                        if (session != null)
                        {
                            openIntro(session);
                        }
                        else
                        {
                            errorLoadingFromInternet();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

                        errorLoadingFromInternet();
                    }
                }
                else
                {
                    errorLoadingFromInternet();
                }

                progressDialog.dismiss();
            }
        }.execute();
    }

    private void sessionFromFile(final Uri uri)
    {
        final ProgressDialog progressDialog = progressDialog();

        new AsyncTask<Void, Void, String>()
        {
            @Override
            protected String doInBackground(Void... voids)
            {
                try
                {
                    FileContent fileContent = new FileContent(uri);
                    return fileContent.content();
                }
                catch (Exception e)
                {
                    e.printStackTrace();

                    return null;
                }
            }

            @Override
            protected void onPostExecute(String content)
            {
                super.onPostExecute(content);

                if (content != null)
                {
                    try
                    {
                        Session session = Session.fromJsonString(content);

                        if (session != null)
                        {
                            openIntro(session);
                        }
                        else
                        {
                            errorLoadingFromFile();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

                        errorLoadingFromFile();
                    }
                }
                else
                {
                    errorLoadingFromFile();
                }

                progressDialog.dismiss();
            }
        }.execute();
    }

    private void errorLoadingFromInternet()
    {
        errorDialog(R.string.screen_new_button_qrCode_error);
    }

    private void errorLoadingFromFile()
    {
        errorDialog(R.string.screen_new_button_file_error);
    }

    private void openIntro(Session session)
    {
        SessionStorage sessionStorage = new SessionStorage(this);
        sessionStorage.addEntity(session);

        Intent intent = IntroSessionActivity.createIntent(this, session.id());
        startActivity(intent);

        finish();
    }

    @OnClick(R.id.screen_new_button_qrCode)
    public void onButtonFromQrCode()
    {
        // TODO
        sessionFromInternet("http://zeronest.com/appickle/session.json");
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
            sessionFromFile(uri);
        }
    }

    private ProgressDialog progressDialog()
    {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.dialog_downloadingSession));
        progressDialog.setCancelable(false);
        progressDialog.show();
        //progressDialog.setContentView(R.layout.dialog_wait);

        //TextView label = (TextView) progressDialog.findViewById(R.id.dialog_wait_text);
        //label.setText(R.string.dialog_downloadingSession);

        return progressDialog;
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_new_session;
    }
}