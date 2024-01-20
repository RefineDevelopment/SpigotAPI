# SpigotAPI
The most modern and support friendly public SpigotAPI (1.8 only)

This SpigotAPI supports KnockbackAPI from different spigot forks, along with helping the issue of EntityHider with spigots that don't have it.
Also, useful if you need PotionExpireEvent or EquipmentSetEvent.

## Features
- Support for popular spigots.
- Support for HCF Bukkit Events. (PotionExpireEvent, EquipmentSetEvent)
- Custom Entity Hider using protocol lib.
- Easy to use.

## Installing
You need to shade this repository in your plugin.

1. Clone this repository
2. Enter the directory: `cd SpigotAPI`
3. Build & install with Maven: `mvn clean package install`

OR
```xml
<repositories>
    <repository>
        <id>refine-public</id>
        <url>https://maven.refinedev.xyz/repository/public-repo/</url>
    </repository>
</repositories>
```
Next, add SpigotAPI to your project's dependencies via Maven

Add this to your `pom.xml` `<dependencies>`:
```xml
<dependency>
  <groupId>xyz.refinedev.api</groupId>
  <artifactId>SpigotAPI</artifactId>
  <version>1.2</version>
  <scope>compile</scope>
</dependency>
```

## Usage
This only requires ProtocolLib if you use the API's EntityHider.
You can initiate and set up the API using the following code:

```java
import xyz.refinedev.api.spigot.SpigotHandler;
import xyz.refinedev.api.spigot.SpigotType;

public class ExamplePlugin extends JavaPlugin {

    private SpigotHandler spigotHandler;

    @Override
    public void onEnable() {
        this.spigotHandler = new SpigotHandler(instance);
        // This will automatically detect if Entity Hider is needed or not.
        // If it's needed, then it'll register it accordingly.
        this.spigotHandler.init(true); // Boolean is for HCF mode.

        SpigotType type = this.spigotHandler.getType();
        if (type != SpigotType.Default) {
            this.getLogger().info("Found " + type.getName() + ", hooking in for support, HCF-TeamFights will be supported.");
        }
        
        // Usage can be like so
        Player player = Bukkit.getPlayer("NotDrizzy"); // your player object
        this.spigotHandler.getKnockback().setKnockback(player, "knockback name");
    }
}
```
