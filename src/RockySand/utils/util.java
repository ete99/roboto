package RockySand.utils;

import RockySand.goblin_killer.RockySands;
import RockySand.goblin_killer.Constants;
import idleChopper.script.AntibanScript;
import idleChopper.script.Util;
import org.powerbot.script.*;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

import static RockySand.utils.sett.*;

public class util {
    

    public static boolean needsHeal()
    {
        return RockySands.s.ctx.combat.health() < 11;
    }

    public static boolean shouldAttack()
    {
        Condition.wait(()-> RockySands.s.ctx.players.local().inCombat(),300,2);
        return !RockySands.s.ctx.players.local().inCombat();
    }

    public static boolean hasFood()
    {
        return RockySands.s.ctx.inventory.select().id(Constants.trout).count() > 0;
    }
/*
    public static void lootItemPile(Tile targetTile)
    {
        RockySands.sSubstatus("Looting");

        if(RockySands.getBones())
        {
            while(s.ctx.groundItems.select().at(targetTile).id(Constants.BONES).poll().valid() && s.ctx.inventory.count() < 28)
            {
                System.out.printf("Item pile contains bones.\n");

                final GroundItem item = s.ctx.groundItems.select().at(targetTile).id(Constants.BONES).poll();

                item.interact(false, "Take", item.name());

                Condition.wait(new Callable<Boolean>()
                {
                    @Override
                    public Boolean call() throws Exception
                    {
                        return !item.valid();
                    }
                }, 250, 8);
            }
        }

        while(s.ctx.groundItems.select().id(Constants.DROPS).at(targetTile).poll().valid() && s.ctx.inventory.count() < 28)
        {
            System.out.printf("Item pile contains loot.\n");

            final GroundItem item = s.ctx.groundItems.select().id(Constants.DROPS).at(targetTile).poll();

            item.interact(false, "Take", item.name());

            Condition.wait(new Callable<Boolean>()
            {
                @Override
                public Boolean call() throws Exception
                {
                    return !item.valid();
                }
            }, 250, 8);
        }

        targetTile = null;
    }

    public static void lootCoins()
    {
        final GroundItem coins = s.ctx.groundItems.select().id(Constants.COINS).select(new Filter<GroundItem>() {
            @Override
            public boolean accept(GroundItem groundItem)
            {
                if(s.ctx.inventory.select().id(Constants.trout).count() > 1)
                {
                    return groundItem.tile().distanceTo(s.ctx.players.local().tile()) < 4;
                }
                else
                {
                    return groundItem.tile().distanceTo(s.ctx.players.local().tile()) < 30;
                }

            }
        }).select(new Filter<GroundItem>()
        {
            @Override
            public boolean accept(GroundItem groundItem)
            {
                return !Constants.ABANDONED_HOUSE.contains(groundItem);
            }
        }).poll();

        if(coins.valid())
        {
            s.ctx.movement.step(coins);

            Condition.wait(new Callable<Boolean>()
            {
                @Override
                public Boolean call() throws Exception
                {
                    return !s.ctx.players.local().inMotion();
                }
            }, 250, 40);

            coins.interact(false, "Take", coins.name());

            Condition.wait(new Callable<Boolean>()
            {
                @Override
                public Boolean call() throws Exception
                {
                    return coins.valid();
                }
            }, 250, 8);
        }
    }
 */

