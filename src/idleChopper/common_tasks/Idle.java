package idleChopper.common_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import idleChopper.SetUp.State;
import idleChopper.utility.Task;

import static idleChopper.quest101.setUp;
import static idleChopper.script.Util.Chop;
import static idleChopper.script.Util.sendMail;

public class Idle extends Task {

    static int c;
    public Idle(ClientContext ctx) {
        super(ctx);
    }

    //@TODO refactor idle
    @Override
    public boolean activate() {

        System.out.println("idle check");
        if(ctx.players.local().animation()==-1){
            System.out.println(Condition.wait(()->ctx.players.local().animation()!=-1));
            c++;
            if(c>3) {
                setUp.STATE = State.IDLE;
            }
            if(c>100) {
                setUp.STATE = State.REALLY_IDLE;
                ctx.game.logout();
                ctx.widgets.select().id(218).poll().component(70).click();
                c=0;
            }
        } else c=0;
        return false;

    }

    @Override
    public void execute() {
        if(c>1000){
            sendMail("Paro");

            System.exit(3);
        }
    }
}
