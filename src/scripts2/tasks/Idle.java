package scripts2.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import scripts2.Task;

public class Idle extends Task {

    static int t, c;
    public Idle(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Condition.sleep(500);
        System.out.println("idle check");
        if(ctx.players.local().animation()==-1  && t==ctx.inventory.select().count()){
            c++;
            t=ctx.inventory.select().count();
            System.out.println("idle");
        }

        else{
            System.out.println("not idle");
            t=ctx.inventory.select().count();
            c=0;
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        if(t>1000){
            System.exit(3);
        }
    }
}
