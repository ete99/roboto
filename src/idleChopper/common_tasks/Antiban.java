package idleChopper.common_tasks;

import org.powerbot.script.rt4.ClientContext;
import idleChopper.Constants;
import idleChopper.SetUp.State;
import idleChopper.utility.Task;
import static idleChopper.script.AntibanScript.antibanned;

import static idleChopper.quest101.setUp;

public class Antiban extends Task {

    public Antiban(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation()== Constants.WC_ANIM && setUp.STATE == State.CHOPPING;
    }

    @Override
    public void execute() {
        System.out.println("AntiBanning");
        setUp.STATE = State.ANTIBANNING;
        antibanned(3,(()->(ctx.players.local().animation()==-1 || ctx.equipment.select().name(setUp.AXE_NAME).count()==1&&ctx.varpbits.varpbit(300)==1000)));

//        moveMouseOffScreen();
//        doAntibanAction(Random.nextInt(1,11));
    }
}
