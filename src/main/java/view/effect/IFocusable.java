package view.effect;

public interface IFocusable {

    void setHovered(boolean hovered);
    void setFocused(boolean focused);
    boolean getIsHovered ();
    boolean getIsFocused ();
}
