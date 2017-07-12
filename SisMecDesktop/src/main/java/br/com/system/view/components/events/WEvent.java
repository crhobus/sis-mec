package br.com.system.view.components.events;

/**
 *
 * @author crhobus
 */
public class WEvent {

    private final WType type;
    private final WComponentType componentType;

    public WEvent(WType type, WComponentType componentType) {
        this.type = type;
        this.componentType = componentType;
    }

    public WType getType() {
        return type;
    }

    public WComponentType getComponentType() {
        return componentType;
    }
}
