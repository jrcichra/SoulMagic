package com.rainbowluigi.soulmagic.enchantment;

import com.rainbowluigi.soulmagic.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static final Enchantment SOUL_STEALER = new SoulStealerEnchantment();

    public static void registerEnchantments() {
    	registerEnchantment(SOUL_STEALER, "soul_stealer");
    }

    private static void registerEnchantment(Enchantment enchant, String name) {
        Registry.register(Registry.ENCHANTMENT, new Identifier(Reference.MOD_ID, name), enchant);
    }
}
