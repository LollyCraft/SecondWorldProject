# SkyWars-Project

Fisierul cu Lumile editate trebuie pus in folderul spigot si sa nu mai fie rar/zip si folderele lumilor sa fie separate: sKYwARS_MAP SI world_lobby. Lumile sunt în folderul src\main\resources\worlds, respectiv fișierele SkyWars_map.zip și world_lobby.zip.
Datorită locației în care se află lumile (src\main\resources\worlds), ele se vor regăsi în ServerProject-1.0-SNAPSHOT.jar în /worlds. Urmează să scriem niște cod care desface lumile acestea din interiorul jar-ului și le pune automat în rădăcina serverului spigot.

~~Iar cel cu ServerProject trebuie unzip si pus in folerul de proiecte din CoderDojo.~~
Apoi deschideti netbeans-ul si deschideti proiectul ServerProject.

Ca sa stiti ce am facut la el:
* la login playerul se spawneaza in lobbyul principal.
* Daca mergeti direct inainte o sa dati de un stone button.Apasati pe el si o sa va teleporteze in arena de skywars la middle. 
* Daca muriti in arena sau in lobby o sa va respawnati in lobby.

acum pt spigot in gitbash aveti comenzile:

```bash
java -jar spigot-1.12.2.jar
```

```bash
op <playername>
```

gamemode 1 sau 0 sau ce mai vreti voi

kill

nu uitati ca in minecraft, daca sunteti op, comenzile se scriu cu / in fata.
