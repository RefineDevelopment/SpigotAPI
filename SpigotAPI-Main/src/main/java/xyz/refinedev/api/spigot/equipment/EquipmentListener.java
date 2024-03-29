package xyz.refinedev.api.spigot.equipment;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Credits to Nick_0251 for this
 * Code not owned or created by anyone from the Refine Development team!
 */
public class EquipmentListener implements Listener {

    private final List<String> blockedMaterials = Arrays.asList("FURNACE", "CHEST", "TRAPPED_CHEST", "BEACON", "DISPENSER",
            "DROPPER", "HOPPER", "WORKBENCH", "ENCHANTMENT_TABLE", "ENDER_CHEST", "ANVIL", "BED_BLOCK", "FENCE_GATE",
            "SPRUCE_FENCE_GATE", "BIRCH_FENCE_GATE", "ACACIA_FENCE_GATE", "JUNGLE_FENCE_GATE", "DARK_OAK_FENCE_GATE",
            "IRON_DOOR_BLOCK", "WOODEN_DOOR", "SPRUCE_DOOR", "BIRCH_DOOR", "JUNGLE_DOOR", "ACACIA_DOOR", "DARK_OAK_DOOR",
            "WOOD_BUTTON", "STONE_BUTTON", "TRAP_DOOR", "IRON_TRAPDOOR", "DIODE_BLOCK_OFF", "DIODE_BLOCK_ON",
            "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "FENCE", "SPRUCE_FENCE", "BIRCH_FENCE", "JUNGLE_FENCE",
            "DARK_OAK_FENCE", "ACACIA_FENCE", "NETHER_FENCE", "BREWING_STAND", "CAULDRON", "SIGN_POST", "WALL_SIGN",
            "SIGN", "LEVER", "BLACK_SHULKER_BOX", "BLUE_SHULKER_BOX", "BROWN_SHULKER_BOX", "CYAN_SHULKER_BOX", "GRAY_SHULKER_BOX",
            "GREEN_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", "LIME_SHULKER_BOX", "MAGENTA_SHULKER_BOX", "ORANGE_SHULKER_BOX",
            "PINK_SHULKER_BOX", "PURPLE_SHULKER_BOX", "RED_SHULKER_BOX", "SILVER_SHULKER_BOX", "WHITE_SHULKER_BOX", "RED_SHULKER_BOX");

