package gtsoffenbach.tourdegts;

import gtsoffenbach.tourdegts.gameinterface.Image;
import gtsoffenbach.tourdegts.gameinterface.Input;

/**
 * Created by Noli on 03.09.2014.
 */
public class Chest extends Element {
    private Animation chest_anim;
    private int speed, finalshow;

    public Chest(UIElement father, int sx, int sy, int speed, int finalshow) {
        super(father, sx, sy);
        this.chest_anim = new Animation(false);
        this.speed = speed;
        this.finalshow = finalshow;
        for (int i = 0; i < Assets.chest_half.length - 1; i++) {
            chest_anim.addFrame(Assets.chest_half[i], speed);
        }
        chest_anim.addFrame(Assets.chest_half[Assets.chest_half.length - 1], finalshow);
    }

    public Animation getChest_anim() {
        return chest_anim;
    }

    public void setChest_anim(Animation chest_anim) {
        this.chest_anim = chest_anim;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        chest_anim.update(50);

    }

    @Override
    public void draw(float delta) {
        Image image = chest_anim.getImage();
        // getGraphics().drawImage(image,getRectangle().left,getRectangle().top,getRectangle().right,getRectangle().bottom,getRectangle().right/2,getRectangle().bottom/2);
        //getGraphics().drawImage(image,0,0,Assets.chest[0].getWidth(),Assets.chest[0].getHeight(),Assets.chest[0].getWidth()/2,Assets.chest[0].getHeight()/2);
        // super.draw(delta);
        if (image != null) {
            //getGraphics().drawScaledImage(image, super.getRectangle().left, super.getRectangle().top, super.getRectangle().width(), super.getRectangle().height(), 0, 0, Assets.chest[0].getWidth(), Assets.chest[0].getHeight());
            //getGraphics().drawImage(image,super.getRectangle().left,super.getRectangle().top,Assets.chest[0].getWidth(),Assets.chest[0].getHeight(),Assets.chest[0].getWidth()/2,Assets.chest[0].getHeight()/2);
            getGraphics().drawImage(image, super.getRectangle().left, super.getRectangle().top);
        }
    }

    @Override
    public void onClick(Input.TouchEvent event) {
        super.onClick(event);
    }

    @Override
    public void Click() {
        super.Click();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
