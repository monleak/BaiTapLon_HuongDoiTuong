package model;


import model.Animals.*;

/**
 * Visitor pattern
 *
 * <ul>
 *     <li></li>
 * </ul>
 *
 */
public interface IInteractable {

    void interact (Chicken animal);
    void interact (Cat animal);
    void interact (Dog animal);
    void interact (Duck animal);
    void interact (Pig animal);

}
