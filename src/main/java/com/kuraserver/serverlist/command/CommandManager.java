package com.kuraserver.serverlist.command;

import cn.nukkit.Server;
import cn.nukkit.command.CommandMap;
import com.kuraserver.serverlist.Core;

public class CommandManager {

    public static void init(Core plugin){
        CommandMap cmdMap = Server.getInstance().getCommandMap();
        cmdMap.register("servers", new ServersCommand(plugin));
    }

}
