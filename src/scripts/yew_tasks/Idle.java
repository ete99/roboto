package scripts.yew_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import static scripts.script.Util.sendMail;
import scripts.SetUp.*;
import scripts.utility.Task;

import static scripts.quest101.setUp;

public class Idle extends Task {

    static int t, c;
    public Idle(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Condition.sleep(500);
//        System.out.println("idle check");
        if(ctx.players.local().animation()==-1  && t==ctx.inventory.select().count() && !(ctx.objects.select().within(setUp.TREE_AREA).name("Tree Stump").size()==2)){
            c++;
            t=ctx.inventory.select().count();
            if(c>20) {
                setUp.STATE = State.IDLE;
            }
            if(c>500) {
                setUp.STATE = State.REALLY_IDLE;
            }
//            System.out.println("idle");
        }
        else{
            if(ctx.objects.select().within(setUp.TREE_AREA).name("Tree Stump").size()==2) {
                setUp.STATE = State.WAITING;
                Condition.wait(() -> ctx.objects.select().within(setUp.TREE_AREA).name("Tree Stump").size()==2, 400,20);
            }
//            System.out.println("not idle");
            t=ctx.inventory.select().count();
            c=0;
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        if(c>1000){
            sendMail("Paro");

            System.exit(3);
        }
    }
}