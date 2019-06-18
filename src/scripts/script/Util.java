package scripts.script;

import org.powerbot.script.rt4.GameObject;
import scripts.Constants;
import scripts.SetUp;

import static scripts.quest101.setUp;

public class Util {
    public static void Chop(GameObject Tree){
        Tree.interact("Chop");
        Tree.click();
        if(setUp.ctx.players.local().animation() == Constants.WC_ANIM)
            setUp.state = SetUp.Type.CHOPPING;
        else if (setUp.ctx.players.local().animation()!=-1 && Constants.WC_ANIM==0)
            Constants.WC_ANIM = setUp.ctx.players.local().animation();
    }
}
