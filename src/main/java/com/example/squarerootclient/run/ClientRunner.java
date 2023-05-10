package com.example.squarerootclient.run;


import com.example.squarerootclient.service.SquareRootClientService;
import com.proto.squareroot.SquareRootRequest;
import io.grpc.StatusRuntimeException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class ClientRunner implements CommandLineRunner {
    SquareRootClientService squareRootClientService;

    @Override
    public void run(String... args) {
        int inputValue = 10;
        SquareRootRequest squareRootRequest = SquareRootRequest.newBuilder()
                .setInputValue(inputValue)
                .build();
        try {
            double result = squareRootClientService.squareRootCall(squareRootRequest)
                    .getResult();
            log.info("Result: {}", result);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
        }
    }
}
