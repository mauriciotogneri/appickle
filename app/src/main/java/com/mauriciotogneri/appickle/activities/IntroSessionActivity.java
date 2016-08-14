package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroSessionActivity extends BaseActivity
{
    public static final String PARAMETER_SESSION_ID = "session.id";

    private Session session;

    @BindView(R.id.session_title)
    public TextView sessionTitle;

    @BindView(R.id.session_description)
    public TextView sessionDescription;

    @BindView(R.id.thumbnail_container)
    public ViewGroup thumbnailContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        String sessionId = parameter(PARAMETER_SESSION_ID);
        SessionStorage sessionStorage = new SessionStorage(this, sessionId);
        this.session = sessionStorage.loadSession();

        displaySession(session);

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

    @Override
    protected int layout()
    {
        return R.layout.screen_intro;
    }
}