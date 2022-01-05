package by.epam.jwdsc.controller.comand;

public final class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private final String pagePath;
    private final RouterType routerType;

    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }
}
