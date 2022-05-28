package view.effect;

public class FocusableHandler implements IFocusable {
    private boolean isHovered;
    private boolean isFocused;

    public boolean getIsHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }

    public boolean getIsFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }
}
