package cn.microvideo.sdk.qywx.agent;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描并加载应用；
 * @author Aaron.tian
 *
 */
class AgentScanner {
	/**
	 * 当前线程类加载器
	 */
	static final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
	
	/**
	 * 扫描包下所有应用
	 * @param packageName 包名
	 * @return 应用类型集合
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	protected static List<Class<Agent>> getAgents(String packageName) throws IOException{
		Set<Class<?>> classes = getAllClassesInPackage(packageName);
		List<Class<Agent>> agentList = new ArrayList<Class<Agent>>();
		for(Class<?> clazz:classes){
			if(Modifier.isAbstract(clazz.getModifiers())){
				continue;
			}
			List<Class<?>> superClasses = new ArrayList<Class<?>>();
			recursionSuperclassBy(clazz, superClasses);
			for(Class<?> agent:superClasses){
				ATAgent marker = agent.getAnnotation(ATAgent.class);
				if(agent == Agent.class && marker != null){
					agentList.add((Class<Agent>) clazz);
				}
			}
		}
		return agentList;
	}
	
	/**
	 * 获取类的所有接口，包含父类实现的接口
	 * @param clazz
	 * @param classes
	 */
	private static void recursionSuperclassBy(Class<?> clazz,List<Class<?>> classes){
		Class<?> superclass = clazz.getSuperclass();
		if(superclass == null){
			return;
		}else{
			classes.add(superclass);
			recursionSuperclassBy(superclass, classes);
		}
	}
	
	/**
	 * 获取指定包下的所有类
	 * @param packageName 包名
	 * @return 所有类集合
	 * @throws IOException 
	 */
	private static Set<Class<?>> getAllClassesInPackage(String packageName) throws IOException {
		String rootPackage = getRootPackage(packageName);
		String pattern = getClassPathPattern(packageName);
		String rootDir = rootPackage.replace('.', '/');
		Enumeration<URL> packages = currentClassLoader.getResources(rootDir);
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		while (packages.hasMoreElements()) {
			URL packURL = (URL) packages.nextElement();
			String protocol = packURL.getProtocol();
			if("file".equals(protocol)){
				String packagePath = URLDecoder.decode(packURL.getFile(), StandardCharsets.UTF_8.name());
				getAllClassesInClassPath(classes, rootPackage, packagePath, pattern);
			}else{
				getAllClassesInJarFile(classes, packURL, pattern);
			}
		}
		return classes;
	}
	/**
	 * 加载类加载路径下的类
	 * @param classes 查找到的类
	 * @param packageName 包名称
	 * @param packagePath 包路径
	 * @param pattern 匹配正则
	 */
	private static void getAllClassesInClassPath(Set<Class<?>> classes, String packageName, String packagePath, String pattern){
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        if(dirfiles == null){
        	return;
        }
        for (File file : dirfiles) {
            if (file.isFile()) {
            	try {
            		String className = file.getName().substring(0,file.getName().length() - 6);
            		String fullClassName = packageName + '.' + className;
            		if(doMatch(pattern, fullClassName)){
	            		Class<?> clazz = currentClassLoader.loadClass(fullClassName);  
	            		classes.add(clazz);
            		}
            	} catch (ClassNotFoundException e) {
            		//
            	}
            } else {
            	getAllClassesInClassPath(classes, packageName + "."
            			+ file.getName(), file.getAbsolutePath(), pattern);
            }
        }
	}
	
	/**
	 * 加载JAR包中的类文件
	 * @param classes
	 * @param packageURL
	 * @param pattern
	 * @throws IOException 
	 */
	private static void getAllClassesInJarFile(Set<Class<?>> classes, URL packageURL, String pattern) throws IOException{
		JarFile jarFile = null;
		try{
			URLConnection urlConnection = packageURL.openConnection();
			if(!(urlConnection instanceof JarURLConnection)){
				return;
			}
			jarFile = ((JarURLConnection) urlConnection).getJarFile();
	        Enumeration<JarEntry> entries = jarFile.entries();
	        // 同样的进行循环迭代
	        while (entries.hasMoreElements()) {
	            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
	            JarEntry entry = entries.nextElement();
	            String name = entry.getName();
	            // 如果是以/开头的
	            if (name.charAt(0) == '/') {
	                name = name.substring(1);
	            }
	            // 如果前半部分和定义的包名相同
	            if (name.endsWith(".class") && !entry.isDirectory()) {
	                try {
	                	int length = name.length();
	                	String className = name.replace('/', '.').substring(0, length-6);
	                	if(doMatch(pattern, className)){
	                		classes.add(Class.forName(className));
	                	}
	                } catch (Throwable e) {
	                    //
	                }
	            }
	        }
		}finally{
			if(jarFile != null){
				jarFile.close();
			}
		}
	}
	/**
	 * 获取根目录
	 * @param location 给定位置
	 * @return
	 */
	private static String getRootPackage(String location){
		int indexStar = location.indexOf("*");
		if(indexStar > -1){
			return location.substring(0, indexStar - 1);
		}else{
			return location;
		}
	}
	/**
	 * 获取类路径的匹配正则
	 * @param location
	 * @return
	 */
	private static String getClassPathPattern(String location) {
		if(location.contains("*")){
			return location.replaceAll("\\.", "\\\\.").replaceAll("\\*\\*", ".*")
					.replaceAll("\\\\.\\*", "\\\\.\\\\w*");
		}else{
			return location;
		}
	}
	/**
	 * 判断字符串是否匹配指定正则
	 * @param pattern
	 * @param path
	 * @return
	 */
	private static boolean doMatch(String pattern, String path){
		return path.matches(pattern + ".*");
	}

}
