package proto.path.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import proto.path.test.Starter;
import proto.path.test.adapters.FileToPointGraphAdapter;
import proto.path.test.planes.PlaneStorage;
import proto.path.test.points.PointGraph;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class LaunchScreen implements Screen {
    private final Stage stage = new Stage();
    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeDrawer shapeDrawer = new ShapeDrawer(batch, new TextureRegion(new Texture("probe.png")));

    private PointGraph pointGraph = new PointGraph();
    private final PlaneStorage planeStorage = new PlaneStorage();

    public LaunchScreen (Starter starter) {
        TextField fileNameTextField = new TextField("", Starter.textFieldStyle);
        TextButton loadFileButton = new TextButton("Загрузить", Starter.textButtonStyle);
        TextButton launchPlaneButton = new TextButton("Запустить", Starter.textButtonStyle);
        TextButton editorScreenTextButton = new TextButton("Редактировать", Starter.textButtonStyle);

        fileNameTextField.setMessageText("Введите путь к файлу");

        fileNameTextField.setSize(200, 50);
        loadFileButton.setSize(200, 50);
        launchPlaneButton.setSize(200, 50);
        editorScreenTextButton.setSize(200, 50);

        float worldHeight = Gdx.graphics.getHeight();

        fileNameTextField.setPosition(50, worldHeight - 70);
        loadFileButton.setPosition(270, worldHeight - 70);
        launchPlaneButton.setPosition(490, worldHeight - 70);
        editorScreenTextButton.setPosition(710, worldHeight - 70);

        stage.addActor(fileNameTextField);
        stage.addActor(loadFileButton);
        stage.addActor(launchPlaneButton);
        stage.addActor(editorScreenTextButton);

        loadFileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pointGraph = FileToPointGraphAdapter.getPointGraphFromFile(fileNameTextField.getText());
            }
        });
        launchPlaneButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                planeStorage.addPlane(pointGraph);
            }
        });
        editorScreenTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                starter.changeScreen(false);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show () {

    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        batch.begin();
        pointGraph.render(shapeDrawer);
        planeStorage.render(shapeDrawer, batch, delta);
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose () {
        batch.dispose();
        stage.dispose();
    }
}
