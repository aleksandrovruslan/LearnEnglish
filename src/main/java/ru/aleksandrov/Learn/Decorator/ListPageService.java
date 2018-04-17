package ru.aleksandrov.Learn.Decorator;

public class ListPageService extends Handler {

    public ListPageService(Service service) {
        super(service);
    }

    @Override
    public void performPost() {
        System.out.println("ListPageService performPost()" + super.message.toUpperCase());
        super.performPost();
    }

    @Override
    public void performGet() {
        super.performGet();
        System.out.println("ListPageService performGet()" + super.message.toUpperCase());
    }

    @Override
    public String getMessage() {
        return super.message;
    }

    @Override
    public int getCount() {
        return super.count;
    }
}
