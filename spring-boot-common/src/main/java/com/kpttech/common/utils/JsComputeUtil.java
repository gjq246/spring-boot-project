package com.kpttech.common.utils;
/**
 * js 公式计算类
 */
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsComputeUtil {
	/**
	 * 自定义计算公式
	 * @param data 传入的值对
	 * @param formula 传入的js公式代码
	 * @return
	 */
	public static Double compute(String data,String formula)
	{
		Double output =0.0;
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String strData="var data="+data+";";
		//用json串传入数据，属性名不能中文
		//String data="var data="+"{\"projectcategory\":\"实践教学类\",\"projectname\":\"实验室建设\",\"projectrank\":\"一级\",\"projectstate\":\"验收合格\",\"projectfunds\":10000}"+";";
		//String data="var data="+"{\"projectcategory\":\"实践教学类\",\"projectname\":\"实验室建设\",\"projectrank\":\"二级\",\"projectstate\":\"验收合格\"}"+";";
		//engine.put("data", "{\"类别\":\"实践教学类\",\"项目名称\":\"实验室建设\",\"级别\":\"二级\",\"状态\":\"立项\"}");//传入json值
		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		try {
			
			engine.eval(strData+formula);

			output = (Double) engine.get("output");

			System.out.println("output = " + output);

		} catch (ScriptException e) {
			e.printStackTrace();

		}
		return output;
	}
	/**
	 * 跟得分和个人参与参数，应用计算公式得到最后得分
	 * @param score 项目得分
	 * @param number 总人数
	 * @param data 个人参与参数
	 * @param formula 计算公式
	 * @param allocate 分配比例
	 * @return
	 */
	public static Double computeScale(String score,String number,String data,String formula,String allocate)
	{
		Double output =0.0;
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String strScore="var score="+score+";";
		String strNumber="var number="+number+";";
		String strData="var data="+data+";";
		String strAllocate="var allocate="+allocate+";";
		//用json串传入数据，属性名不能中文
		//String data="var data="+"{\"projectcategory\":\"实践教学类\",\"projectname\":\"实验室建设\",\"projectrank\":\"一级\",\"projectstate\":\"验收合格\",\"projectfunds\":10000}"+";";
		//String data="var data="+"{\"projectcategory\":\"实践教学类\",\"projectname\":\"实验室建设\",\"projectrank\":\"二级\",\"projectstate\":\"验收合格\"}"+";";
		//engine.put("data", "{\"类别\":\"实践教学类\",\"项目名称\":\"实验室建设\",\"级别\":\"二级\",\"状态\":\"立项\"}");//传入json值
		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		try {
			
			engine.eval(strScore+strNumber+strData+strAllocate+formula);

			output = (Double) engine.get("output");

			System.out.println("output = " + output);

		} catch (ScriptException e) {
			e.printStackTrace();

		}
		return output;
	}

}
