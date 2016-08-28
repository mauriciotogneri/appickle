package com.mauriciotogneri.appickle.model.session;

public class Step
{
    private final gherkin.ast.Step step;

    public Step(gherkin.ast.Step step)
    {
        this.step = step;
    }

    public String content()
    {
        return String.format("%s %s", step.getKeyword(), step.getText());
    }
}