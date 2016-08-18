package com.mauriciotogneri.appickle.test;

import com.mauriciotogneri.appickle.parser.FeatureParser;
import com.mauriciotogneri.appickle.parser.TokenType;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;

public class ParserTest
{
    @Test
    public void test() throws Exception
    {
        File file = new File("../sample.feature");

        TokenType tokenType = new TokenType(
                "#",
                "@",
                "Feature:",
                "Background:",
                "Scenario:",
                "Scenario Outline:",
                "Examples",
                "Given",
                "When",
                "Then",
                "And",
                "But");

        FeatureParser featureParser = new FeatureParser(new FileInputStream(file), tokenType);
        featureParser.features();

        assertEquals(true, true);
    }
}