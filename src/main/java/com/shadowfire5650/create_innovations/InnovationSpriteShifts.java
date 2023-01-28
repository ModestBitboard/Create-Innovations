package com.shadowfire5650.create_innovations;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;

public class InnovationSpriteShifts {
	public static final CTSpriteShiftEntry OVERCHARGED_CASING = omni("overcharged_casing");
	public static final CTSpriteShiftEntry CAUTION_CASING = omni("caution_casing");

	public static final CTSpriteShiftEntry THERMAL_CASING = omni("thermal_casing");

	public static final CTSpriteShiftEntry THERMAL_GLASS_CASING = omni("thermal_glass_casing");
	
	private static CTSpriteShiftEntry omni(String name) {
		return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
	}
	
	private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
		return CTSpriteShifter.getCT(type, CreateInnovations.asResource("block/" + blockTextureName), CreateInnovations.asResource("block/" + connectedTextureName + "_connected"));
	}

	private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
		return getCT(type, blockTextureName, blockTextureName);
	}

}
