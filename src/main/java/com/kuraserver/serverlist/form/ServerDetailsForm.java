package com.kuraserver.serverlist.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import com.kuraserver.serverlist.ServerListAPI;
import com.kuraserver.serverlist.information.ServerDetails;

import java.net.InetSocketAddress;

public class ServerDetailsForm extends FormWindowModal implements FormInterface {

    private transient String name;

    private transient String ip;
    private transient int port;

    public ServerDetailsForm(String bodyLink) {
        super("サーバーリスト", "", "このサーバーへ行く", "戻る");
        ServerDetails details = ServerListAPI.getDetail(bodyLink);

        this.name = details.getName();

        this.ip = details.getIp();
        this.port = details.getPort();

        this.setTitle(details.getName());

        String text = "";
        text += "§l【サーバー説明】§r§f\n";
        text += details.getText() + "...\n\n";
        this.setContent(text);
    }

    @Override
    public void handleResponse(Player player, FormResponse response) {
        if(((FormResponseModal)response).getClickedButtonId() == 0){
            player.transfer(new InetSocketAddress(this.ip, this.port));
            Server.getInstance().getLogger().info(player.getName() +  "が" + this.name + "へ移動しました");
        }
        else{
            ServerListAPI.showServerList(player);
        }
    }

}
