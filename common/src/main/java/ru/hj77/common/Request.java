package ru.hj77.common;

import lombok.Value;


@Value
public class Request {
    int id;
    String data;
    RequestTypes type;
}
