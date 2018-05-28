package louistakepillz.afsu.container;

import java.util.List;

import louistakepillz.afsu.tileentity.TileEntityAFSU;
import net.minecraft.entity.player.EntityPlayer;
import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlotArmor;
import ic2.core.slot.SlotInvSlot;

public class ContainerAFSU extends ContainerFullInv<TileEntityAFSU> {

    public ContainerAFSU(EntityPlayer player, TileEntityAFSU tileEntity){
        super(player, tileEntity, 196);
        for (int col = 0; col < 4; col++) {
            addSlotToContainer(new InvSlotArmor(player.inventory, col, 8 + col * 18, 84));
        }
        addSlotToContainer(new SlotInvSlot(tileEntity.chargeSlot, 0, 56, 17));
        addSlotToContainer(new SlotInvSlot(tileEntity.dischargeSlot, 0, 56, 53));
    }

    @Override
    public List<String> getNetworkedFields(){
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("redstoneMode");
        ret.add("chargeSlot");
        ret.add("dischargeSlot");
        return ret;
    }
}
