package com.example.squarerootclient.service;

import com.proto.squareroot.SquareRootRequest;
import com.proto.squareroot.SquareRootResponse;
import com.proto.squareroot.SquareRootServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@RequiredArgsConstructor
public class SquareRootClientService {
    @Value("${grpc.server.port}")
    private Integer port;

    @Value("${grpc.server.host}")
    private String host;
    private SquareRootServiceGrpc.SquareRootServiceBlockingStub client;

    @PostConstruct
    private void init() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext().build();
        this.client = SquareRootServiceGrpc.newBlockingStub(channel);
    }

    public SquareRootResponse squareRootCall(SquareRootRequest squareRootRequest) {
        return client.squareRoot(squareRootRequest);
    }
}
