package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockMoss extends Block
{
	public BlockMoss()
	{
		super(Material.vine);
		setBurnProperties(blockID, 15, 100);
				//TODO: setTickRandomly()
		this.func_149675_a(true);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:moss");
	}


	@Override
	public void setBlockBoundsForItemRender()
	{
				//TODO: setBlockBounds
		this.func_149676_a0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	//TODO		getRenderType()
	public int func_149645_b()
	{
		return 20;
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
	{
		return false;
	}

	@Override
	//TODO:		   renderAsNormalBlock()
    public boolean func_149686_d()
	{
		return false;
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float var7 = 1.0F;
		float var8 = 1.0F;
		float var9 = 1.0F;
		float var10 = 0.0F;
		float var11 = 0.0F;
		float var12 = 0.0F;
		boolean var13 = var6 > 0;

		if ((var6 & 2) != 0)
		{
			var10 = Math.max(var10, 0.0625F);
			var7 = 0.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
			var13 = true;
		}

		if ((var6 & 8) != 0)
		{
			var7 = Math.min(var7, 0.9375F);
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
			var13 = true;
		}

		if ((var6 & 4) != 0)
		{
			var12 = Math.max(var12, 0.0625F);
			var9 = 0.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var13 = true;
		}

		if ((var6 & 1) != 0)
		{
			var9 = Math.min(var9, 0.9375F);
			var12 = 1.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var13 = true;
		}

		if (!var13 && this.canBePlacedOn(par1IBlockAccess.getBlockId(par2, par3 + 1, par4)))
		{
			var8 = Math.min(var8, 0.9375F);
			var11 = 1.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
		}

				//TODO: setBlockBounds
		this.func_149676_avar7, var8, var9, var10, var11, var12);
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
	{
		switch (par5)
		{
		case 1:
			return this.canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4));

		case 2:
			return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 + 1));

		case 3:
			return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 - 1));

		case 4:
			return this.canBePlacedOn(par1World.getBlockId(par2 + 1, par3, par4));

		case 5:
			return this.canBePlacedOn(par1World.getBlockId(par2 - 1, par3, par4));

		default:
			return false;
		}
	}

	/**
	 * returns true if a vine can be placed on that block (checks for render as normal block and if it is solid)
	 */
	private boolean canBePlacedOn(int par1)
	{
		if (par1 != Block.wood.blockID && par1 != Blocks.logs3.get().blockID && par1 != Block.stone.blockID)
			return false;
		else
			return true;
	}

	/**
	 * Returns if the vine can stay in the world. It also changes the metadata according to neighboring blocks.
	 */
	private boolean canVineStay(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		int var6 = var5;

		if (var5 > 0)
		{
			for (int var7 = 0; var7 <= 3; ++var7)
			{
				int var8 = 1 << var7;

				if ((var5 & var8) != 0 && !this.canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var7], par3, par4 + Direction.offsetZ[var7])) && (par1World.getBlockId(par2, par3 + 1, par4) != blockID || (par1World.getBlockMetadata(par2, par3 + 1, par4) & var8) == 0))
				{
					var6 &= ~var8;
				}
			}
		}

		if (var6 == 0 && !this.canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4)))
			return false;
		else
		{
			if (var6 != var5)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
			}

			return true;
		}
	}

	@Override
	public int getBlockColor()
	{
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	public int getRenderColor(int par1)
	{
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeFoliageColor();
	}

	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		if (!par1World.isRemote && !this.canVineStay(par1World, par2, par3, par4))
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlock(par2, par3, par4, 0);
		}
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void 	//TODO:		updateTick()
	public void func_149674_a(World world, int x, int y, int z, Random random)(World par1World, int par2, int par3, int par4, Random par5Random)
	{
	}

	/**
	 * called before onBlockPlacedBy by ItemBlock and ItemReed
	 */
	public void updateBlockMetadata(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8)
	{
		byte var9 = 0;

		switch (par5)
		{
		case 2:
			var9 = 1;
			break;

		case 3:
			var9 = 4;
			break;

		case 4:
			var9 = 8;
			break;

		case 5:
			var9 = 2;
		}

		if (var9 != 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var9, 2);
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		byte b0 = 0;

		switch (par5)
		{
		case 2:
			b0 = 1;
			break;
		case 3:
			b0 = 4;
			break;
		case 4:
			b0 = 8;
			break;
		case 5:
			b0 = 2;
		}

		return b0 != 0 ? b0 : par9;
	}

	@Override
	//TODO:    getQuantityDropped()
	public int func_149745_a(Random random)
	{
		return 1;
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}
}