    public static void attack()
    {
        System.out.printf("Selecting A Goblin To Attack\n");

        goblin = exclusiveLocateNPC(Constants.sandRocks);
        System.out.println(goblin.toString());

        if (!goblin.inViewport())
        {
            RockySands.s.ctx.camera.turnTo(goblin,50);
            RockySands.s.ctx.movement.step(goblin.tile());
//            s.ctx.movement.findPath(goblin.tile()).traverse();
        }
        final Filter<MenuCommand> filter = new Filter<MenuCommand>() {
            public boolean accept(MenuCommand command) {
                String action = command.action;
                return action.equalsIgnoreCase("Attack") || action.equalsIgnoreCase("Walk here");
            }
        };
        goblin.hover();
        goblin.click();
        Condition.sleep(Random.nextInt(50,80));
//        final boolean b = goblin.interact(filter);
        Util.moveCamera(RockySands.s.ctx,RockySands.s.ctx.camera.yaw()+ Random.nextInt(0,90),99);
        AntibanScript.moveMouseOffScreen(RockySands.s.ctx,1,()->!(goblin.inCombat()&&goblin.valid() && RockySands.s.ctx.players.local().inCombat()));

//        Condition.wait(()->goblin.inCombat()&&s.ctx.players.local().inCombat(), Random.nextInt(350,500),10);
//        goblin.interact(true, "Attack", "Goblin");

        Condition.wait(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                return goblin.inCombat();
            }
        }, 500, 10);

        Condition.wait(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                return RockySands.s.ctx.players.local().inCombat();
            }
        }, 500, 10);
        if(!RockySands.s.ctx.players.local().inCombat())
            RockySands.s.Agro = false;
        else RockySands.s.Agro = true;
    }
    public static void heal()
    {
        if (unselectedInventory.textureId() == -1)
        {
            inventory.click();
        }

        Item food = RockySands.s.ctx.inventory.select().id(Constants.trout).poll();

        final int startingHealth = RockySands.s.ctx.combat.health();

        food.interact("Eat");
        AntibanScript.moveMouseOffScreen(RockySands.s.ctx,-1,()-> RockySands.s.ctx.combat.health()!=startingHealth);

    }
/*
    public static void handleBones()
    {

        if (unselectedInventory.textureId() == -1)
        {
            inventory.click();
        }

        for (final Item bones : s.ctx.inventory.select().id(Constants.BONES))
        {
            startAmountInventory = s.ctx.inventory.select().count();

            if (RockySands.getBones())
            {
                RockySands.sSubstatus("Burying bones");

                bones.interact("Bury", "Bones");
            }
            else
            {
                RockySands.sSubstatus("Dropping bones");

                bones.interact("Drop", "Bones");
            }

            Condition.wait(new Callable<Boolean>()
            {
                @Override
                public Boolean call() throws Exception
                {
                    return !bones.valid() && s.ctx.players.local().animation() == -1;
                }
            }, 150, 20);
        }
    }
 */

    public static Npc exclusiveLocateNPC(int ids[])
    {
        return RockySands.s.ctx.npcs.select().id(ids).select(npc -> (!npc.inCombat())).nearest().poll();
    }
/*
    public static void walkToGoblins()
    {
        RockySands.sSubstatus("Walking to goblins");

        walker.walkPath(Constants.AK_BANK_TO_GOBLINS);

        Condition.wait(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                return s.ctx.players.local().inCombat();
            }
        }, Random.nextInt(850, 1420), 1);

        //if close to the gate and not already on the other side, then pay the toll
        if(s.ctx.objects.select().id(Constants.AL_KHARID_GATE).poll().tile().distanceTo(s.ctx.players.local()) < 8
                && s.ctx.players.local().tile().x() > Constants.GATE_SOUTH_SIDE)
        {
            System.out.printf("Opening Al-Kharid Gate\n");

            if (!s.ctx.objects.select().id(Constants.AL_KHARID_GATE).poll().inViewport())
            {
                s.ctx.camera.turnTo(s.ctx.objects.select().id(Constants.AL_KHARID_GATE).poll());
            }

            s.ctx.objects.select().id(Constants.AL_KHARID_GATE).poll().interact("Pay-toll(10gp)", "Gate");

            Condition.wait(new Callable<Boolean>()
            {
                @Override
                public Boolean call() throws Exception
                {
                    return s.ctx.players.local().tile().x() < Constants.GATE_NORTH_SIDE;
                }
            }, 500, 6);
        }
    }
 */
}
