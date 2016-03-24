Download and run the [libGDX setup app](https://libgdx.badlogicgames.com/download.html).

## HelloGame

In the setup app, call the project `HelloGame` with the package `com.theironyard`. Uncheck everything but "Desktop". After generating the project, import it into IntelliJ. In the import wizard, select "Import project from external model" and make sure Gradle is selected. When the project loads, go to `Run -> Edit Configurations...`. Click the `+` button and add an "Application". The name will be "Desktop", the working directory will be the `core/assets` folder, the classpath module will be "desktop_main", and the main class will be `DesktopLauncher`.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/libgdx-1.png)

You should now be able to run the app with the Desktop configuration selected. Let's open MyGdxGame.java and make the image move:

```java
public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    float x, y;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render () {
        x++;
        y++;

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, x, y);
        batch.end();
    }
}
```

Now add a `move` method that uses the keyboard arrows to determine where to move the image:

```java
public class MyGdxGame extends ApplicationAdapter {
	  ...

    @Override
    public void render () {
        move();

        ...
    }

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x--;
        }
    }
}
```

Now let's introduce velocity with `xv` and `yv` to allow for more fluid movement:

```java
public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    float x, y, xv, yv;
    static final float MAX_VELOCITY = 100;

    ...

    float decelerate(float velocity) {
        float deceleration = 0.99f; // the closer to 1, the slower the deceleration
        velocity *= deceleration;
        if (Math.abs(velocity) < 1) {
            velocity = 0;
        }
        return velocity;
    }

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yv = MAX_VELOCITY * -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xv = MAX_VELOCITY * -1;
        }

        y += yv * Gdx.graphics.getDeltaTime();
        x += xv * Gdx.graphics.getDeltaTime();

        yv = decelerate(yv);
        xv = decelerate(xv);
    }
}
```
