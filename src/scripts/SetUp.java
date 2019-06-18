package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.Client;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import static scripts.Constants.YEW_AREA;

public class  SetUp {
    public static String TREE_NAME = "Yew";
    public static String AXE_NAME = "Rune Axe";
    public static Area TREE_AREA = YEW_AREA;
    public static Type state;
    public ClientContext ctx;
    public static GameObject currentTree;
    public static enum Type {
        IDLE,
        WAITING,
        ANTIBANNING,
        WALKING,
        CHOPPING,
        DROPPING,
        BANKING,
        UNKNOWN;
    }
}
