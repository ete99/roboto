package scripts.tasks;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;
import scripts.Task;

public class WalkToBank extends Task {
    //static Tile  tiles[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};
    Area BANK_AREA;
    Tile RIDE[];
    public WalkToBank(ClientContext ctx, Area BANK_AREA,Tile RIDE[]) {
        super(ctx);
        this.BANK_AREA = BANK_AREA;
        this.RIDE = RIDE.clone();
    }
    @Override
    public boolean activate() {
        return !BANK_AREA.contains(ctx.players.local()) && ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("Walking to bank");
        Condition.sleep(Random.nextInt(250, 500));
        TilePath path = ctx.movement.newTilePath(RIDE);
        path.randomize(1, 1);
        path.traverse();

    }
}