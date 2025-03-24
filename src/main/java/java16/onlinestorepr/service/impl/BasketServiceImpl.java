package java16.onlinestorepr.service.impl;

import java16.onlinestorepr.repo.jdbc.BasketJdbc;
import java16.onlinestorepr.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketJdbc basketJdbc;

    @Override
    public boolean clearBasket(String jwtToken) {
        return basketJdbc.clearBasket(jwtToken);
    }
}
