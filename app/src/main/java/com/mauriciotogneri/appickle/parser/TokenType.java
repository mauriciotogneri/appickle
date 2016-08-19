package com.mauriciotogneri.appickle.parser;

public class TokenType
{
    private final String commentValue = "#";
    private final String tagValue = "@";
    private final String featureValue;
    private final String backgroundValue;
    private final String scenarioValue;
    private final String scenarioOutlineValue;
    private final String examplesValue;
    private final String givenValue;
    private final String whenValue;
    private final String thenValue;
    private final String andValue;
    private final String butValue;

    public enum Type
    {
        COMMENT,
        TAG,
        FEATURE,
        BACKGROUND,
        SCENARIO,
        SCENARIO_OUTLINE,
        EXAMPLES,
        GIVEN,
        WHEN,
        THEN,
        AND,
        BUT,
        FREE_TEXT
    }

    public TokenType(String featureValue, String backgroundValue, String scenarioValue, String scenarioOutlineValue, String examplesValue, String givenValue, String whenValue, String thenValue, String andValue, String butValue)
    {
        this.featureValue = featureValue;
        this.backgroundValue = backgroundValue;
        this.scenarioValue = scenarioValue;
        this.scenarioOutlineValue = scenarioOutlineValue;
        this.examplesValue = examplesValue;
        this.givenValue = givenValue;
        this.whenValue = whenValue;
        this.thenValue = thenValue;
        this.andValue = andValue;
        this.butValue = butValue;
    }

    public Type type(String line)
    {
        if (line.startsWith(commentValue))
        {
            return Type.COMMENT;
        }
        else if (line.startsWith(tagValue))
        {
            return Type.TAG;
        }
        else if (line.startsWith(featureValue))
        {
            return Type.FEATURE;
        }
        else if (line.startsWith(backgroundValue))
        {
            return Type.BACKGROUND;
        }
        else if (line.startsWith(scenarioValue))
        {
            return Type.SCENARIO;
        }
        else if (line.startsWith(scenarioOutlineValue))
        {
            return Type.SCENARIO_OUTLINE;
        }
        else if (line.startsWith(examplesValue))
        {
            return Type.EXAMPLES;
        }
        else if (line.startsWith(givenValue))
        {
            return Type.GIVEN;
        }
        else if (line.startsWith(whenValue))
        {
            return Type.WHEN;
        }
        else if (line.startsWith(thenValue))
        {
            return Type.THEN;
        }
        else if (line.startsWith(andValue))
        {
            return Type.AND;
        }
        else if (line.startsWith(butValue))
        {
            return Type.BUT;
        }
        else
        {
            return Type.FREE_TEXT;
        }
    }

    public String lexeme(String line)
    {
        if (line.startsWith(commentValue))
        {
            return line.substring(1);
        }
        else if (line.startsWith(tagValue))
        {
            return line;
        }
        else if (line.startsWith(featureValue))
        {
            return line.substring(featureValue.length());
        }
        else if (line.startsWith(backgroundValue))
        {
            return line.substring(backgroundValue.length());
        }
        else if (line.startsWith(scenarioValue))
        {
            return line.substring(scenarioValue.length());
        }
        else if (line.startsWith(scenarioOutlineValue))
        {
            return line.substring(scenarioOutlineValue.length());
        }
        else if (line.startsWith(examplesValue))
        {
            return line.substring(examplesValue.length());
        }
        else if (line.startsWith(givenValue))
        {
            return line.substring(givenValue.length());
        }
        else if (line.startsWith(whenValue))
        {
            return line.substring(whenValue.length());
        }
        else if (line.startsWith(thenValue))
        {
            return line.substring(thenValue.length());
        }
        else if (line.startsWith(andValue))
        {
            return line.substring(andValue.length());
        }
        else if (line.startsWith(butValue))
        {
            return line.substring(butValue.length());
        }
        else
        {
            return line;
        }
    }
}