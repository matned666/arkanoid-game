package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets.events;

import com.google.gwt.event.shared.GwtEvent;

public class ItemDeleteEvent<T> extends GwtEvent<ItemDeleteHandler<T>> {
    private static Type<ItemDeleteHandler<?>> TYPE;
    private final T deleteItem;

    public static <T> void fire(HasItemDeleteHandlers<T> source, T deleteItem) {
        if (TYPE != null) {
            ItemDeleteEvent<T> event = new ItemDeleteEvent<>(deleteItem);
            source.fireEvent(event);
        }

    }

    public static Type<ItemDeleteHandler<?>> getType() {
        if (TYPE == null) {
            TYPE = new Type<ItemDeleteHandler<?>>();
        }

        return TYPE;
    }

    protected ItemDeleteEvent(T deleteItem) {
        this.deleteItem = deleteItem;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Type<ItemDeleteHandler<T>> getAssociatedType() {
        return (Type) TYPE;
    }

    public T getDeleteItem() {
        return this.deleteItem;
    }


    @Override
    protected void dispatch(ItemDeleteHandler<T> tItemDeleteHandler) {
        tItemDeleteHandler.onDelete(this);
    }
}
