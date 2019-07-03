package walker;

import idleChopper.Constants;
import idleChopper.script.AntibanScript;
import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

import static idleChopper.quest101.setUp;


@Script.Manifest(name = "1lker", properties = "author=ete; topic=1333332; client=4;", description = "jaja")
public class tests extends PollingScript<ClientContext>{

    public void start() {

    }
    public final static Tile GE = new Tile(3165, 3485);
    public final static Tile PRE_GE = new Tile(3180, 3381);
    public final static Area hearo = new Area(new Tile(3195,3363), new Tile(3207,3351));

    @Override
    public void poll() {
        if(ctx.players.local().tile().distanceTo(GE)>10){
            TilePath p = ctx.movement.newTilePath(PRE_GE);
            p.traverse();
            p.randomize(1,1);
            ctx.movement.step(GE);
            AntibanScript.moveMouseOffScreen(ctx,0,()->!ctx.players.local().inMotion(),false);
        }
    }
}
