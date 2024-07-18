package com.eternalcode.core;

import com.eternalcode.core.feature.catboy.CatboyService;
import com.eternalcode.core.feature.language.LanguageService;
import com.eternalcode.core.feature.spawn.SpawnService;
import com.eternalcode.core.feature.teleport.TeleportService;
import com.eternalcode.core.feature.warp.WarpService;

public interface EternalCoreApi {

    CatboyService getCatboyService();

    LanguageService getLanguageService();

    SpawnService getSpawnService();

    TeleportService getTeleportService();

    WarpService getWarpService();
}
