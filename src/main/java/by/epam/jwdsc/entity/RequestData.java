package by.epam.jwdsc.entity;

import java.util.Arrays;
import java.util.Map;

/**
 * The type Request data.
 */
public class RequestData {
    private Map<String, String[]> requestParameters;
    private Map<String, Object> requestAttributes;
    private String requestPagePath;

    /**
     * Instantiates a new Request data.
     *
     * @param requestParameters the request parameters
     * @param requestAttributes the request attributes
     * @param requestPagePath   the request page path
     */
    public RequestData(Map<String, String[]> requestParameters, Map<String, Object> requestAttributes, String requestPagePath) {
        this.requestParameters = requestParameters;
        this.requestAttributes = requestAttributes;
        this.requestPagePath = requestPagePath;
    }

    /**
     * Gets request parameters.
     *
     * @return the request parameters
     */
    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    /**
     * Sets request parameters.
     *
     * @param requestParameters the request parameters
     */
    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    /**
     * Gets request attributes.
     *
     * @return the request attributes
     */
    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * Sets request attributes.
     *
     * @param requestAttributes the request attributes
     */
    public void setRequestAttributes(Map<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    /**
     * Gets request page path.
     *
     * @return the request page path
     */
    public String getRequestPagePath() {
        return requestPagePath;
    }

    /**
     * Sets request page path.
     *
     * @param requestPagePath the request page path
     */
    public void setRequestPagePath(String requestPagePath) {
        this.requestPagePath = requestPagePath;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestData{");
        sb.append("requestParameters=").append(requestParameters);
        requestParameters.entrySet().forEach(entry -> {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(Arrays.toString(entry.getValue()));
            sb.append(", ");
        });
        sb.append(", requestAttributes=").append(requestAttributes);
        sb.append(", requestPagePath='").append(requestPagePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
