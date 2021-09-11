package com.gaw.currencyexchangeservice;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if(currencyExchange ==null) {
            throw new RuntimeException("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }

    @GetMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstanceByApplicationName(@PathVariable String applicationName){
        return this.discoveryClient.getInstances(applicationName);
    }
}

