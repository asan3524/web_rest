package com.ddshteam.web.core.util;

import java.util.Map;

import com.ddshteam.web.core.support.security.BASE64Encoder;
import com.ddshteam.web.core.support.security.coder.DESCoder;
import com.ddshteam.web.core.support.security.coder.HmacCoder;
import com.ddshteam.web.core.support.security.coder.MDCoder;
import com.ddshteam.web.core.support.security.coder.RSACoder;
import com.ddshteam.web.core.support.security.coder.SHACoder;



/**
 * 数据加密辅助类(默认编码UTF-8)
 * 
 */
public final class SecurityUtil {
	private SecurityUtil() {
	}

	/**
	 * 默认算法密钥
	 */
	private static final byte[] ENCRYPT_KEY = { -81, 0, 105, 7, -32, 26, -49, 88 };

	public static final String CHARSET = "UTF-8";

	/**
	 * BASE64解码
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static final byte[] decryptBASE64(String key) {
		try {
			return new BASE64Encoder().decode(key);
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * BASE64编码
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static final String encryptBASE64(byte[] key) {
		try {
			return new BASE64Encoder().encode(key);
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static final String decryptDes(String cryptData) {
		return decryptDes(cryptData, ENCRYPT_KEY);
	}

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @return 加密后的数据
	 */
	public static final String encryptDes(String data) {
		return encryptDes(data, ENCRYPT_KEY);
	}

	/**
	 * 基于MD5算法的单向加密
	 * 
	 * @param strSrc
	 *            明文
	 * @return 返回密文
	 */
	public static final String encryptMd5(String strSrc) {
		String outString = null;
		try {
			outString = encryptBASE64(MDCoder.encodeMD5(strSrc.getBytes(CHARSET)));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return outString;
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static final String encryptSHA(String data) {
		try {
			return encryptBASE64(SHACoder.encodeSHA256(data.getBytes(CHARSET)));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static final String encryptHMAC(String data) {
		return encryptHMAC(data, ENCRYPT_KEY);
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static final String decryptDes(String cryptData, byte[] key) {
		String decryptedData = null;
		try {
			// 把字符串解码为字节数组，并解密
			decryptedData = new String(DESCoder.decrypt(decryptBASE64(cryptData), key));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @return 加密后的数据
	 */
	public static final String encryptDes(String data, byte[] key) {
		String encryptedData = null;
		try {
			// 加密，并把字节数组编码成字符串
			encryptedData = encryptBASE64(DESCoder.encrypt(data.getBytes(), key));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static final String encryptHMAC(String data, byte[] key) {
		try {
			return encryptBASE64(HmacCoder.encodeHmacSHA512(data.getBytes(CHARSET), key));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * RSA签名
	 * 
	 * @param data
	 *            原数据
	 * @return
	 */
	public static final String signRSA(String data, String privateKey) {
		try {
			return encryptBASE64(RSACoder.sign(data.getBytes(CHARSET), decryptBASE64(privateKey)));
		} catch (Exception e) {
			throw new RuntimeException("签名错误，错误信息：", e);
		}
	}

	/**
	 * RSA验签
	 * 
	 * @param data
	 *            原数据
	 * @return
	 */
	public static final boolean verifyRSA(String data, String publicKey, String sign) {
		try {
			return RSACoder.verify(data.getBytes(CHARSET), decryptBASE64(publicKey), decryptBASE64(sign));
		} catch (Exception e) {
			throw new RuntimeException("验签错误，错误信息：", e);
		}
	}

	/**
	 * 数据加密，算法（RSA）
	 * 
	 * @param data
	 *            数据
	 * @return 加密后的数据
	 */
	public static final String encryptRSAPrivate(String data, String privateKey) {
		try {
			return encryptBASE64(RSACoder.encryptByPrivateKey(data.getBytes(CHARSET), decryptBASE64(privateKey)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * 数据解密，算法（RSA）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static final String decryptRSAPublic(String cryptData, String publicKey) {
		try {
			// 把字符串解码为字节数组，并解密
			return new String(RSACoder.decryptByPublicKey(decryptBASE64(cryptData), decryptBASE64(publicKey)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	public static String encryptPassword(String password) {
		return encryptMd5(SecurityUtil.encryptSHA(password));
	}

	public static void main(String[] args) throws Exception {
		String encryptStr = "杜宇";
		System.out.println(encryptDes(encryptStr));
		System.out.println(decryptDes("5s592mTdeEe="));
		System.out.println(encryptMd5(encryptStr));
		System.out.println(encryptSHA(encryptStr));
		Map<String, Object> key = RSACoder.initKey(); //Server初始化秘钥对
		String privateKey = encryptBASE64(RSACoder.getPrivateKey(key)); //私钥
		String publicKey = encryptBASE64(RSACoder.getPublicKey(key));   //公钥
		System.out.println(privateKey);
		System.out.println(publicKey);
		String sign = signRSA("132", privateKey); //Client使用私钥生成原始数据RSA签名(摘要签名算法一般使用hash算法)
		System.out.println(sign);
		String encrypt = encryptRSAPrivate("132", privateKey); //用户使用私钥(公钥)加密原始数据
		System.out.println(encrypt);
		String org = decryptRSAPublic(encrypt, publicKey); //服务端使用公钥(私钥)解密还原为原始数据
		System.out.println(org);
		System.out.println(verifyRSA(org, publicKey, sign));//服务端验证(还原的原始数据+公钥+签名)

		/**
		 * @author duyu
		 * 1.第三方服务平台OAuth2.0: 
		 *   客户端要求用户给予第三方服务的授权(一般使用第三方登陆,或使用第三方服务的开放API)
		 *   用户同意授权(一般是先扫码/手动登陆第三方服务)
		 *   客户端通过获得的第三方授权,向第三方服务申请令牌token
		 *   客户端使用token,向第三方服务获取资源(API调用权限,头像,用户账号有限信息等等...)
		 *   第三方服务验证token无误,同意开放资源
		 *   流程: 用户访问Client-->Client导向第三方认证服务页面-->用户输入正确账号密码同意授权-->第三方服务重定向到Client事先指定的
		 *         回调URL,同时附上授权码-->Client收到授权码,再去向第三方服务申请token(用户不可见)-->第三方服务验证授权码和重定向URL,
		 *         确认无误后,向Client发送访问令牌(access_token)和更新令牌(refresh_token)
		 * 2.第三方服务授予用户资源访问权限(server/client)
		 *   Server端生成public_key/private_key
		 *   提供给用户public_key用于客户端消息加密(public_key是公开的)
		 *   Server端使用private_key进行解密(private_key只有服务端持有)
		 * 3.HTTPS
		 *   对称/非对称/散列 三者都有用到,具体参考资料
		 */
		
		// System.out.println("-------列出加密服务提供者-----");
		// Provider[] pro = Security.getProviders();
		// for (Provider p : pro) {
		// System.out.println("Provider:" + p.getName() + " - version:" +
		// p.getVersion());
		// System.out.println(p.getInfo());
		// }
		// System.out.println("");
		// System.out.println("-------列出系统支持的消息摘要算法：");
		// for (String s : Security.getAlgorithms("MessageDigest")) {
		// System.out.println(s);
		// }
		// System.out.println("-------列出系统支持的生成公钥和私钥对的算法：");
		// for (String s : Security.getAlgorithms("KeyPairGenerator")) {
		// System.out.println(s);
		// }
	}
}
