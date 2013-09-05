
package net.tribe7.opengl.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import jogamp.common.os.PlatformPropsImpl;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Throwables;
import com.jogamp.common.jvm.JNILibLoaderBase.LoaderAction;

public class GLBootstrap implements LoaderAction {

    private Map<String, String> entryToJar = new HashMap<>();

    public GLBootstrap () throws Exception {
        System.setProperty("jogamp.gluegen.UseTempJarCache", "false");
        for( String jar : getClassPathEntries() ){
            if( jar.contains(PlatformPropsImpl.os_and_arch) ){
                addEntriesOfJar(jar);
            }
        }
    }

    private String[] getClassPathEntries () {
        return System.getProperty("java.class.path").split(System.getProperty("path.separator"));
    }

    private void addEntriesOfJar (String jarName) throws IOException {
        try( JarFile jar = new JarFile(jarName) ){
            Enumeration<JarEntry> entries = jar.entries();
            while( entries.hasMoreElements() ){
                addJarEntry(jarName, entries.nextElement());
            }
        }
    }

    private void addJarEntry (String jarName, JarEntry jarEntry) {
        if( !jarEntry.isDirectory() && !jarEntry.getName().contains("META-INF") ){
            entryToJar.put(jarEntry.getName(), jarName);
        }
    }

    @Override
    public boolean loadLibrary (String libname, boolean ignoreError, ClassLoader cl) {
        for( String entry : entryToJar.keySet() ){
            if( entry.contains(libname) ){
                load(entry);
            }
        }
        return true;
    }

    private void load (String entry) {
        File file = copyToTemp(entry);
        System.load(file.getAbsolutePath());
    }

    private File copyToTemp (String entry) {
        try( JarFile jar = new JarFile(entryToJar.get(entry)) ){
            JarEntry jarEntry = jar.getJarEntry(entry);
            File temp = new File(new File(System.getProperty("java.io.tmpdir")), entry + ".jni");
            temp.createNewFile();
            try( OutputStream out = new BufferedOutputStream(new FileOutputStream(temp)) ){
                IOUtils.copy(jar.getInputStream(jarEntry), out);
            }
            return temp;
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void loadLibrary (String libname, String[] preload, boolean preloadIgnoreError, ClassLoader cl) {
        for( int i = 0; i < preload.length; i++ ){
            loadLibrary(preload[i], preloadIgnoreError, cl);
        }
        loadLibrary(libname, false, cl);
    }

}
