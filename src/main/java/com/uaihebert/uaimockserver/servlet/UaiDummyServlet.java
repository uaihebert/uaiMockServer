package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaidummy.creditcard.DummyCreditCard;
import com.uaihebert.uaidummy.creditcard.DummyCreditCardGenerator;
import com.uaihebert.uaimockserver.dto.response.DummyDataResponseDTO;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UaiDummyServlet extends AbstractServlet {
    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final DummyDataResponseDTO dummyDataResponseDTO = new DummyDataResponseDTO();

        generateNumbers(httpRequest, dummyDataResponseDTO);

        defineAllowedTypes(dummyDataResponseDTO);

        writeInResponse(httpResponse, JsonUtil.toJson(dummyDataResponseDTO));
    }

    private void defineAllowedTypes(final DummyDataResponseDTO dummyDataResponseDTO) {
        final List<String> allowedCreditCardList = DummyCreditCardGenerator.listAllSupportedCreditCards();
        dummyDataResponseDTO.setSupportedCreditCardList(allowedCreditCardList);
    }

    private void generateNumbers(final HttpServletRequest httpRequest, final DummyDataResponseDTO dummyDataResponseDTO) {
        final String selectedCardType = httpRequest.getParameter("cardType");

        if (StringUtils.isBlank(selectedCardType)) {
            return;
        }

        final List<String> generatedPanList = new ArrayList<String>();
        final List<String> generatedCvvList = new ArrayList<String>();
        final List<String> generatedExpirationDateList = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            final DummyCreditCard creditCard = DummyCreditCardGenerator.generateByName(selectedCardType);
            generatedPanList.add(creditCard.getNumber());
            generatedCvvList.add(creditCard.getSecurityNumber());
            generatedExpirationDateList.add(creditCard.getExpirationDate());
        }

        dummyDataResponseDTO.setGeneratedPanList(generatedPanList);
        dummyDataResponseDTO.setGeneratedCvvList(generatedCvvList);
        dummyDataResponseDTO.setGeneratedExpirationDateList(generatedExpirationDateList);
    }
}