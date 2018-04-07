package ro.coderdojo.serverproject;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Server;

public class UnZip {

    public static void unzip(Server server) throws Exception {
        File spigotRootFolder = getServer().getWorldContainer();
        File archeryFolder = new File(spigotRootFolder.getAbsolutePath() + "/Archery_map");
        File arenaFolder = new File(spigotRootFolder.getAbsolutePath() + "/Arena_map");
        File lobbyFolder = new File(spigotRootFolder.getAbsolutePath() + "/world_lobby");
        File worldFolder = new File(spigotRootFolder.getAbsolutePath() + "/Second_world");

        
        //editarea lumilor se face in C:\CoderDojo\SkyWars-Project\src\main\resources\worlds


        if(arenaFolder.exists()) {
        Files.walk(arenaFolder.toPath(), FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(
                        (File f) -> {if(f.exists() ) {
//                             System.out.println("del:" + f);
                            boolean b = f.delete();
//                            System.out.println("deleted? "+ b);
                        }}
                );
        
        }
        
        if(lobbyFolder.exists()) {
        Files.walk(lobbyFolder.toPath(), FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(
                        (File f) -> {if(f.exists() ) {
//                             System.out.println("del:" + f);
                            boolean b = f.delete();
//                            System.out.println("deleted? "+ b);
                        }}
                );
        
        }
        
        if(worldFolder.exists()) {
        Files.walk(worldFolder.toPath(), FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(
                        (File f) -> {if(f.exists() ) {
//                             System.out.println("del:" + f);
                            boolean b = f.delete();
//                            System.out.println("deleted? "+ b);
                        }}
                );
        
        }
        
        if(archeryFolder.exists()) {
        Files.walk(archeryFolder.toPath(), FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(
                        (File f) -> {if(f.exists() ) {
//                             System.out.println("del:" + f);
                            boolean b = f.delete();
//                            System.out.println("deleted? "+ b);
                        }}
                );
        
        }

//        skyWarsFolder.mkdirs();

        URI uri = UnZip.class.getResource("/worlds").toURI();
//        System.out.println("!!!!" + uri);
        Path myPath;
        if (uri.getScheme().equals("jar")) {
//            System.out.println("jar");
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            myPath = fileSystem.getPath("/worlds");
//            System.out.println("!!!!path:" + myPath);
        } else {
            myPath = Paths.get(uri);
        }
//        System.out.println("start copy");
        Files.walk(myPath, Integer.MAX_VALUE).forEach(
                (Path p) -> {
//                    System.out.println("walk:"+p);
//                    if (p.toFile().isFile()) {
                        try {
                            String srcPath = p.toUri().toASCIIString();
                            String destination = spigotRootFolder.getAbsoluteFile().toPath() + "/" + srcPath.substring(srcPath.indexOf("/worlds/")+8);
//                             System.out.println(" copy " + p + " ----> " + destination);
                            Files.copy(p, new File(destination).toPath());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                    }
                }
        );

    }
}
