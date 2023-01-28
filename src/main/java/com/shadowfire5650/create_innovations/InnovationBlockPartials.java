package com.shadowfire5650.create_innovations;

import com.jozufozu.flywheel.core.PartialModel;

public class InnovationBlockPartials {
    public static final PartialModel

            INSCRIBER_HEAD = block("inscriber/head");

    private static PartialModel block(String path) {
        return new PartialModel(CreateInnovations.asResource("block/" + path));
    }

    private static PartialModel entity(String path) {
        return new PartialModel(CreateInnovations.asResource("entity/" + path));
    }

    public static void init() {
        // init static fields
    }
}
