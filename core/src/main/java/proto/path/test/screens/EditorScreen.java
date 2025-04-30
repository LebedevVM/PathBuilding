package proto.path.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import proto.path.test.Starter;
import proto.path.test.adapters.ClickAdapter;
import proto.path.test.adapters.PointGraphToFileAdapter;
import proto.path.test.points.PointGraph;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class EditorScreen implements Screen {
    private final Stage stage = new Stage();
    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeDrawer shapeDrawer = new ShapeDrawer(batch, new TextureRegion(new Texture("probe.png")));

    private final PointGraph pointGraph = new PointGraph();

    public EditorScreen (Starter starter) {
        TextField fileNameTextField = new TextField("", Starter.textFieldStyle);
        TextButton uploadFileButton = new TextButton("Сохранить", Starter.textButtonStyle);
        TextButton launchScreenTextButton = new TextButton("Назад", Starter.textButtonStyle);

        fileNameTextField.setMessageText("Введите путь к файлу");

        fileNameTextField.setSize(200, 50);
        uploadFileButton.setSize(200, 50);
        launchScreenTextButton.setSize(200, 50);

        float worldHeight = Gdx.graphics.getHeight();

        fileNameTextField.setPosition(50, worldHeight - 70);
        uploadFileButton.setPosition(270, worldHeight - 70);
        launchScreenTextButton.setPosition(490, worldHeight - 70);

        stage.addActor(fileNameTextField);
        stage.addActor(uploadFileButton);
        stage.addActor(launchScreenTextButton);

        uploadFileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PointGraphToFileAdapter.uploadPointGraph(pointGraph, fileNameTextField.getText());
            }
        });
        launchScreenTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                starter.changeScreen(true);
            }
        });
    }

    public void click (Vector2 position) {
        position.set(position.x, Gdx.graphics.getHeight() - position.y);
        pointGraph.addPoint(position);
    }

    //Подключение InputMultiplexer-а позволяет считывать нажатия как со Stage, так и с ClickAdapter
    @Override
    public void show () {
        ClickAdapter clickAdapter = new ClickAdapter(this);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(clickAdapter);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        batch.begin();
        pointGraph.render(shapeDrawer);
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
