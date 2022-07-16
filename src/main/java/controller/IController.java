package controller;

import view.main.KeyHandler;
import view.main.MouseHandler;

public interface IController {

    void input (KeyHandler keyHandler, MouseHandler mouseHandler);

}
