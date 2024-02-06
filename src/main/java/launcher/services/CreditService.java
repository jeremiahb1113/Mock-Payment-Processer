package launcher.services;

import launcher.models.CreditCard;
import launcher.models.User;
import launcher.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService{

    @Autowired
    public CreditRepository creditRepository;



    public void makeCard(CreditCard card){

        creditRepository.save(card);
    }

    public void addCard(Long id, CreditCard card) {

        creditRepository.appendCard(card.getId(),card.getCard_number(),
                card.getCcv(),card.isActive(),card.isEnabled(),card.getFunds(),card.getCard_type(), id);
    }
}
