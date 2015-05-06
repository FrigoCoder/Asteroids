
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Image extends Component implements Comparable<Image> {

    public static final int ID = System.identityHashCode(Image.class);

    public final String filename;
    public final int order;

    public Image (String filename) {
        this(filename, 0);
    }

    public Image (String filename, int order) {
        this.filename = filename;
        this.order = order;
    }

    @Override
    public boolean equals (Object object) {
        if( object == null || this.getClass() != object.getClass() ){
            return false;
        }
        Image that = (Image) object;
        return order == that.order && filename.equals(that.filename);
    }

    @Override
    public int hashCode () {
        return (getClass().hashCode() * 31 + order) * 31 + filename.hashCode();
    }

    @Override
    public int compareTo (Image that) {
        if( order != that.order ){
            return order - that.order;
        }
        if( !filename.equals(that.filename) ){
            return filename.compareTo(that.filename);
        }
        return 0;
    }

}
