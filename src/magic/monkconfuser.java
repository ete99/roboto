package magic;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;
import scripts.GUI;
import scripts.common_tasks.Antiban;
import scripts.script.AntibanScript;
import scripts.script.Util;

import java.awt.*;

import static org.powerbot.script.rt4.Magic.Spell.CURSE;
import static scripts.quest101.setUp;


@Script.Manifest(name = "lego", properties = "author=ete; topic=1333332; client=4;", description = "jaja")
public class monkconfuser extends PollingScript<ClientContext> implements MessageListener {

    public void start() {

    }

    boolean stop = false;
    final Tile tile= new Tile(3214, 3476);
    public void poll() {
        if(!ctx.players.local().tile().equals(tile))
            ctx.movement.step(tile);
        else if (!stop) {
            if(!ctx.magic.casting(CURSE)) {
                ctx.magic.cast(ctx.magic.book().spells()[14]);
                Condition.sleep(Random.nextInt(30, 50));
                if(!ctx.magic.casting(CURSE))
                    ctx.input.click(true);
            }
            Npc monk = ctx.npcs.select().id(520).poll();
            if (!stop && ctx.magic.casting(CURSE))
                monk.click("Cast");
            Condition.sleep(Random.nextInt(30, 120));
            miniAntiban();
        }
    }
    void miniAntiban(){
        if (Random.nextDouble() < 0.03 && !stop) {
            AntibanScript.moveMouseOffScreen(ctx, -1);
            Condition.sleep(Random.nextInt(3000, 7000));
            moveCamera(Random.nextInt(-10, 10), Random.nextInt(80, 99));
        }
    }
    void moveCamera(int angle, int pitch) {

        Thread t1 = new Thread(() -> ctx.camera.pitch(pitch));
        Thread t2 = new Thread(() -> ctx.camera.angle(angle));
            t1.start();
            t2.start();
    }

    @Override
    public void messaged(MessageEvent me) {
        String msg = me.text();
        if(msg.contains("Your foe's"))
        {
            System.out.println("little wait");
            this.stop =true;
            this.suspend();
            Condition.sleep(Random.nextInt(500, 1000));
            AntibanScript.moveMouseOffScreen(ctx,-1);
            this.resume();
            this.stop = false;
        }
        if(msg.contains("to cast this spell")){
            System.out.println("henloo");
            stop = true;
            this.stop();
            Condition.sleep(Random.nextInt(1000,2000));
            AntibanScript.moveMouseOffScreen(ctx,-1);
            Util.sendMail("paro magic");
        }
    }
}
