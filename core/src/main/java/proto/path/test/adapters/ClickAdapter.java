package proto.path.test.adapters;

import com.badlogic.gdx.math.Vector2;
import proto.path.test.screens.EditorScreen;

public class ClickAdapter extends com.badlogic.gdx.InputAdapter {
    private final EditorScreen editorScreen;

    public ClickAdapter (EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        editorScreen.click(new Vector2(screenX, screenY));
        return false;
    }
}
