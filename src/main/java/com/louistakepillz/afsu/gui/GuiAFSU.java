package louistakepillz.afsu.gui;

import ic2.core.util.GuiTooltipHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.GuiIconButton;
import ic2.core.IC2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import louistakepillz.afsu.container.ContainerAFSU;

@SideOnly(Side.CLIENT)
public class GuiAFSU extends GuiContainer {

    private final ContainerAFSU container;
    private final String armorInv;
    private final String level;
    private final String name;
    private static final ResourceLocation BACKGROUND = new ResourceLocation("afsu:textures/gui/gui_afsu.png");

    public GuiAFSU(ContainerAFSU container){
        super(container);

        this.ySize = 196;
        this.container = container;
        this.armorInv = StatCollector.translateToLocal("ic2.EUStorage.gui.info.armor");
        this.level = StatCollector.translateToLocal("ic2.EUStorage.gui.info.level");
        this.name = StatCollector.translateToLocal("tile.AFSU.name");
    }

    @Override
    public void initGui(){
        super.initGui();
        this.buttonList.add(new GuiIconButton(0, (this.width - this.xSize) / 2 + 152, (this.height - this.ySize) / 2 + 4, 20, 20, new ItemStack(Items.redstone), true));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2){
        this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.armorInv, 8, this.ySize - 126 + 3, 4210752);

        this.fontRendererObj.drawString(this.level, 79, 25, 4210752);
        int e = (int)Math.min(this.container.base.energy, this.container.base.maxStorage);
        this.fontRendererObj.drawString(" " + e, 110, 35, 4210752);
        this.fontRendererObj.drawString("/" + this.container.base.maxStorage, 110, 45, 4210752);

        String output = StatCollector.translateToLocalFormatted("ic2.EUStorage.gui.info.output", this.container.base.output);
        this.fontRendererObj.drawString(output, 85, 60, 4210752);

        GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, this.container.base.getredstoneMode(), 153, 3, 172, 22);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GuiAFSU.BACKGROUND);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
        if (container.base.energy > 0.0D) {
            int i1 = (int)(24.0F * this.container.base.getChargeLevel());
            drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
        }
    }

    @Override
    protected void actionPerformed(GuiButton guibutton){
        super.actionPerformed(guibutton);
        if (guibutton.id == 0) IC2.network.get().initiateClientTileEntityEvent(container.base, 0);
    }
}
