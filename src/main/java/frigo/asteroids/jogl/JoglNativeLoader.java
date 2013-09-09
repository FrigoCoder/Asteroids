
package frigo.asteroids.jogl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import jogamp.common.os.PlatformPropsImpl;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Throwables;
import com.jogamp.common.jvm.JNILibLoaderBase.LoaderAction;

public class JoglNativeLoader implements LoaderAction {

    @Override
    public void loadLibrary (String libname, String[] preload, boolean preloadIgnoreError, ClassLoader cl) {
        for( int i = 0; i < preload.length; i++ ){
            loadLibrary(preload[i], preloadIgnoreError, cl);
        }
        loadLibrary(libname, false, cl);
    }

    @Override
    public boolean loadLibrary (String libname, boolean ignoreError, ClassLoader cl) {
        for( String jar : getPlatformJars() ){
            for( String entry : getJarEntries(jar, libname) ){
                System.out.println("Loading " + libname + " from " + jar + "!/../" + entry);
                File file = copyToTemp(jar, entry);
                System.load(file.getAbsolutePath());
            }
        }
        return true;
    }

    private List<String> getPlatformJars () {
        List<String> result = new LinkedList<>();
        String[] classpath = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        for( String jar : classpath ){
            if( jar.contains(".jar") && jar.contains(PlatformPropsImpl.os_and_arch) ){
                result.add(jar);
            }
        }
        return result;
    }

    private List<String> getJarEntries (String jarName, String libname) {
        List<String> result = new LinkedList<>();
        try( JarFile jar = new JarFile(jarName) ){
            for( Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements(); ){
                JarEntry entry = entries.nextElement();
                if( !entry.isDirectory() && entry.getName().contains(libname) ){
                    result.add(entry.getName());
                }
            }
        }catch( IOException e ){
            throw Throwables.propagate(e);
        }
        return result;
    }

    private File copyToTemp (String jarName, String entryName) {
        try( JarFile jar = new JarFile(jarName) ){
            JarEntry entry = jar.getJarEntry(entryName);
            try( InputStream inputStream = jar.getInputStream(entry) ){
                return copyToTemp(inputStream);
            }
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }
    }

    private File copyToTemp (InputStream stream) throws IOException, FileNotFoundException {
        File file = Files.createTempFile(null, ".jni").toFile();
        try( OutputStream out = new BufferedOutputStream(new FileOutputStream(file)) ){
            IOUtils.copy(stream, out);
        }
        return file;
    }

}
