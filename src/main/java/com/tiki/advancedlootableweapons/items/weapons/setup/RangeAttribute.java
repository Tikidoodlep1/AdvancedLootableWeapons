package com.tiki.advancedlootableweapons.items.weapons.setup;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;


/**
 * Contains all entity attributes defined and registered by the vanilla game.
 */
public class RangeAttribute {

    public static final Attribute ATTACK_REACH = register("generic.attack_range", new RangedAttribute("generic.reachDistance", 5.0D, 0.0D, 1024.0D));

    public static final Attribute ARMOR = register("generic.armor", (new RangedAttribute("attribute.name.generic.armor", 0.0D, 0.0D, 30.0D)).setSyncable(true));

    public static final Attribute ARMOR_TOUGHNESS = register("generic.armor_toughness", (new RangedAttribute("attribute.name.generic.armor_toughness", 0.0D, 0.0D, 20.0D)).setSyncable(true));

    public static final Attribute ATTACK_SPEED = register("generic.attack_speed", (new RangedAttribute("attribute.name.generic.attack_speed", 4.0D, 0.0D, 1024.0D)).setSyncable(true));

    public static final Attribute ATTACK_KNOCKBACK = register("generic.attack_knockback", new RangedAttribute("attribute.name.generic.attack_knockback", 0.0D, 0.0D, 5.0D));

    public static final Attribute ATTACK_DAMAGE = register("generic.attack_damage", new RangedAttribute("attribute.name.generic.attack_damage", 2.0D, 0.0D, 2048.0D));

    private static Attribute register(String pId, Attribute pAttribute) {
        return Registry.register(Registry.ATTRIBUTE, pId, pAttribute);
    }
}