    @EventHandler
    public final void onInventoryClick(InventoryClickEvent e) {
        boolean shift = false, numberkey = false;
        if (e.isCancelled()) return;
        if (e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)) {
            shift = true;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            numberkey = true;
        }
        if (e.getSlotType() != InventoryType.SlotType.ARMOR && e.getSlotType() != InventoryType.SlotType.QUICKBAR && e.getSlotType() != InventoryType.SlotType.CONTAINER)
            return;
        if (e.getClickedInventory() != null && !e.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
        if (!e.getInventory().getType().equals(InventoryType.CRAFTING) && !e.getInventory().getType().equals(InventoryType.PLAYER))
            return;
        if (!(e.getWhoClicked() instanceof Player)) return;
//        if (e.getCurrentItem() == null) return;
        EquipmentType newEquipmentType= EquipmentType.matchType(shift ? e.getCurrentItem() : e.getCursor());
        if (!shift && newEquipmentType != null && e.getRawSlot() != newEquipmentType.getSlot()) {
            return;
        }
        if (shift) {
            newEquipmentType = EquipmentType.matchType(e.getCurrentItem());
            if (newEquipmentType != null) {
                boolean equipping = true;
                if (e.getRawSlot() == newEquipmentType.getSlot()) {
                    equipping = false;
                }
                if (newEquipmentType.equals(EquipmentType.HELMET) && (equipping == (e.getWhoClicked().getInventory().getHelmet() == null)) || newEquipmentType.equals(EquipmentType.CHESTPLATE) && (equipping == (e.getWhoClicked().getInventory().getChestplate() == null)) || newEquipmentType.equals(EquipmentType.LEGGINGS) && (equipping == (e.getWhoClicked().getInventory().getLeggings() == null)) || newEquipmentType.equals(EquipmentType.BOOTS) && (equipping == (e.getWhoClicked().getInventory().getBoots() == null))) {
                    EquipmentSetEvent equipmentSetEvent= new EquipmentSetEvent((Player) e.getWhoClicked(), EquipmentSetEvent.EquipMethod.SHIFT_CLICK, newEquipmentType, equipping ? null : e.getCurrentItem(), equipping ? e.getCurrentItem() : null);
                    Bukkit.getServer().getPluginManager().callEvent(equipmentSetEvent);
                    if (equipmentSetEvent.isCancelled()) {
                        e.setCancelled(true);
                    }
                }
            }
        } else {
            ItemStack newArmorPiece = e.getCursor();
            ItemStack oldArmorPiece = e.getCurrentItem();
            if (numberkey) {
                if (e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                    ItemStack hotbarItem = e.getClickedInventory().getItem(e.getHotbarButton());
                    if (hotbarItem != null) {// Equipping
                        newEquipmentType= EquipmentType.matchType(hotbarItem);
                        newArmorPiece = hotbarItem;
                        oldArmorPiece = e.getClickedInventory().getItem(e.getSlot());
                    } else {// Unequipping
                        newEquipmentType= EquipmentType.matchType(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR ? e.getCurrentItem() : e.getCursor());
                    }
                }
            } else {
                newEquipmentType = EquipmentType.matchType(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR ? e.getCurrentItem() : e.getCursor());
            }
            if (newEquipmentType != null && e.getRawSlot() == newEquipmentType.getSlot()) {
                EquipmentSetEvent.EquipMethod method = EquipmentSetEvent.EquipMethod.DRAG;
                if (e.getAction().equals(InventoryAction.HOTBAR_SWAP) || numberkey)
                    method = EquipmentSetEvent.EquipMethod.HOTBAR_SWAP;
                EquipmentSetEvent equipmentSetEvent= new EquipmentSetEvent((Player) e.getWhoClicked(), method, newEquipmentType, oldArmorPiece, newArmorPiece);
                Bukkit.getServer().getPluginManager().callEvent(equipmentSetEvent);
                if (equipmentSetEvent.isCancelled()) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL) return;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final Player player = e.getPlayer();
            if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Material mat = e.getClickedBlock().getType();
                for (String s : blockedMaterials) {
                    if (mat.name().equalsIgnoreCase(s)) return;
                }
            }
            EquipmentType newEquipmentType= EquipmentType.matchType(e.getItem());
            if (newEquipmentType != null) {
                if (newEquipmentType.equals(EquipmentType.HELMET) && e.getPlayer().getInventory().getHelmet() == null || newEquipmentType.equals(EquipmentType.CHESTPLATE) && e.getPlayer().getInventory().getChestplate() == null || newEquipmentType.equals(EquipmentType.LEGGINGS) && e.getPlayer().getInventory().getLeggings() == null || newEquipmentType.equals(EquipmentType.BOOTS) && e.getPlayer().getInventory().getBoots() == null) {
                    EquipmentSetEvent equipmentSetEvent= new EquipmentSetEvent(e.getPlayer(), EquipmentSetEvent.EquipMethod.HOTBAR, EquipmentType.matchType(e.getItem()), null, e.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(equipmentSetEvent);
                    if (equipmentSetEvent.isCancelled()) {
                        e.setCancelled(true);
                        player.updateInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void dispenserFireEvent(BlockDispenseEvent e) {
        EquipmentType type = EquipmentType.matchType(e.getItem());
        if (EquipmentType.matchType(e.getItem()) != null) {
            Location loc = e.getBlock().getLocation();
            for (Player p : loc.getWorld().getPlayers()) {
                if (loc.getBlockY() - p.getLocation().getBlockY() >= -1 && loc.getBlockY() - p.getLocation().getBlockY() <= 1) {
                    if (p.getInventory().getHelmet() == null && type.equals(EquipmentType.HELMET) || p.getInventory().getChestplate() == null && type.equals(EquipmentType.CHESTPLATE) || p.getInventory().getLeggings() == null && type.equals(EquipmentType.LEGGINGS) || p.getInventory().getBoots() == null && type.equals(EquipmentType.BOOTS)) {
                        org.bukkit.block.Dispenser dispenser = (org.bukkit.block.Dispenser) e.getBlock().getState();
                        org.bukkit.material.Dispenser dis = (org.bukkit.material.Dispenser) dispenser.getData();
                        BlockFace directionFacing = dis.getFacing();
                        if (directionFacing == BlockFace.EAST && p.getLocation().getBlockX() != loc.getBlockX() && p.getLocation().getX() <= loc.getX() + 2.3 && p.getLocation().getX() >= loc.getX() || directionFacing == BlockFace.WEST && p.getLocation().getX() >= loc.getX() - 1.3 && p.getLocation().getX() <= loc.getX() || directionFacing == BlockFace.SOUTH && p.getLocation().getBlockZ() != loc.getBlockZ() && p.getLocation().getZ() <= loc.getZ() + 2.3 && p.getLocation().getZ() >= loc.getZ() || directionFacing == BlockFace.NORTH && p.getLocation().getZ() >= loc.getZ() - 1.3 && p.getLocation().getZ() <= loc.getZ()) {
                            EquipmentSetEvent equipmentSetEvent= new EquipmentSetEvent(p, EquipmentSetEvent.EquipMethod.DISPENSER, EquipmentType.matchType(e.getItem()), null, e.getItem());
                            Bukkit.getServer().getPluginManager().callEvent(equipmentSetEvent);
                            if (equipmentSetEvent.isCancelled()) {
                                e.setCancelled(true);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void itemBreakEvent(PlayerItemBreakEvent e) {
        EquipmentType type = EquipmentType.matchType(e.getBrokenItem());
        if (type != null) {
            Player p = e.getPlayer();
            EquipmentSetEvent equipmentSetEvent= new EquipmentSetEvent(p, EquipmentSetEvent.EquipMethod.BROKE, type, e.getBrokenItem(), null);
            Bukkit.getServer().getPluginManager().callEvent(equipmentSetEvent);
            if (equipmentSetEvent.isCancelled()) {
                ItemStack i = e.getBrokenItem().clone();
                i.setAmount(1);
                i.setDurability((short) (i.getDurability() - 1));
                if (type.equals(EquipmentType.HELMET)) {
                    p.getInventory().setHelmet(i);
                } else if (type.equals(EquipmentType.CHESTPLATE)) {
                    p.getInventory().setChestplate(i);
                } else if (type.equals(EquipmentType.LEGGINGS)) {
                    p.getInventory().setLeggings(i);
                } else if (type.equals(EquipmentType.BOOTS)) {
                    p.getInventory().setBoots(i);
                }
            }
        }
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        for (ItemStack i : p.getInventory().getArmorContents()) {
            if (i != null && !i.getType().equals(Material.AIR)) {
                Bukkit.getServer().getPluginManager().callEvent(new EquipmentSetEvent(p, EquipmentSetEvent.EquipMethod.DEATH, EquipmentType.matchType(i), i, null));
            }
        }
    }
}
