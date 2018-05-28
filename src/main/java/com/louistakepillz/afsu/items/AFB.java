package louistakepillz.afsu.items;

import ic2.api.item.IElectricItem;
import ic2.core.IC2;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AFB extends Item implements IElectricItem{

    @SideOnly(Side.CLIENT)
    private IIcon empty, partly, full;

    public AFB(){
        this.setCreativeTab(IC2.tabIC2);
        this.setUnlocalizedName("ALC");
        this.setMaxStackSize(1);
        this.setMaxDamage(27);
        this.setNoRepair();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        this.empty = register.registerIcon("AFSU:alcempty");
        this.partly = register.registerIcon("AFSU:alcpartly");
        this.full = register.registerIcon("AFSU:alcfull");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta){
        if (meta <= 1) {
            return full;
        }
        if (meta >= this.getMaxDamage() - 1) {
            return empty;
        }
        return partly;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack){
        return true;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack){
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack){
        return this;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack){
        return 100000000.0D; // 100 million
    }

    @Override
    public int getTier(ItemStack itemStack){
        return 5;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack){
        return 131072.0D;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.values()[2];
    }
}
