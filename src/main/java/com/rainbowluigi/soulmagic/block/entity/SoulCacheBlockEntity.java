package com.rainbowluigi.soulmagic.block.entity;

import com.rainbowluigi.soulmagic.inventory.SoulCacheScreenHandler;
import com.rainbowluigi.soulmagic.item.ReferenceStaffItem;
import com.rainbowluigi.soulmagic.item.SoulEssenceStaff;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class SoulCacheBlockEntity extends LockableContainerBlockEntity implements SidedInventory {

	private static final int[] ALL_SLOTS = new int[]{0,1,2};
	
	private DefaultedList<ItemStack> inventory;
	
	public SoulCacheBlockEntity() {
		super(ModBlockEntity.SOUL_INFUSER);
		this.inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag compound) {
		super.fromTag(state, compound);
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		Inventories.fromTag(compound, this.inventory);
	}
	
	@Override
	public CompoundTag toTag(CompoundTag compound) {
		super.toTag(compound);
		Inventories.toTag(compound, this.inventory);
		return compound;
	}

	@Override
	public int size() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.inventory) {
			if(!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStack(int i) {
		return i >= 0 && i < this.inventory.size() ? this.inventory.get(i) : ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeStack(int i1, int i2) {
		return Inventories.splitStack(this.inventory, i1, i2);
	}

	@Override
	public ItemStack removeStack(int i) {
		return Inventories.removeStack(this.inventory, i);
	}

	@Override
	public void setStack(int i, ItemStack stack) {
		if(i >= 0 && i < this.inventory.size()) {
			this.inventory.set(i, stack);
		}
	}

	@Override
	public boolean canPlayerUse(PlayerEntity playerEntity_1) {
		if (this.world.getBlockEntity(this.pos) != this) {
			return false;
		} else {
			return playerEntity_1.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}

	@Override
	public int[] getAvailableSlots(Direction d) {
		return ALL_SLOTS;
	}

	@Override
	public boolean canInsert(int var1, ItemStack var2, Direction var3) {
		return this.isValid(var1, var2);
	}

	@Override
	public boolean canExtract(int var1, ItemStack var2, Direction var3) {
		return true;
	}

	@Override
	public boolean isValid(int slot, ItemStack stack) {
		return stack.getItem() instanceof SoulEssenceStaff && !(stack.getItem() instanceof ReferenceStaffItem);
	}
	
	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.soulmagic.soul_cache");
	}

	@Override
	protected ScreenHandler createScreenHandler(int i, PlayerInventory pi) {
		return new SoulCacheScreenHandler(i, pi, this);
	}
}
