package com.shadowfire5650.create_innovations.content.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class InnovationServerConfigs {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static final ForgeConfigSpec.ConfigValue<Boolean> doRubberMode;
	
	static {
		BUILDER.push("Configs for Create Innovations");
		
		doRubberMode = BUILDER.comment("Replaces create Dried Kelp recipes with Cured Rubber.").define("Rubber Mode", false);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
