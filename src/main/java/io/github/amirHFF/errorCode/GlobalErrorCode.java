package io.github.amirHFF.errorCode;

public enum GlobalErrorCode implements ErrorCode {
    BAD_INPUT_PARAMETER_VALIDATION("bad input , null parameter received", ErrorCodeBuilder.build("9996")),
    API_RESOURCE_ACCESS_EXCEPTION("api resource is out of access", ErrorCodeBuilder.build("9995")),
    REST_SERVICE_RESPONSE_MISMATCH("rest service response mismatch", ErrorCodeBuilder.build("9994")),
    DATASOURCE_ACCESS_EXCEPTION("datasource is out of access", ErrorCodeBuilder.build("9993"));

    private String message;
    private String code;

    GlobalErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
