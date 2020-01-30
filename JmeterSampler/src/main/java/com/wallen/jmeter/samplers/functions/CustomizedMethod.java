package com.wallen.jmeter.samplers.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomizedMethod extends AbstractFunction {

    private Object[] params;

    /**
     * 执行方法
     *
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        System.out.println("execute functions");

        CompoundVariable param1 = (CompoundVariable)params[0];
        CompoundVariable param2 = (CompoundVariable)params[1];


        int value1 = new Integer(param1.execute().trim());
        int value2 = new Integer(param2.execute().trim());

        return "" + (value1 + value2);
    }

    /**
     * 接收用户传递的参数
     *
     * @param collection
     * @throws InvalidVariableException
     */
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {

        // check parameter count should be 2
        checkParameterCount(collection, 2);

        params = collection.toArray();


        System.out.println("setParameters run");
    }

    /**
     * function 名称
     *
     * @return
     */
    public String getReferenceKey() {
        System.out.println("getReferenceKey run");
        return "__myPlusDemo";
    }

    /**
     * 参数描述
     *
     * @return
     */
    public List<String> getArgumentDesc() {
        System.out.println("getArgumentDesc run");
        List<String> argDesc = new ArrayList<String>();
        argDesc.add("被加数(整数)");
        argDesc.add("加数(整数)");
        return argDesc;
    }
}
