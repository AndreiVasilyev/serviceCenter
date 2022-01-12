package by.epam.jwdsc.controller.command;

public final class Router {
    public enum RouterType {
        FORWARD, REDIRECT, RESPONSE_BODY
    }

    private String pagePath;
    private final RouterType routerType;
    private String json;

    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public Router(RouterType routerType, String json) {
        this.routerType = routerType;
        this.json = json;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public String getJson() {
        return json;
    }
}
