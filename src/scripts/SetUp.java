package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import static scripts.Constants.EDGE_BANK_AREA;
import static scripts.Constants.YEW_AREA;

public class  SetUp {
    public  String TREE_NAME = "Yew";
    public  String LOGS_NAME;  // To drop, possibly deprecated
    public  String AXE_NAME;
    public  int AXE_ID;
    public  Area TREE_AREA;
    public  Area BANK_AREA;
    public  State state = State.IDLE;
    public ClientContext ctx;
    public  Tile[] RIDE;

    SetUp (ClientContext ctx, int set){
        this.ctx = ctx;
        if(set == 1) {
            // @TODO hacer jframe y otras areas
            this.TREE_AREA = YEW_AREA;
            this.BANK_AREA = EDGE_BANK_AREA;
            this.TREE_NAME = "Yew";
            this.AXE_NAME = "Rune Axe";  //can change(update)
            this.AXE_ID = 1359;  //can change(update)
            this.RIDE = Constants.EDGE_TO_BANK_RIDE;
        } else {
            System.out.println("no tadavia");
        }
    }

    public enum State {
        IDLE,
        REALLY_IDLE,
        WAITING,
        ANTIBANNING,
        WALKING,
        CHOPPING,
        DROPPING,
        BANKING,
        UNKNOWN;
    }
}
