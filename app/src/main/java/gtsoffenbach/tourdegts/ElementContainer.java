package gtsoffenbach.tourdegts;

import java.util.ArrayList;

import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;

/**
 * Created by Noli on 31.08.2014.
 */
public class ElementContainer {
    private ArrayList<UIElement> elements = new ArrayList<UIElement>();
    private Screen screen;
    private boolean enabled;

    public ElementContainer(Screen screen, boolean visible) {
        this.screen = screen;
        enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void bringToForeground(UIElement element) {
        if (elements.contains(element)) {
            elements.remove(element);
            elements.add(element);
        }
    }

    public void addElement(UIElement element) {
        elements.add(element);
    }

    public void updateAll(float delta, Graphics graphics) {
        if (enabled) {
            for (UIElement element : elements) {
                element.setGraphics(graphics);
                element.update(delta);
            }
        }
    }

    public void processClick(Input.TouchEvent event) {
        if (enabled && elements.size() != 0) {
            for (int i = elements.size(); i > 0; i--) {
                UIElement element = elements.get(i - 1);
                if (Utils.inBounds(event, element.getRectangle())) {
                    element.onClick(event);
                    break;
                }
            }
        }
    }


    public void remove(UIElement element) {
        if (element != null) {
            elements.remove(element);
            element.dismiss();
        }
    }

    public void remove(int i) {
        UIElement element = elements.get(i);
        elements.remove(i);
        element.dismiss();
    }
}
