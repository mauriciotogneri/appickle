package com.mauriciotogneri.appickle.parser;

import com.mauriciotogneri.appickle.model.gherkin.Background;
import com.mauriciotogneri.appickle.model.gherkin.Feature;
import com.mauriciotogneri.appickle.model.gherkin.Scenario;
import com.mauriciotogneri.appickle.parser.TokenType.Type;
import com.mauriciotogneri.appickle.parser.tokens.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FeatureParser
{
    private final InputStream stream;
    private final TokenType tokenType;

    public FeatureParser(InputStream stream, TokenType tokenType)
    {
        this.stream = stream;
        this.tokenType = tokenType;
    }

    public Feature feature() throws IOException
    {
        for (Token token : tokens())
        {
            // TODO
        }

        return new Feature(new ArrayList<String>(), "", new ArrayList<String>(), new ArrayList<Background>(), new ArrayList<Scenario>());
    }

    private List<Token> tokens() throws IOException
    {
        List<Token> result = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;

        while ((line = bufferedReader.readLine()) != null)
        {
            line = line.trim();

            if (!line.isEmpty())
            {
                Type type = tokenType.type(line);

                if (type != Type.COMMENT)
                {
                    String lexeme = tokenType.lexeme(line).trim();
                    result.add(Token.fromLine(lexeme, type));
                }
            }
        }

        bufferedReader.close();

        return result;
    }
}