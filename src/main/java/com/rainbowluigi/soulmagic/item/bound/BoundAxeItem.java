package com.rainbowluigi.soulmagic.item.bound;

import com.rainbowluigi.soulmagic.item.SoulEssenceStaffDisplayer;
import com.rainbowluigi.soulmagic.soultype.ModSoulTypes;
import com.rainbowluigi.soulmagic.soultype.SoulType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BoundAxeItem extends AxeItem implements SoulEssenceStaffDisplayer {

	private SoulType[] types = {ModSoulTypes.LIGHT, ModSoulTypes.DARK};
	
	public BoundAxeItem(Settings item$Settings_1) {
		super(BoundToolMaterial.BOUND_MATERIAL, 6.0F, -3.1F, item$Settings_1);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		if(stack.hasTag() && stack.getTag().contains("soulGem")) {
			ItemStack gem = ItemStack.fromTag(stack.getTag().getCompound("soulGem"));
			player.setStackInHand(hand, gem);
		}
		return new TypedActionResult<ItemStack>(ActionResult.PASS, stack);
	}

	@Override
	public SoulType[] getSoulTypesToShow(ItemStack stack, PlayerEntity player) {
		return this.types;
	}
}
