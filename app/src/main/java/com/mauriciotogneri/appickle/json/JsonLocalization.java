package com.mauriciotogneri.appickle.json;

public class JsonLocalization
{
    private final String feature;
    private final String background;
    private final String scenario;
    private final String scenarioOutline;
    private final String examples;
    private final String given;
    private final String when;
    private final String then;
    private final String and;
    private final String but;

    public JsonLocalization(String feature, String background, String scenario, String scenarioOutline, String examples, String given, String when, String then, String and, String but)
    {
        this.feature = feature;
        this.background = background;
        this.scenario = scenario;
        this.scenarioOutline = scenarioOutline;
        this.examples = examples;
        this.given = given;
        this.when = when;
        this.then = then;
        this.and = and;
        this.but = but;
    }

    public String feature()
    {
        return feature;
    }

    public String background()
    {
        return background;
    }

    public String scenario()
    {
        return scenario;
    }

    public String scenarioOutline()
    {
        return scenarioOutline;
    }

    public String examples()
    {
        return examples;
    }

    public String given()
    {
        return given;
    }

    public String when()
    {
        return when;
    }

    public String then()
    {
        return then;
    }

    public String and()
    {
        return and;
    }

    public String but()
    {
        return but;
    }
}