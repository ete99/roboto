package scripts.tasks;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import scripts.script.AntibanScript;
import scripts.Constants;
import scripts.SetUp.*;
import scripts.Task;
import scripts.script.Util;

import static scripts.quest101.setUp;

public class Antiban extends Task {

    public Antiban(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation()== Constants.WC_ANIM;
    }

    @Override
    public void execute() {
//        System.out.println("AntiBanning");
        setUp.STATE = State.ANTIBANNING;
        if (Random.nextDouble() > 0.90){
//            AntibanScript.randomCameraTurn(ctx);
            Thread t1 = new Thread(()-> Util.moveCamera(Random.nextInt(-90,90),Random.nextInt(50,99)));
            Thread t2 = new Thread(()-> AntibanScript.randomMouseMovement(ctx));
            t1.start();
            t2.start();
//            Util.moveCamera(Random.nextInt(-90,90),Random.nextInt(50,99));
//            AntibanScript.randomMouseMovement(ctx);
        }
        else {
            AntibanScript.moveMouseOffScreen(ctx,3);
        }

//        moveMouseOffScreen();
//        doAntibanAction(Random.nextInt(1,11));
    }
}
