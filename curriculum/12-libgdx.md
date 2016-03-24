Download and run the [libGDX setup app](https://libgdx.badlogicgames.com/download.html).

## HelloGame

In the setup app, call the project `HelloGame` with the package `com.theironyard`. Uncheck everything but "Desktop". After generating the project, import it into IntelliJ. In the import wizard, select "Import project from external model" and make sure Gradle is selected. When the project loads, go to `Run -> Edit Configurations...`. Click the `+` button and add an "Application". The name will be "Desktop", the main class will be `DesktopLauncher`, the working directory will be the `core/assets` folder, and the classpath module will be "desktop_main".

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/assets/libgdx-1.png)

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
        float deceleration = 0.95f; // the closer to 1, the slower the deceleration
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

## SuperKoalio

In the setup app, call the project `SuperKoalio` with the package `com.theironyard`. Uncheck everything but "Desktop". After generating the project, import it into IntelliJ. In the import wizard, select "Import project from external model" and make sure Gradle is selected. When the project loads, go to `Run -> Edit Configurations...`. Click the `+` button and add an "Application". The name will be "Desktop", the main class will be `DesktopLauncher`, the working directory will be the `core/assets` folder, and the classpath module will be "desktop_main".

Download the [assets zip file](https://github.com/oakes/java-assignments/raw/master/curriculum/assets/super-koalio-assets.zip) and extract it into your `core/assets` folder.

You should now be able to run the app with the Desktop configuration selected. Let's open MyGdxGame.java and make it display the koala bear with a blue background:

```java
public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    TextureRegion stand, jump;

    static final int WIDTH = 18;
    static final int HEIGHT = 26;

    @Override
    public void create () {
        batch = new SpriteBatch();
        Texture sheet = new Texture("koalio.png");
        TextureRegion[][] tiles = TextureRegion.split(sheet, WIDTH, HEIGHT);
        stand = tiles[0][0];
        jump = tiles[0][1];
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(stand, 0, 0);
        batch.end();
    }
}
```

We should adjust it to upscale the image:

```java
public class MyGdxGame extends ApplicationAdapter {
    ...
    static final int DRAW_WIDTH = WIDTH*3;
    static final int DRAW_HEIGHT = HEIGHT*3;

    ...

    @Override
    public void render () {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(stand, 0, 0, DRAW_WIDTH, DRAW_HEIGHT);
        batch.end();
    }
}
```

Now copy the `move` method from `HelloGame` and all that it depends on:

```java
public class MyGdxGame extends ApplicationAdapter {
    ...
    float x, y, xv, yv;
    
    ...
    static final float MAX_VELOCITY = 500;
    
    ...

    @Override
    public void render () {
        move();

        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(stand, x, y, DRAW_WIDTH, DRAW_HEIGHT);
        batch.end();
    }
    
    float decelerate(float velocity) {
        float deceleration = 0.95f; // the closer to 1, the slower the deceleration
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

Add gravity and jump velocity:

```java
public class MyGdxGame extends ApplicationAdapter {
    ...
    boolean canJump;

    ...
    static final float MAX_JUMP_VELOCITY = 2000;
    static final int GRAVITY = -50;

    ...

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && canJump) {
            yv = MAX_JUMP_VELOCITY;
            canJump = false;
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

        yv += GRAVITY;

        y += yv * Gdx.graphics.getDeltaTime();
        x += xv * Gdx.graphics.getDeltaTime();

        if (y < 0) {
            y = 0;
            canJump = true;
        }

        yv = decelerate(yv);
        xv = decelerate(xv);
    }
}
```

Show the jump texture when jumping:

```java
public class MyGdxGame extends ApplicationAdapter {
    ...
    
    @Override
    public void render () {
        move();

        TextureRegion img;
        if (y > 0) {
            img = jump;
        }
        else {
            img = stand;
        }

        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, x, y, DRAW_WIDTH, DRAW_HEIGHT);
        batch.end();
    }
    
    ...
}
```

Flip the texture when the direction changes:

```java
public class MyGdxGame extends ApplicationAdapter {
    ...
    boolean canJump, faceRight = true;

    ...

    @Override
    public void render () {
        ...

        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (faceRight) {
            batch.draw(img, x, y, DRAW_WIDTH, DRAW_HEIGHT);
        }
        else {
            batch.draw(img, x + DRAW_WIDTH, y, DRAW_WIDTH * -1, DRAW_HEIGHT);
        }
        batch.end();
    }

    ...

    void move() {
        ...
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;
            faceRight = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xv = MAX_VELOCITY * -1;
            faceRight = false;
        }

        ...
    }
}
```

Finally, add the walk animation:

```java
public class MyGdxGame extends ApplicationAdapter {
    ...
    Animation walk;
    float time;
    
    ...

    @Override
    public void create () {
        ...
        walk = new Animation(0.2f, tiles[0][2], tiles[0][3], tiles[0][4]);
    }

    @Override
    public void render () {
        time += Gdx.graphics.getDeltaTime();

        move();

        TextureRegion img;
        if (y > 0) {
            img = jump;
        }
        else if (xv != 0) {
            img = walk.getKeyFrame(time, true);
        }
        else {
            img = stand;
        }

        ...
    }

    ...
}
```
