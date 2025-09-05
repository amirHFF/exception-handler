package io.github.amirHFF.errorCode;

import io.github.amirHFF.exceptions.ManamanRuntimeException;

public class ErrorCodeBuilder {
    public static String moduleName;
    private String exceptionTypeId;
    private String exceptionCount;

    public static String build(String code) {
        if (moduleName == null || moduleName.isEmpty()) {
            throw new ManamanRuntimeException("error occurred in definition of errorCode wrong module name", "???1");
        }
        if (code.length() == 4) {
            return moduleName + "-" + code;
        }
        if (code.length() < 4) {
            throw new ManamanRuntimeException("error occurred in definition of errorCode", "???0");
        }

        return code;

    }
}
