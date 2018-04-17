package ru.aleksandrov.Learn.Decorator;

public class HomeService extends Handler{
    public HomeService(Service service) {
        super(service);
    }

    @Override
    public void performPost() {
        System.out.println("HomeService performPost()" + super.message.toUpperCase());
        super.performPost();
    }

    @Override
    public void performGet() {
        super.performGet();
        System.out.println("HomeService performGet()" + super.message.toUpperCase());
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
