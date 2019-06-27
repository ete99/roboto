package RockySand.utils;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.Npc;

import java.awt.*;

public class sett {
    public ClientContext  ctx;
    public static boolean Agro = true;


    //inventoryOpen VS inventoryClosed
    public static org.powerbot.script.rt4.Component inventory;
    public static  Component unselectedInventory;

    public static Npc goblin;
    public static Tile targetTile;



    public static Font font = new Font("Tahoma", Font.PLAIN, 12);
    public  static String status = "-";
    public static String substatus = "-";
    
    public sett(ClientContext ctx){
        this.ctx = ctx;
        this.unselectedInventory=ctx.widgets.widget(164).component(53);
        this.inventory = ctx.widgets.widget(164).component(60);
    }
}
