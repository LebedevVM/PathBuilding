package proto.path.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import proto.path.test.screens.EditorScreen;
import proto.path.test.screens.LaunchScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Starter extends Game {
    public static BitmapFont font;
    public static TextField.TextFieldStyle textFieldStyle;
    public static TextButton.TextButtonStyle textButtonStyle;

    @Override
    public void create() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("TimesNewRoman.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = Color.WHITE;
        fontParameter.size = 18;
        fontParameter.characters = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя" +
            "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz,+*=:\\.0123456789";
        font = fontGenerator.generateFont(fontParameter);

        TextureRegionDrawable noTexture = new TextureRegionDrawable(new Texture("noTexture.png"));
        TextureRegionDrawable cursor = new TextureRegionDrawable(new Texture("probe.png"));

        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = noTexture;
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.cursor = cursor;

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.overFontColor = Color.GRAY;
        textButtonStyle.checkedFontColor = Color.WHITE;
        textButtonStyle.downFontColor = Color.GRAY;

        changeScreen(true);
    }

    public void changeScreen (boolean launch) {
        if (launch) {
            this.setScreen(new LaunchScreen(this));
        }
        else {
            this.setScreen(new EditorScreen(this));
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
