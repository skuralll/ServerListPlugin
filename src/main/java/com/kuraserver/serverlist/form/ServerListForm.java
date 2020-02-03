package com.kuraserver.serverlist.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.scheduler.NukkitRunnable;
import com.kuraserver.serverlist.Core;
import com.kuraserver.serverlist.information.ServerOverView;
import com.kuraserver.serverlist.ServerListAPI;

import java.util.ArrayList;

public class ServerListForm extends FormWindowSimple implements FormInterface {

    private transient Core plugin;

    private transient ArrayList<String>links;

    public ServerListForm(Core plugin) {
        super("サーバーリスト", "");
        this.plugin = plugin;

        this.links = new ArrayList<String>();
        for (ServerOverView overview : ServerListAPI.getOverViews()){
            this.links.add(overview.getBodyLink());
            this.addButton(new ElementButton("§f｜｜｜§8§l" + overview.getName() + "§r§f｜｜｜§8\n" + overview.getTip()));
        }
    }

    @Override
    public void handleResponse(Player player, FormResponse response) {
        String link = this.links.get(((FormResponseSimple) response).getClickedButtonId());
        ServerListAPI.showServerDetail(player, link);
    }

}
