package ru.aleksandrov.Learn.Decorator;

public class EndService extends Handler implements Service, TemplateHandler {
    public EndService(Service service) {
        super(service);
    }

    @Override
    public void performPost() {
        super.performPost();
        after();
    }

    @Override
    public void performGet() {
        super.performGet();
        after();
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
