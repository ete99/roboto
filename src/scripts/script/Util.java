package scripts.script;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import scripts.Constants;
import scripts.SetUp;


import static scripts.quest101.setUp;

public class Util {
    public static void Chop(GameObject Tree){
        Tree.interact("Chop");
        Tree.click();
//        if(setUp.ctx.players.local().animation() == Constants.WC_ANIM)
        setUp.state = SetUp.State.CHOPPING;
        if (setUp.ctx.players.local().animation()!=-1 && Constants.WC_ANIM==0)
            Constants.WC_ANIM = setUp.ctx.players.local().animation();
    }

    public static void walkToBank(Tile[] RIDE){
        TilePath path = setUp.ctx.movement.newTilePath(RIDE);
        path.randomize(1, 1);
        path.traverse();
        if(setUp.ctx.players.local().animation()!=-1 && Constants.RUN_ANIM==0)
            Constants.RUN_ANIM = setUp.ctx.players.local().animation();
    }

    public static void pickAxe(){
        ClientContext ctx = setUp.ctx;
        ctx.camera.angle(ctx.camera.yaw() + Random.nextInt(-50, 50));
        if(!ctx.bank.inViewport() && ctx.bank.close()){
            ctx.camera.pitch(99-Random.nextInt(0, 3));
        }else {
            setUp.state = SetUp.State.BANKING;
            ctx.bank.open();
            ctx.bank.withdraw(setUp.AXE_ID, 1);
            ctx.bank.close();
        }
    }
}
