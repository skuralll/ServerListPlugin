package com.kuraserver.serverlist;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.NukkitRunnable;
import com.kuraserver.serverlist.command.CommandManager;
import com.kuraserver.serverlist.form.FormHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Core extends PluginBase {

    @Override
    public void onEnable() {
        ServerListAPI.init(this);
        CommandManager.init(this);
        FormHandler.init(this);

        ServerListAPI.getDetail("https://pe.minecraft.jp/servers/133.130.88.110:19132").getName();
    }

}