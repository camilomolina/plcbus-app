package cl.bennu.plcbus.common.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class ResourcesFinder {

    private ResourcesFinder() {
    }

    public static ClassLoader getDefaultClassLoader() {
        return defaultClassLoader;
    }

    public static void setDefaultClassLoader(ClassLoader p_defaultClassLoader) {
        defaultClassLoader = p_defaultClassLoader;
    }

    public static URL getResourceURL(String resource)
            throws IOException {
        return getResourceURL(getClassLoader(), resource);
    }

    public static URL getResourceURL(ClassLoader loader, String resource)
            throws IOException {
        URL url = null;
        if (loader != null)
            url = loader.getResource(resource);
        if (url == null)
            url = ClassLoader.getSystemResource(resource);
        if (url == null)
            throw new IOException("Could not find resource " + resource);
        else
            return url;
    }

    public static InputStream getResourceAsStream(String resource) throws IOException {
        return getResourceAsStream(getClassLoader(), resource);
    }

    public static InputStream getResourceAsStream(ClassLoader loader, String resource)
            throws IOException {
        InputStream in = null;
        if (loader != null)
            in = loader.getResourceAsStream(resource);
        if (in == null)
            in = ClassLoader.getSystemResourceAsStream(resource);
        if (in == null)
            throw new IOException("Could not find resource " + resource);
        else
            return in;
    }

    public static Properties getResourceAsProperties(String resource) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        in = getResourceAsStream(resource);
        props.load(in);
        in.close();
        return props;
    }

    public static Properties getResourceAsProperties(ClassLoader loader, String resource) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        in = getResourceAsStream(loader, resource);
        props.load(in);
        in.close();
        return props;
    }

    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    public static Reader getResourceAsReader(ClassLoader loader, String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(loader, resource));
    }

    public static File getResourceAsFile(String resource) throws IOException {
        return new File(getResourceURL(resource).getFile());
    }

    public static File getResourceAsFile(ClassLoader loader, String resource) throws IOException {
        return new File(getResourceURL(loader, resource).getFile());
    }

    public static InputStream getUrlAsStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }

    public static Reader getUrlAsReader(String urlString) throws IOException {
        return new InputStreamReader(getUrlAsStream(urlString));
    }

    public static Properties getUrlAsProperties(String urlString) throws IOException {
        Properties props = new Properties();
        InputStream in = getUrlAsStream(urlString);
        props.load(in);
        in.close();
        return props;
    }

    public static Class classForName(String className) throws ClassNotFoundException {
        Class clazz = null;
        try {
            clazz = getClassLoader().loadClass(className);
        } catch (Exception e) {
        }
        if (clazz == null)
            clazz = Class.forName(className);
        return clazz;
    }

    public static Object instantiate(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return instantiate(classForName(className));
    }

    public static Object instantiate(Class clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    private static ClassLoader getClassLoader() {
        if (defaultClassLoader != null) {
            return defaultClassLoader;
        } else {
            return Thread.currentThread().getContextClassLoader();
        }
    }

    private static ClassLoader defaultClassLoader;

    public static byte[] getResourceAsByteArray(String file, int p_minSize) throws IOException {
        InputStream inputStream = getResourceAsStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] arrayLine = new byte[p_minSize];
        bufferedInputStream.read(arrayLine);
        return arrayLine;
    }

    public static String getResourceAsString(String file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(getResourceAsReader(file));
        StringBuilder t_buf = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            t_buf.append(line + "\n");
        }
        return t_buf.toString();
    }
}

