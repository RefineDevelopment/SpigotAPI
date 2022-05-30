package xyz.refinedev.spigot.event.impl.equipment;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Credits to Nick_0251 for this
 * Code not owned or created by anyone from the Refine Development team!
 */
public final class EquipmentSetEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final EquipMethod equipType;
    private final EquipmentType type;
    private boolean cancel = false;
    private ItemStack oldArmorPiece, newArmorPiece;

    /**
     * Constructor for the ArmorEquipEvent.
     *
     * @param player        The player who put on / removed the armor.
     * @param type          The ArmorType of the armor added
     * @param oldArmorPiece The ItemStack of the armor removed.
     * @param newArmorPiece The ItemStack of the armor added.
     */
    public EquipmentSetEvent(final Player player, final EquipMethod equipType, final EquipmentType type, final ItemStack oldArmorPiece, final ItemStack newArmorPiece) {
        super(player);
        this.equipType = equipType;
        this.type = type;
        this.oldArmorPiece = oldArmorPiece;
        this.newArmorPiece = newArmorPiece;
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    public final static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Gets if this event is cancelled.
     *
     * @return If this event is cancelled
     */
    public final boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets if this event should be cancelled.
     *
     * @param cancel If this event should be cancelled.
     */
    public final void setCancelled(final boolean cancel) {
        this.cancel = cancel;
    }

    public final EquipmentType getType() {
        return type;
    }

    /**
     * Returns the last equipped armor piece, could be a piece of armor, or null.
     */
    public final ItemStack getOldArmorPiece() {
        return oldArmorPiece;
    }

    public final void setOldArmorPiece(final ItemStack oldArmorPiece) {
        this.oldArmorPiece = oldArmorPiece;
    }

    /**
     * Returns the newly equipped armor, could be a piece of armor, or null.
     */
    public final ItemStack getNewArmorPiece() {
        return newArmorPiece;
    }

    public final void setNewArmorPiece(final ItemStack newArmorPiece) {
        this.newArmorPiece = newArmorPiece;
    }

    /**
     * Gets the method used to either equip or unequip an armor piece.
     */
    public EquipMethod getMethod() {
        return equipType;
    }

    public enum EquipMethod {
        /**
         * When you join the server
         */
        JOIN,
        /**
         * When you shift click an armor piece to equip or unequip
         */
        SHIFT_CLICK,
        /**
         * When you drag and drop the item to equip or unequip
         */
        DRAG,
        /**
         * When you right click an armor piece in the hotbar without the inventory open to equip.
         */
        HOTBAR,
        /**
         * When you press the hotbar slot number while hovering over the armor slot to equip or unequip
         */
        HOTBAR_SWAP,
        /**
         * When in range of a dispenser that shoots an armor piece to equip.
         */
        DISPENSER,
        /**
         * When an armor piece breaks to unequip
         */
        BROKE,
        /**
         * When you die causing all armor to unequip
         */
        DEATH,
        ;
    }
}
