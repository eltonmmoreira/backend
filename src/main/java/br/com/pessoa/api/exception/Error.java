package br.com.pessoa.api.exception;

import java.util.HashMap;
import java.util.Map;

public class Error {

    private String message;

    private String type;

    private Map<String, Object> parameters = new HashMap<>();

    public Error(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void addParameter(String key, Object value) {
        this.parameters.put(key, value);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Error setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }
}
