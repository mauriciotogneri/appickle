package com.mauriciotogneri.appickle.parser.tokens;

import com.mauriciotogneri.appickle.parser.TokenType.Type;

import java.util.ArrayList;
import java.util.List;

public class Token
{
    public boolean isTags()
    {
        return false;
    }

    public boolean isFeature()
    {
        return false;
    }

    public boolean isBackground()
    {
        return false;
    }

    public boolean isScenario()
    {
        return false;
    }

    public boolean isScenarioOutline()
    {
        return false;
    }

    public boolean isExamples()
    {
        return false;
    }

    public boolean isGiven()
    {
        return false;
    }

    public boolean isWhen()
    {
        return false;
    }

    public boolean isThen()
    {
        return false;
    }

    public boolean isAnd()
    {
        return false;
    }

    public boolean isBut()
    {
        return false;
    }

    public boolean isFreeText()
    {
        return false;
    }

    private static Token tagsToken(String line)
    {
        List<String> tags = new ArrayList<>();

        for (String part : line.split(" "))
        {
            if (part.startsWith("@"))
            {
                tags.add(part.trim());
            }
            else
            {
                throw new RuntimeException(String.format("Invalid line: %s", line));
            }
        }

        return new TagsToken(tags);
    }

    private static Token featureToken(String line)
    {
        return new FeatureToken(line);
    }

    private static Token backgroundToken()
    {
        return new BackgroundToken();
    }

    private static Token scenarioToken(String line)
    {
        return new ScenarioToken(line);
    }

    private static Token scenarioOutlineToken(String line)
    {
        return new ScenarioOutlineToken(line);
    }

    private static Token examplesToken()
    {
        return new ExamplesToken();
    }

    private static Token givenToken(String line)
    {
        return new GivenToken(line);
    }

    private static Token whenToken(String line)
    {
        return new WhenToken(line);
    }

    private static Token thenToken(String line)
    {
        return new ThenToken(line);
    }

    private static Token andToken(String line)
    {
        return new AndToken(line);
    }

    private static Token butToken(String line)
    {
        return new ButToken(line);
    }

    private static Token freeTextToken(String line)
    {
        return new FreeTextToken(line);
    }

    public static Token fromLine(String lexeme, Type type)
    {
        switch (type)
        {
            case TAG:
                return tagsToken(lexeme);

            case FEATURE:
                return featureToken(lexeme);

            case BACKGROUND:
                return backgroundToken();

            case SCENARIO:
                return scenarioToken(lexeme);

            case SCENARIO_OUTLINE:
                return scenarioOutlineToken(lexeme);

            case EXAMPLES:
                return examplesToken();

            case GIVEN:
                return givenToken(lexeme);

            case WHEN:
                return whenToken(lexeme);

            case THEN:
                return thenToken(lexeme);

            case AND:
                return andToken(lexeme);

            case BUT:
                return butToken(lexeme);

            case FREE_TEXT:
                return freeTextToken(lexeme);
        }

        throw new RuntimeException(String.format("Invalid line: %s", lexeme));
    }
}