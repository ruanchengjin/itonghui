package com.fbw.service.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;

/**
 * 
 * <功能详细描述> HTTP请求工具类
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpRequestUtil
{
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     * @throws InnerException
     */
    public static String sendGet(String url, String param) throws InnerException
    {
        String result = "";
        BufferedReader in = null;
        try
        {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet())
            {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            // 发送GET请求异常
            throw new InnerException(ErrorMsgConstant.RECHARGE_SEND_GET_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_SEND_GET_ERROR));
        }
        // 使用finally块来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
                // 发送GET请求异常
                throw new InnerException(ErrorMsgConstant.RECHARGE_SEND_GET_ERROR,
                        InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_SEND_GET_ERROR));
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws InnerException
     */
    public static String sendPost(String url, String param) throws InnerException
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            // 发送POST请求异常
            throw new InnerException(ErrorMsgConstant.RECHARGE_SEND_POST_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_SEND_POST_ERROR));
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                // 发送POST请求异常
                throw new InnerException(ErrorMsgConstant.RECHARGE_SEND_POST_ERROR,
                        InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_SEND_POST_ERROR));
            }
        }
        return result;
    }

    /**
     * 
     * <功能详细描述> inputStream转换为String
     * @param is
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public static String inputStreamToString(InputStream is) throws InnerException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        try
        {
            while ((i = is.read()) != -1)
            {
                baos.write(i);
            }
        }
        catch (IOException e)
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_WECHAT_GET_RETURN_MSG_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_WECHAT_GET_RETURN_MSG_ERROR));
        }
        return baos.toString();
    }

    /**
     * 
     * <功能详细描述> 商家通知微信支付结果
     * @param notifyXml
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public static void notifyWeChatPayResult(String notifyXml, HttpServletResponse response) throws InnerException
    {
        System.out.println("response：" + notifyXml);
        BufferedOutputStream out;
        try
        {
            out = new BufferedOutputStream(response.getOutputStream());
            out.write(notifyXml.getBytes());
            out.flush();
            out.close();
        }
        catch (IOException e)
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_WECHAT_NOTIFY_PAY_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_WECHAT_NOTIFY_PAY_ERROR));
        }

    }

}
