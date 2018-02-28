package com.ddshteam.web.controller.util;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.exception.BusinessException;
import com.ddshteam.web.core.exception.DataParseException;
import com.ddshteam.web.core.exception.FtpException;
import com.ddshteam.web.core.exception.IllegalParameterException;
import com.ddshteam.web.core.exception.InstanceException;
import com.ddshteam.web.core.exception.LoginException;
import com.ddshteam.web.core.support.HttpCode;

@RestController
@ControllerAdvice
public class ExceptionHandleController extends BaseController implements ErrorController{

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class);

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Object handler(Exception e){
    	
        if( e instanceof BusinessException){
            
            logger.error("[业务逻辑异常] {}", e.getLocalizedMessage());

            return getResponse(HttpCode.CONFLICT, false, "业务逻辑异常");

        }else if(e instanceof IllegalParameterException){

            logger.warn("[请求参数出错异常] {}", e.getLocalizedMessage());
            return getResponse(HttpCode.BAD_REQUEST, false, "请求参数出错");
            
        }else if(e instanceof LoginException){

            logger.warn("[登陆失败异常] {}", e.getLocalizedMessage());
            return getResponse(HttpCode.LOGIN_FAIL, false, "登陆失败");
        	
        }else if(e instanceof DataParseException || e instanceof InstanceException || e instanceof FtpException){

            logger.error("[服务器异常] {}", e.getLocalizedMessage());
            return getResponse(HttpCode.INTERNAL_SERVER_ERROR, false, "服务器异常");
        }else if(e instanceof UnauthorizedException || e instanceof AuthorizationException) {
        	
        	return getResponse(HttpCode.FORBIDDEN, false, "没有权限");
        }else if(e instanceof UnauthenticatedException) {
        	
        	return getResponse(HttpCode.FORBIDDEN, false, "请登录");
        }else {
            logger.error("[系统异常] {}",e);
            return getResponse(HttpCode.INTERNAL_SERVER_ERROR, false, "系统异常");
        }
    }
    
	
	@Override
	public String getErrorPath() {
		return null;
	}
	
	@RequestMapping(value = {"/error"})
	@ResponseBody
    public Object error() {
		logger.debug("api not found.");
		return getResponse(HttpCode.NOT_FOUND, false, "api not found.");
    }

}
