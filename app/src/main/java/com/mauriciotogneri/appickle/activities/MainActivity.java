package com.mauriciotogneri.appickle.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.network.GetRequest;
import com.mauriciotogneri.appickle.resources.FileContent;
import com.mauriciotogneri.appickle.stats.Statistics;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.uibinder.annotations.BindView;
import com.mauriciotogneri.uibinder.annotations.OnClick;

public class MainActivity extends BaseActivity
{
    private static final int SELECT_FILE_REQUEST_CODE = 1000;

    @BindView(R.id.screen_main_container_new)
    public View containerNew;

    private Statistics statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.app_name);

        statistics = new Statistics(this);
    }

    private void openNewMenu()
    {
        containerNew.setVisibility(View.VISIBLE);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        containerNew.startAnimation(fadeInAnimation);
    }

    private void closeNewMenu()
    {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeOutAnimation.setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                containerNew.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });

        containerNew.startAnimation(fadeOutAnimation);
    }

    @OnClick(R.id.screen_main_button_new)
    @SuppressWarnings("unused")
    public void onButtonNew()
    {
        if (containerNew.getVisibility() == View.GONE)
        {
            openNewMenu();
        }
        else
        {
            closeNewMenu();
        }
    }

    @OnClick(R.id.screen_main_button_load)
    @SuppressWarnings("unused")
    public void onButtonLoad()
    {
        startActivity(LoadSessionActivity.createIntent(this));
    }

    @OnClick(R.id.screen_main_button_qrCode)
    @SuppressWarnings("unused")
    public void onButtonFromQrCode()
    {
        statistics.newFromQrCode();

        // TODO
        sessionFromInternet("http://zeronest.com/appickle/session.json");
    }

    @OnClick(R.id.screen_main_button_file)
    @SuppressWarnings("unused")
    public void onButtonFromFile()
    {
        statistics.newFromQrFile();

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/json");
        startActivityForResult(intent, SELECT_FILE_REQUEST_CODE);
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
        errorDialog(R.string.screen_main_button_qrCode_error);
    }

    private void errorLoadingFromFile()
    {
        errorDialog(R.string.screen_main_button_file_error);
    }

    private void openIntro(Session session)
    {
        SessionStorage sessionStorage = new SessionStorage(this);
        sessionStorage.addEntity(session);

        Intent intent = IntroSessionActivity.createIntent(this, session.id(), true);
        startActivity(intent);

        closeNewMenu();
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

        return progressDialog;
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_main;
    }

    @Override
    protected boolean displayBackButton()
    {
        return false;
    }
}