package org.leo;

import java.text.MessageFormat;

public class Test {

	public static void main(String[] args) {
		//在MessageFormat.format方法中组装jason数据字符串：{code:"w1",des:"w2"}，起止分别有左大括号和右大括号。方法是将单引号把大括号包含起来。如下：
        String responseTemplate = "'{'code:\"{0}\",des:\"{1}\"'}'";
        System.out.println(MessageFormat.format(responseTemplate, "w1","w2"));

        //如果格式化字符串中包含单引号，处理方法是用2个单引号进行转义：

        responseTemplate = "'{'code:''{0}'',des:''{1}'''}'";
        System.out.println(MessageFormat.format(responseTemplate, new Object[]{"w1","w2"}));
        System.out.println(MessageFormat.format(responseTemplate, new Object[]{"w1"}));
        System.out.println(MessageFormat.format(responseTemplate, new Object[]{"w1","w3","wxx"}));
        
        


	}

}
