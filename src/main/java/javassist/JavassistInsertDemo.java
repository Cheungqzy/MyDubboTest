package javassist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Cheungqzy on 2018/5/4.
 */
public class JavassistInsertDemo {

    private static Logger logger = LoggerFactory.getLogger(JavassistInsertDemo.class);

    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException, InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("javassist.Calculator");
        String targetName = "getSum";
        CtMethod mold = ctClass.getDeclaredMethod(targetName); // 需要修改的方法名称
        String newName = targetName + "$impl"; // 修改原有的方法名称
        mold.setName(newName);
        CtMethod mNew = CtNewMethod.copy(mold, targetName, ctClass, null); //创建新的方法，复制原来的方法
        StringBuffer body = new StringBuffer(); // 主要的注入代码
        body.append("{\nlong start = System.currentTimeMillis();\n");
        body.append(newName + "($$);\n"); // 调用原有代码，类似于method();($$)表示所有的参数
        body.append("System.out.println(\"Call to method " + targetName + " took \" +\n (System.currentTimeMillis()-start) + " + "\" ms.\");\n");
        body.append("logger.info(\"hello!\");\n");
        body.append("}");
        mNew.setBody(body.toString()); // 替换新方法
        ctClass.addMethod(mNew);  // 增加新方法

        CtField ctField = new CtField(pool.get("org.slf4j.Logger"), "logger", ctClass);
        ctClass.addField(ctField);
        Calculator calculator =(Calculator)ctClass.toClass().newInstance();
        calculator.getSum(10000);
    }

}

class Calculator {

    public void getSum(long n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        System.out.println("n=" + n + ",sum=" + sum);
    }
}