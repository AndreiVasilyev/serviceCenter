package by.epam.jwdsc.entity;

import java.util.Map;

public class RequestData {
    private Map<String, String[]> requestParameters;
    private Map<String, Object> requestAttributes;
    private String requestPagePath;

    public RequestData(Map<String, String[]> requestParameters, Map<String, Object> requestAttributes, String requestPagePath) {
        this.requestParameters = requestParameters;
        this.requestAttributes = requestAttributes;
        this.requestPagePath = requestPagePath;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(Map<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public String getRequestPagePath() {
        return requestPagePath;
    }

    public void setRequestPagePath(String requestPagePath) {
        this.requestPagePath = requestPagePath;
    }
}
