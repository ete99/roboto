package scripts.common_tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import scripts.SetUp.State;
import scripts.utility.Task;

import static scripts.quest101.setUp;
import static scripts.script.Util.sendMail;

public class Idle extends Task {

    static int c;
    public Idle(ClientContext ctx) {
        super(ctx);
    }

    //@TODO refactor idle
    @Override
    public boolean activate() {

        Condition.sleep(500);
//        System.out.println("idle check");
        if(ctx.players.local().animation()==-1 && (ctx.objects.select().name(setUp.TREE_NAME).size()>0)){
            System.out.println("entro idle");
            c++;
            if(c>3) {
                setUp.STATE = State.IDLE;
            }
            if(c>100) {
                setUp.STATE = State.REALLY_IDLE;
            }
        }
        else if (ctx.objects.select().name(setUp.TREE_NAME).size()==0){
                setUp.STATE = State.WAITING;
                System.out.println("waiting");

                Condition.wait(() -> !(ctx.objects.select().id(setUp.TREE_ID).size()>0), 400,20);
                c=0;
            return false;
        }
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
