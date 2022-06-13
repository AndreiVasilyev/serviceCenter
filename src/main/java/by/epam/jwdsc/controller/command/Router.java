package by.epam.jwdsc.controller.command;

/**
 * The type Router.
 */
public final class Router {
    /**
     * The enum Router type.
     */
    public enum RouterType {
        /**
         * Forward router type.
         */
        FORWARD,
        /**
         * Redirect router type.
         */
        REDIRECT,
        /**
         * Json router type.
         */
        JSON
    }

    private String pagePath;
    private final RouterType routerType;
    private String json;

    /**
     * Instantiates a new Router.
     *
     * @param pagePath   the page path
     * @param routerType the router type
     */
    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    /**
     * Instantiates a new Router.
     *
     * @param routerType the router type
     * @param json       the json
     */
    public Router(RouterType routerType, String json) {
        this.routerType = routerType ;
        this.json = json;
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Gets router type.
     *
     * @return the router type
     */
    public RouterType getRouterType() {
        return routerType;
    }

    /**
     * Gets json.
     *
     * @return the json
     */
    public String getJson() {
        return json;
    }
}
