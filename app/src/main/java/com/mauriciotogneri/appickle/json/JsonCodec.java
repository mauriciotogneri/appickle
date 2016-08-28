package com.mauriciotogneri.appickle.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mauriciotogneri.appickle.model.fields.CheckboxField;
import com.mauriciotogneri.appickle.model.fields.DateField;
import com.mauriciotogneri.appickle.model.fields.DropdownField;
import com.mauriciotogneri.appickle.model.fields.FieldValue;
import com.mauriciotogneri.appickle.model.fields.RadioField;
import com.mauriciotogneri.appickle.model.fields.SurveyField;
import com.mauriciotogneri.appickle.model.fields.TextField;
import com.mauriciotogneri.appickle.model.fields.TimeField;
import com.mauriciotogneri.appickle.model.fields.ToggleField;
import com.mauriciotogneri.appickle.model.reports.EmailReport;
import com.mauriciotogneri.appickle.model.reports.HttpReport;
import com.mauriciotogneri.appickle.model.reports.Report;
import com.mauriciotogneri.appickle.model.reports.Report.Output;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.model.session.Survey;

import java.lang.reflect.Type;
import java.util.List;

public class JsonCodec
{
    private final Gson gson;

    public JsonCodec()
    {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Session.class, new SessionAdapter())
                .registerTypeAdapter(Survey.class, new SurveyAdapter())
                .registerTypeHierarchyAdapter(SurveyField.class, new SurveyFieldAdapter())
                .registerTypeAdapter(FieldValue.class, new FieldValueAdapter())
                .registerTypeHierarchyAdapter(Report.class, new ReportAdapter())
                .create();
    }

    public <T> T fromString(String string, Class<T> clazz)
    {
        return gson.fromJson(string, clazz);
    }

    public String toJson(Object object)
    {
        return gson.toJson(object);
    }

    private static JsonArray toArray(List<?> list, JsonSerializationContext context)
    {
        JsonArray array = new JsonArray();

        for (Object element : list)
        {
            array.add(context.serialize(element));
        }

        return array;
    }

    public static class SessionAdapter implements JsonSerializer<Session>, JsonDeserializer<Session>
    {
        private static final String ID = "id";
        private static final String TITLE = "title";
        private static final String DESCRIPTION = "description";
        private static final String THUMBNAILS = "thumbnails";
        private static final String SURVEY = "survey";
        private static final String REPORT = "report";
        private static final String FEATURES = "features";

        public Session deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        {
            JsonObjectWrapper json = new JsonObjectWrapper(element);

            String id = json.getString(ID);
            String title = json.getString(TITLE);
            String description = json.getString(DESCRIPTION);
            List<String> thumbnails = json.getList(THUMBNAILS, String.class, context);
            Survey survey = context.deserialize(json.get(SURVEY), Survey.class);
            Report report = context.deserialize(json.get(REPORT), Report.class);
            List<String> features = json.getList(FEATURES, String.class, context);

            return new Session(id, title, description, thumbnails, survey, report, features);
        }

        public JsonElement serialize(Session session, Type type, JsonSerializationContext context)
        {
            JsonObject result = new JsonObject();
            result.addProperty(ID, session.id());
            result.addProperty(TITLE, session.title());
            result.addProperty(DESCRIPTION, session.description());
            result.add(THUMBNAILS, toArray(session.thumbnails(), context));
            result.add(SURVEY, context.serialize(session.survey()));
            result.add(REPORT, context.serialize(session.report()));
            result.add(FEATURES, toArray(session.features(), context));

            return result;
        }
    }

    public static class SurveyAdapter implements JsonSerializer<Survey>, JsonDeserializer<Survey>
    {
        private static final String DESCRIPTION = "description";
        private static final String FIELDS = "fields";

        public Survey deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        {
            JsonObjectWrapper json = new JsonObjectWrapper(element);

            String description = json.getString(DESCRIPTION);
            List<SurveyField> fields = json.getList(FIELDS, SurveyField.class, context);

            return new Survey(description, fields);
        }

        public JsonElement serialize(Survey survey, Type type, JsonSerializationContext context)
        {
            JsonObject result = new JsonObject();
            result.addProperty(DESCRIPTION, survey.description());
            result.add(FIELDS, toArray(survey.fields(), context));

            return result;
        }
    }

    public static class SurveyFieldAdapter implements JsonSerializer<SurveyField>, JsonDeserializer<SurveyField>
    {
        private static final String ID = "id";
        private static final String TYPE = "type";
        private static final String DESCRIPTION = "description";
        private static final String ERROR = "error";
        private static final String REQUIRED = "required";
        private static final String FORMAT = "format";
        private static final String PLACEHOLDER = "placeholder";
        private static final String DEFAULT_VALUE = "defaultValue";
        private static final String VALUES = "values";
        private static final String SELECTED = "selected";
        private static final String RESULT = "result";

        public enum FieldType
        {
            text,
            radio,
            dropdown,
            checkbox,
            toggle,
            date,
            time
        }

        public enum Format
        {
            text(0x00000001 | 0x000000b1),
            textMultiLine(0x00020001 | 0x000000b1),
            textAutoComplete(0x00010001),
            integer(0x00000002),
            decimal(0x00002002),
            telephone(0x00000003),
            email(0x00000021),
            uri(0x00000011);

            private final int value;

            Format(int value)
            {
                this.value = value;
            }

            public int value()
            {
                return value;
            }
        }

        public SurveyField deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        {
            JsonObjectWrapper json = new JsonObjectWrapper(element);

            String id = json.getString(ID);
            FieldType fieldType = json.getEnum(TYPE, FieldType.class);
            String description = json.getString(DESCRIPTION);
            String error = json.getString(ERROR);
            Boolean required = json.getBoolean(REQUIRED);
            String result = json.getString(RESULT);

            switch (fieldType)
            {
                case text:
                    Format format = json.getEnum(FORMAT, Format.class);
                    String textPlaceholder = json.getString(PLACEHOLDER);
                    String textDefaultValue = json.getString(DEFAULT_VALUE);
                    return new TextField(id, description, error, required, result, format, textPlaceholder, textDefaultValue);

                case radio:
                    List<FieldValue> radioValues = json.getList(VALUES, FieldValue.class, context);
                    return new RadioField(FieldType.radio, id, description, error, required, result, radioValues);

                case dropdown:
                    List<FieldValue> dropdownValues = json.getList(VALUES, FieldValue.class, context);
                    return new DropdownField(FieldType.dropdown, id, description, error, required, result, dropdownValues);

                case checkbox:
                    List<FieldValue> checkboxValue = json.getList(VALUES, FieldValue.class, context);
                    return new CheckboxField(FieldType.checkbox, id, description, error, required, result, checkboxValue);

                case toggle:
                    Boolean toggleSelected = json.getBoolean(SELECTED);
                    return new ToggleField(id, description, error, required, result, toggleSelected);

                case date:
                    return new DateField(id, description, error, required, result);

                case time:
                    return new TimeField(id, description, error, required, result);
            }

            throw new RuntimeException();
        }

        public JsonElement serialize(SurveyField field, Type type, JsonSerializationContext context)
        {
            JsonObject result = new JsonObject();
            result.addProperty(ID, field.id());
            result.addProperty(DESCRIPTION, field.description());
            result.addProperty(ERROR, field.error());
            result.addProperty(REQUIRED, field.required());
            result.addProperty(RESULT, field.result());

            if (field instanceof TextField)
            {
                TextField textField = (TextField) field;
                result.addProperty(TYPE, FieldType.text.toString());
                result.addProperty(FORMAT, textField.format().toString());
                result.addProperty(PLACEHOLDER, textField.placeholder());
                result.addProperty(DEFAULT_VALUE, textField.defaultValue());
            }
            else if (field instanceof RadioField)
            {
                RadioField radioField = (RadioField) field;
                result.addProperty(TYPE, FieldType.radio.toString());
                result.add(VALUES, toArray(radioField.values(), context));
            }
            else if (field instanceof DropdownField)
            {
                DropdownField dropdownField = (DropdownField) field;
                result.addProperty(TYPE, FieldType.dropdown.toString());
                result.add(VALUES, toArray(dropdownField.values(), context));
            }
            else if (field instanceof CheckboxField)
            {
                CheckboxField checkboxField = (CheckboxField) field;
                result.addProperty(TYPE, FieldType.checkbox.toString());
                result.add(VALUES, toArray(checkboxField.values(), context));
            }
            else if (field instanceof ToggleField)
            {
                ToggleField toggleField = (ToggleField) field;
                result.addProperty(TYPE, FieldType.toggle.toString());
                result.addProperty(SELECTED, toggleField.isSelected());
            }
            else if (field instanceof DateField)
            {
                result.addProperty(TYPE, FieldType.date.toString());
            }
            else if (field instanceof TimeField)
            {
                result.addProperty(TYPE, FieldType.time.toString());
            }
            else
            {
                throw new JsonParseException("Field type not defined");
            }

            return result;
        }
    }

    public static class FieldValueAdapter implements JsonSerializer<FieldValue>, JsonDeserializer<FieldValue>
    {
        private static final String KEY = "key";
        private static final String LABEL = "label";
        private static final String SELECTED = "selected";

        public FieldValue deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        {
            JsonObjectWrapper json = new JsonObjectWrapper(element);

            String key = json.getString(KEY);
            String label = json.getString(LABEL);
            Boolean selected = json.getBoolean(SELECTED);

            return new FieldValue(key, label, selected);
        }

        public JsonElement serialize(FieldValue fieldValue, Type type, JsonSerializationContext context)
        {
            JsonObject result = new JsonObject();

            result.addProperty(KEY, fieldValue.key());
            result.addProperty(LABEL, fieldValue.label());
            result.addProperty(SELECTED, fieldValue.isSelected());

            return result;
        }
    }

    public static class ReportAdapter implements JsonSerializer<Report>, JsonDeserializer<Report>
    {
        private static final String TARGET = "target";
        private static final String URI = "uri";
        private static final String EMAIL = "email";
        private static final String SUBJECT = "subject";
        private static final String OUTPUT = "output";

        private enum Target
        {
            http,
            email
        }

        public Report deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        {
            JsonObjectWrapper json = new JsonObjectWrapper(element);

            Target target = json.getEnum(TARGET, Target.class);
            Output output = json.getEnum(OUTPUT, Output.class);

            switch (target)
            {
                case http:
                    String uri = json.getString(URI);
                    return new HttpReport(uri, output);

                case email:
                    String email = json.getString(EMAIL);
                    String subject = json.getString(SUBJECT);
                    return new EmailReport(email, subject, output);
            }

            throw new JsonParseException("Target not defined");
        }

        public JsonElement serialize(Report report, Type type, JsonSerializationContext context)
        {
            JsonObject result = new JsonObject();

            result.addProperty(OUTPUT, report.output().toString());

            if (report instanceof HttpReport)
            {
                HttpReport httpReport = (HttpReport) report;
                result.addProperty(TARGET, Target.http.toString());
                result.addProperty(URI, httpReport.uri());
            }
            else if (report instanceof EmailReport)
            {
                EmailReport emailReport = (EmailReport) report;
                result.addProperty(TARGET, Target.email.toString());
                result.addProperty(EMAIL, emailReport.email());
                result.addProperty(SUBJECT, emailReport.subject());
            }
            else
            {
                throw new JsonParseException("Target not defined");
            }

            return result;
        }
    }
}