package com.naixwf.core.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 静态类，获取java类信息的方法工具
 */
public class JavaClassUtil {
    /**
     * 指定包名称获取所有的类
     *
     * @param packageName
     * @return
     */
    public static List<Class<?>> getClassNameByPackageName(String packageName) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            assert classLoader != null;
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<String> dirs = new ArrayList<String>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(resource.getFile());
            }
            TreeSet<String> classes = new TreeSet<String>();
            for (String directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
            ArrayList<Class<?>> classList = new ArrayList<Class<?>>();
            for (String clazz : classes) {
                classList.add(Class.forName(clazz));
            }
            return classList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs. Adapted from http://snippets.dzone.com/posts/show/4831 and
     * extended to support use of JAR files
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static TreeSet<String> findClasses(String directory, String packageName)
            throws Exception {
        TreeSet<String> classes = new TreeSet<String>();
        if (directory.startsWith("file:") && directory.contains("!")) {
            String[] split = directory.split("!");
            URL jar = new URL(split[0]);
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replaceAll("[$].*", "")
                            .replaceAll("[.]class", "").replace('/', '.');
                    classes.add(className);
                }
            }
        }
        File dir = new File(directory);
        if (!dir.exists()) {
            return classes;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file.getAbsolutePath(),
                        packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(packageName + '.'
                        + file.getName().substring(0, file.getName().length() - 6));
            }
        }
        return classes;
    }

    /**
     * 转化get属性方法名为属性名
     *
     * @param methodName
     * @return
     */
    public static String methodGetNameToProperName(String methodName) {
        return methodNameToProperName(methodName, "get");
    }

    public static String methodNameToProperName(String methodName, String prifix) {
        if ((methodName.startsWith(prifix)) && methodName.length() > 3) {
            String ret = methodName.substring(3, 4).toLowerCase();
            if (methodName.length() > 4)
                ret += methodName.substring(4);

            return ret;
        } else {
            return methodName;
        }
    }

}
