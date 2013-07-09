
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Renderable extends Component {

	public float size;

	public Renderable () {
		this(10.0f);
	}

	public Renderable(float size) {
		this.size = size;
	}
	
}
