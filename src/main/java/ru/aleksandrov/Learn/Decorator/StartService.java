package ru.aleksandrov.Learn.Decorator;

public class StartService extends Handler implements Service, TemplateHandler {
    public StartService(Service service) {
        super(service);
    }

    @Override
    public void performPost() {
        before();
        super.performPost();
    }

    @Override
    public void performGet() {
        before();
        super.performGet();
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
