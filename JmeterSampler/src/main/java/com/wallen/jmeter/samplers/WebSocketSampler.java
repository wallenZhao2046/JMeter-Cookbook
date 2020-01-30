package com.wallen.jmeter.samplers;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class WebSocketSampler extends AbstractJavaSamplerClient {


    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("hello world");
        return null;
    }
}
