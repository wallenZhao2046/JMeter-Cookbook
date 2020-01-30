package com.wallen.jmeter.samplers;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebSocketSamplerImpl implements JavaSamplerClient {

    public static String URLNAME = "url";
    public static String DEFAULT_URL = "http://www.baidu.com";
    // 用来存储响应结果, 目的是将响应结果显示到结果树种便于查看和debug
    private String resultData = "";

    /**
     * 这个方法决定了在jmeter当中要显示哪些属性 在引入这个sampler的时候就会运行
     *
     * @return
     */
    public Arguments getDefaultParameters() {
        System.out.println("getDefaultParameters method run");
        Arguments arguments = new Arguments();
        arguments.addArgument(URLNAME, DEFAULT_URL);
        return arguments;
    }

    /**
     * 初始化方法
     *
     * @param javaSamplerContext
     */
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        String url = javaSamplerContext.getParameter(URLNAME, "defaultURL: www.baidu.com");

        System.out.println("用户输入的URL: " + url);

    }

    /**
     * 这个方法是具体的功能逻辑
     *
     * @param javaSamplerContext
     * @return
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        SampleResult result = new SampleResult();
        String inputUrl = javaSamplerContext.getParameter(URLNAME, "http://www.baidu.com");
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(inputUrl);

            result.sampleStart();
            URLConnection conn = url.openConnection();

            byte[] buffer = new byte[1024];
            InputStream in = conn.getInputStream();
            int len = 0;
            while( (len = in.read(buffer)) >= 0 ){
                resultData = new String(buffer, "UTF-8");
                sb.append(resultData);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultData = sb.toString();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.sampleEnd();
        result.setSuccessful(true);
        result.setResponseData(resultData, null);
        result.setDataType(SampleResult.TEXT);
        return result;
    }

    /**
     * 收尾工作的方法
     *
     * @param javaSamplerContext
     */
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }

}
