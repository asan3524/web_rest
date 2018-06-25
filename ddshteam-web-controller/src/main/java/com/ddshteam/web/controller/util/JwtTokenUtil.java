package com.ddshteam.web.controller.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JwtTokenUtil
 * @ClassName: JwtTokenUtil
 * @author arpgate
 * @date 2018年6月25日 下午10:56:19
 * @version v1.0.0
 * 
 */
public class JwtTokenUtil {

	private final static String secret="ddshteam@ddshteam.com.cn";
	public final static String HTTP_HEADER_KEY="JTOKEN";
	
	public static String getToken(String userid)
	{
		Map<String, Object> headerClaims = new HashMap();
		headerClaims.put("userid", userid);
		String token=null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    token = JWT.create()
		        .withHeader(headerClaims)
		        .sign(algorithm);
		} catch (UnsupportedEncodingException exception){
			return null;
		} catch (JWTCreationException exception){
			return null;
		}
		return token;
	}
	
	
	public static String  verifyToken(String token)
	{
		String userid=null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    Claim useridclain=jwt.getHeaderClaim("userid");
		    if(useridclain!=null)
		    {
		    	userid=useridclain.asString();
		    }
		} catch (UnsupportedEncodingException exception){
			return null;
		} catch (JWTVerificationException exception){
			return null;
		}
		
		return userid;
	}
}
