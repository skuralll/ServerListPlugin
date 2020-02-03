package com.kuraserver.serverlist.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.permission.DefaultPermissions;
import cn.nukkit.permission.Permission;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.TextFormat;
import com.kuraserver.serverlist.Core;
import com.kuraserver.serverlist.ServerListAPI;
import com.kuraserver.serverlist.form.ServerListForm;

public class ServersCommand extends BaseCommand {

    public ServersCommand(Core plugin) {
        super("servers", "サーバーリストコマンド", "/servers", "com.kuraserver.servers", Permission.DEFAULT_TRUE, plugin);
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args){
        if(!super.execute(sender, commandLabel, args)) return false;

        if(!(sender instanceof Player)) sender.sendMessage(TextFormat.RED + "サーバー内で実行してください");

        ServerListAPI.showServerList((Player) sender);
        return true;
    }

}
