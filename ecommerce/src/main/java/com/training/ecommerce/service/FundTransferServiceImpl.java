package com.training.ecommerce.service;

import com.training.ecommerce.client.FundTransferInterface;
import com.training.ecommerce.dto.AccountInfoDto;
import com.training.ecommerce.dto.CredentialDto;
import com.training.ecommerce.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FundTransferServiceImpl {

    @Autowired
    private FundTransferInterface fundTransfer;

    public CredentialDto register(RegisterDto registerDto) {
        return fundTransfer.register(registerDto.getName(),registerDto.getEmail(),registerDto.getPassword(),registerDto.getGender()
        ,registerDto.getAccountType(),registerDto.getBalance()).getBody();
    }

    /*public String fundTransfer(FundTransferDto fundTransferDto) {
        return fundTransfer.fundTransfer(fundTransferDto.getFromAccountId(), fundTransferDto.getToAccountId(),
                fundTransferDto.getAmount()).getBody();
    }*/

    public AccountInfoDto login(CredentialDto dto) {
        return fundTransfer.login(dto.getEmail(),dto.getPassword()).getBody();
    }
}
