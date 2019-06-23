package scripts.common_tasks;

import org.powerbot.script.rt4.ClientContext;
import scripts.Constants;
import scripts.SetUp.State;
import scripts.utility.Task;
import static scripts.script.AntibanScript.antibanned;

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
        antibanned(3,(()->ctx.objects.select().name(setUp.TREE_NAME).size()>0));

//        moveMouseOffScreen();
//        doAntibanAction(Random.nextInt(1,11));
    }
}
