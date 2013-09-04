
package net.tribe7.opengl.util;

import static java.lang.String.format;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import jogamp.common.os.PlatformPropsImpl;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jogamp.common.jvm.JNILibLoaderBase.LoaderAction;

public class GLBootstrap implements LoaderAction {

    public static final String JAVA_TMP_DIR = "java.io.tmpdir";
    public static final String JAVA_CLASSPATH = "java.class.path";
    public static final String JAVA_SEPARATOR = "path.separator";
    public static final String JAVA_META_INF = "META-INF";
    public static final String NATIVES = "natives";
    public static final String JAWT = "jawt";
    private static final Logger LOG = LoggerFactory.getLogger(GLBootstrap.class);

    private Map<String, String> platformNativeIndex = new HashMap<>();

    public GLBootstrap () throws Exception {

        System.setProperty("jogamp.gluegen.UseTempJarCache", "false");

        LOG.info(format("Initializing native JOGL jar dependencies for platform [%s]. Temp jar cache disabled.",
            PlatformPropsImpl.os_and_arch));

        String nativeJarName = String.format("%s-%s", NATIVES, PlatformPropsImpl.os_and_arch);
        String[] classpathEntries = System.getProperty(JAVA_CLASSPATH).split(System.getProperty(JAVA_SEPARATOR));

        for( String jarPath : classpathEntries ){

            if( jarPath.contains(nativeJarName) ){

                if( LOG.isDebugEnabled() ){
                    LOG.debug(format("Applicable platform jar: [%s]", jarPath));
                }

                try( JarFile jf = new JarFile(jarPath) ){
                    Enumeration<JarEntry> jarEntries = jf.entries();

                    while( jarEntries.hasMoreElements() ){

                        JarEntry je = jarEntries.nextElement();

                        if( !je.isDirectory() && !je.getName().contains(JAVA_META_INF) ){
                            if( LOG.isDebugEnabled() ){
                                LOG.debug(format("Mapping jar entry [%s] -> [%s]", je.getName(), jarPath));
                            }
                            if( LOG.isDebugEnabled() && platformNativeIndex.containsKey(je.getName()) ){
                                LOG.debug(format("Duplicate jar entry: [%s]", je.getName()));
                                LOG.debug(format("Mapped at: [%s]", platformNativeIndex.get(je.getName())));
                                LOG.debug(format("Also at: [%s]", jarPath));
                            }
                            platformNativeIndex.put(je.getName(), jarPath);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean loadLibrary (String libname, boolean ignoreError, ClassLoader cl) {
        try{
            for( Entry<String, String> nativeEntry : platformNativeIndex.entrySet() ){
                if( nativeEntry.getKey().contains(libname) ){
                    if( LOG.isDebugEnabled() ){
                        LOG.debug(format("Loading mapped entry: [%s] [%s] [%s]", libname, nativeEntry.getKey(),
                            nativeEntry.getValue()));
                    }

                    try( JarFile jf = new JarFile(nativeEntry.getValue()) ){
                        JarEntry je = jf.getJarEntry(nativeEntry.getKey());
                        File tempDir = new File(System.getProperty(JAVA_TMP_DIR));
                        File temp = new File(tempDir, format("%s.jni", libname));

                        if( LOG.isDebugEnabled() ){
                            LOG.debug(format("Extracting to file [%s]", temp.getAbsolutePath()));
                        }
                        if( temp.createNewFile() ){
                            try( OutputStream out = new BufferedOutputStream(new FileOutputStream(temp)) ){
                                IOUtils.copy(jf.getInputStream(je), out);
                            }
                        }
                        System.load(temp.getAbsolutePath());
                        return true;
                    }
                }
            }
        }catch( Exception e ){
            LOG.error(format("Unable to load native library [%s]", libname), e);
        }

        if( LOG.isDebugEnabled() ){
            LOG.debug(format("No mapped library match for [%s]", libname));
        }
        return false;
    }

    @Override
    public void loadLibrary (String libname, String[] preload, boolean preloadIgnoreError, ClassLoader cl) {

        if( JAWT.compareTo(libname) == 0 ){
            Runtime.getRuntime().loadLibrary("jawt");
            return;
        }

        if( null != preload ){
            for( int i = 0; i < preload.length; i++ ){
                loadLibrary(preload[i], preloadIgnoreError, cl);
            }
        }
        loadLibrary(libname, false, cl);
    }
}
