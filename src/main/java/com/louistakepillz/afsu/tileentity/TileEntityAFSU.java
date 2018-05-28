package louistakepillz.afsu.tileentity;

import ic2.core.block.wiring.TileEntityElectricBlock;

public class TileEntityAFSU extends TileEntityElectricBlock{

    public static final int MAX_OUTPUT = 8192;
    public static final int MAX_STORAGE = 1000000000; // 1 billion

    public TileEntityAFSU(){
        super(5, TileEntityAFSU.MAX_OUTPUT, TileEntityAFSU.MAX_STORAGE);
    }

    @Override
    public String getInventoryName(){
        return "AFSU";
    }
}
