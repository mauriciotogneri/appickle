package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.uibinder.annotations.BindView;
import com.mauriciotogneri.uibinder.annotations.OnClick;
import com.squareup.picasso.Picasso;

public class IntroSessionActivity extends BaseActivity
{
    private static final String PARAMETER_SESSION_ID = "session.id";

    @BindView(R.id.session_title)
    public TextView sessionTitle;

    @BindView(R.id.session_description)
    public TextView sessionDescription;

    @BindView(R.id.thumbnail_container)
    public ViewGroup thumbnailContainer;

    public static Intent createIntent(Context context, String sessionId)
    {
        Bundle parameters = new Bundle();
        parameters.putString(PARAMETER_SESSION_ID, sessionId);

        Intent intent = new Intent(context, IntroSessionActivity.class);
        intent.putExtras(parameters);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String sessionId = parameter(PARAMETER_SESSION_ID);

        SessionStorage sessionStorage = new SessionStorage(this);
        displaySession(sessionStorage.entity(sessionId));

        toolbarTitle(R.string.screen_intro_title);
    }

    private void displaySession(Session session)
    {
        sessionTitle.setText(session.title());
        sessionDescription.setText(session.description());

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for (String thumbnail : session.thumbnails())
        {
            ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.view_thumbnail, thumbnailContainer, false);
            thumbnailContainer.addView(imageView);
            Picasso.with(this).load(thumbnail).into(imageView);
        }
    }

    @OnClick(R.id.screen_intro_button_next)
    public void onButtonNext()
    {
        String sessionId = parameter(PARAMETER_SESSION_ID);

        Intent intent = SurveyActivity.createIntent(this, sessionId);
        startActivity(intent);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_intro;
    }
}