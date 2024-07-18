package com.eternalcode.core;

import com.eternalcode.core.feature.catboy.CatboyService;
import com.eternalcode.core.feature.language.LanguageService;
import com.eternalcode.core.feature.spawn.SpawnService;
import com.eternalcode.core.feature.teleport.TeleportService;
import com.eternalcode.core.feature.warp.WarpService;
import com.eternalcode.core.injector.DependencyProvider;

class EternalCoreApiImpl implements EternalCoreApi {

    private final DependencyProvider dependencyProvider;

    public EternalCoreApiImpl(DependencyProvider dependencyProvider) {
        this.dependencyProvider = dependencyProvider;
    }

    @Override
    public CatboyService getCatboyService() {
        return this.dependencyProvider.getDependency(CatboyService.class);
    }

    @Override
    public LanguageService getLanguageService() {
        return this.dependencyProvider.getDependency(LanguageService.class);
    }

    @Override
    public SpawnService getSpawnService() {
        return this.dependencyProvider.getDependency(SpawnService.class);
    }

    @Override
    public TeleportService getTeleportService() {
        return this.dependencyProvider.getDependency(TeleportService.class);
    }

    @Override
    public WarpService getWarpService() {
        return this.dependencyProvider.getDependency(WarpService.class);
    }
}
