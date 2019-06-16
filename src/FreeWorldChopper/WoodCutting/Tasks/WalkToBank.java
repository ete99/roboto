package FreeWorldChopper.WoodCutting.Tasks;

import FreeWorldChopper.Methods.Walker;
import FreeWorldChopper.Script.FreeChopper;
import FreeWorldChopper.Script.Task;
import FreeWorldChopper.WoodCutting.Wrapper.WoodCutting;
import org.powerbot.script.rt6.Backpack;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

public class WalkToBank extends Task<ClientContext> {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    private Walker walk = new Walker(ctx);
    private Player myPlayer = ctx.players.local();
    private Backpack myBackpack = ctx.backpack;

    public boolean activate() {
        return myBackpack.select().count() == 28
                && !WoodCutting.getBankArea().contains(myPlayer);
    }

    public void execute() {
        FreeChopper.scriptStatus = "Walking to Bank.";

        walk.followPath(WoodCutting.getPathToBank(), -3, 3);
    }
}
