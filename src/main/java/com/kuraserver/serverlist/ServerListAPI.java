package com.kuraserver.serverlist;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.NukkitRunnable;
import com.kuraserver.serverlist.form.ServerDetailsForm;
import com.kuraserver.serverlist.form.ServerListForm;
import com.kuraserver.serverlist.information.ServerDetails;
import com.kuraserver.serverlist.information.ServerOverView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ServerListAPI {

    private static Core plugin;

    public static void init(Core plugin){
        ServerListAPI.plugin = plugin;
    }

    public static ArrayList<ServerOverView> getOverViews(){
        ArrayList list = new ArrayList<ServerOverView>();

        Document document = null;
        try {
            document = Jsoup.connect("https://pe.minecraft.jp/").userAgent("Mozilla").get();
        } catch (IOException e) {
            return list;
        }

        Elements nameElements = document.select("body #wrap table tbody tr .name");
        for (Element nameElement : nameElements) {
            list.add(new ServerOverView(nameElement.select("a").text(), nameElement.select("a").attr("abs:href")));
        }

        int i = 0;
        Elements tipElements = document.select("body #wrap table tbody tr .tags");
        for (Element tipElement : tipElements) {
            ((ServerOverView) list.get(i)).setTip(tipElement.getElementsByTag("p").text());
            i++;
        }

        return list;
    }

    public static ServerDetails getDetail(String link){
        Document document = null;
        try {
            document = Jsoup.connect(link).userAgent("Mozilla").get();
        } catch (IOException e) {
            return new ServerDetails("", "", "", 19132, "", 0, 0, 0, "");
        }

        Elements metaElements = document.select("head meta");

        String name = metaElements.select("[name=og:title]").attr("content");
        String text = metaElements.select("[name=og:description]").attr("content");

        Elements contentElements = document.select("body [id=content] [class=row] [class=span4] table tbody tr td");

        String[] address = contentElements.get(0).text().split(":");
        String ip = address[0];
        int port = address.length == 2 ? Integer.parseInt(address[1]) : 19132;

        String version = contentElements.get(1).text();

        String[] players = contentElements.get(4).text().split(" ");
        int currentPlayers = Integer.parseInt(players[0]);
        int maxPlayers = Integer.parseInt(players[2]);

        int ping = Integer.parseInt(contentElements.get(7).text().replace("ms", ""));

        String owner = contentElements.get(10).select("a").text();

        return new ServerDetails(name, text, ip, port, version, maxPlayers, currentPlayers, ping, owner);
    }

    public static void showServerList(Player player){
        Server.getInstance().getScheduler().scheduleTask(plugin, new NukkitRunnable() {
            @Override
            public void run() {
                ServerListForm form = new ServerListForm(plugin);
                ((Player) player).showFormWindow(form);
            }
        }, true);
    }

    public static void showServerDetail(Player player, String link){
        Server.getInstance().getScheduler().scheduleTask(plugin, new NukkitRunnable() {
            @Override
            public void run() {
                ServerDetailsForm form = new ServerDetailsForm(link);
                player.showFormWindow(form);
            }
        }, true);
    }

}
