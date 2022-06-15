package com.pos.client.cli;

import com.pos.cart.model.Cart;
import com.pos.cart.model.User;
import com.pos.cart.model.UserItem;
import com.pos.client.service.ClientService;
import com.pos.database.model.Item;
import com.pos.database.model.Product;
import com.pos.database.model.Status;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.shell.Availability;
import org.springframework.shell.Shell;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.commands.Quit;
import org.springframework.web.reactive.function.client.WebClient;
import com.alibaba.fastjson.*;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@ShellComponent
public class PosCommandLine {

    private ClientService clientService;

    private String uid;

    private Product selection;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8001").build();


    private Subscription productSubscription, deliverySubscription;

    public PosCommandLine(ClientService clientService) {
        this.clientService = clientService;
    }

    private void print(String buffer){
        System.out.println(buffer);
    }

    private void printError(String error){
        System.err.println(error);
    }

    private Availability loginCheck(){
        return uid != null
                ? Availability.available()
                : Availability.unavailable("You Are Not Login");
    }

    private Availability selectionCheck(){
        return selection !=null
                ? Availability.available()
                : Availability.unavailable("You Have Not Select A Product");
    }

    private Availability productSubscribeCheck(){
        return productSubscription !=null
                ? Availability.available()
                : Availability.unavailable("You Should Fetch Product First");
    }

    private Availability deliverySubscribeCheck(){
        return deliverySubscription !=null
                ? Availability.available()
                : Availability.unavailable("You Should Fetch Deliveries First");
    }

    private Availability addCartCheck(){
        Availability res = loginCheck();
        if(res.isAvailable()){
            return selectionCheck();
        }
        return res;
    }

    class PosQuit implements Quit.Command{
        @ShellMethod
        void quit(){
            deliverySubscription.cancel();
            productSubscription.cancel();
            System.exit(0);
        }
    }

    @ShellMethod("login")
    public String login(String uid){
        this.uid = uid;
        return String.format("Welcome %s !", uid);
    }

    @ShellMethod("fetch products")
    public void fetchProducts(){
        webClient.get()
                .uri("/product")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                .map(JSONObject::toJSONString)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        productSubscription = subscription;
                    }

                    @Override
                    public void onNext(String s) {
                        print(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        printError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        print("These are All Products");
                    }
                });
    }

    @ShellMethod("show products")
    @ShellMethodAvailability("productSubscribeCheck")
    public void showProducts(@ShellOption(defaultValue = "5") int size){
        productSubscription.request(size);
    }

    @ShellMethod("reset products subscription")
    public void selectProduct(String asin){
        webClient.get()
                .uri("/product/{asin}", asin)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .subscribe(s -> selection = s);
    }

    @ShellMethod("add product")
    @ShellMethodAvailability("addCartCheck")
    public void addProduct(int quantity){
        webClient.post()
                .uri("/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new UserItem(new Item(selection, quantity), uid)), UserItem.class);
    }

    @ShellMethod("show cart")
    @ShellMethodAvailability("loginCheck")
    public void showCart(){
        webClient.post()
                .uri("/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new User(uid)), User.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Cart.class)
                .map(JSONObject::toJSONString)
                .subscribe(this::print);
    }

    @ShellMethod("sum")
    @ShellMethodAvailability("loginCheck")
    public void cartTotal(){
        webClient.post()
                .uri("/cart/total")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new User(uid)), User.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Boolean.class)
                .map(String::valueOf)
                .subscribe(this::print);
    }

    @ShellMethod("checkout")
    @ShellMethodAvailability("loginCheck")
    public void cartCheckout(){
        webClient.post()
                .uri("/cart/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new User(uid)), User.class);
    }

    @ShellMethod("fetch delivery")
    @ShellMethodAvailability("deliverySubscribeCheck")
    public void fetchDelivery(){
        webClient.post()
                .uri("/order/")
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just(uid), String.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Status.class)
                .map(JSONObject::toJSONString)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        deliverySubscription = subscription;
                        print("Fetch Success");
                    }

                    @Override
                    public void onNext(String s) {
                        print(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        printError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        print("These are All Deliveries");
                    }
                });
    }

    @ShellMethod("show deliveries")
    @ShellMethodAvailability("deliverySubscribeCheck")
    public void showDeliveries(@ShellOption(defaultValue = "5") int size){
        deliverySubscription.request(size);
    }
}
