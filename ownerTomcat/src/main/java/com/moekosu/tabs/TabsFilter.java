package com.moekosu.tabs;

import com.moekosu.controller.TestController1;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author chenxu
 * @date 2018/06
 */
public class TabsFilter {

    private static Map<String, Map<String, Object>> requestMap;

    private static List<Class<?>> getClass(String package_) throws Exception
    {
        String projectDir = System.getProperty("user.dir")
                + System.getProperty("file.separator") + "src"
                + System.getProperty("file.separator") + "main"
                + System.getProperty("file.separator") + "java"
                + System.getProperty("file.separator");
        String[] packages = package_.split(",");
        String path = "", fileAllName = "", fileName = "", fileSuppix = "";
        String[] fileNames;
        List<Class<?>> classList = new LinkedList<>();
        // 遍历包
        for(String p : packages) {
            // 替换.
            String pp = p.replaceAll("\\.", "\\\\");
            // 包文件夹绝对路径
            path = projectDir + pp;
            //
            File parent = new File(projectDir);
            if(!parent.exists() || !parent.isDirectory()) {
                continue;
            }
            // 获取包文件夹
            File file = new File(path);
            if(file.exists() && file.isDirectory()) {
                // 包文件夹下所有文件
                File[] child = file.listFiles();
                for(File f : child) {
                    if(f.exists() && f.isFile()) {
                        fileAllName = f.getName();
                        fileNames = fileAllName.split("\\.");
                        fileName = fileNames[0];
                        fileSuppix = fileNames[1];
                        if("java".equalsIgnoreCase(fileSuppix)) {
                            // Class.forName() 会触发static方法
//                            classList.add(Class.forName(fileName));
                            classList.add(Thread.currentThread().getContextClassLoader().loadClass(p+"."+fileName));
                        }
                    }
                }
            }
        }
        return classList;
    }

    public static void init(String packages) throws Exception
    {
        List<Class<?>> list = getClass(packages);

        for(Class<?> clazz : list) {
            Method[] methods = clazz.getDeclaredMethods();

            for(Method m : methods) {
                TestPath path = m.getAnnotation(TestPath.class);
                String value = path.value();
                // 挂一个map，里面装class跟要反射的method
                Map<String, Object> rm = new HashMap<>();
                rm.put("class", clazz);
                rm.put("method", m);
                requestMap.put(value, rm);
            }
        }
    }

    public static Map<String, Object> getMethod(String path)
    {
        return requestMap.get(path);
    }

    public static void main(String[] args) throws Exception {
        TabsFilter.init("com.moekosu.controller");
    }


}
