package ru.iris.restful;

/**
 * IRISv2 Project
 * Author: Nikolay A. Viguro
 * WWW: iris.ph-systems.ru
 * E-Mail: nv@ph-systems.ru
 * Date: 06.09.12
 * Time: 17:24
 * License: GPL v3
 */

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.qpid.AMQException;
import org.jetbrains.annotations.NonNls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iris.common.Config;
import ru.iris.common.I18N;
import ru.iris.common.Messaging;
import ru.iris.common.SQL;

import javax.jms.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;

public class Service
{
    public static HashMap<String, String> config;
    public static SQL sql;
    private static Logger log = LoggerFactory.getLogger (Service.class);
    public static MessageConsumer messageConsumer;
    public static MessageProducer messageProducer;
    @NonNls
    public static Messaging msg;
    public static Session session;
    private static I18N i18n = new I18N();

    public static void main(String[] args) throws IOException, SQLException, AMQException, JMSException, URISyntaxException
    {
        DOMConfigurator.configure("conf/etc/log4j.xml");

        Config cfg = new Config ();
        config = cfg.getConfig ();
        sql = new SQL ();

        msg = new Messaging ();
        messageConsumer = msg.getConsumer ();
        messageProducer = msg.getProducer ();
        session = msg.getSession ();

        try {
            HttpServer server = HttpServerFactory.create("http://" + config.get("httpHost") + ":" + config.get("httpPort") + "/");
            server.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check module status

        Message mess;
        @NonNls MapMessage m = null;

        msg.simpleSendMessage("status.answer", "alive", "rest");

        while ((mess = Service.messageConsumer.receive (0)) != null)
        {
            m = (MapMessage) mess;

            if(m.getStringProperty("qpid.subject").equals ("status.rest") || m.getStringProperty("qpid.subject").equals ("status.all"))
            {
                log.info (i18n.message("rest.got.status.query"));
                msg.simpleSendMessage("status.answer", "alive", "rest");
            }
        }
}
}