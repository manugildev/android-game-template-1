package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import configuration.Configuration;

/**
 * Created by ManuGil on 09/03/15.
 */
public class AssetLoader {

    public static Texture logoTexture, dotT;
    public static TextureRegion logo, square, dot;

    public static BitmapFont font, fontS, fontXS;
    private static Preferences prefs;

    public static void load() {
        //LOGO TEXTURE "logo.png"
        logoTexture = new Texture(Gdx.files.internal("logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, logoTexture.getWidth(),
                logoTexture.getHeight());

        square = new TextureRegion(new Texture(Gdx.files.internal("square.png")), 0, 0, 10, 10);

        dotT = new Texture(Gdx.files.internal("dot.png"));
        dot = new TextureRegion(dotT, 0, 0, dotT.getWidth(), dotT.getHeight());


        //LOADING FONT
        Texture tfont = new Texture(Gdx.files.internal("font.png"), true);
        tfont.setFilter(Texture.TextureFilter.MipMapLinearNearest, TextureFilter.Linear);

        font = new BitmapFont(Gdx.files.internal("font.fnt"),
                new TextureRegion(tfont), true);
        font.setScale(2f, -2f);
        font.setColor(Color.WHITE);

        fontS = new BitmapFont(Gdx.files.internal("font.fnt"),
                new TextureRegion(tfont), true);
        fontS.setScale(1.5f, -1.5f);
        fontS.setColor(Color.WHITE);

        fontXS = new BitmapFont(Gdx.files.internal("font.fnt"),
                new TextureRegion(tfont), true);
        fontXS.setScale(1.3f, -1.3f);
        fontXS.setColor(Color.WHITE);


        //PREFERENCES - SAVE DATA IN FILE
        prefs = Gdx.app.getPreferences(Configuration.GAME_NAME);

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("games")) {
            prefs.putInteger("games", 0);
        }
    }

    public static void dispose() {
        font.dispose();
        fontS.dispose();
        fontXS.dispose();
        dotT.dispose();
        logoTexture.dispose();

    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void addGamesPlayed() {
        prefs.putInteger("games", prefs.getInteger("games") + 1);
        prefs.flush();
    }

    public static int getGamesPlayed() {
        return prefs.getInteger("games");
    }

    public static void setAds(boolean removeAdsVersion) {
        prefs.putBoolean("ads",removeAdsVersion);
        prefs.flush();
    }

    public static boolean getAds() {
        return prefs.getBoolean("ads",false);
    }
}
