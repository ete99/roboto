package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public class Constants {
    //@TODO: Pasar todo a constantes
    final static int OAK_ID = 10820;
    final static int WILL_ID = 10833;
    final static int YEW_ID = 10833;
    final static int MAGIC_ID = 10834;
    public static int WC_ANIM;
    public static int RUN_ANIM;
    final static  int MITH_AXE = 1355;
    final static  int ADAM_AXE = 1357;
    public final static int WOODCUTTING_SKILL=22;
    public final static Tile GE = new Tile(3165, 3485);
    public final static Tile EDGE = new Tile(3093, 3491);
    public final static Area WILLOW_AREA = new Area(new Tile(3170,3276), new Tile(3160, 3266));
    public final static Area YEW_AREA = new Area( new Tile(3089, 3482),new Tile(3085,3468));
    public final static Area SGE_BANK_AREA =  new Area(new Tile(3180, 3446), new Tile(3185, 3434));
    public final static Area EDGE_BANK_AREA =  new Area(new Tile(3091, 3488), new Tile(3098, 3497));
    public final static Area MAGIC_BANK_AREA =  new Area(new Tile(3380, 3266), new Tile(3385, 3274));
    public final static Area MAGIC_REST_AREA =  new Area(new Tile(3367, 3299), new Tile(3359, 3295));
    public final static Area MAGIC_AREA1 =  new Area(new Tile(3366, 3313), new Tile(3371, 3309));
    public final static Area MAGIC_AREA2 =  new Area(new Tile(3353, 3314), new Tile(3359, 3309));
    public final static Area MAGIC_FULL_AREA =  new Area(new Tile(3373, 3295), new Tile(3353, 3324));
    public final static Tile EDGE_TO_BANK_RIDE[] = {
            new Tile(3087, 3477),
            new Tile(3089, 3470),
            new Tile(3092, 3470),
            new Tile(3094, 3474),
            new Tile(3089, 3487),
            new Tile(3092, 3491)};
    public final static Tile MAGIC_TO_BANK_RIDE[] = {
            new Tile(3358, 3300),
            new Tile(3363, 3290),
            new Tile(3370, 3296),
            new Tile(3379, 3289),
            new Tile(3381, 3281),
            new Tile(3383, 3271)
    };

/* gnome
    public final static Tile TREE_TWO_TO_BANK[] = {
            new Tile(2438, 3421),
            new Tile(2423, 3427),
            new Tile(2410, 3429),
            new Tile(2395, 3428),
            new Tile(2381, 3425),
            new Tile(2375, 3477),
    };
    public final static Tile TREE_THREE_TO_BANK[] = {
            new Tile(2490, 3412),
            new Tile(2474, 3409),
            new Tile(2457, 3409),
            new Tile(2447, 3413),
    };
*/
public final static Tile SGE_BANK_RIDE[] = { new Tile(3172, 3426), new Tile(3177, 3428), new Tile(3182, 3434)};


}
