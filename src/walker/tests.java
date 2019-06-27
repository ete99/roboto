package walker;

import idleChopper.script.AntibanScript;
import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;

import static idleChopper.quest101.setUp;


@Script.Manifest(name = "1lker", properties = "author=ete; topic=1333332; client=4;", description = "jaja")
public class tests extends PollingScript<ClientContext>{

    public void start() {

    }
    public final static Tile GE = new Tile(3165, 3485);

    @Override
    public void poll() {
        if(ctx.players.local().tile().distanceTo(GE)>10){
            ctx.movement.step(GE);
            AntibanScript.moveMouseOffScreen(ctx,0,()->!ctx.players.local().inMotion(),false);
        }
    }
}
