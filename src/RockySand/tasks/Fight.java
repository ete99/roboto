package Rockysand.tasks;

import RockySand.goblin_killer.Task;
import RockySand.utils.util;

import org.powerbot.script.rt4.*;

import static RockySand.utils.sett.*;
import static RockySand.utils.util.*;
import static RockySand.goblin_killer.Constants.ABANDONED_HOUSE;

public class Fight extends Task
{
    public Fight(ClientContext ctx) 
    {
        super(ctx);
    }

    @Override
    public boolean activate() 
    {
        return Agro && hasFood() && ABANDONED_HOUSE.contains(ctx.players.local()) && !needsHeal();
    }

    @Override
    public void execute()
    {
        if (hasFood() || ctx.combat.health() >= 10)
        {
            if (needsHeal())
            {
                heal();
            }
            else if (shouldAttack())
            {
                attack();
            }

            if(goblin != null && goblin.valid())
            {
                targetTile = goblin.tile();
            }
        }
    }
}
