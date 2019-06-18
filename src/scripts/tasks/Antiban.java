package scripts.tasks;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import scripts.AntibanScript;
import scripts.Constants;
import scripts.SetUp.*;
import scripts.Task;

import static scripts.quest101.setUp;


/**
 * Created by Thijs on 16-6-2017.
 */
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
        System.out.println("AntiBanning");
        setUp.state = Type.ANTIBANNING;
        if (Random.nextDouble() > 0.90){
            AntibanScript.randomCameraTurn(ctx);
            AntibanScript.randomMouseMovement(ctx);
        }
        else {
            AntibanScript.moveMouseOffScreen(ctx,3);
        }

//        moveMouseOffScreen();
//        doAntibanAction(Random.nextInt(1,11));
    }
}
