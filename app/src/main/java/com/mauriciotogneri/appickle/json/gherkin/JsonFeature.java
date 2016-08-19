package com.mauriciotogneri.appickle.json.gherkin;

import com.mauriciotogneri.appickle.json.JsonEntity;
import com.mauriciotogneri.appickle.model.gherkin.Background;
import com.mauriciotogneri.appickle.model.gherkin.Feature;
import com.mauriciotogneri.appickle.model.gherkin.Scenario;

import java.util.ArrayList;

public class JsonFeature extends JsonEntity<Feature>
{
    @Override
    public Feature model()
    {
        return new Feature(new ArrayList<String>(), "", new ArrayList<String>(), new ArrayList<Background>(), new ArrayList<Scenario>()); // TODO
    }
}