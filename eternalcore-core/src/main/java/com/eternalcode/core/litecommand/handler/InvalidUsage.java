package com.eternalcode.core.litecommand.handler;

import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.injector.annotations.lite.LiteHandler;
import com.eternalcode.core.notice.NoticeService;
import com.eternalcode.core.placeholder.Placeholders;
import com.eternalcode.core.viewer.ViewerProvider;
import com.eternalcode.core.viewer.Viewer;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import org.bukkit.command.CommandSender;

import java.util.List;

@LiteHandler(Schematic.class)
public class InvalidUsage implements InvalidUsageHandler<CommandSender> {

    private static final Placeholders<String> SCHEME = Placeholders.of("{USAGE}", scheme -> scheme);

    private final ViewerProvider viewerProvider;
    private final NoticeService noticeService;

    @Inject
    public InvalidUsage(ViewerProvider viewerProvider, NoticeService noticeService) {
        this.viewerProvider = viewerProvider;
        this.noticeService = noticeService;
    }

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        Viewer viewer = this.viewerProvider.any(sender);

        List<String> schematics = schematic.getSchematics();

        if (schematics.size() == 1) {
            this.noticeService.create()
                .viewer(viewer)
                .notice(translation -> translation.argument().usageMessage())
                .placeholder(SCHEME, schematics.get(0))
                .send();
            return;
        }

        this.noticeService.viewer(viewer, translation -> translation.argument().usageMessageHead());

        for (String schema : schematics) {
            this.noticeService.viewer(viewer, translation -> translation.argument().usageMessageEntry(), SCHEME.toFormatter(schema));
        }

    }
}