package com.hendisantika.springbootfilter.web;

import com.hendisantika.springbootfilter.domain.Customer;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-filter
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/01/18
 * Time: 08.14
 * To change this template use File | Settings | File Templates.
 */

@Spec(path = "deleted", constVal = "false", spec = Equal.class)
public interface NotDeletedCustomerSpecification extends Specification<Customer> {
}
