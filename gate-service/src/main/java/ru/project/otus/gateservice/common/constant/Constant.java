package ru.project.otus.gateservice.common.constant;

public class Constant {
    public static final String PROJECT_NAME = "gateway";
    public static final String LOG_LEVEL = PROJECT_NAME + "logLevel";
    public static final String LOG_STEP = PROJECT_NAME + "logStep";
    public static final String LOG_COMPONENT = PROJECT_NAME + "logComponent";
    public static final String LOG_DIRECTION = PROJECT_NAME + "logDirection";
    public static final String LOG_OPERATION = PROJECT_NAME + "logOperation";

    public static final String TRACE_ID = PROJECT_NAME + "_logId";
    public static final String SPAN_ID = PROJECT_NAME + "_logCorrelationId";

    public static final String CAMEL_CONTEXT_PATH = PROJECT_NAME + "logCorrelationId";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DB_URL = "/api/v1/db/save";
    public static final String URI = "CamelHttpUri";
    public static final String CAMEL_HTTP_METHOD = "CamelHttpMethod";
}
