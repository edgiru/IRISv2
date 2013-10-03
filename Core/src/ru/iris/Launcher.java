package ru.iris;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * IRISv2 Project
 * Author: Nikolay A. Viguro
 * WWW: iris.ph-systems.ru
 * E-Mail: nv@ph-systems.ru
 * Date: 08.09.13
 * Time: 22:52
 * License: GPL v3
 */
public class Launcher {

    public static HashMap<String, String> config;
    private static Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) throws Exception {

        log.info ("----------------------------------------");
        log.info ("----       IRISv2 is starting       ----");
        log.info ("----------------------------------------");

        // Запускаем TCP сервер H2
        Server.createTcpServer().start();

        // Запускаем сервис REST;
        runModule("java -jar Rest.jar");

        // Запускаем захват звука
        runModule("java -jar Record.jar");

        // Запускаем синтез звука
        runModule("java -jar Speak.jar");

        // Запускаем модуль для работы с устройствами
        runModule("java -jar Devices.jar");

        // Запускаем модуль планировщика
        runModule("java -jar Scheduler.jar");

        // Запускаем модуль для работы c событиями
        runModule("java -jar Events.jar");
    }

    private static void runModule(String cmd) throws IOException {

        ProcessBuilder builder = new ProcessBuilder(cmd.split("\\s+")).redirectOutput(ProcessBuilder.Redirect.INHERIT).redirectErrorStream(true);
        builder.start();
    }
}
