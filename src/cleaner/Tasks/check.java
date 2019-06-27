package cleaner.Tasks;


import cleaner.Task;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class check extends Task {
    public check(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        if(Random.nextDouble()<0.01){
            AntibanScript.moveMouseOffScreen(ctx,-1);
            Condition.sleep(Random.nextInt(3000,5000));
        }
        return false;
    }

    @Override
    public void execute() {

    }
}
