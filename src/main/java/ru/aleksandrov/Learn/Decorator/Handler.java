package ru.aleksandrov.Learn.Decorator;

abstract class Handler implements TemplateHandler, Service {
    protected Service service;
    protected String message;
    protected int count;

    public Handler(Service service){
        this.service = service;
        this.message = service.getMessage();
        this.count = service.getCount();
    }

    @Override
    public void performPost() {
//        before();
        service.performPost();
//        after();
    }

    @Override
    public void performGet() {
//        before();
        service.performGet();
//        after();
    }
}
