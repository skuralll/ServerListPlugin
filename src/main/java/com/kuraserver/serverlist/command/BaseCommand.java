package com.kuraserver.serverlist.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.permission.DefaultPermissions;
import cn.nukkit.permission.Permission;
import com.kuraserver.serverlist.Core;

abstract public class BaseCommand extends Command {

    protected Core plugin;

    public BaseCommand(String name, String description, String usageMessage, String permissionName, String permissionValue, Core plugin){
        super(name, description, usageMessage);

        this.plugin = plugin;

        Permission permission = new Permission(permissionName, "", permissionValue);
        DefaultPermissions.registerPermission(permission);
        this.setPermission(permissionName);
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args){
        if(!this.plugin.isEnabled()){
            return false;
        }

        if(!this.testPermission(sender)){
            return false;
        }

        return true;
    }


}
