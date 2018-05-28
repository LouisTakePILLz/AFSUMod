package louistakepillz.afsu;

import louistakepillz.afsu.blocks.AFSUBlock;
import louistakepillz.afsu.blocks.ItemBlockAFSU;
import louistakepillz.afsu.gui.GuiHandler;
import louistakepillz.afsu.items.AFB;
import louistakepillz.afsu.tileentity.TileEntityAFSU;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = AFSUMod.AFSU_MODID, name = "AFSU Mod", version = "@VERSION@", dependencies = "required-after:IC2")
public class AFSUMod {

    public static final String AFSU_MODID = "AFSU";

    @Instance(AFSUMod.AFSU_MODID)
    public static AFSUMod instance;

    public static final Block AFSU = new AFSUBlock();
    public static final Item ALC = new AFB();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        GameRegistry.registerTileEntity(TileEntityAFSU.class, "AFSU");

        GameRegistry.registerBlock(AFSU, ItemBlockAFSU.class, "AFSU");
        GameRegistry.registerItem(ALC, "ALC");
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(AFSUMod.instance, new GuiHandler());
        Recipes.advRecipes.addRecipe(new ItemStack(ALC),
            "LLL", "LCL", "LLL",
                'L', IC2Items.getItem("lithiumDust"),
                'C', IC2Items.getItem("lapotronCrystal"));

        Recipes.advRecipes.addRecipe(new ItemStack(AFSU),
            "IMI", "MAM", "IMI",
                'M', IC2Items.getItem("mfsUnit"),
                'I', IC2Items.getItem("iridiumPlate"),
                'A', AFSUMod.ALC);
    }
}
