package cn.kgc.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  ksfxhw3818@sandbox.com   111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000117656644";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDfW3wlAgeiOwmkaVNdxFa4Tg3NaCeS+oizv++1gSIwn6bqKsGLmJwiRWFEnG8s1WkLt5nt4qJwo2IbDZyWfoGOzrrzO1MpVQy0Zct9XtPn4DYS1SXBMLKUI1TYDgkIDY7rpzzFuroFoddwj3ONZKppXz/+sZC5yE6CxElmmdKk15GvOvvljf3YUXpTV4DlCoIjV7h3AkyjHOS6jrzEpliG3h6UI6XtbnLHC0AOyJxiPV1ZgXKiz4FlkPBruOT3PZGJbA2NmSTih7H7bftB1y8kfU31GDlbYhlAZi04dy4HYSZ+qsN5g47qtvFyR4/NIYfNFb4vpYKuf+wdsS9u+j3nAgMBAAECggEActU4KFa40gGEXZEBGW4+H5eRpQ9teTVxXujJNwnjKBiM2kuNIlYdv8jnzzyWM/isfyev7gvc9AgsAjinTwBPX7qNh0vy34codv729cfxLn+pjVkkhgSha2QW1NWycv91gVaD+iycQxUIDIjUIdelfXNJFYr5G2sOYlDEZAmlsLVlp3e5RSUyNEQSUI9JvqePh8JsBxYSNT/4IH6llsQCXac1CrB7hVQVCk32WpHrjPmRJOokRjHrJJKXSyO2WZL9ZOOisNZHgXy3jlPn8JXJBhoIG29blb3QELIEqhUwk2M1pv9qE+Y2PV9O8B5fLeVqOiybPgyf2YWuuaxQZ9KQMQKBgQD8SyzqPS5TerusH8BLiiFsh1bogmQ8zWKgl7r7yRFCaA/5Zr+NlRFCJCSQB+AVUAVR+Dhxz8tVT4Vr7WRAjOZLMvyntzY585gi1iQbs95ah8o2XFNXNz3eYdzZn9lTWmOt9wN6xoRiHuQybZvhU6GNO7TMcyIjlfss3ErgRRkvuwKBgQDio3xtzBCIJjH3UOQI9D08dFfS6D9aZ556aUC4ghD7i7r+VpMbz6rlu/8h3kXy+8BZEkYH8s3SmHiAVPnOksgIqODpeSOQkSWd+9qzST6UyqC4GLhPEelRcjD4FmwF23HUqL3HzN9mY/7H1gaCyQdyTAjvlrzq/DZ1IuTrEbnZxQKBgQDqwDhGrC7S1jyR5+lRYHHBPh0Ls2f5e1HuA8CkT26Tz6gtdAGAh9fLJz4uFmXW7uJEZyZ+H88eziNjTTwfI/uUtRiKJdt7CMHiiEaI0FBxBDCPbFDPskMLDRwmEqpw+IYPdOvz7Yt4kS3cDkkg3GRRueB87BQz8HYKQ+AX1bOdyQKBgHz1Mte+2aaXmJkJqpEdSN4aL4oDr1qDIyfO/HY+kGqA4jr1NOrylwFPpB3iVaLhF26E2sPrJXxlwJrRP2KcH1pW8psHxYoh3SG8CRss2SpkhjS6vHQsJf5X/ZPrmOep1Jsx5mFRHiNwIru/z5swh8CHF64x6DuPj98JvDm3bkcZAoGBAKFSWWG+atXCVzxZIyMHnXdQ+C55/eDru/JB5r+K0FO+NLwR5kQAvR4+OEa/xNv++UCn7IY64kE1M31gGg5qZdw0qtk4QCybwO9XlEOHqKdZzxd7dYzpJc0rmEMNLetABcTailVyUa2QJKcD59+Imu5s/9pdkylrFaC0tMAMjSNZ";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA31t8JQIHojsJpGlTXcRWuE4NzWgnkvqIs7/vtYEiMJ+m6irBi5icIkVhRJxvLNVpC7eZ7eKicKNiGw2cln6Bjs668ztTKVUMtGXLfV7T5+A2EtUlwTCylCNU2A4JCA2O66c8xbq6BaHXcI9zjWSqaV8//rGQuchOgsRJZpnSpNeRrzr75Y392FF6U1eA5QqCI1e4dwJMoxzkuo68xKZYht4elCOl7W5yxwtADsicYj1dWYFyos+BZZDwa7jk9z2RiWwNjZkk4oex+237QdcvJH1N9Rg5W2IZQGYtOHcuB2EmfqrDeYOO6rbxckePzSGHzRW+L6WCrn/sHbEvbvo95wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//回调路径，支付完成后回到自己的项目中
    public static String return_url = "http://localhost/hotel-day01-1.0-SNAPSHOT/orders/afterOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

