package oakNnormal.tasks;


import org.powerbot.script.rt4.ClientContext;
import oakNnormal.Task;

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
