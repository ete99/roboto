package cleaner.Tasks;


import cleaner.Task;
import idleChopper.script.AntibanScript;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.MenuCommand;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class store extends Task {
    int HERB;
    public store(ClientContext ctx, int HERB) {
        super(ctx);
        this.HERB=HERB;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(HERB).count() == 0;
    }

    @Override
    public void execute() {
        if (!openBank()) {
            ctx.inventory.select().poll().click();
            if(!ctx.bank.opened())
                openBank();
        }
        if (ctx.bank.opened()) {
            ctx.bank.depositInventory();

            if (ctx.bank.opened() && ctx.bank.id(HERB).select().count() == 0) {
                throw new Error();
            }
            Condition.wait(() -> ctx.inventory.isEmpty(),150,3);
            ctx.bank.select().id(HERB).poll().click();
            Condition.wait(() -> ctx.inventory.select().id(HERB).count()!=0,150,3);
            ctx.bank.close();
        }
    }
    public boolean openBank(){
        System.out.println("open");
        if(!ctx.bank.opened()) {
            ctx.camera.turnTo(ctx.bank.nearest().tile(), 7);
            final Filter<MenuCommand> filter = new Filter<MenuCommand>() {
                public boolean accept(MenuCommand command) {
                    String action = command.action;
                    return action.equalsIgnoreCase("Bank") || action.equalsIgnoreCase("Open");
                }
            };
            GameObject bank = ctx.objects.select().id(10583).nearest().poll();
//            bank.hover();
//            if(ctx.menu.commands()[0].toString().contains("Use")){
//                Condition.sleep(Random.nextInt(350,600));
//                ctx.inventory.select().poll().click();
//                Condition.sleep(Random.nextInt(150,200));
//            }
            final boolean b = bank.click();
            return b;
        } else {
            return true;
        }
    }
}
