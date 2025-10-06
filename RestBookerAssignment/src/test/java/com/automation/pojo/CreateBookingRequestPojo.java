package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateBookingRequestPojo {
    String firstname;
    String lastname;
    int totalprice;
    boolean depositpaid;
    String additionalneeds;
    BookingDate bookingdates;

}
