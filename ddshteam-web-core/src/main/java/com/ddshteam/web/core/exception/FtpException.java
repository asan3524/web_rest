package com.ddshteam.web.core.exception;

import com.ddshteam.web.core.support.HttpCode;

/**
 * FTP异常
 * 
 */
@SuppressWarnings("serial")
public class FtpException extends BaseException {
    public FtpException() {
    }

    public FtpException(String message) {
        super(message);
    }

    public FtpException(String message, Throwable throwable) {
        super(message, throwable);
    }

    protected HttpCode getHttpCode() {
        return HttpCode.INTERNAL_SERVER_ERROR;
    }
}
