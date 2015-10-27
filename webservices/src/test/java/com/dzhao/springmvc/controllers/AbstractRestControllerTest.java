package com.dzhao.springmvc.controllers;

import com.dzhao.springmvc.model.Customer;
import com.dzhao.springmvc.repositories.CustomerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by dzhao on 21/10/2015.
 */
@WebAppConfiguration
@IntegrationTest
public abstract class AbstractRestControllerTest {
    @Autowired
    protected CustomerRepository customerRepository;

    @Value("${local.server.port}")
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    protected RestTemplate restTemplate = new TestRestTemplate();

    protected Gson gson = new Gson();

    protected <T> T getEntity(final String requestMappingUrl, final Class<T> serviceReturnTypeClass, final Object... parametersInOrderOfAppearance) {
        final HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
        try {
            final ResponseEntity<T> entity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.GET, requestEntity, serviceReturnTypeClass,
                    parametersInOrderOfAppearance);
            return entity.getBody();
        } catch (final Exception ex) {
            // Handle exceptions
        }
        return null;
    }

    protected <T> T postEntity(final String requestMappingUrl, final String requestJson, final Class<T> serviceReturnTypeClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<String>(requestJson, headers);
        try {
            final ResponseEntity<T> entity = restTemplate.postForEntity(getBaseUrl() + requestMappingUrl, requestEntity, serviceReturnTypeClass);
            return entity.getBody();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected <T> T[] getEntityList(final String requestMappingUrl, final Class<T[]> serviceReturnTypeClass, final Object... parametersInOrderOfAppearance) {
        final HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
        try {
            final ResponseEntity<T[]> entity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.GET, requestEntity, serviceReturnTypeClass,
                    parametersInOrderOfAppearance);
            return entity.getBody();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void clearAllCustomers(){
        customerRepository.deleteAll();
    }

    protected void initCustomers(){
        newCustomer("Tony", "Abbott", false);
        newCustomer("Malcolm", "Turnbull", false);
    }


    protected String newCustomer(String firstName, String lastName, Boolean gender){
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setGender(gender);
        customer.setCreationDate(new Date());
        customerRepository.save(customer);
        return customer.getId();
    }

}
