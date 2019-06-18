package scripts2.tasks;


import org.powerbot.script.rt4.ClientContext;
import scripts2.Task;

public class BoilerPlate extends Task {

    public BoilerPlate(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
    }
}
