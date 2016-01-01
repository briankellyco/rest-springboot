package co.bk.restspringboot;

import co.bk.restspringboot.cmd.OrderCreateCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    /*
     * curl --header "Content-type: application/json" --header "Accept: application/json"  --data '{"order":{"orderNumber":"12345"}, "orderLines":[{"articleNumber":"M2134F","articleName":"myArticle","unitPrice":"1234","quantity": 4}]}' http://localhost:8080/orders
     * curl --header "Content-type: application/json" --header "Accept: application/json"  --data '{"order":{"orderNumber":"22222"},"orderLines":[{"articleNumber":"M2134F","articleName":"myFirstArticle","unitPrice":"1234","quantity":4},{"articleNumber":"VV23433","articleName":"mySecondArticle","unitPrice":"4242","quantity":2}]}' http://localhost:8080/orders
     */
    @RequestMapping(value = "/orders", method=RequestMethod.POST, consumes = "application/json")
    public void addOrder(@RequestBody OrderCreateCmd cmd, HttpServletResponse response) {

        Order order = cmd.getOrder();
        order.setOrderLines(cmd.getOrderLines());
        Order result = orderRepository.save(order);

        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}").build()
                        .expand(result.getPk()).toUri();

        response.setHeader("Location", location.toString());
        response.setStatus(HttpStatus.CREATED.value());
    }


    /*
     * curl -X GET -H "Content-Type: application/json" http://localhost:8080/orders/1
     */
    @RequestMapping(value="/orders/{id}", method=RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Order getOrder(@PathVariable int id, HttpServletResponse response){
        Order o = orderRepository.findOne(new Long(id));

        if (o == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return o;
    }

//    @RequestMapping(value="/orders/{id}", method=RequestMethod.PUT, consumes = "application/json")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateOrder(@PathVariable int id){
//        Order o = orderRepository.findOne(new Long(id));
//
//
//        //return orderService.findOrder(id);
//    }

}
