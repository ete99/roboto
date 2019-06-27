package RockySand.tasks;

import RockySand.goblin_killer.Task;
import org.powerbot.script.rt4.ClientContext;

import static RockySand.utils.util.heal;
import static RockySand.utils.util.needsHeal;
import static RockySand.utils.sett.Agro;

public class ValidCheck extends Task
{



    public ValidCheck(ClientContext ctx)
    {
        super(ctx);
    }

    @Override
    public boolean activate() 
    {
        if (needsHeal())
            heal();
        if(ctx.players.local().inCombat())
            Agro=true;

        return false;
    }

    @Override
    public void execute()
    {
    }
}
