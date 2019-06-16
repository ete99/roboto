package FreeWorldChopper.WoodCutting.Tasks;

import FreeWorldChopper.Methods.Walker;
import FreeWorldChopper.Script.FreeChopper;
import FreeWorldChopper.Script.Task;
import FreeWorldChopper.WoodCutting.Wrapper.WoodCutting;
import org.powerbot.script.rt6.Backpack;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

public class WalkToSpot extends Task<ClientContext> {

    public WalkToSpot(ClientContext ctx) {
        super(ctx);
    }

    private Walker walk = new Walker(ctx);
    private Player myPlayer = ctx.players.local();
    private Backpack myBackpack = ctx.backpack;

    public boolean activate() {
        return myBackpack.select().isEmpty()
                && !WoodCutting.getChopArea().contains(myPlayer);
    }

    public void execute() {
        FreeChopper.scriptStatus = "Walking to Area.";

        walk.followPath(WoodCutting.getPathToSpot(), -3, 3);
    }
}
