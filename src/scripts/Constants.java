package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public class Constants {
    //@TODO: Pasar todo a constantes
    final static int OAK_ID = 10820;
    final static int WILL_ID = 10833;
    public static int WC_ANIM;
    public static int RUN_ANIM;
    final static  int MITH_AXE = 1355;
    final static  int ADAM_AXE = 1357;
    public final static Area WILLOW_AREA = new Area(new Tile(3170,3276), new Tile(3160, 3266));
    public final static Area YEW_AREA = new Area( new Tile(3088, 3482),new Tile(3085,3468));
    public final static Area SGE_BANK_AREA =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    public final static Area EDGE_BANK_AREA =  new Area(new Tile(3091, 3488), new Tile(3098, 3497));
    public final static Tile EDGE_TO_BANK_RIDE[] = {
            new Tile(3086, 3476),
            new Tile(3089, 3470),
            new Tile(3092, 3470),
            new Tile(3094, 3474),
            new Tile(3089, 3487),
            new Tile(3092, 3491)};
    public final static Tile SGE_BANK_RIDE[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};

}
