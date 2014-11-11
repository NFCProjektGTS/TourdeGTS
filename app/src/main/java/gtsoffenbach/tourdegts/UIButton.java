package gtsoffenbach.tourdegts;

/**
 * Created by Noli on 01.09.2014.
 */
public class UIButton extends UIElement {


    public UIButton(final ElementContainer container, final int dx, final int dy, final int sx, final int sy) {
        super(container, dx, dy, sx, sy);

    }

    public UIButton(final ElementContainer container, final int dx, final int dy) {
        super(container, dx, dy, Assets.button.getWidth(), Assets.button.getHeight());
    }

    @Override
    public void draw(float delta) {
        if (isPressed()) {
            //getGraphics().drawImage(Assets.button,getRectangle().left,getRectangle().top, 0, 0,getRectangle().width(),getRectangle().height());
            //getGraphics().drawImage(Assets.button,getRectangle().left,getRectangle().top, getRectangle().width(), getRectangle().height() );
            getGraphics().drawImage(Assets.button, getRectangle().left, getRectangle().top);
        } else {
            //getGraphics().drawImage(Assets.button_pressed,getRectangle().left,getRectangle().top, 0, 0,getRectangle().width(),getRectangle().height());
            getGraphics().drawImage(Assets.button_pressed, getRectangle().left, getRectangle().top);
        }
    }

    @Override
    public void Click() {
        Assets.click.play(1f);
        //getContainer().remove(1); Button click to remove other button :D

    }
}
